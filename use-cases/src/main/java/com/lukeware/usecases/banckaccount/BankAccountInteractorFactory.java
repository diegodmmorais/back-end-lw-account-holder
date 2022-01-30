package com.lukeware.usecases.banckaccount;

import com.lukeware.usecases.banckaccount.boundary.IBankAccountInputBoundary;
import com.lukeware.usecases.banckaccount.boundary.IBankAccountOutputBoundary;

import java.util.Objects;

/**
 * @author Diego Morais
 */
public class BankAccountInteractorFactory {
  private static BankAccountInteractorFactory bankAccountInteractorFactory;

  private BankAccountInteractorFactory() {
    super();
  }

  public synchronized static BankAccountInteractorFactory builder() {
    if (Objects.isNull(bankAccountInteractorFactory)) {
      bankAccountInteractorFactory = new BankAccountInteractorFactory();
    }
    return bankAccountInteractorFactory;
  }

  public IBankAccountInputBoundary create(IBankAccountDataProvider bankAccountRepository,
                                          IBankAccountOutputBoundary bankAccountOutputBoundary) {
    return new BankAccountInteractor(bankAccountRepository, bankAccountOutputBoundary);
  }
}
