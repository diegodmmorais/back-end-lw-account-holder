## Estrutura de pasta
```bach
├── Flow-Of-Control.png
├── README.md
├── apigateway
│   ├── pom.xml
│   └── src
│       ├── main
│       │   ├── java
│       │   │   └── com
│       │   │       └── lukeware
│       │   │           └── apigateway
│       │   │               ├── accountHolder
│       │   │               │   ├── AccountHolderGateway.java
│       │   │               │   └── AccountHolderGatewayFactory.java
│       │   │               └── bankAccount
│       │   │                   ├── BankAccountDsRequest.java
│       │   │                   ├── BankAccountDsResponse.java
│       │   │                   ├── BankAccountGateway.java
│       │   │                   ├── BankAccountGatewayFactory.java
│       │   │                   └── IBankAccountRegisterDsGateway.java
│       │   └── resources
│       └── test
│           └── java
│               └── com
│                   └── lukeware
│                       └── apigateway
│                           ├── accountHolder
│                           │   ├── AccountHolderGatewayFactoryTest.java
│                           │   └── AccountHolderGatewayTest.java
│                           └── bankAccount
│                               ├── BankAccountGatewayFactoryTest.java
│                               └── BankAccountGatewayTest.java
├── application
│   ├── pom.xml
│   └── src
│       ├── main
│       │   ├── java
│       │   │   └── com
│       │   │       └── lukeware
│       │   │           └── application
│       │   │               ├── Application.java
│       │   │               └── configuration
│       │   │                   └── ApplicationBean.java
│       │   └── resources
│       │       └── application.yml
│       └── test
│           ├── java
│           │   └── com
│           │       └── lukeware
│           │           └── application
│           │               └── ApplicationTest.java
│           └── resources
│               └── application.yml
├── controllers
│   ├── pom.xml
│   └── src
│       ├── main
│       │   ├── java
│       │   │   └── com
│       │   │       └── lukeware
│       │   │           └── controllers
│       │   │               ├── bankaccount
│       │   │               └── customer
│       │   │                   ├── CustomerController.java
│       │   │                   ├── CustomerControllerFactory.java
│       │   │                   └── ICustomerController.java
│       │   └── resources
│       └── test
│           ├── java
│           │   └── com
│           │       └── lukeware
│           │           └── controllers
│           │               └── customer
│           │                   ├── CustomerControllerFactoryTest.java
│           │                   └── CustomerControllerTest.java
│           └── resources
├── entities
│   └── src
│       ├── main
│       │   ├── java
│       │   │   └── com
│       │   │       └── lukeware
│       │   │           └── entities
│       │   │               ├── account
│       │   │               │   ├── Account.java
│       │   │               │   ├── AccountBuilder.java
│       │   │               │   ├── AccountValidateException.java
│       │   │               │   ├── IAccount.java
│       │   │               │   └── TypeAccount.java
│       │   │               └── accountholder
│       │   │                   ├── AccountHolder.java
│       │   │                   ├── AccountHolderBuilder.java
│       │   │                   └── IAccountHolder.java
│       │   └── resources
│       └── test
│           └── java
│               └── com
│                   └── lukeware
│                       └── entities
│                           └── account
│                               └── AccountTest.java
├── pom.xml
├── presenters
│   ├── pom.xml
│   └── src
│       ├── main
│       │   ├── java
│       │   │   └── com
│       │   │       └── lukeware
│       │   │           └── presenters
│       │   │               ├── CustomerPresenter.java
│       │   │               ├── CustomerPresenterException.java
│       │   │               └── CustomerPresenterFactory.java
│       │   └── resources
│       └── test
│           └── java
│               └── com
│                   └── lukeware
│                       └── presenters
│                           ├── CustomerPresenterFactoryTest.java
│                           └── CustomerPresenterTest.java
├── repositories
│   ├── pom.xml
│   └── src
│       ├── main
│       │   ├── java
│       │   │   └── com
│       │   │       └── lukeware
│       │   │           └── repositories
│       │   │               └── bankAccount
│       │   │                   ├── BankAccountMapper.java
│       │   │                   ├── BankAccountMapperBuilder.java
│       │   │                   ├── BankAccountRegisterDsFactory.java
│       │   │                   ├── BankAccountRegisterDsGateway.java
│       │   │                   └── BankAccountRepository.java
│       │   └── resources
│       └── test
│           ├── java
│           │   └── com
│           │       └── lukeware
│           │           └── repositories
│           │               ├── TestApplication.java
│           │               └── bankAccount
│           │                   ├── BankAccountRegisterDsFactoryTest.java
│           │                   └── BankAccountRegisterDsGatewayTest.java
│           └── resources
│               └── application.yml
└── usecases
    ├── pom.xml
    └── src
        ├── main
        │   ├── java
        │   │   └── com
        │   │       └── lukeware
        │   │           └── usecases
        │   │               ├── IGateway.java
        │   │               ├── accountholder
        │   │               │   ├── AccountHolderResponse.java
        │   │               │   └── IAccountHolderGateway.java
        │   │               ├── banckaccount
        │   │               │   ├── BankAccountResponse.java
        │   │               │   └── IBankAccountGateway.java
        │   │               └── customer
        │   │                   ├── CustomerInteractor.java
        │   │                   ├── CustomerInteractorFactory.java
        │   │                   ├── CustomerRequest.java
        │   │                   ├── CustomerResponse.java
        │   │                   ├── ICustomerInputBoundary.java
        │   │                   ├── ICustomerPresenter.java
        │   │                   └── TypeCustomer.java
        │   └── resources
        └── test
            └── java
                └── com
                    └── lukeware
                        └── usecases
                            └── customer
                                ├── CustomerInteractorFactoryTest.java
                                └── CustomerInteractorTest.java
```
