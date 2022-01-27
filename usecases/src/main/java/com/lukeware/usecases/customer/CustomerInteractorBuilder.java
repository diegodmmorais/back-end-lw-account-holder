package com.lukeware.usecases.customer;

import com.lukeware.usecases.accountholder.IAccountHolderGateway;
import com.lukeware.usecases.banckaccount.IBankAccountGateway;

import java.util.Objects;

/**
 * @author Diego Morais
 */
public final class CustomerInteractorBuilder {

  private static CustomerInteractorBuilder customerInteractorBuilder;

  private CustomerInteractorBuilder() {
    super();
  }

  public synchronized static CustomerInteractorBuilder builder() {
    if (Objects.isNull(customerInteractorBuilder)) {
      customerInteractorBuilder = new CustomerInteractorBuilder();
    }
    return customerInteractorBuilder;
  }

  public ICustomerInputBoundary create(IAccountHolderGateway accountHolderGateway, IBankAccountGateway bankAccountGateway, ICustomerPresenter customerPresenter) {
    return new CustomerInteractor(accountHolderGateway, bankAccountGateway, customerPresenter);
  }

}
