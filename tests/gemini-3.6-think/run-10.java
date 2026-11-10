import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

public class GeneratedApiTest {

@BeforeAll
public static void setup() {
    String baseUrl = System.getenv("API_BASE_URL");
    if (baseUrl == null || baseUrl.isBlank()) {
        baseUrl = System.getProperty("API_BASE_URL", "http://localhost:8080");
    }
    RestAssured.baseURI = baseUrl;
}

@Test
public void testCreateProjectSuccess() {
    given()
        .contentType(ContentType.JSON)
        .body("{\"title\": \"Valid Project Title\"}")
    .when()
        .post("/api/project")
    .then()
        .statusCode(201);
}

@Test
public void testCreateProjectInvalidTitleBoundary() {
    given()
        .contentType(ContentType.JSON)
        .body("{\"title\": \"Test\"}")
    .when()
        .post("/api/project")
    .then()
        .statusCode(400);
}

@Test
public void testGetProjectInvalidCodeBoundary() {
    given()
        .pathParam("code", "12345")
    .when()
        .get("/api/project/{code}")
    .then()
        .statusCode(400);
}

@Test
public void testGetProjectNotFound() {
    given()
        .pathParam("code", "NONEXISTENT123")
    .when()
        .get("/api/project/{code}")
    .then()
        .statusCode(404);
}

@Test
public void testUpdateProjectInvalidCodeBoundary() {
    given()
        .pathParam("code", "12345")
        .contentType(ContentType.JSON)
        .body("{\"title\": \"Updated Title\"}")
    .when()
        .put("/api/project/{code}")
    .then()
        .statusCode(400);
}

@Test
public void testUpdateProjectNotFound() {
    given()
        .pathParam("code", "NONEXISTENT123")
        .contentType(ContentType.JSON)
        .body("{\"title\": \"Updated Title\"}")
    .when()
        .put("/api/project/{code}")
    .then()
        .statusCode(404);
}

@Test
public void testDeleteProjectInvalidCodeBoundary() {
    given()
        .pathParam("code", "12345")
    .when()
        .delete("/api/project/{code}")
    .then()
        .statusCode(400);
}

@Test
public void testDeleteProjectNotFound() {
    given()
        .pathParam("code", "NONEXISTENT123")
    .when()
        .delete("/api/project/{code}")
    .then()
        .statusCode(404);
}

@Test
public void testGetProblemsInvalidCodeBoundary() {
    given()
        .pathParam("code", "12345")
    .when()
        .get("/api/problem/{code}")
    .then()
        .statusCode(400);
}

@Test
public void testGetProblemsNotFound() {
    given()
        .pathParam("code", "NONEXISTENT123")
    .when()
        .get("/api/problem/{code}")
    .then()
        .statusCode(404);
}

@Test
public void testCreateProblemInvalidCodeBoundary() {
    given()
        .pathParam("code", "12345")
        .contentType(ContentType.JSON)
        .body("{\"title\": \"Problem Title\"}")
    .when()
        .post("/api/problem/{code}")
    .then()
        .statusCode(400);
}

@Test
public void testCreateProblemProjectNotFound() {
    given()
        .pathParam("code", "NONEXISTENT123")
        .contentType(ContentType.JSON)
        .body("{\"title\": \"Problem Title\"}")
    .when()
        .post("/api/problem/{code}")
    .then()
        .statusCode(404);
}

@Test
public void testDeleteProblemsInvalidCodeBoundary() {
    given()
        .pathParam("code", "12345")
    .when()
        .delete("/api/problem/{code}")
    .then()
        .statusCode(400);
}

@Test
public void testDeleteAllProblemsInvalidCodeBoundary() {
    given()
        .pathParam("code", "12345")
    .when()
        .delete("/api/problem/{code}/all")
    .then()
        .statusCode(400);
}

@Test
public void testDeleteAllProblemsNotFound() {
    given()
        .pathParam("code", "NONEXISTENT123")
    .when()
        .delete("/api/problem/{code}/all")
    .then()
        .statusCode(404);
}

@Test
public void testCreateSubProblemInvalidContentBoundary() {
    given()
        .contentType(ContentType.JSON)
        .body("{\"content\": \"123456789\", \"pro_idx\": 1}")
    .when()
        .post("/api/subproblem")
    .then()
        .statusCode(400);
}

@Test
public void testCreateSubProblemNotFound() {
    given()
        .contentType(ContentType.JSON)
        .body("{\"content\": \"Valid content with length greater than nine\", \"pro_idx\": 9999999}")
    .when()
        .post("/api/subproblem")
    .then()
        .statusCode(404);
}

@Test
public void testProjectLifecycle() {
    Response createResponse = given()
        .contentType(ContentType.JSON)
        .body("{\"title\": \"New Project Title\"}")
    .when()
        .post("/api/project")
    .then()
        .statusCode(201)
        .extract().response();

    String code = createResponse.jsonPath().getString("code");
    if (code != null && !code.isEmpty()) {
        given()
            .pathParam("code", code)
        .when()
            .get("/api/project/{code}")
        .then()
            .statusCode(200);

        given()
            .pathParam("code", code)
            .contentType(ContentType.JSON)
            .body("{\"title\": \"Updated Project Title\"}")
        .when()
            .put("/api/project/{code}")
        .then()
            .statusCode(200);

        given()
            .pathParam("code", code)
        .when()
            .delete("/api/project/{code}")
        .then()
            .statusCode(200);

        given()
            .pathParam("code", code)
        .when()
            .get("/api/project/{code}")
        .then()
            .statusCode(404);
    }
}

@Test
public void testProblemAndSubProblemLifecycle() {
    Response projectResponse = given()
        .contentType(ContentType.JSON)
        .body("{\"title\": \"Project For Problem Test\"}")
    .when()
        .post("/api/project")
    .then()
        .statusCode(201)
        .extract().response();

    String code = projectResponse.jsonPath().getString("code");
    if (code != null && !code.isEmpty()) {
        given()
            .pathParam("code", code)
            .contentType(ContentType.JSON)
            .body("{\"title\": \"Problem One\"}")
        .when()
            .post("/api/problem/{code}")
        .then()
            .statusCode(201);

        Response getProblemsResponse = given()
            .pathParam("code", code)
        .when()
            .get("/api/problem/{code}")
        .then()
            .statusCode(200)
            .extract().response();

        Long proIdx = getProblemsResponse.jsonPath().getLong("[0].idx");
        if (proIdx != null && proIdx > 0) {
            given()
                .contentType(ContentType.JSON)
                .body("{\"content\": \"Valid subproblem content greater than nine\", \"pro_idx\": " + proIdx + "}")
            .when()
                .post("/api/subproblem")
            .then()
                .statusCode(201);
        }

        given()
            .pathParam("code", code)
        .when()
            .delete("/api/problem/{code}")
        .then()
            .statusCode(200);

        given()
            .pathParam("code", code)
        .when()
            .delete("/api/project/{code}")
        .then()
            .statusCode(200);
    }
}

@Test
public void testDeleteAllProblemsAndSubProblemsLifecycle() {
    Response projectResponse = given()
        .contentType(ContentType.JSON)
        .body("{\"title\": \"Project For Delete All Test\"}")
    .when()
        .post("/api/project")
    .then()
        .statusCode(201)
        .extract().response();

    String code = projectResponse.jsonPath().getString("code");
    if (code != null && !code.isEmpty()) {
        given()
            .pathParam("code", code)
            .contentType(ContentType.JSON)
            .body("{\"title\": \"Problem To Delete All\"}")
        .when()
            .post("/api/problem/{code}")
        .then()
            .statusCode(201);

        given()
            .pathParam("code", code)
        .when()
            .delete("/api/problem/{code}/all")
        .then()
            .statusCode(200);

        given()
            .pathParam("code", code)
        .when()
            .delete("/api/project/{code}")
        .then()
            .statusCode(200);
    }
}

}