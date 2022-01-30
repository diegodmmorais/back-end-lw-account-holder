package com.lukeware.usecases.banckaccount.ds;

import java.time.LocalDate;

/**
 * data structure
 *
 * @author diegomorais
 */
public final class BankAccountDsResponse {

  private String identifierCode;
  private String customerId;
  private boolean active;
  private boolean externalMovement;
  private String type;
  private LocalDate openDate;
  private LocalDate lastMoveDate;
  private String message;
  private String createTime;

  public BankAccountDsResponse(String identifierCode,
                               String customerId,
                               boolean active,
                               boolean externalMovement,
                               String type,
                               LocalDate openDate,
                               LocalDate lastMoveDate) {
    this.identifierCode = identifierCode;
    this.customerId = customerId;
    this.active = active;
    this.externalMovement = externalMovement;
    this.type = type;
    this.openDate = openDate;
    this.lastMoveDate = lastMoveDate;
  }

  public String getIdentifierCode() {
    return identifierCode;
  }

  public String getCustomerId() {
    return customerId;
  }

  public boolean isActive() {
    return active;
  }

  public boolean isExternalMovement() {
    return externalMovement;
  }

  public String getType() {
    return type;
  }

  public LocalDate getOpenDate() {
    return openDate;
  }

  public LocalDate getLastMoveDate() {
    return lastMoveDate;
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  public String getCreateTime() {
    return createTime;
  }

  public void setCreateTime(String createTime) {
    this.createTime = createTime;
  }
}
