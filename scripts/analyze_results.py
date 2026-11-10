#!/usr/bin/env python3
import csv
import re
import xml.etree.ElementTree as ET
from pathlib import Path

ROOT = Path(__file__).resolve().parents[1]
MODELS = ('gemini-3.6-think', 'gpt-6')


def source_methods(source):
    text = source.read_text(encoding='utf-8')
    starts = list(re.finditer(r'@Test\s+(?:public\s+|protected\s+|private\s+)?(?:static\s+)?(?:[\w<>\[\], ?]+\s+)?(\w+)\s*\([^)]*\)\s*\{', text))
    methods = {}
    for index, match in enumerate(starts):
        end = starts[index + 1].start() if index + 1 < len(starts) else len(text)
        methods[match.group(1)] = text[match.start():end]
    return methods, len(starts)


def scenarios(method, body):
    lower = body.lower()
    requests = len(re.findall(r'\.(?:get|post|put|delete|patch|head|options)\s*\(', body))
    negative_words = ('invalid', 'notfound', 'not_found', 'doesnotexist', 'does_not_exist', 'malformed', 'missing', 'null', 'empty', 'negative', 'unknown', 'nonexistent', 'error', 'reject')
    boundary_words = ('boundary', 'limit', 'maximum', 'minimum', 'max', 'min', 'zero', 'empty', 'long', 'short', 'length', 'negative')
    positive = ('success' in lower or 'valid' in lower or 'created' in lower) and not any(w in lower for w in ('invalid', 'notfound', 'not_found', 'negative'))
    negative = any(w in lower for w in negative_words)
    boundary = any(w in lower for w in boundary_words)
    multi = requests >= 2
    return positive, negative, boundary, multi


def main():
    individual = []
    summary = []
    for model in MODELS:
        for run in range(1, 11):
            run_id = f'{run:02d}'
            source = ROOT / 'tests' / model / f'run-{run_id}.java'
            report = ROOT / 'results' / 'junit-reports' / model / f'run-{run_id}' / 'TEST-GeneratedApiTest.xml'
            methods, source_count = source_methods(source)
            if not report.exists():
                log = ROOT / 'results' / 'execution-logs' / model / f'run-{run_id}' / 'stdout.txt'
                evidence = str(log.relative_to(ROOT)) if log.exists() else 'missing Surefire XML'
                summary.append({
                    'model': model, 'run': run_id, 'total_tests': source_count,
                    'syntactically_valid_count': 0, 'syntactically_valid_pct': '0.00' if source_count else '',
                    'executable_count': 0, 'executable_pct': '0.00' if source_count else '',
                    'approved_count': 0, 'approved_pct': '0.00' if source_count else '',
                    'positive_tests': '', 'negative_tests': '', 'boundary_tests': '', 'multi_endpoint_tests': '',
                    'assertion_errors': '', 'out_of_spec_requests': '', 'api_errors': '', 'other_errors': '',
                    'compile_status': 'failed', 'execution_status': 'not_executed'
                })
                continue
            root = ET.parse(report).getroot()
            testcases = root.findall('testcase')
            total = int(root.attrib.get('tests', len(testcases)))
            errors = int(root.attrib.get('errors', 0))
            failures = int(root.attrib.get('failures', 0))
            approved = 0
            executable = 0
            counts = {'positive': 0, 'negative': 0, 'boundary': 0, 'multi': 0}
            for case in testcases:
                name = case.attrib.get('name', '')
                failure = case.find('failure')
                error = case.find('error')
                text = ''
                if failure is not None:
                    text = (failure.attrib.get('message') or '') + '\n' + (failure.text or '')
                elif error is not None:
                    text = (error.attrib.get('message') or '') + '\n' + (error.text or '')
                is_error = error is not None
                is_failure = failure is not None
                if not is_error:
                    executable += 1
                if not is_error and not is_failure:
                    approved += 1
                body = methods.get(name, '')
                positive, negative, boundary, multi = scenarios(name, body)
                for key, value in (('positive', positive), ('negative', negative), ('boundary', boundary), ('multi', multi)):
                    if value:
                        counts[key] += 1
                cause = ''
                if is_error:
                    cause = 'execution_error'
                elif is_failure:
                    if re.search(r'Expected status code <(?:200|201)> but was <(?:200|201)>', text):
                        cause = 'api_error'
                        text += ' | subcategory=contrato-openapi-status-http-200-201'
                    else:
                        cause = 'assertion_error'
                individual.append({
                    'model': model, 'run': run_id, 'test_method': name,
                    'syntactically_valid': 'TRUE',
                    'executable': 'TRUE' if not is_error else 'FALSE',
                    'approved': 'TRUE' if not is_error and not is_failure else 'FALSE',
                    'scenario_positive': 'TRUE' if positive else 'FALSE',
                    'scenario_negative': 'TRUE' if negative else 'FALSE',
                    'scenario_boundary': 'TRUE' if boundary else 'FALSE',
                    'scenario_multi_endpoint': 'TRUE' if multi else 'FALSE',
                    'failure_cause': cause,
                    'evidence': str(report.relative_to(ROOT)) + ((' | ' + ' '.join(text.split())[:500]) if text.strip() else ''),
                    'review_status': 'PENDING_HUMAN_REVIEW'
                })
            total = max(total, source_count)
            pct = lambda value: '' if total == 0 else f'{value / total * 100:.2f}'
            summary.append({
                'model': model, 'run': run_id, 'total_tests': total,
                'syntactically_valid_count': total, 'syntactically_valid_pct': pct(total),
                'executable_count': executable, 'executable_pct': pct(executable),
                'approved_count': approved, 'approved_pct': pct(approved),
                'positive_tests': counts['positive'], 'negative_tests': counts['negative'],
                'boundary_tests': counts['boundary'], 'multi_endpoint_tests': counts['multi'],
                'assertion_errors': failures, 'out_of_spec_requests': '',
                'api_errors': sum(1 for row in individual if row['model'] == model and row['run'] == run_id and row['failure_cause'] == 'api_error'),
                'other_errors': errors, 'compile_status': 'passed',
                'execution_status': 'passed' if errors == 0 and failures == 0 else 'failed'
            })
    results = ROOT / 'results'
    with (results / 'individual-tests.csv').open('w', newline='', encoding='utf-8') as handle:
        fields = list(individual[0])
        writer = csv.DictWriter(handle, fieldnames=fields); writer.writeheader(); writer.writerows(individual)
    with (results / 'summary.csv').open('w', newline='', encoding='utf-8') as handle:
        fields = list(summary[0])
        writer = csv.DictWriter(handle, fieldnames=fields); writer.writeheader(); writer.writerows(summary)
    print(f'analysed {len(summary)} sets and {len(individual)} testcases')


if __name__ == '__main__':
    main()
