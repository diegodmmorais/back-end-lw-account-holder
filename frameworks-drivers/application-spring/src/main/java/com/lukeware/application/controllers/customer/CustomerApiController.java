package com.lukeware.application.controllers.customer;

import com.lukeware.controllers.customer.ICustomerController;
import com.lukeware.usecases.customer.CustomerResponse;
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
final record CustomerApiController(ICustomerController customerController) {

  @GetMapping("active-customer/{code}")
  CustomerResponse isActiveCustomer(
      @PathVariable("code") String identifierCode,
      @RequestHeader("x-identifier-document") String identifierDocument) {
    return this.customerController.isActiveCustomer(identifierCode, identifierDocument);
  }


}
