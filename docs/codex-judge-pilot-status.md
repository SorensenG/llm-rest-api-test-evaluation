# Status do piloto Codex LLM-as-a-Judge

## Execução registrada

- Codex CLI: `0.167.0`, instalado via npm global `@openai/codex`.
- Autenticação: `codex login status` confirmou conta ChatGPT; `codex doctor` confirmou conectividade.
- Avaliador: `gpt-5.6-sol`, identificador aceito por uma chamada real.
- Interface: `codex exec`, sandbox `read-only`, sessão `--ephemeral`, schema JSON estrito.
- Prompt do avaliador: SHA-256 `b118d8cbc5536e6b59b00ff280c7b56f19b9497929cf94206ef1a74977db82bf`.

## Resultado

Uma avaliação unitária foi concluída e validada. O piloto balanceado foi iniciado com retomada e registrou 16 julgamentos únicos de 40. Os 24 restantes não foram concluídos nesta execução por timeouts/erros de chamada. O piloto, portanto, **não está pronto para revisão como conjunto completo**.

Os 16 julgamentos exportados estão em `results/ai-review-codex/ai-judgments.csv`, com `evaluation_status=AI_REVIEWED` e `review_status=PENDING_HUMAN_REVIEW`. As respostas brutas estão em `raw.jsonl`. Nenhum julgamento foi marcado como humano.

O lote de 817 métodos não foi iniciado. O CSV original `results/final-analysis/human-review.csv` não foi alterado e os testes experimentais não foram reexecutados.

## Problema operacional

O primeiro piloto revelou que o executor precisava de schema JSON estrito com `properties` e `required` nos objetos aninhados; isso foi corrigido. Também foi adicionado bloqueio de processo e timeout por caso. Os erros anteriores permanecem registrados separadamente no log local de execução; não são julgamentos científicos.

Para retomar os 24 casos restantes:

```bash
python3 scripts/codex_ai_judge.py \
  --model gpt-5.6-sol \
  --source results/ai-review/manual-sample-40-balanced.csv \
  --timeout 60 \
  --execute
```

Depois, exportar novamente:

```bash
python3 scripts/export_ai_judgments.py \
  --input results/ai-review-codex/ai-judgments.jsonl \
  --output-dir results/ai-review-codex
```
