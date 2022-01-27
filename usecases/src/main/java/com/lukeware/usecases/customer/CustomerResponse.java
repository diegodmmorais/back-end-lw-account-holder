package com.lukeware.usecases.customer;

import java.util.Objects;

/**
 * @author Diego Morais
 */
public final class CustomerResponse {
  private final String identifierCode;
  private final String identifierDocument;
  private final TypeCustomer type;
  private String message;

  public CustomerResponse(String identifierCode,
                          String identifierDocument,
                          TypeCustomer type) {
    this.identifierCode = identifierCode;
    this.identifierDocument = identifierDocument;
    this.type = type;

  }

  public String getIdentifierCode() {
    return identifierCode;
  }

  public String getIdentifierDocument() {
    return identifierDocument;
  }

  public TypeCustomer getType() {
    return type;
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

}