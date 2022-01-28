package com.lukeware.usecases.banckaccount;

import com.lukeware.usecases.IGateway;

import java.util.Set;

/**
 * @author Diego Morais
 */
public interface IBankAccountGateway extends IGateway<String, BankAccountResponse> {
  @Override
  Set<BankAccountResponse> findAll(String identifierCode);
}
