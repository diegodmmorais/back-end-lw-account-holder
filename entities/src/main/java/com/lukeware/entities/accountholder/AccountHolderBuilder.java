package com.lukeware.entities.accountholder;

/**
 * @author Diego Morais
 */
public final class AccountHolderBuilder {

  private String identifierDocument;
  private String identifierCode;
  private boolean owner;
  private int sequence;

  private AccountHolderBuilder() {
    super();
  }

  public static AccountHolderBuilder builder() {
    return new AccountHolderBuilder();
  }

  public AccountHolderBuilder identifierCode(String identifierCode) {
    this.identifierCode = identifierCode;
    return this;
  }

  public AccountHolderBuilder identifierDocument(String identifierDocument) {
    this.identifierDocument = identifierDocument;
    return this;
  }

  public AccountHolderBuilder owner(boolean owner) {
    this.owner = owner;
    return this;
  }

  public AccountHolderBuilder sequence(int sequence) {
    this.sequence = sequence;
    return this;
  }


  public IAccountHolder build() {
    return new AccountHolder(this.identifierDocument,
                             this.identifierCode,
                             this.owner,
                             this.sequence
    );
  }


}
