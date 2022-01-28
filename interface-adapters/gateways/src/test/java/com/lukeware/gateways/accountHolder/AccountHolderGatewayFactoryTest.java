package com.lukeware.apigateway.accountHolder;

import com.lukeware.gateways.accountHolder.AccountHolderGatewayFactory;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * @author Diego Morais
 */
@DisplayName("Account holder gateway builder test")
class AccountHolderGatewayFactoryTest {

  @Test
  @DisplayName("1 - create bankaccount holder gateway")
  void createAccountHolderGateway() {
    final var accountHolderGateway = AccountHolderGatewayFactory.builder().create();
    Assertions.assertThat(accountHolderGateway).isNotNull();
  }

}