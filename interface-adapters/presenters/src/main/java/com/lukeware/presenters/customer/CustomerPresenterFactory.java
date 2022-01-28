package com.lukeware.presenters.customer;

import com.lukeware.usecases.customer.ICustomerPresenter;

import java.util.Objects;

/**
 * @author Diego Morais
 */
public class CustomerPresenterFactory {

  private static CustomerPresenterFactory CustomerPresenterFactory;

  private CustomerPresenterFactory() {
    super();
  }

  public static synchronized CustomerPresenterFactory builder() {
    if (Objects.isNull(CustomerPresenterFactory)) {
      CustomerPresenterFactory = new CustomerPresenterFactory();
    }
    return CustomerPresenterFactory;
  }

  public ICustomerPresenter create() {
    return new CustomerPresenter();
  }

}
