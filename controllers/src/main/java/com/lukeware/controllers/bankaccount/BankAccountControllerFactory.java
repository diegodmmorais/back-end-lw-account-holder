package com.lukeware.controllers.bankaccount;

import com.lukeware.apigateway.bankAccount.IBankAccountRegisterDsGateway;

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

  public BankAccountController create(IBankAccountRegisterDsGateway bankAccountRegisterDsGateway) {
    return new BankAccountController(bankAccountRegisterDsGateway);
  }
}
