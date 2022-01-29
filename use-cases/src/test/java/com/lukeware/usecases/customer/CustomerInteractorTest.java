package com.lukeware.usecases.customer;

import com.lukeware.usecases.accountholder.AccountHolderDsResponse;
import com.lukeware.usecases.accountholder.IAccountHolderGateway;
import com.lukeware.usecases.banckaccount.IBankAccountMapper;
import com.lukeware.usecases.banckaccount.IBankAccountGateway;
import com.lukeware.usecases.customer.boundary.ICustomerOutputBoundary;
import com.lukeware.usecases.customer.ds.CustomerDsRequest;
import com.lukeware.usecases.customer.ds.CustomerDsResponse;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.Collections;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author diegomorais
 */
@ExtendWith(MockitoExtension.class)
@DisplayName("CustomerInteractor test")
class CustomerInteractorTest {

  @InjectMocks
  CustomerInteractor customerInteractor;
  @Mock
  IAccountHolderGateway accountHolderGateway;
  @Mock
  IBankAccountGateway bankAccountRepository;
  @Mock
  ICustomerOutputBoundary customerPresenter;

  @Test
  @DisplayName("1 - Customer without bank bankaccount.")
  void customerWithoutBankAccount() {
    /* preparation */
    final var customerRequest = new CustomerDsRequest("789123456", "999.999.999-99");
    final var customerResponse = new CustomerDsResponse("789123456", "999.999.999-99", TypeCustomer.NC);

    Mockito.lenient().when(this.bankAccountRepository.findAll("789123456")).thenReturn(Collections.emptySet());
    Mockito.lenient().when(this.customerPresenter.successView(customerResponse)).thenReturn(customerResponse);

    /* execution */
    final var response = customerInteractor.validateActiveCustomerPf(customerRequest);

    /* validation */
    Assertions.assertThat(response).isNotNull();
    Assertions.assertThat(response.hashCode()).isNotNull();
    Assertions.assertThat(response.equals(response)).isNotNull();
    Assertions.assertThat(response.getType()).isNotNull().isEqualTo(TypeCustomer.NC);
    Assertions.assertThat(response.getIdentifierCode()).isNotNull().isEqualTo("789123456");
    Assertions.assertThat(response.getIdentifierDocument()).isNotNull().isEqualTo("999.999.999-99");
  }

  @Test
  @DisplayName("2 - Customer with a savings bankaccount.")
  void customerWithSavingsAccount() {
    /* preparation */
    final var customerRequest = new CustomerDsRequest("789123456", "999.999.999-99");
    final var customerResponse = new CustomerDsResponse("789123456", "999.999.999-99", TypeCustomer.NC);

    final var accountHolderResponse = new AccountHolderDsResponse("789123456", true, 1);
    final var accountHolders = Stream.of(accountHolderResponse).collect(Collectors.toSet());
    final var bankAccountMapper = Mockito.mock(IBankAccountMapper.class);
    final var accounts = Stream.of(bankAccountMapper).collect(Collectors.toSet());

    Mockito.lenient().when(bankAccountMapper.getIdentifierCode()).thenReturn("789123456");
    Mockito.lenient().when(bankAccountMapper.isActive()).thenReturn(true);
    Mockito.lenient().when(bankAccountMapper.getType()).thenReturn("SAVINGS_ACCOUNT");
    Mockito.lenient().when(bankAccountMapper.isExternalMovement()).thenReturn(false);
    Mockito.lenient().when(bankAccountMapper.getCustomerId()).thenReturn("999.999.999-99");
    Mockito.lenient().when(bankAccountMapper.getOpenDate()).thenReturn(LocalDate.now());
    Mockito.lenient().when(bankAccountMapper.getLastMoveDate()).thenReturn(LocalDate.now());
    Mockito.lenient().when(this.bankAccountRepository.findAll("789123456")).thenReturn(accounts);
    Mockito.lenient().when(this.accountHolderGateway.findAll("789123456")).thenReturn(accountHolders);
    Mockito.lenient().when(this.customerPresenter.successView(customerResponse)).thenReturn(customerResponse);

    /* execution */
    final var response = customerInteractor.validateActiveCustomerPf(customerRequest);

    /* validation */
    Assertions.assertThat(response).isNotNull();
    Assertions.assertThat(response.getType()).isNotNull().isEqualTo(TypeCustomer.NC);
    Assertions.assertThat(response.getIdentifierCode()).isNotNull().isEqualTo("789123456");
    Assertions.assertThat(response.getIdentifierDocument()).isNotNull().isEqualTo("999.999.999-99");
  }


