package com.lukeware.entities.bankaccount;

import com.lukeware.entities.accountholder.AccountHolderBuilder;
import com.lukeware.entities.accountholder.IAccountHolder;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@DisplayName("BankBankAccount Test")
class BankAccountTest {

  @Test
  @DisplayName("1 - This bankaccount is active")
  void thisAccountIsActive() {

    IAccountHolder accountHolder = AccountHolderBuilder.builder()
                                                       .owner(true)
                                                       .identifierCode("7588564")
                                                       .sequence(1)
                                                       .identifierDocument("999.999.999-99")
                                                       .build();

    IBankAccount account = BankAccountBuilder.builder()
                                             .active(true)
                                             .externalMovement(false)
                                             .type(TypeAccount.CHECKING_ACCOUNT_PF)
                                             .ownerAccount(accountHolder)
                                             .lastMoveDate(LocalDate.now().minusDays(90))
                                             .openDate(LocalDate.now().minusDays(180))
                                             .build();

    Assertions.assertThat(account).isNotNull();
    Assertions.assertThat(account.isActiveAccount()).isTrue();
    Assertions.assertThat(account.accountHolders()).isNotNull().isNotEmpty().hasSize(1);
    Assertions.assertThat(account.type()).isNotNull().isEqualTo(TypeAccount.CHECKING_ACCOUNT_PF);
    Assertions.assertThat(account.lastMoveDate()).isNotNull().isEqualTo(LocalDate.now().minusDays(90));
    Assertions.assertThat(account.openDate()).isNotNull().isEqualTo(LocalDate.now().minusDays(180));
    Assertions.assertThat(account.isExternalMovement()).isNotNull().isFalse();
  }


  @Test
  @DisplayName("2 - Is owner of the bankaccount?")
  void isOwnerOfTheAccount() {
    IAccountHolder accountHolder = AccountHolderBuilder.builder()
                                                       .owner(true)
                                                       .identifierCode("7588564")
                                                       .sequence(1)
                                                       .identifierDocument("999.999.999-99")
                                                       .build();

    IBankAccount account = BankAccountBuilder.builder()
                                             .active(true)
                                             .externalMovement(false)
                                             .type(TypeAccount.CHECKING_ACCOUNT_PF.name())
                                             .ownersAccount(Stream.of(accountHolder).collect(Collectors.toSet()))
                                             .lastMoveDate(LocalDate.now().minusDays(90))
                                             .openDate(LocalDate.now().minusDays(180))
                                             .build();

    Assertions.assertThat(account).isNotNull();
    Assertions.assertThat(account.isActiveAccount()).isTrue();
    Assertions.assertThat(account.isAccountHolder()).isTrue();
  }


  @Test
  @DisplayName("3 - Is not owner of the bankaccount?")
  void isNotOwnerOfTheAccount() {
    IAccountHolder accountHolder = AccountHolderBuilder.builder()
                                                       .owner(false)
                                                       .identifierCode("7588564")
                                                       .sequence(1)
                                                       .identifierDocument("999.999.999-99")
                                                       .build();

    IBankAccount account = BankAccountBuilder.builder()
                                             .active(true)
                                             .externalMovement(false)
                                             .type(TypeAccount.CHECKING_ACCOUNT_PF)
                                             .ownerAccount(accountHolder)
                                             .lastMoveDate(LocalDate.now().minusDays(90))
                                             .openDate(LocalDate.now().minusDays(180))
                                             .build();

    Assertions.assertThat(account).isNotNull();
    Assertions.assertThat(account.isActiveAccount()).isTrue();
    Assertions.assertThat(account.isAccountHolder()).isFalse();
  }


  @Test
  @DisplayName("4 - This bankaccount is not active")
  void thisAccountIsNotActive() {

    IAccountHolder accountHolder = AccountHolderBuilder.builder()
                                                       .owner(true)
                                                       .identifierCode("7588564")
                                                       .sequence(1)
                                                       .identifierDocument("999.999.999-99")
                                                       .build();

    IBankAccount account = BankAccountBuilder.builder()
                                             .active(false)
                                             .externalMovement(false)
                                             .type(TypeAccount.CHECKING_ACCOUNT_PF)
                                             .ownerAccount(accountHolder)
                                             .lastMoveDate(LocalDate.now().minusDays(90))
                                             .openDate(LocalDate.now().minusDays(180))
                                             .build();

    Assertions.assertThat(account).isNotNull();
    Assertions.assertThat(account.isActiveAccount()).isFalse();
  }

  @Test
  @DisplayName("5 - This bankaccount is not active with external movement ")
  void thisAccountIsNotActive2() {

    IAccountHolder accountHolder = AccountHolderBuilder.builder()
                                                       .owner(true)
                                                       .identifierCode("7588564")
                                                       .sequence(1)
                                                       .identifierDocument("999.999.999-99")
                                                       .build();

    IBankAccount account = BankAccountBuilder.builder()
                                             .active(true)
                                             .externalMovement(true)
                                             .type(TypeAccount.CHECKING_ACCOUNT_PF)
                                             .ownerAccount(accountHolder)
                                             .lastMoveDate(LocalDate.now().minusDays(90))
                                             .openDate(LocalDate.now().minusDays(180))
                                             .build();

    Assertions.assertThat(account).isNotNull();
    Assertions.assertThat(account.isActiveAccount()).isFalse();
  }

