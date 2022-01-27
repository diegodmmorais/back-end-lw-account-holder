package com.lukeware.controllers;

import com.lukeware.usecases.customer.CustomerRequest;
import com.lukeware.usecases.customer.CustomerResponse;
import com.lukeware.usecases.customer.ICustomerInputBoundary;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Diego Morais
 */
@RestController
@RequestMapping("customers")
final record CustomerController(ICustomerInputBoundary customerInputBoundary) {

  @GetMapping("active-customer/{code}")
  CustomerResponse isActiveCustomer(
      @PathVariable("code") String identifierCode,
      @RequestHeader("x-identifier-document") String identifierDocument) {
    return this.customerInputBoundary.validateActiveCustomer(new CustomerRequest(identifierCode, identifierDocument));
  }

}
