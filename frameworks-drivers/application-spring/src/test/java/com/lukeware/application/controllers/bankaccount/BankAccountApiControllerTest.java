package com.lukeware.application.controllers.bankaccount;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lukeware.controllers.bankaccount.BankAccountResquest;
import com.lukeware.controllers.bankaccount.IBankAccountController;
import com.lukeware.usecases.banckaccount.ds.BankAccountDsResponse;
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

import java.time.LocalDate;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author Diego Morais
 */
@WebMvcTest
@AutoConfigureMockMvc
@DisplayName("Bank account controller test")
@ContextConfiguration(classes = {BankAccountApiController.class})
class BankAccountApiControllerTest {

  @MockBean
  IBankAccountController bankAccountController;

  @Autowired
  private MockMvc mockMvc;

  @Test
  @DisplayName("1 - save bank account")
  void saveBankAccount() throws Exception {
    final var bankAccountResponse = new BankAccountDsResponse(
        "789123456",
        "999.999.999-999",
        true,
        false,
        "CHECKING_ACCOUNT",
        LocalDate.now(),
        LocalDate.now()
    );

    final var bankAccountRequest = new BankAccountResquest(
        "789123456",
        true,
        false,
        "CHECKING_ACCOUNT",
        LocalDate.now(),
        LocalDate.now()
    );

    Mockito.when(this.bankAccountController.save(Mockito.any(), Mockito.any()))
           .thenReturn(Optional.of(bankAccountResponse));

    final var objectMapper = new ObjectMapper();

    final var requestBuilder = MockMvcRequestBuilders.post("/bank-accounts")
                                                     .content(objectMapper.writeValueAsString(bankAccountRequest))
                                                     .contentType(MediaType.APPLICATION_JSON)
                                                     .header("x-identifier-document", "999.999.999-99");

    this.mockMvc
        .perform(requestBuilder)
        .andDo(MockMvcResultHandlers.print())
        .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.jsonPath("$.identifierCode").value("789123456"))
        .andExpect(MockMvcResultMatchers.jsonPath("$.customerId").value("999.999.999-999"))
        .andExpect(MockMvcResultMatchers.jsonPath("$.active").value(true))
        .andExpect(MockMvcResultMatchers.jsonPath("$.externalMovement").value(false))
        .andExpect(MockMvcResultMatchers.jsonPath("$.type").value("CHECKING_ACCOUNT"))
    ;

  }


  @Test
  @DisplayName("2 - find all bank account")
  void findAllBankAccount() throws Exception {
    final var bankAccountResponse = new BankAccountDsResponse(
        "789123456",
        "999.999.999-999",
        true,
        false,
        "CHECKING_ACCOUNT",
        LocalDate.now(),
        LocalDate.now()
    );

    Mockito.when(this.bankAccountController.findAll("789123456"))
           .thenReturn(Stream.of(bankAccountResponse).collect(Collectors.toSet()));

    final var requestBuilder = MockMvcRequestBuilders.get("/bank-accounts/{code}", 789123456)
                                                     .header("x-identifier-document", "999.999.999-99");
    this.mockMvc
        .perform(requestBuilder)
        .andDo(MockMvcResultHandlers.print())
        .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.jsonPath("$[0].identifierCode").value("789123456"))
        .andExpect(MockMvcResultMatchers.jsonPath("$[0].customerId").value("999.999.999-999"))
        .andExpect(MockMvcResultMatchers.jsonPath("$[0].active").value(true))
        .andExpect(MockMvcResultMatchers.jsonPath("$[0].externalMovement").value(false))
        .andExpect(MockMvcResultMatchers.jsonPath("$[0].type").value("CHECKING_ACCOUNT"))
    ;

  }
}