  @Test
  @DisplayName("3 - Non-bankaccount holder customer.")
  void nonAccountHolderCustomer() {
    /* preparation */
    final var customerRequest = new CustomerDsRequest("789123456", "999.999.999-99");
    final var customerResponse = new CustomerDsResponse("789123456", "999.999.999-99", TypeCustomer.NC);

    final var accountHolderResponse = new AccountHolderDsResponse("789123456", false, 2);
    final var accountHolders = Stream.of(accountHolderResponse).collect(Collectors.toSet());
    final var bankAccountMapper = Mockito.mock(IBankAccountMapper.class);
    final var accounts = Stream.of(bankAccountMapper).collect(Collectors.toSet());

    Mockito.lenient().when(bankAccountMapper.getIdentifierCode()).thenReturn("789123456");
    Mockito.lenient().when(bankAccountMapper.isActive()).thenReturn(true);
    Mockito.lenient().when(bankAccountMapper.getType()).thenReturn("CHECKING_ACCOUNT_PF");
    Mockito.lenient().when(bankAccountMapper.isExternalMovement()).thenReturn(false);
    Mockito.lenient().when(bankAccountMapper.getCustomerId()).thenReturn("999.999.999-99");
    Mockito.lenient().when(bankAccountMapper.getOpenDate()).thenReturn(LocalDate.now().minusDays(180));
    Mockito.lenient().when(bankAccountMapper.getLastMoveDate()).thenReturn(LocalDate.now().minusDays(90));
    Mockito.lenient().when(this.bankAccountRepository.findAll("789123456")).thenReturn(accounts);
    Mockito.lenient().when(this.accountHolderGateway.findAll("789123456")).thenReturn(accountHolders);
    Mockito.lenient().when(this.customerPresenter.successView(customerResponse)).thenReturn(customerResponse);

    /* execution */
    final var response = customerInteractor.validateActiveCustomerPf(customerRequest);

    /* validation */
    Assertions.assertThat(response).isNotNull();
    Assertions.assertThat(response.getType()).isNotNull().isEqualTo(TypeCustomer.NC);
    Assertions.assertThat(response.getIdentifierCode()).isNotNull().isEqualTo("789123456");
    Assertions.assertThat(response.getIdentifierDocument()).isNotNull().isEqualTo("999.999.999-99");
  }

  @Test
  @DisplayName("4 - Customer with inactive checking bankaccount.")
  void CustomerWithInactivecheckingAccount() {
    /* preparation */
    final var customerRequest = new CustomerDsRequest("789123456", "999.999.999-99");
    final var customerResponse = new CustomerDsResponse("789123456", "999.999.999-99", TypeCustomer.IC);

    final var accountHolderResponse = new AccountHolderDsResponse("789123456", true, 1);
    final var accountHolders = Stream.of(accountHolderResponse).collect(Collectors.toSet());
    final var bankAccountMapper = Mockito.mock(IBankAccountMapper.class);
    final var accounts = Stream.of(bankAccountMapper).collect(Collectors.toSet());

    Mockito.lenient().when(bankAccountMapper.getIdentifierCode()).thenReturn("789123456");
    Mockito.lenient().when(bankAccountMapper.isActive()).thenReturn(false);
    Mockito.lenient().when(bankAccountMapper.getType()).thenReturn("CHECKING_ACCOUNT_PF");
    Mockito.lenient().when(bankAccountMapper.isExternalMovement()).thenReturn(false);
    Mockito.lenient().when(bankAccountMapper.getCustomerId()).thenReturn("999.999.999-99");
    Mockito.lenient().when(bankAccountMapper.getOpenDate()).thenReturn(LocalDate.now().minusDays(180));
    Mockito.lenient().when(bankAccountMapper.getLastMoveDate()).thenReturn(LocalDate.now().minusDays(90));
    Mockito.lenient().when(this.bankAccountRepository.findAll("789123456")).thenReturn(accounts);
    Mockito.lenient().when(this.accountHolderGateway.findAll("789123456")).thenReturn(accountHolders);
    Mockito.lenient().when(this.customerPresenter.successView(customerResponse)).thenReturn(customerResponse);

    /* execution */
    final var response = customerInteractor.validateActiveCustomerPf(customerRequest);

    /* validation */
    Assertions.assertThat(response).isNotNull();
    Assertions.assertThat(response.getType()).isNotNull().isEqualTo(TypeCustomer.IC);
    Assertions.assertThat(response.getIdentifierCode()).isNotNull().isEqualTo("789123456");
    Assertions.assertThat(response.getIdentifierDocument()).isNotNull().isEqualTo("999.999.999-99");
  }

