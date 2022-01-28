package com.lukeware.gateways.accountHolder;

import com.lukeware.usecases.accountholder.AccountHolderResponse;
import com.lukeware.usecases.accountholder.IAccountHolderGateway;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * simulation service api external
 *
 * @author Diego Morais
 */
final record AccountHolderGateway() implements IAccountHolderGateway {


  private static Set<AccountHolderResponse> accountHolders = Stream.of(new AccountHolderResponse("789123465", true, 1),
                                                                       new AccountHolderResponse("789123963", true, 1),
                                                                       new AccountHolderResponse("789123749", true, 1),
                                                                       new AccountHolderResponse("789123741", true, 1))
                                                                   .collect(Collectors.toSet());

  @Override
  public Set<AccountHolderResponse> findAll(String identifierCode) {
    return accountHolders.stream().filter(it -> it.identifierCode().equals(identifierCode)).collect(Collectors.toSet());
  }
}
