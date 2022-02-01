package com.lukeware.usecases.customer;

import com.lukeware.usecases.accountholder.IAccountHolderGateway;
import com.lukeware.usecases.banckaccount.IBankAccountDataProvider;
import com.lukeware.usecases.customer.boundary.ICustomerInputBoundary;
import com.lukeware.usecases.customer.boundary.ICustomerOutputBoundary;

import java.util.Objects;

/**
 * @author Diego Morais
 */
public final class CustomerInteractorFactory {

  private static CustomerInteractorFactory instance;

  private CustomerInteractorFactory() {
    super();
  }

  public static CustomerInteractorFactory getInstance() {
    if (Objects.isNull(instance)) {
      synchronized (CustomerInteractorFactory.class) {
        if (Objects.isNull(instance)) {
          instance = new CustomerInteractorFactory();
        }
      }
    }
    return instance;
  }

  public ICustomerInputBoundary create(IAccountHolderGateway accountHolderGateway,
                                       IBankAccountDataProvider bankAccountRepository,
                                       ICustomerOutputBoundary customerPresenter) {
    return new CustomerInteractor(accountHolderGateway, bankAccountRepository, customerPresenter);
  }

}
