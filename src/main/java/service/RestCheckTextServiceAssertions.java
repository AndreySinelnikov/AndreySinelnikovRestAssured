package service;

import dto.SpellingErrorDto;

import static org.assertj.core.api.Assertions.assertThat;

public class RestCheckTextServiceAssertions {

    private SpellingErrorDto[] spellingErrors;

    public RestCheckTextServiceAssertions(SpellingErrorDto[] response) {
        this.spellingErrors = response;
    }

    public RestCheckTextServiceAssertions verifySpellingErrorsQuantity(int expectedNumber) {
        assertThat(spellingErrors.length)
            .as("There should be an expected number of caught spelling errors in the response")
            .isEqualTo(expectedNumber);

        return this;
    }
}
