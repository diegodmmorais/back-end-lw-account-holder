package com.lukeware.repositories.bankAccount;

import com.lukeware.usecases.banckaccount.IBankAccountDataProvider;

import java.util.Objects;

/**
 * @author Diego Morais
 */
public final class BankAccountRepositoryFactory {

  private static BankAccountRepositoryFactory instance;

  private BankAccountRepositoryFactory() {
    super();
  }

  public static BankAccountRepositoryFactory getInstance() {
    if (Objects.isNull(instance)) {
      synchronized (BankAccountRepositoryFactory.class) {
        if (Objects.isNull(instance)) {
          instance = new BankAccountRepositoryFactory();
        }
      }
    }
    return instance;
  }

  public IBankAccountDataProvider create(BankAccountJpaRepository bankAccountJpaRepository) {
    return new BankAccountRepository(bankAccountJpaRepository);
  }

}
