package com.danskebank.numeralsconverter.service;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class NumeralsConverterServiceTest {

    private NumeralsConverterService converterService = new NumeralsConverterService();

    @ParameterizedTest
    @MethodSource("provideValidTestValues")
    void givenValidNumber_thenValidNumericIsReturned(Integer number, String numeric) {
        assertThat(converterService.convert(number)).isEqualTo(numeric);
    }

    @ParameterizedTest
    @MethodSource("provideValidTestValues")
    void givenValidNumeric_thenValidNumberIsReturned(Integer number, String numeric) {
        assertThat(converterService.convert(numeric)).isEqualTo(number);
    }

    private static Stream<Arguments> provideValidTestValues() {
        return Stream.of(
                Arguments.of(1, "I"),
                Arguments.of(4, "IV"),
                Arguments.of(9, "IX"),
                Arguments.of(90, "XC"),
                Arguments.of(95, "XCV"),
                Arguments.of(900, "CM"),
                Arguments.of(1903, "MCMIII"),
                Arguments.of(1997, "MCMXCVII"),
                Arguments.of(400, "CD"),
                Arguments.of(4000, "MMMM"));
    }
}