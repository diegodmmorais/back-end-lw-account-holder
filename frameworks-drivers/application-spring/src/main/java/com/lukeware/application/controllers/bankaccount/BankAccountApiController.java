package com.lukeware.application.controllers.bankaccount;

import com.lukeware.controllers.bankaccount.BankAccountResquest;
import com.lukeware.controllers.bankaccount.IBankAccountController;
import com.lukeware.usecases.banckaccount.BankAccountResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;
import java.util.Set;

/**
 * @author diegomorais
 */
@RestController
@RequestMapping("bank-accounts")
final record BankAccountApiController(IBankAccountController bankAccountController) {

  @PostMapping
  Optional<BankAccountResponse> save(
      @RequestBody BankAccountResquest resquest,
      @RequestHeader("x-identifier-document") String identifierDocument
  ) {
    return bankAccountController.save(resquest, identifierDocument);
  }

  @GetMapping("{code}")
  Set<BankAccountResponse> findAll(@PathVariable("code") String identifierCode) {
    return this.bankAccountController.findAll(identifierCode);
  }
}
