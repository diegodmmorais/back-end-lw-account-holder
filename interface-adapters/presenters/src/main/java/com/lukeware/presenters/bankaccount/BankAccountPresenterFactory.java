package com.lukeware.presenters.bankaccount;

import com.lukeware.usecases.banckaccount.boundary.IBankAccountOutputBoundary;

import java.util.Objects;

/**
 * @author diegomorais
 */
public final class BankAccountPresenterFactory {
  private static BankAccountPresenterFactory accountPresenterFactory;

  private BankAccountPresenterFactory() {
    super();
  }

  public static synchronized BankAccountPresenterFactory builder() {
    if (Objects.isNull(accountPresenterFactory)) {
      accountPresenterFactory = new BankAccountPresenterFactory();
    }
    return accountPresenterFactory;
  }

  public IBankAccountOutputBoundary create() {
    return new BankAccountPresenter();
  }
}
