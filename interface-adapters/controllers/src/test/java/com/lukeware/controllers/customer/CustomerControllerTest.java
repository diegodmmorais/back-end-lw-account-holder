package com.lukeware.controllers.customer;

import com.lukeware.usecases.customer.ds.CustomerDsResponse;
import com.lukeware.usecases.customer.boundary.ICustomerInputBoundary;
import com.lukeware.usecases.customer.TypeCustomer;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

/**
 * @author Diego Morais
 */
@ExtendWith(MockitoExtension.class)
@DisplayName("Customer controller test")
class CustomerControllerTest {

  @InjectMocks
  CustomerController customerController;

  @Mock
  ICustomerInputBoundary customerInputBoundary;

  @Test
  @DisplayName("Validate active customer")
  void validateActiveCustomer() {
    final var customerResponse = new CustomerDsResponse("789123456", "999.999.999-999", TypeCustomer.AC);
    customerResponse.setMessage("Customer is Active");

    Mockito.when(this.customerInputBoundary.validateActiveCustomerPf(Mockito.any()))
           .thenReturn(customerResponse);

    final var response = customerController.isActiveCustomer("789123456", "999.999.999-999");

    Assertions.assertThat(response).isNotNull();
    Assertions.assertThat(response.getIdentifierCode()).isNotNull().isEqualTo("789123456");
    Assertions.assertThat(response.getMessage()).isNotNull().isEqualTo("Customer is Active");
    Assertions.assertThat(response.getIdentifierDocument()).isNotNull().isEqualTo("999.999.999-999");

  }

}