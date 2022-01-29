package com.lukeware.application.configuration;

import com.lukeware.controllers.bankaccount.BankAccountControllerFactory;
import com.lukeware.controllers.bankaccount.IBankAccountController;
import com.lukeware.controllers.customer.CustomerControllerFactory;
import com.lukeware.controllers.customer.ICustomerController;
import com.lukeware.presenters.bankaccount.BankAccountPresenterFactory;
import com.lukeware.presenters.customer.CustomerPresenterFactory;
import com.lukeware.repositories.bankAccount.BankAccountJpaRepository;
import com.lukeware.repositories.bankAccount.BankAccountRepositoryFactory;
import com.lukeware.restclients.accountHolder.AccountHolderServiceFactory;
import com.lukeware.usecases.accountholder.IAccountHolderGateway;
import com.lukeware.usecases.banckaccount.IBankAccountGateway;
import com.lukeware.usecases.banckaccount.IBankAccountOutputBoundary;
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
@EnableJpaRepositories("com.lukeware.repositories.*")
@EntityScan("com.lukeware.repositories.*")
class ApplicationBean {

  @Bean
  ICustomerOutputBoundary customerOutputBoundary() {
    return CustomerPresenterFactory.builder().create();
  }

  @Bean
  IBankAccountOutputBoundary bankAccountOutputBoundary() {
    return BankAccountPresenterFactory.builder().create();
  }

  @Bean
  IAccountHolderGateway accountHolderGateway() {
    return AccountHolderServiceFactory.builder().create();
  }

  @Bean
  IBankAccountGateway bankAccountRepository(BankAccountJpaRepository bankAccountJpaRepository) {
    return BankAccountRepositoryFactory.builder().create(bankAccountJpaRepository);
  }

  @Bean
  ICustomerInputBoundary customerInputBoundary(IAccountHolderGateway accountHolderGateway,
                                               IBankAccountGateway bankAccountRepository,
                                               ICustomerOutputBoundary customerPresenter) {
    return CustomerInteractorFactory.builder().create(accountHolderGateway, bankAccountRepository, customerPresenter);
  }

  @Bean
  ICustomerController customerController(ICustomerInputBoundary customerInputBoundary) {
    return CustomerControllerFactory.builder().create(customerInputBoundary);
  }

  @Bean
  IBankAccountController bankAccountController(IBankAccountGateway bankAccountRepository, IBankAccountOutputBoundary bankAccountOutputBoundary) {
    return BankAccountControllerFactory.builder().create(bankAccountRepository, bankAccountOutputBoundary);
  }

}
