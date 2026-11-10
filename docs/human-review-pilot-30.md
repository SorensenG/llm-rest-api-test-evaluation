# Revisão-piloto humana — 30 métodos

> Piloto de calibração, não uma amostra estatística. Gerado a partir do CSV preservado em `results/final-analysis/human-review.csv`. Nenhuma linha foi validada automaticamente.

## Procedimento

Para cada caso, conferir o código original indicado em `code_reference`, o relatório/evidência disponível e o OpenAPI. Preencher a decisão somente após julgamento humano; esta versão deixa todos os campos de decisão em aberto. A seleção cobre os dois modelos, prioridades HIGH/MEDIUM/LOW, aprovações, falhas de asserção, erros de execução e compilação inválida.

## Caso P-01 — `gemini-3.6-think` / repetição `05` / `testCreateProject_Success`

**Identificador CSV:** `model=gemini-3.6-think; run=05; test_method=testCreateProject_Success`
**Referência:** `tests/gemini-3.6-think/run-05.java#testCreateProject_Success`
**Prioridade:** `HIGH`

### Código Java e contexto

```java
public class GeneratedApiTest {

@BeforeAll
public static void setup() {
    String baseUrl = System.getenv("API_BASE_URL");
    if (baseUrl == null || baseUrl.trim().isEmpty()) {
        baseUrl = System.getProperty("API_BASE_URL", "${API_BASE_URL}");
    }
    if ("${API_BASE_URL}".equals(baseUrl) \vert{}\vert{} baseUrl.startsWith("$")) {
        baseUrl = "http://localhost:8080";
    }
    RestAssured.baseURI = baseUrl;
}

@Test
public void testCreateProject_Success() {
    given()
            .contentType(ContentType.JSON)
            .body("{\"title\": \"Valid Project Title\"}")
    .when()
            .post("/api/project")
    .then()
            .statusCode(201);
}
```

### Evidências e classificações propostas

- Operações REST: `POST /api/project`
- Q4 positivo proposto: `TRUE`
- Q4 negativo proposto: `FALSE`
- Q4 borda proposto: `FALSE`
- Q4 multi-endpoint proposto: `FALSE`
- Q1 — compilação: `failed`; sintaxe observada: `UNKNOWN`; executável: `UNKNOWN`; aprovado: `UNKNOWN`
- Causa registrada: `compile_failure_unknown_per_method`
- Evidência: results/official-run-2/execution-logs/gemini-3.6-think/run-05/stdout.txt

### Pontos para julgamento humano

- Confirmar ou corrigir cada categoria Q4 a partir da intenção efetiva do método.
- Confirmar se a resposta observada corresponde ao contrato OpenAPI e se a falha é do teste, da API ou inconclusiva.
- Registrar decisão, revisor, data e justificativa no CSV; não alterar o código original.

---

## Caso P-02 — `gemini-3.6-think` / repetição `05` / `testCreateProject_InvalidTitle_TooShort`

**Identificador CSV:** `model=gemini-3.6-think; run=05; test_method=testCreateProject_InvalidTitle_TooShort`
**Referência:** `tests/gemini-3.6-think/run-05.java#testCreateProject_InvalidTitle_TooShort`
**Prioridade:** `HIGH`

### Código Java e contexto

```java
public class GeneratedApiTest {

@BeforeAll
public static void setup() {
    String baseUrl = System.getenv("API_BASE_URL");
    if (baseUrl == null || baseUrl.trim().isEmpty()) {
        baseUrl = System.getProperty("API_BASE_URL", "${API_BASE_URL}");
    }
    if ("${API_BASE_URL}".equals(baseUrl) \vert{}\vert{} baseUrl.startsWith("$")) {
        baseUrl = "http://localhost:8080";
    }
    RestAssured.baseURI = baseUrl;
}

@Test
public void testCreateProject_Success() {
    given()
            .contentType(ContentType.JSON)
            .body("{\"title\": \"Valid Project Title\"}")
    .when()
            .post("/api/project")
    .then()
            .statusCode(201);
}

@Test
public void testCreateProject_InvalidTitle_TooShort() {
    given()
            .contentType(ContentType.JSON)
            .body("{\"title\": \"Tiny\"}")
    .when()
            .post("/api/project")
    .then()
            .statusCode(400);
}
```

### Evidências e classificações propostas

- Operações REST: `POST /api/project`
- Q4 positivo proposto: `FALSE`
- Q4 negativo proposto: `TRUE`
- Q4 borda proposto: `TRUE`
- Q4 multi-endpoint proposto: `FALSE`
- Q1 — compilação: `failed`; sintaxe observada: `UNKNOWN`; executável: `UNKNOWN`; aprovado: `UNKNOWN`
- Causa registrada: `compile_failure_unknown_per_method`
- Evidência: results/official-run-2/execution-logs/gemini-3.6-think/run-05/stdout.txt

### Pontos para julgamento humano

- Confirmar ou corrigir cada categoria Q4 a partir da intenção efetiva do método.
- Confirmar se a resposta observada corresponde ao contrato OpenAPI e se a falha é do teste, da API ou inconclusiva.
- Registrar decisão, revisor, data e justificativa no CSV; não alterar o código original.

---

## Caso P-03 — `gemini-3.6-think` / repetição `06` / `testCreateProject_Success`

**Identificador CSV:** `model=gemini-3.6-think; run=06; test_method=testCreateProject_Success`
**Referência:** `tests/gemini-3.6-think/run-06.java#testCreateProject_Success`
**Prioridade:** `HIGH`

### Código Java e contexto

```java
public class GeneratedApiTest {

@BeforeAll
public static void setup() {
    String baseUrl = System.getenv("API_BASE_URL");
    if (baseUrl == null || baseUrl.trim().isEmpty()) {
        baseUrl = System.getProperty("API_BASE_URL", "${API_BASE_URL}");
    }
    if ("${API_BASE_URL}".equals(baseUrl) \vert{}\vert{} baseUrl.startsWith("$")) {
        baseUrl = "http://localhost:8080";
    }
    RestAssured.baseURI = baseUrl;
}

@Test
public void testCreateProject_Success() {
    given()
            .contentType(ContentType.JSON)
            .body("{\"title\": \"Valid Project Title\"}")
    .when()
            .post("/api/project")
    .then()
            .statusCode(201);
}
```

### Evidências e classificações propostas

- Operações REST: `POST /api/project`
- Q4 positivo proposto: `TRUE`
- Q4 negativo proposto: `FALSE`
- Q4 borda proposto: `FALSE`
- Q4 multi-endpoint proposto: `FALSE`
- Q1 — compilação: `failed`; sintaxe observada: `UNKNOWN`; executável: `UNKNOWN`; aprovado: `UNKNOWN`
- Causa registrada: `compile_failure_unknown_per_method`
- Evidência: results/official-run-2/execution-logs/gemini-3.6-think/run-06/stdout.txt

### Pontos para julgamento humano

- Confirmar ou corrigir cada categoria Q4 a partir da intenção efetiva do método.
- Confirmar se a resposta observada corresponde ao contrato OpenAPI e se a falha é do teste, da API ou inconclusiva.
- Registrar decisão, revisor, data e justificativa no CSV; não alterar o código original.

---

## Caso P-04 — `gemini-3.6-think` / repetição `06` / `testCreateProject_InvalidTitle_TooShort`

**Identificador CSV:** `model=gemini-3.6-think; run=06; test_method=testCreateProject_InvalidTitle_TooShort`
**Referência:** `tests/gemini-3.6-think/run-06.java#testCreateProject_InvalidTitle_TooShort`
**Prioridade:** `HIGH`

### Código Java e contexto

```java
public class GeneratedApiTest {

@BeforeAll
public static void setup() {
    String baseUrl = System.getenv("API_BASE_URL");
    if (baseUrl == null || baseUrl.trim().isEmpty()) {
        baseUrl = System.getProperty("API_BASE_URL", "${API_BASE_URL}");
    }
    if ("${API_BASE_URL}".equals(baseUrl) \vert{}\vert{} baseUrl.startsWith("$")) {
        baseUrl = "http://localhost:8080";
    }
    RestAssured.baseURI = baseUrl;
}

@Test
public void testCreateProject_Success() {
    given()
            .contentType(ContentType.JSON)
            .body("{\"title\": \"Valid Project Title\"}")
    .when()
            .post("/api/project")
    .then()
            .statusCode(201);
}

@Test
public void testCreateProject_InvalidTitle_TooShort() {
    given()
            .contentType(ContentType.JSON)
            .body("{\"title\": \"Tiny\"}")
    .when()
            .post("/api/project")
    .then()
            .statusCode(400);
}
```

### Evidências e classificações propostas

- Operações REST: `POST /api/project`
- Q4 positivo proposto: `FALSE`
- Q4 negativo proposto: `TRUE`
- Q4 borda proposto: `TRUE`
- Q4 multi-endpoint proposto: `FALSE`
- Q1 — compilação: `failed`; sintaxe observada: `UNKNOWN`; executável: `UNKNOWN`; aprovado: `UNKNOWN`
- Causa registrada: `compile_failure_unknown_per_method`
- Evidência: results/official-run-2/execution-logs/gemini-3.6-think/run-06/stdout.txt

### Pontos para julgamento humano

- Confirmar ou corrigir cada categoria Q4 a partir da intenção efetiva do método.
- Confirmar se a resposta observada corresponde ao contrato OpenAPI e se a falha é do teste, da API ou inconclusiva.
- Registrar decisão, revisor, data e justificativa no CSV; não alterar o código original.

---

## Caso P-05 — `gemini-3.6-think` / repetição `01` / `testMultiEndpointLifecycleScenario`

**Identificador CSV:** `model=gemini-3.6-think; run=01; test_method=testMultiEndpointLifecycleScenario`
**Referência:** `tests/gemini-3.6-think/run-01.java#testMultiEndpointLifecycleScenario`
**Prioridade:** `HIGH`

### Código Java e contexto

```java
public class GeneratedApiTest {

@BeforeAll
public static void setup() {
    String baseUrl = System.getenv("API_BASE_URL");
    if (baseUrl == null || baseUrl.trim().isEmpty()) {
        baseUrl = System.getProperty("API_BASE_URL", "http://localhost:8080");
    }
    RestAssured.baseURI = baseUrl;
}

@Test
public void testCreateProjectSuccess() {
    given()
        .contentType(ContentType.JSON)
        .body("""
            {
                "title": "Valid Project Title"
            }
            """)
    .when()
        .post("/api/project")
    .then()
        .statusCode(201);
}

@Test
public void testCreateProjectBoundaryTitleLengthFour() {
    given()
        .contentType(ContentType.JSON)
        .body("""
            {
                "title": "Four"
            }
            """)
    .when()
        .post("/api/project")
    .then()
        .statusCode(400);
}

@Test
public void testCreateProjectBoundaryTitleLengthFive() {
    given()
        .contentType(ContentType.JSON)
        .body("""
            {
                "title": "Five5"
            }
            """)
    .when()
        .post("/api/project")
    .then()
        .statusCode(201);
}

@Test
public void testGetProjectInvalidCodeLength() {
    given()
    .when()
        .get("/api/project/12345")
    .then()
        .statusCode(400);
}

@Test
public void testGetProjectNotFound() {
    given()
    .when()
        .get("/api/project/NOTFOUND123")
    .then()
        .statusCode(404);
}

@Test
public void testUpdateProjectInvalidCodeLength() {
    given()
        .contentType(ContentType.JSON)
        .body("""
            {
                "title": "Updated Title"
            }
            """)
    .when()
        .put("/api/project/12345")
    .then()
        .statusCode(400);
}

@Test
public void testUpdateProjectNotFound() {
    given()
        .contentType(ContentType.JSON)
        .body("""
            {
                "title": "Updated Title"
            }
            """)
    .when()
        .put("/api/project/NOTFOUND123")
    .then()
        .statusCode(404);
}

@Test
public void testDeleteProjectInvalidCodeLength() {
    given()
    .when()
        .delete("/api/project/12345")
    .then()
        .statusCode(400);
}

@Test
public void testDeleteProjectNotFound() {
    given()
    .when()
        .delete("/api/project/NOTFOUND123")
    .then()
        .statusCode(404);
}

@Test
public void testGetProblemsInvalidCodeLength() {
    given()
    .when()
        .get("/api/problem/12345")
    .then()
        .statusCode(400);
}

@Test
public void testGetProblemsNotFound() {
    given()
    .when()
        .get("/api/problem/NOTFOUND123")
    .then()
        .statusCode(404);
}

@Test
public void testCreateProblemInvalidCodeLength() {
    given()
        .contentType(ContentType.JSON)
        .body("""
            {
                "title": "Problem Title"
            }
            """)
    .when()
        .post("/api/problem/12345")
    .then()
        .statusCode(400);
}

@Test
public void testCreateProblemProjectNotFound() {
    given()
        .contentType(ContentType.JSON)
        .body("""
            {
                "title": "Problem Title"
            }
            """)
    .when()
        .post("/api/problem/NOTFOUND123")
    .then()
        .statusCode(404);
}

@Test
public void testDeleteProblemsInvalidCodeLength() {
    given()
    .when()
        .delete("/api/problem/12345")
    .then()
        .statusCode(400);
}

@Test
public void testDeleteProblemsAllInvalidCodeLength() {
    given()
    .when()
        .delete("/api/problem/12345/all")
    .then()
        .statusCode(400);
}

@Test
public void testDeleteProblemsAllNotFound() {
    given()
    .when()
        .delete("/api/problem/NOTFOUND123/all")
    .then()
        .statusCode(404);
}

@Test
public void testCreateSubProblemBoundaryContentLengthNine() {
    given()
        .contentType(ContentType.JSON)
        .body("""
            {
                "content": "123456789",
                "pro_idx": 1
            }
            """)
    .when()
        .post("/api/subproblem")
    .then()
        .statusCode(400);
}

@Test
public void testCreateSubProblemProblemNotFound() {
    given()
        .contentType(ContentType.JSON)
        .body("""
            {
                "content": "1234567890",
                "pro_idx": 999999999
            }
            """)
    .when()
        .post("/api/subproblem")
    .then()
        .statusCode(404);
}

@Test
public void testMultiEndpointLifecycleScenario() {
    Response createProjectResponse = given()
        .contentType(ContentType.JSON)
        .body("""
            {
                "title": "Multi Endpoint Test Project"
            }
            """)
    .when()
        .post("/api/project")
    .then()
        .statusCode(201)
        .extract().response();

    String code = createProjectResponse.jsonPath().getString("code");
    if (code != null && !code.isEmpty()) {
        given()
        .when()
            .get("/api/project/" + code)
        .then()
            .statusCode(200);

        given()
            .contentType(ContentType.JSON)
            .body("""
                {
                    "title": "Updated Multi Endpoint Title"
                }
                """)
        .when()
            .put("/api/project/" + code)
        .then()
            .statusCode(200);

        given()
            .contentType(ContentType.JSON)
            .body("""
                {
                    "title": "Multi Endpoint Problem"
                }
                """)
        .when()
            .post("/api/problem/" + code)
        .then()
            .statusCode(201);

        Response problemsResponse = given()
        .when()
            .get("/api/problem/" + code)
        .then()
            .statusCode(200)
            .extract().response();

        Long problemIdx = problemsResponse.jsonPath().getLong("[0].idx");
        if (problemIdx != null && problemIdx > 0) {
            given()
                .contentType(ContentType.JSON)
                .body(String.format("""
                    {
                        "content": "Valid subproblem content greater than nine",
                        "pro_idx": %d
                    }
                    """, problemIdx))
            .when()
                .post("/api/subproblem")
            .then()
                .statusCode(201);
        }

        given()
        .when()
            .delete("/api/problem/" + code + "/all")
        .then()
            .statusCode(200);

        given()
        .when()
            .delete("/api/project/" + code)
        .then()
            .statusCode(200);
    }
}
```

