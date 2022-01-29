package com.lukeware.usecases.banckaccount;

import com.lukeware.usecases.banckaccount.ds.BankAccountDsRequest;
import com.lukeware.usecases.banckaccount.ds.BankAccountDsResponse;

import java.util.Optional;
import java.util.Set;

/**
 * @author Diego Morais
 */
public interface IBankAccountRepository {
  Optional<BankAccountDsResponse> save(BankAccountDsRequest dataMapper);

  Set<BankAccountDsResponse> findAll(String identifierCode);
}
