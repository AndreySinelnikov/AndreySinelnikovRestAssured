package speller;

import static io.restassured.RestAssured.given;

import dto.SpellingErrorDto;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import service.RestCheckTextService;
import service.RestCheckTextServiceAssertions;


public class CheckTextTests extends BaseTest {


    @Test(description = "Entered text returns expected number of suggestions", dataProvider = "suggestionNumberCheck")
    public void enteredTextReturnsExpectedNumberOfSuggestions(String testId, String text, int expectedNumberOfErrors) {

        SpellingErrorDto[] spellingErrors = new RestCheckTextService().checkSingleText(text);

        new RestCheckTextServiceAssertions(spellingErrors)
            .verifySpellingErrorsQuantity(expectedNumberOfErrors);
    }

    @DataProvider(name = "suggestionNumberCheck")
    public Object[][] enteredTextReturnsExpectedNumberOfSuggestionsData() {
        return new Object[][] {
            {"0 SPELLING ERRORS", "Black snake living in a black hole, hiding from the sun", 0},
            {"1 SPELLING ERROR", "Black snake living in a black hole ttil the game is won", 1},
        };
    }
}