### Evidências e classificações propostas

- Operações REST: `DELETE /api/problem/; DELETE /api/project/; GET /api/problem/; GET /api/project/; POST /api/problem/; POST /api/project; POST /api/subproblem; PUT /api/project/`
- Q4 positivo proposto: `FALSE`
- Q4 negativo proposto: `TRUE`
- Q4 borda proposto: `TRUE`
- Q4 multi-endpoint proposto: `TRUE`
- Q1 — compilação: `passed`; sintaxe observada: `TRUE`; executável: `FALSE`; aprovado: `FALSE`
- Causa registrada: `execution_error`
- Evidência: Failed to parse the JSON document io.restassured.path.json.exception.JsonPathException: Failed to parse the JSON document at io.restassured.path.json.JsonPath$ExceptionCatcher.invoke(JsonPath.java:1002) at io.restassured.path.json.JsonPath$4.doParseWith(JsonPath.java:967) at io.restassured.path.json.JsonPath$JsonParser.parseWith(JsonPath.java:1047) at io.restassured.path.json.JsonPath.get(JsonPath.java:202) at io.restassured.path.json.JsonPath.getString(JsonPath.java:352) at GeneratedApiTest.testMultiEndpointLifecycleScenario(GeneratedApiTest.java:254) at java.base/java.lang.reflect.Method.invoke(Method.java:569) at java.base/java.util.ArrayList.forEach(ArrayList.java:1511) at java.base/java.util.ArrayList.forEach(ArrayList.java:1511) Caused by: java.lang.IllegalArgumentException: The JSON input text should neither be null nor empty. at java.base/jdk.internal.reflect.NativeConstructorAccessorImpl.newInstance0(Native Method) at java.base/jdk.internal.reflect.NativeConstructorAccessorImpl.newInstance(NativeConstructorAccessorImpl.java:77) at java.base/jdk.internal.reflect.DelegatingConstructorAccessorImpl.newInstance(DelegatingConstructorAccessorImpl.java:45) at java.base/java.lang.reflect.Constructor.newInstanceWithCaller(Constructor.java:500) at java.base/java.lang.reflect.Constructor.newInstance(Constructor.java:481) at org.codehaus.groovy.reflection.CachedConstructor.invoke(CachedConstructor.java:73) at org.codehaus.groovy.runtime.callsite.ConstructorSite$ConstructorSiteNoUnwrapNoCoerce.callConstructor(ConstructorSite.java:108) at org.codehaus.groovy.runtime.callsite.CallSiteArray.defaultCallConstructor(CallSiteArray.java:57) at org.codehaus.groovy.runtime.callsite.AbstractCallSite.callConstructor(AbstractCallSite.java:263) at org.codehaus.groovy.runtime.callsite.AbstractCallSite.callConstructor(AbstractCallSite.java:277) at io.restassured.internal.path.json.ConfigurableJsonSlurper.parseText(ConfigurableJsonSlurper.groovy:80) at io.restassured.path.json.JsonPath$4$1.method(JsonPath.java:965) at io.restassured.path.json.JsonPath$ExceptionCatcher.invoke(JsonPath.java:1000) ... 8 more

### Pontos para julgamento humano

- Confirmar ou corrigir cada categoria Q4 a partir da intenção efetiva do método.
- Confirmar se a resposta observada corresponde ao contrato OpenAPI e se a falha é do teste, da API ou inconclusiva.
- Registrar decisão, revisor, data e justificativa no CSV; não alterar o código original.

---

## Caso P-06 — `gemini-3.6-think` / repetição `02` / `testMultiEndpoint_ProjectAndProblemLifecycle`

**Identificador CSV:** `model=gemini-3.6-think; run=02; test_method=testMultiEndpoint_ProjectAndProblemLifecycle`
**Referência:** `tests/gemini-3.6-think/run-02.java#testMultiEndpoint_ProjectAndProblemLifecycle`
**Prioridade:** `HIGH`

### Código Java e contexto

```java
public class GeneratedApiTest {

@BeforeAll
public static void setup() {
    String baseUrl = System.getProperty("API_BASE_URL");
    if (baseUrl == null || baseUrl.trim().isEmpty()) {
        baseUrl = System.getenv("API_BASE_URL");
    }
    if (baseUrl == null || baseUrl.trim().isEmpty() || baseUrl.equals("${API_BASE_URL}")) {
        baseUrl = "http://localhost:8080";
    }
    RestAssured.baseURI = baseUrl;
}

@Test
public void testCreateProject_Success_BoundaryTitleLengthFive() {
    given()
        .contentType(ContentType.JSON)
        .body("{\"title\":\"12345\"}")
    .when()
        .post("/api/project")
    .then()
        .statusCode(201);
}

@Test
public void testCreateProject_Success_ValidTitle() {
    given()
        .contentType(ContentType.JSON)
        .body("{\"title\":\"Valid Project Title\"}")
    .when()
        .post("/api/project")
    .then()
        .statusCode(201);
}

@Test
public void testCreateProject_Failure_BoundaryTitleLengthFour() {
    given()
        .contentType(ContentType.JSON)
        .body("{\"title\":\"1234\"}")
    .when()
        .post("/api/project")
    .then()
        .statusCode(400);
}

@Test
public void testCreateProject_Failure_ShortTitle() {
    given()
        .contentType(ContentType.JSON)
        .body("{\"title\":\"ABC\"}")
    .when()
        .post("/api/project")
    .then()
        .statusCode(400);
}

@Test
public void testGetProject_Failure_InvalidCodeLengthBoundary() {
    given()
    .when()
        .get("/api/project/12345")
    .then()
        .statusCode(400);
}

@Test
public void testGetProject_Failure_NotFound() {
    given()
    .when()
        .get("/api/project/NONEXISTENT_CODE_123")
    .then()
        .statusCode(404);
}

@Test
public void testUpdateProject_Failure_InvalidCode() {
    given()
        .contentType(ContentType.JSON)
        .body("{\"title\":\"Updated Valid Title\"}")
    .when()
        .put("/api/project/12345")
    .then()
        .statusCode(400);
}

@Test
public void testUpdateProject_Failure_NotFound() {
    given()
        .contentType(ContentType.JSON)
        .body("{\"title\":\"Updated Valid Title\"}")
    .when()
        .put("/api/project/NONEXISTENT_CODE_123")
    .then()
        .statusCode(404);
}

@Test
public void testDeleteProject_Failure_InvalidCode() {
    given()
    .when()
        .delete("/api/project/12345")
    .then()
        .statusCode(400);
}

@Test
public void testDeleteProject_Failure_NotFound() {
    given()
    .when()
        .delete("/api/project/NONEXISTENT_CODE_123")
    .then()
        .statusCode(404);
}

@Test
public void testGetProblems_Failure_InvalidCode() {
    given()
    .when()
        .get("/api/problem/12345")
    .then()
        .statusCode(400);
}

@Test
public void testGetProblems_Failure_NotFound() {
    given()
    .when()
        .get("/api/problem/NONEXISTENT_CODE_123")
    .then()
        .statusCode(404);
}

@Test
public void testCreateProblem_Failure_InvalidCode() {
    given()
        .contentType(ContentType.JSON)
        .body("{\"title\":\"Problem Title\"}")
    .when()
        .post("/api/problem/12345")
    .then()
        .statusCode(400);
}

@Test
public void testCreateProblem_Failure_ProjectNotFound() {
    given()
        .contentType(ContentType.JSON)
        .body("{\"title\":\"Problem Title\"}")
    .when()
        .post("/api/problem/NONEXISTENT_CODE_123")
    .then()
        .statusCode(404);
}

@Test
public void testDeleteProblems_Failure_InvalidCode() {
    given()
    .when()
        .delete("/api/problem/12345")
    .then()
        .statusCode(400);
}

@Test
public void testDeleteAllProblemsAndSubproblems_Failure_InvalidCode() {
    given()
    .when()
        .delete("/api/problem/12345/all")
    .then()
        .statusCode(400);
}

@Test
public void testDeleteAllProblemsAndSubproblems_Failure_NotFound() {
    given()
    .when()
        .delete("/api/problem/NONEXISTENT_CODE_123/all")
    .then()
        .statusCode(404);
}

@Test
public void testCreateSubProblem_Failure_ContentLengthNineBoundary() {
    given()
        .contentType(ContentType.JSON)
        .body("{\"content\":\"123456789\",\"pro_idx\":1}")
    .when()
        .post("/api/subproblem")
    .then()
        .statusCode(400);
}

@Test
public void testCreateSubProblem_Failure_ShortContent() {
    given()
        .contentType(ContentType.JSON)
        .body("{\"content\":\"Short\",\"pro_idx\":1}")
    .when()
        .post("/api/subproblem")
    .then()
        .statusCode(400);
}

@Test
public void testCreateSubProblem_Failure_ProblemNotFound() {
    given()
        .contentType(ContentType.JSON)
        .body("{\"content\":\"Valid subproblem content longer than 9 chars\",\"pro_idx\":999999999}")
    .when()
        .post("/api/subproblem")
    .then()
        .statusCode(404);
}

@Test
public void testMultiEndpoint_ProjectAndProblemLifecycle() {
    Response createProjectResponse = given()
        .contentType(ContentType.JSON)
        .body("{\"title\":\"Lifecycle Integration Test Project\"}")
    .when()
        .post("/api/project");

    createProjectResponse.then().statusCode(201);

    String code = createProjectResponse.jsonPath().getString("code");
    if (code != null && !code.isEmpty()) {
        given()
        .when()
            .get("/api/project/" + code)
        .then()
            .statusCode(200)
            .body("title", equalTo("Lifecycle Integration Test Project"));

        given()
            .contentType(ContentType.JSON)
            .body("{\"title\":\"Updated Integration Test Project Title\"}")
        .when()
            .put("/api/project/" + code)
        .then()
            .statusCode(200);

        Response createProblemResponse = given()
            .contentType(ContentType.JSON)
            .body("{\"title\":\"Lifecycle Problem Title\"}")
        .when()
            .post("/api/problem/" + code);

        if (createProblemResponse.statusCode() == 201) {
            Long proIdx = createProblemResponse.jsonPath().getLong("idx");
            if (proIdx != null && proIdx > 0) {
                given()
                    .contentType(ContentType.JSON)
                    .body("{\"content\":\"Valid subproblem content description\",\"pro_idx\":" + proIdx + "}")
                .when()
                    .post("/api/subproblem")
                .then()
                    .statusCode(201);
            }
        }

        given()
        .when()
            .get("/api/problem/" + code)
        .then()
            .statusCode(200);

        given()
        .when()
            .delete("/api/problem/" + code)
        .then()
            .statusCode(200);

        given()
        .when()
            .delete("/api/problem/" + code + "/all")
        .then()
            .statusCode(200);

        given()
        .when()
            .delete("/api/project/" + code)
        .then()
            .statusCode(200);
    }
}
```

### Evidências e classificações propostas

