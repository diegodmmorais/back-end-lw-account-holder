package com.lukeware.presenters.customer;

import com.lukeware.usecases.customer.ds.CustomerDsResponse;
import com.lukeware.usecases.customer.boundary.ICustomerOutputBoundary;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @author Diego Morais
 */
final record CustomerPresenter() implements ICustomerOutputBoundary {

  @Override
  public CustomerDsResponse successView(CustomerDsResponse customerDsResponse) {
    customerDsResponse.setMessage(String.format("Customer is %s", customerDsResponse.getType().toString()));
    customerDsResponse.setCreateTime(DateTimeFormatter.ofPattern("dd/MM/yyy hh:mm:ss").format(LocalDateTime.now()));
    return customerDsResponse;
  }

  @Override
  public CustomerDsResponse failView(CustomerDsResponse customerDsResponse, String error) {
    throw new CustomerPresenterException(String.format("Error customer %s: %s", customerDsResponse.getIdentifierCode(), error));
  }
}
