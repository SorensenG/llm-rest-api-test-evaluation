# Mini-experimento: testes REST gerados por modelos de linguagem

Esta iniciação científica investiga testes Java gerados por Gemini 3.6 Think e GPT-6 para a API `spring-rest-example`, do Web Fuzzing Dataset. Foram coletadas dez respostas independentes por modelo, com o mesmo prompt congelado, e executadas sem reparação dos testes.

| Modelo | Métodos gerados | Conjuntos compilados | Executáveis observados | Aprovados | Aprovados / executáveis |
|---|---:|---:|---:|---:|---:|
| Gemini 3.6 Think | 216 | 8/10 | 152 | 122 | 80,26% |
| GPT-6 | 601 | 10/10 | 601 | 178 | 29,62% |

Os 44 métodos dos dois conjuntos Gemini não compilados têm resultado individual indeterminado. As classificações Q4 são preliminares e requerem revisão humana; as taxas não demonstram superioridade geral.

O [relatório](EXPERIMENT_REPORT.md) apresenta método, resultados e limitações. A [branch de arquivo técnico](https://github.com/SorensenG/llm-rest-api-test-evaluation/tree/archive/complete-experiment-2026-11-18) preserva infraestrutura, evidências e histórico completo.

- `api/`: OpenAPI e metadados do sistema sob teste.
- `prompts/`: prompt congelado e hash.
- `responses/`: respostas originais, dez por modelo.
- `tests/`: Java extraído, dez arquivos por modelo.
- `results/`: tabelas Q1 e Q4 preliminar copiadas da análise existente.
