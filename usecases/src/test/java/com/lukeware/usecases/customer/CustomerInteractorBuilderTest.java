package com.lukeware.usecases.customer;

import com.lukeware.usecases.accountholder.IAccountHolderGateway;
import com.lukeware.usecases.banckaccount.IBankAccountGateway;
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
class CustomerInteractorBuilderTest {

  @Mock
  IAccountHolderGateway accountHolderGateway;
  @Mock
  IBankAccountGateway bankAccountGateway;
  @Mock
  ICustomerPresenter customerPresenter;

  @Test
  @DisplayName("1 - create account holder gateway")
  void createAccountHolderGateway() {
    final var customerInputBoundary = CustomerInteractorBuilder.builder()
                                                               .create(accountHolderGateway, bankAccountGateway, customerPresenter);
    Assertions.assertThat(customerInputBoundary).isNotNull();
  }

}

