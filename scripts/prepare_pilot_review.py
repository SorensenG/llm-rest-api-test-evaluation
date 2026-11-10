#!/usr/bin/env python3
import csv, re
from pathlib import Path

ROOT = Path(__file__).resolve().parents[1]
CSV = ROOT / 'results' / 'final-analysis' / 'human-review.csv'
OUT = ROOT / 'docs' / 'human-review-pilot-30.md'

def choose(rows, model, run=None, priority=None, cause=None, approved=None, n=1):
    candidates = [r for r in rows if r['model'] == model]
    if run: candidates = [r for r in candidates if r['run'] == run]
    if priority: candidates = [r for r in candidates if r['priority'] == priority]
    if cause is not None: candidates = [r for r in candidates if r['q1_failure_cause'] == cause]
    if approved is not None: candidates = [r for r in candidates if r['q1_approved_observed'] == approved]
    return candidates[:n]

def method_code(model, run, name):
    path = ROOT / 'tests' / model / f'run-{run}.java'
    if not path.exists(): return '(fonte Java não encontrada)'
    text = path.read_text(encoding='utf-8')
    starts = [m for m in re.finditer(r'@Test\b', text)]
    for i, start in enumerate(starts):
        end = starts[i + 1].start() if i + 1 < len(starts) else text.rfind('}')
        block = text[start.start():end].strip()
        if re.search(r'\b' + re.escape(name) + r'\s*\(', block):
            setup = text[:start.start()]
            setup = setup[setup.find('public class GeneratedApiTest'):]
            return setup.strip() + '\n\n' + block
    return '(método não localizado automaticamente; consultar code_reference)'

def main():
    with CSV.open(encoding='utf-8', newline='') as f: rows = list(csv.DictReader(f))
    selected = []
    # Gemini: compile failures, execution errors, assertion errors and approved cases.
    selected += choose(rows, 'gemini-3.6-think', run='05', cause='compile_failure_unknown_per_method', n=2)
    selected += choose(rows, 'gemini-3.6-think', run='06', cause='compile_failure_unknown_per_method', n=2)
    selected += choose(rows, 'gemini-3.6-think', cause='execution_error', n=2)
    selected += choose(rows, 'gemini-3.6-think', cause='assertion_error', n=2)
    selected += choose(rows, 'gemini-3.6-think', priority='LOW', approved='TRUE', n=7)
    # GPT-6: 200/201, assertion, medium, low approved, and low non-approved.
    selected += choose(rows, 'gpt-6', cause='api_error_contract_status_200_201', n=2)
    selected += choose(rows, 'gpt-6', cause='assertion_error', n=3)
    selected += choose(rows, 'gpt-6', priority='MEDIUM', approved='TRUE', n=3)
    selected += choose(rows, 'gpt-6', priority='LOW', approved='TRUE', n=3)
    selected += choose(rows, 'gpt-6', cause='assertion_error', n=4)
    assert len(selected) == 30, len(selected)
    lines = ['# Revisão-piloto humana — 30 métodos', '', '> Piloto de calibração, não uma amostra estatística. Gerado a partir do CSV preservado em `results/final-analysis/human-review.csv`. Nenhuma linha foi validada automaticamente.', '', '## Procedimento', '', 'Para cada caso, conferir o código original indicado em `code_reference`, o relatório/evidência disponível e o OpenAPI. Preencher a decisão somente após julgamento humano; esta versão deixa todos os campos de decisão em aberto. A seleção cobre os dois modelos, prioridades HIGH/MEDIUM/LOW, aprovações, falhas de asserção, erros de execução e compilação inválida.', '']
    for i, r in enumerate(selected, 1):
        lines += [
            f"## Caso P-{i:02d} — `{r['model']}` / repetição `{r['run']}` / `{r['test_method']}`", '',
            f"**Identificador CSV:** `model={r['model']}; run={r['run']}; test_method={r['test_method']}`",
            f"**Referência:** `{r['code_reference']}`", f"**Prioridade:** `{r['priority']}`", '',
            '### Código Java e contexto', '', '```java', method_code(r['model'], r['run'], r['test_method']), '```', '',
            '### Evidências e classificações propostas', '',
            f"- Operações REST: `{r['operations'] or 'não identificadas'}`",
            f"- Q4 positivo proposto: `{r['q4_positive_proposed']}`",
            f"- Q4 negativo proposto: `{r['q4_negative_proposed']}`",
            f"- Q4 borda proposto: `{r['q4_boundary_proposed']}`",
            f"- Q4 multi-endpoint proposto: `{r['q4_multi_endpoint_proposed']}`",
            f"- Q1 — compilação: `{r['compile_status']}`; sintaxe observada: `{r['q1_syntactically_valid_observed'] or 'UNKNOWN'}`; executável: `{r['q1_executable_observed'] or 'UNKNOWN'}`; aprovado: `{r['q1_approved_observed'] or 'UNKNOWN'}`",
            f"- Causa registrada: `{r['q1_failure_cause'] or 'nenhuma'}`",
            f"- Evidência: {r['evidence'] or 'não há evidência de falha no Surefire.'}", '',
            '### Pontos para julgamento humano', '',
            '- Confirmar ou corrigir cada categoria Q4 a partir da intenção efetiva do método.',
            '- Confirmar se a resposta observada corresponde ao contrato OpenAPI e se a falha é do teste, da API ou inconclusiva.',
            '- Registrar decisão, revisor, data e justificativa no CSV; não alterar o código original.', '', '---', '']
    OUT.write_text('\n'.join(lines), encoding='utf-8')
    print(f'wrote {len(selected)} pilot cases to {OUT}')

if __name__ == '__main__': main()
