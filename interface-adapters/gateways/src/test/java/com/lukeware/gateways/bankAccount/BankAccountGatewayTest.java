package com.lukeware.apigateway.bankAccount;

import com.lukeware.gateways.bankAccount.BankAccountDsResponse;
import com.lukeware.gateways.bankAccount.BankAccountGateway;
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
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author Diego Morais
 */
@ExtendWith(MockitoExtension.class)
@DisplayName("bank bankaccount gateway Test")
class BankAccountGatewayTest {

  @InjectMocks
  BankAccountGateway bankAccountGateway;
  @Mock
  IBankAccountRegisterDsGateway accountRegisterDsGateway;


  @Test
  @DisplayName("1 - Find all bankaccount holders")
  void FindAllBankAccounts() {
    /* preparation */
    final var accountDsResponse = new BankAccountDsResponse("789123456",
                                                            "999.999.999-99",
                                                            true,
                                                            false,
                                                            "CHECKING_ACCOUNT_PF",
                                                            LocalDate.now().minusDays(190),
                                                            LocalDate.now().minusDays(80));
    final var bankAccounts = Stream.of(accountDsResponse).collect(Collectors.toSet());

    Mockito.lenient().when(accountRegisterDsGateway.findAll("789123456")).thenReturn(bankAccounts);

    /* execution */
    final var accountHolders = bankAccountGateway.findAll("789123456");

    /* validation */
    Assertions.assertThat(accountHolders).isNotNull().isNotEmpty().hasSize(1);
    final var bankAccountResponse = accountHolders.stream().findFirst();
    Assertions.assertThat(bankAccountResponse.isPresent()).isTrue();
    Assertions.assertThat(bankAccountResponse.get().identifierCode()).isNotBlank().isNotBlank();
    Assertions.assertThat(bankAccountResponse.get().active()).isTrue();
    Assertions.assertThat(bankAccountResponse.get().externalMovement()).isFalse();
    Assertions.assertThat(bankAccountResponse.get().type()).isEqualTo("CHECKING_ACCOUNT_PF");
    Assertions.assertThat(bankAccountResponse.get().lastMoveDate()).isEqualTo(LocalDate.now().minusDays(80));
    Assertions.assertThat(bankAccountResponse.get().openDate()).isEqualTo(LocalDate.now().minusDays(190));
  }

}