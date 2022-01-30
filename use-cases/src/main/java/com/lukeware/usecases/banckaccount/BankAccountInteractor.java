package com.lukeware.usecases.banckaccount;

import com.lukeware.usecases.banckaccount.boundary.IBankAccountInputBoundary;
import com.lukeware.usecases.banckaccount.boundary.IBankAccountOutputBoundary;
import com.lukeware.usecases.banckaccount.ds.BankAccountDsRequest;
import com.lukeware.usecases.banckaccount.ds.BankAccountDsResponse;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author diegomorais
 */
final record BankAccountInteractor(IBankAccountDataProvider bankAccountDataProvider,
                                   IBankAccountOutputBoundary bankAccountOutputBoundary) implements IBankAccountInputBoundary {

  @Override
  public Optional<BankAccountDsResponse> save(BankAccountDsRequest resquest, String identifierDocument) {
    final var OptBankAccountMapper = this.bankAccountDataProvider.save(new BankAccountDsRequest(resquest.identifierCode(),
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
    return this.bankAccountDataProvider.findAll(identifierCode)
                                       .stream()
                                       .map(it -> bankAccountOutputBoundary.successView(it))
                                       .collect(Collectors.toSet());
  }
}
