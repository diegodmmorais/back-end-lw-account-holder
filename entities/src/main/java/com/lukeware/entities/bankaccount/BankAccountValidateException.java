package com.lukeware.entities.bankaccount;

/**
 * @author Diego Morais
 */
final class BankAccountValidateException extends RuntimeException {

  protected BankAccountValidateException(String mensage) {
    super(mensage);
  }

}
