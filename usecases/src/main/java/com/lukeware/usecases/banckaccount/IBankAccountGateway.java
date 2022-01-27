package com.lukeware.usecases.banckaccount;

import java.util.Set;

/**
 * @author Diego Morais
 */
public interface IBankAccountGateway {

  Set<BankAccountResponse> findAll(String identifierCode);

}
