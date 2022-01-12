package com.danskebank.numeralsconverter.util;

import lombok.experimental.UtilityClass;

@UtilityClass
public class Constant {

    public final String NUMERAL_PATTERN ="^M{0,4}(CM|CD|D?C{0,3})(XC|XL|L?X{0,3})(IX|IV|V?I{0,3})$";
    public final String NUMERAL_EXCEPTION_MESSAGE ="Must be Roman Numeral";
}
