package service;

import static io.restassured.RestAssured.given;
import static service.URI.BASE_URI;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import java.util.Map;
import org.hamcrest.Matchers;
import org.apache.http.HttpStatus;

public class CommonService {
    private RequestSpecification REQUEST_SPECIFICATION;

    public CommonService() {
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
        REQUEST_SPECIFICATION = new RequestSpecBuilder()
            .setBaseUri(BASE_URI)
            .build();
    }

    public Response getWithParams(String uri, Map<String, Object> paramMap) {
        RequestSpecification specification = given(REQUEST_SPECIFICATION);
        specification.params(paramMap);
        specification.then()
                     .statusCode(Matchers.lessThan(300))
                     .statusCode(Matchers.greaterThanOrEqualTo(200));

        return specification.get(uri);
    }

    // TODO Post without params (for alternate checkText method)? investigate
}
