import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.*;

public class GeneratedApiTest {

 private static final String BASE_URL = "http://localhost:8080";

 static {
 RestAssured.baseURI = BASE_URL;
 }

 private String uniqueTitle() {
 return "Project-" + java.util.UUID.randomUUID();
 }

 private String createProject() {
 String title = uniqueTitle();

 Response response = given()
 .contentType(ContentType.JSON)
 .body(Map.of("title", title))
 .post("/api/project");

 assertEquals(200, response.statusCode());

 String code = response.jsonPath().getString("code");
 assertNotNull(code);
 assertEquals(6, code.length());

 return code;
 }

 @Test
 void createProjectWithValidTitle() {
 given()
 .contentType(ContentType.JSON)
 .body(Map.of("title", uniqueTitle()))
 .when()
 .post("/api/project")
 .then()
 .statusCode(200);
 }

 @Test
 void createProjectWithEmptyTitle() {
 given()
 .contentType(ContentType.JSON)
 .body(Map.of("title", ""))
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
 .body(Map.of("title", title))
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
 .body(Map.of("title", title))
 .when()
 .post("/api/project")
 .then()
 .statusCode(200);
 }

 @Test
 void createProjectWithoutRequiredTitle() {
 given()
 .contentType(ContentType.JSON)
 .body(Map.of())
 .when()
 .post("/api/project")
 .then()
 .statusCode(200);
 }

 @Test
 void createProjectWithNullTitle() {
 given()
 .contentType(ContentType.JSON)
 .body("{\"title\":null}")
 .when()
 .post("/api/project")
 .then()
 .statusCode(200);
 }

 @Test
 void createProjectWithOptionalFields() {
 given()
 .contentType(ContentType.JSON)
 .body(Map.of(
 "title", uniqueTitle(),
 "code", "ABC123",
 "problem", java.util.List.of()
 ))
 .when()
 .post("/api/project")
 .then()
 .statusCode(200);
 }

 @Test
 void getProjectWithValidCode() {
 String code = createProject();

 given()
 .when()
 .get("/api/project/{code}", code)
 .then()
 .statusCode(200);
 }

 @Test
 void getProjectWithSixCharacterCode() {
 given()
 .when()
 .get("/api/project/{code}", "ABC123")
 .then()
 .statusCode(200);
 }

 @Test
 void getProjectWithEmptyCode() {
 given()
 .when()
 .get("/api/project/{code}", "")
 .then()
 .statusCode(200);
 }

 @Test
 void getProjectWithShortCode() {
 given()
 .when()
 .get("/api/project/{code}", "ABC12")
 .then()
 .statusCode(200);
 }

 @Test
 void getProjectWithLongCode() {
 given()
 .when()
 .get("/api/project/{code}", "ABC1234")
 .then()
 .statusCode(200);
 }

 @Test
 void getProjectWithNonexistentCode() {
 given()
 .when()
 .get("/api/project/{code}", "ZZZ999")
 .then()
 .statusCode(200);
 }

 @Test
 void updateProjectWithValidTitle() {
 String code = createProject();

 given()
 .contentType(ContentType.JSON)
 .body(Map.of("title", uniqueTitle()))
 .when()
 .put("/api/project/{code}", code)
 .then()
 .statusCode(200);
 }

 @Test
 void updateProjectWithMaximumLengthTitle() {
 String code = createProject();

 given()
 .contentType(ContentType.JSON)
 .body(Map.of("title", "B".repeat(200)))
 .when()
 .put("/api/project/{code}", code)
 .then()
 .statusCode(200);
 }

 @Test
 void updateProjectWithEmptyTitle() {
 String code = createProject();

 given()
 .contentType(ContentType.JSON)
 .body(Map.of("title", ""))
 .when()
 .put("/api/project/{code}", code)
 .then()
 .statusCode(200);
 }

 @Test
 void updateProjectWithoutRequiredTitle() {
 String code = createProject();

 given()
 .contentType(ContentType.JSON)
 .body(Map.of())
 .when()
 .put("/api/project/{code}", code)
 .then()
 .statusCode(200);
 }

 @Test
 void updateProjectWithNullTitle() {
 String code = createProject();

 given()
 .contentType(ContentType.JSON)
 .body("{\"title\":null}")
 .when()
 .put("/api/project/{code}", code)
 .then()
 .statusCode(200);
 }

 @Test
 void updateProjectWithExcessivelyLongTitle() {
 String code = createProject();

 given()
 .contentType(ContentType.JSON)
 .body(Map.of("title", "C".repeat(201)))
 .when()
 .put("/api/project/{code}", code)
 .then()
 .statusCode(200);
 }

 @Test
 void updateProjectWithNonexistentCode() {
 given()
 .contentType(ContentType.JSON)
 .body(Map.of("title", uniqueTitle()))
 .when()
 .put("/api/project/{code}", "ZZZ999")
 .then()
 .statusCode(200);
 }