  @Test
  @DisplayName("6 - This bankaccount is not active with saving bankaccount ")
  void thisAccountIsNotActive3() {

    IAccountHolder accountHolder = AccountHolderBuilder.builder()
                                                       .owner(true)
                                                       .identifierCode("7588564")
                                                       .sequence(1)
                                                       .identifierDocument("999.999.999-99")
                                                       .build();

    IBankAccount account = BankAccountBuilder.builder()
                                             .active(true)
                                             .externalMovement(false)
                                             .type(TypeAccount.SAVINGS_ACCOUNT)
                                             .ownerAccount(accountHolder)
                                             .lastMoveDate(LocalDate.now().minusDays(90))
                                             .openDate(LocalDate.now().minusDays(180))
                                             .build();

    Assertions.assertThat(account).isNotNull();
    Assertions.assertThat(account.isActiveAccount()).isFalse();
  }

  @Test
  @DisplayName("7 - Owner not found of the bankaccount?")
  void isNotOwnerOfTheAccountv2() {

    IAccountHolder accountHolder = AccountHolderBuilder.builder()
                                                       .owner(false)
                                                       .identifierCode("7588564")
                                                       .sequence(1)
                                                       .identifierDocument("999.999.999-99")
                                                       .build();

    IBankAccount account = BankAccountBuilder.builder()
                                             .active(true)
                                             .externalMovement(false)
                                             .type(TypeAccount.CHECKING_ACCOUNT_PF)
                                             .lastMoveDate(LocalDate.now().minusDays(90))
                                             .openDate(LocalDate.now().minusDays(180))
                                             .build();

    Assertions.assertThat(account).isNotNull();
    Assertions.assertThat(account.isActiveAccount()).isTrue();
    Assertions.assertThat(account.isAccountHolder()).isFalse();
  }

  @Test
  @DisplayName("8 - This bankaccount is inactive")
  void thisAccountIsInactive() {

    IAccountHolder accountHolder = AccountHolderBuilder.builder()
                                                       .owner(true)
                                                       .identifierCode("7588564")
                                                       .sequence(1)
                                                       .identifierDocument("999.999.999-99")
                                                       .build();

    IBankAccount account = BankAccountBuilder.builder()
                                             .active(true)
                                             .externalMovement(false)
                                             .type(TypeAccount.CHECKING_ACCOUNT_PF)
                                             .ownerAccount(accountHolder)
                                             .lastMoveDate(LocalDate.now().minusDays(100))
                                             .openDate(LocalDate.now().minusDays(180))
                                             .build();

    Assertions.assertThat(account).isNotNull();
    Assertions.assertThat(account.isActiveAccount()).isFalse();
    Assertions.assertThat(account.accountHolders()).isNotNull().isNotEmpty().hasSize(1);
    Assertions.assertThat(account.type()).isNotNull().isEqualTo(TypeAccount.CHECKING_ACCOUNT_PF);
    Assertions.assertThat(account.lastMoveDate()).isNotNull().isEqualTo(LocalDate.now().minusDays(100));
    Assertions.assertThat(account.openDate()).isNotNull().isEqualTo(LocalDate.now().minusDays(180));
    Assertions.assertThat(account.isExternalMovement()).isNotNull().isFalse();
  }

  @Test
  @DisplayName("9 - This bankaccount is inactive less open date")
  void thisAccountIsInactiveLessOpenDate() {

    IAccountHolder accountHolder = AccountHolderBuilder.builder()
                                                       .owner(true)
                                                       .identifierCode("7588564")
                                                       .sequence(1)
                                                       .identifierDocument("999.999.999-99")
                                                       .build();

    IBankAccount account = BankAccountBuilder.builder()
                                             .active(true)
                                             .externalMovement(false)
                                             .type(TypeAccount.CHECKING_ACCOUNT_PF)
                                             .ownerAccount(accountHolder)
                                             .lastMoveDate(LocalDate.now().minusDays(90))
                                             .openDate(LocalDate.now().minusDays(150))
                                             .build();

    Assertions.assertThat(account).isNotNull();
    Assertions.assertThat(account.isActiveAccount()).isFalse();
    Assertions.assertThat(account.accountHolders()).isNotNull().isNotEmpty().hasSize(1);
    Assertions.assertThat(account.accountHolders().stream().findFirst().isPresent()).isTrue();
    Assertions.assertThat(account.accountHolders().stream().findFirst().get().identifierCode())
              .isNotNull()
              .isNotEmpty()
              .isEqualTo("7588564");
    Assertions.assertThat(account.accountHolders().stream().findFirst().get().identifierDocument())
              .isNotNull()
              .isNotEmpty()
              .isEqualTo("999.999.999-99");
    Assertions.assertThat(account.accountHolders().stream().findFirst().get().sequence())
              .isNotNull()
              .isEqualTo(1);
    Assertions.assertThat(account.type()).isNotNull().isEqualTo(TypeAccount.CHECKING_ACCOUNT_PF);
    Assertions.assertThat(account.lastMoveDate()).isNotNull().isEqualTo(LocalDate.now().minusDays(90));
    Assertions.assertThat(account.openDate()).isNotNull().isEqualTo(LocalDate.now().minusDays(150));
    Assertions.assertThat(account.isExternalMovement()).isNotNull().isFalse();
  }

}