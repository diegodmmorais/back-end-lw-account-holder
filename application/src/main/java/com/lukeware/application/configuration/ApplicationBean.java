package com.lukeware.application.configuration;

import com.lukeware.apigateway.accountHolder.AccountHolderGatewayBuilder;
import com.lukeware.apigateway.bankAccount.BankAccountGatewayBuilder;
import com.lukeware.controllers.CustomerControllerBuilder;
import com.lukeware.controllers.ICustomerController;
import com.lukeware.presenters.CustomerPresenterBuilder;
import com.lukeware.usecases.accountholder.IAccountHolderGateway;
import com.lukeware.usecases.banckaccount.IBankAccountGateway;
import com.lukeware.usecases.customer.CustomerInteractorBuilder;
import com.lukeware.usecases.customer.ICustomerInputBoundary;
import com.lukeware.usecases.customer.ICustomerPresenter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Diego Morais
 */
@Configuration
class ApplicationBean {

  @Bean
  ICustomerPresenter createCustomerPresenter() {
    return CustomerPresenterBuilder.builder().create();
  }

  @Bean
  IAccountHolderGateway createAccountHolderGateway() {
    return AccountHolderGatewayBuilder.builder().create();
  }

  @Bean
  IBankAccountGateway createBankAccountGateway() {
    return BankAccountGatewayBuilder.builder().create();
  }

  @Bean
  ICustomerInputBoundary createCustomerInputBoundary(IAccountHolderGateway accountHolderGateway,
                                                     IBankAccountGateway bankAccountGateway,
                                                     ICustomerPresenter customerPresenter) {
    return CustomerInteractorBuilder.builder().create(accountHolderGateway, bankAccountGateway, customerPresenter);
  }

  @Bean
  ICustomerController customerController(ICustomerInputBoundary customerInputBoundary) {
    return CustomerControllerBuilder.builder().create(customerInputBoundary);
  }

}