 @Test
 void updateProjectWithOptionalFields() {
 String code = createProject();

 given()
 .contentType(ContentType.JSON)
 .body(Map.of(
 "title", uniqueTitle(),
 "code", code,
 "problem", java.util.List.of()
 ))
 .when()
 .put("/api/project/{code}", code)
 .then()
 .statusCode(200);
 }

 @Test
 void deleteExistingProject() {
 String code = createProject();

 given()
 .when()
 .delete("/api/project/{code}", code)
 .then()
 .statusCode(200);
 }

 @Test
 void deleteNonexistentProject() {
 given()
 .when()
 .delete("/api/project/{code}", "ZZZ999")
 .then()
 .statusCode(200);
 }

 @Test
 void deleteProjectWithInvalidCodeLength() {
 given()
 .when()
 .delete("/api/project/{code}", "ABC")
 .then()
 .statusCode(200);
 }

 @Test
 void getProblemsForExistingProject() {
 String code = createProject();

 given()
 .when()
 .get("/api/problem/{code}", code)
 .then()
 .statusCode(200);
 }

 @Test
 void getProblemsForNonexistentProject() {
 given()
 .when()
 .get("/api/problem/{code}", "ZZZ999")
 .then()
 .statusCode(200);
 }

 @Test
 void getProblemsWithInvalidCodeLength() {
 given()
 .when()
 .get("/api/problem/{code}", "ABC12")
 .then()
 .statusCode(200);
 }

 @Test
 void getProblemsWithLongCode() {
 given()
 .when()
 .get("/api/problem/{code}", "ABC1234")
 .then()
 .statusCode(200);
 }

 @Test
 void createProblemForExistingProject() {
 String code = createProject();

 given()
 .contentType(ContentType.JSON)
 .body(Map.of("title", "Test Problem"))
 .when()
 .post("/api/problem/{code}", code)
 .then()
 .statusCode(200);
 }

 @Test
 void createProblemWithEmptyTitle() {
 String code = createProject();

 given()
 .contentType(ContentType.JSON)
 .body(Map.of("title", ""))
 .when()
 .post("/api/problem/{code}", code)
 .then()
 .statusCode(200);
 }

 @Test
 void createProblemWithoutOptionalFields() {
 String code = createProject();

 given()
 .contentType(ContentType.JSON)
 .body(Map.of())
 .when()
 .post("/api/problem/{code}", code)
 .then()
 .statusCode(200);
 }

 @Test
 void createProblemWithNullTitle() {
 String code = createProject();

 given()
 .contentType(ContentType.JSON)
 .body("{\"title\":null}")
 .when()
 .post("/api/problem/{code}", code)
 .then()
 .statusCode(200);
 }

 @Test
 void createProblemWithAllOptionalFields() {
 String code = createProject();

 given()
 .contentType(ContentType.JSON)
 .body(Map.of(
 "title", "Detailed Problem",
 "problemCode", "PRB123",
 "idx", 1
 ))
 .when()
 .post("/api/problem/{code}", code)
 .then()
 .statusCode(200);
 }

 @Test
 void createProblemForNonexistentProject() {
 given()
 .contentType(ContentType.JSON)
 .body(Map.of("title", "Orphan Problem"))
 .when()
 .post("/api/problem/{code}", "ZZZ999")
 .then()
 .statusCode(200);
 }

 @Test
 void createProblemWithInvalidCodeLength() {
 given()
 .contentType(ContentType.JSON)
 .body(Map.of("title", "Problem"))
 .when()
 .post("/api/problem/{code}", "ABC")
 .then()
 .statusCode(200);
 }

 @Test
 void deleteRecentProblemAfterCreatingIt() {
 String code = createProject();

 given()
 .contentType(ContentType.JSON)
 .body(Map.of("title", "Problem To Delete"))
 .post("/api/problem/{code}", code)
 .then()
 .statusCode(200);

 given()
 .when()
 .delete("/api/problem/{code}", code)
 .then()
 .statusCode(200);
 }

 @Test
 void deleteRecentProblemWithoutExistingProblems() {
 String code = createProject();

 given()
 .when()
 .delete("/api/problem/{code}", code)
 .then()
 .statusCode(200);
 }

 @Test
 void deleteRecentProblemForNonexistentProject() {
 given()
 .when()
 .delete("/api/problem/{code}", "ZZZ999")
 .then()
 .statusCode(200);
 }

 @Test
 void deleteAllProblemsForProject() {
 String code = createProject();

 given()
 .contentType(ContentType.JSON)
 .body(Map.of("title", "First Problem"))
 .post("/api/problem/{code}", code)
 .then()
 .statusCode(200);

 given()
 .contentType(ContentType.JSON)
 .body(Map.of("title", "Second Problem"))
 .post("/api/problem/{code}", code)
 .then()
 .statusCode(200);

 given()
 .when()
 .delete("/api/problem/{code}/all", code)
 .then()
 .statusCode(200);
 }

 @Test
 void deleteAllProblemsWhenNoneExist() {
 String code = createProject();

 given()
 .when()
 .delete("/api/problem/{code}/all", code)
 .then()
 .statusCode(200);
 }

