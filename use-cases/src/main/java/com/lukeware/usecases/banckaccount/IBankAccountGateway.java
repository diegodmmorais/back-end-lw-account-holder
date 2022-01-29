package com.lukeware.usecases.banckaccount;

import com.lukeware.usecases.IGateway;
import com.lukeware.usecases.banckaccount.ds.BankAccountDsRequest;

import java.util.Optional;
import java.util.Set;

/**
 * @author Diego Morais
 */
public interface IBankAccountGateway extends IGateway<String, IBankAccountMapper> {
  Optional<IBankAccountMapper> save(BankAccountDsRequest dataMapper);

  Set<IBankAccountMapper> findAll(String identifierCode);
}
