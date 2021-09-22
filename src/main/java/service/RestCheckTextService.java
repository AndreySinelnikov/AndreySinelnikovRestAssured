package service;

import static service.URI.CHECK_TEXT_URI;

import com.google.gson.Gson;
import dto.SpellingErrorDto;
import java.util.HashMap;
import java.util.Map;

public class RestCheckTextService extends CommonService {

    private RestCheckTextService restCheckTextService;

    public RestCheckTextService getInstance() {
        if (restCheckTextService == null)
            restCheckTextService = new RestCheckTextService();

        return restCheckTextService;
    }

    public SpellingErrorDto[] checkSingleText(String text) {
        Map<String, Object> params = new HashMap<>();
        params.put("text", text);

        return getResponseBodyAsSpellingErrorDtoArray(params);
    }

    // Postman requests show 'lang' parameter doesn't actually affect anything, so Yandex Speller options only
    public SpellingErrorDto[] checkSingleTextWithOptions(String text, int optionsCode) {
        Map<String, Object> params = new HashMap<>();
        params.put("text", text);
        params.put("options", optionsCode);

        return getResponseBodyAsSpellingErrorDtoArray(params);
    }

    private static SpellingErrorDto[] getResponseBodyAsSpellingErrorDtoArray(Map<String, Object> requestParameters) {
        return
            new Gson().fromJson(
                new CommonService()
                    .getWithParams(CHECK_TEXT_URI, requestParameters)
                    .getBody().asString(), SpellingErrorDto[].class);
    }
}
