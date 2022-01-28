package com.lukeware.application.configuration;

import com.lukeware.apigateway.accountHolder.AccountHolderGatewayFactory;
import com.lukeware.apigateway.bankAccount.BankAccountGatewayFactory;
import com.lukeware.apigateway.bankAccount.IBankAccountRegisterDsGateway;
import com.lukeware.controllers.bankaccount.BankAccountControllerFactory;
import com.lukeware.controllers.bankaccount.IBankAccountController;
import com.lukeware.controllers.customer.CustomerControllerFactory;
import com.lukeware.controllers.customer.ICustomerController;
import com.lukeware.presenters.CustomerPresenterFactory;
import com.lukeware.repositories.bankAccount.BankAccountRegisterDsFactory;
import com.lukeware.repositories.bankAccount.BankAccountRepository;
import com.lukeware.usecases.accountholder.IAccountHolderGateway;
import com.lukeware.usecases.banckaccount.IBankAccountGateway;
import com.lukeware.usecases.customer.CustomerInteractorFactory;
import com.lukeware.usecases.customer.ICustomerInputBoundary;
import com.lukeware.usecases.customer.ICustomerPresenter;
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
  ICustomerPresenter createCustomerPresenter() {
    return CustomerPresenterFactory.builder().create();
  }

  @Bean
  IAccountHolderGateway createAccountHolderGateway() {
    return AccountHolderGatewayFactory.builder().create();
  }

  @Bean
  IBankAccountRegisterDsGateway bankAccountRegisterDsGateway(BankAccountRepository bankAccountRepository) {
    return BankAccountRegisterDsFactory.builder().create(bankAccountRepository);
  }

  @Bean
  IBankAccountGateway createBankAccountGateway(IBankAccountRegisterDsGateway bankAccountRepository) {
    return BankAccountGatewayFactory.builder().create(bankAccountRepository);
  }

  @Bean
  ICustomerInputBoundary createCustomerInputBoundary(IAccountHolderGateway accountHolderGateway,
                                                     IBankAccountGateway bankAccountGateway,
                                                     ICustomerPresenter customerPresenter) {
    return CustomerInteractorFactory.builder().create(accountHolderGateway, bankAccountGateway, customerPresenter);
  }

  @Bean
  ICustomerController customerController(ICustomerInputBoundary customerInputBoundary) {
    return CustomerControllerFactory.builder().create(customerInputBoundary);
  }

  @Bean
  IBankAccountController bankAccountController(IBankAccountRegisterDsGateway bankAccountRegisterDsGateway) {
    return BankAccountControllerFactory.builder().create(bankAccountRegisterDsGateway);
  }

}
