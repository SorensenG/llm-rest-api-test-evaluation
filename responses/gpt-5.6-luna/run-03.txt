import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class GeneratedApiTest {

 private static final String BASE_URL = "http://localhost:8080";

 @BeforeAll
 static void setUp() {
 RestAssured.baseURI = BASE_URL;
 }

 // Project creation

 @Test
 void createProjectWithValidTitle() {
 given()
 .contentType(ContentType.JSON)
 .body("{\"title\":\"Integration Test Project\"}")
 .when()
 .post("/api/project")
 .then()
 .statusCode(200)
 .contentType(ContentType.JSON);
 }

 @Test
 void createProjectWithMinimumLengthTitle() {
 given()
 .contentType(ContentType.JSON)
 .body("{\"title\":\"\"}")
 .when()
 .post("/api/project")
 .then()
 .statusCode(200);
 }

 @Test
 void createProjectWithMaximumLengthTitle() {
 String title = "A".repeat(200);

 given()
 .contentType(ContentType.JSON)
 .body("{\"title\":\"" + title + "\"}")
 .when()
 .post("/api/project")
 .then()
 .statusCode(200);
 }

 @Test
 void createProjectWithTitleExceedingMaximumLength() {
 String title = "A".repeat(201);

 given()
 .contentType(ContentType.JSON)
 .body("{\"title\":\"" + title + "\"}")
 .when()
 .post("/api/project")
 .then()
 .statusCode(anyOf(is(200), is(400), is(422)));
 }

 @Test
 void createProjectWithoutRequiredTitle() {
 given()
 .contentType(ContentType.JSON)
 .body("{}")
 .when()
 .post("/api/project")
 .then()
 .statusCode(anyOf(is(400), is(422)));
 }

 @Test
 void createProjectWithNullTitle() {
 given()
 .contentType(ContentType.JSON)
 .body("{\"title\":null}")
 .when()
 .post("/api/project")
 .then()
 .statusCode(anyOf(is(400), is(422)));
 }

 @Test
 void createProjectWithOptionalFields() {
 given()
 .contentType(ContentType.JSON)
 .body("{\"title\":\"Project With Optional Fields\",\"idx\":1,\"code\":\"ABC123\"}")
 .when()
 .post("/api/project")
 .then()
 .statusCode(200);
 }

 // Project retrieval

 @Test
 void getProjectWithSixCharacterCode() {
 given()
 .when()
 .get("/api/project/ABC123")
 .then()
 .statusCode(anyOf(is(200), is(404)));
 }

 @Test
 void getProjectWithAlphanumericCode() {
 given()
 .when()
 .get("/api/project/a1B2c3")
 .then()
 .statusCode(anyOf(is(200), is(404)));
 }

 @Test
 void getProjectWithUnknownCode() {
 given()
 .when()
 .get("/api/project/ZZZ999")
 .then()
 .statusCode(anyOf(is(200), is(404)));
 }

 @Test
 void getProjectWithEmptyCode() {
 given()
 .when()
 .get("/api/project/")
 .then()
 .statusCode(anyOf(is(400), is(404)));
 }

 @Test
 void getProjectWithShortCode() {
 given()
 .when()
 .get("/api/project/ABC12")
 .then()
 .statusCode(anyOf(is(200), is(400), is(404)));
 }

 @Test
 void getProjectWithLongCode() {
 given()
 .when()
 .get("/api/project/ABC1234")
 .then()
 .statusCode(anyOf(is(200), is(400), is(404)));
 }

 // Project update

 @Test
 void updateProjectWithValidTitle() {
 given()
 .contentType(ContentType.JSON)
 .body("{\"title\":\"Updated Project Title\"}")
 .when()
 .put("/api/project/ABC123")
 .then()
 .statusCode(anyOf(is(200), is(404)));
 }

 @Test
 void updateProjectWithAllOptionalFields() {
 given()
 .contentType(ContentType.JSON)
 .body("{\"idx\":1,\"code\":\"ABC123\",\"title\":\"Updated Project\",\"problem\":[]}")
 .when()
 .put("/api/project/ABC123")
 .then()
 .statusCode(anyOf(is(200), is(400), is(404)));
 }

 @Test
 void updateProjectWithoutTitle() {
 given()
 .contentType(ContentType.JSON)
 .body("{\"idx\":1,\"code\":\"ABC123\"}")
 .when()
 .put("/api/project/ABC123")
 .then()
 .statusCode(anyOf(is(400), is(404), is(422)));
 }

 @Test
 void updateProjectWithNullTitle() {
 given()
 .contentType(ContentType.JSON)
 .body("{\"title\":null}")
 .when()
 .put("/api/project/ABC123")
 .then()
 .statusCode(anyOf(is(200), is(400), is(404), is(422)));
 }

 @Test
 void updateProjectWithEmptyTitle() {
 given()
 .contentType(ContentType.JSON)
 .body("{\"title\":\"\"}")
 .when()
 .put("/api/project/ABC123")
 .then()
 .statusCode(anyOf(is(200), is(400), is(404)));
 }

 @Test
 void updateProjectWithMaximumLengthTitle() {
 String title = "B".repeat(200);

 given()
 .contentType(ContentType.JSON)
 .body("{\"title\":\"" + title + "\"}")
 .when()
 .put("/api/project/ABC123")
 .then()
 .statusCode(anyOf(is(200), is(400), is(404)));
 }

 @Test
 void updateProjectWithTitleExceedingMaximumLength() {
 String title = "B".repeat(201);

 given()
 .contentType(ContentType.JSON)
 .body("{\"title\":\"" + title + "\"}")
 .when()
 .put("/api/project/ABC123")
 .then()
 .statusCode(anyOf(is(200), is(400), is(404), is(422)));
 }

 // Project deletion

 @Test
 void deleteProjectWithValidCode() {
 given()
 .when()
 .delete("/api/project/ABC123")
 .then()
 .statusCode(anyOf(is(200), is(404)));
 }

 @Test
 void deleteProjectWithUnknownCode() {
 given()
 .when()
 .delete("/api/project/ZZZ999")
 .then()
 .statusCode(anyOf(is(200), is(404)));
 }

 // Problem creation

 @Test
 void createProblemWithValidRequest() {
 given()
 .contentType(ContentType.JSON)
 .body("{\"title\":\"Integration Test Problem\"}")
 .when()
 .post("/api/problem/ABC123")
 .then()
 .statusCode(200);
 }

 @Test
 void createProblemWithAllDocumentedFields() {
 given()
 .contentType(ContentType.JSON)
 .body("{\"idx\":1,\"title\":\"Test Problem\",\"problemCode\":\"PRB123\",\"project\":{\"title\":\"Test Project\"}}")
 .when()
 .post("/api/problem/ABC123")
 .then()
 .statusCode(200);
 }

 @Test
 void createProblemWithEmptyTitle() {
 given()
 .contentType(ContentType.JSON)
 .body("{\"title\":\"\"}")
 .when()
 .post("/api/problem/ABC123")
 .then()
 .statusCode(200);
 }

 @Test
 void createProblemWithNullTitle() {
 given()
 .contentType(ContentType.JSON)
 .body("{\"title\":null}")
 .when()
 .post("/api/problem/ABC123")
 .then()
 .statusCode(200);
 }

 @Test
 void createProblemWithEmptyObject() {
 given()
 .contentType(ContentType.JSON)
 .body("{}")
 .when()
 .post("/api/problem/ABC123")
 .then()
 .statusCode(200);
 }

 @Test
 void createProblemWithoutRequestBody() {
 given()
 .contentType(ContentType.JSON)
 .when()
 .post("/api/problem/ABC123")
 .then()
 .statusCode(anyOf(is(400), is(415)));
 }

 @Test
 void createProblemWithShortCode() {
 given()
 .contentType(ContentType.JSON)
 .body("{\"title\":\"Test Problem\"}")
 .when()
 .post("/api/problem/ABC12")
 .then()
 .statusCode(anyOf(is(200), is(400), is(404)));
 }

 @Test
 void createProblemWithLongCode() {
 given()
 .contentType(ContentType.JSON)
 .body("{\"title\":\"Test Problem\"}")
 .when()
 .post("/api/problem/ABC1234")
 .then()
 .statusCode(anyOf(is(200), is(400), is(404)));
 }

 // Problem retrieval

 @Test
 void getProblemsByCode() {
 given()
 .when()
 .get("/api/problem/ABC123")
 .then()
 .statusCode(200)
 .contentType(ContentType.JSON)
 .body("$", instanceOf(java.util.List.class));
 }

 @Test
 void getProblemsWithUnknownCode() {
 given()
 .when()
 .get("/api/problem/ZZZ999")
 .then()
 .statusCode(anyOf(is(200), is(404)));
 }

 @Test
 void getProblemsWithShortCode() {
 given()
 .when()
 .get("/api/problem/ABC12")
 .then()
 .statusCode(anyOf(is(200), is(400), is(404)));
 }

 @Test
 void getProblemsWithLongCode() {
 given()
 .when()
 .get("/api/problem/ABC1234")
 .then()
 .statusCode(anyOf(is(200), is(400), is(404)));
 }

 @Test
 void getProblemsWithAlphanumericCode() {
 given()
 .when()
 .get("/api/problem/a1B2c3")
 .then()
 .statusCode(200)
 .body("$", instanceOf(java.util.List.class));
 }

 // Problem deletion

 @Test
 void deleteRecentProblemWithValidCode() {
 given()
 .when()
 .delete("/api/problem/ABC123")
 .then()
 .statusCode(200);
 }

 @Test
 void deleteRecentProblemWithUnknownCode() {
 given()
 .when()
 .delete("/api/problem/ZZZ999")
 .then()
 .statusCode(anyOf(is(200), is(404)));
 }

 @Test
 void deleteAllProblemsWithValidCode() {
 given()
 .when()
 .delete("/api/problem/ABC123/all")
 .then()
 .statusCode(200);
 }

 @Test
 void deleteAllProblemsWithUnknownCode() {
 given()
 .when()
 .delete("/api/problem/ZZZ999/all")
 .then()
 .statusCode(anyOf(is(200), is(404)));
 }

 @Test
 void deleteAllProblemsWithShortCode() {
 given()
 .when()
 .delete("/api/problem/ABC12/all")
 .then()
 .statusCode(anyOf(is(200), is(400), is(404)));
 }

 // Subproblem creation

 @Test
 void createSubProblemWithValidRequest() {
 given()
 .contentType(ContentType.JSON)
 .body("{\"pro_idx\":1,\"content\":\"Integration Test Subproblem\"}")
 .when()
 .post("/api/subproblem")
 .then()
 .statusCode(200);
 }

 @Test
 void createSubProblemWithMinimumProIdx() {
 given()
 .contentType(ContentType.JSON)
 .body("{\"pro_idx\":0,\"content\":\"Minimum index\"}")
 .when()
 .post("/api/subproblem")
 .then()
 .statusCode(200);
 }

 @Test
 void createSubProblemWithNegativeProIdx() {
 given()
 .contentType(ContentType.JSON)
 .body("{\"pro_idx\":-1,\"content\":\"Negative index\"}")
 .when()
 .post("/api/subproblem")
 .then()
 .statusCode(200);
 }

 @Test
 void createSubProblemWithMaximumLongProIdx() {
 given()
 .contentType(ContentType.JSON)
 .body("{\"pro_idx\":9223372036854775807,\"content\":\"Maximum index\"}")
 .when()
 .post("/api/subproblem")
 .then()
 .statusCode(200);
 }

 @Test
 void createSubProblemWithProIdxExceedingLongRange() {
 given()
 .contentType(ContentType.JSON)
 .body("{\"pro_idx\":9223372036854775808,\"content\":\"Out of range index\"}")
 .when()
 .post("/api/subproblem")
 .then()
 .statusCode(anyOf(is(400), is(200), is(422)));
 }

 @Test
 void createSubProblemWithEmptyContent() {
 given()
 .contentType(ContentType.JSON)
 .body("{\"pro_idx\":1,\"content\":\"\"}")
 .when()
 .post("/api/subproblem")
 .then()
 .statusCode(200);
 }

 @Test
 void createSubProblemWithNullContent() {
 given()
 .contentType(ContentType.JSON)
 .body("{\"pro_idx\":1,\"content\":null}")
 .when()
 .post("/api/subproblem")
 .then()
 .statusCode(200);
 }

 @Test
 void createSubProblemWithNullProIdx() {
 given()
 .contentType(ContentType.JSON)
 .body("{\"pro_idx\":null,\"content\":\"Test content\"}")
 .when()
 .post("/api/subproblem")
 .then()
 .statusCode(200);
 }

 @Test
 void createSubProblemWithEmptyObject() {
 given()
 .contentType(ContentType.JSON)
 .body("{}")
 .when()
 .post("/api/subproblem")
 .then()
 .statusCode(200);
 }

 @Test
 void createSubProblemWithoutRequestBody() {
 given()
 .contentType(ContentType.JSON)
 .when()
 .post("/api/subproblem")
 .then()
 .statusCode(anyOf(is(400), is(415)));
 }

 // Multi-endpoint scenarios

 @Test
 void createProjectThenRetrieveUsingReturnedCode() {
 Response response =
 given()
 .contentType(ContentType.JSON)
 .body("{\"title\":\"Create And Retrieve Project\"}")
 .when()
 .post("/api/project")
 .then()
 .statusCode(200)
 .extract()
 .response();

 String code = response.jsonPath().getString("code");

 if (code != null) {
 given()
 .when()
 .get("/api/project/" + code)
 .then()
 .statusCode(200);
 }
 }

 @Test
 void createProjectThenUpdateUsingReturnedCode() {
 Response response =
 given()
 .contentType(ContentType.JSON)
 .body("{\"title\":\"Project Before Update\"}")
 .when()
 .post("/api/project")
 .then()
 .statusCode(200)
 .extract()
 .response();

 String code = response.jsonPath().getString("code");

 if (code != null) {
 given()
 .contentType(ContentType.JSON)
 .body("{\"title\":\"Project After Update\"}")
 .when()
 .put("/api/project/" + code)
 .then()
 .statusCode(200);

 given()
 .when()
 .get("/api/project/" + code)
 .then()
 .statusCode(200);
 }
 }

 @Test
 void createProjectThenDeleteUsingReturnedCode() {
 Response response =
 given()
 .contentType(ContentType.JSON)
 .body("{\"title\":\"Project To Delete\"}")
 .when()
 .post("/api/project")
 .then()
 .statusCode(200)
 .extract()
 .response();

 String code = response.jsonPath().getString("code");

 if (code != null) {
 given()
 .when()
 .delete("/api/project/" + code)
 .then()
 .statusCode(200);
 }
 }

 @Test
 void createProblemThenRetrieveByCode() {
 given()
 .contentType(ContentType.JSON)
 .body("{\"title\":\"Independent Problem Creation\"}")
 .when()
 .post("/api/problem/ABC123")
 .then()
 .statusCode(200);

 given()
 .when()
 .get("/api/problem/ABC123")
 .then()
 .statusCode(200)
 .body("$", instanceOf(java.util.List.class));
 }

 @Test
 void createMultipleProblemsThenDeleteRecentProblem() {
 given()
 .contentType(ContentType.JSON)
 .body("{\"title\":\"First Independent Problem\"}")
 .when()
 .post("/api/problem/DEF456")
 .then()
 .statusCode(200);

 given()
 .contentType(ContentType.JSON)
 .body("{\"title\":\"Second Independent Problem\"}")
 .when()
 .post("/api/problem/DEF456")
 .then()
 .statusCode(200);

 given()
 .when()
 .delete("/api/problem/DEF456")
 .then()
 .statusCode(200);

 given()
 .when()
 .get("/api/problem/DEF456")
 .then()
 .statusCode(200);
 }

 @Test
 void createProblemsThenDeleteAllProblems() {
 given()
 .contentType(ContentType.JSON)
 .body("{\"title\":\"First Problem For Bulk Deletion\"}")
 .when()
 .post("/api/problem/GHI789")
 .then()
 .statusCode(200);

 given()
 .contentType(ContentType.JSON)
 .body("{\"title\":\"Second Problem For Bulk Deletion\"}")
 .when()
 .post("/api/problem/GHI789")
 .then()
 .statusCode(200);

 given()
 .when()
 .delete("/api/problem/GHI789/all")
 .then()
 .statusCode(200);

 given()
 .when()
 .get("/api/problem/GHI789")
 .then()
 .statusCode(200)
 .body("$", hasSize(0));
 }

 @Test
 void createSubProblemWithoutDependingOnExistingProject() {
 given()
 .contentType(ContentType.JSON)
 .body("{\"pro_idx\":9223372036854775807,\"content\":\"Independent subproblem\"}")
 .when()
 .post("/api/subproblem")
 .then()
 .statusCode(200);
 }
}

