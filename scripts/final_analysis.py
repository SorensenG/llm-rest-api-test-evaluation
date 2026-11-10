#!/usr/bin/env python3
import csv, hashlib, re, statistics
import xml.etree.ElementTree as ET
from collections import Counter, defaultdict
from pathlib import Path

ROOT = Path(__file__).resolve().parents[1]
RUN = ROOT / 'results' / 'official-run-2'
OUT = ROOT / 'results' / 'final-analysis'
MODELS = ('gemini-3.6-think', 'gpt-6')

def methods_for(path):
    text = path.read_text(encoding='utf-8')
    matches = list(re.finditer(r'@Test\s+(?:public\s+|protected\s+|private\s+)?(?:static\s+)?(?:[\w<>\[\], ?]+\s+)?(\w+)\s*\([^)]*\)\s*\{', text))
    return {m.group(1): text[m.start():(matches[i+1].start() if i+1 < len(matches) else len(text))] for i, m in enumerate(matches)}, len(matches)

def operations(body):
    found = []
    for m in re.finditer(r'\.(get|post|put|delete|patch|head|options)\s*\(\s*(["\'])(.*?)\2', body, flags=re.I):
        found.append(f'{m.group(1).upper()} {m.group(3)}')
    return sorted(set(found))

def q4(body, name):
    text = (name + ' ' + body).lower()
    ops = operations(body)
    negative_words = ('invalid', 'notfound', 'not_found', 'unknown', 'missing', 'malformed', 'reject', 'null', 'empty', 'negative', 'nonexistent', 'doesnotexist')
    edge_words = ('boundary', 'limit', 'maximum', 'minimum', 'max', 'min', 'zero', 'empty', 'long', 'short', 'length', 'negative')
    negative = any(w in text for w in negative_words)
    boundary = any(w in text for w in edge_words)
    positive = any(w in text for w in ('success', 'valid', 'created')) and not negative
    multi = len(ops) >= 2
    return positive, negative, boundary, multi, '; '.join(ops)

def pct(n, d): return '' if d == 0 or n is None else f'{100*n/d:.2f}'

