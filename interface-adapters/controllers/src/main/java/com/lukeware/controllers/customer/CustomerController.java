package com.lukeware.controllers.customer;

import com.lukeware.usecases.customer.CustomerRequest;
import com.lukeware.usecases.customer.CustomerResponse;
import com.lukeware.usecases.customer.ICustomerInputBoundary;

/**
 * @author Diego Morais
 */
final record CustomerController(ICustomerInputBoundary customerInputBoundary) implements ICustomerController {

  @Override
  public CustomerResponse isActiveCustomer(String identifierCode, String identifierDocument) {
    return this.customerInputBoundary.validateActiveCustomerPf(new CustomerRequest(identifierCode, identifierDocument));
  }

}
