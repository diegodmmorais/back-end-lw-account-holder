package com.lukeware.usecases.accountholder;

import java.util.Set;

/**
 * @author Diego Morais
 */
public interface IAccountHolderGateway {
  Set<AccountHolderResponse> findAll(String identifierCode);
}
