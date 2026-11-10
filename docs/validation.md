# Validação inicial

Resultados registrados no momento da preparação:

- `pom.xml`: inspeção estática concluída; execução Maven bloqueada porque Maven não está instalado localmente.
- Java: bloqueado para o requisito, pois o host expõe Java 26, não Java 17.
- Docker/Compose: cliente e arquivo Compose disponíveis; `docker compose config` passou, mas a execução foi bloqueada porque o daemon não estava rodando (`Cannot connect to the Docker daemon ... docker.sock`). MySQL e SUT não foram iniciados.
- OpenAPI: JSON válido; endpoints, métodos e nomes de schemas conferidos contra `/v3/api-docs` runtime. A especificação salva foi alinhada à captura runtime.
- Prompt: `prepare_prompt.py` gera o prompt completo e seu SHA-256.
- Scripts Python: compilação sintática com `python3 -m py_compile scripts/*.py`.
- Gerações oficiais: não executadas.
- Teste JUnit 5 + REST Assured passou; XML gerado em `results/junit-reports/infra/smoke/TEST-InfrastructureApiTest.xml`.
- Reset do banco passou; reset completo API+banco exige reinício do processo externo e foi documentado após a validação do comportamento HTTP 500 sem restart.
