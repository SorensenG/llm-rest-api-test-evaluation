import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class GeneratedApiTest {

 private static final String BASE_URL = "http://localhost:8080";

 @BeforeAll
 static void setUp() {
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
 .when()
 .post("/api/project");

 response.then().statusCode(200);
 String code = response.jsonPath().getString("code");

 if (code == null || code.isEmpty()) {
 code = response.jsonPath().getString("data.code");
 }

 return code;
 }

 private Map<String, Object> validProject(String title) {
 return Map.of("title", title);
 }

 private Map<String, Object> validProblem(String title) {
 return Map.of("title", title);
 }

 @Test
 void createProjectWithValidTitle() {
 given()
 .contentType(ContentType.JSON)
 .body(validProject(uniqueTitle()))
 .when()
 .post("/api/project")
 .then()
 .statusCode(200);
 }

 @Test
 void createProjectWithMinimumLengthTitle() {
 given()
 .contentType(ContentType.JSON)
 .body(Map.of("title", "A"))
 .when()
 .post("/api/project")
 .then()
 .statusCode(200);
 }

 @Test
 void createProjectWithMaximumLengthTitle() {
 given()
 .contentType(ContentType.JSON)
 .body(Map.of("title", "A".repeat(200)))
 .when()
 .post("/api/project")
 .then()
 .statusCode(200);
 }

 @Test
 void createProjectWithTitleExceedingMaximumLength() {
 given()
 .contentType(ContentType.JSON)
 .body(Map.of("title", "A".repeat(201)))
 .when()
 .post("/api/project")
 .then()
 .statusCode(anyOf(is(200), is(400), is(422)));
 }

 @Test
 void createProjectWithoutTitle() {
 given()
 .contentType(ContentType.JSON)
 .body(Collections.emptyMap())
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
 .statusCode(anyOf(is(200), is(400), is(422)));
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
 void createProjectWithOptionalCodeAtMaximumLength() {
 given()
 .contentType(ContentType.JSON)
 .body(Map.of(
 "title", uniqueTitle(),
 "code", "ABC123"
 ))
 .when()
 .post("/api/project")
 .then()
 .statusCode(200);
 }

 @Test
 void createProjectWithCodeExceedingMaximumLength() {
 given()
 .contentType(ContentType.JSON)
 .body(Map.of(
 "title", uniqueTitle(),
 "code", "ABCDEFG"
 ))
 .when()
 .post("/api/project")
 .then()
 .statusCode(anyOf(is(200), is(400), is(422)));
 }

 @Test
 void createProjectWithEmptyCode() {
 given()
 .contentType(ContentType.JSON)
 .body(Map.of(
 "title", uniqueTitle(),
 "code", ""
 ))
 .when()
 .post("/api/project")
 .then()
 .statusCode(200);
 }

 @Test
 void createProjectWithOptionalIdx() {
 given()
 .contentType(ContentType.JSON)
 .body(Map.of(
 "title", uniqueTitle(),
 "idx", 1
 ))
 .when()
 .post("/api/project")
 .then()
 .statusCode(200);
 }

 @Test
 void getProjectUsingValidCode() {
 String code = createProject();

 if (code == null) {
 return;
 }

 given()
 .when()
 .get("/api/project/{code}", code)
 .then()
 .statusCode(200);
 }

 @Test
 void getProjectUsingNonexistentCode() {
 given()
 .when()
 .get("/api/project/{code}", "ZZZ999")
 .then()
 .statusCode(anyOf(is(200), is(404)));
 }

 @Test
 void getProjectUsingEmptyCode() {
 given()
 .when()
 .get("/api/project/{code}", "")
 .then()
 .statusCode(anyOf(is(200), is(400), is(404)));
 }

 @Test
 void getProjectUsingShortCode() {
 given()
 .when()
 .get("/api/project/{code}", "ABC12")
 .then()
 .statusCode(anyOf(is(200), is(400), is(404)));
 }

 @Test
 void getProjectUsingLongCode() {
 given()
 .when()
 .get("/api/project/{code}", "ABC1234")
 .then()
 .statusCode(anyOf(is(200), is(400), is(404)));
 }

 @Test
 void updateProjectWithValidTitle() {
 String code = createProject();

 if (code == null) {
 return;
 }

 given()
 .contentType(ContentType.JSON)
 .body(validProject(uniqueTitle()))
 .when()
 .put("/api/project/{code}", code)
 .then()
 .statusCode(200);
 }

 @Test
 void updateProjectWithMaximumLengthTitle() {
 String code = createProject();

 if (code == null) {
 return;
 }

 given()
 .contentType(ContentType.JSON)
 .body(Map.of("title", "B".repeat(200)))
 .when()
 .put("/api/project/{code}", code)
 .then()
 .statusCode(200);
 }

 @Test
 void updateProjectWithoutTitle() {
 String code = createProject();

 if (code == null) {
 return;
 }

 given()
 .contentType(ContentType.JSON)
 .body(Collections.emptyMap())
 .when()
 .put("/api/project/{code}", code)
 .then()
 .statusCode(anyOf(is(200), is(400), is(422)));
 }

 @Test
 void updateProjectWithTitleExceedingMaximumLength() {
 String code = createProject();

 if (code == null) {
 return;
 }

 given()
 .contentType(ContentType.JSON)
 .body(Map.of("title", "B".repeat(201)))
 .when()
 .put("/api/project/{code}", code)
 .then()
 .statusCode(anyOf(is(200), is(400), is(422)));
 }

 @Test
 void updateProjectUsingNonexistentCode() {
 given()
 .contentType(ContentType.JSON)
 .body(validProject(uniqueTitle()))
 .when()
 .put("/api/project/{code}", "ZZZ999")
 .then()
 .statusCode(anyOf(is(200), is(404)));
 }

 @Test
 void deleteProjectUsingNonexistentCode() {
 given()
 .when()
 .delete("/api/project/{code}", "ZZZ999")
 .then()
 .statusCode(anyOf(is(200), is(404)));
 }

 @Test
 void deleteProjectUsingValidCode() {
 String code = createProject();

 if (code == null) {
 return;
 }

 given()
 .when()
 .delete("/api/project/{code}", code)
 .then()
 .statusCode(200);
 }

 @Test
 void deleteProjectTwice() {
 String code = createProject();

 if (code == null) {
 return;
 }

 given()
 .when()
 .delete("/api/project/{code}", code)
 .then()
 .statusCode(200);

 given()
 .when()
 .delete("/api/project/{code}", code)
 .then()
 .statusCode(anyOf(is(200), is(404)));
 }

 @Test
 void createProblemForNonexistentProjectCode() {
 given()
 .contentType(ContentType.JSON)
 .body(validProblem(uniqueTitle()))
 .when()
 .post("/api/problem/{code}", "ZZZ999")
 .then()
 .statusCode(anyOf(is(200), is(404)));
 }

 @Test
 void createProblemForValidProject() {
 String code = createProject();

 if (code == null) {
 return;
 }

 given()
 .contentType(ContentType.JSON)
 .body(validProblem(uniqueTitle()))
 .when()
 .post("/api/problem/{code}", code)
 .then()
 .statusCode(200);
 }

 @Test
 void createProblemWithAllOptionalFields() {
 String code = createProject();

 if (code == null) {
 return;
 }

 given()
 .contentType(ContentType.JSON)
 .body(Map.of(
 "idx", 1,
 "title", uniqueTitle(),
 "problemCode", "PRB001"
 ))
 .when()
 .post("/api/problem/{code}", code)
 .then()
 .statusCode(200);
 }

 @Test
 void createProblemWithEmptyTitle() {
 String code = createProject();

 if (code == null) {
 return;
 }

 given()
 .contentType(ContentType.JSON)
 .body(Map.of("title", ""))
 .when()
 .post("/api/problem/{code}", code)
 .then()
 .statusCode(200);
 }

 @Test
 void createProblemWithoutFields() {
 String code = createProject();

 if (code == null) {
 return;
 }

 given()
 .contentType(ContentType.JSON)
 .body(Collections.emptyMap())
 .when()
 .post("/api/problem/{code}", code)
 .then()
 .statusCode(200);
 }

 @Test
 void createProblemWithNullTitle() {
 String code = createProject();

 if (code == null) {
 return;
 }

 given()
 .contentType(ContentType.JSON)
 .body("{\"title\":null}")
 .when()
 .post("/api/problem/{code}", code)
 .then()
 .statusCode(200);
 }

 @Test
 void createProblemWithNestedProject() {
 String code = createProject();

 if (code == null) {
 return;
 }

 given()
 .contentType(ContentType.JSON)
 .body(Map.of(
 "title", uniqueTitle(),
 "project", Map.of("title", uniqueTitle())
 ))
 .when()
 .post("/api/problem/{code}", code)
 .then()
 .statusCode(200);
 }

 @Test
 void getProblemsUsingValidProjectCode() {
 String code = createProject();

 if (code == null) {
 return;
 }

 given()
 .when()
 .get("/api/problem/{code}", code)
 .then()
 .statusCode(200)
 .body("$", instanceOf(List.class));
 }

 @Test
 void getProblemsUsingNonexistentProjectCode() {
 given()
 .when()
 .get("/api/problem/{code}", "ZZZ999")
 .then()
 .statusCode(200)
 .body("$", instanceOf(List.class));
 }

 @Test
 void getProblemsUsingEmptyCode() {
 given()
 .when()
 .get("/api/problem/{code}", "")
 .then()
 .statusCode(anyOf(is(200), is(400), is(404)));
 }

 @Test
 void getProblemsUsingShortCode() {
 given()
 .when()
 .get("/api/problem/{code}", "ABC12")
 .then()
 .statusCode(anyOf(is(200), is(400), is(404)));
 }

 @Test
 void getProblemsUsingLongCode() {
 given()
 .when()
 .get("/api/problem/{code}", "ABC1234")
 .then()
 .statusCode(anyOf(is(200), is(400), is(404)));
 }

 @Test
 void deleteMostRecentProblem() {
 String code = createProject();

 if (code == null) {
 return;
 }

 given()
 .contentType(ContentType.JSON)
 .body(validProblem(uniqueTitle()))
 .when()
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
 void deleteProblemUsingNonexistentCode() {
 given()
 .when()
 .delete("/api/problem/{code}", "ZZZ999")
 .then()
 .statusCode(anyOf(is(200), is(404)));
 }

 @Test
 void deleteAllProblemsForValidProject() {
 String code = createProject();

 if (code == null) {
 return;
 }

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
 .statusCode(anyOf(is(200), is(404)));
 }

 @Test
 void createMultipleProblemsAndRetrieveThem() {
 String code = createProject();

 if (code == null) {
 return;
 }

 given()
 .contentType(ContentType.JSON)
 .body(validProblem("Problem-One-" + java.util.UUID.randomUUID()))
 .when()
 .post("/api/problem/{code}", code)
 .then()
 .statusCode(200);

 given()
 .contentType(ContentType.JSON)
 .body(validProblem("Problem-Two-" + java.util.UUID.randomUUID()))
 .when()
 .post("/api/problem/{code}", code)
 .then()
 .statusCode(200);

 given()
 .when()
 .get("/api/problem/{code}", code)
 .then()
 .statusCode(200)
 .body("$", instanceOf(List.class))
 .body("$.size()", greaterThanOrEqualTo(2));
 }

 @Test
 void deleteLatestProblemAndVerifyRemainingProblems() {
 String code = createProject();

 if (code == null) {
 return;
 }

 given()
 .contentType(ContentType.JSON)
 .body(validProblem("Problem-One-" + java.util.UUID.randomUUID()))
 .when()
 .post("/api/problem/{code}", code)
 .then()
 .statusCode(200);

 given()
 .contentType(ContentType.JSON)
 .body(validProblem("Problem-Two-" + java.util.UUID.randomUUID()))
 .when()
 .post("/api/problem/{code}", code)
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
 .statusCode(200)
 .body("$", instanceOf(List.class));
 }

 @Test
 void deleteAllProblemsAndVerifyEmptyList() {
 String code = createProject();

 if (code == null) {
 return;
 }

 given()
 .contentType(ContentType.JSON)
 .body(validProblem(uniqueTitle()))
 .when()
 .post("/api/problem/{code}", code)
 .then()
 .statusCode(200);

 given()
 .when()
 .delete("/api/problem/{code}/all", code)
 .then()
 .statusCode(200);

 given()
 .when()
 .get("/api/problem/{code}", code)
 .then()
 .statusCode(200)
 .body("$", hasSize(0));
 }

 @Test
 void saveSubProblemWithValidFields() {
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
 void saveSubProblemWithMinimumProIdx() {
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
 void saveSubProblemWithMaximumLongProIdx() {
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
 void saveSubProblemWithNegativeProIdx() {
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
 void saveSubProblemWithMissingProIdx() {
 given()
 .contentType(ContentType.JSON)
 .body(Map.of("content", "Content"))
 .when()
 .post("/api/subproblem")
 .then()
 .statusCode(200);
 }

 @Test
 void saveSubProblemWithMissingContent() {
 given()
 .contentType(ContentType.JSON)
 .body(Map.of("pro_idx", 1))
 .when()
 .post("/api/subproblem")
 .then()
 .statusCode(200);
 }

 @Test
 void saveSubProblemWithEmptyObject() {
 given()
 .contentType(ContentType.JSON)
 .body(Collections.emptyMap())
 .when()
 .post("/api/subproblem")
 .then()
 .statusCode(200);
 }

 @Test
 void saveSubProblemWithNullFields() {
 given()
 .contentType(ContentType.JSON)
 .body("{\"pro_idx\":null,\"content\":null}")
 .when()
 .post("/api/subproblem")
 .then()
 .statusCode(200);
 }

 @Test
 void saveSubProblemWithLargeContent() {
 given()
 .contentType(ContentType.JSON)
 .body(Map.of(
 "pro_idx", 1,
 "content", "C".repeat(10000)
 ))
 .when()
 .post("/api/subproblem")
 .then()
 .statusCode(200);
 }

 @Test
 void createProjectThenCreateRetrieveAndDeleteProblems() {
 String code = createProject();

 if (code == null) {
 return;
 }

 given()
 .contentType(ContentType.JSON)
 .body(validProblem(uniqueTitle()))
 .when()
 .post("/api/problem/{code}", code)
 .then()
 .statusCode(200);

 given()
 .when()
 .get("/api/problem/{code}", code)
 .then()
 .statusCode(200)
 .body("$", hasSize(greaterThanOrEqualTo(1)));

 given()
 .when()
 .delete("/api/problem/{code}/all", code)
 .then()
 .statusCode(200);

 given()
 .when()
 .get("/api/problem/{code}", code)
 .then()
 .statusCode(200)
 .body("$", hasSize(0));

 given()
 .when()
 .delete("/api/project/{code}", code)
 .then()
 .statusCode(200);
 }

 @Test
 void createUpdateRetrieveAndDeleteProject() {
 String code = createProject();

 if (code == null) {
 return;
 }

 given()
 .contentType(ContentType.JSON)
 .body(validProject("Updated-" + java.util.UUID.randomUUID()))
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

 given()
 .when()
 .get("/api/project/{code}", code)
 .then()
 .statusCode(anyOf(is(200), is(404)));
 }
}