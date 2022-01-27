package com.lukeware.apigateway.bankAccount;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * @author diegomorais
 */
@DisplayName("Account holder gateway builder test")
class BankAccountGatewayBuilderTest {

  @Test
  @DisplayName("1 - create account holder gateway")
  void createAccountHolderGateway() {
    final var bankAccountGateway = BankAccountGatewayBuilder.builder().create();
    Assertions.assertThat(bankAccountGateway).isNotNull();
  }
}