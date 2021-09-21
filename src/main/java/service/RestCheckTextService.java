package service;

import static service.URI.CHECK_TEXT_URI;

import com.google.gson.Gson;
import dto.SuggestionDto;
import java.util.HashMap;
import java.util.Map;

public class RestCheckTextService extends CommonService {

    private RestCheckTextService restCheckTextService;

    public RestCheckTextService getInstance() {
        if (restCheckTextService == null)
            restCheckTextService = new RestCheckTextService();

        return restCheckTextService;
    }

    public SuggestionDto[] checkSingleText(String text) {
        Map<String, Object> params = new HashMap<>();
        params.put("text", text);

        return
            new Gson().fromJson(
                new CommonService()
                    .getWithParams(CHECK_TEXT_URI, params)
                    .getBody().asString(), SuggestionDto[].class);
    }
}
