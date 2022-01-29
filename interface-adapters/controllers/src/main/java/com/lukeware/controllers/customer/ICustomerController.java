package com.lukeware.controllers.customer;

import com.lukeware.usecases.customer.ds.CustomerDsResponse;

/**
 * @author diegomorais
 */
public interface ICustomerController {
  CustomerDsResponse isActiveCustomer(String identifierCode, String identifierDocument);
}
