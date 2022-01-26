package com.lukeware.entities.accountholder;

/**
 * @author Diego Morais
 */
final record AccountHolder(String identifierDocument,
                           String identifierCode,
                           boolean owner,
                           int sequence) implements IAccountHolder {}
