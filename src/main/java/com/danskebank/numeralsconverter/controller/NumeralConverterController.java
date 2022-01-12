package com.danskebank.numeralsconverter.controller;

import com.danskebank.numeralsconverter.service.NumeralsConverterService;

import lombok.RequiredArgsConstructor;
import org.hibernate.validator.constraints.Range;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.ConstraintViolationException;
import javax.validation.constraints.Pattern;

import static com.danskebank.numeralsconverter.util.Constant.*;
import static com.danskebank.numeralsconverter.util.UrlPath.*;

@Validated
@RestController
@RequestMapping(CONVERT)
@RequiredArgsConstructor
public class NumeralConverterController {

    private final NumeralsConverterService converterService;

    @GetMapping(NUMERAL + "/{value}")
    public ResponseEntity<String> convertNumeral(
            @PathVariable @Pattern(regexp = NUMERAL_PATTERN, message = NUMERAL_EXCEPTION_MESSAGE) String value) {
        return ResponseEntity.ok(converterService.convert(value).toString());
    }

    @GetMapping(NUMBER + "/{value}")
    public ResponseEntity<String> convertDigit(@PathVariable @Range(min = 1, max = 4000) Integer value) {
        return ResponseEntity.ok(converterService.convert(value));
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<String> handleValidationExceptions(ConstraintViolationException ex) {
        var violations = ex.getConstraintViolations();
        var strBuilder = new StringBuilder();
        violations.forEach(violation->{
            strBuilder.append(violation.getMessage());
            strBuilder.append("\n");
        });

        strBuilder.deleteCharAt(strBuilder.lastIndexOf("\n"));

        return ResponseEntity.badRequest().body(strBuilder.toString());
    }
}
