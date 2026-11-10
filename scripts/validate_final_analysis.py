#!/usr/bin/env python3
import csv, hashlib
from pathlib import Path

ROOT = Path(__file__).resolve().parents[1]
OUT = ROOT / 'results' / 'final-analysis'
MODELS = ('gemini-3.6-think', 'gpt-6')
EXPECTED_PROMPT = '6b7d7e6c97703a79a10a9a7de600ab36c0d176669f9ec8835c3793afb4e857ed'

def rows(name):
    with (OUT / name).open(encoding='utf-8', newline='') as f:
        return list(csv.DictReader(f))

def main():
    q1 = rows('q1-summary.csv')
    q4 = rows('q4-summary.csv')
    tests = rows('validated-tests.csv')
    assert len(q1) == 20, len(q1)
    assert len(q4) == 20, len(q4)
    assert len(tests) == 817, len(tests)
    assert {r['model'] for r in q1} == set(MODELS)
    assert all(r['classification_status'] == 'PROPOSED_PENDING_HUMAN_REVIEW' for r in q4)
    assert all(r['review_status'] == 'PENDING_HUMAN_REVIEW' for r in tests)
    assert sum(r['compile_status'] == 'failed' for r in q1) == 2
    assert sum(not r['syntactically_valid_count'] for r in q1) == 2
    prompt = ROOT / 'prompts' / 'generation.txt'
    assert hashlib.sha256(prompt.read_bytes()).hexdigest() == EXPECTED_PROMPT
    print('final analysis validation: PASS')
    print(f'q1_rows={len(q1)} q4_rows={len(q4)} validated_test_rows={len(tests)} prompt_sha256={EXPECTED_PROMPT}')

if __name__ == '__main__':
    main()
