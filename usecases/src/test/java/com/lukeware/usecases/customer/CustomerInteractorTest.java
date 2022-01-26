package com.lukeware.usecases.customer;

import com.lukeware.usecases.accountholder.AccountHolderResponse;
import com.lukeware.usecases.accountholder.IAccountHolderGateway;
import com.lukeware.usecases.banckaccount.BankAccountResponse;
import com.lukeware.usecases.banckaccount.IBankAccountGateway;
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
  IAccountHolderGateway iAccountHolderGateway;
  @Mock
  IBankAccountGateway bankAccountGateway;
  
  @Test
  @DisplayName("1 - Customer without bank account.")
  void customerWithoutBankAccount() {
    final var customerRequest = new CustomerRequest("789123456", "999.999.999-99");
    
    Mockito.lenient().when(this.bankAccountGateway.findAll("789123456")).thenReturn(Collections.emptySet());
    
    final var customerResponse = customerInteractor.validateActiveCustomer(customerRequest);
    
    Assertions.assertThat(customerResponse).isNotNull();
    Assertions.assertThat(customerResponse.type()).isNotNull().isEqualTo(TypeCustomer.NC);
    Assertions.assertThat(customerResponse.identifierCode()).isNotNull().isEqualTo("789123456");
    Assertions.assertThat(customerResponse.identifierDocument()).isNotNull().isEqualTo("999.999.999-99");
  }
  
  @Test
  @DisplayName("2 - Customer with a savings account.")
  void customerWithSavingsAccount() {
    final var customerRequest = new CustomerRequest("789123456", "999.999.999-99");
    final var bankAccountResponse = new BankAccountResponse("789123456",
                                                            true,
                                                            false,
                                                            "SAVINGS_ACCOUNT",
                                                            LocalDate.now(),
                                                            LocalDate.now()
    );
    final var accounts = Stream.of(bankAccountResponse).collect(Collectors.toSet());
    final var accountHolderResponse = new AccountHolderResponse("789123456", true, 1);
    final var accountHolders = Stream.of(accountHolderResponse).collect(Collectors.toSet());
    
    Mockito.lenient().when(this.bankAccountGateway.findAll("789123456")).thenReturn(accounts);
    Mockito.lenient().when(this.iAccountHolderGateway.findAll("789123456")).thenReturn(accountHolders);
    
    final var customerResponse = customerInteractor.validateActiveCustomer(customerRequest);
    
    Assertions.assertThat(customerResponse).isNotNull();
    Assertions.assertThat(customerResponse.type()).isNotNull().isEqualTo(TypeCustomer.NC);
    Assertions.assertThat(customerResponse.identifierCode()).isNotNull().isEqualTo("789123456");
    Assertions.assertThat(customerResponse.identifierDocument()).isNotNull().isEqualTo("999.999.999-99");
  }
  
  @Test
  @DisplayName("3 - Non-account holder customer.")
  void nonAccountHolderCustomer() {
    final var customerRequest = new CustomerRequest("789123456", "999.999.999-99");
    final var bankAccountResponse = new BankAccountResponse("789123456",
                                                            true,
                                                            false,
                                                            "CHECKING_ACCOUNT",
                                                            LocalDate.now().minusDays(180),
                                                            LocalDate.now().minusDays(90)
    );
    final var accounts = Stream.of(bankAccountResponse).collect(Collectors.toSet());
    final var accountHolderResponse = new AccountHolderResponse("789123456", false, 1);
    final var accountHolders = Stream.of(accountHolderResponse).collect(Collectors.toSet());
    
    Mockito.lenient().when(this.bankAccountGateway.findAll("789123456")).thenReturn(accounts);
    Mockito.lenient().when(this.iAccountHolderGateway.findAll("789123456")).thenReturn(accountHolders);
    
    final var customerResponse = customerInteractor.validateActiveCustomer(customerRequest);
    
    Assertions.assertThat(customerResponse).isNotNull();
    Assertions.assertThat(customerResponse.type()).isNotNull().isEqualTo(TypeCustomer.NC);
    Assertions.assertThat(customerResponse.identifierCode()).isNotNull().isEqualTo("789123456");
    Assertions.assertThat(customerResponse.identifierDocument()).isNotNull().isEqualTo("999.999.999-99");
  }
  
  @Test
  @DisplayName("4 - Customer with inactive checking account.")
  void CustomerWithInactivecheckingAccount() {
    final var customerRequest = new CustomerRequest("789123456", "999.999.999-99");
    final var bankAccountResponse = new BankAccountResponse("789123456",
                                                            false,
                                                            false,
                                                            "CHECKING_ACCOUNT",
                                                            LocalDate.now().minusDays(180),
                                                            LocalDate.now().minusDays(90)
    );
    final var accounts = Stream.of(bankAccountResponse).collect(Collectors.toSet());
    final var accountHolderResponse = new AccountHolderResponse("789123456", true, 1);
    final var accountHolders = Stream.of(accountHolderResponse).collect(Collectors.toSet());
    
    Mockito.lenient().when(this.bankAccountGateway.findAll("789123456")).thenReturn(accounts);
    Mockito.lenient().when(this.iAccountHolderGateway.findAll("789123456")).thenReturn(accountHolders);
    
    final var customerResponse = customerInteractor.validateActiveCustomer(customerRequest);
    
    Assertions.assertThat(customerResponse).isNotNull();
    Assertions.assertThat(customerResponse.type()).isNotNull().isEqualTo(TypeCustomer.IC);
    Assertions.assertThat(customerResponse.identifierCode()).isNotNull().isEqualTo("789123456");
    Assertions.assertThat(customerResponse.identifierDocument()).isNotNull().isEqualTo("999.999.999-99");
  }
  
  @Test
  @DisplayName("5 - Customer with a current account with external movement.")
  void customerWithCurrentAccountWithExternalMovement() {
    final var customerRequest = new CustomerRequest("789123456", "999.999.999-99");
    final var bankAccountResponse = new BankAccountResponse("789123456",
                                                            true,
                                                            true,
                                                            "CHECKING_ACCOUNT",
                                                            LocalDate.now().minusDays(180),
                                                            LocalDate.now().minusDays(90)
    );
    final var accounts = Stream.of(bankAccountResponse).collect(Collectors.toSet());
    final var accountHolderResponse = new AccountHolderResponse("789123456", true, 1);
    final var accountHolders = Stream.of(accountHolderResponse).collect(Collectors.toSet());
    
    Mockito.lenient().when(this.bankAccountGateway.findAll("789123456")).thenReturn(accounts);
    Mockito.lenient().when(this.iAccountHolderGateway.findAll("789123456")).thenReturn(accountHolders);
    
    final var customerResponse = customerInteractor.validateActiveCustomer(customerRequest);
    
    Assertions.assertThat(customerResponse).isNotNull();
    Assertions.assertThat(customerResponse.type()).isNotNull().isEqualTo(TypeCustomer.IC);
    Assertions.assertThat(customerResponse.identifierCode()).isNotNull().isEqualTo("789123456");
    Assertions.assertThat(customerResponse.identifierDocument()).isNotNull().isEqualTo("999.999.999-99");
  }
  
  @Test
  @DisplayName("6 - customer with current account with date of last transaction greater than 180 days")
  void customerWithCurrentAccountWithOpeningDateLessThan180Days() {
    final var customerRequest = new CustomerRequest("789123456", "999.999.999-99");
    final var bankAccountResponse = new BankAccountResponse("789123456",
                                                            true,
                                                            false,
                                                            "CHECKING_ACCOUNT",
                                                            LocalDate.now().minusDays(170),
                                                            LocalDate.now().minusDays(90)
    );
    final var accounts = Stream.of(bankAccountResponse).collect(Collectors.toSet());
    final var accountHolderResponse = new AccountHolderResponse("789123456", true, 1);
    final var accountHolders = Stream.of(accountHolderResponse).collect(Collectors.toSet());
    
    Mockito.lenient().when(this.bankAccountGateway.findAll("789123456")).thenReturn(accounts);
    Mockito.lenient().when(this.iAccountHolderGateway.findAll("789123456")).thenReturn(accountHolders);
    
    final var customerResponse = customerInteractor.validateActiveCustomer(customerRequest);
    
    Assertions.assertThat(customerResponse).isNotNull();
    Assertions.assertThat(customerResponse.type()).isNotNull().isEqualTo(TypeCustomer.IC);
    Assertions.assertThat(customerResponse.identifierCode()).isNotNull().isEqualTo("789123456");
    Assertions.assertThat(customerResponse.identifierDocument()).isNotNull().isEqualTo("999.999.999-99");
  }
  
  
  @Test
  @DisplayName("7 - Customer with current account with date of last transaction greater than 90 days.")
  void customerWithCurrentAccountWithDateOfLastTransactionGreaterThan90Days() {
    final var customerRequest = new CustomerRequest("789123456", "999.999.999-99");
    final var bankAccountResponse = new BankAccountResponse("789123456",
                                                            true,
                                                            false,
                                                            "CHECKING_ACCOUNT",
                                                            LocalDate.now().minusDays(180),
                                                            LocalDate.now().minusDays(100)
    );
    final var accounts = Stream.of(bankAccountResponse).collect(Collectors.toSet());
    final var accountHolderResponse = new AccountHolderResponse("789123456", true, 1);
    final var accountHolders = Stream.of(accountHolderResponse).collect(Collectors.toSet());
    
    Mockito.lenient().when(this.bankAccountGateway.findAll("789123456")).thenReturn(accounts);
    Mockito.lenient().when(this.iAccountHolderGateway.findAll("789123456")).thenReturn(accountHolders);
    
    final var customerResponse = customerInteractor.validateActiveCustomer(customerRequest);
    
    Assertions.assertThat(customerResponse).isNotNull();
    Assertions.assertThat(customerResponse.type()).isNotNull().isEqualTo(TypeCustomer.IC);
    Assertions.assertThat(customerResponse.identifierCode()).isNotNull().isEqualTo("789123456");
    Assertions.assertThat(customerResponse.identifierDocument()).isNotNull().isEqualTo("999.999.999-99");
  }
  
  @Test
  @DisplayName("8 - Customer with banck account ativa.")
  void CustomerWithBanckAccountAtiva() {
    final var customerRequest = new CustomerRequest("789123456", "999.999.999-99");
    final var bankAccountResponse = new BankAccountResponse("789123456",
                                                            true,
                                                            false,
                                                            "CHECKING_ACCOUNT",
                                                            LocalDate.now().minusDays(180),
                                                            LocalDate.now().minusDays(80)
    );
    final var accounts = Stream.of(bankAccountResponse).collect(Collectors.toSet());
    final var accountHolderResponse = new AccountHolderResponse("789123456", true, 1);
    final var accountHolders = Stream.of(accountHolderResponse).collect(Collectors.toSet());
    
    Mockito.lenient().when(this.bankAccountGateway.findAll("789123456")).thenReturn(accounts);
    Mockito.lenient().when(this.iAccountHolderGateway.findAll("789123456")).thenReturn(accountHolders);
    
    final var customerResponse = customerInteractor.validateActiveCustomer(customerRequest);
    
    Assertions.assertThat(customerResponse).isNotNull();
    Assertions.assertThat(customerResponse.type()).isNotNull().isEqualTo(TypeCustomer.AC);
    Assertions.assertThat(customerResponse.type().toString()).isNotNull().isEqualTo(TypeCustomer.AC.toString());
    Assertions.assertThat(customerResponse.identifierCode()).isNotNull().isEqualTo("789123456");
    Assertions.assertThat(customerResponse.identifierDocument()).isNotNull().isEqualTo("999.999.999-99");
  }
  
}