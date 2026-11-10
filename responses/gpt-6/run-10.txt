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
 void createProjectWithEmptyTitle() {
 given()
 .contentType(ContentType.JSON)
 .body("{\"title\":\"\"}")
 .when()
 .post("/api/project")
 .then()
 .statusCode(anyOf(is(200), is(400)));
 }

 @Test
 void createProjectWithMissingTitle() {
 given()
 .contentType(ContentType.JSON)
 .body("{}")
 .when()
 .post("/api/project")
 .then()
 .statusCode(400);
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
 .statusCode(anyOf(is(200), is(400)));
 }

 @Test
 void createProjectWithNullTitle() {
 given()
 .contentType(ContentType.JSON)
 .body("{\"title\":null}")
 .when()
 .post("/api/project")
 .then()
 .statusCode(400);
 }

 @Test
 void createProjectWithoutRequestBody() {
 given()
 .contentType(ContentType.JSON)
 .when()
 .post("/api/project")
 .then()
 .statusCode(400);
 }

 @Test
 void createProjectWithProblemArray() {
 given()
 .contentType(ContentType.JSON)
 .body("{\"title\":\"Project With Problems\",\"problem\":[]}")
 .when()
 .post("/api/project")
 .then()
 .statusCode(200);
 }

 @Test
 void createProjectWithDuplicateProblems() {
 String body = """
 {
 "title": "Project With Duplicate Problems",
 "problem": [
 {"title": "Problem A"},
 {"title": "Problem A"}
 ]
 }
 """;

 given()
 .contentType(ContentType.JSON)
 .body(body)
 .when()
 .post("/api/project")
 .then()
 .statusCode(anyOf(is(200), is(400)));
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
 void getProjectWithNonexistentCode() {
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
 .statusCode(anyOf(is(200), is(400), is(404)));
 }

 @Test
 void getProjectWithCodeLongerThanSixCharacters() {
 given()
 .when()
 .get("/api/project/ABC1234")
 .then()
 .statusCode(anyOf(is(200), is(400), is(404)));
 }

 @Test
 void getProjectWithSpecialCharactersInCode() {
 given()
 .when()
 .get("/api/project/ABC%401")
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
 void updateProjectWithoutTitle() {
 given()
 .contentType(ContentType.JSON)
 .body("{}")
 .when()
 .put("/api/project/ABC123")
 .then()
 .statusCode(anyOf(is(200), is(400), is(404)));
 }

 @Test
 void updateProjectWithExcessivelyLongTitle() {
 String title = "C".repeat(201);

 given()
 .contentType(ContentType.JSON)
 .body("{\"title\":\"" + title + "\"}")
 .when()
 .put("/api/project/ABC123")
 .then()
 .statusCode(anyOf(is(200), is(400), is(404)));
 }

 @Test
 void updateProjectWithoutRequestBody() {
 given()
 .contentType(ContentType.JSON)
 .when()
 .put("/api/project/ABC123")
 .then()
 .statusCode(anyOf(is(400), is(404)));
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
 void deleteProjectWithNonexistentCode() {
 given()
 .when()
 .delete("/api/project/ZZZ999")
 .then()
 .statusCode(anyOf(is(200), is(404)));
 }

 @Test
 void deleteProjectWithLongCode() {
 given()
 .when()
 .delete("/api/project/ABC1234")
 .then()
 .statusCode(anyOf(is(200), is(400), is(404)));
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
 void createProblemWithAllOptionalFields() {
 String body = """
 {
 "idx": 1,
 "title": "Problem With All Fields",
 "problemCode": "PRB001",
 "project": {
 "title": "Test Project"
 }
 }
 """;

 given()
 .contentType(ContentType.JSON)
 .body(body)
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
 .when()
 .post("/api/problem/ABC123")
 .then()
 .statusCode(400);
 }

 @Test
 void createProblemWithNonexistentProjectCode() {
 given()
 .contentType(ContentType.JSON)
 .body("{\"title\":\"Independent Problem\"}")
 .when()
 .post("/api/problem/ZZZ999")
 .then()
 .statusCode(200);
 }

 @Test
 void createProblemWithLongProjectCode() {
 given()
 .contentType(ContentType.JSON)
 .body("{\"title\":\"Problem\"}")
 .when()
 .post("/api/problem/ABC1234")
 .then()
 .statusCode(anyOf(is(200), is(400), is(404)));
 }

 // Problem retrieval

 @Test
 void getProblemsByValidCode() {
 given()
 .when()
 .get("/api/problem/ABC123")
 .then()
 .statusCode(200)
 .body("$", instanceOf(java.util.List.class));
 }

 @Test
 void getProblemsByNonexistentCode() {
 given()
 .when()
 .get("/api/problem/ZZZ999")
 .then()
 .statusCode(200)
 .body("$", instanceOf(java.util.List.class));
 }

 @Test
 void getProblemsByLongCode() {
 given()
 .when()
 .get("/api/problem/ABC1234")
 .then()
 .statusCode(anyOf(is(200), is(400), is(404)));
 }

 @Test
 void getProblemsBySpecialCharacterCode() {
 given()
 .when()
 .get("/api/problem/ABC%401")
 .then()
 .statusCode(anyOf(is(200), is(400), is(404)));
 }

 // Problem deletion

 @Test
 void deleteRecentProblemByCode() {
 given()
 .when()
 .delete("/api/problem/ABC123")
 .then()
 .statusCode(200);
 }

 @Test
 void deleteRecentProblemByNonexistentCode() {
 given()
 .when()
 .delete("/api/problem/ZZZ999")
 .then()
 .statusCode(200);
 }

 @Test
 void deleteAllProblemsByCode() {
 given()
 .when()
 .delete("/api/problem/ABC123/all")
 .then()
 .statusCode(200);
 }

 @Test
 void deleteAllProblemsByNonexistentCode() {
 given()
 .when()
 .delete("/api/problem/ZZZ999/all")
 .then()
 .statusCode(200);
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
 void createSubProblemWithMinimumIndex() {
 given()
 .contentType(ContentType.JSON)
 .body("{\"pro_idx\":0,\"content\":\"Minimum index\"}")
 .when()
 .post("/api/subproblem")
 .then()
 .statusCode(200);
 }

 @Test
 void createSubProblemWithMaximumLongIndex() {
 given()
 .contentType(ContentType.JSON)
 .body("{\"pro_idx\":9223372036854775807,\"content\":\"Maximum index\"}")
 .when()
 .post("/api/subproblem")
 .then()
 .statusCode(200);
 }

 @Test
 void createSubProblemWithNegativeIndex() {
 given()
 .contentType(ContentType.JSON)
 .body("{\"pro_idx\":-1,\"content\":\"Negative index\"}")
 .when()
 .post("/api/subproblem")
 .then()
 .statusCode(200);
 }

 @Test
 void createSubProblemWithNullIndex() {
 given()
 .contentType(ContentType.JSON)
 .body("{\"pro_idx\":null,\"content\":\"Null index\"}")
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
 .when()
 .post("/api/subproblem")
 .then()
 .statusCode(400);
 }

 // Multi-endpoint scenarios

 @Test
 void createAndRetrieveProject() {
 Response createResponse =
 given()
 .contentType(ContentType.JSON)
 .body("{\"title\":\"Create And Retrieve Project\"}")
 .when()
 .post("/api/project")
 .then()
 .statusCode(200)
 .extract()
 .response();

 String code = createResponse.jsonPath().getString("code");

 if (code != null && !code.isBlank()) {
 given()
 .when()
 .get("/api/project/" + code)
 .then()
 .statusCode(200);
 }
 }

 @Test
 void createAndUpdateProject() {
 Response createResponse =
 given()
 .contentType(ContentType.JSON)
 .body("{\"title\":\"Create And Update Project\"}")
 .when()
 .post("/api/project")
 .then()
 .statusCode(200)
 .extract()
 .response();

 String code = createResponse.jsonPath().getString("code");

 if (code != null && !code.isBlank()) {
 given()
 .contentType(ContentType.JSON)
 .body("{\"title\":\"Updated Independently Created Project\"}")
 .when()
 .put("/api/project/" + code)
 .then()
 .statusCode(200);
 }
 }

 @Test
 void createProjectCreateProblemAndRetrieveProblems() {
 Response createResponse =
 given()
 .contentType(ContentType.JSON)
 .body("{\"title\":\"Project For Problem Integration\"}")
 .when()
 .post("/api/project")
 .then()
 .statusCode(200)
 .extract()
 .response();

 String code = createResponse.jsonPath().getString("code");

 if (code != null && !code.isBlank()) {
 given()
 .contentType(ContentType.JSON)
 .body("{\"title\":\"Integration Problem\"}")
 .when()
 .post("/api/problem/" + code)
 .then()
 .statusCode(200);

 given()
 .when()
 .get("/api/problem/" + code)
 .then()
 .statusCode(200)
 .body("$", instanceOf(java.util.List.class));
 }
 }

 @Test
 void createProjectAndDeleteIt() {
 Response createResponse =
 given()
 .contentType(ContentType.JSON)
 .body("{\"title\":\"Project For Deletion Integration\"}")
 .when()
 .post("/api/project")
 .then()
 .statusCode(200)
 .extract()
 .response();

 String code = createResponse.jsonPath().getString("code");

 if (code != null && !code.isBlank()) {
 given()
 .when()
 .delete("/api/project/" + code)
 .then()
 .statusCode(200);
 }
 }

 @Test
 void createMultipleProblemsAndDeleteRecentProblem() {
 String code = "TST789";

 given()
 .contentType(ContentType.JSON)
 .body("{\"title\":\"First Problem\"}")
 .when()
 .post("/api/problem/" + code)
 .then()
 .statusCode(200);

 given()
 .contentType(ContentType.JSON)
 .body("{\"title\":\"Second Problem\"}")
 .when()
 .post("/api/problem/" + code)
 .then()
 .statusCode(200);

 given()
 .when()
 .delete("/api/problem/" + code)
 .then()
 .statusCode(200);
 }

 @Test
 void createProblemsAndDeleteAllProblems() {
 String code = "TST790";

 given()
 .contentType(ContentType.JSON)
 .body("{\"title\":\"First Problem\"}")
 .when()
 .post("/api/problem/" + code)
 .then()
 .statusCode(200);

 given()
 .contentType(ContentType.JSON)
 .body("{\"title\":\"Second Problem\"}")
 .when()
 .post("/api/problem/" + code)
 .then()
 .statusCode(200);

 given()
 .when()
 .delete("/api/problem/" + code + "/all")
 .then()
 .statusCode(200);

 given()
 .when()
 .get("/api/problem/" + code)
 .then()
 .statusCode(200)
 .body("$", hasSize(0));
 }

 @Test
 void createSubProblemForProblem() {
 String code = "TST791";

 given()
 .contentType(ContentType.JSON)
 .body("{\"title\":\"Problem For Subproblem\"}")
 .when()
 .post("/api/problem/" + code)
 .then()
 .statusCode(200);

 given()
 .contentType(ContentType.JSON)
 .body("{\"pro_idx\":1,\"content\":\"Associated Subproblem\"}")
 .when()
 .post("/api/subproblem")
 .then()
 .statusCode(200);
 }

 @Test
 void retrieveProblemsAfterDeletingRecentProblem() {
 String code = "TST792";

 given()
 .contentType(ContentType.JSON)
 .body("{\"title\":\"Problem One\"}")
 .when()
 .post("/api/problem/" + code)
 .then()
 .statusCode(200);

 given()
 .contentType(ContentType.JSON)
 .body("{\"title\":\"Problem Two\"}")
 .when()
 .post("/api/problem/" + code)
 .then()
 .statusCode(200);

 given()
 .when()
 .delete("/api/problem/" + code)
 .then()
 .statusCode(200);

 given()
 .when()
 .get("/api/problem/" + code)
 .then()
 .statusCode(200);
 }
}