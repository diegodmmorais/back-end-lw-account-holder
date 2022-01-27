package com.lukeware.apigateway.bankAccount;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;

/**
 * @author Diego Morais
 */
@ExtendWith(MockitoExtension.class)
@DisplayName("bank account gateway Test")
class BankAccountGatewayTest {

  @InjectMocks
  BankAccountGateway bankAccountGateway;

  @Test
  @DisplayName("1 - Find all account holders")
  void FindAllBankAccounts() {
    final var accountHolders = bankAccountGateway.findAll("789123456");

    Assertions.assertThat(accountHolders).isNotNull().isNotEmpty().hasSize(1);
    Assertions.assertThat(accountHolders.stream().findFirst().isPresent()).isTrue();
    Assertions.assertThat(accountHolders.stream().findFirst().get().identifierCode())
              .isNotBlank()
              .isNotBlank();
    Assertions.assertThat(accountHolders.stream().findFirst().get().active()).isTrue();
    Assertions.assertThat(accountHolders.stream().findFirst().get().externalMovement()).isFalse();
    Assertions.assertThat(accountHolders.stream().findFirst().get().type()).isEqualTo("CHECKING_ACCOUNT");
    Assertions.assertThat(accountHolders.stream().findFirst().get().lastMoveDate())
              .isEqualTo(LocalDate.now().minusDays(80));
    Assertions.assertThat(accountHolders.stream().findFirst().get().openDate())
              .isEqualTo(LocalDate.now().minusDays(190));
  }

}