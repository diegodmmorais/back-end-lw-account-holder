package com.lukeware.presenters;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * @author diegomorais
 */
@DisplayName("Customer presenter builder test")
class CustomerPresenterBuilderTest {

  @Test
  @DisplayName("1 - Create customer presenter")
  void CreateCustomerPresenter() {
    final var customerPresenter = CustomerPresenterBuilder.builder().create();
    Assertions.assertThat(customerPresenter).isNotNull();
  }
}