- Operações REST: `DELETE /api/problem/; DELETE /api/project/; GET /api/problem/; GET /api/project/; POST /api/problem/; POST /api/project; POST /api/subproblem; PUT /api/project/`
- Q4 positivo proposto: `FALSE`
- Q4 negativo proposto: `TRUE`
- Q4 borda proposto: `TRUE`
- Q4 multi-endpoint proposto: `TRUE`
- Q1 — compilação: `passed`; sintaxe observada: `TRUE`; executável: `FALSE`; aprovado: `FALSE`
- Causa registrada: `execution_error`
- Evidência: Failed to parse the JSON document io.restassured.path.json.exception.JsonPathException: Failed to parse the JSON document at io.restassured.path.json.JsonPath$ExceptionCatcher.invoke(JsonPath.java:1002) at io.restassured.path.json.JsonPath$4.doParseWith(JsonPath.java:967) at io.restassured.path.json.JsonPath$JsonParser.parseWith(JsonPath.java:1047) at io.restassured.path.json.JsonPath.get(JsonPath.java:202) at io.restassured.path.json.JsonPath.getString(JsonPath.java:352) at GeneratedApiTest.testMultiEndpoint_ProjectAndProblemLifecycle(GeneratedApiTest.java:236) at java.base/java.lang.reflect.Method.invoke(Method.java:569) at java.base/java.util.ArrayList.forEach(ArrayList.java:1511) at java.base/java.util.ArrayList.forEach(ArrayList.java:1511) Caused by: java.lang.IllegalArgumentException: The JSON input text should neither be null nor empty. at java.base/jdk.internal.reflect.NativeConstructorAccessorImpl.newInstance0(Native Method) at java.base/jdk.internal.reflect.NativeConstructorAccessorImpl.newInstance(NativeConstructorAccessorImpl.java:77) at java.base/jdk.internal.reflect.DelegatingConstructorAccessorImpl.newInstance(DelegatingConstructorAccessorImpl.java:45) at java.base/java.lang.reflect.Constructor.newInstanceWithCaller(Constructor.java:500) at java.base/java.lang.reflect.Constructor.newInstance(Constructor.java:481) at org.codehaus.groovy.reflection.CachedConstructor.invoke(CachedConstructor.java:73) at org.codehaus.groovy.runtime.callsite.ConstructorSite$ConstructorSiteNoUnwrapNoCoerce.callConstructor(ConstructorSite.java:108) at org.codehaus.groovy.runtime.callsite.CallSiteArray.defaultCallConstructor(CallSiteArray.java:57) at org.codehaus.groovy.runtime.callsite.AbstractCallSite.callConstructor(AbstractCallSite.java:263) at org.codehaus.groovy.runtime.callsite.AbstractCallSite.callConstructor(AbstractCallSite.java:277) at io.restassured.internal.path.json.ConfigurableJsonSlurper.parseText(ConfigurableJsonSlurper.groovy:80) at io.restassured.path.json.JsonPath$4$1.method(JsonPath.java:965) at io.restassured.path.json.JsonPath$ExceptionCatcher.invoke(JsonPath.java:1000) ... 8 more

### Pontos para julgamento humano

- Confirmar ou corrigir cada categoria Q4 a partir da intenção efetiva do método.
- Confirmar se a resposta observada corresponde ao contrato OpenAPI e se a falha é do teste, da API ou inconclusiva.
- Registrar decisão, revisor, data e justificativa no CSV; não alterar o código original.

---

## Caso P-07 — `gemini-3.6-think` / repetição `01` / `testDeleteProjectNotFound`

**Identificador CSV:** `model=gemini-3.6-think; run=01; test_method=testDeleteProjectNotFound`
**Referência:** `tests/gemini-3.6-think/run-01.java#testDeleteProjectNotFound`
**Prioridade:** `HIGH`

### Código Java e contexto

```java
public class GeneratedApiTest {

@BeforeAll
public static void setup() {
    String baseUrl = System.getenv("API_BASE_URL");
    if (baseUrl == null || baseUrl.trim().isEmpty()) {
        baseUrl = System.getProperty("API_BASE_URL", "http://localhost:8080");
    }
    RestAssured.baseURI = baseUrl;
}

@Test
public void testCreateProjectSuccess() {
    given()
        .contentType(ContentType.JSON)
        .body("""
            {
                "title": "Valid Project Title"
            }
            """)
    .when()
        .post("/api/project")
    .then()
        .statusCode(201);
}

@Test
public void testCreateProjectBoundaryTitleLengthFour() {
    given()
        .contentType(ContentType.JSON)
        .body("""
            {
                "title": "Four"
            }
            """)
    .when()
        .post("/api/project")
    .then()
        .statusCode(400);
}

@Test
public void testCreateProjectBoundaryTitleLengthFive() {
    given()
        .contentType(ContentType.JSON)
        .body("""
            {
                "title": "Five5"
            }
            """)
    .when()
        .post("/api/project")
    .then()
        .statusCode(201);
}

@Test
public void testGetProjectInvalidCodeLength() {
    given()
    .when()
        .get("/api/project/12345")
    .then()
        .statusCode(400);
}

@Test
public void testGetProjectNotFound() {
    given()
    .when()
        .get("/api/project/NOTFOUND123")
    .then()
        .statusCode(404);
}

@Test
public void testUpdateProjectInvalidCodeLength() {
    given()
        .contentType(ContentType.JSON)
        .body("""
            {
                "title": "Updated Title"
            }
            """)
    .when()
        .put("/api/project/12345")
    .then()
        .statusCode(400);
}

@Test
public void testUpdateProjectNotFound() {
    given()
        .contentType(ContentType.JSON)
        .body("""
            {
                "title": "Updated Title"
            }
            """)
    .when()
        .put("/api/project/NOTFOUND123")
    .then()
        .statusCode(404);
}

@Test
public void testDeleteProjectInvalidCodeLength() {
    given()
    .when()
        .delete("/api/project/12345")
    .then()
        .statusCode(400);
}

@Test
public void testDeleteProjectNotFound() {
    given()
    .when()
        .delete("/api/project/NOTFOUND123")
    .then()
        .statusCode(404);
}
```

### Evidências e classificações propostas

- Operações REST: `DELETE /api/project/NOTFOUND123`
- Q4 positivo proposto: `FALSE`
- Q4 negativo proposto: `TRUE`
- Q4 borda proposto: `FALSE`
- Q4 multi-endpoint proposto: `FALSE`
- Q1 — compilação: `passed`; sintaxe observada: `TRUE`; executável: `TRUE`; aprovado: `FALSE`
- Causa registrada: `assertion_error`
- Evidência: 1 expectation failed. Expected status code <404> but was <200>. java.lang.AssertionError: 1 expectation failed. Expected status code <404> but was <200>. at java.base/jdk.internal.reflect.NativeConstructorAccessorImpl.newInstance0(Native Method) at java.base/jdk.internal.reflect.NativeConstructorAccessorImpl.newInstance(NativeConstructorAccessorImpl.java:77) at java.base/jdk.internal.reflect.DelegatingConstructorAccessorImpl.newInstance(DelegatingConstructorAccessorImpl.java:45) at java.base/java.lang.reflect.Constructor.newInstanceWithCaller(Constructor.java:500) at java.base/java.lang.reflect.Constructor.newInstance(Constructor.java:481) at org.codehaus.groovy.reflection.CachedConstructor.invoke(CachedConstructor.java:73) at org.codehaus.groovy.runtime.callsite.ConstructorSite$ConstructorSiteNoUnwrapNoCoerce.callConstructor(ConstructorSite.java:108) at org.codehaus.groovy.runtime.callsite.AbstractCallSite.callConstructor(AbstractCallSite.java:277) at io.restassured.internal.ResponseSpecificationImpl$HamcrestAssertionClosure.validate(ResponseSpecificationImpl.groovy:512) at io.restassured.internal.ResponseSpecificationImpl$HamcrestAssertionClosure$validate$1.call(Unknown Source) at io.restassured.internal.ResponseSpecificationImpl.validateResponseIfRequired(ResponseSpecificationImpl.groovy:696) at io.restassured.internal.ResponseSpecificationImpl.this$2$validateResponseIfRequired(ResponseSpecificationImpl.groovy) at java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke0(Native Method) at java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:77) at java.base/jdk.internal.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43) at java.base/java.lang.reflect.Method.invoke(Method.java:569) at org.codehaus.groovy.runtime.callsite.PlainObjectMetaMethodSite.doInvoke(PlainObjectMetaMethodSite.java:43) at org.codehaus.groovy.runtime.callsite.PogoMetaMethodSite$PogoCachedMethodSiteNoUnwrapNoCoerce.invoke(PogoMetaMethodSite.java:198) at org.codehaus.groovy.runtime.callsite.PogoMetaMethodSite.callCurrent(PogoMetaMethodSite.java:62) at org.codehaus.groovy.runtime.callsite.AbstractCallSite.callCurrent(AbstractCallSite.java:185) at io.restassured.internal.ResponseSpecificationImpl.statusCode(ResponseSpecificationImpl.groovy:135) at io.restassured.specification.ResponseSpecification$statusCode$0.callCurrent(Unknown Source) at io.restassured.internal.ResponseSpecificationImpl.statusCode(ResponseSpecificationImpl.groovy:143) at io.restassured.internal.ValidatableResponseOptionsImpl.statusCode(ValidatableResponseOptionsImpl.java:89) at GeneratedApiTest.testDeleteProjectNotFound(GeneratedApiTest.java:129) at java.base/java.lang.reflect.Method.invoke(Method.java:569) at java.base/java.util.ArrayList.forEach(ArrayList.java:1511) at java.base/java.util.ArrayList.forEach(ArrayList.java:1511)

### Pontos para julgamento humano

- Confirmar ou corrigir cada categoria Q4 a partir da intenção efetiva do método.
- Confirmar se a resposta observada corresponde ao contrato OpenAPI e se a falha é do teste, da API ou inconclusiva.
- Registrar decisão, revisor, data e justificativa no CSV; não alterar o código original.

---

## Caso P-08 — `gemini-3.6-think` / repetição `01` / `testGetProblemsNotFound`

**Identificador CSV:** `model=gemini-3.6-think; run=01; test_method=testGetProblemsNotFound`
**Referência:** `tests/gemini-3.6-think/run-01.java#testGetProblemsNotFound`
**Prioridade:** `HIGH`

### Código Java e contexto

```java
public class GeneratedApiTest {

@BeforeAll
public static void setup() {
    String baseUrl = System.getenv("API_BASE_URL");
    if (baseUrl == null || baseUrl.trim().isEmpty()) {
        baseUrl = System.getProperty("API_BASE_URL", "http://localhost:8080");
    }
    RestAssured.baseURI = baseUrl;
}

@Test
public void testCreateProjectSuccess() {
    given()
        .contentType(ContentType.JSON)
        .body("""
            {
                "title": "Valid Project Title"
            }
            """)
    .when()
        .post("/api/project")
    .then()
        .statusCode(201);
}

@Test
public void testCreateProjectBoundaryTitleLengthFour() {
    given()
        .contentType(ContentType.JSON)
        .body("""
            {
                "title": "Four"
            }
            """)
    .when()
        .post("/api/project")
    .then()
        .statusCode(400);
}

@Test
public void testCreateProjectBoundaryTitleLengthFive() {
    given()
        .contentType(ContentType.JSON)
        .body("""
            {
                "title": "Five5"
            }
            """)
    .when()
        .post("/api/project")
    .then()
        .statusCode(201);
}

@Test
public void testGetProjectInvalidCodeLength() {
    given()
    .when()
        .get("/api/project/12345")
    .then()
        .statusCode(400);
}

@Test
public void testGetProjectNotFound() {
    given()
    .when()
        .get("/api/project/NOTFOUND123")
    .then()
        .statusCode(404);
}

@Test
public void testUpdateProjectInvalidCodeLength() {
    given()
        .contentType(ContentType.JSON)
        .body("""
            {
                "title": "Updated Title"
            }
            """)
    .when()
        .put("/api/project/12345")
    .then()
        .statusCode(400);
}

@Test
public void testUpdateProjectNotFound() {
    given()
        .contentType(ContentType.JSON)
        .body("""
            {
                "title": "Updated Title"
            }
            """)
    .when()
        .put("/api/project/NOTFOUND123")
    .then()
        .statusCode(404);
}

@Test
public void testDeleteProjectInvalidCodeLength() {
    given()
    .when()
        .delete("/api/project/12345")
    .then()
        .statusCode(400);
}

@Test
public void testDeleteProjectNotFound() {
    given()
    .when()
        .delete("/api/project/NOTFOUND123")
    .then()
        .statusCode(404);
}

@Test
public void testGetProblemsInvalidCodeLength() {
    given()
    .when()
        .get("/api/problem/12345")
    .then()
        .statusCode(400);
}

@Test
public void testGetProblemsNotFound() {
    given()
    .when()
        .get("/api/problem/NOTFOUND123")
    .then()
        .statusCode(404);
}
```

### Evidências e classificações propostas

