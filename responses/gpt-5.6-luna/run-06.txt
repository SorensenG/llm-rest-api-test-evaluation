import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.*;

public class GeneratedApiTest {

 @BeforeAll
 static void setup() {
 RestAssured.baseURI = "http://localhost:8080";
 }

 private String uniqueTitle() {
 return "Project-" + java.util.UUID.randomUUID();
 }

 private String createProject(String title) {
 Response response = given()
 .contentType(ContentType.JSON)
 .body("{\"title\":\"" + title + "\"}")
 .post("/api/project");

 assertEquals(200, response.statusCode());
 String code = response.jsonPath().getString("code");
 assertNotNull(code);
 return code;
 }

 private String validCode() {
 return "T" + java.util.UUID.randomUUID()
 .toString().replace("-", "").substring(0, 5);
 }

 @Test
 void createProjectWithValidTitle() {
 given()
 .contentType(ContentType.JSON)
 .body("{\"title\":\"Valid Project\"}")
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
 void createProjectWithTitleLongerThanMaximum() {
 String title = "A".repeat(201);

 given()
 .contentType(ContentType.JSON)
 .body("{\"title\":\"" + title + "\"}")
 .when()
 .post("/api/project")
 .then()
 .statusCode(200);
 }

 @Test
 void createProjectWithoutTitle() {
 given()
 .contentType(ContentType.JSON)
 .body("{}")
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
 void createProjectWithAdditionalProperties() {
 given()
 .contentType(ContentType.JSON)
 .body("{\"title\":\"Additional Fields\",\"extra\":\"value\"}")
 .when()
 .post("/api/project")
 .then()
 .statusCode(200);
 }

 @Test
 void createProjectWithEmptyProblemArray() {
 given()
 .contentType(ContentType.JSON)
 .body("{\"title\":\"Empty Problems\",\"problem\":[]}")
 .when()
 .post("/api/project")
 .then()
 .statusCode(200);
 }

 @Test
 void createProjectWithNullProblemArray() {
 given()
 .contentType(ContentType.JSON)
 .body("{\"title\":\"Null Problems\",\"problem\":null}")
 .when()
 .post("/api/project")
 .then()
 .statusCode(200);
 }

 @Test
 void createProjectWithDuplicateProblems() {
 String body = """
 {
 "title":"Duplicate Problems",
 "problem":[
 {"title":"Problem A"},
 {"title":"Problem A"}
 ]
 }
 """;

 given()
 .contentType(ContentType.JSON)
 .body(body)
 .when()
 .post("/api/project")
 .then()
 .statusCode(200);
 }

 @Test
 void getProjectUsingValidCode() {
 String code = createProject(uniqueTitle());

 given()
 .pathParam("code", code)
 .when()
 .get("/api/project/{code}")
 .then()
 .statusCode(200);
 }

 @Test
 void getProjectUsingSixCharacterCode() {
 given()
 .pathParam("code", validCode())
 .when()
 .get("/api/project/{code}")
 .then()
 .statusCode(200);
 }

 @Test
 void getProjectUsingNonexistentCode() {
 given()
 .pathParam("code", validCode())
 .when()
 .get("/api/project/{code}")
 .then()
 .statusCode(200);
 }

 @Test
 void getProjectUsingEmptyCode() {
 given()
 .when()
 .get("/api/project/")
 .then()
 .statusCode(404);
 }

 @Test
 void getProjectUsingShortCode() {
 given()
 .pathParam("code", "ABC12")
 .when()
 .get("/api/project/{code}")
 .then()
 .statusCode(200);
 }

 @Test
 void getProjectUsingLongCode() {
 given()
 .pathParam("code", "ABCDEFG")
 .when()
 .get("/api/project/{code}")
 .then()
 .statusCode(200);
 }

 @Test
 void getProjectUsingSpecialCharactersInCode() {
 given()
 .pathParam("code", "AB@123")
 .when()
 .get("/api/project/{code}")
 .then()
 .statusCode(200);
 }

 @Test
 void updateProjectWithValidTitle() {
 String code = createProject(uniqueTitle());

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
 void updateProjectWithEmptyTitle() {
 String code = createProject(uniqueTitle());

 given()
 .contentType(ContentType.JSON)
 .pathParam("code", code)
 .body("{\"title\":\"\"}")
 .when()
 .put("/api/project/{code}")
 .then()
 .statusCode(200);
 }

 @Test
 void updateProjectWithMaximumLengthTitle() {
 String code = createProject(uniqueTitle());
 String title = "B".repeat(200);

 given()
 .contentType(ContentType.JSON)
 .pathParam("code", code)
 .body("{\"title\":\"" + title + "\"}")
 .when()
 .put("/api/project/{code}")
 .then()
 .statusCode(200);
 }

 @Test
 void updateProjectWithTitleLongerThanMaximum() {
 String code = createProject(uniqueTitle());
 String title = "B".repeat(201);

 given()
 .contentType(ContentType.JSON)
 .pathParam("code", code)
 .body("{\"title\":\"" + title + "\"}")
 .when()
 .put("/api/project/{code}")
 .then()
 .statusCode(200);
 }

 @Test
 void updateProjectWithoutTitle() {
 String code = createProject(uniqueTitle());

 given()
 .contentType(ContentType.JSON)
 .pathParam("code", code)
 .body("{}")
 .when()
 .put("/api/project/{code}")
 .then()
 .statusCode(200);
 }

 @Test
 void updateProjectWithNullTitle() {
 String code = createProject(uniqueTitle());

 given()
 .contentType(ContentType.JSON)
 .pathParam("code", code)
 .body("{\"title\":null}")
 .when()
 .put("/api/project/{code}")
 .then()
 .statusCode(200);
 }

 @Test
 void updateProjectWithNonexistentCode() {
 given()
 .contentType(ContentType.JSON)
 .pathParam("code", validCode())
 .body("{\"title\":\"Updated Project\"}")
 .when()
 .put("/api/project/{code}")
 .then()
 .statusCode(200);
 }

 @Test
 void updateProjectWithProblemArray() {
 String code = createProject(uniqueTitle());

 given()
 .contentType(ContentType.JSON)
 .pathParam("code", code)
 .body("""
 {
 "title":"Updated With Problems",
 "problem":[
 {"title":"Problem One"},
 {"title":"Problem Two"}
 ]
 }
 """)
 .when()
 .put("/api/project/{code}")
 .then()
 .statusCode(200);
 }

 @Test
 void deleteExistingProject() {
 String code = createProject(uniqueTitle());

 given()
 .pathParam("code", code)
 .when()
 .delete("/api/project/{code}")
 .then()
 .statusCode(200);
 }

 @Test
 void deleteNonexistentProject() {
 given()
 .pathParam("code", validCode())
 .when()
 .delete("/api/project/{code}")
 .then()
 .statusCode(200);
 }

 @Test
 void deleteProjectWithInvalidCode() {
 given()
 .pathParam("code", "INVALID")
 .when()
 .delete("/api/project/{code}")
 .then()
 .statusCode(200);
 }

 @Test
 void createProblemForExistingProject() {
 String code = createProject(uniqueTitle());

 given()
 .contentType(ContentType.JSON)
 .pathParam("code", code)
 .body("{\"title\":\"Problem One\"}")
 .when()
 .post("/api/problem/{code}")
 .then()
 .statusCode(200);
 }

 @Test
 void createProblemWithAllProperties() {
 String code = createProject(uniqueTitle());

 given()
 .contentType(ContentType.JSON)
 .pathParam("code", code)
 .body("""
 {
 "idx":1,
 "title":"Problem Title",
 "problemCode":"ABC123",
 "project":{
 "title":"Nested Project"
 }
 }
 """)
 .when()
 .post("/api/problem/{code}")
 .then()
 .statusCode(200);
 }

 @Test
 void createProblemWithEmptyTitle() {
 String code = createProject(uniqueTitle());

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
 void createProblemWithNullTitle() {
 String code = createProject(uniqueTitle());

 given()
 .contentType(ContentType.JSON)
 .pathParam("code", code)
 .body("{\"title\":null}")
 .when()
 .post("/api/problem/{code}")
 .then()
 .statusCode(200);
 }

 @Test
 void createProblemWithEmptyObject() {
 String code = createProject(uniqueTitle());

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
 void createProblemWithNonexistentProjectCode() {
 given()
 .contentType(ContentType.JSON)
 .pathParam("code", validCode())
 .body("{\"title\":\"Orphan Problem\"}")
 .when()
 .post("/api/problem/{code}")
 .then()
 .statusCode(200);
 }

 @Test
 void createProblemWithInvalidProjectCodeLength() {
 given()
 .contentType(ContentType.JSON)
 .pathParam("code", "ABC")
 .body("{\"title\":\"Problem\"}")
 .when()
 .post("/api/problem/{code}")
 .then()
 .statusCode(200);
 }

 @Test
 void createProblemWithMaximumIntegerIndex() {
 String code = createProject(uniqueTitle());

 given()
 .contentType(ContentType.JSON)
 .pathParam("code", code)
 .body("{\"idx\":9223372036854775807,\"title\":\"Large Index\"}")
 .when()
 .post("/api/problem/{code}")
 .then()
 .statusCode(200);
 }

 @Test
 void getProblemsForExistingProject() {
 String code = createProject(uniqueTitle());

 given()
 .pathParam("code", code)
 .when()
 .get("/api/problem/{code}")
 .then()
 .statusCode(200);
 }

 @Test
 void getProblemsForNonexistentProject() {
 given()
 .pathParam("code", validCode())
 .when()
 .get("/api/problem/{code}")
 .then()
 .statusCode(200);
 }

 @Test
 void getProblemsAfterCreatingMultipleProblems() {
 String code = createProject(uniqueTitle());

 given()
 .contentType(ContentType.JSON)
 .pathParam("code", code)
 .body("{\"title\":\"First Problem\"}")
 .post("/api/problem/{code}")
 .then()
 .statusCode(200);

 given()
 .contentType(ContentType.JSON)
 .pathParam("code", code)
 .body("{\"title\":\"Second Problem\"}")
 .post("/api/problem/{code}")
 .then()
 .statusCode(200);

 given()
 .pathParam("code", code)
 .when()
 .get("/api/problem/{code}")
 .then()
 .statusCode(200);
 }

 @Test
 void deleteMostRecentProblem() {
 String code = createProject(uniqueTitle());

 given()
 .contentType(ContentType.JSON)
 .pathParam("code", code)
 .body("{\"title\":\"Problem To Delete\"}")
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
 void deleteProblemWhenNoProblemsExist() {
 String code = createProject(uniqueTitle());

 given()
 .pathParam("code", code)
 .when()
 .delete("/api/problem/{code}")
 .then()
 .statusCode(200);
 }

 @Test
 void deleteProblemForNonexistentProject() {
 given()
 .pathParam("code", validCode())
 .when()
 .delete("/api/problem/{code}")
 .then()
 .statusCode(200);
 }

 @Test
 void deleteAllProblemsForExistingProject() {
 String code = createProject(uniqueTitle());

 given()
 .contentType(ContentType.JSON)
 .pathParam("code", code)
 .body("{\"title\":\"Problem One\"}")
 .post("/api/problem/{code}")
 .then()
 .statusCode(200);

 given()
 .contentType(ContentType.JSON)
 .pathParam("code", code)
 .body("{\"title\":\"Problem Two\"}")
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
 void deleteAllProblemsWhenNoneExist() {
 String code = createProject(uniqueTitle());

 given()
 .pathParam("code", code)
 .when()
 .delete("/api/problem/{code}/all")
 .then()
 .statusCode(200);
 }

 @Test
 void deleteAllProblemsForNonexistentProject() {
 given()
 .pathParam("code", validCode())
 .when()
 .delete("/api/problem/{code}/all")
 .then()
 .statusCode(200);
 }

 @Test
 void verifyProblemListAfterDeletingAllProblems() {
 String code = createProject(uniqueTitle());

 given()
 .contentType(ContentType.JSON)
 .pathParam("code", code)
 .body("{\"title\":\"Problem One\"}")
 .post("/api/problem/{code}");

 given()
 .contentType(ContentType.JSON)
 .pathParam("code", code)
 .body("{\"title\":\"Problem Two\"}")
 .post("/api/problem/{code}");

 given()
 .pathParam("code", code)
 .delete("/api/problem/{code}/all")
 .then()
 .statusCode(200);

 given()
 .pathParam("code", code)
 .get("/api/problem/{code}")
 .then()
 .statusCode(200)
 .body("$", org.hamcrest.Matchers.empty());
 }

 @Test
 void saveSubProblemWithValidData() {
 given()
 .contentType(ContentType.JSON)
 .body("{\"pro_idx\":1,\"content\":\"Subproblem content\"}")
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
 void saveSubProblemWithMissingContent() {
 given()
 .contentType(ContentType.JSON)
 .body("{\"pro_idx\":1}")
 .when()
 .post("/api/subproblem")
 .then()
 .statusCode(200);
 }

 @Test
 void saveSubProblemWithMissingIndex() {
 given()
 .contentType(ContentType.JSON)
 .body("{\"content\":\"Subproblem content\"}")
 .when()
 .post("/api/subproblem")
 .then()
 .statusCode(200);
 }

 @Test
 void saveSubProblemWithNullIndex() {
 given()
 .contentType(ContentType.JSON)
 .body("{\"pro_idx\":null,\"content\":\"Subproblem content\"}")
 .when()
 .post("/api/subproblem")
 .then()
 .statusCode(200);
 }

 @Test
 void saveSubProblemWithZeroIndex() {
 given()
 .contentType(ContentType.JSON)
 .body("{\"pro_idx\":0,\"content\":\"Zero index\"}")
 .when()
 .post("/api/subproblem")
 .then()
 .statusCode(200);
 }

 @Test
 void saveSubProblemWithNegativeIndex() {
 given()
 .contentType(ContentType.JSON)
 .body("{\"pro_idx\":-1,\"content\":\"Negative index\"}")
 .when()
 .post("/api/subproblem")
 .then()
 .statusCode(200);
 }

 @Test
 void saveSubProblemWithMaximumLongIndex() {
 given()
 .contentType(ContentType.JSON)
 .body("{\"pro_idx\":9223372036854775807,\"content\":\"Maximum index\"}")
 .when()
 .post("/api/subproblem")
 .then()
 .statusCode(200);
 }

 @Test
 void saveSubProblemWithMinimumLongIndex() {
 given()
 .contentType(ContentType.JSON)
 .body("{\"pro_idx\":-9223372036854775808,\"content\":\"Minimum index\"}")
 .when()
 .post("/api/subproblem")
 .then()
 .statusCode(200);
 }

 @Test
 void saveSubProblemWithEmptyObject() {
 given()
 .contentType(ContentType.JSON)
 .body("{}")
 .when()
 .post("/api/subproblem")
 .then()
 .statusCode(200);
 }

 @Test
 void saveSubProblemWithAdditionalProperties() {
 given()
 .contentType(ContentType.JSON)
 .body("{\"pro_idx\":1,\"content\":\"Content\",\"extra\":\"value\"}")
 .when()
 .post("/api/subproblem")
 .then()
 .statusCode(200);
 }

 @Test
 void createProjectThenRetrieveAndDeleteIt() {
 String code = createProject(uniqueTitle());

 given()
 .pathParam("code", code)
 .get("/api/project/{code}")
 .then()
 .statusCode(200);

 given()
 .pathParam("code", code)
 .delete("/api/project/{code}")
 .then()
 .statusCode(200);
 }

 @Test
 void createProjectAddProblemsRetrieveAndDeleteAll() {
 String code = createProject(uniqueTitle());

 given()
 .contentType(ContentType.JSON)
 .pathParam("code", code)
 .body("{\"title\":\"Problem One\"}")
 .post("/api/problem/{code}")
 .then()
 .statusCode(200);

 given()
 .contentType(ContentType.JSON)
 .pathParam("code", code)
 .body("{\"title\":\"Problem Two\"}")
 .post("/api/problem/{code}")
 .then()
 .statusCode(200);

 given()
 .pathParam("code", code)
 .get("/api/problem/{code}")
 .then()
 .statusCode(200);

 given()
 .pathParam("code", code)
 .delete("/api/problem/{code}/all")
 .then()
 .statusCode(200);

 given()
 .pathParam("code", code)
 .get("/api/problem/{code}")
 .then()
 .statusCode(200);
 }

 @Test
 void deleteProjectAfterAddingProblems() {
 String code = createProject(uniqueTitle());

 given()
 .contentType(ContentType.JSON)
 .pathParam("code", code)
 .body("{\"title\":\"Associated Problem\"}")
 .post("/api/problem/{code}")
 .then()
 .statusCode(200);

 given()
 .pathParam("code", code)
 .delete("/api/project/{code}")
 .then()
 .statusCode(200);
 }

 @Test
 void updateProjectThenRetrieveUpdatedProject() {
 String code = createProject(uniqueTitle());

 given()
 .contentType(ContentType.JSON)
 .pathParam("code", code)
 .body("{\"title\":\"Updated Title\"}")
 .put("/api/project/{code}")
 .then()
 .statusCode(200);

 given()
 .pathParam("code", code)
 .get("/api/project/{code}")
 .then()
 .statusCode(200);
 }

 @Test
 void deleteSingleProblemThenRetrieveProblemList() {
 String code = createProject(uniqueTitle());

 given()
 .contentType(ContentType.JSON)
 .pathParam("code", code)
 .body("{\"title\":\"First Problem\"}")
 .post("/api/problem/{code}")
 .then()
 .statusCode(200);

 given()
 .contentType(ContentType.JSON)
 .pathParam("code", code)
 .body("{\"title\":\"Second Problem\"}")
 .post("/api/problem/{code}")
 .then()
 .statusCode(200);

 given()
 .pathParam("code", code)
 .delete("/api/problem/{code}")
 .then()
 .statusCode(200);

 given()
 .pathParam("code", code)
 .get("/api/problem/{code}")
 .then()
 .statusCode(200);
 }

 @Test
 void deleteAllProblemsThenDeleteProject() {
 String code = createProject(uniqueTitle());

 given()
 .contentType(ContentType.JSON)
 .pathParam("code", code)
 .body("{\"title\":\"Problem\"}")
 .post("/api/problem/{code}")
 .then()
 .statusCode(200);

 given()
 .pathParam("code", code)
 .delete("/api/problem/{code}/all")
 .then()
 .statusCode(200);

 given()
 .pathParam("code", code)
 .delete("/api/project/{code}")
 .then()
 .statusCode(200);
 }
}