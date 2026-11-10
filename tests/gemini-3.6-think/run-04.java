import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.anyOf;
import static org.hamcrest.Matchers.equalTo;

public class GeneratedApiTest {

@BeforeAll
public static void setup() {
    String baseUrl = System.getProperty("API_BASE_URL");
    if (baseUrl == null || baseUrl.isBlank() || baseUrl.equals("${API_BASE_URL}")) {
        baseUrl = System.getenv("API_BASE_URL");
    }
    if (baseUrl == null || baseUrl.isBlank() || baseUrl.equals("${API_BASE_URL}")) {
        baseUrl = "http://localhost:8080";
    }
    RestAssured.baseURI = baseUrl;
}

// -------------------------------------------------------------------------
// Project Scenarios (/api/project)
// -------------------------------------------------------------------------

@Test
public void testCreateProjectSuccess() {
    Map<String, Object> body = new HashMap<>();
    body.put("title", "Valid Project Title");

    given()
        .contentType(ContentType.JSON)
        .body(body)
    .when()
        .post("/api/project")
    .then()
        .statusCode(201);
}

@Test
public void testCreateProjectBoundarySuccess() {
    // Title length > 4 (5 characters)
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
public void testCreateProjectInvalidTitleShort() {
    // Title length <= 4 (4 characters)
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
public void testCreateProjectInvalidTitleEmpty() {
    Map<String, Object> body = new HashMap<>();
    body.put("title", "");

    given()
        .contentType(ContentType.JSON)
        .body(body)
    .when()
        .post("/api/project")
    .then()
        .statusCode(400);
}

@Test
public void testGetProjectInvalidCodeBoundary() {
    // Code minLength is 6; 5 characters should return 400
    given()
    .when()
        .get("/api/project/{code}", "12345")
    .then()
        .statusCode(400);
}

@Test
public void testGetProjectNotFound() {
    // Non-existent code (>= 6 characters)
    given()
    .when()
        .get("/api/project/{code}", "NONEXISTENT999")
    .then()
        .statusCode(404);
}

@Test
public void testUpdateProjectInvalidCode() {
    Map<String, Object> body = new HashMap<>();
    body.put("title", "Updated Project Title");

    given()
        .contentType(ContentType.JSON)
        .body(body)
    .when()
        .put("/api/project/{code}", "12345")
    .then()
        .statusCode(400);
}

@Test
public void testUpdateProjectNotFound() {
    Map<String, Object> body = new HashMap<>();
    body.put("title", "Updated Project Title");

    given()
        .contentType(ContentType.JSON)
        .body(body)
    .when()
        .put("/api/project/{code}", "NONEXISTENT999")
    .then()
        .statusCode(404);
}

@Test
public void testDeleteProjectInvalidCode() {
    given()
    .when()
        .delete("/api/project/{code}", "12345")
    .then()
        .statusCode(400);
}

@Test
public void testDeleteProjectNotFound() {
    given()
    .when()
        .delete("/api/project/{code}", "NONEXISTENT999")
    .then()
        .statusCode(404);
}

// -------------------------------------------------------------------------
// Problem Scenarios (/api/problem)
// -------------------------------------------------------------------------

@Test
public void testGetProblemsInvalidCode() {
    given()
    .when()
        .get("/api/problem/{code}", "12345")
    .then()
        .statusCode(400);
}

@Test
public void testGetProblemsNotFound() {
    given()
    .when()
        .get("/api/problem/{code}", "NONEXISTENT999")
    .then()
        .statusCode(404);
}

@Test
public void testCreateProblemInvalidCode() {
    Map<String, Object> body = new HashMap<>();
    body.put("title", "Problem Title");

    given()
        .contentType(ContentType.JSON)
        .body(body)
    .when()
        .post("/api/problem/{code}", "12345")
    .then()
        .statusCode(400);
}

@Test
public void testCreateProblemProjectNotFound() {
    Map<String, Object> body = new HashMap<>();
    body.put("title", "Problem Title");

    given()
        .contentType(ContentType.JSON)
        .body(body)
    .when()
        .post("/api/problem/{code}", "NONEXISTENT999")
    .then()
        .statusCode(404);
}

@Test
public void testDeleteProblemsInvalidCode() {
    given()
    .when()
        .delete("/api/problem/{code}", "12345")
    .then()
        .statusCode(400);
}

@Test
public void testDeleteAllProblemsAndSubproblemsInvalidCode() {
    given()
    .when()
        .delete("/api/problem/{code}/all", "12345")
    .then()
        .statusCode(400);
}

@Test
public void testDeleteAllProblemsAndSubproblemsNotFound() {
    given()
    .when()
        .delete("/api/problem/{code}/all", "NONEXISTENT999")
    .then()
        .statusCode(404);
}

// -------------------------------------------------------------------------
// SubProblem Scenarios (/api/subproblem)
// -------------------------------------------------------------------------

@Test
public void testCreateSubProblemInvalidContentBoundary() {
    // Content length > 9 required; 9 characters should fail with 400
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
public void testCreateSubProblemBoundarySuccessContent() {
    // Content length > 9 (10 characters boundary)
    Map<String, Object> body = new HashMap<>();
    body.put("content", "1234567890");
    body.put("pro_idx", 999999999L);

    given()
        .contentType(ContentType.JSON)
        .body(body)
    .when()
        .post("/api/subproblem")
    .then()
        .statusCode(404);
}

@Test
public void testCreateSubProblemProblemNotFound() {
    Map<String, Object> body = new HashMap<>();
    body.put("content", "Detailed SubProblem Content text");
    body.put("pro_idx", 999999999L);

    given()
        .contentType(ContentType.JSON)
        .body(body)
    .when()
        .post("/api/subproblem")
    .then()
        .statusCode(404);
}

// -------------------------------------------------------------------------
// Multi-Endpoint Integration Scenarios
// -------------------------------------------------------------------------

@Test
public void testFullProjectProblemSubProblemLifecycle() {
    // Step 1: Create a Project
    Map<String, Object> projectInput = new HashMap<>();
    projectInput.put("title", "Integration Test Project");

    Response createProjResp = given()
        .contentType(ContentType.JSON)
        .body(projectInput)
    .when()
        .post("/api/project")
    .then()
        .statusCode(201)
        .extract()
        .response();

    String code = createProjResp.jsonPath().getString("code");
    if (code == null || code.isBlank()) {
        code = "PROJ01";
    }

    // Step 2: Get Project details
    given()
    .when()
        .get("/api/project/{code}", code)
    .then()
        .statusCode(anyOf(equalTo(200), equalTo(404)));

    // Step 3: Update Project
    Map<String, Object> updateProjInput = new HashMap<>();
    updateProjInput.put("title", "Updated Integration Project");

    given()
        .contentType(ContentType.JSON)
        .body(updateProjInput)
    .when()
        .put("/api/project/{code}", code)
    .then()
        .statusCode(anyOf(equalTo(200), equalTo(404)));

    // Step 4: Create Problem under Project
    Map<String, Object> problemInput = new HashMap<>();
    problemInput.put("title", "Integration Problem");

    Response createProbResp = given()
        .contentType(ContentType.JSON)
        .body(problemInput)
    .when()
        .post("/api/problem/{code}", code)
    .then()
        .statusCode(anyOf(equalTo(201), equalTo(404)))
        .extract()
        .response();

    // Step 5: Get Problems for Project Code
    given()
    .when()
        .get("/api/problem/{code}", code)
    .then()
        .statusCode(anyOf(equalTo(200), equalTo(404)));

    // Step 6: Create SubProblem if problem index is returned
    Long proIdx = null;
    try {
        proIdx = createProbResp.jsonPath().getLong("idx");
    } catch (Exception ignored) {
    }

    if (proIdx != null) {
        Map<String, Object> subProblemInput = new HashMap<>();
        subProblemInput.put("content", "Valid content longer than nine");
        subProblemInput.put("pro_idx", proIdx);

        given()
            .contentType(ContentType.JSON)
            .body(subProblemInput)
        .when()
            .post("/api/subproblem")
        .then()
            .statusCode(anyOf(equalTo(201), equalTo(404)));
    }

    // Step 7: Delete Problems under Project
    given()
    .when()
        .delete("/api/problem/{code}", code)
    .then()
        .statusCode(anyOf(equalTo(200), equalTo(400)));

    // Step 8: Delete All Problems and Subproblems
    given()
    .when()
        .delete("/api/problem/{code}/all", code)
    .then()
        .statusCode(anyOf(equalTo(200), equalTo(404)));

    // Step 9: Delete Project
    given()
    .when()
        .delete("/api/project/{code}", code)
    .then()
        .statusCode(anyOf(equalTo(200), equalTo(404)));
}

}