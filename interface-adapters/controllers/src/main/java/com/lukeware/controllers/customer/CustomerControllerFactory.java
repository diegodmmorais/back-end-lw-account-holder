package com.lukeware.controllers.customer;

import com.lukeware.usecases.customer.ICustomerInputBoundary;

import java.util.Objects;

/**
 * @author Diego Morais
 */
public final class CustomerControllerFactory {

  private static CustomerControllerFactory customerControllerFactory;

  private CustomerControllerFactory() {
    super();
  }

  public synchronized static CustomerControllerFactory builder() {
    if (Objects.isNull(customerControllerFactory)) {
      customerControllerFactory = new CustomerControllerFactory();
    }
    return customerControllerFactory;
  }

  public CustomerController create(ICustomerInputBoundary iCustomerInputBoundary) {
    return new CustomerController(iCustomerInputBoundary);
  }
}
