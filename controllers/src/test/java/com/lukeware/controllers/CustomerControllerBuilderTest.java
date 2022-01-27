package com.lukeware.controllers;

import com.lukeware.usecases.customer.ICustomerInputBoundary;
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
@DisplayName("Customer controller builder test")
class CustomerControllerBuilderTest {

  @Mock
  ICustomerInputBoundary iCustomerInputBoundary;

  @Test
  @DisplayName("1 - Create customer controller")
  void CreateCustomerController() {

    final var customerController = CustomerControllerBuilder.builder().create(iCustomerInputBoundary);

    Assertions.assertThat(customerController).isNotNull();
  }
}