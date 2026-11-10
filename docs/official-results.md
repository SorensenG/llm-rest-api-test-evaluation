# Execução oficial e análise do mini-experimento

As vinte unidades experimentais foram executadas em 10 de novembro de 2026 com Java 17, Maven, JUnit 5, REST Assured, MySQL e o SUT `spring-rest-example` no commit registrado em `api/sut-metadata.json`. Cada conjunto teve o banco restaurado e o SUT reiniciado antes da execução. O prompt congelado permaneceu com SHA-256 `6b7d7e6c97703a79a10a9a7de600ab36c0d176669f9ec8835c3793afb4e857ed`.

O primeiro lote foi invalidado como tentativa técnica porque o extrator selecionava apenas o primeiro bloco Markdown de respostas Gemini que dividiam a classe Java entre prefixo, bloco e sufixo. Os logs dessa tentativa foram preservados localmente em `work/official-attempt-1/` para auditoria, mas não fazem parte dos resultados científicos. O extrator foi corrigido para concatenar somente os segmentos originais, sem reparar Java, imports ou asserções; o segundo lote foi o utilizado nos CSVs.

## Q1

O `results/summary.csv` contém uma linha por conjunto e o `results/individual-tests.csv` contém uma linha por método reportado pelo Surefire. Quando a classe não compilou, não foram inventadas linhas de teste: o total foi contado estaticamente pelos `@Test`, enquanto validade, execução e aprovação permaneceram zero; a compilação e a execução aparecem como falhas/não executadas.

Os resultados completos, incluindo erros de compilação, erros de execução, falhas de asserção e a divergência HTTP 200/201, estão nos logs e XML versionados em `results/execution-logs/` e `results/junit-reports/`. A divergência HTTP é preservada; as classificações de `individual-tests.csv` permanecem `PENDING_HUMAN_REVIEW`.

## Q4

As classificações de cenário foram propostas por análise estrutural dos nomes e corpos dos métodos: positivo, negativo, borda e multi-endpoint podem se sobrepor. Nenhuma proposta foi apresentada como validação humana; todos os registros permanecem pendentes de revisão.

## Limitações

Os metadados das respostas não comprovam automaticamente que cada conversa usou o prompt congelado; a identidade dos modelos foi confirmada por declaração do pesquisador. Respostas idênticas entre repetições foram preservadas e não foram descartadas. A execução em interfaces de chat não permite recuperar todos os parâmetros internos de amostragem.

## Reprodução

Com o SUT disponível, Java 17 selecionado e Docker ativo:

```bash
export JAVA_HOME=/Library/Java/JavaVirtualMachines/temurin-17.jdk/Contents/Home
export PATH="$JAVA_HOME/bin:$PATH"
python3 scripts/verify_prompt.py
API_BASE_URL=http://localhost:8080 python3 scripts/run_all.py --api-restart-command 'sh work/official-restart.sh'
python3 scripts/analyze_results.py
python3 scripts/summarize.py
```
