# LLM-as-a-Judge — etapa complementar

## Estado desta preparação

Esta infraestrutura foi preparada a partir do commit `b15c61f`, mas nenhum modelo avaliador foi acessível no ambiente durante esta execução. Portanto, foram produzidos **0 julgamentos de IA**. Não foram simuladas respostas, justificativas, confiança ou concordâncias. Os 817 métodos permanecem `NOT_EVALUATED` e `PENDING_HUMAN_REVIEW`.

O LLM-as-a-Judge é uma etapa complementar e não substitui o protocolo experimental, a revisão humana ou os resultados originais. O arquivo `results/final-analysis/human-review.csv` foi preservado; seu SHA-256 nesta preparação é `d8db691e3b99c3ac2cd626c896eb1c8ae73e4ba95ad2c600d4399815016a3ce6`.

## Evidência e unidade de avaliação

Cada unidade é identificada por `model`, `run` e `test_method`. O avaliador deverá receber o método Java original com o contexto auxiliar necessário, a especificação OpenAPI congelada, o relatório Surefire correspondente quando existir, os logs oficiais e o protocolo. Os conjuntos Gemini 05/06 devem manter Q1 individual como `UNKNOWN`, pois não há observação Surefire por método.

O avaliador deve distinguir:

- dados observados: compilação, execução, aprovação e mensagens dos relatórios;
- inferências: intenção Q4, categoria de falha e avaliação de contrato;
- ausência de evidência: `UNKNOWN` ou `UNCERTAIN`, nunca uma conclusão inventada.

## Prompt e critérios congelados

O prompt fixo está em `prompts/ai-judge.template.txt` (SHA-256 `b118d8cbc5536e6b59b00ff280c7b56f19b9497929cf94206ef1a74977db82bf`). A resposta deve ser JSON estrito, com Q1, Q4, justificativa, evidências, confiança, ambiguidade e necessidade de revisão humana. O nome do método não pode ser a única evidência. Multi-endpoint exige pelo menos dois pares distintos de método HTTP e caminho. Aprovação no JUnit não é qualidade nem capacidade de detectar defeitos. `api_contract_200_201` só pode ser usado para requisição válida em `POST /api/project` com evidência explícita da comparação entre os status.

## Artefatos produzidos

- `results/ai-review/ai-judgments.csv`: 817 linhas rastreáveis, sem julgamentos, estado `NOT_EVALUATED`.
- `results/ai-review/uncertain-cases.csv`: 817 pendências, com motivo explícito de indisponibilidade do avaliador.
- `results/ai-review/disagreements.csv`: somente cabeçalho; não houve segunda avaliação.
- `results/ai-review/summary.csv`: 216 Gemini, 601 GPT-6, 0 avaliados e 817 pendentes.
- `results/ai-review/manual-sample-40.csv`: amostra diversificada para conferência humana posterior.

## Procedimento futuro reproduzível

1. Fixar o identificador e a configuração do avaliador independente, incluindo provedor, modelo, versão/data, temperatura, limite de tokens e lote.
2. Usar `prompts/ai-judge.template.txt` sem alterações e registrar seu hash.
3. Enviar cada unidade individualmente ou em lotes que incluam todo o contexto daquele método; preservar a resposta bruta do avaliador.
4. Validar JSON, identificador, enumerações e referências de evidência antes de marcar `evaluation_status=AI_REVIEWED`.
5. Reavaliar independentemente casos `LOW` confidence, `PRESENT` ambiguity e divergências de contrato; registrar divergências sem escolher silenciosamente um resultado.
6. Manter `review_status=PENDING_HUMAN_REVIEW` até conferência humana. Concordância entre IAs não equivale a validação humana.

## Riscos e limitações

Um LLM pode seguir nomes de métodos em vez do comportamento, interpretar incorretamente o OpenAPI, confundir aprovação com qualidade, omitir evidência, apresentar excesso de confiança e reproduzir vieses do prompt. Temperatura, modelo, versão e truncamento podem alterar decisões. A seleção de 40 métodos é para calibração e não representa estatisticamente a amostra. A adoção dessa etapa como método final depende da aprovação do orientador.

Validação executada: `python3 scripts/validate_ai_review.py`.
