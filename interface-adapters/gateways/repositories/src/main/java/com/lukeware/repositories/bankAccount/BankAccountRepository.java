package com.lukeware.repositories.bankAccount;

import com.lukeware.usecases.banckaccount.IBankAccountMapper;
import com.lukeware.usecases.banckaccount.IBankAccountGateway;
import com.lukeware.usecases.banckaccount.ds.BankAccountDsRequest;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author diegomorais
 */
final record BankAccountRepository(
    BankAccountJpaRepository bankAccountJpaRepository) implements IBankAccountGateway {

  @Override
  public Optional<IBankAccountMapper> save(BankAccountDsRequest dataMapper) {
    return Optional.ofNullable(bankAccountJpaRepository.save(toMapper(dataMapper)));
  }

  @Override
  public Set<IBankAccountMapper> findAll(String identifierCode) {
    return bankAccountJpaRepository.findAllByIdentifierCode(identifierCode).stream().collect(Collectors.toSet());
  }

  private BankAccountMapper toMapper(BankAccountDsRequest dataMapper) {
    return BankAccountMapperBuilder.builder()
                                   .customerId(dataMapper.customerId())
                                   .identifierCode(dataMapper.identifierCode())
                                   .active(dataMapper.active())
                                   .externalMovement(dataMapper.externalMovement())
                                   .type(dataMapper.type())
                                   .lastMoveDate(dataMapper.lastMoveDate())
                                   .openDate(dataMapper.openDate())
                                   .build();
  }
}
