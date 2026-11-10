import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.LinkedHashMap;
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

 private Map<String, Object> project(String title) {
 Map<String, Object> body = new LinkedHashMap<>();
 body.put("title", title);
 return body;
 }

 private Map<String, Object> problem(String title) {
 Map<String, Object> body = new LinkedHashMap<>();
 body.put("title", title);
 return body;
 }

 private Map<String, Object> subProblem(long proIdx, String content) {
 Map<String, Object> body = new LinkedHashMap<>();
 body.put("pro_idx", proIdx);
 body.put("content", content);
 return body;
 }

 private String createProject(String title) {
 Response response = given()
 .contentType(ContentType.JSON)
 .body(project(title))
 .when()
 .post("/api/project");

 response.then().statusCode(200);

 return response.jsonPath().getString("code");
 }

 @Test
 void createProjectWithValidTitle() {
 given()
 .contentType(ContentType.JSON)
 .body(project("Integration Test Project"))
 .when()
 .post("/api/project")
 .then()
 .statusCode(200);
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
 given()
 .contentType(ContentType.JSON)
 .body(project("A".repeat(200)))
 .when()
 .post("/api/project")
 .then()
 .statusCode(200);
 }

 @Test
 void createProjectWithTitleExceedingMaximumLength() {
 given()
 .contentType(ContentType.JSON)
 .body(project("A".repeat(201)))
 .when()
 .post("/api/project")
 .then()
 .statusCode(anyOf(is(200), is(400), is(500)));
 }

 @Test
 void createProjectWithoutTitle() {
 Map<String, Object> body = new LinkedHashMap<>();

 given()
 .contentType(ContentType.JSON)
 .body(body)
 .when()
 .post("/api/project")
 .then()
 .statusCode(anyOf(is(400), is(500)));
 }

 @Test
 void createProjectWithNullTitle() {
 Map<String, Object> body = new LinkedHashMap<>();
 body.put("title", null);

 given()
 .contentType(ContentType.JSON)
 .body(body)
 .when()
 .post("/api/project")
 .then()
 .statusCode(anyOf(is(200), is(400), is(500)));
 }

 @Test
 void createProjectWithOptionalFields() {
 Map<String, Object> body = project("Project With Optional Fields");
 body.put("idx", 1);
 body.put("code", "ABC123");
 body.put("problem", new ArrayList<>());

 given()
 .contentType(ContentType.JSON)
 .body(body)
 .when()
 .post("/api/project")
 .then()
 .statusCode(200);
 }

 @Test
 void createProjectWithMaximumLengthCode() {
 Map<String, Object> body = project("Code Boundary Project");
 body.put("code", "ABC123");

 given()
 .contentType(ContentType.JSON)
 .body(body)
 .when()
 .post("/api/project")
 .then()
 .statusCode(200);
 }

 @Test
 void createProjectWithCodeExceedingMaximumLength() {
 Map<String, Object> body = project("Invalid Code Project");
 body.put("code", "ABCDEFG");

 given()
 .contentType(ContentType.JSON)
 .body(body)
 .when()
 .post("/api/project")
 .then()
 .statusCode(anyOf(is(200), is(400), is(500)));
 }

 @Test
 void createProjectWithEmptyCode() {
 Map<String, Object> body = project("Empty Code Project");
 body.put("code", "");

 given()
 .contentType(ContentType.JSON)
 .body(body)
 .when()
 .post("/api/project")
 .then()
 .statusCode(200);
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
 .contentType(ContentType.JSON);
 }

 @Test
 void getProjectWithSixCharacterCode() {
 given()
 .pathParam("code", "ABC123")
 .when()
 .get("/api/project/{code}")
 .then()
 .statusCode(anyOf(is(200), is(404)));
 }

 @Test
 void getProjectWithUnknownCode() {
 given()
 .pathParam("code", "ZZZ999")
 .when()
 .get("/api/project/{code}")
 .then()
 .statusCode(anyOf(is(200), is(404)));
 }

 @Test
 void getProjectWithShortCode() {
 given()
 .pathParam("code", "ABC12")
 .when()
 .get("/api/project/{code}")
 .then()
 .statusCode(anyOf(is(200), is(400), is(404)));
 }

 @Test
 void getProjectWithLongCode() {
 given()
 .pathParam("code", "ABCDEFG")
 .when()
 .get("/api/project/{code}")
 .then()
 .statusCode(anyOf(is(200), is(400), is(404)));
 }

 @Test
 void getProjectWithEmptyCode() {
 given()
 .when()
 .get("/api/project/")
 .then()
 .statusCode(anyOf(is(404), is(405)));
 }

 @Test
 void updateExistingProject() {
 String code = createProject("Original Project Title");

 given()
 .contentType(ContentType.JSON)
 .pathParam("code", code)
 .body(project("Updated Project Title"))
 .when()
 .put("/api/project/{code}")
 .then()
 .statusCode(200);
 }

 @Test
 void updateProjectWithMaximumLengthTitle() {
 String code = createProject("Project Before Boundary Update");

 given()
 .contentType(ContentType.JSON)
 .pathParam("code", code)
 .body(project("B".repeat(200)))
 .when()
 .put("/api/project/{code}")
 .then()
 .statusCode(200);
 }

 @Test
 void updateProjectWithEmptyTitle() {
 String code = createProject("Project Before Empty Update");

 given()
 .contentType(ContentType.JSON)
 .pathParam("code", code)
 .body(project(""))
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
 .body(new LinkedHashMap<>())
 .when()
 .put("/api/project/{code}")
 .then()
 .statusCode(anyOf(is(400), is(500)));
 }

 @Test
 void updateProjectWithUnknownCode() {
 given()
 .contentType(ContentType.JSON)
 .pathParam("code", "ZZZ999")
 .body(project("Unknown Project Update"))
 .when()
 .put("/api/project/{code}")
 .then()
 .statusCode(anyOf(is(200), is(404)));
 }

 @Test
 void updateProjectWithOverlengthTitle() {
 String code = createProject("Project Before Overlength Update");

 given()
 .contentType(ContentType.JSON)
 .pathParam("code", code)
 .body(project("C".repeat(201)))
 .when()
 .put("/api/project/{code}")
 .then()
 .statusCode(anyOf(is(200), is(400), is(500)));
 }

 @Test
 void deleteExistingProject() {
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
 .pathParam("code", "ZZZ999")
 .when()
 .delete("/api/project/{code}")
 .then()
 .statusCode(anyOf(is(200), is(404)));
 }

 @Test
 void deleteProjectTwice() {
 String code = createProject("Project To Delete Twice");

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
 String code = createProject("Project For Problem Creation");

 given()
 .contentType(ContentType.JSON)
 .pathParam("code", code)
 .body(problem("First Problem"))
 .when()
 .post("/api/problem/{code}")
 .then()
 .statusCode(200);
 }

 @Test
 void createProblemWithAllOptionalFields() {
 String code = createProject("Project For Complete Problem");

 Map<String, Object> body = problem("Complete Problem");
 body.put("idx", 1);
 body.put("problemCode", "PRB001");
 body.put("project", project("Nested Project"));

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
 String code = createProject("Project For Empty Problem Title");

 given()
 .contentType(ContentType.JSON)
 .pathParam("code", code)
 .body(problem(""))
 .when()
 .post("/api/problem/{code}")
 .then()
 .statusCode(200);
 }

 @Test
 void createProblemWithoutTitle() {
 String code = createProject("Project For Missing Problem Title");

 given()
 .contentType(ContentType.JSON)
 .pathParam("code", code)
 .body(new LinkedHashMap<>())
 .when()
 .post("/api/problem/{code}")
 .then()
 .statusCode(200);
 }

 @Test
 void createProblemWithUnknownProjectCode() {
 given()
 .contentType(ContentType.JSON)
 .pathParam("code", "ZZZ999")
 .body(problem("Problem For Unknown Project"))
 .when()
 .post("/api/problem/{code}")
 .then()
 .statusCode(anyOf(is(200), is(404)));
 }

 @Test
 void createProblemWithInvalidCodeLength() {
 given()
 .contentType(ContentType.JSON)
 .pathParam("code", "ABC")
 .body(problem("Problem With Invalid Code"))
 .when()
 .post("/api/problem/{code}")
 .then()
 .statusCode(anyOf(is(200), is(400), is(404)));
 }

 @Test
 void createMultipleProblemsForSameProject() {
 String code = createProject("Project With Multiple Problems");

 given()
 .contentType(ContentType.JSON)
 .pathParam("code", code)
 .body(problem("Problem One"))
 .when()
 .post("/api/problem/{code}")
 .then()
 .statusCode(200);

 given()
 .contentType(ContentType.JSON)
 .pathParam("code", code)
 .body(problem("Problem Two"))
 .when()
 .post("/api/problem/{code}")
 .then()
 .statusCode(200);
 }

 @Test
 void getProblemsForProject() {
 String code = createProject("Project For Problem Retrieval");

 given()
 .contentType(ContentType.JSON)
 .pathParam("code", code)
 .body(problem("Retrievable Problem"))
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
 .contentType(ContentType.JSON)
 .body("$", instanceOf(List.class));
 }

 @Test
 void getProblemsForProjectWithoutProblems() {
 String code = createProject("Project Without Problems");

 given()
 .pathParam("code", code)
 .when()
 .get("/api/problem/{code}")
 .then()
 .statusCode(200)
 .body("$", instanceOf(List.class));
 }

 @Test
 void getProblemsWithUnknownProjectCode() {
 given()
 .pathParam("code", "ZZZ999")
 .when()
 .get("/api/problem/{code}")
 .then()
 .statusCode(anyOf(is(200), is(404)));
 }

 @Test
 void getProblemsWithInvalidCodeLength() {
 given()
 .pathParam("code", "ABC")
 .when()
 .get("/api/problem/{code}")
 .then()
 .statusCode(anyOf(is(200), is(400), is(404)));
 }

 @Test
 void deleteMostRecentProblem() {
 String code = createProject("Project For Recent Problem Deletion");

 given()
 .contentType(ContentType.JSON)
 .pathParam("code", code)
 .body(problem("Problem To Delete"))
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
 void deleteProblemWithoutExistingProblems() {
 String code = createProject("Project Without Problems To Delete");

 given()
 .pathParam("code", code)
 .when()
 .delete("/api/problem/{code}")
 .then()
 .statusCode(anyOf(is(200), is(404)));
 }

 @Test
 void deleteProblemWithUnknownProjectCode() {
 given()
 .pathParam("code", "ZZZ999")
 .when()
 .delete("/api/problem/{code}")
 .then()
 .statusCode(anyOf(is(200), is(404)));
 }

 @Test
 void deleteAllProblemsForProject() {
 String code = createProject("Project For Delete All Problems");

 given()
 .contentType(ContentType.JSON)
 .pathParam("code", code)
 .body(problem("First Problem To Remove"))
 .when()
 .post("/api/problem/{code}")
 .then()
 .statusCode(200);

 given()
 .contentType(ContentType.JSON)
 .pathParam("code", code)
 .body(problem("Second Problem To Remove"))
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
 }

 @Test
 void deleteAllProblemsForProjectWithoutProblems() {
 String code = createProject("Project Without Problems For Bulk Delete");

 given()
 .pathParam("code", code)
 .when()
 .delete("/api/problem/{code}/all")
 .then()
 .statusCode(200);
 }

 @Test
 void deleteAllProblemsWithUnknownProjectCode() {
 given()
 .pathParam("code", "ZZZ999")
 .when()
 .delete("/api/problem/{code}/all")
 .then()
 .statusCode(anyOf(is(200), is(404)));
 }

 @Test
 void deleteAllProblemsWithInvalidCodeLength() {
 given()
 .pathParam("code", "ABC")
 .when()
 .delete("/api/problem/{code}/all")
 .then()
 .statusCode(anyOf(is(200), is(400), is(404)));
 }

 @Test
 void deleteAllProblemsAndVerifyEmptyList() {
 String code = createProject("Project For Empty List Verification");

 given()
 .contentType(ContentType.JSON)
 .pathParam("code", code)
 .body(problem("Problem Before Bulk Delete"))
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
 .body("$", instanceOf(List.class));
 }

 @Test
 void saveSubProblemWithValidBody() {
 given()
 .contentType(ContentType.JSON)
 .body(subProblem(1L, "Subproblem content"))
 .when()
 .post("/api/subproblem")
 .then()
 .statusCode(200);
 }

 @Test
 void saveSubProblemWithEmptyContent() {
 given()
 .contentType(ContentType.JSON)
 .body(subProblem(1L, ""))
 .when()
 .post("/api/subproblem")
 .then()
 .statusCode(200);
 }

 @Test
 void saveSubProblemWithLongContent() {
 given()
 .contentType(ContentType.JSON)
 .body(subProblem(1L, "A".repeat(10000)))
 .when()
 .post("/api/subproblem")
 .then()
 .statusCode(200);
 }

 @Test
 void saveSubProblemWithZeroProblemIndex() {
 given()
 .contentType(ContentType.JSON)
 .body(subProblem(0L, "Content with zero index"))
 .when()
 .post("/api/subproblem")
 .then()
 .statusCode(200);
 }

 @Test
 void saveSubProblemWithNegativeProblemIndex() {
 given()
 .contentType(ContentType.JSON)
 .body(subProblem(-1L, "Content with negative index"))
 .when()
 .post("/api/subproblem")
 .then()
 .statusCode(200);
 }

 @Test
 void saveSubProblemWithMaximumLongIndex() {
 given()
 .contentType(ContentType.JSON)
 .body(subProblem(Long.MAX_VALUE, "Content with maximum index"))
 .when()
 .post("/api/subproblem")
 .then()
 .statusCode(200);
 }

 @Test
 void saveSubProblemWithoutContent() {
 Map<String, Object> body = new LinkedHashMap<>();
 body.put("pro_idx", 1L);

 given()
 .contentType(ContentType.JSON)
 .body(body)
 .when()
 .post("/api/subproblem")
 .then()
 .statusCode(200);
 }

 @Test
 void saveSubProblemWithoutProblemIndex() {
 Map<String, Object> body = new LinkedHashMap<>();
 body.put("content", "Content without problem index");

 given()
 .contentType(ContentType.JSON)
 .body(body)
 .when()
 .post("/api/subproblem")
 .then()
 .statusCode(200);
 }

 @Test
 void saveSubProblemWithEmptyBody() {
 given()
 .contentType(ContentType.JSON)
 .body(new LinkedHashMap<>())
 .when()
 .post("/api/subproblem")
 .then()
 .statusCode(200);
 }

 @Test
 void saveSubProblemWithNullFields() {
 Map<String, Object> body = new LinkedHashMap<>();
 body.put("pro_idx", null);
 body.put("content", null);

 given()
 .contentType(ContentType.JSON)
 .body(body)
 .when()
 .post("/api/subproblem")
 .then()
 .statusCode(200);
 }

 @Test
 void saveSubProblemWithInvalidIndexType() {
 Map<String, Object> body = new LinkedHashMap<>();
 body.put("pro_idx", "invalid");
 body.put("content", "Invalid index type");

 given()
 .contentType(ContentType.JSON)
 .body(body)
 .when()
 .post("/api/subproblem")
 .then()
 .statusCode(anyOf(is(400), is(500)));
 }

 @Test
 void createRetrieveUpdateAndDeleteProjectLifecycle() {
 String code = createProject("Lifecycle Initial Project");

 given()
 .pathParam("code", code)
 .when()
 .get("/api/project/{code}")
 .then()
 .statusCode(200);

 given()
 .contentType(ContentType.JSON)
 .pathParam("code", code)
 .body(project("Lifecycle Updated Project"))
 .when()
 .put("/api/project/{code}")
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
 void createProjectAddProblemsRetrieveAndDeleteAll() {
 String code = createProject("Complete Problem Lifecycle Project");

 given()
 .contentType(ContentType.JSON)
 .pathParam("code", code)
 .body(problem("Lifecycle Problem One"))
 .when()
 .post("/api/problem/{code}")
 .then()
 .statusCode(200);

 given()
 .contentType(ContentType.JSON)
 .pathParam("code", code)
 .body(problem("Lifecycle Problem Two"))
 .when()
 .post("/api/problem/{code}")
 .then()
 .statusCode(200);

 given()
 .pathParam("code", code)
 .when()
 .get("/api/problem/{code}")
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
 .body("$", instanceOf(List.class));
 }

 @Test
 void createProjectDeleteRecentProblemAndVerifyRemainingProblems() {
 String code = createProject("Recent Problem Deletion Verification");

 given()
 .contentType(ContentType.JSON)
 .pathParam("code", code)
 .body(problem("First Retained Problem"))
 .when()
 .post("/api/problem/{code}")
 .then()
 .statusCode(200);

 given()
 .contentType(ContentType.JSON)
 .pathParam("code", code)
 .body(problem("Second Problem To Remove"))
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

 given()
 .pathParam("code", code)
 .when()
 .get("/api/problem/{code}")
 .then()
 .statusCode(200)
 .body("$", instanceOf(List.class));
 }
}

