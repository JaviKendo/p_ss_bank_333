package com.bank.account.controller;

import com.bank.account.dto.AccountDetailsDTO;
import com.bank.account.exception.AccountDetailsNotFoundException;
import com.bank.account.service.AccountDetailsService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.math.BigInteger;
import java.util.List;

@ExtendWith(SpringExtension.class)
@WebMvcTest(AccountDetailsController.class)
public class AccountDetailsControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private AccountDetailsService service;
    @Autowired
    private AccountDetailsController controller;

  @Test
  public void test() {
      org.hamcrest.MatcherAssert.assertThat(controller, Matchers.notNullValue());
  }

    AccountDetailsDTO detailsDTO = new AccountDetailsDTO(1L, 1L, 1L, 1L, BigInteger.valueOf(220L), false, 1L);
    ResultMatcher[] matchers = {
            MockMvcResultMatchers.jsonPath("$.passportId", Matchers.is(1)),
            MockMvcResultMatchers.jsonPath("$.accountNumber", Matchers.is(1)),
            MockMvcResultMatchers.jsonPath("$.bankDetailsId", Matchers.is(1)),
            MockMvcResultMatchers.jsonPath("$.money", Matchers.is(220)),
            MockMvcResultMatchers.jsonPath("$.negativeBalance", Matchers.is(false)),
            MockMvcResultMatchers.jsonPath("$.profileId", Matchers.is(1))
    };

    @Test
    public void shouldSaveAccountDetails() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.post("/details/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(asJsonString(detailsDTO)))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void shouldReturnExceptionOnSave() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.post("/details")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(asJsonString(new AccountDetailsDTO())))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }


    @Test
    public void shouldReturnAccountDetailsWithId() throws Exception {
        Mockito.when(service.getAccountDetails(1L)).thenReturn(detailsDTO);

        ResultActions resultActions = this.mockMvc.perform(MockMvcRequestBuilders
                      .get("/details/{id}", 1L)
                      .contentType(MediaType.APPLICATION_JSON))
              .andDo(MockMvcResultHandlers.print())
              .andExpect(MockMvcResultMatchers.status().isOk());

        for (ResultMatcher matcher : matchers) {
            resultActions.andExpect(matcher);
        }
    }



    @Test
    public void shouldReturnExceptionOnFindById() throws Exception {
        Mockito.when(service.getAccountDetails(2L)).thenThrow(new AccountDetailsNotFoundException());

        this.mockMvc.perform(MockMvcRequestBuilders
                        .get("/details/{id}", 2L)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    @Test
    public void shouldReturnAllAccountDetails() throws Exception {
        Mockito.when(service.getAllAccountDetails()).thenReturn(getListDetailsDto());

        this.mockMvc.perform(MockMvcRequestBuilders
                        .get("/details/")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(2)));
    }

    @Test
    public void shouldDeleteAccountDetailsById() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders
                        .delete("/details/{id}", 1L)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void shouldUpdateAccountDetails() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders
                        .patch("/details")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(asJsonString(detailsDTO)))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    List<AccountDetailsDTO> getListDetailsDto() {
        AccountDetailsDTO firstDto = new AccountDetailsDTO(1L, 1L, 1L, 1L, BigInteger.valueOf(220L), false, 1L);
        AccountDetailsDTO secondDto = new AccountDetailsDTO(2L, 2L, 2L, 2L, BigInteger.valueOf(220L), false, 2L);
        return List.of(firstDto, secondDto);
    }
}



