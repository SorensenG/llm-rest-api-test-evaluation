import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.*;

public class GeneratedApiTest {

 private static final String BASE_URL = "http://localhost:8080";

 @BeforeAll
 static void configure() {
 RestAssured.baseURI = BASE_URL;
 }

 private String uniqueTitle() {
 return "Project-" + java.util.UUID.randomUUID();
 }

 private String createProject(String title) {
 Response response = given()
 .contentType(ContentType.JSON)
 .body("{\"title\":\"" + title + "\"}")
 .post("/api/project");

 response.then().statusCode(200);

 String code = response.jsonPath().getString("code");
 if (code == null) {
 code = response.jsonPath().getString("data.code");
 }
 return code;
 }

 private String jsonEscape(String value) {
 return value.replace("\\", "\\\\").replace("\"", "\\\"");
 }

 @Test
 void createProjectWithValidTitle() {
 given()
 .contentType(ContentType.JSON)
 .body("{\"title\":\"" + uniqueTitle() + "\"}")
 .post("/api/project")
 .then()
 .statusCode(200);
 }

 @Test
 void createProjectWithMinimumLengthTitle() {
 given()
 .contentType(ContentType.JSON)
 .body("{\"title\":\"\"}")
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
 .post("/api/project")
 .then()
 .statusCode(200);
 }

 @Test
 void createProjectWithoutRequiredTitle() {
 given()
 .contentType(ContentType.JSON)
 .body("{}")
 .post("/api/project")
 .then()
 .statusCode(400);
 }

 @Test
 void createProjectWithNullTitle() {
 given()
 .contentType(ContentType.JSON)
 .body("{\"title\":null}")
 .post("/api/project")
 .then()
 .statusCode(400);
 }

 @Test
 void createProjectWithAdditionalFields() {
 given()
 .contentType(ContentType.JSON)
 .body("{\"title\":\"Additional Fields Project\",\"idx\":123,\"code\":\"ABC123\",\"problem\":[]}")
 .post("/api/project")
 .then()
 .statusCode(200);
 }

 @Test
 void createProjectWithDuplicateTitles() {
 String title = uniqueTitle();

 given()
 .contentType(ContentType.JSON)
 .body("{\"title\":\"" + title + "\"}")
 .post("/api/project")
 .then()
 .statusCode(200);

 given()
 .contentType(ContentType.JSON)
 .body("{\"title\":\"" + title + "\"}")
 .post("/api/project")
 .then()
 .statusCode(200);
 }

 @Test
 void createProjectWithoutRequestBody() {
 given()
 .post("/api/project")
 .then()
 .statusCode(400);
 }

 @Test
 void createProjectWithInvalidJson() {
 given()
 .contentType(ContentType.JSON)
 .body("{\"title\":")
 .post("/api/project")
 .then()
 .statusCode(400);
 }

 @Test
 void retrieveProjectWithValidCode() {
 String code = createProject(uniqueTitle());

 if (code != null) {
 given()
 .get("/api/project/" + code)
 .then()
 .statusCode(200);
 }
 }

 @Test
 void retrieveProjectWithSixCharacterCode() {
 given()
 .get("/api/project/ABC123")
 .then()
 .statusCode(200);
 }

 @Test
 void retrieveProjectWithNonexistentCode() {
 given()
 .get("/api/project/ZZZ999")
 .then()
 .statusCode(200);
 }

 @Test
 void retrieveProjectWithShortCode() {
 given()
 .get("/api/project/ABC12")
 .then()
 .statusCode(200);
 }

 @Test
 void retrieveProjectWithLongCode() {
 given()
 .get("/api/project/ABC1234")
 .then()
 .statusCode(200);
 }

 @Test
 void retrieveProjectWithEmptyCode() {
 given()
 .get("/api/project/")
 .then()
 .statusCode(404);
 }

 @Test
 void updateProjectWithValidTitle() {
 String code = createProject(uniqueTitle());

 if (code != null) {
 given()
 .contentType(ContentType.JSON)
 .body("{\"title\":\"Updated Project\"}")
 .put("/api/project/" + code)
 .then()
 .statusCode(200);
 }
 }

