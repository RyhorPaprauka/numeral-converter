package com.danskebank.numeralsconverter.service;

import com.danskebank.numeralsconverter.model.RomanNumeral;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NumeralsConverterService {

    public String convert(Integer number) {
        List<RomanNumeral> romanNumerals = RomanNumeral.getReverseSortedValues();
        StringBuilder sb = new StringBuilder();
        int i = 0;

        while ((number > 0)) {
            RomanNumeral currentSymbol = romanNumerals.get(i);
            if (currentSymbol.getValue() <= number) {
                sb.append(currentSymbol.name());
                number -= currentSymbol.getValue();
            } else {
                i++;
            }
        }

        return sb.toString();
    }

    public Integer convert(String numeral) {
        int result = 0;
        List<RomanNumeral> romanNumerals = RomanNumeral.getReverseSortedValues();
        int i = 0;

        while ((numeral.length() > 0)) {
            RomanNumeral symbol = romanNumerals.get(i);
            if (numeral.startsWith(symbol.name())) {
                result += symbol.getValue();
                numeral = numeral.substring(symbol.name().length());
            } else {
                i++;
            }
        }

        return result;
    }
}
