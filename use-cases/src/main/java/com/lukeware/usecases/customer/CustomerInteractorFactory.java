package com.lukeware.usecases.customer;

import com.lukeware.usecases.accountholder.IAccountHolderGateway;
import com.lukeware.usecases.banckaccount.IBankAccountGateway;
import com.lukeware.usecases.customer.boundary.ICustomerInputBoundary;
import com.lukeware.usecases.customer.boundary.ICustomerOutputBoundary;

import java.util.Objects;

/**
 * @author Diego Morais
 */
public final class CustomerInteractorFactory {

  private static CustomerInteractorFactory customerInteractorFactory;

  private CustomerInteractorFactory() {
    super();
  }

  public synchronized static CustomerInteractorFactory builder() {
    if (Objects.isNull(customerInteractorFactory)) {
      customerInteractorFactory = new CustomerInteractorFactory();
    }
    return customerInteractorFactory;
  }

  public ICustomerInputBoundary create(IAccountHolderGateway accountHolderGateway,
                                       IBankAccountGateway bankAccountRepository,
                                       ICustomerOutputBoundary customerPresenter) {
    return new CustomerInteractor(accountHolderGateway, bankAccountRepository, customerPresenter);
  }

}