- Operações REST: `GET /api/problem/NOTFOUND123`
- Q4 positivo proposto: `FALSE`
- Q4 negativo proposto: `TRUE`
- Q4 borda proposto: `FALSE`
- Q4 multi-endpoint proposto: `FALSE`
- Q1 — compilação: `passed`; sintaxe observada: `TRUE`; executável: `TRUE`; aprovado: `FALSE`
- Causa registrada: `assertion_error`
- Evidência: 1 expectation failed. Expected status code <404> but was <501>. java.lang.AssertionError: 1 expectation failed. Expected status code <404> but was <501>. at java.base/jdk.internal.reflect.NativeConstructorAccessorImpl.newInstance0(Native Method) at java.base/jdk.internal.reflect.NativeConstructorAccessorImpl.newInstance(NativeConstructorAccessorImpl.java:77) at java.base/jdk.internal.reflect.DelegatingConstructorAccessorImpl.newInstance(DelegatingConstructorAccessorImpl.java:45) at java.base/java.lang.reflect.Constructor.newInstanceWithCaller(Constructor.java:500) at java.base/java.lang.reflect.Constructor.newInstance(Constructor.java:481) at org.codehaus.groovy.reflection.CachedConstructor.invoke(CachedConstructor.java:73) at org.codehaus.groovy.runtime.callsite.ConstructorSite$ConstructorSiteNoUnwrapNoCoerce.callConstructor(ConstructorSite.java:108) at org.codehaus.groovy.runtime.callsite.CallSiteArray.defaultCallConstructor(CallSiteArray.java:57) at org.codehaus.groovy.runtime.callsite.AbstractCallSite.callConstructor(AbstractCallSite.java:263) at org.codehaus.groovy.runtime.callsite.AbstractCallSite.callConstructor(AbstractCallSite.java:277) at io.restassured.internal.ResponseSpecificationImpl$HamcrestAssertionClosure.validate(ResponseSpecificationImpl.groovy:512) at io.restassured.internal.ResponseSpecificationImpl$HamcrestAssertionClosure$validate$1.call(Unknown Source) at io.restassured.internal.ResponseSpecificationImpl.validateResponseIfRequired(ResponseSpecificationImpl.groovy:696) at io.restassured.internal.ResponseSpecificationImpl.this$2$validateResponseIfRequired(ResponseSpecificationImpl.groovy) at java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke0(Native Method) at java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:77) at java.base/jdk.internal.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43) at java.base/java.lang.reflect.Method.invoke(Method.java:569) at org.codehaus.groovy.runtime.callsite.PlainObjectMetaMethodSite.doInvoke(PlainObjectMetaMethodSite.java:43) at org.codehaus.groovy.runtime.callsite.PogoMetaMethodSite$PogoCachedMethodSiteNoUnwrapNoCoerce.invoke(PogoMetaMethodSite.java:198) at org.codehaus.groovy.runtime.callsite.PogoMetaMethodSite.callCurrent(PogoMetaMethodSite.java:62) at org.codehaus.groovy.runtime.callsite.AbstractCallSite.callCurrent(AbstractCallSite.java:185) at io.restassured.internal.ResponseSpecificationImpl.statusCode(ResponseSpecificationImpl.groovy:135) at io.restassured.specification.ResponseSpecification$statusCode$0.callCurrent(Unknown Source) at io.restassured.internal.ResponseSpecificationImpl.statusCode(ResponseSpecificationImpl.groovy:143) at io.restassured.internal.ValidatableResponseOptionsImpl.statusCode(ValidatableResponseOptionsImpl.java:89) at GeneratedApiTest.testGetProblemsNotFound(GeneratedApiTest.java:147) at java.base/java.lang.reflect.Method.invoke(Method.java:569) at java.base/java.util.ArrayList.forEach(ArrayList.java:1511) at java.base/java.util.ArrayList.forEach(ArrayList.java:1511)

### Pontos para julgamento humano

- Confirmar ou corrigir cada categoria Q4 a partir da intenção efetiva do método.
- Confirmar se a resposta observada corresponde ao contrato OpenAPI e se a falha é do teste, da API ou inconclusiva.
- Registrar decisão, revisor, data e justificativa no CSV; não alterar o código original.

---

## Caso P-09 — `gemini-3.6-think` / repetição `01` / `testCreateProjectSuccess`

**Identificador CSV:** `model=gemini-3.6-think; run=01; test_method=testCreateProjectSuccess`
**Referência:** `tests/gemini-3.6-think/run-01.java#testCreateProjectSuccess`
**Prioridade:** `LOW`

### Código Java e contexto

```java
public class GeneratedApiTest {

@BeforeAll
public static void setup() {
    String baseUrl = System.getenv("API_BASE_URL");
    if (baseUrl == null || baseUrl.trim().isEmpty()) {
        baseUrl = System.getProperty("API_BASE_URL", "http://localhost:8080");
    }
    RestAssured.baseURI = baseUrl;
}

@Test
public void testCreateProjectSuccess() {
    given()
        .contentType(ContentType.JSON)
        .body("""
            {
                "title": "Valid Project Title"
            }
            """)
    .when()
        .post("/api/project")
    .then()
        .statusCode(201);
}
```

### Evidências e classificações propostas

- Operações REST: `POST /api/project`
- Q4 positivo proposto: `TRUE`
- Q4 negativo proposto: `FALSE`
- Q4 borda proposto: `FALSE`
- Q4 multi-endpoint proposto: `FALSE`
- Q1 — compilação: `passed`; sintaxe observada: `TRUE`; executável: `TRUE`; aprovado: `TRUE`
- Causa registrada: `nenhuma`
- Evidência: não há evidência de falha no Surefire.

### Pontos para julgamento humano

- Confirmar ou corrigir cada categoria Q4 a partir da intenção efetiva do método.
- Confirmar se a resposta observada corresponde ao contrato OpenAPI e se a falha é do teste, da API ou inconclusiva.
- Registrar decisão, revisor, data e justificativa no CSV; não alterar o código original.

---

## Caso P-10 — `gemini-3.6-think` / repetição `01` / `testCreateProjectBoundaryTitleLengthFour`

**Identificador CSV:** `model=gemini-3.6-think; run=01; test_method=testCreateProjectBoundaryTitleLengthFour`
**Referência:** `tests/gemini-3.6-think/run-01.java#testCreateProjectBoundaryTitleLengthFour`
**Prioridade:** `LOW`

### Código Java e contexto

```java
public class GeneratedApiTest {

@BeforeAll
public static void setup() {
    String baseUrl = System.getenv("API_BASE_URL");
    if (baseUrl == null || baseUrl.trim().isEmpty()) {
        baseUrl = System.getProperty("API_BASE_URL", "http://localhost:8080");
    }
    RestAssured.baseURI = baseUrl;
}

@Test
public void testCreateProjectSuccess() {
    given()
        .contentType(ContentType.JSON)
        .body("""
            {
                "title": "Valid Project Title"
            }
            """)
    .when()
        .post("/api/project")
    .then()
        .statusCode(201);
}

@Test
public void testCreateProjectBoundaryTitleLengthFour() {
    given()
        .contentType(ContentType.JSON)
        .body("""
            {
                "title": "Four"
            }
            """)
    .when()
        .post("/api/project")
    .then()
        .statusCode(400);
}
```

### Evidências e classificações propostas

- Operações REST: `POST /api/project`
- Q4 positivo proposto: `FALSE`
- Q4 negativo proposto: `FALSE`
- Q4 borda proposto: `TRUE`
- Q4 multi-endpoint proposto: `FALSE`
- Q1 — compilação: `passed`; sintaxe observada: `TRUE`; executável: `TRUE`; aprovado: `TRUE`
- Causa registrada: `nenhuma`
- Evidência: não há evidência de falha no Surefire.

### Pontos para julgamento humano

- Confirmar ou corrigir cada categoria Q4 a partir da intenção efetiva do método.
- Confirmar se a resposta observada corresponde ao contrato OpenAPI e se a falha é do teste, da API ou inconclusiva.
- Registrar decisão, revisor, data e justificativa no CSV; não alterar o código original.

---

## Caso P-11 — `gemini-3.6-think` / repetição `01` / `testCreateProjectBoundaryTitleLengthFive`

**Identificador CSV:** `model=gemini-3.6-think; run=01; test_method=testCreateProjectBoundaryTitleLengthFive`
**Referência:** `tests/gemini-3.6-think/run-01.java#testCreateProjectBoundaryTitleLengthFive`
**Prioridade:** `LOW`

### Código Java e contexto

```java
public class GeneratedApiTest {

@BeforeAll
public static void setup() {
    String baseUrl = System.getenv("API_BASE_URL");
    if (baseUrl == null || baseUrl.trim().isEmpty()) {
        baseUrl = System.getProperty("API_BASE_URL", "http://localhost:8080");
    }
    RestAssured.baseURI = baseUrl;
}

@Test
public void testCreateProjectSuccess() {
    given()
        .contentType(ContentType.JSON)
        .body("""
            {
                "title": "Valid Project Title"
            }
            """)
    .when()
        .post("/api/project")
    .then()
        .statusCode(201);
}

@Test
public void testCreateProjectBoundaryTitleLengthFour() {
    given()
        .contentType(ContentType.JSON)
        .body("""
            {
                "title": "Four"
            }
            """)
    .when()
        .post("/api/project")
    .then()
        .statusCode(400);
}

@Test
public void testCreateProjectBoundaryTitleLengthFive() {
    given()
        .contentType(ContentType.JSON)
        .body("""
            {
                "title": "Five5"
            }
            """)
    .when()
        .post("/api/project")
    .then()
        .statusCode(201);
}
```

### Evidências e classificações propostas

- Operações REST: `POST /api/project`
- Q4 positivo proposto: `FALSE`
- Q4 negativo proposto: `FALSE`
- Q4 borda proposto: `TRUE`
- Q4 multi-endpoint proposto: `FALSE`
- Q1 — compilação: `passed`; sintaxe observada: `TRUE`; executável: `TRUE`; aprovado: `TRUE`
- Causa registrada: `nenhuma`
- Evidência: não há evidência de falha no Surefire.

### Pontos para julgamento humano

- Confirmar ou corrigir cada categoria Q4 a partir da intenção efetiva do método.
- Confirmar se a resposta observada corresponde ao contrato OpenAPI e se a falha é do teste, da API ou inconclusiva.
- Registrar decisão, revisor, data e justificativa no CSV; não alterar o código original.

---

## Caso P-12 — `gemini-3.6-think` / repetição `01` / `testGetProjectInvalidCodeLength`

**Identificador CSV:** `model=gemini-3.6-think; run=01; test_method=testGetProjectInvalidCodeLength`
**Referência:** `tests/gemini-3.6-think/run-01.java#testGetProjectInvalidCodeLength`
**Prioridade:** `LOW`

### Código Java e contexto

```java
public class GeneratedApiTest {

@BeforeAll
public static void setup() {
    String baseUrl = System.getenv("API_BASE_URL");
    if (baseUrl == null || baseUrl.trim().isEmpty()) {
        baseUrl = System.getProperty("API_BASE_URL", "http://localhost:8080");
    }
    RestAssured.baseURI = baseUrl;
}

@Test
public void testCreateProjectSuccess() {
    given()
        .contentType(ContentType.JSON)
        .body("""
            {
                "title": "Valid Project Title"
            }
            """)
    .when()
        .post("/api/project")
    .then()
        .statusCode(201);
}

@Test
public void testCreateProjectBoundaryTitleLengthFour() {
    given()
        .contentType(ContentType.JSON)
        .body("""
            {
                "title": "Four"
            }
            """)
    .when()
        .post("/api/project")
    .then()
        .statusCode(400);
}

@Test
public void testCreateProjectBoundaryTitleLengthFive() {
    given()
        .contentType(ContentType.JSON)
        .body("""
            {
                "title": "Five5"
            }
            """)
    .when()
        .post("/api/project")
    .then()
        .statusCode(201);
}

@Test
public void testGetProjectInvalidCodeLength() {
    given()
    .when()
        .get("/api/project/12345")
    .then()
        .statusCode(400);
}
```

### Evidências e classificações propostas

- Operações REST: `GET /api/project/12345`
- Q4 positivo proposto: `FALSE`
- Q4 negativo proposto: `TRUE`
- Q4 borda proposto: `TRUE`
- Q4 multi-endpoint proposto: `FALSE`
- Q1 — compilação: `passed`; sintaxe observada: `TRUE`; executável: `TRUE`; aprovado: `TRUE`
- Causa registrada: `nenhuma`
- Evidência: não há evidência de falha no Surefire.

### Pontos para julgamento humano

- Confirmar ou corrigir cada categoria Q4 a partir da intenção efetiva do método.
- Confirmar se a resposta observada corresponde ao contrato OpenAPI e se a falha é do teste, da API ou inconclusiva.
- Registrar decisão, revisor, data e justificativa no CSV; não alterar o código original.

---

## Caso P-13 — `gemini-3.6-think` / repetição `01` / `testGetProjectNotFound`

**Identificador CSV:** `model=gemini-3.6-think; run=01; test_method=testGetProjectNotFound`
**Referência:** `tests/gemini-3.6-think/run-01.java#testGetProjectNotFound`
**Prioridade:** `LOW`

### Código Java e contexto

```java
public class GeneratedApiTest {

@BeforeAll
public static void setup() {
    String baseUrl = System.getenv("API_BASE_URL");
    if (baseUrl == null || baseUrl.trim().isEmpty()) {
        baseUrl = System.getProperty("API_BASE_URL", "http://localhost:8080");
    }
    RestAssured.baseURI = baseUrl;
}

@Test
public void testCreateProjectSuccess() {
    given()
        .contentType(ContentType.JSON)
        .body("""
            {
                "title": "Valid Project Title"
            }
            """)
    .when()
        .post("/api/project")
    .then()
        .statusCode(201);
}

@Test
public void testCreateProjectBoundaryTitleLengthFour() {
    given()
        .contentType(ContentType.JSON)
        .body("""
            {
                "title": "Four"
            }
            """)
    .when()
        .post("/api/project")
    .then()
        .statusCode(400);
}

@Test
public void testCreateProjectBoundaryTitleLengthFive() {
    given()
        .contentType(ContentType.JSON)
        .body("""
            {
                "title": "Five5"
            }
            """)
    .when()
        .post("/api/project")
    .then()
        .statusCode(201);
}

@Test
public void testGetProjectInvalidCodeLength() {
    given()
    .when()
        .get("/api/project/12345")
    .then()
        .statusCode(400);
}

@Test
public void testGetProjectNotFound() {
    given()
    .when()
        .get("/api/project/NOTFOUND123")
    .then()
        .statusCode(404);
}
```

### Evidências e classificações propostas

- Operações REST: `GET /api/project/NOTFOUND123`
- Q4 positivo proposto: `FALSE`
- Q4 negativo proposto: `TRUE`
- Q4 borda proposto: `FALSE`
- Q4 multi-endpoint proposto: `FALSE`
- Q1 — compilação: `passed`; sintaxe observada: `TRUE`; executável: `TRUE`; aprovado: `TRUE`
- Causa registrada: `nenhuma`
- Evidência: não há evidência de falha no Surefire.

