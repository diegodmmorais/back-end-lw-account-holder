package com.lukeware.apigateway.bankAccount;

import com.lukeware.usecases.banckaccount.IBankAccountGateway;

import java.util.Objects;

/**
 * @author Diego Morais
 */
public final class BankAccountGatewayBuilder {

  private static BankAccountGatewayBuilder accountHolderGatewayBuilder;

  private BankAccountGatewayBuilder() {
    super();
  }

  public synchronized static BankAccountGatewayBuilder builder() {
    if (Objects.isNull(accountHolderGatewayBuilder)) {
      accountHolderGatewayBuilder = new BankAccountGatewayBuilder();
    }
    return accountHolderGatewayBuilder;
  }

  public IBankAccountGateway create() {
    return new BankAccountGateway();
  }

}