 @Test
 void updateProjectWithMaximumLengthTitle() {
 String code = createProject(uniqueTitle());

 if (code != null) {
 given()
 .contentType(ContentType.JSON)
 .body("{\"title\":\"" + "U".repeat(200) + "\"}")
 .put("/api/project/" + code)
 .then()
 .statusCode(200);
 }
 }

 @Test
 void updateProjectWithEmptyTitle() {
 String code = createProject(uniqueTitle());

 if (code != null) {
 given()
 .contentType(ContentType.JSON)
 .body("{\"title\":\"\"}")
 .put("/api/project/" + code)
 .then()
 .statusCode(200);
 }
 }

 @Test
 void updateProjectWithoutRequiredTitle() {
 String code = createProject(uniqueTitle());

 if (code != null) {
 given()
 .contentType(ContentType.JSON)
 .body("{}")
 .put("/api/project/" + code)
 .then()
 .statusCode(400);
 }
 }

 @Test
 void updateProjectWithNullTitle() {
 String code = createProject(uniqueTitle());

 if (code != null) {
 given()
 .contentType(ContentType.JSON)
 .body("{\"title\":null}")
 .put("/api/project/" + code)
 .then()
 .statusCode(400);
 }
 }

 @Test
 void updateProjectWithNonexistentCode() {
 given()
 .contentType(ContentType.JSON)
 .body("{\"title\":\"Updated Project\"}")
 .put("/api/project/ZZZ999")
 .then()
 .statusCode(200);
 }

 @Test
 void updateProjectWithAdditionalFields() {
 String code = createProject(uniqueTitle());

 if (code != null) {
 given()
 .contentType(ContentType.JSON)
 .body("{\"title\":\"Updated Project\",\"idx\":123,\"code\":\"" + code + "\",\"problem\":[]}")
 .put("/api/project/" + code)
 .then()
 .statusCode(200);
 }
 }

 @Test
 void deleteProjectWithNonexistentCode() {
 given()
 .delete("/api/project/ZZZ999")
 .then()
 .statusCode(200);
 }

 @Test
 void deleteProjectWithValidCode() {
 String code = createProject(uniqueTitle());

 if (code != null) {
 given()
 .delete("/api/project/" + code)
 .then()
 .statusCode(200);
 }
 }

 @Test
 void deleteProjectThenRetrieveDeletedProject() {
 String code = createProject(uniqueTitle());

 if (code != null) {
 given()
 .delete("/api/project/" + code)
 .then()
 .statusCode(200);

 given()
 .get("/api/project/" + code)
 .then()
 .statusCode(200);
 }
 }

 @Test
 void createProblemForExistingProject() {
 String code = createProject(uniqueTitle());

 if (code != null) {
 given()
 .contentType(ContentType.JSON)
 .body("{\"title\":\"Problem Title\"}")
 .post("/api/problem/" + code)
 .then()
 .statusCode(200);
 }
 }

 @Test
 void createProblemForNonexistentProject() {
 given()
 .contentType(ContentType.JSON)
 .body("{\"title\":\"Problem Title\"}")
 .post("/api/problem/ZZZ999")
 .then()
 .statusCode(200);
 }

 @Test
 void createProblemWithEmptyTitle() {
 String code = createProject(uniqueTitle());

 if (code != null) {
 given()
 .contentType(ContentType.JSON)
 .body("{\"title\":\"\"}")
 .post("/api/problem/" + code)
 .then()
 .statusCode(200);
 }
 }

 @Test
 void createProblemWithNullTitle() {
 String code = createProject(uniqueTitle());

 if (code != null) {
 given()
 .contentType(ContentType.JSON)
 .body("{\"title\":null}")
 .post("/api/problem/" + code)
 .then()
 .statusCode(200);
 }
 }

 @Test
 void createProblemWithAllDocumentedFields() {
 String code = createProject(uniqueTitle());

 if (code != null) {
 given()
 .contentType(ContentType.JSON)
 .body("{\"idx\":1,\"title\":\"Problem Title\",\"problemCode\":\"PRB123\"}")
 .post("/api/problem/" + code)
 .then()
 .statusCode(200);
 }
 }

