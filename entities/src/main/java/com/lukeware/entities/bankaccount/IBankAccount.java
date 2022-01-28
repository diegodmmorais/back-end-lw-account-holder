package com.lukeware.entities.bankaccount;

import com.lukeware.entities.accountholder.IAccountHolder;

import java.time.LocalDate;
import java.util.Set;

/**
 * @author Diego Morais
 */
public interface IBankAccount {
  boolean isActiveAccount();

  boolean isAccountHolder();

  boolean isExternalMovement();

  TypeAccount type();

  LocalDate openDate();

  LocalDate lastMoveDate();

  Set<IAccountHolder> accountHolders();

}
