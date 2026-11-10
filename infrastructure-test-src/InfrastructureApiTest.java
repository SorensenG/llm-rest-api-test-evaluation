import io.restassured.RestAssured;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

class InfrastructureApiTest {
    @Test
    void apiRespondsWithNotFoundForUnknownProject() {
        RestAssured.baseURI = System.getenv().getOrDefault("API_BASE_URL", "http://127.0.0.1:8080");
        given().when().get("/api/project/abcdef").then().statusCode(equalTo(404));
    }
}
