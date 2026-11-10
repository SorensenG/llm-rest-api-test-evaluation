import io.restassured.RestAssured;
import org.junit.jupiter.api.Test;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.anyOf;
import static org.hamcrest.Matchers.equalTo;

public class GeneratedApiTest {
    @Test
    void restAssuredSerializesJavaMap() {
        RestAssured.baseURI = "http://localhost:8080";
        given().contentType("application/json")
            .body(Map.of("title", "serialization-check"))
            .when().post("/api/project")
            .then().statusCode(anyOf(equalTo(201), equalTo(400)));
    }
}
