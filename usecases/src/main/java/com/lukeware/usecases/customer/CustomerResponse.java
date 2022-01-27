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

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    CustomerResponse that = (CustomerResponse) o;
    return Objects.equals(identifierCode, that.identifierCode) && Objects.equals(identifierDocument, that.identifierDocument) && type == that.type;
  }

  @Override
  public int hashCode() {
    return Objects.hash(identifierCode, identifierDocument, type);
  }
}