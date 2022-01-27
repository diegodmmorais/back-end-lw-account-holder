package com.lukeware.controllers;

import com.lukeware.usecases.customer.CustomerResponse;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author diegomorais
 */
@RestController
@RequestMapping("customers")
public interface ICustomerController {
  CustomerResponse isActiveCustomer(String identifierCode, String identifierDocument);
}
