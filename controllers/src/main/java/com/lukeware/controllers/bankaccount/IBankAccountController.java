package com.lukeware.controllers.bankaccount;

import com.lukeware.usecases.banckaccount.BankAccountResponse;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;
import java.util.Set;

/**
 * @author Diego Morais
 */
@RestController
@RequestMapping("bank-accounts")
public interface IBankAccountController {

  Optional<BankAccountResponse> save(BankAccountResquest resquest, String identifierDocument);

  Set<BankAccountResponse> findAll(String identifierCode);

}
