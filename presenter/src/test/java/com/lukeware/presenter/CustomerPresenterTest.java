package com.lukeware.presenter;

import com.lukeware.usecases.customer.CustomerResponse;
import com.lukeware.usecases.customer.TypeCustomer;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class CustomerPresenterTest {

  @InjectMocks
  CustomerPresenter customerPresenter;

  @Test
  void successView() {
    /* preparation */
    final var customerResponse = new CustomerResponse("789123456", "999.999.999-99", TypeCustomer.AC);

    /* execution */
    final var response = customerPresenter.successView(customerResponse);

    /* validation */
    Assertions.assertThat(response).isNotNull();
    Assertions.assertThat(response.getIdentifierDocument()).isNotNull().isEqualTo("999.999.999-99");
    Assertions.assertThat(response.getIdentifierCode()).isNotNull().isEqualTo("789123456");
    Assertions.assertThat(response.getType()).isNotNull().isEqualTo(TypeCustomer.AC);
    Assertions.assertThat(response.getMessage()).isNotNull().isEqualTo("Customer is Active customer");
  }

  @Test
  void failView() {
    /* preparation */
    final var customerResponse = new CustomerResponse("789123456", "999.999.999-99", TypeCustomer.AC);

    /* execution/validation */
    Assertions.assertThatThrownBy(() -> customerPresenter.failView(customerResponse, "Customer not found"))
              .isInstanceOf(CustomerPresenterException.class)
              .hasMessageContaining("Error customer 789123456: Customer not found");
  }

}