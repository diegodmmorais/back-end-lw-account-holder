package com.lukeware.controllers.bankaccount;

import com.lukeware.usecases.banckaccount.boundary.IBankAccountInputBoundary;
import com.lukeware.usecases.banckaccount.ds.BankAccountDsRequest;
import com.lukeware.usecases.banckaccount.ds.BankAccountDsResponse;

import java.util.Optional;
import java.util.Set;

/**
 * @author Diego Morais
 */
final record BankAccountController(
    IBankAccountInputBoundary bankAccountInputBoundary) implements IBankAccountController {

  @Override
  public Optional<BankAccountDsResponse> save(BankAccountResquest resquest, String identifierDocument) {
    return bankAccountInputBoundary.save(new BankAccountDsRequest(resquest.identifierCode(),
                                                                  identifierDocument,
                                                                  resquest.active(),
                                                                  resquest.externalMovement(),
                                                                  resquest.type(),
                                                                  resquest.openDate(),
                                                                  resquest.lastMoveDate()), identifierDocument);
  }

  @Override
  public Set<BankAccountDsResponse> findAll(String identifierCode) {
    return this.bankAccountInputBoundary.findAll(identifierCode);
  }
}
