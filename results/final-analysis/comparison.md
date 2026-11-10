# Comparação final

Fonte principal: `results/official-run-2/`. Os resultados da primeira execução são históricos.

| Modelo | Testes | Compilabilidade | Executáveis | Aprovados | Aprovados/executáveis |
|---|---:|---:|---:|---:|---:|
| gemini-3.6-think | 216 | 172/216 conjuntos conhecidos | 152/216 | 122/216 | 80.26% |
| gpt-6 | 601 | 601/601 conjuntos conhecidos | 601/601 | 178/601 | 29.62% |

As classificações Q4 são propostas estáticas e permanecem pendentes de revisão humana. A maior quantidade de testes não é interpretada como maior qualidade.

## Duplicações

- `gemini-3.6-think`: 3 hashes duplicados entre repetições; respostas não foram excluídas.
- `gpt-6`: 0 hashes duplicados entre repetições; respostas não foram excluídas.
