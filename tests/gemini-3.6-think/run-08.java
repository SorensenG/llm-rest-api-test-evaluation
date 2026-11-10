import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class GeneratedApiTest {

@BeforeAll
public static void setup() {
    String baseUrl = System.getenv("API_BASE_URL");
    if (baseUrl == null || baseUrl.trim().isEmpty()) {
        baseUrl = System.getProperty("API_BASE_URL", "http://localhost:8080");
    }
    RestAssured.baseURI = baseUrl;
}

// --- PROJECT ENDPOINT TESTS ---

@Test
public void testCreateProjectSuccessBoundaryTitleLengthFive() {
    Map<String, Object> body = new HashMap<>();
    body.put("title", "12345");

    given()
        .contentType(ContentType.JSON)
        .body(body)
    .when()
        .post("/api/project")
    .then()
        .statusCode(201);
}

@Test
public void testCreateProjectInvalidTitleTooShortLengthFour() {
    Map<String, Object> body = new HashMap<>();
    body.put("title", "1234");

    given()
        .contentType(ContentType.JSON)
        .body(body)
    .when()
        .post("/api/project")
    .then()
        .statusCode(400);
}

@Test
public void testCreateProjectMissingTitle() {
    Map<String, Object> body = new HashMap<>();

    given()
        .contentType(ContentType.JSON)
        .body(body)
    .when()
        .post("/api/project")
    .then()
        .statusCode(400);
}

@Test
public void testGetProjectInvalidCodeTooShortLengthFive() {
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
public void testUpdateProjectInvalidCodeTooShortLengthFive() {
    Map<String, Object> body = new HashMap<>();
    body.put("title", "Valid Project Title");

    given()
        .contentType(ContentType.JSON)
        .pathParam("code", "12345")
        .body(body)
    .when()
        .put("/api/project/{code}")
    .then()
        .statusCode(400);
}

@Test
public void testUpdateProjectNotFound() {
    Map<String, Object> body = new HashMap<>();
    body.put("title", "Valid Project Title");

    given()
        .contentType(ContentType.JSON)
        .pathParam("code", "NONEXISTENT123")
        .body(body)
    .when()
        .put("/api/project/{code}")
    .then()
        .statusCode(404);
}

@Test
public void testDeleteProjectInvalidCodeTooShortLengthFive() {
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

// --- PROBLEM ENDPOINT TESTS ---

@Test
public void testGetProblemsInvalidCodeTooShortLengthFive() {
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
public void testCreateProblemInvalidCodeTooShortLengthFive() {
    Map<String, Object> body = new HashMap<>();
    body.put("title", "Problem Title");

    given()
        .contentType(ContentType.JSON)
        .pathParam("code", "12345")
        .body(body)
    .when()
        .post("/api/problem/{code}")
    .then()
        .statusCode(400);
}

@Test
public void testCreateProblemProjectNotFound() {
    Map<String, Object> body = new HashMap<>();
    body.put("title", "Problem Title");

    given()
        .contentType(ContentType.JSON)
        .pathParam("code", "NONEXISTENT123")
        .body(body)
    .when()
        .post("/api/problem/{code}")
    .then()
        .statusCode(404);
}

@Test
public void testDeleteProblemsInvalidCodeTooShortLengthFive() {
    given()
        .pathParam("code", "12345")
    .when()
        .delete("/api/problem/{code}")
    .then()
        .statusCode(400);
}

@Test
public void testDeleteAllProblemsAndSubproblemsInvalidCodeTooShortLengthFive() {
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

// --- SUBPROBLEM ENDPOINT TESTS ---

@Test
public void testCreateSubProblemInvalidContentTooShortLengthNine() {
    Map<String, Object> body = new HashMap<>();
    body.put("content", "123456789");
    body.put("pro_idx", 1L);

    given()
        .contentType(ContentType.JSON)
        .body(body)
    .when()
        .post("/api/subproblem")
    .then()
        .statusCode(400);
}

@Test
public void testCreateSubProblemMissingContent() {
    Map<String, Object> body = new HashMap<>();
    body.put("pro_idx", 1L);

    given()
        .contentType(ContentType.JSON)
        .body(body)
    .when()
        .post("/api/subproblem")
    .then()
        .statusCode(400);
}

@Test
public void testCreateSubProblemMissingProIdx() {
    Map<String, Object> body = new HashMap<>();
    body.put("content", "Content longer than nine characters");

    given()
        .contentType(ContentType.JSON)
        .body(body)
    .when()
        .post("/api/subproblem")
    .then()
        .statusCode(400);
}

@Test
public void testCreateSubProblemProblemNotFound() {
    Map<String, Object> body = new HashMap<>();
    body.put("content", "Content longer than nine characters");
    body.put("pro_idx", 999999999L);

    given()
        .contentType(ContentType.JSON)
        .body(body)
    .when()
        .post("/api/subproblem")
    .then()
        .statusCode(404);
}

// --- MULTI-ENDPOINT LIFECYCLE TEST ---

@Test
public void testProjectProblemSubproblemFullLifecycle() {
    Map<String, Object> projectInput = new HashMap<>();
    projectInput.put("title", "Integration Test Project");

    Response createProjectResp = given()
        .contentType(ContentType.JSON)
        .body(projectInput)
    .when()
        .post("/api/project");

    createProjectResp.then().statusCode(201);

    String code = createProjectResp.jsonPath().getString("code");
    if (code != null && !code.isEmpty()) {
        given()
            .pathParam("code", code)
        .when()
            .get("/api/project/{code}")
        .then()
            .statusCode(200)
            .body("title", equalTo("Integration Test Project"));

        Map<String, Object> updateInput = new HashMap<>();
        updateInput.put("title", "Updated Project Title");

        given()
            .contentType(ContentType.JSON)
            .pathParam("code", code)
            .body(updateInput)
        .when()
            .put("/api/project/{code}")
        .then()
            .statusCode(200);

        Map<String, Object> problemInput = new HashMap<>();
        problemInput.put("title", "Lifecycle Problem");

        given()
            .contentType(ContentType.JSON)
            .pathParam("code", code)
            .body(problemInput)
        .when()
            .post("/api/problem/{code}")
        .then()
            .statusCode(201);

        Response getProblemsResp = given()
            .pathParam("code", code)
        .when()
            .get("/api/problem/{code}");

        getProblemsResp.then().statusCode(200);

        Long problemIdx = getProblemsResp.jsonPath().getLong("[0].idx");
        if (problemIdx != null && problemIdx != 0L) {
            Map<String, Object> subProblemInput = new HashMap<>();
            subProblemInput.put("content", "Valid content longer than 9 characters");
            subProblemInput.put("pro_idx", problemIdx);

            given()
                .contentType(ContentType.JSON)
                .body(subProblemInput)
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
            .delete("/api/problem/{code}/all")
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

}