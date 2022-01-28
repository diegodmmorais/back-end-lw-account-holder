package com.lukeware.controllers.customer;

import com.lukeware.usecases.customer.CustomerResponse;

/**
 * @author diegomorais
 */
public interface ICustomerController {
  CustomerResponse isActiveCustomer(String identifierCode, String identifierDocument);
}
