# Guia de execução

1. Obtenha o dataset fora deste repositório: `git clone --branch master https://github.com/WebFuzzing/Dataset.git` e confira o SHA em `api/sut-metadata.json`.
2. Inicie MySQL: `docker compose -f infrastructure/docker-compose.yml up -d mysql`.
3. No projeto selecionado, configure propriedades equivalentes a `jdbc:mysql://localhost:3306/project_api`, usuário `project`, senha `project`, porta 8080. Não altere controllers ou regras do SUT.
4. Compile/inicie o SUT com Java 17/Maven conforme o `README.md` original. O dataset não contém Dockerfile nem Compose para este projeto.
5. Verifique `curl -fsS "$API_BASE_URL/v3/api-docs"` e compare com `api/openapi.json`; diferenças devem ser registradas antes da coleta.
6. Antes de cada conjunto, execute `API_RESTART_COMMAND='seu-comando-de-restart' python3 scripts/reset_api.py`. O script recria `project_api` dentro do MySQL; o comando de restart é obrigatório na prática para o Hibernate recriar o schema. Sem ele, o banco é resetado, mas a aplicação em execução pode retornar HTTP 500 por estar com tabelas antigas/inexistentes.

Para validar somente a infraestrutura, sem contabilizar testes gerados:

```bash
API_BASE_URL=http://127.0.0.1:8080 mvn -Dtest=InfrastructureApiTest -DfailIfNoTests=false test
```

Esse teste fica em `infrastructure-test-src/` e é excluído dos builds de `run_one.py`. Para validar scripts sem dados oficiais, use `extract_java.py --response ... --output ...`, `run_one.py --dry-run` e `summarize.py --input ... --output ...` com arquivos em `work/validation-fixtures/`.

Neste ambiente, Java 17 e Maven não estavam instalados. O daemon Docker também não estava ativo, então MySQL, SUT, acesso HTTP, reset de estado, teste manual de infraestrutura e relatórios Surefire não foram executados. Não há resultados científicos gerados.
