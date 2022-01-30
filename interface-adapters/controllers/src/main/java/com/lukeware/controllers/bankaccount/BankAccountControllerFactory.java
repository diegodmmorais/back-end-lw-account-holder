package com.lukeware.controllers.bankaccount;

import com.lukeware.usecases.banckaccount.boundary.IBankAccountInputBoundary;
import com.lukeware.usecases.banckaccount.boundary.IBankAccountOutputBoundary;
import com.lukeware.usecases.banckaccount.IBankAccountDataProvider;

import java.util.Objects;

/**
 * @author Diego Morais
 */
public class BankAccountControllerFactory {
  private static BankAccountControllerFactory bankAccountControllerFactory;

  private BankAccountControllerFactory() {
    super();
  }

  public synchronized static BankAccountControllerFactory builder() {
    if (Objects.isNull(bankAccountControllerFactory)) {
      bankAccountControllerFactory = new BankAccountControllerFactory();
    }
    return bankAccountControllerFactory;
  }

  public BankAccountController create(IBankAccountInputBoundary bankAccountInputBoundary) {
    return new BankAccountController(bankAccountInputBoundary);
  }
}
