package com.lukeware.usecases.customer.boundary;

import com.lukeware.usecases.customer.ds.CustomerDsRequest;
import com.lukeware.usecases.customer.ds.CustomerDsResponse;

/**
 * @author Diego Morais
 */
public interface ICustomerInputBoundary {
  CustomerDsResponse validateActiveCustomerPf(CustomerDsRequest customerDsRequest);
}
