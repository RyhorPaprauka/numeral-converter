package com.danskebank.numeralsconverter.controller;

import com.danskebank.numeralsconverter.service.NumeralsConverterService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static com.danskebank.numeralsconverter.model.RomanNumeral.*;
import static com.danskebank.numeralsconverter.util.Constant.NUMERAL_EXCEPTION_MESSAGE;
import static com.danskebank.numeralsconverter.util.UrlPath.*;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.*;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
@WebMvcTest(NumeralConverterController.class)
@AutoConfigureMockMvc
class NumeralConverterControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private NumeralsConverterService converterService;

    @Test
    void givenValidNumber_thenOkIsReturned() throws Exception {
        when(converterService.convert(CM.getValue())).thenReturn(CM.name());

        mockMvc.perform(MockMvcRequestBuilders.get(CONVERT + NUMBER + "/" + CM.getValue()).contentType(APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", is(CM.name())));
        verify(converterService).convert(CM.getValue());
        reset(converterService);
    }

    @ParameterizedTest
    @ValueSource(ints = {0, -1, 4001, 125463457})
    void givenInvalidNumber_then404IsReturned(Integer invalidValue) throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get(CONVERT + NUMBER + "/" + invalidValue).contentType(APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$", is("must be between 1 and 4000")));
        verify(converterService, never()).convert(anyInt());
    }

    @Test
    void givenValidNumeral_thenOkIsReturned() throws Exception {
        when(converterService.convert(CM.name())).thenReturn(CM.getValue());

        mockMvc.perform(MockMvcRequestBuilders.get(CONVERT + NUMERAL + "/" + CM.name()).contentType(APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", is(CM.getValue())));
        verify(converterService).convert(CM.name());
        reset(converterService);
    }

    @ParameterizedTest
    @NullSource
    @ValueSource(strings = {"4000", "AMCX", "iv", "Xl"})
    void givenInvalidNumeral_then404IsReturned(String invalidValue) throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get(CONVERT + NUMERAL + "/" + invalidValue).contentType(APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$", is(NUMERAL_EXCEPTION_MESSAGE)));
        verify(converterService, never()).convert(anyString());
    }
}