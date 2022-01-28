package com.lukeware.apigateway.bankAccount;

import com.lukeware.usecases.banckaccount.IBankAccountGateway;

import java.util.Objects;

/**
 * @author Diego Morais
 */
public final class BankAccountGatewayFactory {

  private static BankAccountGatewayFactory accountHolderGatewayBuilder;

  private BankAccountGatewayFactory() {
    super();
  }

  public synchronized static BankAccountGatewayFactory builder() {
    if (Objects.isNull(accountHolderGatewayBuilder)) {
      accountHolderGatewayBuilder = new BankAccountGatewayFactory();
    }
    return accountHolderGatewayBuilder;
  }

  public IBankAccountGateway create(IBankAccountRegisterDsGateway bankAccountRepository) {
    return new BankAccountGateway(bankAccountRepository);
  }

}
