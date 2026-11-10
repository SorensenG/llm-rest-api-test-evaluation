#!/usr/bin/env python3
import csv
from pathlib import Path
ROOT=Path(__file__).resolve().parents[1]
SRC=ROOT/'results/final-analysis/human-review.csv'; OUT=ROOT/'results/ai-review/manual-sample-40-balanced.csv'
def main():
    with SRC.open(encoding='utf-8',newline='') as f: rows=list(csv.DictReader(f))
    selected=[]
    for model in ('gemini-3.6-think','gpt-6'):
        groups=[lambda r:r['q1_failure_cause']=='compile_failure_unknown_per_method',lambda r:r['q1_failure_cause']=='execution_error',lambda r:r['q1_failure_cause']=='api_error_contract_status_200_201',lambda r:r['q1_failure_cause']=='assertion_error',lambda r:r['q1_failure_cause']=='']
        for group in groups:
            selected += [r for r in rows if r['model']==model and group(r) and r not in selected][:4]
        selected += [r for r in rows if r['model']==model and r not in selected][:20-len([x for x in selected if x['model']==model])]
    assert len(selected)==40
    fields=['sample_id','model','run','test_method','code_reference','priority','q1_failure_cause','operations','review_status']
    with OUT.open('w',encoding='utf-8',newline='') as f:
        w=csv.DictWriter(f,fieldnames=fields); w.writeheader()
        for i,r in enumerate(selected,1): w.writerow({'sample_id':f'S-{i:02d}','model':r['model'],'run':r['run'],'test_method':r['test_method'],'code_reference':r['code_reference'],'priority':r['priority'],'q1_failure_cause':r['q1_failure_cause'],'operations':r['operations'],'review_status':'PENDING_HUMAN_REVIEW'})
    print('wrote balanced 40-case pilot')
if __name__=='__main__':main()
