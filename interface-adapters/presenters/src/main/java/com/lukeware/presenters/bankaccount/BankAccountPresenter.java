package com.lukeware.presenters.bankaccount;

import com.lukeware.usecases.banckaccount.IBankAccountMapper;
import com.lukeware.usecases.banckaccount.boundary.IBankAccountOutputBoundary;
import com.lukeware.usecases.banckaccount.ds.BankAccountDsResponse;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @author diegomorais
 */
final record BankAccountPresenter() implements IBankAccountOutputBoundary {


  @Override
  public BankAccountDsResponse successView(IBankAccountMapper iBankAccountMapper) {
    final var accountDsResponse = toResponse(iBankAccountMapper);
    accountDsResponse.setMessage(String.format("Bank account %s, create Success", accountDsResponse.getIdentifierCode()));
    accountDsResponse.setCreateTime(DateTimeFormatter.ofPattern("dd/MM/yyyy hh:mm:ss").format(LocalDateTime.now()));
    return accountDsResponse;
  }

  @Override
  public BankAccountDsResponse failView(IBankAccountMapper iBankAccountMapper, String error) {
    throw new BankAccountPresenterException(String.format("Error bank account %s: %s", iBankAccountMapper.getIdentifierCode(), error));
  }

  private BankAccountDsResponse toResponse(IBankAccountMapper accountMapper) {
    return new BankAccountDsResponse(accountMapper.getIdentifierCode(),
                                     accountMapper.getCustomerId(),
                                     accountMapper.isActive(),
                                     accountMapper.isExternalMovement(),
                                     accountMapper.getType(),
                                     accountMapper.getOpenDate(),
                                     accountMapper.getLastMoveDate());
  }
}
