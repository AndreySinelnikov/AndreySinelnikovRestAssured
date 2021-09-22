package speller;

import static speller.ErrorCodes.ERROR_UNKNOWN_WORD;

import dto.SpellingErrorDto;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import service.RestCheckTextService;
import service.RestCheckTextServiceAssertions;
import service.SpellerOptions;

public class CheckTextTests extends BaseTest {

    @DataProvider(name = "spellingErrorNumberCheck")
    public Object[][] sentTextReturnsExpectedNumberOfSpellingErrorsData() {
        return new Object[][] {
            {"0 SPELLING ERRORS", "Black snake living in a black hole, hiding from the sun", 0},
            {"1 SPELLING ERROR", "Black snake living in a black hole ttil the game is won", 1},
        };
    }

    @Test(description = "Sent text returns expected number of spelling errors",
          dataProvider = "spellingErrorNumberCheck")
    public void sentTextReturnsExpectedNumberOfSpellingErrors(String testId,
                                                              String text, int expectedNumberOfErrors) {
        SpellingErrorDto[] spellingErrors = new RestCheckTextService().checkSingleText(text);

        new RestCheckTextServiceAssertions(spellingErrors)
            .spellingErrorQuantityIsEqualTo(expectedNumberOfErrors);
    }

    // Postman requests show ERROR_UNKNOWN_WORD is pretty much the only error that actually shows up
    @Test(description = "Sent text returns single spelling error with an expected error code")
    public void sentTextReturnsSpellingErrorWithExpectedErrorCode() {
        String text = "каракозябра";
        int expectedErrorCode = ERROR_UNKNOWN_WORD;

        SpellingErrorDto[] spellingErrors = new RestCheckTextService().checkSingleText(text);

        new RestCheckTextServiceAssertions(spellingErrors)
            .spellingErrorQuantityIsEqualTo(1)
            .spellingErrorCodeIsEqualTo(expectedErrorCode);
    }

    @Test(description = "Ignore digits options excludes words mixed with digits from spellcheck")
    public void wordMixedWithIgnoredDigitsReturnsNoSpellingErrors() {
        String text = "aberr3tion";
        int options = new SpellerOptions()
            .setIgnoreDigits()
            .getOptionsCode();

        SpellingErrorDto[] spellingErrors = new RestCheckTextService().checkSingleTextWithOptions(text,
            options);

        new RestCheckTextServiceAssertions(spellingErrors)
            .spellingErrorQuantityIsEqualTo(0);
    }

    @Test(description = "Ignore digits and ignore URLs options combined"
        + "exclude both URLs and words mixed with digits from spellcheck")
    public void textWithUrlsAndMixedDigitWordsReturnsNoSpellingErrorsWithProperOptions() {
        String text = "D3ar fri3nd, reach me at https://reques.cem";
        int options = new SpellerOptions()
            .setIgnoreDigits()
            .setIgnoreUrls()
            .getOptionsCode();

        SpellingErrorDto[] spellingErrors = new RestCheckTextService().checkSingleTextWithOptions(text,
            options);

        new RestCheckTextServiceAssertions(spellingErrors)
            .spellingErrorQuantityIsEqualTo(0);
    }

    @Test(description = "One of the errors returned from sending incorrectly spelled text "
        + "contains specified spelling suggestion")
    public void responseWithMultipleErrorsAndMultipleSuggestionsContainsExpectedSuggestion() {
        String text = "I tiped al thes louse textt and all i got was eror sugestions";
        String expectedSuggestion = "the";

        SpellingErrorDto[] spellingErrors = new RestCheckTextService().checkSingleText(text);

        new RestCheckTextServiceAssertions(spellingErrors)
            .containsSuggestedCorrectSpelling(expectedSuggestion);
    }

}

