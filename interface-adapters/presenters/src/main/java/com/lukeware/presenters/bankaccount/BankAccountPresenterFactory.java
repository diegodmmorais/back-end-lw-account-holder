package com.lukeware.presenters.bankaccount;

import com.lukeware.usecases.banckaccount.boundary.IBankAccountOutputBoundary;

import java.util.Objects;

/**
 * @author diegomorais
 */
public final class BankAccountPresenterFactory {

  private static BankAccountPresenterFactory instance;

  private BankAccountPresenterFactory() {
    super();
  }

  public static BankAccountPresenterFactory getInstance() {
    if (Objects.isNull(instance)) {
      synchronized (BankAccountPresenterFactory.class) {
        if (Objects.isNull(instance)) {
          instance = new BankAccountPresenterFactory();
        }
      }
    }
    return instance;
  }

  public IBankAccountOutputBoundary create() {
    return new BankAccountPresenter();
  }
}
