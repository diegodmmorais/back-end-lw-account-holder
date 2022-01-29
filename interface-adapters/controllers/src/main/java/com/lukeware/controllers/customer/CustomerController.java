package com.lukeware.controllers.customer;

import com.lukeware.usecases.customer.ds.CustomerDsRequest;
import com.lukeware.usecases.customer.ds.CustomerDsResponse;
import com.lukeware.usecases.customer.boundary.ICustomerInputBoundary;

/**
 * @author Diego Morais
 */
final record CustomerController(ICustomerInputBoundary customerInputBoundary) implements ICustomerController {

  @Override
  public CustomerDsResponse isActiveCustomer(String identifierCode, String identifierDocument) {
    return this.customerInputBoundary.validateActiveCustomerPf(new CustomerDsRequest(identifierCode, identifierDocument));
  }

}
