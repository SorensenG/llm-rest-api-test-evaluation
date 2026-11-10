import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class GeneratedApiTest {

 static {
 RestAssured.baseURI = "http://localhost:8080";
 }

 private Map<String, Object> project(String title) {
 return Map.of("title", title);
 }

 private Map<String, Object> problem(String title) {
 return Map.of("title", title);
 }

 @Test
 void createProjectWithValidTitle() {
 given()
 .contentType(ContentType.JSON)
 .body(project("Integration Test Project"))
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
 .body(project(""))
 .when()
 .post("/api/project")
 .then()
 .statusCode(200);
 }

 @Test
 void createProjectWithMaximumLengthTitle() {
 String title = "a".repeat(200);

 given()
 .contentType(ContentType.JSON)
 .body(project(title))
 .when()
 .post("/api/project")
 .then()
 .statusCode(200);
 }

 @Test
 void createProjectWithTitleExceedingMaximumLength() {
 String title = "a".repeat(201);

 given()
 .contentType(ContentType.JSON)
 .body(project(title))
 .when()
 .post("/api/project")
 .then()
 .statusCode(anyOf(is(200), is(400), is(422)));
 }

 @Test
 void createProjectWithoutRequiredTitle() {
 given()
 .contentType(ContentType.JSON)
 .body(Map.of())
 .when()
 .post("/api/project")
 .then()
 .statusCode(anyOf(is(200), is(400), is(422)));
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
 void createProjectWithOptionalFields() {
 given()
 .contentType(ContentType.JSON)
 .body(Map.of(
 "title", "Project With Optional Fields",
 "problem", List.of()
 ))
 .when()
 .post("/api/project")
 .then()
 .statusCode(200);
 }

 @Test
 void createProjectWithEmptyProblemArray() {
 given()
 .contentType(ContentType.JSON)
 .body(Map.of(
 "title", "Empty Problem List",
 "problem", List.of()
 ))
 .when()
 .post("/api/project")
 .then()
 .statusCode(200);
 }

 @Test
 void createProjectWithDuplicateProblems() {
 Map<String, Object> duplicateProblem = problem("Duplicate Problem");

 given()
 .contentType(ContentType.JSON)
 .body(Map.of(
 "title", "Project With Duplicate Problems",
 "problem", List.of(duplicateProblem, duplicateProblem)
 ))
 .when()
 .post("/api/project")
 .then()
 .statusCode(anyOf(is(200), is(400), is(422)));
 }

 @Test
 void createProjectWithCodeAtMaximumLength() {
 given()
 .contentType(ContentType.JSON)
 .body(Map.of(
 "title", "Project With Code",
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
 "title", "Project With Long Code",
 "code", "ABC1234"
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
 "title", "Project With Empty Code",
 "code", ""
 ))
 .when()
 .post("/api/project")
 .then()
 .statusCode(200);
 }

 @Test
 void getProjectWithValidLengthCode() {
 given()
 .when()
 .get("/api/project/ABC123")
 .then()
 .statusCode(200);
 }

 @Test
 void getProjectWithMinimumLengthCode() {
 given()
 .when()
 .get("/api/project/")
 .then()
 .statusCode(anyOf(is(200), is(404), is(405)));
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

 @Test
 void getProjectWithAlphanumericCode() {
 given()
 .when()
 .get("/api/project/A1B2C3")
 .then()
 .statusCode(200);
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
 void updateProjectWithValidTitle() {
 given()
 .contentType(ContentType.JSON)
 .body(project("Updated Project"))
 .when()
 .put("/api/project/ABC123")
 .then()
 .statusCode(200);
 }

 @Test
 void updateProjectWithEmptyTitle() {
 given()
 .contentType(ContentType.JSON)
 .body(project(""))
 .when()
 .put("/api/project/ABC123")
 .then()
 .statusCode(200);
 }

 @Test
 void updateProjectWithMaximumLengthTitle() {
 given()
 .contentType(ContentType.JSON)
 .body(project("a".repeat(200)))
 .when()
 .put("/api/project/ABC123")
 .then()
 .statusCode(200);
 }

 @Test
 void updateProjectWithoutTitle() {
 given()
 .contentType(ContentType.JSON)
 .body(Map.of())
 .when()
 .put("/api/project/ABC123")
 .then()
 .statusCode(anyOf(is(200), is(400), is(422)));
 }

 @Test
 void updateProjectWithNullTitle() {
 given()
 .contentType(ContentType.JSON)
 .body("{\"title\":null}")
 .when()
 .put("/api/project/ABC123")
 .then()
 .statusCode(anyOf(is(200), is(400), is(422)));
 }

 @Test
 void updateProjectWithUnknownCode() {
 given()
 .contentType(ContentType.JSON)
 .body(project("Unknown Project"))
 .when()
 .put("/api/project/ZZZ999")
 .then()
 .statusCode(anyOf(is(200), is(404)));
 }

 @Test
 void updateProjectWithEmptyBody() {
 given()
 .contentType(ContentType.JSON)
 .when()
 .put("/api/project/ABC123")
 .then()
 .statusCode(anyOf(is(400), is(415), is(422)));
 }

 @Test
 void deleteProjectWithValidLengthCode() {
 given()
 .when()
 .delete("/api/project/ABC123")
 .then()
 .statusCode(200);
 }

 @Test
 void deleteProjectWithUnknownCode() {
 given()
 .when()
 .delete("/api/project/ZZZ999")
 .then()
 .statusCode(200);
 }

 @Test
 void deleteProjectWithShortCode() {
 given()
 .when()
 .delete("/api/project/ABC12")
 .then()
 .statusCode(200);
 }

 @Test
 void deleteProjectWithLongCode() {
 given()
 .when()
 .delete("/api/project/ABC1234")
 .then()
 .statusCode(200);
 }

 @Test
 void getProblemByValidLengthCode() {
 given()
 .when()
 .get("/api/problem/ABC123")
 .then()
 .statusCode(200)
 .contentType(ContentType.JSON)
 .body("$", instanceOf(List.class));
 }

 @Test
 void getProblemByUnknownCode() {
 given()
 .when()
 .get("/api/problem/ZZZ999")
 .then()
 .statusCode(200)
 .body("$", instanceOf(List.class));
 }

 @Test
 void getProblemByShortCode() {
 given()
 .when()
 .get("/api/problem/ABC12")
 .then()
 .statusCode(anyOf(is(200), is(400), is(404)));
 }

 @Test
 void getProblemByLongCode() {
 given()
 .when()
 .get("/api/problem/ABC1234")
 .then()
 .statusCode(anyOf(is(200), is(400), is(404)));
 }

 @Test
 void getProblemByAlphanumericCode() {
 given()
 .when()
 .get("/api/problem/A1B2C3")
 .then()
 .statusCode(200);
 }

 @Test
 void saveProblemWithValidTitle() {
 given()
 .contentType(ContentType.JSON)
 .body(problem("Integration Test Problem"))
 .when()
 .post("/api/problem/ABC123")
 .then()
 .statusCode(200);
 }

 @Test
 void saveProblemWithEmptyTitle() {
 given()
 .contentType(ContentType.JSON)
 .body(problem(""))
 .when()
 .post("/api/problem/ABC123")
 .then()
 .statusCode(200);
 }

 @Test
 void saveProblemWithOptionalFields() {
 given()
 .contentType(ContentType.JSON)
 .body(Map.of(
 "title", "Problem With Optional Fields",
 "problemCode", "PRB123"
 ))
 .when()
 .post("/api/problem/ABC123")
 .then()
 .statusCode(200);
 }

 @Test
 void saveProblemWithNestedProject() {
 given()
 .contentType(ContentType.JSON)
 .body(Map.of(
 "title", "Problem With Nested Project",
 "project", project("Nested Project")
 ))
 .when()
 .post("/api/problem/ABC123")
 .then()
 .statusCode(200);
 }

 @Test
 void saveProblemWithIntegerIndex() {
 given()
 .contentType(ContentType.JSON)
 .body(Map.of(
 "idx", 1L,
 "title", "Problem With Index"
 ))
 .when()
 .post("/api/problem/ABC123")
 .then()
 .statusCode(200);
 }

 @Test
 void saveProblemWithNegativeIndex() {
 given()
 .contentType(ContentType.JSON)
 .body(Map.of(
 "idx", -1L,
 "title", "Problem With Negative Index"
 ))
 .when()
 .post("/api/problem/ABC123")
 .then()
 .statusCode(200);
 }

 @Test
 void saveProblemWithMissingTitle() {
 given()
 .contentType(ContentType.JSON)
 .body(Map.of())
 .when()
 .post("/api/problem/ABC123")
 .then()
 .statusCode(200);
 }

 @Test
 void saveProblemWithNullTitle() {
 given()
 .contentType(ContentType.JSON)
 .body("{\"title\":null}")
 .when()
 .post("/api/problem/ABC123")
 .then()
 .statusCode(200);
 }

 @Test
 void saveProblemWithEmptyBody() {
 given()
 .contentType(ContentType.JSON)
 .when()
 .post("/api/problem/ABC123")
 .then()
 .statusCode(anyOf(is(400), is(415), is(422)));
 }

 @Test
 void saveProblemWithUnknownProjectCode() {
 given()
 .contentType(ContentType.JSON)
 .body(problem("Problem For Unknown Project"))
 .when()
 .post("/api/problem/ZZZ999")
 .then()
 .statusCode(200);
 }

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
 .statusCode(200);
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
 .statusCode(200);
 }

 @Test
 void deleteAllProblemsWithShortCode() {
 given()
 .when()
 .delete("/api/problem/ABC12/all")
 .then()
 .statusCode(200);
 }

 @Test
 void deleteAllProblemsWithLongCode() {
 given()
 .when()
 .delete("/api/problem/ABC1234/all")
 .then()
 .statusCode(200);
 }

 @Test
 void saveSubProblemWithValidFields() {
 given()
 .contentType(ContentType.JSON)
 .body(Map.of(
 "pro_idx", 1L,
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
 "pro_idx", 1L,
 "content", ""
 ))
 .when()
 .post("/api/subproblem")
 .then()
 .statusCode(200);
 }

 @Test
 void saveSubProblemWithZeroIndex() {
 given()
 .contentType(ContentType.JSON)
 .body(Map.of(
 "pro_idx", 0L,
 "content", "Subproblem with zero index"
 ))
 .when()
 .post("/api/subproblem")
 .then()
 .statusCode(200);
 }

 @Test
 void saveSubProblemWithNegativeIndex() {
 given()
 .contentType(ContentType.JSON)
 .body(Map.of(
 "pro_idx", -1L,
 "content", "Subproblem with negative index"
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
 "content", "Subproblem with maximum index"
 ))
 .when()
 .post("/api/subproblem")
 .then()
 .statusCode(200);
 }

 @Test
 void saveSubProblemWithMissingIndex() {
 given()
 .contentType(ContentType.JSON)
 .body(Map.of("content", "Subproblem without index"))
 .when()
 .post("/api/subproblem")
 .then()
 .statusCode(200);
 }

 @Test
 void saveSubProblemWithMissingContent() {
 given()
 .contentType(ContentType.JSON)
 .body(Map.of("pro_idx", 1L))
 .when()
 .post("/api/subproblem")
 .then()
 .statusCode(200);
 }

 @Test
 void saveSubProblemWithEmptyObject() {
 given()
 .contentType(ContentType.JSON)
 .body(Map.of())
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
 void saveSubProblemWithEmptyBody() {
 given()
 .contentType(ContentType.JSON)
 .when()
 .post("/api/subproblem")
 .then()
 .statusCode(anyOf(is(400), is(415), is(422)));
 }

 @Test
 void createProjectThenRetrieveItUsingSuppliedCode() {
 Response response =
 given()
 .contentType(ContentType.JSON)
 .body(Map.of(
 "title", "Create And Retrieve Project",
 "code", "TST001"
 ))
 .when()
 .post("/api/project");

 response.then().statusCode(200);

 given()
 .when()
 .get("/api/project/TST001")
 .then()
 .statusCode(200);
 }

 @Test
 void createProjectThenUpdateIt() {
 given()
 .contentType(ContentType.JSON)
 .body(Map.of(
 "title", "Project Before Update",
 "code", "TST002"
 ))
 .when()
 .post("/api/project")
 .then()
 .statusCode(200);

 given()
 .contentType(ContentType.JSON)
 .body(project("Project After Update"))
 .when()
 .put("/api/project/TST002")
 .then()
 .statusCode(200);
 }

 @Test
 void createProjectThenDeleteIt() {
 given()
 .contentType(ContentType.JSON)
 .body(Map.of(
 "title", "Project To Delete",
 "code", "TST003"
 ))
 .when()
 .post("/api/project")
 .then()
 .statusCode(200);

 given()
 .when()
 .delete("/api/project/TST003")
 .then()
 .statusCode(200);
 }

 @Test
 void saveProblemThenRetrieveProblemsForCode() {
 given()
 .contentType(ContentType.JSON)
 .body(problem("Problem To Retrieve"))
 .when()
 .post("/api/problem/TST004")
 .then()
 .statusCode(200);

 given()
 .when()
 .get("/api/problem/TST004")
 .then()
 .statusCode(200)
 .body("$", instanceOf(List.class));
 }

 @Test
 void saveMultipleProblemsThenDeleteMostRecent() {
 given()
 .contentType(ContentType.JSON)
 .body(problem("First Problem"))
 .when()
 .post("/api/problem/TST005")
 .then()
 .statusCode(200);

 given()
 .contentType(ContentType.JSON)
 .body(problem("Second Problem"))
 .when()
 .post("/api/problem/TST005")
 .then()
 .statusCode(200);

 given()
 .when()
 .delete("/api/problem/TST005")
 .then()
 .statusCode(200);
 }

 @Test
 void saveMultipleProblemsThenDeleteAll() {
 given()
 .contentType(ContentType.JSON)
 .body(problem("First Problem"))
 .when()
 .post("/api/problem/TST006")
 .then()
 .statusCode(200);

 given()
 .contentType(ContentType.JSON)
 .body(problem("Second Problem"))
 .when()
 .post("/api/problem/TST006")
 .then()
 .statusCode(200);

 given()
 .when()
 .delete("/api/problem/TST006/all")
 .then()
 .statusCode(200);
 }

 @Test
 void saveProblemThenDeleteAllAndRetrieveEmptyList() {
 given()
 .contentType(ContentType.JSON)
 .body(problem("Problem To Remove"))
 .when()
 .post("/api/problem/TST007")
 .then()
 .statusCode(200);

 given()
 .when()
 .delete("/api/problem/TST007/all")
 .then()
 .statusCode(200);

 given()
 .when()
 .get("/api/problem/TST007")
 .then()
 .statusCode(200)
 .body("$", instanceOf(List.class));
 }

 @Test
 void createProjectThenSaveProblemForItsCode() {
 given()
 .contentType(ContentType.JSON)
 .body(Map.of(
 "title", "Project With Problem",
 "code", "TST008"
 ))
 .when()
 .post("/api/project")
 .then()
 .statusCode(200);

 given()
 .contentType(ContentType.JSON)
 .body(problem("Associated Problem"))
 .when()
 .post("/api/problem/TST008")
 .then()
 .statusCode(200);

 given()
 .when()
 .get("/api/problem/TST008")
 .then()
 .statusCode(200);
 }

 @Test
 void createProjectSaveSubProblemAndDeleteProject() {
 given()
 .contentType(ContentType.JSON)
 .body(Map.of(
 "title", "Project For Subproblem",
 "code", "TST009"
 ))
 .when()
 .post("/api/project")
 .then()
 .statusCode(200);

 given()
 .contentType(ContentType.JSON)
 .body(problem("Problem For Subproblem"))
 .when()
 .post("/api/problem/TST009")
 .then()
 .statusCode(200);

 given()
 .contentType(ContentType.JSON)
 .body(Map.of(
 "pro_idx", 1L,
 "content", "Associated subproblem"
 ))
 .when()
 .post("/api/subproblem")
 .then()
 .statusCode(200);

 given()
 .when()
 .delete("/api/project/TST009")
 .then()
 .statusCode(200);
 }
}

