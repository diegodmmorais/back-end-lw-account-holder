package com.lukeware.presenters.customer;

import com.lukeware.usecases.customer.boundary.ICustomerOutputBoundary;

import java.util.Objects;

/**
 * @author Diego Morais
 */
public final class CustomerPresenterFactory {

  private static CustomerPresenterFactory instance;

  private CustomerPresenterFactory() {
    super();
  }

  public static CustomerPresenterFactory getInstance() {
    if (Objects.isNull(instance)) {
      synchronized (CustomerPresenterFactory.class) {
        if (Objects.isNull(instance)) {
          instance = new CustomerPresenterFactory();
        }
      }
    }
    return instance;
  }

  public ICustomerOutputBoundary create() {
    return new CustomerPresenter();
  }

}
