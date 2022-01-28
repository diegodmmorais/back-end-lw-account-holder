package com.lukeware.repositories.bankAccount;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

class BankAccountMapperTest {

  @Test
  void testEquals() {
    BankAccountMapper bankAccountMapper = new BankAccountMapper();
    bankAccountMapper.setId(1L);
    bankAccountMapper.setIdentifierCode("789123456");
    bankAccountMapper.setActive(true);
    bankAccountMapper.setType("CHECKING_ACCOUNT");
    bankAccountMapper.setLastMoveDate(LocalDate.now());
    bankAccountMapper.setOpenDate(LocalDate.now());
    bankAccountMapper.setCustomerId("999.999.999-99");


    final var bankAccountMapper2 = BankAccountMapperBuilder.builder()
                                                           .identifierCode("789123456").active(true)
                                                           .type("CHECKING_ACCOUNT")
                                                           .lastMoveDate(LocalDate.now())
                                                           .openDate(LocalDate.now())
                                                           .customerId("999.999.999-99")
                                                           .build();

    bankAccountMapper2.setId(1L);

    Assertions.assertThat(bankAccountMapper.equals(bankAccountMapper2)).isTrue();
    Assertions.assertThat(bankAccountMapper.hashCode()).isNotNull();

  }

  @Test
  void testHashCode() {
  }
}