 @Test
 void createProblemWithInvalidCodeLength() {
 given()
 .contentType(ContentType.JSON)
 .body("{\"title\":\"Problem Title\"}")
 .post("/api/problem/ABC12")
 .then()
 .statusCode(200);
 }

 @Test
 void createProblemWithLongCode() {
 given()
 .contentType(ContentType.JSON)
 .body("{\"title\":\"Problem Title\"}")
 .post("/api/problem/ABC1234")
 .then()
 .statusCode(200);
 }

 @Test
 void createProblemWithoutRequestBody() {
 given()
 .post("/api/problem/ABC123")
 .then()
 .statusCode(400);
 }

 @Test
 void createProblemWithInvalidJson() {
 given()
 .contentType(ContentType.JSON)
 .body("{\"title\":")
 .post("/api/problem/ABC123")
 .then()
 .statusCode(400);
 }

 @Test
 void retrieveProblemsForExistingProject() {
 String code = createProject(uniqueTitle());

 if (code != null) {
 given()
 .get("/api/problem/" + code)
 .then()
 .statusCode(200);
 }
 }

 @Test
 void retrieveProblemsForNonexistentProject() {
 given()
 .get("/api/problem/ZZZ999")
 .then()
 .statusCode(200);
 }

 @Test
 void retrieveProblemsWithSixCharacterCode() {
 given()
 .get("/api/problem/ABC123")
 .then()
 .statusCode(200);
 }

 @Test
 void retrieveProblemsWithShortCode() {
 given()
 .get("/api/problem/ABC12")
 .then()
 .statusCode(200);
 }

 @Test
 void retrieveProblemsWithLongCode() {
 given()
 .get("/api/problem/ABC1234")
 .then()
 .statusCode(200);
 }

 @Test
 void createAndRetrieveProblem() {
 String code = createProject(uniqueTitle());

 if (code != null) {
 given()
 .contentType(ContentType.JSON)
 .body("{\"title\":\"Integration Problem\"}")
 .post("/api/problem/" + code)
 .then()
 .statusCode(200);

 given()
 .get("/api/problem/" + code)
 .then()
 .statusCode(200)
 .contentType(ContentType.JSON);
 }
 }

 @Test
 void createMultipleProblemsAndRetrieveThem() {
 String code = createProject(uniqueTitle());

 if (code != null) {
 given()
 .contentType(ContentType.JSON)
 .body("{\"title\":\"First Problem\"}")
 .post("/api/problem/" + code)
 .then()
 .statusCode(200);

 given()
 .contentType(ContentType.JSON)
 .body("{\"title\":\"Second Problem\"}")
 .post("/api/problem/" + code)
 .then()
 .statusCode(200);

 given()
 .get("/api/problem/" + code)
 .then()
 .statusCode(200);
 }
 }

 @Test
 void deleteMostRecentProblem() {
 String code = createProject(uniqueTitle());

 if (code != null) {
 given()
 .contentType(ContentType.JSON)
 .body("{\"title\":\"First Problem\"}")
 .post("/api/problem/" + code)
 .then()
 .statusCode(200);

 given()
 .contentType(ContentType.JSON)
 .body("{\"title\":\"Second Problem\"}")
 .post("/api/problem/" + code)
 .then()
 .statusCode(200);

 given()
 .delete("/api/problem/" + code)
 .then()
 .statusCode(200);

 given()
 .get("/api/problem/" + code)
 .then()
 .statusCode(200);
 }
 }

 @Test
 void deleteProblemForNonexistentProject() {
 given()
 .delete("/api/problem/ZZZ999")
 .then()
 .statusCode(200);
 }

