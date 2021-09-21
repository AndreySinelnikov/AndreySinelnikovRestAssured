package example;

import io.restassured.response.Response;
import org.testng.annotations.Test;

// 3 recommended static imports for RA tests
import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import static org.assertj.core.api.Assertions.assertThat;

public class ExampleTest {

    @Test
    public void test_1() {
        baseURI = "https://reqres.in/api";  // gets prepended to requests automatically
        given().get("/users?page=2")
               .then().statusCode(200) // next check chains after this
               .body("data[3].first_name", equalTo("Byron")) // matcher
               .log().all();  // logs response data to console

    }
}
