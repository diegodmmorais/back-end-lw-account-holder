package com.lukeware.apigateway.accountHolder;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

/**
 * @author Diego Morais
 */
@ExtendWith(MockitoExtension.class)
@DisplayName("Account holder gateway Test")
class AccountHolderGatewayTest {

  @InjectMocks
  AccountHolderGateway accountHolderGateway;



  @Test
  @DisplayName("1 - Find all bankaccount holders")
  void FindAllAccountHolders(){
    final var accountHolders = accountHolderGateway.findAll("789123465");

    Assertions.assertThat(accountHolders).isNotNull().isNotEmpty().hasSize(1);
    Assertions.assertThat(accountHolders.stream().findFirst().isPresent()).isTrue();
    Assertions.assertThat(accountHolders.stream().findFirst().get().identifierCode()).isNotBlank().isEqualTo("789123465");
    Assertions.assertThat(accountHolders.stream().findFirst().get().owner()).isTrue();
    Assertions.assertThat(accountHolders.stream().findFirst().get().sequence()).isEqualTo(1);
  }

}