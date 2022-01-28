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
  private final Set<IAccountHolder> ownersAccount = new LinkedHashSet<>();
  private boolean active;
  private boolean externalMovement;
  private TypeAccount type;
  private LocalDate openDate;
  private LocalDate lastMoveDate;

  private AccountBuilder() {
    super();
  }

  public static synchronized AccountBuilder builder() {
    return AccountBuilder.accountBuilder = new AccountBuilder();
  }

  public AccountBuilder active(boolean active) {
    this.active = active;
    return this;
  }

  public AccountBuilder externalMovement(boolean externalMovement) {
    this.externalMovement = externalMovement;
    return this;
  }

  public AccountBuilder type(TypeAccount type) {
    this.type = type;
    return this;
  }

  public AccountBuilder type(String type) {
    this.type = TypeAccount.valueOf(type);
    return this;
  }

  public AccountBuilder openDate(LocalDate openDate) {
    this.openDate = openDate;
    return this;
  }

  public AccountBuilder lastMoveDate(LocalDate lastMoveDate) {
    this.lastMoveDate = lastMoveDate;
    return this;
  }

  public AccountBuilder ownerAccount(IAccountHolder ownerAccount) {
    this.ownersAccount.add(ownerAccount);
    return this;
  }

  public AccountBuilder ownersAccount(Set<IAccountHolder> ownerAccount) {
    this.ownersAccount.addAll(ownerAccount);
    return this;
  }

  public IAccount build() {
    return new Account(this.active,
                       this.externalMovement,
                       this.type,
                       this.openDate,
                       this.lastMoveDate,
                       this.ownersAccount);
  }
}