### Pontos para julgamento humano

- Confirmar ou corrigir cada categoria Q4 a partir da intenção efetiva do método.
- Confirmar se a resposta observada corresponde ao contrato OpenAPI e se a falha é do teste, da API ou inconclusiva.
- Registrar decisão, revisor, data e justificativa no CSV; não alterar o código original.

---

## Caso P-14 — `gemini-3.6-think` / repetição `01` / `testUpdateProjectInvalidCodeLength`

**Identificador CSV:** `model=gemini-3.6-think; run=01; test_method=testUpdateProjectInvalidCodeLength`
**Referência:** `tests/gemini-3.6-think/run-01.java#testUpdateProjectInvalidCodeLength`
**Prioridade:** `LOW`

### Código Java e contexto

```java
public class GeneratedApiTest {

@BeforeAll
public static void setup() {
    String baseUrl = System.getenv("API_BASE_URL");
    if (baseUrl == null || baseUrl.trim().isEmpty()) {
        baseUrl = System.getProperty("API_BASE_URL", "http://localhost:8080");
    }
    RestAssured.baseURI = baseUrl;
}

@Test
public void testCreateProjectSuccess() {
    given()
        .contentType(ContentType.JSON)
        .body("""
            {
                "title": "Valid Project Title"
            }
            """)
    .when()
        .post("/api/project")
    .then()
        .statusCode(201);
}

@Test
public void testCreateProjectBoundaryTitleLengthFour() {
    given()
        .contentType(ContentType.JSON)
        .body("""
            {
                "title": "Four"
            }
            """)
    .when()
        .post("/api/project")
    .then()
        .statusCode(400);
}

@Test
public void testCreateProjectBoundaryTitleLengthFive() {
    given()
        .contentType(ContentType.JSON)
        .body("""
            {
                "title": "Five5"
            }
            """)
    .when()
        .post("/api/project")
    .then()
        .statusCode(201);
}

@Test
public void testGetProjectInvalidCodeLength() {
    given()
    .when()
        .get("/api/project/12345")
    .then()
        .statusCode(400);
}

@Test
public void testGetProjectNotFound() {
    given()
    .when()
        .get("/api/project/NOTFOUND123")
    .then()
        .statusCode(404);
}

@Test
public void testUpdateProjectInvalidCodeLength() {
    given()
        .contentType(ContentType.JSON)
        .body("""
            {
                "title": "Updated Title"
            }
            """)
    .when()
        .put("/api/project/12345")
    .then()
        .statusCode(400);
}
```

### Evidências e classificações propostas

- Operações REST: `PUT /api/project/12345`
- Q4 positivo proposto: `FALSE`
- Q4 negativo proposto: `TRUE`
- Q4 borda proposto: `TRUE`
- Q4 multi-endpoint proposto: `FALSE`
- Q1 — compilação: `passed`; sintaxe observada: `TRUE`; executável: `TRUE`; aprovado: `TRUE`
- Causa registrada: `nenhuma`
- Evidência: não há evidência de falha no Surefire.

### Pontos para julgamento humano

- Confirmar ou corrigir cada categoria Q4 a partir da intenção efetiva do método.
- Confirmar se a resposta observada corresponde ao contrato OpenAPI e se a falha é do teste, da API ou inconclusiva.
- Registrar decisão, revisor, data e justificativa no CSV; não alterar o código original.

---

## Caso P-15 — `gemini-3.6-think` / repetição `01` / `testUpdateProjectNotFound`

**Identificador CSV:** `model=gemini-3.6-think; run=01; test_method=testUpdateProjectNotFound`
**Referência:** `tests/gemini-3.6-think/run-01.java#testUpdateProjectNotFound`
**Prioridade:** `LOW`

### Código Java e contexto

```java
public class GeneratedApiTest {

@BeforeAll
public static void setup() {
    String baseUrl = System.getenv("API_BASE_URL");
    if (baseUrl == null || baseUrl.trim().isEmpty()) {
        baseUrl = System.getProperty("API_BASE_URL", "http://localhost:8080");
    }
    RestAssured.baseURI = baseUrl;
}

@Test
public void testCreateProjectSuccess() {
    given()
        .contentType(ContentType.JSON)
        .body("""
            {
                "title": "Valid Project Title"
            }
            """)
    .when()
        .post("/api/project")
    .then()
        .statusCode(201);
}

@Test
public void testCreateProjectBoundaryTitleLengthFour() {
    given()
        .contentType(ContentType.JSON)
        .body("""
            {
                "title": "Four"
            }
            """)
    .when()
        .post("/api/project")
    .then()
        .statusCode(400);
}

@Test
public void testCreateProjectBoundaryTitleLengthFive() {
    given()
        .contentType(ContentType.JSON)
        .body("""
            {
                "title": "Five5"
            }
            """)
    .when()
        .post("/api/project")
    .then()
        .statusCode(201);
}

@Test
public void testGetProjectInvalidCodeLength() {
    given()
    .when()
        .get("/api/project/12345")
    .then()
        .statusCode(400);
}

@Test
public void testGetProjectNotFound() {
    given()
    .when()
        .get("/api/project/NOTFOUND123")
    .then()
        .statusCode(404);
}

@Test
public void testUpdateProjectInvalidCodeLength() {
    given()
        .contentType(ContentType.JSON)
        .body("""
            {
                "title": "Updated Title"
            }
            """)
    .when()
        .put("/api/project/12345")
    .then()
        .statusCode(400);
}

@Test
public void testUpdateProjectNotFound() {
    given()
        .contentType(ContentType.JSON)
        .body("""
            {
                "title": "Updated Title"
            }
            """)
    .when()
        .put("/api/project/NOTFOUND123")
    .then()
        .statusCode(404);
}
```

### Evidências e classificações propostas

- Operações REST: `PUT /api/project/NOTFOUND123`
- Q4 positivo proposto: `FALSE`
- Q4 negativo proposto: `TRUE`
- Q4 borda proposto: `FALSE`
- Q4 multi-endpoint proposto: `FALSE`
- Q1 — compilação: `passed`; sintaxe observada: `TRUE`; executável: `TRUE`; aprovado: `TRUE`
- Causa registrada: `nenhuma`
- Evidência: não há evidência de falha no Surefire.

### Pontos para julgamento humano

- Confirmar ou corrigir cada categoria Q4 a partir da intenção efetiva do método.
- Confirmar se a resposta observada corresponde ao contrato OpenAPI e se a falha é do teste, da API ou inconclusiva.
- Registrar decisão, revisor, data e justificativa no CSV; não alterar o código original.

---

## Caso P-16 — `gpt-6` / repetição `01` / `createProjectWithValidTitle`

**Identificador CSV:** `model=gpt-6; run=01; test_method=createProjectWithValidTitle`
**Referência:** `tests/gpt-6/run-01.java#createProjectWithValidTitle`
**Prioridade:** `HIGH`

### Código Java e contexto

```java
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
```

### Evidências e classificações propostas

- Operações REST: `POST /api/project`
- Q4 positivo proposto: `TRUE`
- Q4 negativo proposto: `FALSE`
- Q4 borda proposto: `FALSE`
- Q4 multi-endpoint proposto: `FALSE`
- Q1 — compilação: `passed`; sintaxe observada: `TRUE`; executável: `TRUE`; aprovado: `FALSE`
- Causa registrada: `api_error_contract_status_200_201`
- Evidência: 1 expectation failed. Expected status code <200> but was <201>. java.lang.AssertionError: 1 expectation failed. Expected status code <200> but was <201>. at java.base/jdk.internal.reflect.NativeConstructorAccessorImpl.newInstance0(Native Method) at java.base/jdk.internal.reflect.NativeConstructorAccessorImpl.newInstance(NativeConstructorAccessorImpl.java:77) at java.base/jdk.internal.reflect.DelegatingConstructorAccessorImpl.newInstance(DelegatingConstructorAccessorImpl.java:45) at java.base/java.lang.reflect.Constructor.newInstanceWithCaller(Constructor.java:500) at java.base/java.lang.reflect.Constructor.newInstance(Constructor.java:481) at org.codehaus.groovy.reflection.CachedConstructor.invoke(CachedConstructor.java:73) at org.codehaus.groovy.runtime.callsite.ConstructorSite$ConstructorSiteNoUnwrapNoCoerce.callConstructor(ConstructorSite.java:108) at org.codehaus.groovy.runtime.callsite.AbstractCallSite.callConstructor(AbstractCallSite.java:277) at io.restassured.internal.ResponseSpecificationImpl$HamcrestAssertionClosure.validate(ResponseSpecificationImpl.groovy:512) at io.restassured.internal.ResponseSpecificationImpl$HamcrestAssertionClosure$validate$1.call(Unknown Source) at io.restassured.internal.ResponseSpecificationImpl.validateResponseIfRequired(ResponseSpecificationImpl.groovy:696) at io.restassured.internal.ResponseSpecificationImpl.this$2$validateResponseIfRequired(ResponseSpecificationImpl.groovy) at java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke0(Native Method) at java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:77) at java.base/jdk.internal.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43) at java.base/java.lang.reflect.Method.invoke(Method.java:569) at org.codehaus.groovy.runtime.callsite.PlainObjectMetaMethodSite.doInvoke(PlainObjectMetaMethodSite.java:43) at org.codehaus.groovy.runtime.callsite.PogoMetaMethodSite$PogoCachedMethodSiteNoUnwrapNoCoerce.invoke(PogoMetaMethodSite.java:198) at org.codehaus.groovy.runtime.callsite.PogoMetaMethodSite.callCurrent(PogoMetaMethodSite.java:62) at org.codehaus.groovy.runtime.callsite.AbstractCallSite.callCurrent(AbstractCallSite.java:185) at io.restassured.internal.ResponseSpecificationImpl.statusCode(ResponseSpecificationImpl.groovy:135) at io.restassured.specification.ResponseSpecification$statusCode$0.callCurrent(Unknown Source) at io.restassured.internal.ResponseSpecificationImpl.statusCode(ResponseSpecificationImpl.groovy:143) at io.restassured.internal.ValidatableResponseOptionsImpl.statusCode(ValidatableResponseOptionsImpl.java:89) at GeneratedApiTest.createProjectWithValidTitle(GeneratedApiTest.java:62) at java.base/java.lang.reflect.Method.invoke(Method.java:569) at java.base/java.util.ArrayList.forEach(ArrayList.java:1511) at java.base/java.util.ArrayList.forEach(ArrayList.java:1511)

### Pontos para julgamento humano

- Confirmar ou corrigir cada categoria Q4 a partir da intenção efetiva do método.
- Confirmar se a resposta observada corresponde ao contrato OpenAPI e se a falha é do teste, da API ou inconclusiva.
- Registrar decisão, revisor, data e justificativa no CSV; não alterar o código original.

---

## Caso P-17 — `gpt-6` / repetição `02` / `createProjectWithValidTitle`

**Identificador CSV:** `model=gpt-6; run=02; test_method=createProjectWithValidTitle`
**Referência:** `tests/gpt-6/run-02.java#createProjectWithValidTitle`
**Prioridade:** `HIGH`

### Código Java e contexto

```java
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
```

### Evidências e classificações propostas

- Operações REST: `POST /api/project`
- Q4 positivo proposto: `TRUE`
- Q4 negativo proposto: `FALSE`
- Q4 borda proposto: `FALSE`
- Q4 multi-endpoint proposto: `FALSE`
- Q1 — compilação: `passed`; sintaxe observada: `TRUE`; executável: `TRUE`; aprovado: `FALSE`
- Causa registrada: `api_error_contract_status_200_201`
- Evidência: 1 expectation failed. Expected status code <200> but was <201>. java.lang.AssertionError: 1 expectation failed. Expected status code <200> but was <201>. at java.base/jdk.internal.reflect.NativeConstructorAccessorImpl.newInstance0(Native Method) at java.base/jdk.internal.reflect.NativeConstructorAccessorImpl.newInstance(NativeConstructorAccessorImpl.java:77) at java.base/jdk.internal.reflect.DelegatingConstructorAccessorImpl.newInstance(DelegatingConstructorAccessorImpl.java:45) at java.base/java.lang.reflect.Constructor.newInstanceWithCaller(Constructor.java:500) at java.base/java.lang.reflect.Constructor.newInstance(Constructor.java:481) at org.codehaus.groovy.reflection.CachedConstructor.invoke(CachedConstructor.java:73) at org.codehaus.groovy.runtime.callsite.ConstructorSite$ConstructorSiteNoUnwrapNoCoerce.callConstructor(ConstructorSite.java:108) at org.codehaus.groovy.runtime.callsite.AbstractCallSite.callConstructor(AbstractCallSite.java:277) at io.restassured.internal.ResponseSpecificationImpl$HamcrestAssertionClosure.validate(ResponseSpecificationImpl.groovy:512) at io.restassured.internal.ResponseSpecificationImpl$HamcrestAssertionClosure$validate$1.call(Unknown Source) at io.restassured.internal.ResponseSpecificationImpl.validateResponseIfRequired(ResponseSpecificationImpl.groovy:696) at io.restassured.internal.ResponseSpecificationImpl.this$2$validateResponseIfRequired(ResponseSpecificationImpl.groovy) at java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke0(Native Method) at java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:77) at java.base/jdk.internal.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43) at java.base/java.lang.reflect.Method.invoke(Method.java:569) at org.codehaus.groovy.runtime.callsite.PlainObjectMetaMethodSite.doInvoke(PlainObjectMetaMethodSite.java:43) at org.codehaus.groovy.runtime.callsite.PogoMetaMethodSite$PogoCachedMethodSiteNoUnwrapNoCoerce.invoke(PogoMetaMethodSite.java:198) at org.codehaus.groovy.runtime.callsite.PogoMetaMethodSite.callCurrent(PogoMetaMethodSite.java:62) at org.codehaus.groovy.runtime.callsite.AbstractCallSite.callCurrent(AbstractCallSite.java:185) at io.restassured.internal.ResponseSpecificationImpl.statusCode(ResponseSpecificationImpl.groovy:135) at io.restassured.specification.ResponseSpecification$statusCode$0.callCurrent(Unknown Source) at io.restassured.internal.ResponseSpecificationImpl.statusCode(ResponseSpecificationImpl.groovy:143) at io.restassured.internal.ValidatableResponseOptionsImpl.statusCode(ValidatableResponseOptionsImpl.java:89) at GeneratedApiTest.createProjectWithValidTitle(GeneratedApiTest.java:63) at java.base/java.lang.reflect.Method.invoke(Method.java:569) at java.base/java.util.ArrayList.forEach(ArrayList.java:1511) at java.base/java.util.ArrayList.forEach(ArrayList.java:1511)

