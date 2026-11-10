# Comparação final

Fonte principal: `results/official-run-2/`. Os resultados da primeira execução são históricos.

| Modelo | Métodos gerados | Conjuntos compilados | Métodos sintaticamente válidos conhecidos | Executáveis | Aprovados | Aprovados/executáveis |
|---|---:|---:|---:|---:|---:|
| gemini-3.6-think | 216 | 8/10 | 172 | 152 | 122 | 80.26% |
| gpt-6 | 601 | 10/10 | 601 | 601 | 178 | 29.62% |

“Conjuntos compilados” é uma propriedade por repetição. “Métodos sintaticamente válidos conhecidos” é a soma dos métodos dos conjuntos que compilaram; nos dois conjuntos Gemini sem compilação não há atribuição individual de validade sintática.

As classificações Q4 são propostas estáticas e permanecem pendentes de revisão humana. A maior quantidade de testes não é interpretada como maior qualidade.

## Duplicações

- `gemini-3.6-think`: 3 hashes duplicados entre repetições; respostas não foram excluídas.
- `gpt-6`: 0 hashes duplicados entre repetições; respostas não foram excluídas.
