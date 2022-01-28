package com.lukeware.controllers;

import com.lukeware.usecases.customer.CustomerResponse;
import com.lukeware.usecases.customer.ICustomerInputBoundary;
import com.lukeware.usecases.customer.TypeCustomer;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

/**
 * @author Diego Morais
 */
@WebMvcTest
@AutoConfigureMockMvc
@DisplayName("Customer controller test")
@ContextConfiguration(classes = {CustomerController.class})
class CustomerControllerTest {

  @MockBean
  ICustomerInputBoundary customerInputBoundary;

  @Autowired
  private MockMvc mockMvc;

  @Test
  @DisplayName("Validate active customer")
  void teste() throws Exception {
    final var customerResponse = new CustomerResponse("789123456", "999.999.999-999", TypeCustomer.AC);

    Mockito.when(this.customerInputBoundary.validateActiveCustomerPf(Mockito.any()))
           .thenReturn(customerResponse);

    final var requestBuilder = MockMvcRequestBuilders.get("/customers/active-customer/{code}", "789123456")
                                                     .header("x-identifier-document", "999.999.999-99");
    this.mockMvc
        .perform(requestBuilder)
        .andDo(MockMvcResultHandlers.print())
        .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.jsonPath("$.identifierCode").value("789123456"))
        .andExpect(MockMvcResultMatchers.jsonPath("$.identifierDocument").value("999.999.999-999"))
        .andExpect(MockMvcResultMatchers.jsonPath("$.type").value("AC"));

  }

}