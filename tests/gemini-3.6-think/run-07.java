import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class GeneratedApiTest {

@BeforeAll
public static void setup() {
    String baseUrl = System.getenv("API_BASE_URL");
    if (baseUrl == null || baseUrl.trim().isEmpty()) {
        baseUrl = System.getProperty("API_BASE_URL", "http://localhost:8080");
    }
    RestAssured.baseURI = baseUrl;
}

@Test
public void testCreateProjectSuccess() {
    given()
        .contentType(ContentType.JSON)
        .body(Map.of("title", "Valid Project Title"))
    .when()
        .post("/api/project")
    .then()
        .statusCode(201);
}

@Test
public void testCreateProjectInvalidTitleTooShort() {
    given()
        .contentType(ContentType.JSON)
        .body(Map.of("title", "1234"))
    .when()
        .post("/api/project")
    .then()
        .statusCode(400);
}

@Test
public void testCreateProjectInvalidTitleEmpty() {
    given()
        .contentType(ContentType.JSON)
        .body(Map.of("title", ""))
    .when()
        .post("/api/project")
    .then()
        .statusCode(400);
}

@Test
public void testGetProjectInvalidCode() {
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
public void testProjectLifecycleCreateGetUpdateDelete() {
    Response createResponse = given()
        .contentType(ContentType.JSON)
        .body(Map.of("title", "Lifecycle Project"))
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
            .statusCode(200)
            .body("title", equalTo("Lifecycle Project"));

        given()
            .pathParam("code", code)
            .contentType(ContentType.JSON)
            .body(Map.of("title", "Updated Lifecycle Project"))
        .when()
            .put("/api/project/{code}")
        .then()
            .statusCode(200);

        given()
            .pathParam("code", code)
        .when()
            .get("/api/project/{code}")
        .then()
            .statusCode(200)
            .body("title", equalTo("Updated Lifecycle Project"));

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
public void testUpdateProjectInvalidCode() {
    given()
        .pathParam("code", "12345")
        .contentType(ContentType.JSON)
        .body(Map.of("title", "Valid Title"))
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
        .body(Map.of("title", "Valid Title"))
    .when()
        .put("/api/project/{code}")
    .then()
        .statusCode(404);
}

@Test
public void testDeleteProjectInvalidCode() {
    given()
        .pathParam("code", "123")
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
public void testCreateAndGetProblemSuccess() {
    Response createProjectResp = given()
        .contentType(ContentType.JSON)
        .body(Map.of("title", "Project for Problem Test"))
    .when()
        .post("/api/project")
    .then()
        .statusCode(201)
        .extract().response();

    String code = createProjectResp.jsonPath().getString("code");
    if (code != null && !code.isEmpty()) {
        given()
            .pathParam("code", code)
            .contentType(ContentType.JSON)
            .body(Map.of("title", "New Problem Title"))
        .when()
            .post("/api/problem/{code}")
        .then()
            .statusCode(201);

        given()
            .pathParam("code", code)
        .when()
            .get("/api/problem/{code}")
        .then()
            .statusCode(200)
            .body("$", isA(java.util.List.class));
    }
}

@Test
public void testCreateProblemInvalidCode() {
    given()
        .pathParam("code", "12345")
        .contentType(ContentType.JSON)
        .body(Map.of("title", "Problem Title"))
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
        .body(Map.of("title", "Problem Title"))
    .when()
        .post("/api/problem/{code}")
    .then()
        .statusCode(404);
}

@Test
public void testGetProblemsInvalidCode() {
    given()
        .pathParam("code", "12345")
    .when()
        .get("/api/problem/{code}")
    .then()
        .statusCode(400);
}

@Test
public void testGetProblemsProjectNotFound() {
    given()
        .pathParam("code", "NONEXISTENT123")
    .when()
        .get("/api/problem/{code}")
    .then()
        .statusCode(404);
}

@Test
public void testDeleteProblemsForCodeSuccess() {
    Response createProjectResp = given()
        .contentType(ContentType.JSON)
        .body(Map.of("title", "Project for Delete Problems"))
    .when()
        .post("/api/project")
    .then()
        .statusCode(201)
        .extract().response();

    String code = createProjectResp.jsonPath().getString("code");
    if (code != null && !code.isEmpty()) {
        given()
            .pathParam("code", code)
        .when()
            .delete("/api/problem/{code}")
        .then()
            .statusCode(200);
    }
}

@Test
public void testDeleteProblemsForCodeInvalidCode() {
    given()
        .pathParam("code", "12345")
    .when()
        .delete("/api/problem/{code}")
    .then()
        .statusCode(400);
}

@Test
public void testDeleteAllProblemsAndSubproblemsSuccess() {
    Response createProjectResp = given()
        .contentType(ContentType.JSON)
        .body(Map.of("title", "Project for Delete All"))
    .when()
        .post("/api/project")
    .then()
        .statusCode(201)
        .extract().response();

    String code = createProjectResp.jsonPath().getString("code");
    if (code != null && !code.isEmpty()) {
        given()
            .pathParam("code", code)
        .when()
            .delete("/api/problem/{code}/all")
        .then()
            .statusCode(200);
    }
}

@Test
public void testDeleteAllProblemsAndSubproblemsInvalidCode() {
    given()
        .pathParam("code", "12345")
    .when()
        .delete("/api/problem/{code}/all")
    .then()
        .statusCode(400);
}

@Test
public void testDeleteAllProblemsAndSubproblemsNotFound() {
    given()
        .pathParam("code", "NONEXISTENT123")
    .when()
        .delete("/api/problem/{code}/all")
    .then()
        .statusCode(404);
}

@Test
public void testCreateSubproblemSuccess() {
    Response createProjectResp = given()
        .contentType(ContentType.JSON)
        .body(Map.of("title", "Project for Subproblem Test"))
    .when()
        .post("/api/project")
    .then()
        .statusCode(201)
        .extract().response();

    String code = createProjectResp.jsonPath().getString("code");
    if (code != null && !code.isEmpty()) {
        Response createProbResp = given()
            .pathParam("code", code)
            .contentType(ContentType.JSON)
            .body(Map.of("title", "Problem for Subproblem"))
        .when()
            .post("/api/problem/{code}")
        .then()
            .statusCode(201)
            .extract().response();

        Long probIdx = createProbResp.jsonPath().getLong("idx");
        if (probIdx == null || probIdx == 0) {
            probIdx = 1L;
        }

        given()
            .contentType(ContentType.JSON)
            .body(Map.of("content", "Valid content longer than 9 characters", "pro_idx", probIdx))
        .when()
            .post("/api/subproblem")
        .then()
            .statusCode(201);
    }
}

@Test
public void testCreateSubproblemInvalidContentLengthNine() {
    given()
        .contentType(ContentType.JSON)
        .body(Map.of("content", "123456789", "pro_idx", 1L))
    .when()
        .post("/api/subproblem")
    .then()
        .statusCode(400);
}

@Test
public void testCreateSubproblemProblemNotFound() {
    given()
        .contentType(ContentType.JSON)
        .body(Map.of("content", "Valid content longer than 9 characters", "pro_idx", 999999999L))
    .when()
        .post("/api/subproblem")
    .then()
        .statusCode(404);
}

@Test
public void testCreateSubproblemMissingContent() {
    given()
        .contentType(ContentType.JSON)
        .body(Map.of("pro_idx", 1L))
    .when()
        .post("/api/subproblem")
    .then()
        .statusCode(400);
}

@Test
public void testCreateSubproblemMissingProIdx() {
    given()
        .contentType(ContentType.JSON)
        .body(Map.of("content", "Valid content longer than 9 characters"))
    .when()
        .post("/api/subproblem")
    .then()
        .statusCode(400);
}

}