def main():
    OUT.mkdir(parents=True, exist_ok=True)
    validated, q1, q4_rows, failures = [], [], [], []
    per_model = defaultdict(lambda: defaultdict(list))
    duplicate_hashes = {}
    for model in MODELS:
        hashes = Counter()
        for run in range(1, 11):
            rid = f'{run:02d}'
            response = ROOT / 'responses' / model / f'run-{rid}.txt'
            hashes[hashlib.sha256(response.read_bytes()).hexdigest()] += 1
            source = ROOT / 'tests' / model / f'run-{rid}.java'
            methods, total = methods_for(source)
            report = RUN / 'junit-reports' / model / f'run-{rid}' / 'TEST-GeneratedApiTest.xml'
            log = RUN / 'execution-logs' / model / f'run-{rid}' / 'stdout.txt'
            testcase_map = {}
            compile_status = 'passed' if report.exists() else 'failed'
            if report.exists():
                root = ET.parse(report).getroot()
                for case in root.findall('testcase'):
                    name = case.attrib.get('name', '')
                    failure = case.find('failure'); error = case.find('error')
                    node = failure if failure is not None else error
                    text = '' if node is None else ' '.join(((node.attrib.get('message') or '') + ' ' + (node.text or '')).split())
                    testcase_map[name] = (failure is not None, error is not None, text)
            for name, body in methods.items():
                positive, negative, boundary, multi, optext = q4(body, name)
                known = name in testcase_map
                if known:
                    is_failure, is_error, evidence = testcase_map[name]
                    executable = not is_error
                    approved = not is_failure and not is_error
                    syntax = True
                    if is_error: cause = 'execution_error'
                    elif is_failure:
                        cause = 'assertion_error'
                        if ('POST /API/PROJECT' in optext.upper() and positive and re.search(r'Expected status code <(?:200|201)> but was <(?:200|201)>', evidence)):
                            cause = 'api_error_contract_status_200_201'
                    else: cause = ''
                else:
                    syntax = executable = approved = None
                    cause = 'compile_failure_unknown_per_method'
                    evidence = str(log.relative_to(ROOT)) if log.exists() else 'compile log unavailable'
                validated.append({'model': model, 'run': rid, 'test_method': name,
                    'compile_status': compile_status, 'syntactically_valid': '' if syntax is None else str(syntax).upper(),
                    'executable': '' if executable is None else str(executable).upper(), 'approved': '' if approved is None else str(approved).upper(),
                    'failure_cause': cause, 'scenario_positive': str(positive).upper(), 'scenario_negative': str(negative).upper(),
                    'scenario_boundary': str(boundary).upper(), 'scenario_multi_endpoint': str(multi).upper(),
                    'operations': optext, 'evidence': evidence, 'classification_status': 'PROPOSED', 'review_status': 'PENDING_HUMAN_REVIEW'})
                q4_rows.append({'model': model, 'run': rid, 'test_method': name, 'positive': int(positive), 'negative': int(negative), 'boundary': int(boundary), 'multi_endpoint': int(multi), 'operations': optext, 'status': 'PROPOSED'})
                if known and (is_failure or is_error):
                    failures.append({'model': model, 'run': rid, 'test_method': name, 'cause': cause, 'evidence': evidence, 'operations': optext, 'review_status': 'PENDING_HUMAN_REVIEW'})
            known_syntax = total if compile_status == 'passed' else None
            exec_n = sum(1 for x in testcase_map.values() if not x[1]) if testcase_map else None
            appr_n = sum(1 for x in testcase_map.values() if not x[0] and not x[1]) if testcase_map else None
            assertion_n = sum(1 for x in testcase_map.values() if x[0] and not x[1]) if testcase_map else None
            execution_n = sum(1 for x in testcase_map.values() if x[1]) if testcase_map else None
            row = {'model': model, 'run': rid, 'total_tests': total, 'compile_status': compile_status,
                   'syntactically_valid_count': '' if known_syntax is None else known_syntax,
                   'syntactically_valid_pct': '' if known_syntax is None else pct(known_syntax, total),
                   'executable_count': '' if exec_n is None else exec_n, 'executable_pct': '' if exec_n is None else pct(exec_n, total),
                   'approved_count': '' if appr_n is None else appr_n, 'approved_pct': '' if appr_n is None else pct(appr_n, total),
                   'approved_among_executable_pct': '' if not exec_n else pct(appr_n, exec_n),
                   'assertion_errors': '' if assertion_n is None else assertion_n, 'execution_errors': '' if execution_n is None else execution_n,
                   'review_status': 'PENDING_HUMAN_REVIEW'}
            q1.append(row)
            for field in ('total_tests','syntactically_valid_count','syntactically_valid_pct','executable_count','executable_pct','approved_count','approved_pct','approved_among_executable_pct'):
                if row[field] != '': per_model[model][field].append(float(row[field]))
        duplicate_hashes[model] = [(h, n) for h, n in hashes.items() if n > 1]

    def write(name, rows):
        path = OUT / name
        fields = list(rows[0]) if rows else []
        with path.open('w', encoding='utf-8', newline='') as f:
            w = csv.DictWriter(f, fieldnames=fields); w.writeheader(); w.writerows(rows)
    write('validated-tests.csv', validated)
    write('q1-summary.csv', q1)
    q4_summary = []
    for model in MODELS:
        subset = [r for r in q4_rows if r['model'] == model]
        for run in range(1, 11):
            rows = [r for r in subset if r['run'] == f'{run:02d}']
            q4_summary.append({'model': model, 'run': f'{run:02d}', 'total_methods': len(rows), 'positive': sum(r['positive'] for r in rows), 'negative': sum(r['negative'] for r in rows), 'boundary': sum(r['boundary'] for r in rows), 'multi_endpoint': sum(r['multi_endpoint'] for r in rows), 'classification_status': 'PROPOSED_PENDING_HUMAN_REVIEW'})
    write('q4-summary.csv', q4_summary); write('failure-analysis.csv', failures)
    variability = []
    for model in MODELS:
        for field, values in per_model[model].items():
            values = sorted(values); q1i = statistics.median(values[:len(values)//2]); q3i = statistics.median(values[(len(values)+1)//2:])
            variability.append({'model': model, 'metric': field, 'n_observations': len(values), 'minimum': min(values), 'maximum': max(values), 'median': statistics.median(values), 'iqr': q3i-q1i})
    write('variability.csv', variability)
    with (OUT / 'comparison.md').open('w', encoding='utf-8') as f:
        f.write('# Comparação final\n\nFonte principal: `results/official-run-2/`. Os resultados da primeira execução são históricos.\n\n')
        f.write('| Modelo | Testes | Compilabilidade | Executáveis | Aprovados | Aprovados/executáveis |\n|---|---:|---:|---:|---:|---:|\n')
        for model in MODELS:
            rows = [r for r in q1 if r['model'] == model]; total=sum(int(r['total_tests']) for r in rows); known=sum(int(r['syntactically_valid_count']) for r in rows if r['syntactically_valid_count']); exe=sum(int(r['executable_count']) for r in rows if r['executable_count']); app=sum(int(r['approved_count']) for r in rows if r['approved_count'])
            f.write(f'| {model} | {total} | {known}/{total} conjuntos conhecidos | {exe}/{total} | {app}/{total} | {pct(app, exe)}% |\n')
        f.write('\nAs classificações Q4 são propostas estáticas e permanecem pendentes de revisão humana. A maior quantidade de testes não é interpretada como maior qualidade.\n\n')
        f.write('## Duplicações\n\n')
        for model, pairs in duplicate_hashes.items():
            f.write(f'- `{model}`: {len(pairs)} hashes duplicados entre repetições; respostas não foram excluídas.\n')
    print(f'final analysis written to {OUT}')

if __name__ == '__main__': main()
