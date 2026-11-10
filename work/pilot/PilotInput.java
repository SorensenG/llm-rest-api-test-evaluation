import io.restassured.RestAssured;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class GeneratedApiTest {
    @Test
    void pilotCreatesProjectUsingObservedStatus() {
        RestAssured.baseURI = System.getenv().getOrDefault("API_BASE_URL", "http://localhost:8080");
        given().contentType("application/json").body("{\"title\":\"pilot validation project\"}")
            .when().post("/api/project").then().statusCode(equalTo(201));
    }

    @Test
    void pilotReadsUnknownProject() {
        RestAssured.baseURI = System.getenv().getOrDefault("API_BASE_URL", "http://localhost:8080");
        given().when().get("/api/project/zzzzzz").then().statusCode(equalTo(404));
    }
}
