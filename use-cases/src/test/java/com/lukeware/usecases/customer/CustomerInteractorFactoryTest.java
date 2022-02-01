package com.lukeware.usecases.customer;

import com.lukeware.usecases.accountholder.IAccountHolderGateway;
import com.lukeware.usecases.banckaccount.IBankAccountDataProvider;
import com.lukeware.usecases.customer.boundary.ICustomerOutputBoundary;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

/**
 * @author Diego Morais
 */
@ExtendWith(MockitoExtension.class)
@DisplayName("Customer interactor builder test")
class CustomerInteractorFactoryTest {

  @Mock
  IAccountHolderGateway accountHolderGateway;
  @Mock
  IBankAccountDataProvider bankAccountRepository;
  @Mock
  ICustomerOutputBoundary customerPresenter;

  @Test
  @DisplayName("1 - create bankaccount holder gateway")
  void createAccountHolderGateway() {
    final var customerInputBoundary = CustomerInteractorFactory.getInstance()
                                                               .create(accountHolderGateway, bankAccountRepository, customerPresenter);
    Assertions.assertThat(customerInputBoundary).isNotNull();
  }

}

