package com.lukeware.application.configuration;

import com.lukeware.controllers.bankaccount.BankAccountControllerFactory;
import com.lukeware.controllers.bankaccount.IBankAccountController;
import com.lukeware.controllers.customer.CustomerControllerFactory;
import com.lukeware.controllers.customer.ICustomerController;
import com.lukeware.presenters.bankaccount.BankAccountPresenterFactory;
import com.lukeware.presenters.customer.CustomerPresenterFactory;
import com.lukeware.repositories.bankAccount.BankAccountJpaRepository;
import com.lukeware.repositories.bankAccount.BankAccountRepositoryFactory;
import com.lukeware.serviceslisteners.accountHolder.AccountHolderServiceFactory;
import com.lukeware.usecases.accountholder.IAccountHolderGateway;
import com.lukeware.usecases.banckaccount.BankAccountInteractorFactory;
import com.lukeware.usecases.banckaccount.IBankAccountDataProvider;
import com.lukeware.usecases.banckaccount.boundary.IBankAccountInputBoundary;
import com.lukeware.usecases.banckaccount.boundary.IBankAccountOutputBoundary;
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
    return CustomerPresenterFactory.getInstance().create();
  }

  @Bean
  IBankAccountOutputBoundary bankAccountOutputBoundary() {
    return BankAccountPresenterFactory.getInstance().create();
  }

  @Bean
  IAccountHolderGateway accountHolderGateway() {
    return AccountHolderServiceFactory.getInstance().create();
  }

  @Bean
  IBankAccountDataProvider bankAccountRepository(BankAccountJpaRepository bankAccountJpaRepository) {
    return BankAccountRepositoryFactory.getInstance().create(bankAccountJpaRepository);
  }

  @Bean
  ICustomerInputBoundary customerInputBoundary(IAccountHolderGateway accountHolderGateway,
                                               IBankAccountDataProvider bankAccountRepository,
                                               ICustomerOutputBoundary customerPresenter) {
    return CustomerInteractorFactory.getInstance().create(accountHolderGateway, bankAccountRepository, customerPresenter);
  }

  @Bean
  ICustomerController customerController(ICustomerInputBoundary customerInputBoundary) {
    return CustomerControllerFactory.getInstance().create(customerInputBoundary);
  }

  @Bean
  IBankAccountInputBoundary bankAccountInputBoundary(IBankAccountDataProvider bankAccountDataProvider,
                                                     IBankAccountOutputBoundary bankAccountOutputBoundary) {
    return BankAccountInteractorFactory.getInstance().create(bankAccountDataProvider, bankAccountOutputBoundary);
  }

  @Bean
  IBankAccountController bankAccountController(IBankAccountInputBoundary bankAccountRepository) {
    return BankAccountControllerFactory.getInstance().create(bankAccountRepository);
  }

}
