package com.lukeware.controllers.bankaccount;

import com.lukeware.usecases.banckaccount.IBankAccountOutputBoundary;
import com.lukeware.usecases.banckaccount.IBankAccountRepository;

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

  public BankAccountController create(IBankAccountRepository bankAccountRepository, IBankAccountOutputBoundary bankAccountOutputBoundary) {
    return new BankAccountController(bankAccountRepository, bankAccountOutputBoundary);
  }
}
