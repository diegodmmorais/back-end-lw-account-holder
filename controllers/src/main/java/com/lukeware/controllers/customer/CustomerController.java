package com.lukeware.controllers.customer;

import com.lukeware.usecases.customer.CustomerRequest;
import com.lukeware.usecases.customer.CustomerResponse;
import com.lukeware.usecases.customer.ICustomerInputBoundary;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;

/**
 * @author Diego Morais
 */
final record CustomerController(ICustomerInputBoundary customerInputBoundary) implements ICustomerController {

  @Override
  @GetMapping("active-customer/{code}")
  public CustomerResponse isActiveCustomer(
      @PathVariable("code") String identifierCode,
      @RequestHeader("x-identifier-document") String identifierDocument) {
    return this.customerInputBoundary.validateActiveCustomerPf(new CustomerRequest(identifierCode, identifierDocument));
  }

}
