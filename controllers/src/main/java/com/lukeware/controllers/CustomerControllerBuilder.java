package com.lukeware.controllers;

import com.lukeware.usecases.customer.ICustomerInputBoundary;

import java.util.Objects;

/**
 * @author Diego Morais
 */
public final class CustomerControllerBuilder {

  private static CustomerControllerBuilder customerControllerBuilder;

  private CustomerControllerBuilder() {
    super();
  }

  public synchronized static CustomerControllerBuilder builder() {
    if (Objects.isNull(customerControllerBuilder)) {
      customerControllerBuilder = new CustomerControllerBuilder();
    }
    return customerControllerBuilder;
  }

  public CustomerController create(ICustomerInputBoundary iCustomerInputBoundary) {
    return new CustomerController(iCustomerInputBoundary);
  }
}
