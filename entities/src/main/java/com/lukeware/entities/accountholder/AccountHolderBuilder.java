package com.lukeware.entities.accountholder;

/**
 * @author Diego Morais
 */
public final class AccountHolderBuilder {
  
  private static AccountHolderBuilder accountHolderBuilder;
  private String identifierDocument;
  private String identifierCode;
  private boolean owner;
  private int sequence;
  
  private AccountHolderBuilder() {
    super();
  }
  
  public static synchronized AccountHolderBuilder builder() {
    return accountHolderBuilder = new AccountHolderBuilder();
  }
  
  public AccountHolderBuilder identifierCode(String identifierCode) {
    accountHolderBuilder.identifierCode = identifierCode;
    return accountHolderBuilder;
  }
  
  public AccountHolderBuilder identifierDocument(String identifierDocument) {
    accountHolderBuilder.identifierDocument = identifierDocument;
    return accountHolderBuilder;
  }
  
  public AccountHolderBuilder owner(boolean owner) {
    accountHolderBuilder.owner = owner;
    return accountHolderBuilder;
  }
  
  public AccountHolderBuilder sequence(int sequence) {
    accountHolderBuilder.sequence = sequence;
    return accountHolderBuilder;
  }
  
  
  public IAccountHolder build() {
    return new AccountHolder(accountHolderBuilder.identifierDocument,
                             accountHolderBuilder.identifierCode,
                             accountHolderBuilder.owner,
                             accountHolderBuilder.sequence
    );
  }
  
  
}
