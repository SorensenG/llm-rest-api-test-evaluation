# Validação inicial

Resultados registrados no momento da preparação:

- `pom.xml`: compilação e teste de infraestrutura executados com sucesso.
- Java: Java 17 Temurin 17.0.21 verificado.
- Maven: Maven 3.10 verificado.
- Docker/Compose: Docker Desktop iniciado; MySQL 8.4.3 ficou saudável.
- OpenAPI: JSON válido; captura integral de `/v3/api-docs` preservada em `api/openapi.json`. A comparação estrutural passou para paths, operações, parâmetros, schemas e respostas; o dataset não contém outro arquivo OpenAPI versionado.
- Prompt: `prepare_prompt.py` gera o prompt completo e seu SHA-256.
- Scripts Python: compilação sintática com `python3 -m py_compile scripts/*.py`.
- Gerações oficiais: não executadas.
- Teste JUnit 5 + REST Assured passou; XML gerado em `results/junit-reports/infra/methodology-review/TEST-InfrastructureApiTest.xml`.
- Reset do banco passou; reset completo API+banco exige reinício do processo externo e foi documentado após a validação do comportamento HTTP 500 sem restart.