### Pontos para julgamento humano

- Confirmar ou corrigir cada categoria Q4 a partir da intenção efetiva do método.
- Confirmar se a resposta observada corresponde ao contrato OpenAPI e se a falha é do teste, da API ou inconclusiva.
- Registrar decisão, revisor, data e justificativa no CSV; não alterar o código original.

---

## Caso P-18 — `gpt-6` / repetição `01` / `createProjectWithMinimumLengthTitle`

**Identificador CSV:** `model=gpt-6; run=01; test_method=createProjectWithMinimumLengthTitle`
**Referência:** `tests/gpt-6/run-01.java#createProjectWithMinimumLengthTitle`
**Prioridade:** `HIGH`

### Código Java e contexto

```java
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
```

### Evidências e classificações propostas

- Operações REST: `POST /api/project`
- Q4 positivo proposto: `FALSE`
- Q4 negativo proposto: `FALSE`
- Q4 borda proposto: `TRUE`
- Q4 multi-endpoint proposto: `FALSE`
- Q1 — compilação: `passed`; sintaxe observada: `TRUE`; executável: `TRUE`; aprovado: `FALSE`
- Causa registrada: `assertion_error`
- Evidência: 1 expectation failed. Expected status code <200> but was <400>. java.lang.AssertionError: 1 expectation failed. Expected status code <200> but was <400>. at java.base/jdk.internal.reflect.NativeConstructorAccessorImpl.newInstance0(Native Method) at java.base/jdk.internal.reflect.NativeConstructorAccessorImpl.newInstance(NativeConstructorAccessorImpl.java:77) at java.base/jdk.internal.reflect.DelegatingConstructorAccessorImpl.newInstance(DelegatingConstructorAccessorImpl.java:45) at java.base/java.lang.reflect.Constructor.newInstanceWithCaller(Constructor.java:500) at java.base/java.lang.reflect.Constructor.newInstance(Constructor.java:481) at org.codehaus.groovy.reflection.CachedConstructor.invoke(CachedConstructor.java:73) at org.codehaus.groovy.runtime.callsite.ConstructorSite$ConstructorSiteNoUnwrapNoCoerce.callConstructor(ConstructorSite.java:108) at org.codehaus.groovy.runtime.callsite.AbstractCallSite.callConstructor(AbstractCallSite.java:277) at io.restassured.internal.ResponseSpecificationImpl$HamcrestAssertionClosure.validate(ResponseSpecificationImpl.groovy:512) at io.restassured.internal.ResponseSpecificationImpl$HamcrestAssertionClosure$validate$1.call(Unknown Source) at io.restassured.internal.ResponseSpecificationImpl.validateResponseIfRequired(ResponseSpecificationImpl.groovy:696) at io.restassured.internal.ResponseSpecificationImpl.this$2$validateResponseIfRequired(ResponseSpecificationImpl.groovy) at java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke0(Native Method) at java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:77) at java.base/jdk.internal.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43) at java.base/java.lang.reflect.Method.invoke(Method.java:569) at org.codehaus.groovy.runtime.callsite.PlainObjectMetaMethodSite.doInvoke(PlainObjectMetaMethodSite.java:43) at org.codehaus.groovy.runtime.callsite.PogoMetaMethodSite$PogoCachedMethodSiteNoUnwrapNoCoerce.invoke(PogoMetaMethodSite.java:198) at org.codehaus.groovy.runtime.callsite.PogoMetaMethodSite.callCurrent(PogoMetaMethodSite.java:62) at org.codehaus.groovy.runtime.callsite.AbstractCallSite.callCurrent(AbstractCallSite.java:185) at io.restassured.internal.ResponseSpecificationImpl.statusCode(ResponseSpecificationImpl.groovy:135) at io.restassured.specification.ResponseSpecification$statusCode$0.callCurrent(Unknown Source) at io.restassured.internal.ResponseSpecificationImpl.statusCode(ResponseSpecificationImpl.groovy:143) at io.restassured.internal.ValidatableResponseOptionsImpl.statusCode(ValidatableResponseOptionsImpl.java:89) at GeneratedApiTest.createProjectWithMinimumLengthTitle(GeneratedApiTest.java:73) at java.base/java.lang.reflect.Method.invoke(Method.java:569) at java.base/java.util.ArrayList.forEach(ArrayList.java:1511) at java.base/java.util.ArrayList.forEach(ArrayList.java:1511)

### Pontos para julgamento humano

- Confirmar ou corrigir cada categoria Q4 a partir da intenção efetiva do método.
- Confirmar se a resposta observada corresponde ao contrato OpenAPI e se a falha é do teste, da API ou inconclusiva.
- Registrar decisão, revisor, data e justificativa no CSV; não alterar o código original.

---

## Caso P-19 — `gpt-6` / repetição `01` / `createProjectWithMaximumLengthTitle`

**Identificador CSV:** `model=gpt-6; run=01; test_method=createProjectWithMaximumLengthTitle`
**Referência:** `tests/gpt-6/run-01.java#createProjectWithMaximumLengthTitle`
**Prioridade:** `HIGH`

### Código Java e contexto

```java
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
```

### Evidências e classificações propostas

- Operações REST: `POST /api/project`
- Q4 positivo proposto: `FALSE`
- Q4 negativo proposto: `FALSE`
- Q4 borda proposto: `TRUE`
- Q4 multi-endpoint proposto: `FALSE`
- Q1 — compilação: `passed`; sintaxe observada: `TRUE`; executável: `TRUE`; aprovado: `FALSE`
- Causa registrada: `assertion_error`
- Evidência: 1 expectation failed. Expected status code <200> but was <201>. java.lang.AssertionError: 1 expectation failed. Expected status code <200> but was <201>. at java.base/jdk.internal.reflect.NativeConstructorAccessorImpl.newInstance0(Native Method) at java.base/jdk.internal.reflect.NativeConstructorAccessorImpl.newInstance(NativeConstructorAccessorImpl.java:77) at java.base/jdk.internal.reflect.DelegatingConstructorAccessorImpl.newInstance(DelegatingConstructorAccessorImpl.java:45) at java.base/java.lang.reflect.Constructor.newInstanceWithCaller(Constructor.java:500) at java.base/java.lang.reflect.Constructor.newInstance(Constructor.java:481) at org.codehaus.groovy.reflection.CachedConstructor.invoke(CachedConstructor.java:73) at org.codehaus.groovy.runtime.callsite.ConstructorSite$ConstructorSiteNoUnwrapNoCoerce.callConstructor(ConstructorSite.java:108) at org.codehaus.groovy.runtime.callsite.AbstractCallSite.callConstructor(AbstractCallSite.java:277) at io.restassured.internal.ResponseSpecificationImpl$HamcrestAssertionClosure.validate(ResponseSpecificationImpl.groovy:512) at io.restassured.internal.ResponseSpecificationImpl$HamcrestAssertionClosure$validate$1.call(Unknown Source) at io.restassured.internal.ResponseSpecificationImpl.validateResponseIfRequired(ResponseSpecificationImpl.groovy:696) at io.restassured.internal.ResponseSpecificationImpl.this$2$validateResponseIfRequired(ResponseSpecificationImpl.groovy) at jdk.internal.reflect.GeneratedMethodAccessor110.invoke(Unknown Source) at java.base/jdk.internal.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43) at java.base/java.lang.reflect.Method.invoke(Method.java:569) at org.codehaus.groovy.runtime.callsite.PlainObjectMetaMethodSite.doInvoke(PlainObjectMetaMethodSite.java:43) at org.codehaus.groovy.runtime.callsite.PogoMetaMethodSite$PogoCachedMethodSiteNoUnwrapNoCoerce.invoke(PogoMetaMethodSite.java:198) at org.codehaus.groovy.runtime.callsite.PogoMetaMethodSite.callCurrent(PogoMetaMethodSite.java:62) at org.codehaus.groovy.runtime.callsite.AbstractCallSite.callCurrent(AbstractCallSite.java:185) at io.restassured.internal.ResponseSpecificationImpl.statusCode(ResponseSpecificationImpl.groovy:135) at io.restassured.specification.ResponseSpecification$statusCode$0.callCurrent(Unknown Source) at io.restassured.internal.ResponseSpecificationImpl.statusCode(ResponseSpecificationImpl.groovy:143) at io.restassured.internal.ValidatableResponseOptionsImpl.statusCode(ValidatableResponseOptionsImpl.java:89) at GeneratedApiTest.createProjectWithMaximumLengthTitle(GeneratedApiTest.java:84) at java.base/java.lang.reflect.Method.invoke(Method.java:569) at java.base/java.util.ArrayList.forEach(ArrayList.java:1511) at java.base/java.util.ArrayList.forEach(ArrayList.java:1511)

### Pontos para julgamento humano

- Confirmar ou corrigir cada categoria Q4 a partir da intenção efetiva do método.
- Confirmar se a resposta observada corresponde ao contrato OpenAPI e se a falha é do teste, da API ou inconclusiva.
- Registrar decisão, revisor, data e justificativa no CSV; não alterar o código original.

---

## Caso P-20 — `gpt-6` / repetição `01` / `createProjectWithEmptyTitle`

**Identificador CSV:** `model=gpt-6; run=01; test_method=createProjectWithEmptyTitle`
**Referência:** `tests/gpt-6/run-01.java#createProjectWithEmptyTitle`
**Prioridade:** `HIGH`

### Código Java e contexto

```java
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
```

### Evidências e classificações propostas

- Operações REST: `POST /api/project`
- Q4 positivo proposto: `FALSE`
- Q4 negativo proposto: `TRUE`
- Q4 borda proposto: `TRUE`
- Q4 multi-endpoint proposto: `FALSE`
- Q1 — compilação: `passed`; sintaxe observada: `TRUE`; executável: `TRUE`; aprovado: `FALSE`
- Causa registrada: `assertion_error`
- Evidência: 1 expectation failed. Expected status code <200> but was <400>. java.lang.AssertionError: 1 expectation failed. Expected status code <200> but was <400>. at java.base/jdk.internal.reflect.NativeConstructorAccessorImpl.newInstance0(Native Method) at java.base/jdk.internal.reflect.NativeConstructorAccessorImpl.newInstance(NativeConstructorAccessorImpl.java:77) at java.base/jdk.internal.reflect.DelegatingConstructorAccessorImpl.newInstance(DelegatingConstructorAccessorImpl.java:45) at java.base/java.lang.reflect.Constructor.newInstanceWithCaller(Constructor.java:500) at java.base/java.lang.reflect.Constructor.newInstance(Constructor.java:481) at org.codehaus.groovy.reflection.CachedConstructor.invoke(CachedConstructor.java:73) at org.codehaus.groovy.runtime.callsite.ConstructorSite$ConstructorSiteNoUnwrapNoCoerce.callConstructor(ConstructorSite.java:108) at org.codehaus.groovy.runtime.callsite.CallSiteArray.defaultCallConstructor(CallSiteArray.java:57) at org.codehaus.groovy.runtime.callsite.AbstractCallSite.callConstructor(AbstractCallSite.java:263) at org.codehaus.groovy.runtime.callsite.AbstractCallSite.callConstructor(AbstractCallSite.java:277) at io.restassured.internal.ResponseSpecificationImpl$HamcrestAssertionClosure.validate(ResponseSpecificationImpl.groovy:512) at io.restassured.internal.ResponseSpecificationImpl$HamcrestAssertionClosure$validate$1.call(Unknown Source) at org.codehaus.groovy.runtime.callsite.CallSiteArray.defaultCall(CallSiteArray.java:45) at org.codehaus.groovy.runtime.callsite.AbstractCallSite.call(AbstractCallSite.java:125) at org.codehaus.groovy.runtime.callsite.AbstractCallSite.call(AbstractCallSite.java:139) at io.restassured.internal.ResponseSpecificationImpl.validateResponseIfRequired(ResponseSpecificationImpl.groovy:696) at io.restassured.internal.ResponseSpecificationImpl.this$2$validateResponseIfRequired(ResponseSpecificationImpl.groovy) at java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke0(Native Method) at java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:77) at java.base/jdk.internal.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43) at java.base/java.lang.reflect.Method.invoke(Method.java:569) at org.codehaus.groovy.runtime.callsite.PlainObjectMetaMethodSite.doInvoke(PlainObjectMetaMethodSite.java:43) at org.codehaus.groovy.runtime.callsite.PogoMetaMethodSite$PogoCachedMethodSiteNoUnwrapNoCoerce.invoke(PogoMetaMethodSite.java:198) at org.codehaus.groovy.runtime.callsite.PogoMetaMethodSite.callCurrent(PogoMetaMethodSite.java:62) at org.codehaus.groovy.runtime.callsite.CallSiteArray.defaultCallCurrent(CallSiteArray.java:49) at org.codehaus.groovy.runtime.callsite.AbstractCallSite.callCurrent(AbstractCallSite.java:171) at org.codehaus.groovy.runtime.callsite.AbstractCallSite.callCurrent(AbstractCallSite.java:185) at io.restassured.internal.ResponseSpecificationImpl.statusCode(ResponseSpecificationImpl.groovy:135) at io.restassured.specification.ResponseSpecification$statusCode$0.callCurrent(Unknown Source) at org.codehaus.groovy.runtime.callsite.CallSiteArray.defaultCallCurrent(CallSiteArray.java:49) at org.codehaus.groovy.runtime.callsite.AbstractCallSite.callCurrent(AbstractCallSite.java:171) at org.codehaus.groovy.runtime.callsite.AbstractCallSite.callCurrent(AbstractCallSite.java:185) at io.restassured.internal.ResponseSpecificationImpl.statusCode(ResponseSpecificationImpl.groovy:143) at io.restassured.internal.ValidatableResponseOptionsImpl.statusCode(ValidatableResponseOptionsImpl.java:89) at GeneratedApiTest.createProjectWithEmptyTitle(GeneratedApiTest.java:128) at java.base/java.lang.reflect.Method.invoke(Method.java:569) at java.base/java.util.ArrayList.forEach(ArrayList.java:1511) at java.base/java.util.ArrayList.forEach(ArrayList.java:1511)

