package com.lukeware.controllers.bankaccount;

import com.lukeware.gateways.bankAccount.BankAccountDsResponse;
import com.lukeware.gateways.bankAccount.IBankAccountRegisterDsGateway;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author Diego Morais
 */
@DisplayName("Bank account controller test")
@ExtendWith(MockitoExtension.class)
class BankAccountControllerTest {

  @InjectMocks
  BankAccountController bankAccountController;

  @Mock
  IBankAccountRegisterDsGateway bankAccountRegisterDsGateway;


  @Test
  @DisplayName("1 - save bank account")
  void saveBankAccount() throws Exception {
    final var bankAccountDsResponse = new BankAccountDsResponse(
        "789123456",
        "999.999.999-999",
        true,
        false,
        "CHECKING_ACCOUNT",
        LocalDate.now(),
        LocalDate.now()
    );

    final var bankAccountRequest = new BankAccountResquest(
        "789123456",
        true,
        false,
        "CHECKING_ACCOUNT",
        LocalDate.now(),
        LocalDate.now()
    );

    Mockito.when(this.bankAccountRegisterDsGateway.save(Mockito.any())).thenReturn(Optional.of(bankAccountDsResponse));

    final var bankAccountResponse = bankAccountController.save(bankAccountRequest, "789123456");

    Assertions.assertThat(bankAccountResponse).isNotNull();
    Assertions.assertThat(bankAccountResponse.isPresent()).isTrue();
    Assertions.assertThat(bankAccountResponse.get().identifierCode()).isEqualTo("789123456");
    Assertions.assertThat(bankAccountResponse.get().active()).isTrue();
    Assertions.assertThat(bankAccountResponse.get().externalMovement()).isFalse();
    Assertions.assertThat(bankAccountResponse.get().type()).isEqualTo("CHECKING_ACCOUNT");

  }


  @Test
  @DisplayName("2 - find all bank account")
  void findAllBankAccount() throws Exception {
    final var bankAccountDsResponse = new BankAccountDsResponse(
        "789123456",
        "999.999.999-999",
        true,
        false,
        "CHECKING_ACCOUNT",
        LocalDate.now(),
        LocalDate.now()
    );

    Mockito.when(this.bankAccountRegisterDsGateway.findAll("789123456"))
           .thenReturn(Stream.of(bankAccountDsResponse).collect(Collectors.toSet()));

    final var bankAccountResponses = bankAccountController.findAll("789123456");

    Assertions.assertThat(bankAccountResponses).isNotNull();
    Assertions.assertThat(bankAccountResponses.isEmpty()).isFalse();
    Assertions.assertThat(bankAccountResponses.stream().findFirst().get().identifierCode()).isEqualTo("789123456");
    Assertions.assertThat(bankAccountResponses.stream().findFirst().get().active()).isTrue();
    Assertions.assertThat(bankAccountResponses.stream().findFirst().get().externalMovement()).isFalse();
    Assertions.assertThat(bankAccountResponses.stream().findFirst().get().type()).isEqualTo("CHECKING_ACCOUNT");

  }
}