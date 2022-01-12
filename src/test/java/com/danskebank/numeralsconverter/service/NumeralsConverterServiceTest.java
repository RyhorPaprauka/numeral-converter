package com.danskebank.numeralsconverter.service;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class NumeralsConverterServiceTest {

    private NumeralsConverterService converterService = new NumeralsConverterService();

    @ParameterizedTest
    @MethodSource("com.danskebank.numeralsconverter.util.TestValues#provideValidTestValues")
    void givenValidNumber_thenValidNumericIsReturned(Integer number, String numeric) {
        assertThat(converterService.convert(number)).isEqualTo(numeric);
    }

    @ParameterizedTest
    @MethodSource("com.danskebank.numeralsconverter.util.TestValues#provideValidTestValues")
    void givenValidNumeric_thenValidNumberIsReturned(Integer number, String numeric) {
        assertThat(converterService.convert(numeric)).isEqualTo(number);
    }
}