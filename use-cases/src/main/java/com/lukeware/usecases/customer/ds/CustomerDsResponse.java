package com.lukeware.usecases.customer.ds;

import com.lukeware.usecases.customer.TypeCustomer;

import java.util.Objects;

/**
 * @author Diego Morais
 */
public final class CustomerDsResponse {
  private final String identifierCode;
  private final String identifierDocument;
  private final TypeCustomer type;
  private String message;
  private String createTime;

  public CustomerDsResponse(String identifierCode,
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

  public void setCreateTime(String createTime) {
    this.createTime = createTime;
  }

  public String getCreateTime() {
    return createTime;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    CustomerDsResponse that = (CustomerDsResponse) o;
    return Objects.equals(identifierCode, that.identifierCode) && Objects.equals(identifierDocument, that.identifierDocument) && type == that.type;
  }

  @Override
  public int hashCode() {
    return Objects.hash(identifierCode, identifierDocument, type);
  }
}