### Pontos para julgamento humano

- Confirmar ou corrigir cada categoria Q4 a partir da intenção efetiva do método.
- Confirmar se a resposta observada corresponde ao contrato OpenAPI e se a falha é do teste, da API ou inconclusiva.
- Registrar decisão, revisor, data e justificativa no CSV; não alterar o código original.

---

## Caso P-21 — `gpt-6` / repetição `02` / `createProjectWithoutTitle`

**Identificador CSV:** `model=gpt-6; run=02; test_method=createProjectWithoutTitle`
**Referência:** `tests/gpt-6/run-02.java#createProjectWithoutTitle`
**Prioridade:** `MEDIUM`

### Código Java e contexto

```java
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
```

### Evidências e classificações propostas

- Operações REST: `POST /api/project`
- Q4 positivo proposto: `FALSE`
- Q4 negativo proposto: `FALSE`
- Q4 borda proposto: `FALSE`
- Q4 multi-endpoint proposto: `FALSE`
- Q1 — compilação: `passed`; sintaxe observada: `TRUE`; executável: `TRUE`; aprovado: `TRUE`
- Causa registrada: `nenhuma`
- Evidência: não há evidência de falha no Surefire.

### Pontos para julgamento humano

- Confirmar ou corrigir cada categoria Q4 a partir da intenção efetiva do método.
- Confirmar se a resposta observada corresponde ao contrato OpenAPI e se a falha é do teste, da API ou inconclusiva.
- Registrar decisão, revisor, data e justificativa no CSV; não alterar o código original.

---

## Caso P-22 — `gpt-6` / repetição `02` / `getProjectWithSixCharacterCode`

**Identificador CSV:** `model=gpt-6; run=02; test_method=getProjectWithSixCharacterCode`
**Referência:** `tests/gpt-6/run-02.java#getProjectWithSixCharacterCode`
**Prioridade:** `MEDIUM`

### Código Java e contexto

```java
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
```

### Evidências e classificações propostas

- Operações REST: `GET /api/project/{code}`
- Q4 positivo proposto: `FALSE`
- Q4 negativo proposto: `FALSE`
- Q4 borda proposto: `FALSE`
- Q4 multi-endpoint proposto: `FALSE`
- Q1 — compilação: `passed`; sintaxe observada: `TRUE`; executável: `TRUE`; aprovado: `TRUE`
- Causa registrada: `nenhuma`
- Evidência: não há evidência de falha no Surefire.

### Pontos para julgamento humano

- Confirmar ou corrigir cada categoria Q4 a partir da intenção efetiva do método.
- Confirmar se a resposta observada corresponde ao contrato OpenAPI e se a falha é do teste, da API ou inconclusiva.
- Registrar decisão, revisor, data e justificativa no CSV; não alterar o código original.

---

## Caso P-23 — `gpt-6` / repetição `03` / `createProjectWithoutRequiredTitle`

**Identificador CSV:** `model=gpt-6; run=03; test_method=createProjectWithoutRequiredTitle`
**Referência:** `tests/gpt-6/run-03.java#createProjectWithoutRequiredTitle`
**Prioridade:** `MEDIUM`

### Código Java e contexto

```java
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
```

### Evidências e classificações propostas

- Operações REST: `POST /api/project`
- Q4 positivo proposto: `FALSE`
- Q4 negativo proposto: `FALSE`
- Q4 borda proposto: `FALSE`
- Q4 multi-endpoint proposto: `FALSE`
- Q1 — compilação: `passed`; sintaxe observada: `TRUE`; executável: `TRUE`; aprovado: `TRUE`
- Causa registrada: `nenhuma`
- Evidência: não há evidência de falha no Surefire.

### Pontos para julgamento humano

- Confirmar ou corrigir cada categoria Q4 a partir da intenção efetiva do método.
- Confirmar se a resposta observada corresponde ao contrato OpenAPI e se a falha é do teste, da API ou inconclusiva.
- Registrar decisão, revisor, data e justificativa no CSV; não alterar o código original.

---

## Caso P-24 — `gpt-6` / repetição `01` / `createProjectWithTitleExceedingMaximumLength`

**Identificador CSV:** `model=gpt-6; run=01; test_method=createProjectWithTitleExceedingMaximumLength`
**Referência:** `tests/gpt-6/run-01.java#createProjectWithTitleExceedingMaximumLength`
**Prioridade:** `LOW`

### Código Java e contexto

```java
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
```

### Evidências e classificações propostas

- Operações REST: `POST /api/project`
- Q4 positivo proposto: `FALSE`
- Q4 negativo proposto: `FALSE`
- Q4 borda proposto: `TRUE`
- Q4 multi-endpoint proposto: `FALSE`
- Q1 — compilação: `passed`; sintaxe observada: `TRUE`; executável: `TRUE`; aprovado: `TRUE`
- Causa registrada: `nenhuma`
- Evidência: não há evidência de falha no Surefire.

### Pontos para julgamento humano

- Confirmar ou corrigir cada categoria Q4 a partir da intenção efetiva do método.
- Confirmar se a resposta observada corresponde ao contrato OpenAPI e se a falha é do teste, da API ou inconclusiva.
- Registrar decisão, revisor, data e justificativa no CSV; não alterar o código original.

---

## Caso P-25 — `gpt-6` / repetição `01` / `createProjectWithoutTitle`

**Identificador CSV:** `model=gpt-6; run=01; test_method=createProjectWithoutTitle`
**Referência:** `tests/gpt-6/run-01.java#createProjectWithoutTitle`
**Prioridade:** `LOW`

### Código Java e contexto

```java
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
```

### Evidências e classificações propostas

- Operações REST: `POST /api/project`
- Q4 positivo proposto: `FALSE`
- Q4 negativo proposto: `TRUE`
- Q4 borda proposto: `TRUE`
- Q4 multi-endpoint proposto: `FALSE`
- Q1 — compilação: `passed`; sintaxe observada: `TRUE`; executável: `TRUE`; aprovado: `TRUE`
- Causa registrada: `nenhuma`
- Evidência: não há evidência de falha no Surefire.

### Pontos para julgamento humano

- Confirmar ou corrigir cada categoria Q4 a partir da intenção efetiva do método.
- Confirmar se a resposta observada corresponde ao contrato OpenAPI e se a falha é do teste, da API ou inconclusiva.
- Registrar decisão, revisor, data e justificativa no CSV; não alterar o código original.

---

## Caso P-26 — `gpt-6` / repetição `01` / `createProjectWithNullTitle`

**Identificador CSV:** `model=gpt-6; run=01; test_method=createProjectWithNullTitle`
**Referência:** `tests/gpt-6/run-01.java#createProjectWithNullTitle`
**Prioridade:** `LOW`

### Código Java e contexto

```java
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
```

### Evidências e classificações propostas

- Operações REST: `POST /api/project`
- Q4 positivo proposto: `FALSE`
- Q4 negativo proposto: `TRUE`
- Q4 borda proposto: `FALSE`
- Q4 multi-endpoint proposto: `FALSE`
- Q1 — compilação: `passed`; sintaxe observada: `TRUE`; executável: `TRUE`; aprovado: `TRUE`
- Causa registrada: `nenhuma`
- Evidência: não há evidência de falha no Surefire.

### Pontos para julgamento humano

- Confirmar ou corrigir cada categoria Q4 a partir da intenção efetiva do método.
- Confirmar se a resposta observada corresponde ao contrato OpenAPI e se a falha é do teste, da API ou inconclusiva.
- Registrar decisão, revisor, data e justificativa no CSV; não alterar o código original.

---

## Caso P-27 — `gpt-6` / repetição `01` / `createProjectWithMinimumLengthTitle`

**Identificador CSV:** `model=gpt-6; run=01; test_method=createProjectWithMinimumLengthTitle`
**Referência:** `tests/gpt-6/run-01.java#createProjectWithMinimumLengthTitle`
**Prioridade:** `HIGH`

### Código Java e contexto

```java
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
```

### Evidências e classificações propostas

- Operações REST: `POST /api/project`
- Q4 positivo proposto: `FALSE`
- Q4 negativo proposto: `FALSE`
- Q4 borda proposto: `TRUE`
- Q4 multi-endpoint proposto: `FALSE`
- Q1 — compilação: `passed`; sintaxe observada: `TRUE`; executável: `TRUE`; aprovado: `FALSE`
- Causa registrada: `assertion_error`
- Evidência: 1 expectation failed. Expected status code <200> but was <400>. java.lang.AssertionError: 1 expectation failed. Expected status code <200> but was <400>. at java.base/jdk.internal.reflect.NativeConstructorAccessorImpl.newInstance0(Native Method) at java.base/jdk.internal.reflect.NativeConstructorAccessorImpl.newInstance(NativeConstructorAccessorImpl.java:77) at java.base/jdk.internal.reflect.DelegatingConstructorAccessorImpl.newInstance(DelegatingConstructorAccessorImpl.java:45) at java.base/java.lang.reflect.Constructor.newInstanceWithCaller(Constructor.java:500) at java.base/java.lang.reflect.Constructor.newInstance(Constructor.java:481) at org.codehaus.groovy.reflection.CachedConstructor.invoke(CachedConstructor.java:73) at org.codehaus.groovy.runtime.callsite.ConstructorSite$ConstructorSiteNoUnwrapNoCoerce.callConstructor(ConstructorSite.java:108) at org.codehaus.groovy.runtime.callsite.AbstractCallSite.callConstructor(AbstractCallSite.java:277) at io.restassured.internal.ResponseSpecificationImpl$HamcrestAssertionClosure.validate(ResponseSpecificationImpl.groovy:512) at io.restassured.internal.ResponseSpecificationImpl$HamcrestAssertionClosure$validate$1.call(Unknown Source) at io.restassured.internal.ResponseSpecificationImpl.validateResponseIfRequired(ResponseSpecificationImpl.groovy:696) at io.restassured.internal.ResponseSpecificationImpl.this$2$validateResponseIfRequired(ResponseSpecificationImpl.groovy) at java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke0(Native Method) at java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:77) at java.base/jdk.internal.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43) at java.base/java.lang.reflect.Method.invoke(Method.java:569) at org.codehaus.groovy.runtime.callsite.PlainObjectMetaMethodSite.doInvoke(PlainObjectMetaMethodSite.java:43) at org.codehaus.groovy.runtime.callsite.PogoMetaMethodSite$PogoCachedMethodSiteNoUnwrapNoCoerce.invoke(PogoMetaMethodSite.java:198) at org.codehaus.groovy.runtime.callsite.PogoMetaMethodSite.callCurrent(PogoMetaMethodSite.java:62) at org.codehaus.groovy.runtime.callsite.AbstractCallSite.callCurrent(AbstractCallSite.java:185) at io.restassured.internal.ResponseSpecificationImpl.statusCode(ResponseSpecificationImpl.groovy:135) at io.restassured.specification.ResponseSpecification$statusCode$0.callCurrent(Unknown Source) at io.restassured.internal.ResponseSpecificationImpl.statusCode(ResponseSpecificationImpl.groovy:143) at io.restassured.internal.ValidatableResponseOptionsImpl.statusCode(ValidatableResponseOptionsImpl.java:89) at GeneratedApiTest.createProjectWithMinimumLengthTitle(GeneratedApiTest.java:73) at java.base/java.lang.reflect.Method.invoke(Method.java:569) at java.base/java.util.ArrayList.forEach(ArrayList.java:1511) at java.base/java.util.ArrayList.forEach(ArrayList.java:1511)

### Pontos para julgamento humano

- Confirmar ou corrigir cada categoria Q4 a partir da intenção efetiva do método.
- Confirmar se a resposta observada corresponde ao contrato OpenAPI e se a falha é do teste, da API ou inconclusiva.
- Registrar decisão, revisor, data e justificativa no CSV; não alterar o código original.

---

## Caso P-28 — `gpt-6` / repetição `01` / `createProjectWithMaximumLengthTitle`

**Identificador CSV:** `model=gpt-6; run=01; test_method=createProjectWithMaximumLengthTitle`
**Referência:** `tests/gpt-6/run-01.java#createProjectWithMaximumLengthTitle`
**Prioridade:** `HIGH`

