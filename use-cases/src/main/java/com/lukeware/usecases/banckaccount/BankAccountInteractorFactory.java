package com.lukeware.usecases.banckaccount;

import com.lukeware.usecases.banckaccount.boundary.IBankAccountInputBoundary;
import com.lukeware.usecases.banckaccount.boundary.IBankAccountOutputBoundary;

import java.util.Objects;

/**
 * @author Diego Morais
 */
public class BankAccountInteractorFactory {
  private static BankAccountInteractorFactory instance;

  private BankAccountInteractorFactory() {
    super();
  }

  public static BankAccountInteractorFactory getInstance() {
    if (Objects.isNull(instance)) {
      synchronized (BankAccountInteractorFactory.class) {
        if (Objects.isNull(instance)) {
          instance = new BankAccountInteractorFactory();
        }
      }
    }
    return instance;
  }

  public IBankAccountInputBoundary create(IBankAccountDataProvider bankAccountRepository,
                                          IBankAccountOutputBoundary bankAccountOutputBoundary) {
    return new BankAccountInteractor(bankAccountRepository, bankAccountOutputBoundary);
  }
}
