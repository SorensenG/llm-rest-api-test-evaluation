#!/usr/bin/env python3
import csv, hashlib
from collections import Counter
from pathlib import Path

ROOT = Path(__file__).resolve().parents[1]
SRC = ROOT / 'results' / 'final-analysis' / 'human-review.csv'
OUT = ROOT / 'results' / 'ai-review'

JUDGMENT_FIELDS = ['model','run','test_method','source_identifier','code_reference','q1_syntactically_valid','q1_executable','q1_approved','q1_failure_category','q1_contract_assessment','q4_positive','q4_negative','q4_boundary','q4_multi_endpoint','justification','evidence','confidence','ambiguity','model_evaluator','evaluator_configuration','evaluation_status','review_status']

def main():
    with SRC.open(encoding='utf-8', newline='') as f: rows = list(csv.DictReader(f))
    assert len(rows) == 817 and len({(r['model'], r['run'], r['test_method']) for r in rows}) == 817
    OUT.mkdir(parents=True, exist_ok=True)
    judgments = []
    uncertain = []
    for r in rows:
        ident = f"model={r['model']};run={r['run']};test_method={r['test_method']}"
        judgments.append({k: ({'model':r['model'],'run':r['run'],'test_method':r['test_method'],'source_identifier':ident,'code_reference':r['code_reference'],'evaluation_status':'NOT_EVALUATED','review_status':'PENDING_HUMAN_REVIEW'}.get(k, '')) for k in JUDGMENT_FIELDS})
        uncertain.append({'model':r['model'],'run':r['run'],'test_method':r['test_method'],'source_identifier':ident,'reason':'evaluator_unavailable; no AI judgment simulated','existing_evidence':r['evidence'],'review_status':'PENDING_HUMAN_REVIEW'})
    for name, fields, data in [('ai-judgments.csv', JUDGMENT_FIELDS, judgments), ('uncertain-cases.csv', list(uncertain[0]), uncertain)]:
        with (OUT / name).open('w', encoding='utf-8', newline='') as f:
            w=csv.DictWriter(f, fieldnames=fields); w.writeheader(); w.writerows(data)
    with (OUT / 'disagreements.csv').open('w', encoding='utf-8', newline='') as f:
        fields=['source_identifier','first_evaluator','second_evaluator','disagreement_type','details','review_status']; csv.DictWriter(f,fieldnames=fields).writeheader()
    counts=Counter(r['model'] for r in rows)
    with (OUT / 'summary.csv').open('w', encoding='utf-8', newline='') as f:
        fields=['model','methods_total','ai_evaluated','ai_uncertain','human_review_required','evaluation_status']; w=csv.DictWriter(f,fieldnames=fields); w.writeheader()
        for model in ('gemini-3.6-think','gpt-6'): w.writerow({'model':model,'methods_total':counts[model],'ai_evaluated':0,'ai_uncertain':counts[model],'human_review_required':counts[model],'evaluation_status':'EVALUATOR_UNAVAILABLE'})
    # A diverse 40-row manual sample, without changing the original CSV.
    selected=[]
    for predicate in [lambda r:r['q1_failure_cause']=='compile_failure_unknown_per_method', lambda r:r['q1_failure_cause']=='execution_error', lambda r:r['q1_failure_cause']=='api_error_contract_status_200_201', lambda r:r['q1_failure_cause']=='assertion_error', lambda r:r['q1_failure_cause']=='', lambda r:r['priority']=='MEDIUM']:
        selected += [r for r in rows if predicate(r) and r not in selected][:8]
    selected=selected[:40]
    with (OUT / 'manual-sample-40.csv').open('w',encoding='utf-8',newline='') as f:
        fields=['sample_id','model','run','test_method','code_reference','priority','q1_failure_cause','operations','review_status']; w=csv.DictWriter(f,fieldnames=fields); w.writeheader()
        for i,r in enumerate(selected,1): w.writerow({'sample_id':f'S-{i:02d}','model':r['model'],'run':r['run'],'test_method':r['test_method'],'code_reference':r['code_reference'],'priority':r['priority'],'q1_failure_cause':r['q1_failure_cause'],'operations':r['operations'],'review_status':'PENDING_HUMAN_REVIEW'})
    print(f'prepared {len(rows)} unevaluated methods; manual sample={len(selected)}')

if __name__ == '__main__': main()
