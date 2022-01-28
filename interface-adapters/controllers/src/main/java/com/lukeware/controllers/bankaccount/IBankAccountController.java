package com.lukeware.controllers.bankaccount;

import com.lukeware.usecases.banckaccount.BankAccountResponse;

import java.util.Optional;
import java.util.Set;

/**
 * @author Diego Morais
 */
public interface IBankAccountController {

  Optional<BankAccountResponse> save(BankAccountResquest resquest, String identifierDocument);

  Set<BankAccountResponse> findAll(String identifierCode);

}
