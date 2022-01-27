package com.lukeware.usecases.customer;

/**
 * @author Diego Morais
 */
public interface ICustomerInputBoundary {
  CustomerResponse validateActiveCustomer(CustomerRequest customerRequest);
}
