package com.lukeware.repositories.bankAccount;

import com.lukeware.usecases.banckaccount.IBankAccountDataProvider;
import com.lukeware.usecases.banckaccount.ds.BankAccountDsRequest;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDate;

/**
 * Diego morais
 */
@DataJpaTest
@ExtendWith(SpringExtension.class)
@DisplayName("Bank account repository test")
class BankAccountRepositoryTest {

  @Autowired
  private TestEntityManager entityManager;

  @Autowired
  BankAccountJpaRepository bankAccountJpaRepository;

  IBankAccountDataProvider bankAccountRepository;

  @BeforeEach
  void setUp() {
    bankAccountRepository = new BankAccountRepository(bankAccountJpaRepository);
  }

  @Test
  @DisplayName("1 - save new bank bankaccount")
  void saveBankAccount() {
    final var accountDsRequest = new BankAccountDsRequest("789123456",
                                                          "999.999.999-99",
                                                          true,
                                                          false,
                                                          "CHECKING_ACCOUNT_PF",
                                                          LocalDate.now().minusDays(90),
                                                          LocalDate.now().minusDays(180));

    final var bankAccountMapper = bankAccountRepository.save(accountDsRequest);

    Assertions.assertThat(bankAccountMapper).isNotNull();
    Assertions.assertThat(bankAccountMapper.isPresent()).isTrue();
    Assertions.assertThat(bankAccountMapper.get().getIdentifierCode()).isNotNull().isEqualTo("789123456");
    Assertions.assertThat(bankAccountMapper.get().getCustomerId()).isNotNull().isEqualTo("999.999.999-99");
    Assertions.assertThat(bankAccountMapper.get().isActive()).isNotNull().isTrue();
    Assertions.assertThat(bankAccountMapper.get().isExternalMovement()).isNotNull().isFalse();
    Assertions.assertThat(bankAccountMapper.get().getType()).isNotNull().isEqualTo("CHECKING_ACCOUNT_PF");
    Assertions.assertThat(bankAccountMapper.get().getOpenDate()).isNotNull().isEqualTo(LocalDate.now().minusDays(90));
    Assertions.assertThat(bankAccountMapper.get().getLastMoveDate()).isNotNull()
              .isEqualTo(LocalDate.now().minusDays(180));
  }

  @Test
  @DisplayName("2 - find all bank bankaccount")
  void findAllBankAccount() {
    final var accountDsRequest = new BankAccountDsRequest("789123456",
                                                          "999.999.999-99",
                                                          true,
                                                          false,
                                                          "CHECKING_ACCOUNT_PF",
                                                          LocalDate.now().minusDays(90),
                                                          LocalDate.now().minusDays(180));

    bankAccountRepository.save(accountDsRequest);

    final var bankAccountMappers = bankAccountRepository.findAll("789123456");

    Assertions.assertThat(bankAccountMappers).isNotNull();
    final var bankAccountsMapper = bankAccountMappers.stream().findFirst();
    Assertions.assertThat(bankAccountsMapper.isPresent()).isTrue();
    Assertions.assertThat(bankAccountsMapper.get().getIdentifierCode()).isNotNull().isEqualTo("789123456");
    Assertions.assertThat(bankAccountsMapper.get().getCustomerId()).isNotNull().isEqualTo("999.999.999-99");
    Assertions.assertThat(bankAccountsMapper.get().isActive()).isNotNull().isTrue();
    Assertions.assertThat(bankAccountsMapper.get().isExternalMovement()).isNotNull().isFalse();
    Assertions.assertThat(bankAccountsMapper.get().getType()).isNotNull().isEqualTo("CHECKING_ACCOUNT_PF");
    Assertions.assertThat(bankAccountsMapper.get().getOpenDate()).isNotNull().isEqualTo(LocalDate.now().minusDays(90));
    Assertions.assertThat(bankAccountsMapper.get().getLastMoveDate()).isNotNull()
              .isEqualTo(LocalDate.now().minusDays(180));
  }


}