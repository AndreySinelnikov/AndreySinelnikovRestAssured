package service;

import static io.restassured.RestAssured.given;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import java.util.Map;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hamcrest.Matchers;

@NoArgsConstructor
public class CommonService {
    private RequestSpecification REQUEST_SPECIFICATION;
    @Getter
    protected String baseUri;

    public CommonService(String baseUri) {
        this.baseUri = baseUri;
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
        REQUEST_SPECIFICATION = new RequestSpecBuilder()
            .setBaseUri(baseUri)
            .build();
    }

    public Response getWithParams(String uri, Map<String, Object> requestParameters) {
        RequestSpecification specification = given(REQUEST_SPECIFICATION);
        specification.params(requestParameters);
        specification.then()
                     .statusCode(Matchers.lessThan(300))
                     .statusCode(Matchers.greaterThanOrEqualTo(200));

        return specification.get(uri);
    }
}
