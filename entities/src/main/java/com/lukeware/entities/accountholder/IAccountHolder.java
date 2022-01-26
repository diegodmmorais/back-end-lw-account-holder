package com.lukeware.entities.accountholder;

/**
 * @author Diego Morais
 */
public interface IAccountHolder {
  boolean owner();
  
  String identifierDocument();
  
  String identifierCode();
  
  int sequence();
}
