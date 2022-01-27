package com.lukeware.apigateway.bankAccount;

import java.util.Optional;
import java.util.Set;

/**
 * @author Diego Morais
 */
public interface IBankAccountRegisterDsGateway {
  Optional<BankAccountDsResponse> save(BankAccountDsRequest dataMapper);

  Set<BankAccountDsResponse> findAll(String identifierCode);
}
