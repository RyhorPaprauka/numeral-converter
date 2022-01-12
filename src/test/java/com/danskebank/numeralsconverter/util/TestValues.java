package com.danskebank.numeralsconverter.util;

import java.util.stream.Stream;
import lombok.experimental.UtilityClass;
import org.junit.jupiter.params.provider.Arguments;

@UtilityClass
public class TestValues {

    public Stream<Arguments> provideValidTestValues() {
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
