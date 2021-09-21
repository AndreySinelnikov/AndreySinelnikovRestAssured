package speller;

import static io.restassured.RestAssured.given;

import dto.SuggestionDto;
import io.restassured.response.Response;
import java.util.HashMap;
import java.util.Map;
import org.testng.annotations.Test;
import service.CommonService;

import static org.assertj.core.api.Assertions.assertThat;
import static service.URI.CHECK_TEXT_URI;

public class CheckTextTests extends BaseTest {

    @Test
    public void test() {

        //ErrorDto error = restCheckTextService.checkSingleText("blek");
        SuggestionDto[] suggestion = restCheckTextService.checkSingleText("black");
        SuggestionDto[] suggestion2 = restCheckTextService.checkSingleText("abberation");
        //System.out.println(suggestion[0].getS());


    }

    //    @Test
    //    public void singleIncorrectEnglishWord_ReturnsSingleSuggestion() {
    //
    //    }
}
