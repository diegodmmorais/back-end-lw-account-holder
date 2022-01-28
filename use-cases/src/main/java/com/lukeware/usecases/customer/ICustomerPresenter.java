package com.lukeware.usecases.customer;

/**
 * @author Diego Morais
 */
public interface ICustomerPresenter {
  CustomerResponse successView(CustomerResponse customerResponse);

  CustomerResponse failView(CustomerResponse customerResponse, String error);
}
