package com.lukeware.controllers.bankaccount;

import com.lukeware.usecases.banckaccount.ds.BankAccountDsResponse;

import java.util.Optional;
import java.util.Set;

/**
 * @author Diego Morais
 */
public interface IBankAccountController {

  Optional<BankAccountDsResponse> save(BankAccountResquest resquest, String identifierDocument);

  Set<BankAccountDsResponse> findAll(String identifierCode);

}
