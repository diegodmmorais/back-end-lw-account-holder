package com.lukeware.usecases.customer;

public interface ICustomerInteractor {
  CustomerResponse validateActiveCustomer(CustomerRequest customerRequest);
}
