#!/usr/bin/env python3
import csv, json, argparse
from pathlib import Path

FIELDS=['model','run','test_method','source_identifier','code_reference','q1_syntactically_valid','q1_executable','q1_approved','q1_failure_category','q1_contract_assessment','q4_positive','q4_negative','q4_boundary','q4_multi_endpoint','justification','evidence','confidence','ambiguity','model_evaluator','evaluator_configuration','evaluation_status','review_status']
def main():
    ap=argparse.ArgumentParser(); ap.add_argument('--input',type=Path,default=Path('results/ai-review-execution/ai-judgments.jsonl')); ap.add_argument('--output-dir',type=Path,default=Path('results/ai-review')); args=ap.parse_args()
    if not args.input.exists(): raise SystemExit(f'no persisted judgments: {args.input}')
    rows=[]
    for line in args.input.read_text(encoding='utf-8').splitlines():
        if not line.strip(): continue
        x=json.loads(line); j=x['judgment']; q1=j['q1']; q4=j['q4']
        rows.append({'model':x['model'],'run':x['run'],'test_method':x['test_method'],'source_identifier':x['source_identifier'],'code_reference':f"tests/{x['model']}/run-{x['run']}.java#{x['test_method']}",'q1_syntactically_valid':q1.get('syntactically_valid',''),'q1_executable':q1.get('executable',''),'q1_approved':q1.get('approved',''),'q1_failure_category':q1.get('failure_category',''),'q1_contract_assessment':q1.get('contract_assessment',''),'q4_positive':q4.get('positive',''),'q4_negative':q4.get('negative',''),'q4_boundary':q4.get('boundary',''),'q4_multi_endpoint':q4.get('multi_endpoint',''),'justification':j.get('justification',''),'evidence':json.dumps(j.get('evidence',[]),ensure_ascii=False),'confidence':j.get('confidence',''),'ambiguity':j.get('ambiguity',''),'model_evaluator':x.get('model_evaluator',''),'evaluator_configuration':json.dumps(x.get('evaluator_configuration',{}),ensure_ascii=False),'evaluation_status':x.get('evaluation_status','AI_REVIEWED'),'review_status':'PENDING_HUMAN_REVIEW'})
    args.output_dir.mkdir(parents=True,exist_ok=True)
    with (args.output_dir/'ai-judgments.csv').open('w',encoding='utf-8',newline='') as f: w=csv.DictWriter(f,fieldnames=FIELDS); w.writeheader(); w.writerows(rows)
    print(f'exported {len(rows)} AI judgments to {args.output_dir}/ai-judgments.csv')
if __name__=='__main__': main()
