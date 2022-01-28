package com.lukeware.usecases.customer;

public enum TypeCustomer {
  NC("New customer"),
  IC("Inactive customer"),
  AC("Active customer");

  private final String description;

  TypeCustomer(String description) {
    this.description = description;
  }

  @Override
  public String toString() {
    return description;
  }
}
