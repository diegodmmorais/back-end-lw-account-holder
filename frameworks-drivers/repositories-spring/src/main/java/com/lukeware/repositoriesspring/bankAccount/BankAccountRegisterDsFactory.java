package com.lukeware.repositoriesspring.bankAccount;

import com.lukeware.gateways.bankAccount.IBankAccountRegisterDsGateway;

import java.util.Objects;

/**
 * @author Diego Morais
 */
public final class BankAccountRegisterDsFactory {

  private static BankAccountRegisterDsFactory bankAccountRegisterDsFactory;

  private BankAccountRegisterDsFactory() {
    super();
  }

  public synchronized static BankAccountRegisterDsFactory builder() {
    if (Objects.isNull(bankAccountRegisterDsFactory)) {
      bankAccountRegisterDsFactory = new BankAccountRegisterDsFactory();
    }
    return bankAccountRegisterDsFactory;
  }

  public IBankAccountRegisterDsGateway create(BankAccountRepository bankAccountRepository) {
    return new BankAccountRegisterDsGateway(bankAccountRepository);
  }

}
