package com.lukeware.repositories.bankAccount;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author Diego Morais
 */
public interface BankAccountRepository extends JpaRepository<BankAccountMapper, Long> {

  List<BankAccountMapper> findAllByIdentifierCode(String identifierCode);
}
