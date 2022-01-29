## Estrutura de pasta

```bach
.
├── Flow-Of-Control.png
├── README.md
├── TREE.md
├── entities
│   ├── pom.xml
│   └── src
│       ├── main
│       │   ├── java
│       │   │   └── com
│       │   │       └── lukeware
│       │   │           └── entities
│       │   │               ├── accountholder
│       │   │               │   ├── AccountHolder.java
│       │   │               │   ├── AccountHolderBuilder.java
│       │   │               │   └── IAccountHolder.java
│       │   │               └── bankaccount
│       │   │                   ├── BankAccountBuilder.java
│       │   │                   ├── BankAccountValidateException.java
│       │   │                   ├── BankBankAccount.java
│       │   │                   ├── IBankAccount.java
│       │   │                   └── TypeAccount.java
│       │   └── resources
│       └── test
│           └── java
│               └── com
│                   └── lukeware
│                       └── entities
│                           └── bankaccount
│                               └── BankAccountTest.java
├── frameworks-drivers
│   ├── application-spring
│   │   ├── data
│   │   │   └── local-test.mv.db
│   │   ├── pom.xml
│   │   └── src
│   │       ├── main
│   │       │   ├── java
│   │       │   │   └── com
│   │       │   │       └── lukeware
│   │       │   │           └── application
│   │       │   │               ├── Application.java
│   │       │   │               ├── configuration
│   │       │   │               │   └── ApplicationBean.java
│   │       │   │               └── controllers
│   │       │   │                   ├── bankaccount
│   │       │   │                   │   └── BankAccountApiController.java
│   │       │   │                   └── customer
│   │       │   │                       └── CustomerApiController.java
│   │       │   └── resources
│   │       │       └── application.yml
│   │       └── test
│   │           ├── java
│   │           │   └── com
│   │           │       └── lukeware
│   │           │           └── application
│   │           │               ├── ApplicationTest.java
│   │           │               └── controllers
│   │           │                   ├── bankaccount
│   │           │                   │   └── BankAccountApiControllerTest.java
│   │           │                   └── customer
│   │           │                       └── CustomerApiControllerTest.java
│   │           └── resources
│   │               └── application.yml
│   ├── pom.xml
│   └── repositories-spring
│       ├── pom.xml
│       └── src
│           ├── main
│           │   ├── java
│           │   │   └── com
│           │   │       └── lukeware
│           │   │           └── repositoriesspring
│           │   │               └── bankAccount
│           │   │                   ├── BankAccountJpaRepository.java
│           │   │                   ├── BankAccountMapper.java
│           │   │                   ├── BankAccountMapperBuilder.java
│           │   │                   ├── BankAccountRepository.java
│           │   │                   └── BankAccountRepositoryFactory.java
│           │   └── resources
│           └── test
│               ├── java
│               │   └── com
│               │       └── lukeware
│               │           └── repositoriesspring
│               │               ├── TestApplication.java
│               │               └── bankAccount
│               │                   ├── BankAccountMapperTest.java
│               │                   ├── BankAccountRegisterDsGatewayTest.java
│               │                   └── BankAccountRepositoryFactoryTest.java
│               └── resources
│                   └── application.yml
├── interface-adapters
│   ├── controllers
│   │   ├── pom.xml
│   │   └── src
│   │       ├── main
│   │       │   ├── java
│   │       │   │   └── com
│   │       │   │       └── lukeware
│   │       │   │           └── controllers
│   │       │   │               ├── bankaccount
│   │       │   │               │   ├── BankAccountController.java
│   │       │   │               │   ├── BankAccountControllerFactory.java
│   │       │   │               │   ├── BankAccountResquest.java
│   │       │   │               │   └── IBankAccountController.java
│   │       │   │               └── customer
│   │       │   │                   ├── CustomerController.java
│   │       │   │                   ├── CustomerControllerFactory.java
│   │       │   │                   └── ICustomerController.java
│   │       │   └── resources
│   │       └── test
│   │           ├── java
│   │           │   └── com
│   │           │       └── lukeware
│   │           │           └── controllers
│   │           │               ├── bankaccount
│   │           │               │   ├── BankAccountControllerFactoryTest.java
│   │           │               │   └── BankAccountControllerTest.java
│   │           │               └── customer
│   │           │                   ├── CustomerControllerFactoryTest.java
│   │           │                   └── CustomerControllerTest.java
│   │           └── resources
│   ├── gateways
│   │   ├── pom.xml
│   │   └── src
│   │       ├── main
│   │       │   ├── java
│   │       │   │   └── com
│   │       │   │       └── lukeware
│   │       │   │           └── gateways
│   │       │   │               └── accountHolder
│   │       │   │                   ├── AccountHolderGateway.java
│   │       │   │                   └── AccountHolderGatewayFactory.java
│   │       │   └── resources
│   │       └── test
│   │           └── java
│   │               └── com
│   │                   └── lukeware
│   │                       └── gateways
│   │                           └── accountHolder
│   │                               ├── AccountHolderGatewayFactoryTest.java
│   │                               └── AccountHolderGatewayTest.java
│   ├── pom.xml
│   └── presenters
│       ├── pom.xml
│       └── src
│           ├── main
│           │   ├── java
│           │   │   └── com
│           │   │       └── lukeware
│           │   │           └── presenters
│           │   │               ├── bankaccount
│           │   │               │   ├── BankAccountPresenter.java
│           │   │               │   ├── BankAccountPresenterException.java
│           │   │               │   └── BankAccountPresenterFactory.java
│           │   │               └── customer
│           │   │                   ├── CustomerPresenter.java
│           │   │                   ├── CustomerPresenterException.java
│           │   │                   └── CustomerPresenterFactory.java
│           │   └── resources
│           └── test
│               └── java
│                   └── com
│                       └── lukeware
│                           └── presenters
│                               ├── bankaccount
│                               │   ├── BankAccountPresenterFactoryTest.java
│                               │   └── BankAccountPresenterTest.java
│                               └── customer
│                                   ├── CustomerPresenterFactoryTest.java
│                                   └── CustomerPresenterTest.java
├── pom.xml
└── use-cases
    ├── pom.xml
    └── src
        ├── main
        │   ├── java
        │   │   └── com
        │   │       └── lukeware
        │   │           └── usecases
        │   │               ├── IGateway.java
        │   │               ├── IOutputBoundary.java
        │   │               ├── accountholder
        │   │               │   ├── AccountHolderDsResponse.java
        │   │               │   └── IAccountHolderGateway.java
        │   │               ├── banckaccount
        │   │               │   ├── IBankAccountMapper.java
        │   │               │   ├── IBankAccountOutputBoundary.java
        │   │               │   ├── IBankAccountRepository.java
        │   │               │   └── ds
        │   │               │       ├── BankAccountDsRequest.java
        │   │               │       └── BankAccountDsResponse.java
        │   │               └── customer
        │   │                   ├── CustomerInteractor.java
        │   │                   ├── CustomerInteractorFactory.java
        │   │                   ├── TypeCustomer.java
        │   │                   ├── boundary
        │   │                   │   ├── ICustomerInputBoundary.java
        │   │                   │   └── ICustomerOutputBoundary.java
        │   │                   └── ds
        │   │                       ├── CustomerDsRequest.java
        │   │                       └── CustomerDsResponse.java
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
