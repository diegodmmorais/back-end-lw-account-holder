package com.lukeware.repositories.bankAccount;

import com.lukeware.usecases.banckaccount.IBankAccountDataProvider;

import java.util.Objects;

/**
 * @author Diego Morais
 */
public final class BankAccountRepositoryFactory {

  private static BankAccountRepositoryFactory bankAccountRepositoryFactory;

  private BankAccountRepositoryFactory() {
    super();
  }

  public synchronized static BankAccountRepositoryFactory builder() {
    if (Objects.isNull(bankAccountRepositoryFactory)) {
      bankAccountRepositoryFactory = new BankAccountRepositoryFactory();
    }
    return bankAccountRepositoryFactory;
  }

  public IBankAccountDataProvider create(BankAccountJpaRepository bankAccountJpaRepository) {
    return new BankAccountRepository(bankAccountJpaRepository);
  }

}
