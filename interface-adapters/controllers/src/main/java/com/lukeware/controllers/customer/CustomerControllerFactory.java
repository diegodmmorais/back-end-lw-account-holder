package com.lukeware.controllers.customer;

import com.lukeware.usecases.customer.boundary.ICustomerInputBoundary;

import java.util.Objects;

/**
 * @author Diego Morais
 */
public final class CustomerControllerFactory {

  private static CustomerControllerFactory instance;

  private CustomerControllerFactory() {
    super();
  }

  public static CustomerControllerFactory getInstance() {
    if (Objects.isNull(instance)) {
      synchronized (CustomerControllerFactory.class) {
        if (Objects.isNull(instance)) {
          instance = new CustomerControllerFactory();
        }
      }
    }
    return instance;
  }

  public CustomerController create(ICustomerInputBoundary iCustomerInputBoundary) {
    return new CustomerController(iCustomerInputBoundary);
  }
}
