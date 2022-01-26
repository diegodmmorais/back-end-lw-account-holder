package com.lukeware.entities.account;

import com.lukeware.entities.accountholder.IAccountHolder;

import java.time.LocalDate;
import java.util.Set;

/**
 * @author Diego Morais
 */
public interface IAccount {
  boolean isActiveAccount();
  
  boolean isAccountHolder();
  
  boolean isExternalMovement();
  
  TypeAccount type();
  
  LocalDate openDate();
  
  LocalDate lastMoveDate();
  
  Set<IAccountHolder> accountHolders();
  
}
