package com.lukeware.repositoriesspring.bankAccount;

import com.lukeware.usecases.banckaccount.IBankAccountRepository;

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

  public IBankAccountRepository create(BankAccountJpaRepository bankAccountJpaRepository) {
    return new BankAccountRepository(bankAccountJpaRepository);
  }

}
