package com.lukeware.presenters.bankaccount;

/**
 * @author Diego Morais
 */
final class BankAccountPresenterException extends RuntimeException {
  public BankAccountPresenterException(String error) {
    super(error);
  }
}
