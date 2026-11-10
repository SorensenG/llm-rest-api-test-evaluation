# Relatório final de validação experimental

## Escopo e fonte dos dados

Esta etapa valida e organiza os resultados já produzidos na segunda execução oficial, em `results/official-run-2/`. Não houve nova execução de testes, alteração das respostas, do Java extraído, do prompt, do OpenAPI ou da API. A primeira execução, em `results/official-run-1/`, foi preservada como registro histórico e não foi usada como substituta dos dados da segunda execução.

Os modelos identificados nos registros são `gemini-3.6-think` e `gpt-6`, com dez respostas por modelo. A análise abaixo é descritiva: não estabelece superioridade entre modelos e não substitui a revisão humana das classificações de cenário.

## Integridade e reprodutibilidade

- Prompt congelado: SHA-256 `6b7d7e6c97703a79a10a9a7de600ab36c0d176669f9ec8835c3793afb4e857ed`.
- A especificação OpenAPI usada é a preservada no repositório; os artefatos oficiais não foram reescritos nesta etapa.
- `scripts/final_analysis.py` reconstrói os resultados a partir dos arquivos de resposta, fontes Java e relatórios Surefire.
- `scripts/validate_final_analysis.py` verifica contagens, estados de revisão, conjuntos com falha de compilação e o hash do prompt.
- As respostas originais permanecem preservadas. Foram encontrados três grupos de hash repetido no Gemini e nenhum no GPT-6; repetições idênticas não foram removidas.

## Q1 — validade, execução e aprovação

| Modelo | Testes | Sintaticamente válidos | Executáveis | Aprovados | Aprovados/executáveis |
|---|---:|---:|---:|---:|---:|
| Gemini 3.6 Think | 216 | 172 conhecidos; 2 conjuntos sem resultado por compilação | 152 | 122 | 80,26% |
| GPT-6 | 601 | 601 | 601 | 178 | 29,62% |

Os percentuais agregados usam os testes executáveis como denominador para aprovação. Os 44 métodos dos dois conjuntos Gemini que não chegaram ao Surefire permanecem com valores desconhecidos por método; não foram convertidos artificialmente em falha ou aprovação.

## Q4 — tipos de cenário

As contagens abaixo são propostas de classificação estática e estão marcadas como `PROPOSED_PENDING_HUMAN_REVIEW`. Positivo, negativo e borda foram inferidos por nomes e conteúdo dos métodos; multi-endpoint exige pelo menos duas operações distintas no par método HTTP + caminho literal. As categorias podem se sobrepor.

| Modelo | Métodos | Positivos | Negativos | Borda | Multi-endpoint |
|---|---:|---:|---:|---:|---:|
| Gemini 3.6 Think | 216 | 15 | 192 | 116 | 43 |
| GPT-6 | 601 | 48 | 275 | 223 | 81 |

Esses números não devem ser tratados como classificação científica final antes da revisão humana, especialmente nos casos em que o nome do método não deixa explícita a intenção do cenário.

## Causas de falha observadas

Na segunda execução, os relatórios registram:

- Gemini: 30 falhas de asserção e 20 erros de execução nos 8 conjuntos que chegaram ao Surefire; os dois conjuntos restantes falharam na compilação.
- GPT-6: 413 falhas de asserção, das quais 10 foram separadas como divergência de contrato HTTP 200/201 em `POST /api/project` quando a evidência do teste indicava requisição positiva e a mensagem registrava a comparação entre esses status.

A divergência HTTP 200 documentado/201 observado foi preservada. Ela não foi tratada como correção do teste nem da API; está apenas identificada para análise metodológica. Outras falhas de asserção permanecem agrupadas como falhas experimentais até revisão da evidência correspondente.

## Variabilidade entre repetições

Os valores completos, incluindo mínimo, máximo, mediana, IQR e número de observações, estão em `results/final-analysis/variability.csv`. A mediana de testes por repetição foi 21,0 para Gemini e 57,5 para GPT-6. A mediana de aprovação entre testes executáveis foi 82,35% para Gemini (8 observações, pois dois conjuntos não compilaram) e 30,73% para GPT-6 (10 observações). A diferença no número de testes gerados e na completude das execuções é uma limitação importante para qualquer comparação direta.

## Limitações e revisão pendente

Todos os registros produzidos por esta análise permanecem com `PENDING_HUMAN_REVIEW`; Q4 é proposicional, não uma codificação humana validada. A análise não infere equivalência de esforço, capacidade ou configuração interna dos modelos. Os conjuntos Gemini 05 e 06 não permitem atribuição por método após a compilação, portanto seus campos Q1 por método ficam desconhecidos. A separação entre erro da API e falha do teste depende da evidência textual registrada e deve ser confirmada manualmente.

## Artefatos

- `results/final-analysis/validated-tests.csv`
- `results/final-analysis/q1-summary.csv`
- `results/final-analysis/q4-summary.csv`
- `results/final-analysis/failure-analysis.csv`
- `results/final-analysis/variability.csv`
- `results/final-analysis/comparison.md`
- `results/final-analysis/human-review.csv`
- `docs/human-review-procedure.md`

Comando de validação: `python3 scripts/validate_final_analysis.py`.
