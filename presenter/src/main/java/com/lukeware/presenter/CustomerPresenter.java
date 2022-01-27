package com.lukeware.presenter;

import com.lukeware.usecases.customer.CustomerResponse;
import com.lukeware.usecases.customer.ICustomerPresenter;

/**
 * @author Diego Morais
 */
final record CustomerPresenter() implements ICustomerPresenter {

  @Override
  public CustomerResponse successView(CustomerResponse customerResponse) {
    customerResponse.setMessage(String.format("Customer is %s", customerResponse.getType().toString()));
    return customerResponse;
  }

  @Override
  public CustomerResponse failView(CustomerResponse customerResponse, String error) {
    throw new CustomerPresenterException(String.format("Error customer %s: %s", customerResponse.getIdentifierCode(), error));
  }
}
