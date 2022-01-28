package com.lukeware.apigateway.bankAccount;

import com.lukeware.usecases.banckaccount.BankAccountResponse;
import com.lukeware.usecases.banckaccount.IBankAccountGateway;

import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author Diego Morais
 */
final record BankAccountGateway(IBankAccountRegisterDsGateway accountRegisterDsGateway) implements IBankAccountGateway {

  @Override
  public Set<BankAccountResponse> findAll(String identifierCode) {
    return this.accountRegisterDsGateway.findAll(identifierCode)
                                        .stream()
                                        .map(banckAccount ->
                                              new BankAccountResponse(banckAccount.identifierCode(),
                                                                      banckAccount.customerId(),
                                                                      banckAccount.active(),
                                                                      banckAccount.externalMovement(),
                                                                      banckAccount.type(),
                                                                      banckAccount.openDate(),
                                                                      banckAccount.lastMoveDate())
                                     ).collect(Collectors.toSet());
  }
}
