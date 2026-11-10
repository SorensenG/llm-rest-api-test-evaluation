# Procedimento de revisão humana

`results/final-analysis/human-review.csv` contém uma linha para cada um dos 817 métodos. As colunas `*_proposed` são sugestões automatizadas; as colunas `human_*` ficam vazias até a intervenção do pesquisador.

## Ordem e procedimento

1. Revisar primeiro `priority=HIGH`: falhas de compilação sem observação por método, erros de execução, falhas de asserção e os casos HTTP 200/201.
2. Revisar `priority=MEDIUM`: métodos sem evidência suficiente para qualquer categoria Q4.
3. Revisar `priority=LOW`: métodos com evidência estática de cenário, confirmando a intenção no código.
4. Abrir `code_reference`, conferir `operations` e a evidência Surefire, quando existir.
5. Marcar cada campo Q4 humano como `TRUE`, `FALSE` ou `UNCERTAIN`. Multi-endpoint exige pelo menos dois pares distintos de método HTTP e caminho.
6. Para falhas, escolher `compile_failure`, `execution_error`, `assertion_test_oracle`, `api_contract_200_201`, `other` ou `UNCERTAIN`.
7. Preencher `human_decision` como `CONFIRMED`, `CORRECTED` ou `PENDING`, além de `reviewer`, `review_date` e `review_notes`.

Nenhuma linha deve ser considerada humanamente validada sem esses campos. Os 44 métodos Gemini 05/06 permanecem com Q1 desconhecido por método. A divergência 200/201 só deve ser confirmada para requisição válida em `POST /api/project` com evidência da comparação entre os dois status.