  @Test
  @DisplayName("5 - Customer with a current bankaccount with external movement.")
  void customerWithCurrentAccountWithExternalMovement() {
    /* preparation */
    final var customerRequest = new CustomerDsRequest("789123456", "999.999.999-99");
    final var customerResponse = new CustomerDsResponse("789123456", "999.999.999-99", TypeCustomer.IC);

    final var accountHolderResponse = new AccountHolderDsResponse("789123456", true, 1);
    final var accountHolders = Stream.of(accountHolderResponse).collect(Collectors.toSet());
    final var bankAccountMapper = Mockito.mock(IBankAccountMapper.class);
    final var accounts = Stream.of(bankAccountMapper).collect(Collectors.toSet());

    Mockito.lenient().when(bankAccountMapper.getIdentifierCode()).thenReturn("789123456");
    Mockito.lenient().when(bankAccountMapper.getCustomerId()).thenReturn("999.999.999-99");
    Mockito.lenient().when(bankAccountMapper.isActive()).thenReturn(true);
    Mockito.lenient().when(bankAccountMapper.isExternalMovement()).thenReturn(true);
    Mockito.lenient().when(bankAccountMapper.getType()).thenReturn("CHECKING_ACCOUNT_PF");
    Mockito.lenient().when(bankAccountMapper.getOpenDate()).thenReturn(LocalDate.now().minusDays(180));
    Mockito.lenient().when(bankAccountMapper.getLastMoveDate()).thenReturn(LocalDate.now().minusDays(90));
    Mockito.lenient().when(this.bankAccountRepository.findAll("789123456")).thenReturn(accounts);
    Mockito.lenient().when(this.accountHolderGateway.findAll("789123456")).thenReturn(accountHolders);
    Mockito.lenient().when(this.customerPresenter.successView(customerResponse)).thenReturn(customerResponse);

    /* execution */
    final var response = customerInteractor.validateActiveCustomerPf(customerRequest);

    /* validation */
    Assertions.assertThat(response).isNotNull();
    Assertions.assertThat(response.getType()).isNotNull().isEqualTo(TypeCustomer.IC);
    Assertions.assertThat(response.getIdentifierCode()).isNotNull().isEqualTo("789123456");
    Assertions.assertThat(response.getIdentifierDocument()).isNotNull().isEqualTo("999.999.999-99");
  }

  @Test
  @DisplayName("6 - customer with current bankaccount with date of last transaction greater than 180 days")
  void customerWithCurrentAccountWithOpeningDateLessThan180Days() {
    /* preparation */
    final var customerRequest = new CustomerDsRequest("789123456", "999.999.999-99");
    final var customerResponse = new CustomerDsResponse("789123456", "999.999.999-99", TypeCustomer.IC);

    final var accountHolderResponse = new AccountHolderDsResponse("789123456", true, 1);
    final var accountHolders = Stream.of(accountHolderResponse).collect(Collectors.toSet());
    final var bankAccountMapper = Mockito.mock(IBankAccountMapper.class);
    final var accounts = Stream.of(bankAccountMapper).collect(Collectors.toSet());

    Mockito.lenient().when(bankAccountMapper.getIdentifierCode()).thenReturn("789123456");
    Mockito.lenient().when(bankAccountMapper.getCustomerId()).thenReturn("999.999.999-99");
    Mockito.lenient().when(bankAccountMapper.isActive()).thenReturn(true);
    Mockito.lenient().when(bankAccountMapper.isExternalMovement()).thenReturn(false);
    Mockito.lenient().when(bankAccountMapper.getType()).thenReturn("CHECKING_ACCOUNT_PF");
    Mockito.lenient().when(bankAccountMapper.getOpenDate()).thenReturn(LocalDate.now().minusDays(170));
    Mockito.lenient().when(bankAccountMapper.getLastMoveDate()).thenReturn(LocalDate.now().minusDays(90));
    Mockito.lenient().when(this.bankAccountRepository.findAll("789123456")).thenReturn(accounts);
    Mockito.lenient().when(this.accountHolderGateway.findAll("789123456")).thenReturn(accountHolders);
    Mockito.lenient().when(this.customerPresenter.successView(customerResponse)).thenReturn(customerResponse);

    /* execution */
    final var response = customerInteractor.validateActiveCustomerPf(customerRequest);

    /* validation */
    Assertions.assertThat(response).isNotNull();
    Assertions.assertThat(response.getType()).isNotNull().isEqualTo(TypeCustomer.IC);
    Assertions.assertThat(response.getIdentifierCode()).isNotNull().isEqualTo("789123456");
    Assertions.assertThat(response.getIdentifierDocument()).isNotNull().isEqualTo("999.999.999-99");
  }


