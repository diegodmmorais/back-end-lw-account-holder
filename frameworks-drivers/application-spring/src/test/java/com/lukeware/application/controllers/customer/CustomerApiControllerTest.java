package com.lukeware.application.controllers.customer;

import com.lukeware.controllers.customer.ICustomerController;
import com.lukeware.usecases.customer.ds.CustomerDsResponse;
import com.lukeware.usecases.customer.TypeCustomer;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
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
@ContextConfiguration(classes = {CustomerApiController.class})
class CustomerApiControllerTest {

  @MockBean
  ICustomerController customerController;

  @Autowired
  private MockMvc mockMvc;

  @Test
  @DisplayName("Validate active customer")
  void teste() throws Exception {
    final var customerResponse = new CustomerDsResponse("789123456", "999.999.999-999", TypeCustomer.AC);

    Mockito.when(this.customerController.isActiveCustomer(Mockito.any(), Mockito.any())).thenReturn(customerResponse);

    final var requestBuilder = MockMvcRequestBuilders.get("/customers/active-customer/{code}", "789123456")
                                                     .contentType(MediaType.APPLICATION_JSON)
                                                     .header("x-identifier-document", "999.999.999-99");
    this.mockMvc
        .perform(requestBuilder)
        .andDo(MockMvcResultHandlers.print())
        .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.jsonPath("$.identifierCode").value("789123456"))
        .andExpect(MockMvcResultMatchers.jsonPath("$.identifierDocument").value("999.999.999-999"))
        .andExpect(MockMvcResultMatchers.jsonPath("$.type").value("AC"));

  }

}