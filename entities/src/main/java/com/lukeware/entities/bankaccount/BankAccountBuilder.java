package com.lukeware.entities.bankaccount;

import com.lukeware.entities.accountholder.IAccountHolder;

import java.time.LocalDate;
import java.util.LinkedHashSet;
import java.util.Set;

/**
 * @author Diego Morais
 */
public final class BankAccountBuilder {

  private static BankAccountBuilder bankAccountBuilder;
  private final Set<IAccountHolder> ownersAccount = new LinkedHashSet<>();
  private boolean active;
  private boolean externalMovement;
  private TypeAccount type;
  private LocalDate openDate;
  private LocalDate lastMoveDate;

  private BankAccountBuilder() {
    super();
  }

  public static synchronized BankAccountBuilder builder() {
    return BankAccountBuilder.bankAccountBuilder = new BankAccountBuilder();
  }

  public BankAccountBuilder active(boolean active) {
    this.active = active;
    return this;
  }

  public BankAccountBuilder externalMovement(boolean externalMovement) {
    this.externalMovement = externalMovement;
    return this;
  }

  public BankAccountBuilder type(TypeAccount type) {
    this.type = type;
    return this;
  }

  public BankAccountBuilder type(String type) {
    this.type = TypeAccount.valueOf(type);
    return this;
  }

  public BankAccountBuilder openDate(LocalDate openDate) {
    this.openDate = openDate;
    return this;
  }

  public BankAccountBuilder lastMoveDate(LocalDate lastMoveDate) {
    this.lastMoveDate = lastMoveDate;
    return this;
  }

  public BankAccountBuilder ownerAccount(IAccountHolder ownerAccount) {
    this.ownersAccount.add(ownerAccount);
    return this;
  }

  public BankAccountBuilder ownersAccount(Set<IAccountHolder> ownerAccount) {
    this.ownersAccount.addAll(ownerAccount);
    return this;
  }

  public IBankAccount build() {
    return new BankBankAccount(this.active,
                               this.externalMovement,
                               this.type,
                               this.openDate,
                               this.lastMoveDate,
                               this.ownersAccount);
  }
}
