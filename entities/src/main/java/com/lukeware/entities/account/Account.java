package com.lukeware.entities.account;

import com.lukeware.entities.accountholder.IAccountHolder;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Objects;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author Diego Morais
 */
final record Account(boolean active,
                     boolean externalMovement,
                     TypeAccount type,
                     LocalDate openDate,
                     LocalDate lastMoveDate,
                     Set<IAccountHolder> accountHolders) implements IAccount {

  public static final int DAYS_OPEN_ACCOUNT = 180;
  public static final int DAYS_MOVED = 90;
  private static final Logger LOGGER = Logger.getLogger(Account.class.getName());

  @Override
  public boolean isActiveAccount() {
    try {
      this.isActive();
      this.doNotHaveExternalMovement();
      this.isCheckingAccount();
      this.accountIsOpen();
      this.wasItMoved();
    } catch (AccountValidateException e) {
      LOGGER.log(Level.WARNING, "{0}", e.getMessage());
      return false;
    }
    return true;
  }

  @Override
  public boolean isAccountHolder() {
    Set<IAccountHolder> accountHolders = Objects.requireNonNull(this.accountHolders, "not found owner");
    return accountHolders.stream().filter(accountHolder -> accountHolder.owner()).findFirst().isPresent();
  }

  public boolean isExternalMovement() {
    return externalMovement;
  }

  private void wasItMoved() {
    if (DAYS_MOVED < ChronoUnit.DAYS.between(lastMoveDate, LocalDate.now())) {
      throw new AccountValidateException("Current account was opened in less than 180 days");
    }
  }

  private void accountIsOpen() {
    if (DAYS_OPEN_ACCOUNT > ChronoUnit.DAYS.between(openDate, LocalDate.now())) {
      throw new AccountValidateException("Current account was opened in less than 180 days");
    }
  }

  private void isCheckingAccount() {
    if (TypeAccount.CHECKING_ACCOUNT != this.type) {
      throw new AccountValidateException("It's not a checking account.");
    }
  }

  private void doNotHaveExternalMovement() {
    if (true == this.externalMovement) {
      throw new AccountValidateException("In this account, there is External Movement.");
    }
  }

  private void isActive() {
    if (false == this.active) {
      throw new AccountValidateException("Inactive account.");
    }
  }
}
