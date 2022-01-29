package com.lukeware.repositoriesspring.bankAccount;

import com.lukeware.usecases.banckaccount.IBankAccountMapper;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDate;
import java.util.Objects;

/**
 * Emtity Banco
 *
 * @author Diego Morais
 */
@Entity(name = "bank_account")
final class BankAccountMapper implements IBankAccountMapper {

  @Id
  @Column(name = "id", nullable = false)
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;
  private String identifierCode;
  private String customerId;
  private boolean active;
  private boolean externalMovement;
  private String type;
  private LocalDate openDate;
  private LocalDate lastMoveDate;

  @Override
  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  @Override
  public String getIdentifierCode() {
    return identifierCode;
  }

  public void setIdentifierCode(String identifierCode) {
    this.identifierCode = identifierCode;
  }

  @Override
  public boolean isActive() {
    return active;
  }

  public void setActive(boolean active) {
    this.active = active;
  }

  @Override
  public boolean isExternalMovement() {
    return externalMovement;
  }

  public void setExternalMovement(boolean externalMovement) {
    this.externalMovement = externalMovement;
  }

  @Override
  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  @Override
  public LocalDate getOpenDate() {
    return openDate;
  }

  public void setOpenDate(LocalDate openDate) {
    this.openDate = openDate;
  }

  @Override
  public LocalDate getLastMoveDate() {
    return lastMoveDate;
  }

  public void setLastMoveDate(LocalDate lastMoveDate) {
    this.lastMoveDate = lastMoveDate;
  }

  @Override
  public String getCustomerId() {
    return customerId;
  }

  public void setCustomerId(String customerId) {
    this.customerId = customerId;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    BankAccountMapper that = (BankAccountMapper) o;
    return active == that.active && externalMovement == that.externalMovement && Objects.equals(identifierCode, that.identifierCode) && Objects.equals(type, that.type) && Objects.equals(openDate, that.openDate) && Objects.equals(lastMoveDate, that.lastMoveDate);
  }

  @Override
  public int hashCode() {
    return Objects.hash(identifierCode, active, externalMovement, type, openDate, lastMoveDate);
  }


}
