package com.lukeware.entities.account;

import com.lukeware.entities.accountholder.IAccountHolder;

import java.time.LocalDate;
import java.util.LinkedHashSet;
import java.util.Set;

/**
 * @author Diego Morais
 */
public final class AccountBuilder {
  
  private static AccountBuilder accountBuilder;
  
  private boolean active;
  private boolean externalMovement;
  private TypeAccount type;
  private LocalDate openDate;
  private LocalDate lastMoveDate;
  private Set<IAccountHolder> ownersAccount = new LinkedHashSet<>();
  
  private AccountBuilder() {
    super();
  }
  
  public static synchronized AccountBuilder builder() {
    return AccountBuilder.accountBuilder = new AccountBuilder();
  }
  
  public AccountBuilder active(boolean active) {
    AccountBuilder.accountBuilder.active = active;
    return AccountBuilder.accountBuilder;
  }
  
  public AccountBuilder externalMovement(boolean externalMovement) {
    AccountBuilder.accountBuilder.externalMovement = externalMovement;
    return AccountBuilder.accountBuilder;
  }
  
  public AccountBuilder type(TypeAccount type) {
    AccountBuilder.accountBuilder.type = type;
    return AccountBuilder.accountBuilder;
  }
  
  public AccountBuilder type(String type) {
    AccountBuilder.accountBuilder.type = TypeAccount.valueOf(type);
    return AccountBuilder.accountBuilder;
  }
  
  public AccountBuilder openDate(LocalDate openDate) {
    AccountBuilder.accountBuilder.openDate = openDate;
    return AccountBuilder.accountBuilder;
  }
  
  public AccountBuilder lastMoveDate(LocalDate lastMoveDate) {
    AccountBuilder.accountBuilder.lastMoveDate = lastMoveDate;
    return AccountBuilder.accountBuilder;
  }
  
  public AccountBuilder ownerAccount(IAccountHolder ownerAccount) {
    AccountBuilder.accountBuilder.ownersAccount.add(ownerAccount);
    return AccountBuilder.accountBuilder;
  }
  
  public AccountBuilder ownersAccount(Set<IAccountHolder> ownerAccount) {
    AccountBuilder.accountBuilder.ownersAccount.addAll(ownerAccount);
    return AccountBuilder.accountBuilder;
  }
  
  public IAccount build() {
    return new Account(AccountBuilder.accountBuilder.active,
                       AccountBuilder.accountBuilder.externalMovement,
                       AccountBuilder.accountBuilder.type,
                       AccountBuilder.accountBuilder.openDate,
                       AccountBuilder.accountBuilder.lastMoveDate,
                       AccountBuilder.accountBuilder.ownersAccount
    );
  }
}
