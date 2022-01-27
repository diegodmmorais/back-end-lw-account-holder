package com.lukeware.apigateway.accountHolder;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * @author Diego Morais
 */
@DisplayName("Account holder gateway builder test")
class AccountHolderGatewayBuilderTest {

  @Test
  @DisplayName("1 - create account holder gateway")
  void createAccountHolderGateway() {
    final var accountHolderGateway = AccountHolderGatewayBuilder.builder().create();
    Assertions.assertThat(accountHolderGateway).isNotNull();
  }

}