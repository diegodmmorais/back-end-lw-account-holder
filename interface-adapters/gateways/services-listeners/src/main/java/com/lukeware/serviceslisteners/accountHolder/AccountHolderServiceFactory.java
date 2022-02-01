package com.lukeware.serviceslisteners.accountHolder;

import com.lukeware.usecases.accountholder.IAccountHolderGateway;

import java.util.Objects;

/**
 * @author Diego Morais
 */
public final class AccountHolderServiceFactory {

  private static AccountHolderServiceFactory instance;

  private AccountHolderServiceFactory() {
    super();
  }

  public static synchronized AccountHolderServiceFactory getInstance() {
    if (Objects.isNull(instance)) {
      instance = new AccountHolderServiceFactory();
    }
    return instance;
  }

  public IAccountHolderGateway create() {
    return new AccountHolderService();
  }

}
