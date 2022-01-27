package com.lukeware.apigateway.accountHolder;

import com.lukeware.usecases.accountholder.AccountHolderResponse;
import com.lukeware.usecases.accountholder.IAccountHolderGateway;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author Diego Morais
 */
final record AccountHolderGateway() implements IAccountHolderGateway {


  private static Set<AccountHolderResponse> accountHolders = Stream.of(new AccountHolderResponse("789123465", true, 1),
                                                                       new AccountHolderResponse("789123963", true, 2),
                                                                       new AccountHolderResponse("789123741", false, 3))
                                                                   .collect(Collectors.toSet());

  @Override
  public Set<AccountHolderResponse> findAll(String identifierCode) {
    return accountHolders;
  }
}
