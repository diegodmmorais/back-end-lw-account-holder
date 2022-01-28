package com.lukeware.repositoriesspring.bankAccount;

import com.lukeware.gateways.bankAccount.BankAccountDsRequest;
import com.lukeware.gateways.bankAccount.BankAccountDsResponse;
import com.lukeware.gateways.bankAccount.IBankAccountRegisterDsGateway;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author diegomorais
 */
final record BankAccountRegisterDsGateway(
    BankAccountRepository bankAccountRepository) implements IBankAccountRegisterDsGateway {

  @Override
  public Optional<BankAccountDsResponse> save(BankAccountDsRequest dataMapper) {
    final var bankAccountMapper = bankAccountRepository.save(toMapper(dataMapper));
    return Optional.ofNullable(toResponse(bankAccountMapper));
  }

  @Override
  public Set<BankAccountDsResponse> findAll(String identifierCode) {
    return bankAccountRepository.findAllByIdentifierCode(identifierCode).stream().map(this::toResponse)
                                .collect(Collectors.toSet());
  }

  private BankAccountDsResponse toResponse(BankAccountMapper it) {
    return new BankAccountDsResponse(it.getIdentifierCode(), it.getCustomerId(), it.isActive(), it.isExternalMovement(), it.getType(), it.getOpenDate(), it.getLastMoveDate());
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
