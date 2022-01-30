package com.lukeware.serviceslisteners.accountHolder;

import com.lukeware.usecases.accountholder.IAccountHolderGateway;

import java.util.Objects;

/**
 * @author Diego Morais
 */
public final class AccountHolderServiceFactory {

  private static AccountHolderServiceFactory accountHolderServiceFactory;

  private AccountHolderServiceFactory() {
    super();
  }

  public static synchronized AccountHolderServiceFactory builder() {
    if (Objects.isNull(accountHolderServiceFactory)) {
      accountHolderServiceFactory = new AccountHolderServiceFactory();
    }
    return accountHolderServiceFactory;
  }

  public IAccountHolderGateway create() {
    return new AccountHolderService();
  }

}
