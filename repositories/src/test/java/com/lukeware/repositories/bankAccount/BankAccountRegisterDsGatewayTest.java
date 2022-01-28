package com.lukeware.repositories.bankAccount;

import com.lukeware.apigateway.bankAccount.BankAccountDsRequest;
import com.lukeware.apigateway.bankAccount.IBankAccountRegisterDsGateway;
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
@DisplayName("bankAccount register test")
class BankAccountRegisterDsGatewayTest {

  @Autowired
  private TestEntityManager entityManager;

  @Autowired
  BankAccountRepository bankAccountRepository;

  IBankAccountRegisterDsGateway bankAccountRegisterDsGateway;

  @BeforeEach
  void setUp() {
    bankAccountRegisterDsGateway = new BankAccountRegisterDsGateway(bankAccountRepository);
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

    final var accountDsResponse = bankAccountRegisterDsGateway.save(accountDsRequest);

    Assertions.assertThat(accountDsResponse).isNotNull();
    Assertions.assertThat(accountDsResponse.isPresent()).isTrue();
    Assertions.assertThat(accountDsResponse.get().identifierCode()).isNotNull().isEqualTo("789123456");
    Assertions.assertThat(accountDsResponse.get().active()).isNotNull().isTrue();
    Assertions.assertThat(accountDsResponse.get().externalMovement()).isNotNull().isFalse();
    Assertions.assertThat(accountDsResponse.get().type()).isNotNull().isEqualTo("CHECKING_ACCOUNT_PF");
    Assertions.assertThat(accountDsResponse.get().openDate()).isNotNull().isEqualTo(LocalDate.now().minusDays(90));
    Assertions.assertThat(accountDsResponse.get().lastMoveDate()).isNotNull().isEqualTo(LocalDate.now().minusDays(180));
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

    bankAccountRegisterDsGateway.save(accountDsRequest);

    final var accountDsResponses = bankAccountRegisterDsGateway.findAll("789123456");

    Assertions.assertThat(accountDsResponses).isNotNull();
    final var accountDsResponse = accountDsResponses.stream().findFirst();
    Assertions.assertThat(accountDsResponse.isPresent()).isTrue();
    Assertions.assertThat(accountDsResponse.get().identifierCode()).isNotNull().isEqualTo("789123456");
    Assertions.assertThat(accountDsResponse.get().active()).isNotNull().isTrue();
    Assertions.assertThat(accountDsResponse.get().externalMovement()).isNotNull().isFalse();
    Assertions.assertThat(accountDsResponse.get().type()).isNotNull().isEqualTo("CHECKING_ACCOUNT_PF");
    Assertions.assertThat(accountDsResponse.get().openDate()).isNotNull().isEqualTo(LocalDate.now().minusDays(90));
    Assertions.assertThat(accountDsResponse.get().lastMoveDate()).isNotNull().isEqualTo(LocalDate.now().minusDays(180));
  }


}