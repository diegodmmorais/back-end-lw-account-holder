package com.lukeware.controllers.bankaccount;

import com.lukeware.usecases.banckaccount.boundary.IBankAccountInputBoundary;

import java.util.Objects;

/**
 * @author Diego Morais
 */
public class BankAccountControllerFactory {
  private static BankAccountControllerFactory instance;

  private BankAccountControllerFactory() {
    super();
  }

  public static BankAccountControllerFactory getInstance() {
    if (Objects.isNull(instance)) {
      synchronized (BankAccountControllerFactory.class) {
        if (Objects.isNull(instance)) {
          instance = new BankAccountControllerFactory();
        }
      }
    }
    return instance;
  }

  public BankAccountController create(IBankAccountInputBoundary bankAccountInputBoundary) {
    return new BankAccountController(bankAccountInputBoundary);
  }
}
