package com.lukeware.presenters.bankaccount;

import com.lukeware.usecases.banckaccount.IBankAccountMapper;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;

@ExtendWith(MockitoExtension.class)
class BankAccountPresenterTest {

  @InjectMocks
  BankAccountPresenter bankAccountPresenter;

  @Test
  void successView() {
    /* preparation */
    final var bankAccountMapper = Mockito.mock(IBankAccountMapper.class);

    Mockito.lenient().when(bankAccountMapper.getIdentifierCode()).thenReturn("789123456");
    Mockito.lenient().when(bankAccountMapper.isExternalMovement()).thenReturn(false);
    Mockito.lenient().when(bankAccountMapper.getCustomerId()).thenReturn("999.999.999-99");
    Mockito.lenient().when(bankAccountMapper.isActive()).thenReturn(true);
    Mockito.lenient().when(bankAccountMapper.getType()).thenReturn("CHECKING_ACCOUNT_PF");
    Mockito.lenient().when(bankAccountMapper.getLastMoveDate()).thenReturn(LocalDate.now().minusDays(180));
    Mockito.lenient().when(bankAccountMapper.getOpenDate()).thenReturn(LocalDate.now().minusDays(90));

    /* execution */
    final var response = bankAccountPresenter.successView(bankAccountMapper);

    /* validation */
    Assertions.assertThat(response).isNotNull();
    Assertions.assertThat(response.getIdentifierCode()).isNotNull().isEqualTo("789123456");
    Assertions.assertThat(response.getCustomerId()).isNotNull().isEqualTo("999.999.999-99");
    Assertions.assertThat(response.getType()).isNotNull().isEqualTo("CHECKING_ACCOUNT_PF");
    Assertions.assertThat(response.isActive()).isNotNull().isEqualTo(true);
    Assertions.assertThat(response.isExternalMovement()).isNotNull().isEqualTo(false);
    Assertions.assertThat(response.getMessage()).isNotNull().isEqualTo("Bank account 789123456, create Success");
  }

  @Test
  void failView() {
    /* preparation */
    final var bankAccountMapper = Mockito.mock(IBankAccountMapper.class);
    Mockito.lenient().when(bankAccountMapper.getIdentifierCode()).thenReturn("789123456");

    /* execution/validation */
    Assertions.assertThatThrownBy(() -> bankAccountPresenter.failView(bankAccountMapper, "erro bank account not found"))
              .isInstanceOf(BankAccountPresenterException.class)
              .hasMessageContaining("Error bank account 789123456: erro bank account not found");
  }

}