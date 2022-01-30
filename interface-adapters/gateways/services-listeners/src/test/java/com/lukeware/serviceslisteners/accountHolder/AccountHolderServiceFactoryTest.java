package com.lukeware.serviceslisteners.accountHolder;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * @author Diego Morais
 */
@DisplayName("Account holder gateway builder test")
class AccountHolderServiceFactoryTest {

  @Test
  @DisplayName("1 - create bankaccount holder gateway")
  void createAccountHolderGateway() {
    final var accountHolderGateway = AccountHolderServiceFactory.builder().create();
    Assertions.assertThat(accountHolderGateway).isNotNull();
  }

}