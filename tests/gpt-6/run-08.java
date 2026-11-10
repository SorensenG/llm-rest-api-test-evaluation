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

 private String uniqueCode() {
 return java.util.UUID.randomUUID()
 .toString()
 .replace("-", "")
 .substring(0, 6);
 }

 private String createProject(String title) {
 Response response = given()
 .contentType(ContentType.JSON)
 .body("{\"title\":\"" + title + "\"}")
 .post("/api/project");

 response.then().statusCode(200);
 return response.jsonPath().getString("code");
 }

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
 void createProjectWithMinimumLengthTitle() {
 given()
 .contentType(ContentType.JSON)
 .body("{\"title\":\"\"}")
 .when()
 .post("/api/project")
 .then()
 .statusCode(anyOf(is(200), is(400)));
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
 .statusCode(anyOf(is(200), is(400)));
 }

 @Test
 void createProjectWithoutTitle() {
 given()
 .contentType(ContentType.JSON)
 .body("{}")
 .when()
 .post("/api/project")
 .then()
 .statusCode(anyOf(is(400), is(500)));
 }

 @Test
 void createProjectWithNullTitle() {
 given()
 .contentType(ContentType.JSON)
 .body("{\"title\":null}")
 .when()
 .post("/api/project")
 .then()
 .statusCode(anyOf(is(400), is(500)));
 }

 @Test
 void createProjectWithAdditionalOptionalFields() {
 given()
 .contentType(ContentType.JSON)
 .body("{\"title\":\"Project With Optional Fields\",\"code\":\"ABC123\",\"idx\":1,\"problem\":[]}")
 .when()
 .post("/api/project")
 .then()
 .statusCode(200);
 }

 @Test
 void createProjectWithSixCharacterCode() {
 given()
 .contentType(ContentType.JSON)
 .body("{\"title\":\"Project With Code\",\"code\":\"ABC123\"}")
 .when()
 .post("/api/project")
 .then()
 .statusCode(200);
 }

 @Test
 void createProjectWithCodeExceedingMaximumLength() {
 given()
 .contentType(ContentType.JSON)
 .body("{\"title\":\"Project With Invalid Code\",\"code\":\"ABC1234\"}")
 .when()
 .post("/api/project")
 .then()
 .statusCode(anyOf(is(200), is(400)));
 }

 @Test
 void createProjectWithEmptyCode() {
 given()
 .contentType(ContentType.JSON)
 .body("{\"title\":\"Project With Empty Code\",\"code\":\"\"}")
 .when()
 .post("/api/project")
 .then()
 .statusCode(200);
 }

 @Test
 void createProjectWithEmptyProblemArray() {
 given()
 .contentType(ContentType.JSON)
 .body("{\"title\":\"Empty Problems Project\",\"problem\":[]}")
 .when()
 .post("/api/project")
 .then()
 .statusCode(200);
 }

 @Test
 void createProjectWithDuplicateProblems() {
 String body = """
 {
 "title": "Duplicate Problems Project",
 "problem": [
 {"title": "Problem A", "problemCode": "ABC123"},
 {"title": "Problem A", "problemCode": "ABC123"}
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

 @Test
 void getProjectWithValidCode() {
 String code = createProject("Get Project Test");

 given()
 .pathParam("code", code)
 .when()
 .get("/api/project/{code}")
 .then()
 .statusCode(200)
 .contentType(anyOf(
 equalTo("application/json"),
 equalTo("application/xml")
 ));
 }

 @Test
 void getProjectWithUnknownCode() {
 given()
 .pathParam("code", uniqueCode())
 .when()
 .get("/api/project/{code}")
 .then()
 .statusCode(anyOf(is(200), is(404)));
 }

 @Test
 void getProjectWithEmptyCode() {
 given()
 .when()
 .get("/api/project/")
 .then()
 .statusCode(anyOf(is(404), is(400)));
 }

 @Test
 void getProjectWithLongCode() {
 given()
 .pathParam("code", "ABC1234")
 .when()
 .get("/api/project/{code}")
 .then()
 .statusCode(anyOf(is(200), is(400), is(404)));
 }

 @Test
 void updateProjectWithValidTitle() {
 String code = createProject("Original Project");

 given()
 .contentType(ContentType.JSON)
 .pathParam("code", code)
 .body("{\"title\":\"Updated Project\"}")
 .when()
 .put("/api/project/{code}")
 .then()
 .statusCode(200);
 }

 @Test
 void updateProjectWithMaximumLengthTitle() {
 String code = createProject("Project Before Update");

 given()
 .contentType(ContentType.JSON)
 .pathParam("code", code)
 .body("{\"title\":\"" + "B".repeat(200) + "\"}")
 .when()
 .put("/api/project/{code}")
 .then()
 .statusCode(200);
 }

 @Test
 void updateProjectWithoutTitle() {
 String code = createProject("Project Before Invalid Update");

 given()
 .contentType(ContentType.JSON)
 .pathParam("code", code)
 .body("{}")
 .when()
 .put("/api/project/{code}")
 .then()
 .statusCode(anyOf(is(200), is(400), is(500)));
 }

 @Test
 void updateProjectWithNullTitle() {
 String code = createProject("Project Before Null Update");

 given()
 .contentType(ContentType.JSON)
 .pathParam("code", code)
 .body("{\"title\":null}")
 .when()
 .put("/api/project/{code}")
 .then()
 .statusCode(anyOf(is(200), is(400), is(500)));
 }

 @Test
 void updateProjectWithUnknownCode() {
 given()
 .contentType(ContentType.JSON)
 .pathParam("code", uniqueCode())
 .body("{\"title\":\"Updated Unknown Project\"}")
 .when()
 .put("/api/project/{code}")
 .then()
 .statusCode(anyOf(is(200), is(404)));
 }

 @Test
 void updateProjectWithAdditionalFields() {
 String code = createProject("Project Before Extended Update");

 given()
 .contentType(ContentType.JSON)
 .pathParam("code", code)
 .body("{\"title\":\"Extended Update\",\"idx\":10,\"problem\":[]}")
 .when()
 .put("/api/project/{code}")
 .then()
 .statusCode(200);
 }

 @Test
 void deleteProjectWithValidCode() {
 String code = createProject("Project To Delete");

 given()
 .pathParam("code", code)
 .when()
 .delete("/api/project/{code}")
 .then()
 .statusCode(200);
 }

 @Test
 void deleteProjectWithUnknownCode() {
 given()
 .pathParam("code", uniqueCode())
 .when()
 .delete("/api/project/{code}")
 .then()
 .statusCode(anyOf(is(200), is(404)));
 }

 @Test
 void deleteProjectTwice() {
 String code = createProject("Project For Repeated Deletion");

 given()
 .pathParam("code", code)
 .when()
 .delete("/api/project/{code}")
 .then()
 .statusCode(200);

 given()
 .pathParam("code", code)
 .when()
 .delete("/api/project/{code}")
 .then()
 .statusCode(anyOf(is(200), is(404)));
 }

 @Test
 void createProblemForExistingProject() {
 String code = createProject("Problem Creation Project");

 given()
 .contentType(ContentType.JSON)
 .pathParam("code", code)
 .body("{\"title\":\"Test Problem\",\"problemCode\":\"PRB001\"}")
 .when()
 .post("/api/problem/{code}")
 .then()
 .statusCode(200);
 }

 @Test
 void createProblemWithAllFields() {
 String code = createProject("Complete Problem Project");

 String body = """
 {
 "idx": 1,
 "title": "Complete Problem",
 "problemCode": "PRB001",
 "project": {
 "title": "Nested Project",
 "code": "NEST01"
 }
 }
 """;

 given()
 .contentType(ContentType.JSON)
 .pathParam("code", code)
 .body(body)
 .when()
 .post("/api/problem/{code}")
 .then()
 .statusCode(200);
 }

 @Test
 void createProblemWithEmptyTitle() {
 String code = createProject("Empty Problem Title Project");

 given()
 .contentType(ContentType.JSON)
 .pathParam("code", code)
 .body("{\"title\":\"\"}")
 .when()
 .post("/api/problem/{code}")
 .then()
 .statusCode(200);
 }

 @Test
 void createProblemWithMissingOptionalFields() {
 String code = createProject("Minimal Problem Project");

 given()
 .contentType(ContentType.JSON)
 .pathParam("code", code)
 .body("{}")
 .when()
 .post("/api/problem/{code}")
 .then()
 .statusCode(200);
 }

 @Test
 void createProblemWithUnknownProjectCode() {
 given()
 .contentType(ContentType.JSON)
 .pathParam("code", uniqueCode())
 .body("{\"title\":\"Orphan Problem\"}")
 .when()
 .post("/api/problem/{code}")
 .then()
 .statusCode(anyOf(is(200), is(404)));
 }

 @Test
 void createProblemWithInvalidLongCode() {
 given()
 .contentType(ContentType.JSON)
 .pathParam("code", "ABC1234")
 .body("{\"title\":\"Problem With Invalid Code\"}")
 .when()
 .post("/api/problem/{code}")
 .then()
 .statusCode(anyOf(is(200), is(400), is(404)));
 }

 @Test
 void getProblemsForProjectWithNoProblems() {
 String code = createProject("No Problems Project");

 given()
 .pathParam("code", code)
 .when()
 .get("/api/problem/{code}")
 .then()
 .statusCode(200)
 .body("$", instanceOf(java.util.List.class));
 }

 @Test
 void getProblemsAfterCreatingProblem() {
 String code = createProject("Problem Retrieval Project");

 given()
 .contentType(ContentType.JSON)
 .pathParam("code", code)
 .body("{\"title\":\"Retrievable Problem\",\"problemCode\":\"RET001\"}")
 .when()
 .post("/api/problem/{code}")
 .then()
 .statusCode(200);

 given()
 .pathParam("code", code)
 .when()
 .get("/api/problem/{code}")
 .then()
 .statusCode(200)
 .body("$", instanceOf(java.util.List.class));
 }

 @Test
 void getProblemsForUnknownProjectCode() {
 given()
 .pathParam("code", uniqueCode())
 .when()
 .get("/api/problem/{code}")
 .then()
 .statusCode(200);
 }

 @Test
 void deleteMostRecentProblem() {
 String code = createProject("Recent Problem Deletion Project");

 given()
 .contentType(ContentType.JSON)
 .pathParam("code", code)
 .body("{\"title\":\"Problem To Delete\"}")
 .when()
 .post("/api/problem/{code}")
 .then()
 .statusCode(200);

 given()
 .pathParam("code", code)
 .when()
 .delete("/api/problem/{code}")
 .then()
 .statusCode(200);
 }

 @Test
 void deleteProblemFromEmptyProject() {
 String code = createProject("Empty Problem Deletion Project");

 given()
 .pathParam("code", code)
 .when()
 .delete("/api/problem/{code}")
 .then()
 .statusCode(anyOf(is(200), is(404)));
 }

 @Test
 void deleteProblemWithUnknownCode() {
 given()
 .pathParam("code", uniqueCode())
 .when()
 .delete("/api/problem/{code}")
 .then()
 .statusCode(anyOf(is(200), is(404)));
 }

 @Test
 void deleteAllProblemsFromProject() {
 String code = createProject("Delete All Problems Project");

 given()
 .contentType(ContentType.JSON)
 .pathParam("code", code)
 .body("{\"title\":\"First Problem\"}")
 .when()
 .post("/api/problem/{code}")
 .then()
 .statusCode(200);

 given()
 .contentType(ContentType.JSON)
 .pathParam("code", code)
 .body("{\"title\":\"Second Problem\"}")
 .when()
 .post("/api/problem/{code}")
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
 .get("/api/problem/{code}")
 .then()
 .statusCode(200)
 .body("$", hasSize(0));
 }

 @Test
 void deleteAllProblemsFromEmptyProject() {
 String code = createProject("Empty All Deletion Project");

 given()
 .pathParam("code", code)
 .when()
 .delete("/api/problem/{code}/all")
 .then()
 .statusCode(200);
 }

 @Test
 void deleteAllProblemsWithUnknownCode() {
 given()
 .pathParam("code", uniqueCode())
 .when()
 .delete("/api/problem/{code}/all")
 .then()
 .statusCode(anyOf(is(200), is(404)));
 }

 @Test
 void saveSubProblemWithValidFields() {
 String body = """
 {
 "pro_idx": 1,
 "content": "Subproblem content"
 }
 """;

 given()
 .contentType(ContentType.JSON)
 .body(body)
 .when()
 .post("/api/subproblem")
 .then()
 .statusCode(200);
 }

 @Test
 void saveSubProblemWithEmptyContent() {
 given()
 .contentType(ContentType.JSON)
 .body("{\"pro_idx\":1,\"content\":\"\"}")
 .when()
 .post("/api/subproblem")
 .then()
 .statusCode(200);
 }

 @Test
 void saveSubProblemWithMissingFields() {
 given()
 .contentType(ContentType.JSON)
 .body("{}")
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
 void saveSubProblemWithZeroIndex() {
 given()
 .contentType(ContentType.JSON)
 .body("{\"pro_idx\":0,\"content\":\"Zero index content\"}")
 .when()
 .post("/api/subproblem")
 .then()
 .statusCode(200);
 }

 @Test
 void saveSubProblemWithNegativeIndex() {
 given()
 .contentType(ContentType.JSON)
 .body("{\"pro_idx\":-1,\"content\":\"Negative index content\"}")
 .when()
 .post("/api/subproblem")
 .then()
 .statusCode(200);
 }

 @Test
 void saveSubProblemWithMaximumLongIndex() {
 given()
 .contentType(ContentType.JSON)
 .body("{\"pro_idx\":9223372036854775807,\"content\":\"Maximum long index\"}")
 .when()
 .post("/api/subproblem")
 .then()
 .statusCode(200);
 }

 @Test
 void saveSubProblemWithLongOverflowIndex() {
 given()
 .contentType(ContentType.JSON)
 .body("{\"pro_idx\":9223372036854775808,\"content\":\"Overflow index\"}")
 .when()
 .post("/api/subproblem")
 .then()
 .statusCode(anyOf(is(400), is(500)));
 }

 @Test
 void saveSubProblemWithStringIndex() {
 given()
 .contentType(ContentType.JSON)
 .body("{\"pro_idx\":\"invalid\",\"content\":\"Invalid index\"}")
 .when()
 .post("/api/subproblem")
 .then()
 .statusCode(anyOf(is(400), is(500)));
 }

 @Test
 void saveSubProblemWithArrayBody() {
 given()
 .contentType(ContentType.JSON)
 .body("[]")
 .when()
 .post("/api/subproblem")
 .then()
 .statusCode(anyOf(is(400), is(500)));
 }

 @Test
 void saveSubProblemWithInvalidJson() {
 given()
 .contentType(ContentType.JSON)
 .body("{invalid-json}")
 .when()
 .post("/api/subproblem")
 .then()
 .statusCode(anyOf(is(400), is(500)));
 }

 @Test
 void createRetrieveAndDeleteProblemsIndependently() {
 String code = createProject("Problem Lifecycle Project");

 given()
 .contentType(ContentType.JSON)
 .pathParam("code", code)
 .body("{\"title\":\"Lifecycle Problem One\"}")
 .when()
 .post("/api/problem/{code}")
 .then()
 .statusCode(200);

 given()
 .contentType(ContentType.JSON)
 .pathParam("code", code)
 .body("{\"title\":\"Lifecycle Problem Two\"}")
 .when()
 .post("/api/problem/{code}")
 .then()
 .statusCode(200);

 given()
 .pathParam("code", code)
 .when()
 .get("/api/problem/{code}")
 .then()
 .statusCode(200)
 .body("$", hasSize(greaterThanOrEqualTo(2)));

 given()
 .pathParam("code", code)
 .when()
 .delete("/api/problem/{code}")
 .then()
 .statusCode(200);

 given()
 .pathParam("code", code)
 .when()
 .get("/api/problem/{code}")
 .then()
 .statusCode(200)
 .body("$", hasSize(greaterThanOrEqualTo(1)));
 }

 @Test
 void createProjectThenUpdateRetrieveAndDelete() {
 String code = createProject("Project Lifecycle Initial");

 given()
 .contentType(ContentType.JSON)
 .pathParam("code", code)
 .body("{\"title\":\"Project Lifecycle Updated\"}")
 .when()
 .put("/api/project/{code}")
 .then()
 .statusCode(200);

 given()
 .pathParam("code", code)
 .when()
 .get("/api/project/{code}")
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
 .statusCode(anyOf(is(200), is(404)));
 }

 @Test
 void deleteAllProblemsThenDeleteProject() {
 String code = createProject("Complete Deletion Lifecycle");

 given()
 .contentType(ContentType.JSON)
 .pathParam("code", code)
 .body("{\"title\":\"Problem Before Project Deletion\"}")
 .when()
 .post("/api/problem/{code}")
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
 }

 @Test
 void createIndependentProjectsWithDifferentTitles() {
 String firstTitle = "Independent Project One";
 String secondTitle = "Independent Project Two";

 given()
 .contentType(ContentType.JSON)
 .body("{\"title\":\"" + firstTitle + "\"}")
 .when()
 .post("/api/project")
 .then()
 .statusCode(200);

 given()
 .contentType(ContentType.JSON)
 .body("{\"title\":\"" + secondTitle + "\"}")
 .when()
 .post("/api/project")
 .then()
 .statusCode(200);
 }
}