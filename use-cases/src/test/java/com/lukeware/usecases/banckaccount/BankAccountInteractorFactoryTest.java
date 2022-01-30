package com.lukeware.usecases.banckaccount;

import com.lukeware.usecases.banckaccount.boundary.IBankAccountOutputBoundary;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

/**
 * @author diegomorais
 */
@ExtendWith(MockitoExtension.class)
@DisplayName("Customer interactor builder test")
class BankAccountInteractorFactoryTest {

  @Mock
  IBankAccountDataProvider bankAccountDataProvider;
  @Mock
  IBankAccountOutputBoundary bankAccountOutputBoundary;

  @Test
  @DisplayName("1 - create Bank account interactor holder gateway")
  void createAccountHolderGateway() {
    final var bankAccountInputBoundary = BankAccountInteractorFactory.builder()
                                                                     .create(bankAccountDataProvider, bankAccountOutputBoundary);
    Assertions.assertThat(bankAccountInputBoundary).isNotNull();
  }


}