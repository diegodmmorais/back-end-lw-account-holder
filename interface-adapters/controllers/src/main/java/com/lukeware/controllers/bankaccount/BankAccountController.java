package com.lukeware.controllers.bankaccount;

import com.lukeware.usecases.banckaccount.IBankAccountRepository;
import com.lukeware.usecases.banckaccount.ds.BankAccountDsRequest;
import com.lukeware.usecases.banckaccount.ds.BankAccountDsResponse;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author Diego Morais
 */
final record BankAccountController(IBankAccountRepository accountRepository) implements IBankAccountController {

  @Override
  public Optional<BankAccountDsResponse> save(BankAccountResquest resquest, String identifierDocument) {
    return this.accountRepository.save(new BankAccountDsRequest(resquest.identifierCode(),
                                                                identifierDocument,
                                                                resquest.active(),
                                                                resquest.externalMovement(),
                                                                resquest.type(),
                                                                resquest.openDate(),
                                                                resquest.lastMoveDate()))
                                 .map(ds -> new BankAccountDsResponse(ds.identifierCode(),
                                                                                 ds.customerId(),
                                                                                 ds.active(),
                                                                                 ds.externalMovement(),
                                                                                 ds.type(),
                                                                                 ds.openDate(),
                                                                                 ds.lastMoveDate()));
  }

  @Override
  public Set<BankAccountDsResponse> findAll(String identifierCode) {
    return this.accountRepository.findAll(identifierCode).stream()
                                 .map(ds -> new BankAccountDsResponse(ds.identifierCode(),
                                                                                 ds.customerId(),
                                                                                 ds.active(),
                                                                                 ds.externalMovement(),
                                                                                 ds.type(),
                                                                                 ds.openDate(),
                                                                                 ds.lastMoveDate()))
                                 .collect(Collectors.toSet());
  }
}
