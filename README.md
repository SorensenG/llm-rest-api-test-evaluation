# Avaliação Experimental de Modelos de Linguagem na Geração Automática de Testes para APIs RESTful

Infraestrutura reproduzível para uma Iniciação Científica da UNICAMP que compara, sob o mesmo prompt e a mesma especificação OpenAPI, testes de integração black-box gerados por `gemini-3.6-think` e `gpt-5.6-sol`.

## Escopo e estado atual

Este repositório prepara o experimento; as 20 respostas oficiais não são geradas aqui. Os diretórios `responses/`, `tests/` e `results/` estão prontos para receber dados coletados manualmente. Respostas originais devem ser preservadas sem edição.

O SUT é `jdk_17_maven/cs/rest/spring-rest-example` do Web Fuzzing Dataset, no commit `915859080da033fc36feae54bab3ad4a7b04e872`. O projeto original requer MySQL e contém placeholders de credenciais; o procedimento de inicialização está em [`docs/execution-guide.md`](docs/execution-guide.md).

## Requisitos

- Java 17 e Maven 3.9+.
- Python 3.10+.
- Docker e Docker Compose.
- `gh` autenticado apenas para publicação do repositório.

## Preparação

```bash
python3 scripts/prepare_prompt.py --verify
docker compose -f infrastructure/docker-compose.yml up -d mysql
```

Configure `API_BASE_URL` (padrão `http://localhost:8080`) e siga o guia de execução para compilar/iniciar o SUT original sem modificar seu comportamento.

Com Java 17 selecionado no macOS:

```bash
export JAVA_HOME=/Library/Java/JavaVirtualMachines/temurin-17.jdk/Contents/Home
export PATH="$JAVA_HOME/bin:$PATH"
cd /caminho/para/Dataset/jdk_17_maven/cs/rest/spring-rest-example
mvn -DskipTests package
java -jar target/spring-rest-example-sut.jar \
  --server.port=8080 \
  --spring.datasource.url='jdbc:mysql://127.0.0.1:3306/project_api?useSSL=false&allowPublicKeyRetrieval=true' \
  --spring.datasource.username=project \
  --spring.datasource.password=project \
  --spring.jpa.hibernate.ddl-auto=update
```

Em outro terminal, valide a infraestrutura com `mvn -Dtest=InfrastructureApiTest -DfailIfNoTests=false test`. O relatório XML ficará em `target/surefire-reports/`.

## Piloto técnico antes da coleta

O piloto isolado fica em `work/pilot/` e não é uma amostra oficial. Ele pode ser repetido com:

```bash
API_RESTART_COMMAND='sh work/pilot/restart_api.sh' \
  python3 scripts/run_one.py \
  --model pilot --run 1 \
  --test-source work/pilot/PilotInput.java \
  --log-dir work/pilot/logs \
  --reports-dir work/pilot/junit-reports
python3 scripts/summarize.py --input work/pilot/summary.csv --output work/pilot/medians.csv
```

Antes de cada geração oficial, execute `python3 scripts/verify_prompt.py`. A coleta manual só deve começar depois da confirmação humana de que Google Gemini 3.6 Think (`gemini-3.6-think`) está selecionado na interface Gemini e OpenAI GPT-5.6 Sol (`gpt-5.6-sol`) na interface ChatGPT Work.

## Coleta manual

Antes da coleta, confirme a disponibilidade e seleção exata dos dois modelos. Para cada conversa nova, cole o conteúdo de `prompts/generation.txt`, salve a primeira resposta integralmente em:

```text
responses/gemini-3.6-think/run-01.txt
responses/gpt-5.6-sol/run-01.txt
```

O prompt não deve ser alterado após a primeira geração oficial. Registre metadados no arquivo `.metadata.json` correspondente, usando o modelo de [`responses/metadata.example.json`](responses/metadata.example.json) e o hash produzido pelo script.

## Extrair e executar um conjunto

```bash
python3 scripts/extract_java.py --model gemini-3.6-think --run 01
API_BASE_URL=http://localhost:8080 python3 scripts/run_one.py --model gemini-3.6-think --run 01
python3 scripts/run_all.py --api-restart-command 'sh /caminho/para/restart-sut.sh'
python3 scripts/summarize.py
```

`run_all.py` exige as 20 respostas originais antes de começar, extrai cada resposta sem reparos e executa os conjuntos em sequência. O reinício do SUT é obrigatório antes de cada conjunto. Falhas de compilação, execução ou asserção retornam código experimental `10` e não interrompem o lote; falhas de Docker, MySQL, reset, reinício ou saúde da API retornam código de infraestrutura `20` e interrompem o lote.

Para dados de validação separados do experimento oficial: `python3 scripts/extract_java.py --model fixture-model --run 99 --response work/validation-fixtures/response.txt --output work/validation-fixtures/GeneratedApiTest.java`, `python3 scripts/run_one.py --model fixture-model --run 99 --test-source work/validation-fixtures/GeneratedApiTest.java --dry-run` e `python3 scripts/summarize.py --input work/validation-fixtures/summary.csv --output work/validation-fixtures/medians.csv`.

`run_one.py` não corrige código, não adiciona imports e não ignora falhas. O teste de infraestrutura, quando usado, deve ficar separado de `tests/` e nunca entra nos CSVs científicos.

## Resultados

`individual-tests.csv` contém uma linha por método `@Test`, com classificações revisadas e causas baseadas em evidências. `summary.csv` contém uma linha por conjunto. `medians.csv` calcula medianas somente para valores presentes; campos pendentes não são convertidos em zero. A metodologia completa está em [`docs/experimental-protocol.md`](docs/experimental-protocol.md).

## Reprodutibilidade e limitações

O prompt final é montado literalmente com a especificação em `api/openapi.json`; o SHA-256 é impresso e salvo em `prompts/generation.sha256`. A interface de chat não permite controlar diretamente todos os parâmetros, como temperatura, e configurações internas das plataformas podem afetar as respostas. Nenhum modelo é presumido equivalente sem evidência experimental.

Referências e links oficiais estão em [`docs/references.md`](docs/references.md). Limitações conhecidas estão em [`docs/limitations.md`](docs/limitations.md).
