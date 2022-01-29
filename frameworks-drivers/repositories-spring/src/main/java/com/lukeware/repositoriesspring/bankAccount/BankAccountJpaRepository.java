package com.lukeware.repositoriesspring.bankAccount;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author Diego Morais
 */
public interface BankAccountJpaRepository extends JpaRepository<BankAccountMapper, Long> {

  List<BankAccountMapper> findAllByIdentifierCode(String identifierCode);
}