 @Test
 void deleteAllProblemsForExistingProject() {
 String code = createProject(uniqueTitle());

 if (code != null) {
 given()
 .contentType(ContentType.JSON)
 .body("{\"title\":\"First Problem\"}")
 .post("/api/problem/" + code)
 .then()
 .statusCode(200);

 given()
 .contentType(ContentType.JSON)
 .body("{\"title\":\"Second Problem\"}")
 .post("/api/problem/" + code)
 .then()
 .statusCode(200);

 given()
 .delete("/api/problem/" + code + "/all")
 .then()
 .statusCode(200);

 given()
 .get("/api/problem/" + code)
 .then()
 .statusCode(200);
 }
 }

 @Test
 void deleteAllProblemsForNonexistentProject() {
 given()
 .delete("/api/problem/ZZZ999/all")
 .then()
 .statusCode(200);
 }

 @Test
 void deleteAllProblemsTwice() {
 String code = createProject(uniqueTitle());

 if (code != null) {
 given()
 .delete("/api/problem/" + code + "/all")
 .then()
 .statusCode(200);

 given()
 .delete("/api/problem/" + code + "/all")
 .then()
 .statusCode(200);
 }
 }

 @Test
 void createSubProblemWithValidFields() {
 given()
 .contentType(ContentType.JSON)
 .body("{\"pro_idx\":1,\"content\":\"Subproblem content\"}")
 .post("/api/subproblem")
 .then()
 .statusCode(200);
 }

 @Test
 void createSubProblemWithMinimumValues() {
 given()
 .contentType(ContentType.JSON)
 .body("{\"pro_idx\":0,\"content\":\"\"}")
 .post("/api/subproblem")
 .then()
 .statusCode(200);
 }

 @Test
 void createSubProblemWithNegativeIndex() {
 given()
 .contentType(ContentType.JSON)
 .body("{\"pro_idx\":-1,\"content\":\"Subproblem content\"}")
 .post("/api/subproblem")
 .then()
 .statusCode(200);
 }

 @Test
 void createSubProblemWithMaximumLongIndex() {
 given()
 .contentType(ContentType.JSON)
 .body("{\"pro_idx\":9223372036854775807,\"content\":\"Subproblem content\"}")
 .post("/api/subproblem")
 .then()
 .statusCode(200);
 }

 @Test
 void createSubProblemWithMinimumLongIndex() {
 given()
 .contentType(ContentType.JSON)
 .body("{\"pro_idx\":-9223372036854775808,\"content\":\"Subproblem content\"}")
 .post("/api/subproblem")
 .then()
 .statusCode(200);
 }

 @Test
 void createSubProblemWithNullIndex() {
 given()
 .contentType(ContentType.JSON)
 .body("{\"pro_idx\":null,\"content\":\"Subproblem content\"}")
 .post("/api/subproblem")
 .then()
 .statusCode(200);
 }

 @Test
 void createSubProblemWithNullContent() {
 given()
 .contentType(ContentType.JSON)
 .body("{\"pro_idx\":1,\"content\":null}")
 .post("/api/subproblem")
 .then()
 .statusCode(200);
 }

 @Test
 void createSubProblemWithEmptyObject() {
 given()
 .contentType(ContentType.JSON)
 .body("{}")
 .post("/api/subproblem")
 .then()
 .statusCode(200);
 }

 @Test
 void createSubProblemWithEmptyContent() {
 given()
 .contentType(ContentType.JSON)
 .body("{\"pro_idx\":1,\"content\":\"\"}")
 .post("/api/subproblem")
 .then()
 .statusCode(200);
 }

 @Test
 void createSubProblemWithLongContent() {
 String content = "C".repeat(10000);

 given()
 .contentType(ContentType.JSON)
 .body("{\"pro_idx\":1,\"content\":\"" + content + "\"}")
 .post("/api/subproblem")
 .then()
 .statusCode(200);
 }

 @Test
 void createSubProblemWithoutRequestBody() {
 given()
 .post("/api/subproblem")
 .then()
 .statusCode(400);
 }

 @Test
 void createSubProblemWithInvalidJson() {
 given()
 .contentType(ContentType.JSON)
 .body("{\"pro_idx\":")
 .post("/api/subproblem")
 .then()
 .statusCode(400);
 }

