package assertions;

import dto.SpellingErrorDto;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

public class RestCheckTextServiceAssertions {
    private SpellingErrorDto[] spellingErrors;

    public RestCheckTextServiceAssertions(SpellingErrorDto[] response) {
        this.spellingErrors = response;
    }

    public RestCheckTextServiceAssertions spellingErrorQuantityIsEqualTo(int expectedNumber) {
        assertThat(spellingErrors.length)
            .as("There should be an expected number of caught spelling errors in the response")
            .isEqualTo(expectedNumber);

        return this;
    }

    // chain after spellingErrorQuantityIsEqualTo(1)
    public RestCheckTextServiceAssertions spellingErrorCodeIsEqualTo(int errorCode) {
        assertThat(spellingErrors[0].getCode())
            .as("Error code should be equal to expected")
            .isEqualTo(errorCode);

        return this;
    }

    public RestCheckTextServiceAssertions containsSuggestedCorrectSpelling(String suggestion) {
        Stream<SpellingErrorDto> errorDtoStream = Stream.of(spellingErrors);
        List<String> suggestions = errorDtoStream
            .map(SpellingErrorDto::getS)  // gets a list of suggestion lists
            .flatMap(Collection::stream)  // flattens it into a single list
            .collect(Collectors.toList());

        assertThat(suggestions)
            .as("Response should contain an expected correct spelling suggestion")
            .contains(suggestion);

        return this;
    }
}
