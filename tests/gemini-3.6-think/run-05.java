import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class GeneratedApiTest {

@BeforeAll
public static void setup() {
    String baseUrl = System.getenv("API_BASE_URL");
    if (baseUrl == null || baseUrl.trim().isEmpty()) {
        baseUrl = System.getProperty("API_BASE_URL", "${API_BASE_URL}");
    }
    if ("${API_BASE_URL}".equals(baseUrl) \vert{}\vert{} baseUrl.startsWith("$")) {
        baseUrl = "http://localhost:8080";
    }
    RestAssured.baseURI = baseUrl;
}

@Test
public void testCreateProject_Success() {
    given()
            .contentType(ContentType.JSON)
            .body("{\"title\": \"Valid Project Title\"}")
    .when()
            .post("/api/project")
    .then()
            .statusCode(201);
}

@Test
public void testCreateProject_InvalidTitle_TooShort() {
    given()
            .contentType(ContentType.JSON)
            .body("{\"title\": \"Tiny\"}")
    .when()
            .post("/api/project")
    .then()
            .statusCode(400);
}

@Test
public void testCreateProject_BoundaryTitle_FourChars_Invalid() {
    given()
            .contentType(ContentType.JSON)
            .body("{\"title\": \"1234\"}")
    .when()
            .post("/api/project")
    .then()
            .statusCode(400);
}

@Test
public void testCreateProject_BoundaryTitle_FiveChars_Valid() {
    given()
            .contentType(ContentType.JSON)
            .body("{\"title\": \"12345\"}")
    .when()
            .post("/api/project")
    .then()
            .statusCode(201);
}

@Test
public void testGetProject_InvalidCode_TooShort() {
    given()
            .pathParam("code", "12345")
    .when()
            .get("/api/project/{code}")
    .then()
            .statusCode(400);
}

@Test
public void testGetProject_NotFound() {
    given()
            .pathParam("code", "NONEXISTENT123")
    .when()
            .get("/api/project/{code}")
    .then()
            .statusCode(404);
}

@Test
public void testUpdateProject_InvalidCode_TooShort() {
    given()
            .pathParam("code", "12345")
            .contentType(ContentType.JSON)
            .body("{\"title\": \"Updated Valid Title\"}")
    .when()
            .put("/api/project/{code}")
    .then()
            .statusCode(400);
}

@Test
public void testUpdateProject_NotFound() {
    given()
            .pathParam("code", "NONEXISTENT123")
            .contentType(ContentType.JSON)
            .body("{\"title\": \"Updated Valid Title\"}")
    .when()
            .put("/api/project/{code}")
    .then()
            .statusCode(404);
}

@Test
public void testDeleteProject_InvalidCode_TooShort() {
    given()
            .pathParam("code", "12345")
    .when()
            .delete("/api/project/{code}")
    .then()
            .statusCode(400);
}

@Test
public void testDeleteProject_NotFound() {
    given()
            .pathParam("code", "NONEXISTENT123")
    .when()
            .delete("/api/project/{code}")
    .then()
            .statusCode(404);
}

@Test
public void testGetProblems_InvalidCode_TooShort() {
    given()
            .pathParam("code", "12345")
    .when()
            .get("/api/problem/{code}")
    .then()
            .statusCode(400);
}

@Test
public void testGetProblems_NotFound() {
    given()
            .pathParam("code", "NONEXISTENT123")
    .when()
            .get("/api/problem/{code}")
    .then()
            .statusCode(404);
}

@Test
public void testCreateProblem_ProjectNotFound() {
    given()
            .pathParam("code", "NONEXISTENT123")
            .contentType(ContentType.JSON)
            .body("{\"title\": \"New Problem Title\"}")
    .when()
            .post("/api/problem/{code}")
    .then()
            .statusCode(404);
}

@Test
public void testCreateProblem_InvalidCode_TooShort() {
    given()
            .pathParam("code", "12345")
            .contentType(ContentType.JSON)
            .body("{\"title\": \"New Problem Title\"}")
    .when()
            .post("/api/problem/{code}")
    .then()
            .statusCode(400);
}

@Test
public void testDeleteProblem_InvalidCode_TooShort() {
    given()
            .pathParam("code", "12345")
    .when()
            .delete("/api/problem/{code}")
    .then()
            .statusCode(400);
}

@Test
public void testDeleteAllProblems_InvalidCode_TooShort() {
    given()
            .pathParam("code", "12345")
    .when()
            .delete("/api/problem/{code}/all")
    .then()
            .statusCode(400);
}

@Test
public void testDeleteAllProblems_NotFound() {
    given()
            .pathParam("code", "NONEXISTENT123")
    .when()
            .delete("/api/problem/{code}/all")
    .then()
            .statusCode(404);
}

@Test
public void testCreateSubproblem_ProblemNotFound() {
    given()
            .contentType(ContentType.JSON)
            .body("{\"content\": \"Valid subproblem content text\", \"pro_idx\": 999999}")
    .when()
            .post("/api/subproblem")
    .then()
            .statusCode(404);
}

@Test
public void testCreateSubproblem_InvalidContent_TooShort() {
    given()
            .contentType(ContentType.JSON)
            .body("{\"content\": \"Short\", \"pro_idx\": 1}")
    .when()
            .post("/api/subproblem")
    .then()
            .statusCode(400);
}

@Test
public void testCreateSubproblem_BoundaryContent_NineChars_Invalid() {
    given()
            .contentType(ContentType.JSON)
            .body("{\"content\": \"123456789\", \"pro_idx\": 1}")
    .when()
            .post("/api/subproblem")
    .then()
            .statusCode(400);
}

@Test
public void testCreateSubproblem_BoundaryContent_TenChars() {
    given()
            .contentType(ContentType.JSON)
            .body("{\"content\": \"1234567890\", \"pro_idx\": 999999}")
    .when()
            .post("/api/subproblem")
    .then()
            .statusCode(404);
}

@Test
public void testMultiEndpoint_CompleteLifecycle() {
    Response projectResponse = given()
            .contentType(ContentType.JSON)
            .body("{\"title\": \"Lifecycle Project Title\"}")
    .when()
            .post("/api/project")
    .then()
            .statusCode(201)
            .extract().response();

    String code = projectResponse.jsonPath().getString("code");
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
                .body("{\"title\": \"Updated Lifecycle Project Title\"}")
        .when()
                .put("/api/project/{code}")
        .then()
                .statusCode(200);

        Response problemResponse = given()
                .pathParam("code", code)
                .contentType(ContentType.JSON)
                .body("{\"title\": \"Lifecycle Problem Title\"}")
        .when()
                .post("/api/problem/{code}")
        .then()
                .statusCode(201)
                .extract().response();

        given()
                .pathParam("code", code)
        .when()
                .get("/api/problem/{code}")
        .then()
                .statusCode(200);

        Long problemIdx = problemResponse.jsonPath().getLong("idx");
        if (problemIdx != null && problemIdx > 0) {
            given()
                    .contentType(ContentType.JSON)
                    .body("{\"content\": \"Valid subproblem content length\", \"pro_idx\": " + problemIdx + "}")
            .when()
                    .post("/api/subproblem")
            .then()
                    .statusCode(201);
        }

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