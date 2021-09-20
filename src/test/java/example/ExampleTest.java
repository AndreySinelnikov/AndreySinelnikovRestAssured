package example;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class ExampleTest {

    @Test
    public void test_1() {
        Response response = RestAssured.get("https://reqres.in/api/users/2");
        assertThat(response.getStatusCode())
            .isEqualTo(200);
    }
}
