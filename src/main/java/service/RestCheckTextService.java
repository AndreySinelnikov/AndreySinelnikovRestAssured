package service;

import com.google.gson.Gson;
import dto.SpellingErrorDto;
import java.util.HashMap;
import java.util.Map;
import lombok.Getter;

public class RestCheckTextService extends CommonService {
    @Getter
    private String checkTextUri;

    public RestCheckTextService(String baseUri, String checkTextUri) {
        this.baseUri = baseUri;
        this.checkTextUri = checkTextUri;
    }

    // Postman requests show 'lang' parameter doesn't actually affect anything, so Yandex Speller options only
    public SpellingErrorDto[] checkSingleText(String text, int optionsCode) {
        Map<String, Object> requestParameters = new HashMap<>();
        requestParameters.put("text", text);
        requestParameters.put("options", optionsCode);

        return new Gson().fromJson(
            new CommonService(getBaseUri())
                .getWithParams(getCheckTextUri(), requestParameters)
                .getBody().asString(), SpellingErrorDto[].class);
    }
}