  @Test
  @DisplayName("7 - Customer with current bankaccount with date of last transaction greater than 90 days.")
  void customerWithCurrentAccountWithDateOfLastTransactionGreaterThan90Days() {
    /* preparation */
    final var customerRequest = new CustomerDsRequest("789123456", "999.999.999-99");
    final var customerResponse = new CustomerDsResponse("789123456", "999.999.999-99", TypeCustomer.IC);

    final var accountHolderResponse = new AccountHolderDsResponse("789123456", true, 1);
    final var accountHolders = Stream.of(accountHolderResponse).collect(Collectors.toSet());
    final var bankAccountMapper = Mockito.mock(IBankAccountMapper.class);
    final var accounts = Stream.of(bankAccountMapper).collect(Collectors.toSet());

    Mockito.lenient().when(bankAccountMapper.getIdentifierCode()).thenReturn("789123456");
    Mockito.lenient().when(bankAccountMapper.getCustomerId()).thenReturn("999.999.999-99");
    Mockito.lenient().when(bankAccountMapper.isActive()).thenReturn(true);
    Mockito.lenient().when(bankAccountMapper.isExternalMovement()).thenReturn(false);
    Mockito.lenient().when(bankAccountMapper.getType()).thenReturn("CHECKING_ACCOUNT_PF");
    Mockito.lenient().when(bankAccountMapper.getOpenDate()).thenReturn(LocalDate.now().minusDays(180));
    Mockito.lenient().when(bankAccountMapper.getLastMoveDate()).thenReturn(LocalDate.now().minusDays(100));
    Mockito.lenient().when(this.bankAccountRepository.findAll("789123456")).thenReturn(accounts);
    Mockito.lenient().when(this.accountHolderGateway.findAll("789123456")).thenReturn(accountHolders);
    Mockito.lenient().when(this.customerPresenter.successView(customerResponse)).thenReturn(customerResponse);

    /* execution */
    final var response = customerInteractor.validateActiveCustomerPf(customerRequest);

    /* validation */
    Assertions.assertThat(response).isNotNull();
    Assertions.assertThat(response.getType()).isNotNull().isEqualTo(TypeCustomer.IC);
    Assertions.assertThat(response.getIdentifierCode()).isNotNull().isEqualTo("789123456");
    Assertions.assertThat(response.getIdentifierDocument()).isNotNull().isEqualTo("999.999.999-99");
  }

  @Test
  @DisplayName("8 - Customer with banck bankaccount ativa.")
  void CustomerWithBanckAccountAtiva() {
    /* preparation */
    final var customerRequest = new CustomerDsRequest("789123456", "999.999.999-99");
    final var customerResponse = new CustomerDsResponse("789123456", "999.999.999-99", TypeCustomer.AC);

    final var accountHolderResponse = new AccountHolderDsResponse("789123456", true, 1);
    final var accountHolders = Stream.of(accountHolderResponse).collect(Collectors.toSet());
    final var bankAccountMapper = Mockito.mock(IBankAccountMapper.class);
    final var accounts = Stream.of(bankAccountMapper).collect(Collectors.toSet());

    Mockito.lenient().when(bankAccountMapper.getIdentifierCode()).thenReturn("789123456");
    Mockito.lenient().when(bankAccountMapper.getCustomerId()).thenReturn("999.999.999-99");
    Mockito.lenient().when(bankAccountMapper.isActive()).thenReturn(true);
    Mockito.lenient().when(bankAccountMapper.isExternalMovement()).thenReturn(false);
    Mockito.lenient().when(bankAccountMapper.getType()).thenReturn("CHECKING_ACCOUNT_PF");
    Mockito.lenient().when(bankAccountMapper.getOpenDate()).thenReturn(LocalDate.now().minusDays(180));
    Mockito.lenient().when(bankAccountMapper.getLastMoveDate()).thenReturn(LocalDate.now().minusDays(90));
    Mockito.lenient().when(this.bankAccountRepository.findAll("789123456")).thenReturn(accounts);
    Mockito.lenient().when(this.accountHolderGateway.findAll("789123456")).thenReturn(accountHolders);
    Mockito.lenient().when(this.customerPresenter.successView(customerResponse)).thenReturn(customerResponse);

    /* execution */
    final var response = customerInteractor.validateActiveCustomerPf(customerRequest);

    /* validation */
    Assertions.assertThat(response).isNotNull();
    Assertions.assertThat(response.getType()).isNotNull().isEqualTo(TypeCustomer.AC);
    Assertions.assertThat(response.getType().toString()).isNotNull().isEqualTo(TypeCustomer.AC.toString());
    Assertions.assertThat(response.getIdentifierCode()).isNotNull().isEqualTo("789123456");
    Assertions.assertThat(response.getIdentifierDocument()).isNotNull().isEqualTo("999.999.999-99");
  }

}