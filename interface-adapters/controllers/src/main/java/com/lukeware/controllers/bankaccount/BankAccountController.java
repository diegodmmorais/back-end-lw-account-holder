package com.lukeware.controllers.bankaccount;

import com.lukeware.gateways.bankAccount.BankAccountDsRequest;
import com.lukeware.gateways.bankAccount.IBankAccountRegisterDsGateway;
import com.lukeware.usecases.banckaccount.BankAccountResponse;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author Diego Morais
 */
final record BankAccountController(
    IBankAccountRegisterDsGateway bankAccountRegisterDsGateway) implements IBankAccountController {

  @Override
  public Optional<BankAccountResponse> save(BankAccountResquest resquest, String identifierDocument) {
    return this.bankAccountRegisterDsGateway.save(new BankAccountDsRequest(resquest.identifierCode(),
                                                                           identifierDocument,
                                                                           resquest.active(),
                                                                           resquest.externalMovement(),
                                                                           resquest.type(),
                                                                           resquest.openDate(),
                                                                           resquest.lastMoveDate()))
                                            .map(ds -> new BankAccountResponse(ds.identifierCode(),
                                                                               ds.customerId(),
                                                                               ds.active(),
                                                                               ds.externalMovement(),
                                                                               ds.type(),
                                                                               ds.openDate(),
                                                                               ds.lastMoveDate()));
  }

  @Override
  public Set<BankAccountResponse> findAll(String identifierCode) {
    return this.bankAccountRegisterDsGateway.findAll(identifierCode).stream()
                                            .map(ds -> new BankAccountResponse(ds.identifierCode(),
                                                                               ds.customerId(),
                                                                               ds.active(),
                                                                               ds.externalMovement(),
                                                                               ds.type(),
                                                                               ds.openDate(),
                                                                               ds.lastMoveDate()))
                                            .collect(Collectors.toSet());
  }
}
