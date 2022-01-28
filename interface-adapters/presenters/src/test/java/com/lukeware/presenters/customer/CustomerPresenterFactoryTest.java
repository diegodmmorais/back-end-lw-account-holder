package com.lukeware.presenters.customer;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * @author diegomorais
 */
@DisplayName("Customer presenter builder test")
class CustomerPresenterFactoryTest {

  @Test
  @DisplayName("1 - Create customer presenter")
  void CreateCustomerPresenter() {
    final var customerPresenter = CustomerPresenterFactory.builder().create();
    Assertions.assertThat(customerPresenter).isNotNull();
  }
}