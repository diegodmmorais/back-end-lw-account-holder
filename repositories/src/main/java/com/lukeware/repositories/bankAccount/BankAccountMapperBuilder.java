package com.lukeware.repositories.bankAccount;

import java.time.LocalDate;

/**
 * @author Diego Morais
 */
final class BankAccountMapperBuilder {

  private static BankAccountMapperBuilder bankAccountMapperBuilder;
  private String identifierCode;
  private String customerId;
  private boolean active;
  private boolean externalMovement;
  private String type;
  private LocalDate openDate;
  private LocalDate lastMoveDate;

  private BankAccountMapperBuilder() {
    super();
  }

  static BankAccountMapperBuilder builder() {
    return bankAccountMapperBuilder = new BankAccountMapperBuilder();
  }

  public BankAccountMapperBuilder customerId(String customerId) {
    this.customerId = customerId;
    return this;
  }

  public BankAccountMapperBuilder identifierCode(String identifierCode) {
    this.identifierCode = identifierCode;
    return this;
  }


  public BankAccountMapperBuilder active(boolean active) {
    this.active = active;
    return this;
  }

  public BankAccountMapperBuilder externalMovement(boolean externalMovement) {
    this.externalMovement = externalMovement;
    return this;
  }

  public BankAccountMapperBuilder type(String type) {
    this.type = type;
    return this;
  }

  public BankAccountMapperBuilder openDate(LocalDate openDate) {
    this.openDate = openDate;
    return this;
  }

  public BankAccountMapperBuilder lastMoveDate(LocalDate lastMoveDate) {
    this.lastMoveDate = lastMoveDate;
    return this;
  }

  BankAccountMapper build() {
    final var bankAccountMapper = new BankAccountMapper();
    bankAccountMapper.setIdentifierCode(this.identifierCode);
    bankAccountMapper.setActive(this.active);
    bankAccountMapper.setExternalMovement(this.externalMovement);
    bankAccountMapper.setType(this.type);
    bankAccountMapper.setOpenDate(this.openDate);
    bankAccountMapper.setLastMoveDate(this.lastMoveDate);
    return bankAccountMapper;
  }
}
