package com.lukeware.presenters.bankaccount;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * @author diegomorais
 */
@DisplayName("Bank account presenter Test")
class BankAccountPresenterFactoryTest {

  @Test
  @DisplayName("1 - Create bank account presenter")
  void CreateCustomerPresenter() {
    final var bankAccountOutputBoundary = BankAccountPresenterFactory.builder().create();
    Assertions.assertThat(bankAccountOutputBoundary).isNotNull();
  }

}