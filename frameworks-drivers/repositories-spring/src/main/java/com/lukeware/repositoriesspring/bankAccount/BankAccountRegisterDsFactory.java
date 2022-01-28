package com.lukeware.repositories.bankAccount;

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

  public BankAccountRegisterDsGateway create(BankAccountRepository bankAccountRepository) {
    return new BankAccountRegisterDsGateway(bankAccountRepository);
  }

}
