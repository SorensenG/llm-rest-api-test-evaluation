import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class GeneratedApiTest {

@BeforeAll
public static void setup() {
    String baseUrl = System.getProperty("API_BASE_URL");
    if (baseUrl == null || baseUrl.trim().isEmpty()) {
        baseUrl = System.getenv("API_BASE_URL");
    }
    if (baseUrl == null || baseUrl.trim().isEmpty() || baseUrl.equals("${API_BASE_URL}")) {
        baseUrl = "http://localhost:8080";
    }
    RestAssured.baseURI = baseUrl;
}

@Test
public void testCreateProject_Success_BoundaryTitleLengthFive() {
    given()
        .contentType(ContentType.JSON)
        .body("{\"title\":\"12345\"}")
    .when()
        .post("/api/project")
    .then()
        .statusCode(201);
}

@Test
public void testCreateProject_Success_ValidTitle() {
    given()
        .contentType(ContentType.JSON)
        .body("{\"title\":\"Valid Project Title\"}")
    .when()
        .post("/api/project")
    .then()
        .statusCode(201);
}

@Test
public void testCreateProject_Failure_BoundaryTitleLengthFour() {
    given()
        .contentType(ContentType.JSON)
        .body("{\"title\":\"1234\"}")
    .when()
        .post("/api/project")
    .then()
        .statusCode(400);
}

@Test
public void testCreateProject_Failure_ShortTitle() {
    given()
        .contentType(ContentType.JSON)
        .body("{\"title\":\"ABC\"}")
    .when()
        .post("/api/project")
    .then()
        .statusCode(400);
}

@Test
public void testGetProject_Failure_InvalidCodeLengthBoundary() {
    given()
    .when()
        .get("/api/project/12345")
    .then()
        .statusCode(400);
}

@Test
public void testGetProject_Failure_NotFound() {
    given()
    .when()
        .get("/api/project/NONEXISTENT_CODE_123")
    .then()
        .statusCode(404);
}

@Test
public void testUpdateProject_Failure_InvalidCode() {
    given()
        .contentType(ContentType.JSON)
        .body("{\"title\":\"Updated Valid Title\"}")
    .when()
        .put("/api/project/12345")
    .then()
        .statusCode(400);
}

@Test
public void testUpdateProject_Failure_NotFound() {
    given()
        .contentType(ContentType.JSON)
        .body("{\"title\":\"Updated Valid Title\"}")
    .when()
        .put("/api/project/NONEXISTENT_CODE_123")
    .then()
        .statusCode(404);
}

@Test
public void testDeleteProject_Failure_InvalidCode() {
    given()
    .when()
        .delete("/api/project/12345")
    .then()
        .statusCode(400);
}

@Test
public void testDeleteProject_Failure_NotFound() {
    given()
    .when()
        .delete("/api/project/NONEXISTENT_CODE_123")
    .then()
        .statusCode(404);
}

@Test
public void testGetProblems_Failure_InvalidCode() {
    given()
    .when()
        .get("/api/problem/12345")
    .then()
        .statusCode(400);
}

@Test
public void testGetProblems_Failure_NotFound() {
    given()
    .when()
        .get("/api/problem/NONEXISTENT_CODE_123")
    .then()
        .statusCode(404);
}

@Test
public void testCreateProblem_Failure_InvalidCode() {
    given()
        .contentType(ContentType.JSON)
        .body("{\"title\":\"Problem Title\"}")
    .when()
        .post("/api/problem/12345")
    .then()
        .statusCode(400);
}

@Test
public void testCreateProblem_Failure_ProjectNotFound() {
    given()
        .contentType(ContentType.JSON)
        .body("{\"title\":\"Problem Title\"}")
    .when()
        .post("/api/problem/NONEXISTENT_CODE_123")
    .then()
        .statusCode(404);
}

@Test
public void testDeleteProblems_Failure_InvalidCode() {
    given()
    .when()
        .delete("/api/problem/12345")
    .then()
        .statusCode(400);
}

@Test
public void testDeleteAllProblemsAndSubproblems_Failure_InvalidCode() {
    given()
    .when()
        .delete("/api/problem/12345/all")
    .then()
        .statusCode(400);
}

@Test
public void testDeleteAllProblemsAndSubproblems_Failure_NotFound() {
    given()
    .when()
        .delete("/api/problem/NONEXISTENT_CODE_123/all")
    .then()
        .statusCode(404);
}

@Test
public void testCreateSubProblem_Failure_ContentLengthNineBoundary() {
    given()
        .contentType(ContentType.JSON)
        .body("{\"content\":\"123456789\",\"pro_idx\":1}")
    .when()
        .post("/api/subproblem")
    .then()
        .statusCode(400);
}

@Test
public void testCreateSubProblem_Failure_ShortContent() {
    given()
        .contentType(ContentType.JSON)
        .body("{\"content\":\"Short\",\"pro_idx\":1}")
    .when()
        .post("/api/subproblem")
    .then()
        .statusCode(400);
}

@Test
public void testCreateSubProblem_Failure_ProblemNotFound() {
    given()
        .contentType(ContentType.JSON)
        .body("{\"content\":\"Valid subproblem content longer than 9 chars\",\"pro_idx\":999999999}")
    .when()
        .post("/api/subproblem")
    .then()
        .statusCode(404);
}

@Test
public void testMultiEndpoint_ProjectAndProblemLifecycle() {
    Response createProjectResponse = given()
        .contentType(ContentType.JSON)
        .body("{\"title\":\"Lifecycle Integration Test Project\"}")
    .when()
        .post("/api/project");

    createProjectResponse.then().statusCode(201);

    String code = createProjectResponse.jsonPath().getString("code");
    if (code != null && !code.isEmpty()) {
        given()
        .when()
            .get("/api/project/" + code)
        .then()
            .statusCode(200)
            .body("title", equalTo("Lifecycle Integration Test Project"));

        given()
            .contentType(ContentType.JSON)
            .body("{\"title\":\"Updated Integration Test Project Title\"}")
        .when()
            .put("/api/project/" + code)
        .then()
            .statusCode(200);

        Response createProblemResponse = given()
            .contentType(ContentType.JSON)
            .body("{\"title\":\"Lifecycle Problem Title\"}")
        .when()
            .post("/api/problem/" + code);

        if (createProblemResponse.statusCode() == 201) {
            Long proIdx = createProblemResponse.jsonPath().getLong("idx");
            if (proIdx != null && proIdx > 0) {
                given()
                    .contentType(ContentType.JSON)
                    .body("{\"content\":\"Valid subproblem content description\",\"pro_idx\":" + proIdx + "}")
                .when()
                    .post("/api/subproblem")
                .then()
                    .statusCode(201);
            }
        }

        given()
        .when()
            .get("/api/problem/" + code)
        .then()
            .statusCode(200);

        given()
        .when()
            .delete("/api/problem/" + code)
        .then()
            .statusCode(200);

        given()
        .when()
            .delete("/api/problem/" + code + "/all")
        .then()
            .statusCode(200);

        given()
        .when()
            .delete("/api/project/" + code)
        .then()
            .statusCode(200);
    }
}

}