package com.lukeware.apigateway.bankAccount;

import com.lukeware.usecases.banckaccount.BankAccountResponse;
import com.lukeware.usecases.banckaccount.IBankAccountGateway;

import java.time.LocalDate;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author Diego Morais
 */
final record BankAccountGateway() implements IBankAccountGateway {

  @Override
  public Set<BankAccountResponse> findAll(String identifierCode) {
    final var bankAccount = new BankAccountResponse(
        String.valueOf(Math.round(Math.random())),
        true,
        false,
        "CHECKING_ACCOUNT",
        LocalDate.now().minusDays(190),
        LocalDate.now().minusDays(80));
    return Stream.of(bankAccount).collect(Collectors.toSet());
  }
}
