package com.lukeware.usecases.banckaccount;

import java.time.LocalDate;

/**
 * @author Diego Morais
 */
public interface IBankAccountMapper {
  Long getId();

  String getIdentifierCode();

  boolean isActive();

  boolean isExternalMovement();

  String getType();

  LocalDate getOpenDate();

  LocalDate getLastMoveDate();

  String getCustomerId();
}
