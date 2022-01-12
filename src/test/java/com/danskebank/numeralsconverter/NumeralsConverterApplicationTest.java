package com.danskebank.numeralsconverter;

import com.danskebank.numeralsconverter.controller.NumeralConverterController;
import com.danskebank.numeralsconverter.service.NumeralsConverterService;
import org.junit.jupiter.api.Test;
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

    @Test
    void givenValidNumber_thenResultIsOkAndValid() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get(CONVERT + NUMBER + "/" + CM.getValue()).contentType(APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", is(CM.name())));
    }

    @Test
    void givenValidNumeral_thenResultIsOkAndValid() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get(CONVERT + NUMERAL + "/" + CM.name()).contentType(APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", is(CM.getValue())));
    }

}