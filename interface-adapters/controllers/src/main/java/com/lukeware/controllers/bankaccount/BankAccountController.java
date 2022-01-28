package com.lukeware.controllers.bankaccount;

import com.lukeware.apigateway.bankAccount.BankAccountDsRequest;
import com.lukeware.apigateway.bankAccount.IBankAccountRegisterDsGateway;
import com.lukeware.usecases.banckaccount.BankAccountResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author Diego Morais
 */
final record BankAccountController(
    IBankAccountRegisterDsGateway bankAccountRegisterDsGateway) implements IBankAccountController {

  @Override
  @PostMapping
  public Optional<BankAccountResponse> save(
      @RequestBody BankAccountResquest resquest,
      @RequestHeader("x-identifier-document") String identifierDocument
  ) {
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
  @GetMapping("{code}")
  public Set<BankAccountResponse> findAll(@PathVariable("code") String identifierCode) {
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
