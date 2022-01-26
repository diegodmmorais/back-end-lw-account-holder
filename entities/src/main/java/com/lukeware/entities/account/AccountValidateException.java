package com.lukeware.entities.account;

/**
 * @author Diego Morais
 */
final class AccountValidateException extends RuntimeException {
  
  protected AccountValidateException(String mensage) {
    super(mensage);
  }
  
}
