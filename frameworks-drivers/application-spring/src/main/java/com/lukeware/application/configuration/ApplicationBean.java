package com.lukeware.application.configuration;

import com.lukeware.controllers.bankaccount.BankAccountControllerFactory;
import com.lukeware.controllers.bankaccount.IBankAccountController;
import com.lukeware.controllers.customer.CustomerControllerFactory;
import com.lukeware.controllers.customer.ICustomerController;
import com.lukeware.gateways.accountHolder.AccountHolderGatewayFactory;
import com.lukeware.presenters.customer.CustomerPresenterFactory;
import com.lukeware.repositoriesspring.bankAccount.BankAccountJpaRepository;
import com.lukeware.repositoriesspring.bankAccount.BankAccountRepositoryFactory;
import com.lukeware.usecases.accountholder.IAccountHolderGateway;
import com.lukeware.usecases.banckaccount.IBankAccountRepository;
import com.lukeware.usecases.customer.CustomerInteractorFactory;
import com.lukeware.usecases.customer.boundary.ICustomerInputBoundary;
import com.lukeware.usecases.customer.boundary.ICustomerOutputBoundary;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * @author Diego Morais
 */
@Configuration
@EnableJpaRepositories("com.lukeware.repositoriesspring.*")
@EntityScan("com.lukeware.repositoriesspring.*")
class ApplicationBean {

  @Bean
  ICustomerOutputBoundary customerOutputBoundary() {
    return CustomerPresenterFactory.builder().create();
  }

  @Bean
  IAccountHolderGateway accountHolderGateway() {
    return AccountHolderGatewayFactory.builder().create();
  }

  @Bean
  IBankAccountRepository bankAccountRepository(BankAccountJpaRepository bankAccountJpaRepository) {
    return BankAccountRepositoryFactory.builder().create(bankAccountJpaRepository);
  }

  @Bean
  ICustomerInputBoundary customerInputBoundary(IAccountHolderGateway accountHolderGateway,
                                               IBankAccountRepository bankAccountRepository,
                                               ICustomerOutputBoundary customerPresenter) {
    return CustomerInteractorFactory.builder().create(accountHolderGateway, bankAccountRepository, customerPresenter);
  }

  @Bean
  ICustomerController customerController(ICustomerInputBoundary customerInputBoundary) {
    return CustomerControllerFactory.builder().create(customerInputBoundary);
  }

  @Bean
  IBankAccountController bankAccountController(IBankAccountRepository bankAccountRepository) {
    return BankAccountControllerFactory.builder().create(bankAccountRepository);
  }

}
