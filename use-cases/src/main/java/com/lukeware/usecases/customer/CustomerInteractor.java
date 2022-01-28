package com.lukeware.usecases.customer;

import com.lukeware.entities.accountholder.AccountHolderBuilder;
import com.lukeware.entities.accountholder.IAccountHolder;
import com.lukeware.entities.bankaccount.BankAccountBuilder;
import com.lukeware.entities.bankaccount.IBankAccount;
import com.lukeware.usecases.accountholder.AccountHolderResponse;
import com.lukeware.usecases.accountholder.IAccountHolderGateway;
import com.lukeware.usecases.banckaccount.BankAccountResponse;
import com.lukeware.usecases.banckaccount.IBankAccountGateway;

import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * @author Diego Morais
 */
final record CustomerInteractor(IAccountHolderGateway iAccountHolderGateway,
                                IBankAccountGateway bankAccountGateway,
                                ICustomerPresenter customerPresenter) implements ICustomerInputBoundary {

  private static final String CHECKING_ACCOUNT_PF = "CHECKING_ACCOUNT_PF";

  @Override
  public CustomerResponse validateActiveCustomerPf(CustomerRequest customerRequest) {
    final var bankAccounts = findAllBankAccount(customerRequest);
    final var accounts = bankAccounts.stream()
                                     .filter(account -> CHECKING_ACCOUNT_PF.equals(account.type()))
                                     .map(bankAccount -> getAccount(customerRequest, bankAccount))
                                     .collect(Collectors.toSet());

    final var accountWithAccountHolder = getAccountWithAccountHolder(accounts);
    if (accountWithAccountHolder.isEmpty()) {
      return createCustomerResponse(customerRequest, TypeCustomer.NC);
    }

    final var optIAccountActive = getAccountActive(accountWithAccountHolder);
    if (optIAccountActive.isEmpty()) {
      return createCustomerResponse(customerRequest, TypeCustomer.IC);
    }

    return createCustomerResponse(customerRequest, TypeCustomer.AC);
  }

  private Set<IBankAccount> getAccountWithAccountHolder(Set<IBankAccount> accounts) {
    return getAccountsWithRule(accounts, account -> account.isAccountHolder());
  }

  private Set<IBankAccount> getAccountActive(Set<IBankAccount> accounts) {
    return getAccountsWithRule(accounts, account -> account.isActiveAccount());
  }

  private CustomerResponse createCustomerResponse(CustomerRequest customerRequest, TypeCustomer type) {
    return customerPresenter.successView(new CustomerResponse(customerRequest.identifierCode(), customerRequest.identifierDocument(), type));
  }

  private Set<IBankAccount> getAccountsWithRule(Set<IBankAccount> accounts, Predicate<IBankAccount> filter) {
    return accounts.stream()
                   .filter(filter)
                   .collect(Collectors.toSet());
  }

  private IBankAccount getAccount(CustomerRequest customerRequest, BankAccountResponse bankAccount) {
    final var accountHolders = findAllAccountHolders(bankAccount);
    final var accountHolder = toaccountHolder(customerRequest, accountHolders);
    return toAccount(bankAccount, accountHolder);
  }


  private Set<BankAccountResponse> findAllBankAccount(CustomerRequest customerRequest) {
    return this.bankAccountGateway.findAll(customerRequest.identifierCode());
  }

  private Set<AccountHolderResponse> findAllAccountHolders(BankAccountResponse bankAccount) {
    return this.iAccountHolderGateway.findAll(bankAccount.identifierCode());
  }


  private Set<IAccountHolder> toaccountHolder(CustomerRequest customerRequest, Set<AccountHolderResponse> accountHolders) {
    return accountHolders.stream()
                         .map(accountHolder -> toaccountHolder(customerRequest, accountHolder))
                         .collect(Collectors.toSet());
  }

  private IAccountHolder toaccountHolder(CustomerRequest customerRequest, AccountHolderResponse accountHolder) {
    return AccountHolderBuilder.builder()
                               .identifierCode(accountHolder.identifierCode())
                               .identifierDocument(customerRequest.identifierDocument())
                               .sequence(accountHolder.sequence())
                               .owner(accountHolder.owner())
                               .build();
  }

  private IBankAccount toAccount(BankAccountResponse bankAccount, Set<IAccountHolder> accountHolder) {
    return BankAccountBuilder.builder()
                             .active(bankAccount.active())
                             .externalMovement(bankAccount.externalMovement())
                             .lastMoveDate(bankAccount.lastMoveDate())
                             .openDate(bankAccount.openDate())
                             .type(bankAccount.type())
                             .ownersAccount(accountHolder)
                             .build();
  }


}
