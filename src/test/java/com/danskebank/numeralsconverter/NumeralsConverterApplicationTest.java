package com.danskebank.numeralsconverter;

import com.danskebank.numeralsconverter.controller.NumeralConverterController;
import com.danskebank.numeralsconverter.service.NumeralsConverterService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static com.danskebank.numeralsconverter.model.RomanNumeral.CM;
import static com.danskebank.numeralsconverter.util.UrlPath.CONVERT;
import static com.danskebank.numeralsconverter.util.UrlPath.NUMBER;
import static com.danskebank.numeralsconverter.util.UrlPath.NUMERAL;
import static org.hamcrest.Matchers.is;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(classes = {NumeralConverterController.class, NumeralsConverterService.class})
@AutoConfigureMockMvc
class NumeralsConverterApplicationTest {

    @Autowired
    private MockMvc mockMvc;

    @ParameterizedTest
    @MethodSource("com.danskebank.numeralsconverter.util.TestValues#provideValidTestValues")
    void givenValidNumber_thenResultIsOkAndValid(Integer number, String numeric) throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get(CONVERT + NUMBER + "/" + number).contentType(APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", is(numeric)));
    }

    @ParameterizedTest
    @MethodSource("com.danskebank.numeralsconverter.util.TestValues#provideValidTestValues")
    void givenValidNumeral_thenResultIsOkAndValid(Integer number, String numeric) throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get(CONVERT + NUMERAL + "/" + numeric).contentType(APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", is(number)));
    }

}