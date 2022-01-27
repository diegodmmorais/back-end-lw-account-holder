package com.lukeware.apigateway.accountHolder;

import com.lukeware.usecases.accountholder.IAccountHolderGateway;

import java.util.Objects;

/**
 * @author Diego Morais
 */
public final class AccountHolderGatewayBuilder {

  private static AccountHolderGatewayBuilder accountHolderGatewayBuilder;

  private AccountHolderGatewayBuilder() {
    super();
  }

  public static synchronized AccountHolderGatewayBuilder builder() {
    if (Objects.isNull(accountHolderGatewayBuilder)) {
      accountHolderGatewayBuilder = new AccountHolderGatewayBuilder();
    }
    return accountHolderGatewayBuilder;
  }

  public IAccountHolderGateway create() {
    return new AccountHolderGateway();
  }

}
