package com.lukeware.usecases.customer.boundary;

import com.lukeware.usecases.customer.ds.CustomerDsResponse;

/**
 * @author Diego Morais
 */
public interface ICustomerOutputBoundary {
  CustomerDsResponse successView(CustomerDsResponse customerDsResponse);

  CustomerDsResponse failView(CustomerDsResponse customerDsResponse, String error);
}
