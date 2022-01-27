package com.lukeware.usecases.customer;

public interface ICustomerInputBoundary {
  CustomerResponse validateActiveCustomer(CustomerRequest customerRequest);
}
