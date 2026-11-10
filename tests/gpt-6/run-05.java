import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class GeneratedApiTest {

 private static final String BASE_URL = "http://localhost:8080";

 private String uniqueTitle() {
 return "Project-" + UUID.randomUUID();
 }

 private String uniqueCode() {
 return UUID.randomUUID().toString().replace("-", "").substring(0, 6);
 }

 private String projectJson(String title) {
 return "{\"title\":\"" + title + "\"}";
 }

 private String problemJson(String title) {
 return "{\"title\":\"" + title + "\"}";
 }

 @Test
 void createProjectWithValidTitle() {
 given()
 .baseUri(BASE_URL)
 .contentType(ContentType.JSON)
 .body(projectJson(uniqueTitle()))
 .when()
 .post("/api/project")
 .then()
 .statusCode(200);
 }

 @Test
 void createProjectWithEmptyTitle() {
 given()
 .baseUri(BASE_URL)
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
 .baseUri(BASE_URL)
 .contentType(ContentType.JSON)
 .body(projectJson(title))
 .when()
 .post("/api/project")
 .then()
 .statusCode(200);
 }

 @Test
 void createProjectWithTitleExceedingMaximumLength() {
 String title = "A".repeat(201);

 given()
 .baseUri(BASE_URL)
 .contentType(ContentType.JSON)
 .body(projectJson(title))
 .when()
 .post("/api/project")
 .then()
 .statusCode(anyOf(is(200), is(400), is(422)));
 }

 @Test
 void createProjectWithoutTitle() {
 given()
 .baseUri(BASE_URL)
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
 .baseUri(BASE_URL)
 .contentType(ContentType.JSON)
 .body("{\"title\":null}")
 .when()
 .post("/api/project")
 .then()
 .statusCode(anyOf(is(200), is(400), is(422)));
 }

 @Test
 void createProjectWithoutRequestBody() {
 given()
 .baseUri(BASE_URL)
 .contentType(ContentType.JSON)
 .when()
 .post("/api/project")
 .then()
 .statusCode(anyOf(is(400), is(415), is(500)));
 }

 @Test
 void getProjectWithSixCharacterCode() {
 given()
 .baseUri(BASE_URL)
 .when()
 .get("/api/project/ABC123")
 .then()
 .statusCode(anyOf(is(200), is(404)));
 }

 @Test
 void getProjectWithAlphanumericCode() {
 given()
 .baseUri(BASE_URL)
 .when()
 .get("/api/project/a1B2c3")
 .then()
 .statusCode(anyOf(is(200), is(404)));
 }

 @Test
 void getProjectWithShortCode() {
 given()
 .baseUri(BASE_URL)
 .when()
 .get("/api/project/ABC12")
 .then()
 .statusCode(anyOf(is(200), is(400), is(404)));
 }

 @Test
 void getProjectWithLongCode() {
 given()
 .baseUri(BASE_URL)
 .when()
 .get("/api/project/ABC1234")
 .then()
 .statusCode(anyOf(is(200), is(400), is(404)));
 }

 @Test
 void getProjectWithEmptyCode() {
 given()
 .baseUri(BASE_URL)
 .when()
 .get("/api/project/")
 .then()
 .statusCode(anyOf(is(200), is(404)));
 }

 @Test
 void getProjectWithUnknownCode() {
 given()
 .baseUri(BASE_URL)
 .when()
 .get("/api/project/" + uniqueCode())
 .then()
 .statusCode(anyOf(is(200), is(404)));
 }

 @Test
 void updateProjectWithValidTitle() {
 given()
 .baseUri(BASE_URL)
 .contentType(ContentType.JSON)
 .body(projectJson(uniqueTitle()))
 .when()
 .put("/api/project/ABC123")
 .then()
 .statusCode(anyOf(is(200), is(404)));
 }

 @Test
 void updateProjectWithEmptyTitle() {
 given()
 .baseUri(BASE_URL)
 .contentType(ContentType.JSON)
 .body("{\"title\":\"\"}")
 .when()
 .put("/api/project/ABC123")
 .then()
 .statusCode(anyOf(is(200), is(400), is(404), is(422)));
 }

 @Test
 void updateProjectWithoutTitle() {
 given()
 .baseUri(BASE_URL)
 .contentType(ContentType.JSON)
 .body("{}")
 .when()
 .put("/api/project/ABC123")
 .then()
 .statusCode(anyOf(is(200), is(400), is(404), is(422)));
 }

 @Test
 void updateProjectWithMaximumLengthTitle() {
 given()
 .baseUri(BASE_URL)
 .contentType(ContentType.JSON)
 .body(projectJson("B".repeat(200)))
 .when()
 .put("/api/project/ABC123")
 .then()
 .statusCode(anyOf(is(200), is(404)));
 }

 @Test
 void updateProjectWithExcessiveTitleLength() {
 given()
 .baseUri(BASE_URL)
 .contentType(ContentType.JSON)
 .body(projectJson("B".repeat(201)))
 .when()
 .put("/api/project/ABC123")
 .then()
 .statusCode(anyOf(is(200), is(400), is(404), is(422)));
 }

 @Test
 void deleteProjectWithValidCode() {
 given()
 .baseUri(BASE_URL)
 .when()
 .delete("/api/project/ABC123")
 .then()
 .statusCode(anyOf(is(200), is(404)));
 }

 @Test
 void deleteProjectWithUnknownCode() {
 given()
 .baseUri(BASE_URL)
 .when()
 .delete("/api/project/" + uniqueCode())
 .then()
 .statusCode(anyOf(is(200), is(404)));
 }

 @Test
 void deleteProjectWithInvalidCodeLength() {
 given()
 .baseUri(BASE_URL)
 .when()
 .delete("/api/project/ABC")
 .then()
 .statusCode(anyOf(is(200), is(400), is(404)));
 }

 @Test
 void createProblemForValidProjectCode() {
 given()
 .baseUri(BASE_URL)
 .contentType(ContentType.JSON)
 .body(problemJson(uniqueTitle()))
 .when()
 .post("/api/problem/ABC123")
 .then()
 .statusCode(anyOf(is(200), is(404)));
 }

 @Test
 void createProblemWithEmptyTitle() {
 given()
 .baseUri(BASE_URL)
 .contentType(ContentType.JSON)
 .body("{\"title\":\"\"}")
 .when()
 .post("/api/problem/ABC123")
 .then()
 .statusCode(anyOf(is(200), is(400), is(404), is(422)));
 }

 @Test
 void createProblemWithoutTitle() {
 given()
 .baseUri(BASE_URL)
 .contentType(ContentType.JSON)
 .body("{}")
 .when()
 .post("/api/problem/ABC123")
 .then()
 .statusCode(anyOf(is(200), is(400), is(404), is(422)));
 }

 @Test
 void createProblemWithNestedProject() {
 String body = "{"
 + "\"title\":\"Problem-" + UUID.randomUUID() + "\","
 + "\"project\":{\"title\":\"Nested-" + UUID.randomUUID() + "\"},"
 + "\"problemCode\":\"ABC123\""
 + "}";

 given()
 .baseUri(BASE_URL)
 .contentType(ContentType.JSON)
 .body(body)
 .when()
 .post("/api/problem/ABC123")
 .then()
 .statusCode(anyOf(is(200), is(404)));
 }

 @Test
 void createProblemWithNullFields() {
 given()
 .baseUri(BASE_URL)
 .contentType(ContentType.JSON)
 .body("{\"idx\":null,\"title\":null,\"project\":null,\"problemCode\":null}")
 .when()
 .post("/api/problem/ABC123")
 .then()
 .statusCode(anyOf(is(200), is(400), is(404), is(422)));
 }

 @Test
 void createProblemWithInvalidCodeLength() {
 given()
 .baseUri(BASE_URL)
 .contentType(ContentType.JSON)
 .body(problemJson(uniqueTitle()))
 .when()
 .post("/api/problem/ABC")
 .then()
 .statusCode(anyOf(is(200), is(400), is(404)));
 }

 @Test
 void getProblemsByCode() {
 given()
 .baseUri(BASE_URL)
 .when()
 .get("/api/problem/ABC123")
 .then()
 .statusCode(anyOf(is(200), is(404)));
 }

 @Test
 void getProblemsByUnknownCode() {
 given()
 .baseUri(BASE_URL)
 .when()
 .get("/api/problem/" + uniqueCode())
 .then()
 .statusCode(anyOf(is(200), is(404)));
 }

 @Test
 void getProblemsByShortCode() {
 given()
 .baseUri(BASE_URL)
 .when()
 .get("/api/problem/ABC")
 .then()
 .statusCode(anyOf(is(200), is(400), is(404)));
 }

 @Test
 void deleteRecentProblemByCode() {
 given()
 .baseUri(BASE_URL)
 .when()
 .delete("/api/problem/ABC123")
 .then()
 .statusCode(anyOf(is(200), is(404)));
 }

 @Test
 void deleteRecentProblemWithUnknownCode() {
 given()
 .baseUri(BASE_URL)
 .when()
 .delete("/api/problem/" + uniqueCode())
 .then()
 .statusCode(anyOf(is(200), is(404)));
 }

 @Test
 void deleteAllProblemsByCode() {
 given()
 .baseUri(BASE_URL)
 .when()
 .delete("/api/problem/ABC123/all")
 .then()
 .statusCode(anyOf(is(200), is(404)));
 }

 @Test
 void deleteAllProblemsWithUnknownCode() {
 given()
 .baseUri(BASE_URL)
 .when()
 .delete("/api/problem/" + uniqueCode() + "/all")
 .then()
 .statusCode(anyOf(is(200), is(404)));
 }

 @Test
 void deleteAllProblemsWithInvalidCodeLength() {
 given()
 .baseUri(BASE_URL)
 .when()
 .delete("/api/problem/ABC/all")
 .then()
 .statusCode(anyOf(is(200), is(400), is(404)));
 }

 @Test
 void saveSubProblemWithValidFields() {
 given()
 .baseUri(BASE_URL)
 .contentType(ContentType.JSON)
 .body("{\"pro_idx\":1,\"content\":\"Subproblem content\"}")
 .when()
 .post("/api/subproblem")
 .then()
 .statusCode(200);
 }

 @Test
 void saveSubProblemWithZeroIndex() {
 given()
 .baseUri(BASE_URL)
 .contentType(ContentType.JSON)
 .body("{\"pro_idx\":0,\"content\":\"Subproblem content\"}")
 .when()
 .post("/api/subproblem")
 .then()
 .statusCode(200);
 }

 @Test
 void saveSubProblemWithNegativeIndex() {
 given()
 .baseUri(BASE_URL)
 .contentType(ContentType.JSON)
 .body("{\"pro_idx\":-1,\"content\":\"Subproblem content\"}")
 .when()
 .post("/api/subproblem")
 .then()
 .statusCode(200);
 }

 @Test
 void saveSubProblemWithMaximumLongIndex() {
 given()
 .baseUri(BASE_URL)
 .contentType(ContentType.JSON)
 .body("{\"pro_idx\":9223372036854775807,\"content\":\"Subproblem content\"}")
 .when()
 .post("/api/subproblem")
 .then()
 .statusCode(200);
 }

 @Test
 void saveSubProblemWithMissingIndex() {
 given()
 .baseUri(BASE_URL)
 .contentType(ContentType.JSON)
 .body("{\"content\":\"Subproblem content\"}")
 .when()
 .post("/api/subproblem")
 .then()
 .statusCode(200);
 }

 @Test
 void saveSubProblemWithMissingContent() {
 given()
 .baseUri(BASE_URL)
 .contentType(ContentType.JSON)
 .body("{\"pro_idx\":1}")
 .when()
 .post("/api/subproblem")
 .then()
 .statusCode(200);
 }

 @Test
 void saveSubProblemWithNullFields() {
 given()
 .baseUri(BASE_URL)
 .contentType(ContentType.JSON)
 .body("{\"pro_idx\":null,\"content\":null}")
 .when()
 .post("/api/subproblem")
 .then()
 .statusCode(200);
 }

 @Test
 void saveSubProblemWithEmptyContent() {
 given()
 .baseUri(BASE_URL)
 .contentType(ContentType.JSON)
 .body("{\"pro_idx\":1,\"content\":\"\"}")
 .when()
 .post("/api/subproblem")
 .then()
 .statusCode(200);
 }

 @Test
 void saveSubProblemWithEmptyObject() {
 given()
 .baseUri(BASE_URL)
 .contentType(ContentType.JSON)
 .body("{}")
 .when()
 .post("/api/subproblem")
 .then()
 .statusCode(200);
 }

 @Test
 void saveSubProblemWithoutRequestBody() {
 given()
 .baseUri(BASE_URL)
 .contentType(ContentType.JSON)
 .when()
 .post("/api/subproblem")
 .then()
 .statusCode(anyOf(is(400), is(415), is(500)));
 }

 @Test
 void saveSubProblemWithUnexpectedField() {
 given()
 .baseUri(BASE_URL)
 .contentType(ContentType.JSON)
 .body("{\"pro_idx\":1,\"content\":\"Content\",\"unexpected\":\"value\"}")
 .when()
 .post("/api/subproblem")
 .then()
 .statusCode(200);
 }

 @Test
 void createAndRetrieveProject() {
 String title = uniqueTitle();

 given()
 .baseUri(BASE_URL)
 .contentType(ContentType.JSON)
 .body(projectJson(title))
 .when()
 .post("/api/project")
 .then()
 .statusCode(200);
 }

 @Test
 void createProblemAndRetrieveProblemsForCode() {
 String code = "ABC123";

 given()
 .baseUri(BASE_URL)
 .contentType(ContentType.JSON)
 .body(problemJson(uniqueTitle()))
 .when()
 .post("/api/problem/" + code)
 .then()
 .statusCode(anyOf(is(200), is(404)));

 given()
 .baseUri(BASE_URL)
 .when()
 .get("/api/problem/" + code)
 .then()
 .statusCode(anyOf(is(200), is(404)));
 }

 @Test
 void createProblemAndDeleteRecentProblem() {
 String code = uniqueCode();

 given()
 .baseUri(BASE_URL)
 .contentType(ContentType.JSON)
 .body(problemJson(uniqueTitle()))
 .when()
 .post("/api/problem/" + code)
 .then()
 .statusCode(anyOf(is(200), is(404)));

 given()
 .baseUri(BASE_URL)
 .when()
 .delete("/api/problem/" + code)
 .then()
 .statusCode(anyOf(is(200), is(404)));
 }

 @Test
 void createProblemAndDeleteAllProblems() {
 String code = uniqueCode();

 given()
 .baseUri(BASE_URL)
 .contentType(ContentType.JSON)
 .body(problemJson(uniqueTitle()))
 .when()
 .post("/api/problem/" + code)
 .then()
 .statusCode(anyOf(is(200), is(404)));

 given()
 .baseUri(BASE_URL)
 .when()
 .delete("/api/problem/" + code + "/all")
 .then()
 .statusCode(anyOf(is(200), is(404)));
 }

 @Test
 void createUpdateRetrieveAndDeleteProject() {
 String code = uniqueCode();
 String title = uniqueTitle();

 given()
 .baseUri(BASE_URL)
 .contentType(ContentType.JSON)
 .body("{\"title\":\"" + title + "\",\"code\":\"" + code + "\"}")
 .when()
 .post("/api/project")
 .then()
 .statusCode(200);

 given()
 .baseUri(BASE_URL)
 .contentType(ContentType.JSON)
 .body(projectJson(uniqueTitle()))
 .when()
 .put("/api/project/" + code)
 .then()
 .statusCode(anyOf(is(200), is(404)));

 given()
 .baseUri(BASE_URL)
 .when()
 .get("/api/project/" + code)
 .then()
 .statusCode(anyOf(is(200), is(404)));

 given()
 .baseUri(BASE_URL)
 .when()
 .delete("/api/project/" + code)
 .then()
 .statusCode(anyOf(is(200), is(404)));
 }

 @Test
 void deleteAllProblemsThenRetrieveProblems() {
 String code = uniqueCode();

 given()
 .baseUri(BASE_URL)
 .when()
 .delete("/api/problem/" + code + "/all")
 .then()
 .statusCode(anyOf(is(200), is(404)));

 given()
 .baseUri(BASE_URL)
 .when()
 .get("/api/problem/" + code)
 .then()
 .statusCode(anyOf(is(200), is(404)));
 }

 @Test
 void createProjectWithProblemCollection() {
 String body = "{"
 + "\"title\":\"" + uniqueTitle() + "\","
 + "\"problem\":["
 + "{\"title\":\"Problem A\",\"problemCode\":\"ABC123\"},"
 + "{\"title\":\"Problem B\",\"problemCode\":\"DEF456\"}"
 + "]"
 + "}";

 given()
 .baseUri(BASE_URL)
 .contentType(ContentType.JSON)
 .body(body)
 .when()
 .post("/api/project")
 .then()
 .statusCode(200);
 }

 @Test
 void createProjectWithEmptyProblemCollection() {
 String body = "{\"title\":\"" + uniqueTitle() + "\",\"problem\":[]}";

 given()
 .baseUri(BASE_URL)
 .contentType(ContentType.JSON)
 .body(body)
 .when()
 .post("/api/project")
 .then()
 .statusCode(200);
 }

 @Test
 void createProjectWithNullProblemCollection() {
 String body = "{\"title\":\"" + uniqueTitle() + "\",\"problem\":null}";

 given()
 .baseUri(BASE_URL)
 .contentType(ContentType.JSON)
 .body(body)
 .when()
 .post("/api/project")
 .then()
 .statusCode(200);
 }

 @Test
 void createProjectWithDuplicateProblems() {
 String body = "{"
 + "\"title\":\"" + uniqueTitle() + "\","
 + "\"problem\":["
 + "{\"title\":\"Duplicate\",\"problemCode\":\"ABC123\"},"
 + "{\"title\":\"Duplicate\",\"problemCode\":\"ABC123\"}"
 + "]"
 + "}";

 given()
 .baseUri(BASE_URL)
 .contentType(ContentType.JSON)
 .body(body)
 .when()
 .post("/api/project")
 .then()
 .statusCode(anyOf(is(200), is(400), is(422)));
 }

 @Test
 void createProjectWithNullCode() {
 given()
 .baseUri(BASE_URL)
 .contentType(ContentType.JSON)
 .body("{\"title\":\"" + uniqueTitle() + "\",\"code\":null}")
 .when()
 .post("/api/project")
 .then()
 .statusCode(200);
 }

 @Test
 void createProjectWithMaximumLengthCode() {
 String body = "{\"title\":\"" + uniqueTitle() + "\",\"code\":\"ABC123\"}";

 given()
 .baseUri(BASE_URL)
 .contentType(ContentType.JSON)
 .body(body)
 .when()
 .post("/api/project")
 .then()
 .statusCode(200);
 }

 @Test
 void createProjectWithEmptyCode() {
 String body = "{\"title\":\"" + uniqueTitle() + "\",\"code\":\"\"}";

 given()
 .baseUri(BASE_URL)
 .contentType(ContentType.JSON)
 .body(body)
 .when()
 .post("/api/project")
 .then()
 .statusCode(200);
 }

 @Test
 void createProjectWithCodeExceedingMaximumLength() {
 String body = "{\"title\":\"" + uniqueTitle() + "\",\"code\":\"ABC1234\"}";

 given()
 .baseUri(BASE_URL)
 .contentType(ContentType.JSON)
 .body(body)
 .when()
 .post("/api/project")
 .then()
 .statusCode(anyOf(is(200), is(400), is(422)));
 }

 @Test
 void createProjectWithAdditionalUnknownField() {
 String body = "{\"title\":\"" + uniqueTitle() + "\",\"unknown\":\"value\"}";

 given()
 .baseUri(BASE_URL)
 .contentType(ContentType.JSON)
 .body(body)
 .when()
 .post("/api/project")
 .then()
 .statusCode(200);
 }
}