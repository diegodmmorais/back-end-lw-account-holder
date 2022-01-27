package com.lukeware.apigateway.accountHolder;

import com.lukeware.usecases.accountholder.IAccountHolderGateway;

import java.util.Objects;

/**
 * @author Diego Morais
 */
public final class AccountHolderGatewayFactory {

  private static AccountHolderGatewayFactory accountHolderGatewayFactory;

  private AccountHolderGatewayFactory() {
    super();
  }

  public static synchronized AccountHolderGatewayFactory builder() {
    if (Objects.isNull(accountHolderGatewayFactory)) {
      accountHolderGatewayFactory = new AccountHolderGatewayFactory();
    }
    return accountHolderGatewayFactory;
  }

  public IAccountHolderGateway create() {
    return new AccountHolderGateway();
  }

}