### Código Java e contexto

```java
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
```

### Evidências e classificações propostas

- Operações REST: `POST /api/project`
- Q4 positivo proposto: `FALSE`
- Q4 negativo proposto: `FALSE`
- Q4 borda proposto: `TRUE`
- Q4 multi-endpoint proposto: `FALSE`
- Q1 — compilação: `passed`; sintaxe observada: `TRUE`; executável: `TRUE`; aprovado: `FALSE`
- Causa registrada: `assertion_error`
- Evidência: 1 expectation failed. Expected status code <200> but was <201>. java.lang.AssertionError: 1 expectation failed. Expected status code <200> but was <201>. at java.base/jdk.internal.reflect.NativeConstructorAccessorImpl.newInstance0(Native Method) at java.base/jdk.internal.reflect.NativeConstructorAccessorImpl.newInstance(NativeConstructorAccessorImpl.java:77) at java.base/jdk.internal.reflect.DelegatingConstructorAccessorImpl.newInstance(DelegatingConstructorAccessorImpl.java:45) at java.base/java.lang.reflect.Constructor.newInstanceWithCaller(Constructor.java:500) at java.base/java.lang.reflect.Constructor.newInstance(Constructor.java:481) at org.codehaus.groovy.reflection.CachedConstructor.invoke(CachedConstructor.java:73) at org.codehaus.groovy.runtime.callsite.ConstructorSite$ConstructorSiteNoUnwrapNoCoerce.callConstructor(ConstructorSite.java:108) at org.codehaus.groovy.runtime.callsite.AbstractCallSite.callConstructor(AbstractCallSite.java:277) at io.restassured.internal.ResponseSpecificationImpl$HamcrestAssertionClosure.validate(ResponseSpecificationImpl.groovy:512) at io.restassured.internal.ResponseSpecificationImpl$HamcrestAssertionClosure$validate$1.call(Unknown Source) at io.restassured.internal.ResponseSpecificationImpl.validateResponseIfRequired(ResponseSpecificationImpl.groovy:696) at io.restassured.internal.ResponseSpecificationImpl.this$2$validateResponseIfRequired(ResponseSpecificationImpl.groovy) at jdk.internal.reflect.GeneratedMethodAccessor110.invoke(Unknown Source) at java.base/jdk.internal.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43) at java.base/java.lang.reflect.Method.invoke(Method.java:569) at org.codehaus.groovy.runtime.callsite.PlainObjectMetaMethodSite.doInvoke(PlainObjectMetaMethodSite.java:43) at org.codehaus.groovy.runtime.callsite.PogoMetaMethodSite$PogoCachedMethodSiteNoUnwrapNoCoerce.invoke(PogoMetaMethodSite.java:198) at org.codehaus.groovy.runtime.callsite.PogoMetaMethodSite.callCurrent(PogoMetaMethodSite.java:62) at org.codehaus.groovy.runtime.callsite.AbstractCallSite.callCurrent(AbstractCallSite.java:185) at io.restassured.internal.ResponseSpecificationImpl.statusCode(ResponseSpecificationImpl.groovy:135) at io.restassured.specification.ResponseSpecification$statusCode$0.callCurrent(Unknown Source) at io.restassured.internal.ResponseSpecificationImpl.statusCode(ResponseSpecificationImpl.groovy:143) at io.restassured.internal.ValidatableResponseOptionsImpl.statusCode(ValidatableResponseOptionsImpl.java:89) at GeneratedApiTest.createProjectWithMaximumLengthTitle(GeneratedApiTest.java:84) at java.base/java.lang.reflect.Method.invoke(Method.java:569) at java.base/java.util.ArrayList.forEach(ArrayList.java:1511) at java.base/java.util.ArrayList.forEach(ArrayList.java:1511)

### Pontos para julgamento humano

- Confirmar ou corrigir cada categoria Q4 a partir da intenção efetiva do método.
- Confirmar se a resposta observada corresponde ao contrato OpenAPI e se a falha é do teste, da API ou inconclusiva.
- Registrar decisão, revisor, data e justificativa no CSV; não alterar o código original.

---

## Caso P-29 — `gpt-6` / repetição `01` / `createProjectWithEmptyTitle`

**Identificador CSV:** `model=gpt-6; run=01; test_method=createProjectWithEmptyTitle`
**Referência:** `tests/gpt-6/run-01.java#createProjectWithEmptyTitle`
**Prioridade:** `HIGH`

### Código Java e contexto

```java
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
```

### Evidências e classificações propostas

- Operações REST: `POST /api/project`
- Q4 positivo proposto: `FALSE`
- Q4 negativo proposto: `TRUE`
- Q4 borda proposto: `TRUE`
- Q4 multi-endpoint proposto: `FALSE`
- Q1 — compilação: `passed`; sintaxe observada: `TRUE`; executável: `TRUE`; aprovado: `FALSE`
- Causa registrada: `assertion_error`
- Evidência: 1 expectation failed. Expected status code <200> but was <400>. java.lang.AssertionError: 1 expectation failed. Expected status code <200> but was <400>. at java.base/jdk.internal.reflect.NativeConstructorAccessorImpl.newInstance0(Native Method) at java.base/jdk.internal.reflect.NativeConstructorAccessorImpl.newInstance(NativeConstructorAccessorImpl.java:77) at java.base/jdk.internal.reflect.DelegatingConstructorAccessorImpl.newInstance(DelegatingConstructorAccessorImpl.java:45) at java.base/java.lang.reflect.Constructor.newInstanceWithCaller(Constructor.java:500) at java.base/java.lang.reflect.Constructor.newInstance(Constructor.java:481) at org.codehaus.groovy.reflection.CachedConstructor.invoke(CachedConstructor.java:73) at org.codehaus.groovy.runtime.callsite.ConstructorSite$ConstructorSiteNoUnwrapNoCoerce.callConstructor(ConstructorSite.java:108) at org.codehaus.groovy.runtime.callsite.CallSiteArray.defaultCallConstructor(CallSiteArray.java:57) at org.codehaus.groovy.runtime.callsite.AbstractCallSite.callConstructor(AbstractCallSite.java:263) at org.codehaus.groovy.runtime.callsite.AbstractCallSite.callConstructor(AbstractCallSite.java:277) at io.restassured.internal.ResponseSpecificationImpl$HamcrestAssertionClosure.validate(ResponseSpecificationImpl.groovy:512) at io.restassured.internal.ResponseSpecificationImpl$HamcrestAssertionClosure$validate$1.call(Unknown Source) at org.codehaus.groovy.runtime.callsite.CallSiteArray.defaultCall(CallSiteArray.java:45) at org.codehaus.groovy.runtime.callsite.AbstractCallSite.call(AbstractCallSite.java:125) at org.codehaus.groovy.runtime.callsite.AbstractCallSite.call(AbstractCallSite.java:139) at io.restassured.internal.ResponseSpecificationImpl.validateResponseIfRequired(ResponseSpecificationImpl.groovy:696) at io.restassured.internal.ResponseSpecificationImpl.this$2$validateResponseIfRequired(ResponseSpecificationImpl.groovy) at java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke0(Native Method) at java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:77) at java.base/jdk.internal.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43) at java.base/java.lang.reflect.Method.invoke(Method.java:569) at org.codehaus.groovy.runtime.callsite.PlainObjectMetaMethodSite.doInvoke(PlainObjectMetaMethodSite.java:43) at org.codehaus.groovy.runtime.callsite.PogoMetaMethodSite$PogoCachedMethodSiteNoUnwrapNoCoerce.invoke(PogoMetaMethodSite.java:198) at org.codehaus.groovy.runtime.callsite.PogoMetaMethodSite.callCurrent(PogoMetaMethodSite.java:62) at org.codehaus.groovy.runtime.callsite.CallSiteArray.defaultCallCurrent(CallSiteArray.java:49) at org.codehaus.groovy.runtime.callsite.AbstractCallSite.callCurrent(AbstractCallSite.java:171) at org.codehaus.groovy.runtime.callsite.AbstractCallSite.callCurrent(AbstractCallSite.java:185) at io.restassured.internal.ResponseSpecificationImpl.statusCode(ResponseSpecificationImpl.groovy:135) at io.restassured.specification.ResponseSpecification$statusCode$0.callCurrent(Unknown Source) at org.codehaus.groovy.runtime.callsite.CallSiteArray.defaultCallCurrent(CallSiteArray.java:49) at org.codehaus.groovy.runtime.callsite.AbstractCallSite.callCurrent(AbstractCallSite.java:171) at org.codehaus.groovy.runtime.callsite.AbstractCallSite.callCurrent(AbstractCallSite.java:185) at io.restassured.internal.ResponseSpecificationImpl.statusCode(ResponseSpecificationImpl.groovy:143) at io.restassured.internal.ValidatableResponseOptionsImpl.statusCode(ValidatableResponseOptionsImpl.java:89) at GeneratedApiTest.createProjectWithEmptyTitle(GeneratedApiTest.java:128) at java.base/java.lang.reflect.Method.invoke(Method.java:569) at java.base/java.util.ArrayList.forEach(ArrayList.java:1511) at java.base/java.util.ArrayList.forEach(ArrayList.java:1511)

### Pontos para julgamento humano

- Confirmar ou corrigir cada categoria Q4 a partir da intenção efetiva do método.
- Confirmar se a resposta observada corresponde ao contrato OpenAPI e se a falha é do teste, da API ou inconclusiva.
- Registrar decisão, revisor, data e justificativa no CSV; não alterar o código original.

---

## Caso P-30 — `gpt-6` / repetição `01` / `createProjectWithOptionalCodeAtMaximumLength`

**Identificador CSV:** `model=gpt-6; run=01; test_method=createProjectWithOptionalCodeAtMaximumLength`
**Referência:** `tests/gpt-6/run-01.java#createProjectWithOptionalCodeAtMaximumLength`
**Prioridade:** `HIGH`

### Código Java e contexto

```java
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
```

### Evidências e classificações propostas

- Operações REST: `POST /api/project`
- Q4 positivo proposto: `FALSE`
- Q4 negativo proposto: `FALSE`
- Q4 borda proposto: `TRUE`
- Q4 multi-endpoint proposto: `FALSE`
- Q1 — compilação: `passed`; sintaxe observada: `TRUE`; executável: `TRUE`; aprovado: `FALSE`
- Causa registrada: `assertion_error`
- Evidência: 1 expectation failed. Expected status code <200> but was <201>. java.lang.AssertionError: 1 expectation failed. Expected status code <200> but was <201>. at java.base/jdk.internal.reflect.GeneratedConstructorAccessor57.newInstance(Unknown Source) at java.base/jdk.internal.reflect.DelegatingConstructorAccessorImpl.newInstance(DelegatingConstructorAccessorImpl.java:45) at java.base/java.lang.reflect.Constructor.newInstanceWithCaller(Constructor.java:500) at java.base/java.lang.reflect.Constructor.newInstance(Constructor.java:481) at org.codehaus.groovy.reflection.CachedConstructor.invoke(CachedConstructor.java:73) at org.codehaus.groovy.runtime.callsite.ConstructorSite$ConstructorSiteNoUnwrapNoCoerce.callConstructor(ConstructorSite.java:108) at org.codehaus.groovy.runtime.callsite.AbstractCallSite.callConstructor(AbstractCallSite.java:277) at io.restassured.internal.ResponseSpecificationImpl$HamcrestAssertionClosure.validate(ResponseSpecificationImpl.groovy:512) at io.restassured.internal.ResponseSpecificationImpl$HamcrestAssertionClosure$validate$1.call(Unknown Source) at io.restassured.internal.ResponseSpecificationImpl.validateResponseIfRequired(ResponseSpecificationImpl.groovy:696) at io.restassured.internal.ResponseSpecificationImpl.this$2$validateResponseIfRequired(ResponseSpecificationImpl.groovy) at jdk.internal.reflect.GeneratedMethodAccessor110.invoke(Unknown Source) at java.base/jdk.internal.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43) at java.base/java.lang.reflect.Method.invoke(Method.java:569) at org.codehaus.groovy.runtime.callsite.PlainObjectMetaMethodSite.doInvoke(PlainObjectMetaMethodSite.java:43) at org.codehaus.groovy.runtime.callsite.PogoMetaMethodSite$PogoCachedMethodSiteNoUnwrapNoCoerce.invoke(PogoMetaMethodSite.java:198) at org.codehaus.groovy.runtime.callsite.PogoMetaMethodSite.callCurrent(PogoMetaMethodSite.java:62) at org.codehaus.groovy.runtime.callsite.AbstractCallSite.callCurrent(AbstractCallSite.java:185) at io.restassured.internal.ResponseSpecificationImpl.statusCode(ResponseSpecificationImpl.groovy:135) at io.restassured.specification.ResponseSpecification$statusCode$0.callCurrent(Unknown Source) at io.restassured.internal.ResponseSpecificationImpl.statusCode(ResponseSpecificationImpl.groovy:143) at io.restassured.internal.ValidatableResponseOptionsImpl.statusCode(ValidatableResponseOptionsImpl.java:89) at GeneratedApiTest.createProjectWithOptionalCodeAtMaximumLength(GeneratedApiTest.java:142) at java.base/java.lang.reflect.Method.invoke(Method.java:569) at java.base/java.util.ArrayList.forEach(ArrayList.java:1511) at java.base/java.util.ArrayList.forEach(ArrayList.java:1511)

### Pontos para julgamento humano

- Confirmar ou corrigir cada categoria Q4 a partir da intenção efetiva do método.
- Confirmar se a resposta observada corresponde ao contrato OpenAPI e se a falha é do teste, da API ou inconclusiva.
- Registrar decisão, revisor, data e justificativa no CSV; não alterar o código original.

---
