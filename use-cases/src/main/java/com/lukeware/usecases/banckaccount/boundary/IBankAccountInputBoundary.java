package com.lukeware.usecases.banckaccount.boundary;

import com.lukeware.usecases.banckaccount.ds.BankAccountDsRequest;
import com.lukeware.usecases.banckaccount.ds.BankAccountDsResponse;

import java.util.Optional;
import java.util.Set;

/**
 * @author diegomorais
 */
public interface IBankAccountInputBoundary {
  Optional<BankAccountDsResponse> save(BankAccountDsRequest resquest, String identifierDocument);

  Set<BankAccountDsResponse> findAll(String identifierCode);
}
