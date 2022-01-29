package com.lukeware.usecases.customer;

import com.lukeware.entities.accountholder.AccountHolderBuilder;
import com.lukeware.entities.accountholder.IAccountHolder;
import com.lukeware.entities.bankaccount.BankAccountBuilder;
import com.lukeware.entities.bankaccount.IBankAccount;
import com.lukeware.usecases.accountholder.AccountHolderDsResponse;
import com.lukeware.usecases.accountholder.IAccountHolderGateway;
import com.lukeware.usecases.banckaccount.IBankAccountMapper;
import com.lukeware.usecases.banckaccount.IBankAccountGateway;
import com.lukeware.usecases.banckaccount.ds.BankAccountDsResponse;
import com.lukeware.usecases.customer.boundary.ICustomerInputBoundary;
import com.lukeware.usecases.customer.boundary.ICustomerOutputBoundary;
import com.lukeware.usecases.customer.ds.CustomerDsRequest;
import com.lukeware.usecases.customer.ds.CustomerDsResponse;

import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * @author Diego Morais
 */
final record CustomerInteractor(IAccountHolderGateway iAccountHolderGateway,
                                IBankAccountGateway bankAccountRepository,
                                ICustomerOutputBoundary customerPresenter) implements ICustomerInputBoundary {

  private static final String CHECKING_ACCOUNT_PF = "CHECKING_ACCOUNT_PF";

  @Override
  public CustomerDsResponse validateActiveCustomerPf(CustomerDsRequest customerDsRequest) {
    final var bankAccounts = findAllBankAccount(customerDsRequest);
    final var accounts = bankAccounts.stream()
                                     .filter(account -> CHECKING_ACCOUNT_PF.equals(account.getType()))
                                     .map(bankAccount -> getAccount(customerDsRequest, bankAccount))
                                     .collect(Collectors.toSet());

    final var accountWithAccountHolder = getAccountWithAccountHolder(accounts);
    if (accountWithAccountHolder.isEmpty()) {
      return createCustomerResponse(customerDsRequest, TypeCustomer.NC);
    }

    final var optIAccountActive = getAccountActive(accountWithAccountHolder);
    if (optIAccountActive.isEmpty()) {
      return createCustomerResponse(customerDsRequest, TypeCustomer.IC);
    }

    return createCustomerResponse(customerDsRequest, TypeCustomer.AC);
  }

  private Set<IBankAccount> getAccountWithAccountHolder(Set<IBankAccount> accounts) {
    return getAccountsWithRule(accounts, account -> account.isAccountHolder());
  }

  private Set<IBankAccount> getAccountActive(Set<IBankAccount> accounts) {
    return getAccountsWithRule(accounts, account -> account.isActiveAccount());
  }

  private CustomerDsResponse createCustomerResponse(CustomerDsRequest customerDsRequest, TypeCustomer type) {
    return customerPresenter.successView(new CustomerDsResponse(customerDsRequest.identifierCode(), customerDsRequest.identifierDocument(), type));
  }

  private Set<IBankAccount> getAccountsWithRule(Set<IBankAccount> accounts, Predicate<IBankAccount> filter) {
    return accounts.stream()
                   .filter(filter)
                   .collect(Collectors.toSet());
  }

  private IBankAccount getAccount(CustomerDsRequest customerDsRequest, BankAccountDsResponse bankAccount) {
    final var accountHolders = findAllAccountHolders(bankAccount);
    final var accountHolder = toaccountHolder(customerDsRequest, accountHolders);
    return toAccount(bankAccount, accountHolder);
  }


  private Set<BankAccountDsResponse> findAllBankAccount(CustomerDsRequest customerDsRequest) {
    return this.bankAccountRepository.findAll(customerDsRequest.identifierCode())
                                     .stream()
                                     .map(this::toResponse)
                                     .collect(Collectors.toSet());
  }

  private Set<AccountHolderDsResponse> findAllAccountHolders(BankAccountDsResponse bankAccount) {
    return this.iAccountHolderGateway.findAll(bankAccount.getIdentifierCode());
  }


  private Set<IAccountHolder> toaccountHolder(CustomerDsRequest customerDsRequest, Set<AccountHolderDsResponse> accountHolders) {
    return accountHolders.stream()
                         .map(accountHolder -> toaccountHolder(customerDsRequest, accountHolder))
                         .collect(Collectors.toSet());
  }

  private IAccountHolder toaccountHolder(CustomerDsRequest customerDsRequest, AccountHolderDsResponse accountHolder) {
    return AccountHolderBuilder.builder()
                               .identifierCode(accountHolder.identifierCode())
                               .identifierDocument(customerDsRequest.identifierDocument())
                               .sequence(accountHolder.sequence())
                               .owner(accountHolder.owner())
                               .build();
  }

  private IBankAccount toAccount(BankAccountDsResponse bankAccount, Set<IAccountHolder> accountHolder) {
    return BankAccountBuilder.builder()
                             .active(bankAccount.isActive())
                             .externalMovement(bankAccount.isExternalMovement())
                             .lastMoveDate(bankAccount.getLastMoveDate())
                             .openDate(bankAccount.getOpenDate())
                             .type(bankAccount.getType())
                             .ownersAccount(accountHolder)
                             .build();
  }

  private BankAccountDsResponse toResponse(IBankAccountMapper it) {
    return new BankAccountDsResponse(it.getIdentifierCode(), it.getCustomerId(), it.isActive(), it.isExternalMovement(), it.getType(), it.getOpenDate(), it.getLastMoveDate());
  }


}
