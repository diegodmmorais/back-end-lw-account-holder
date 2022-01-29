package com.lukeware.controllers.bankaccount;

import com.lukeware.usecases.banckaccount.IBankAccountOutputBoundary;
import com.lukeware.usecases.banckaccount.IBankAccountGateway;
import com.lukeware.usecases.banckaccount.ds.BankAccountDsRequest;
import com.lukeware.usecases.banckaccount.ds.BankAccountDsResponse;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author Diego Morais
 */
final record BankAccountController(IBankAccountGateway accountRepository,
                                   IBankAccountOutputBoundary bankAccountOutputBoundary) implements IBankAccountController {

  @Override
  public Optional<BankAccountDsResponse> save(BankAccountResquest resquest, String identifierDocument) {
    final var OptBankAccountMapper = this.accountRepository.save(new BankAccountDsRequest(resquest.identifierCode(),
                                                                                          identifierDocument,
                                                                                          resquest.active(),
                                                                                          resquest.externalMovement(),
                                                                                          resquest.type(),
                                                                                          resquest.openDate(),
                                                                                          resquest.lastMoveDate()));
    return OptBankAccountMapper.map(it -> bankAccountOutputBoundary.successView(it));
  }

  @Override
  public Set<BankAccountDsResponse> findAll(String identifierCode) {
    return this.accountRepository.findAll(identifierCode)
                                 .stream()
                                 .map(it -> bankAccountOutputBoundary.successView(it))
                                 .collect(Collectors.toSet());
  }
}
