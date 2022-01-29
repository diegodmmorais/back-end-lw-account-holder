package com.lukeware.controllers.bankaccount;

import com.lukeware.usecases.banckaccount.IBankAccountMapper;
import com.lukeware.usecases.banckaccount.IBankAccountOutputBoundary;
import com.lukeware.usecases.banckaccount.IBankAccountGateway;
import com.lukeware.usecases.banckaccount.ds.BankAccountDsResponse;
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
  IBankAccountGateway bankAccountRepository;

  @Mock
  IBankAccountOutputBoundary bankAccountOutputBoundary;


  @Test
  @DisplayName("1 - save bank account")
  void saveBankAccount() throws Exception {

    final var bankAccountMapper = Mockito.mock(IBankAccountMapper.class);

    Mockito.lenient().when(bankAccountMapper.getIdentifierCode()).thenReturn("789123456");
    Mockito.lenient().when(bankAccountMapper.isExternalMovement()).thenReturn(false);
    Mockito.lenient().when(bankAccountMapper.getCustomerId()).thenReturn("999.999.999-99");
    Mockito.lenient().when(bankAccountMapper.isActive()).thenReturn(true);
    Mockito.lenient().when(bankAccountMapper.getType()).thenReturn("CHECKING_ACCOUNT_PF");
    Mockito.lenient().when(bankAccountMapper.getLastMoveDate()).thenReturn(LocalDate.now().minusDays(180));
    Mockito.lenient().when(bankAccountMapper.getOpenDate()).thenReturn(LocalDate.now().minusDays(90));

    final var bankAccountDsResponse = new BankAccountDsResponse(
        "789123456",
        "999.999.999-999",
        true,
        false,
        "CHECKING_ACCOUNT_PF",
        LocalDate.now().minusDays(180),
        LocalDate.now().minusDays(90)
    );

    final var bankAccountRequest = new BankAccountResquest(
        "789123456",
        true,
        false,
        "CHECKING_ACCOUNT_PF",
        LocalDate.now(),
        LocalDate.now()
    );

    Mockito.when(this.bankAccountRepository.save(Mockito.any())).thenReturn(Optional.of(bankAccountMapper));
    Mockito.when(this.bankAccountOutputBoundary.successView(Mockito.any())).thenReturn(bankAccountDsResponse);

    final var bankAccountResponse = bankAccountController.save(bankAccountRequest, "789123456");

    Assertions.assertThat(bankAccountResponse).isNotNull();
    Assertions.assertThat(bankAccountResponse.isPresent()).isTrue();
    Assertions.assertThat(bankAccountResponse.get().getIdentifierCode()).isEqualTo("789123456");
    Assertions.assertThat(bankAccountResponse.get().isActive()).isTrue();
    Assertions.assertThat(bankAccountResponse.get().isExternalMovement()).isFalse();
    Assertions.assertThat(bankAccountResponse.get().getType()).isEqualTo("CHECKING_ACCOUNT_PF");

  }


  @Test
  @DisplayName("2 - find all bank account")
  void findAllBankAccount() throws Exception {

    final var bankAccountMapper = Mockito.mock(IBankAccountMapper.class);

    Mockito.lenient().when(bankAccountMapper.getIdentifierCode()).thenReturn("789123456");
    Mockito.lenient().when(bankAccountMapper.isExternalMovement()).thenReturn(false);
    Mockito.lenient().when(bankAccountMapper.getCustomerId()).thenReturn("999.999.999-99");
    Mockito.lenient().when(bankAccountMapper.isActive()).thenReturn(true);
    Mockito.lenient().when(bankAccountMapper.getType()).thenReturn("CHECKING_ACCOUNT_PF");
    Mockito.lenient().when(bankAccountMapper.getLastMoveDate()).thenReturn(LocalDate.now().minusDays(180));
    Mockito.lenient().when(bankAccountMapper.getOpenDate()).thenReturn(LocalDate.now().minusDays(90));

    final var bankAccountDsResponse = new BankAccountDsResponse(
        "789123456",
        "999.999.999-999",
        true,
        false,
        "CHECKING_ACCOUNT_PF",
        LocalDate.now().minusDays(180),
        LocalDate.now().minusDays(90)
    );

    Mockito.when(this.bankAccountRepository.findAll("789123456"))
           .thenReturn(Stream.of(bankAccountMapper).collect(Collectors.toSet()));
    Mockito.when(this.bankAccountOutputBoundary.successView(bankAccountMapper)).thenReturn(bankAccountDsResponse);

    final var bankAccountResponses = bankAccountController.findAll("789123456");

    Assertions.assertThat(bankAccountResponses).isNotNull();
    Assertions.assertThat(bankAccountResponses.isEmpty()).isFalse();
    Assertions.assertThat(bankAccountResponses.stream().findFirst().get().getIdentifierCode()).isEqualTo("789123456");
    Assertions.assertThat(bankAccountResponses.stream().findFirst().get().isActive()).isTrue();
    Assertions.assertThat(bankAccountResponses.stream().findFirst().get().isExternalMovement()).isFalse();
    Assertions.assertThat(bankAccountResponses.stream().findFirst().get().getType()).isEqualTo("CHECKING_ACCOUNT_PF");

  }
}