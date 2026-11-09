# Guia de execução

1. Obtenha o dataset fora deste repositório: `git clone --branch master https://github.com/WebFuzzing/Dataset.git` e confira o SHA em `api/sut-metadata.json`.
2. Inicie MySQL: `docker compose -f infrastructure/docker-compose.yml up -d mysql`.
3. No projeto selecionado, configure propriedades equivalentes a `jdbc:mysql://localhost:3306/project_api`, usuário `project`, senha `project`, porta 8080. Não altere controllers ou regras do SUT.
4. Compile/inicie o SUT com Java 17/Maven conforme o `README.md` original. O dataset não contém Dockerfile nem Compose para este projeto.
5. Verifique `curl -fsS "$API_BASE_URL/v3/api-docs"` e compare com `api/openapi.json`; diferenças devem ser registradas antes da coleta.
6. Antes de cada conjunto, execute `python3 scripts/reset_api.py`. O reset recria o banco configurado pelo Compose; reinicie a aplicação se necessário para o schema ser recriado.

Neste ambiente, Java 17 e Maven não estavam instalados. O daemon Docker também não estava ativo, então MySQL, SUT, acesso HTTP, reset de estado, teste manual de infraestrutura e relatórios Surefire não foram executados. Não há resultados científicos gerados.