 @Test
 void deleteAllProblemsForNonexistentProject() {
 given()
 .when()
 .delete("/api/problem/{code}/all", "ZZZ999")
 .then()
 .statusCode(200);
 }

 @Test
 void saveSubProblemWithValidContent() {
 given()
 .contentType(ContentType.JSON)
 .body(Map.of(
 "pro_idx", 1,
 "content", "Subproblem content"
 ))
 .when()
 .post("/api/subproblem")
 .then()
 .statusCode(200);
 }

 @Test
 void saveSubProblemWithEmptyContent() {
 given()
 .contentType(ContentType.JSON)
 .body(Map.of(
 "pro_idx", 1,
 "content", ""
 ))
 .when()
 .post("/api/subproblem")
 .then()
 .statusCode(200);
 }

 @Test
 void saveSubProblemWithZeroProblemIndex() {
 given()
 .contentType(ContentType.JSON)
 .body(Map.of(
 "pro_idx", 0,
 "content", "Content"
 ))
 .when()
 .post("/api/subproblem")
 .then()
 .statusCode(200);
 }

 @Test
 void saveSubProblemWithNegativeProblemIndex() {
 given()
 .contentType(ContentType.JSON)
 .body(Map.of(
 "pro_idx", -1,
 "content", "Content"
 ))
 .when()
 .post("/api/subproblem")
 .then()
 .statusCode(200);
 }

 @Test
 void saveSubProblemWithMaximumLongIndex() {
 given()
 .contentType(ContentType.JSON)
 .body(Map.of(
 "pro_idx", Long.MAX_VALUE,
 "content", "Content"
 ))
 .when()
 .post("/api/subproblem")
 .then()
 .statusCode(200);
 }

 @Test
 void saveSubProblemWithoutOptionalFields() {
 given()
 .contentType(ContentType.JSON)
 .body(Map.of())
 .when()
 .post("/api/subproblem")
 .then()
 .statusCode(200);
 }

 @Test
 void saveSubProblemWithNullContent() {
 given()
 .contentType(ContentType.JSON)
 .body("{\"pro_idx\":1,\"content\":null}")
 .when()
 .post("/api/subproblem")
 .then()
 .statusCode(200);
 }

 @Test
 void saveSubProblemWithNullIndex() {
 given()
 .contentType(ContentType.JSON)
 .body("{\"pro_idx\":null,\"content\":\"Content\"}")
 .when()
 .post("/api/subproblem")
 .then()
 .statusCode(200);
 }

 @Test
 void saveSubProblemWithBothFieldsNull() {
 given()
 .contentType(ContentType.JSON)
 .body("{\"pro_idx\":null,\"content\":null}")
 .when()
 .post("/api/subproblem")
 .then()
 .statusCode(200);
 }

 @Test
 void createProjectRetrieveUpdateAndDelete() {
 String code = createProject();

 given()
 .when()
 .get("/api/project/{code}", code)
 .then()
 .statusCode(200);

 given()
 .contentType(ContentType.JSON)
 .body(Map.of("title", "Updated Integration Project"))
 .when()
 .put("/api/project/{code}", code)
 .then()
 .statusCode(200);

 given()
 .when()
 .get("/api/project/{code}", code)
 .then()
 .statusCode(200);

 given()
 .when()
 .delete("/api/project/{code}", code)
 .then()
 .statusCode(200);
 }

 @Test
 void createProjectAddRetrieveAndDeleteProblems() {
 String code = createProject();

 given()
 .contentType(ContentType.JSON)
 .body(Map.of("title", "Integration Problem"))
 .when()
 .post("/api/problem/{code}", code)
 .then()
 .statusCode(200);

 given()
 .when()
 .get("/api/problem/{code}", code)
 .then()
 .statusCode(200);

 given()
 .when()
 .delete("/api/problem/{code}", code)
 .then()
 .statusCode(200);

 given()
 .when()
 .get("/api/problem/{code}", code)
 .then()
 .statusCode(200);
 }

 @Test
 void createProjectAddMultipleProblemsAndDeleteAll() {
 String code = createProject();

 for (int i = 0; i < 3; i++) {
 given()
 .contentType(ContentType.JSON)
 .body(Map.of("title", "Problem " + i))
 .when()
 .post("/api/problem/{code}", code)
 .then()
 .statusCode(200);
 }

 given()
 .when()
 .delete("/api/problem/{code}/all", code)
 .then()
 .statusCode(200);

 given()
 .when()
 .get("/api/problem/{code}", code)
 .then()
 .statusCode(200);
 }

 @Test
 void createAndDeleteProjectWithProblems() {
 String code = createProject();

 given()
 .contentType(ContentType.JSON)
 .body(Map.of("title", "Problem Before Project Deletion"))
 .post("/api/problem/{code}", code)
 .then()
 .statusCode(200);

 given()
 .when()
 .delete("/api/project/{code}", code)
 .then()
 .statusCode(200);
 }
}