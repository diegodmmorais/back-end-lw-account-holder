package com.lukeware.presenters;

import com.lukeware.usecases.customer.ICustomerPresenter;

import java.util.Objects;

/**
 * @author Diego Morais
 */
public class CustomerPresenterBuilder {

  private static CustomerPresenterBuilder CustomerPresenterBuilder;

  private CustomerPresenterBuilder() {
    super();
  }

  public static synchronized CustomerPresenterBuilder builder() {
    if (Objects.isNull(CustomerPresenterBuilder)) {
      CustomerPresenterBuilder = new CustomerPresenterBuilder();
    }
    return CustomerPresenterBuilder;
  }
  public ICustomerPresenter create() {
    return new CustomerPresenter();
  }

}