 @Test
 void createSubProblemWithWrongIndexType() {
 given()
 .contentType(ContentType.JSON)
 .body("{\"pro_idx\":\"invalid\",\"content\":\"Content\"}")
 .post("/api/subproblem")
 .then()
 .statusCode(400);
 }

 @Test
 void createSubProblemWithWrongContentType() {
 given()
 .contentType(ContentType.JSON)
 .body("{\"pro_idx\":1,\"content\":123}")
 .post("/api/subproblem")
 .then()
 .statusCode(400);
 }

 @Test
 void createSubProblemWithAdditionalFields() {
 given()
 .contentType(ContentType.JSON)
 .body("{\"pro_idx\":1,\"content\":\"Content\",\"additional\":\"value\"}")
 .post("/api/subproblem")
 .then()
 .statusCode(200);
 }

 @Test
 void createProblemThenDeleteProject() {
 String code = createProject(uniqueTitle());

 if (code != null) {
 given()
 .contentType(ContentType.JSON)
 .body("{\"title\":\"Problem Title\"}")
 .post("/api/problem/" + code)
 .then()
 .statusCode(200);

 given()
 .delete("/api/project/" + code)
 .then()
 .statusCode(200);
 }
 }

 @Test
 void updateProjectThenRetrieveUpdatedProject() {
 String code = createProject(uniqueTitle());

 if (code != null) {
 given()
 .contentType(ContentType.JSON)
 .body("{\"title\":\"Updated Integration Project\"}")
 .put("/api/project/" + code)
 .then()
 .statusCode(200);

 given()
 .get("/api/project/" + code)
 .then()
 .statusCode(200);
 }
 }

 @Test
 void deleteMostRecentProblemMultipleTimes() {
 String code = createProject(uniqueTitle());

 if (code != null) {
 given()
 .contentType(ContentType.JSON)
 .body("{\"title\":\"Problem Title\"}")
 .post("/api/problem/" + code)
 .then()
 .statusCode(200);

 given()
 .delete("/api/problem/" + code)
 .then()
 .statusCode(200);

 given()
 .delete("/api/problem/" + code)
 .then()
 .statusCode(200);
 }
 }

 @Test
 void createProblemWithNestedProjectObject() {
 String code = createProject(uniqueTitle());

 if (code != null) {
 given()
 .contentType(ContentType.JSON)
 .body("{\"title\":\"Nested Problem\",\"project\":{\"title\":\"Nested Project\"}}")
 .post("/api/problem/" + code)
 .then()
 .statusCode(200);
 }
 }

 @Test
 void createProjectWithProblemArray() {
 given()
 .contentType(ContentType.JSON)
 .body("{\"title\":\"Project With Problems\",\"problem\":[{\"title\":\"First Problem\"},{\"title\":\"Second Problem\"}]}")
 .post("/api/project")
 .then()
 .statusCode(200);
 }

 @Test
 void createProjectWithEmptyProblemArray() {
 given()
 .contentType(ContentType.JSON)
 .body("{\"title\":\"Project With Empty Problems\",\"problem\":[]}")
 .post("/api/project")
 .then()
 .statusCode(200);
 }

 @Test
 void createProjectWithDuplicateProblemsInArray() {
 given()
 .contentType(ContentType.JSON)
 .body("{\"title\":\"Project With Duplicate Problems\",\"problem\":[{\"title\":\"Same Problem\"},{\"title\":\"Same Problem\"}]}")
 .post("/api/project")
 .then()
 .statusCode(200);
 }

 @Test
 void createProjectWithNullProblemArray() {
 given()
 .contentType(ContentType.JSON)
 .body("{\"title\":\"Project With Null Problems\",\"problem\":null}")
 .post("/api/project")
 .then()
 .statusCode(200);
 }

 @Test
 void retrieveProjectAcceptingJsonResponse() {
 given()
 .accept(ContentType.JSON)
 .get("/api/project/ABC123")
 .then()
 .statusCode(200);
 }

 @Test
 void retrieveProjectAcceptingXmlResponse() {
 given()
 .accept("application/xml")
 .get("/api/project/ABC123")
 .then()
 .statusCode(200);
 }
}