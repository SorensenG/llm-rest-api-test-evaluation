#!/usr/bin/env python3
import csv
from pathlib import Path

ROOT = Path(__file__).resolve().parents[1]
SRC = ROOT / 'results' / 'final-analysis' / 'validated-tests.csv'
OUT = ROOT / 'results' / 'final-analysis' / 'human-review.csv'

def priority(row):
    if row['compile_status'] == 'failed' or row['failure_cause'] == 'compile_failure_unknown_per_method': return 'HIGH'
    if row['failure_cause'] in ('api_error_contract_status_200_201', 'execution_error', 'assertion_error'): return 'HIGH'
    flags = [row['scenario_positive'], row['scenario_negative'], row['scenario_boundary'], row['scenario_multi_endpoint']]
    return 'MEDIUM' if sum(x == 'TRUE' for x in flags) == 0 else 'LOW'

def main():
    with SRC.open(encoding='utf-8', newline='') as f: rows = list(csv.DictReader(f))
    fields = ['priority','model','run','test_method','code_reference','compile_status','q1_syntactically_valid_observed','q1_executable_observed','q1_approved_observed','q1_failure_cause','q4_positive_proposed','q4_negative_proposed','q4_boundary_proposed','q4_multi_endpoint_proposed','operations','evidence','human_q4_positive','human_q4_negative','human_q4_boundary','human_q4_multi_endpoint','human_failure_category','human_decision','reviewer','review_date','review_notes']
    output = []
    for r in rows:
        output.append({'priority':priority(r),'model':r['model'],'run':r['run'],'test_method':r['test_method'],'code_reference':f"tests/{r['model']}/run-{r['run']}.java#{r['test_method']}",'compile_status':r['compile_status'],'q1_syntactically_valid_observed':r['syntactically_valid'],'q1_executable_observed':r['executable'],'q1_approved_observed':r['approved'],'q1_failure_cause':r['failure_cause'],'q4_positive_proposed':r['scenario_positive'],'q4_negative_proposed':r['scenario_negative'],'q4_boundary_proposed':r['scenario_boundary'],'q4_multi_endpoint_proposed':r['scenario_multi_endpoint'],'operations':r['operations'],'evidence':r['evidence'],'human_q4_positive':'','human_q4_negative':'','human_q4_boundary':'','human_q4_multi_endpoint':'','human_failure_category':'','human_decision':'','reviewer':'','review_date':'','review_notes':''})
    with OUT.open('w', encoding='utf-8', newline='') as f:
        w = csv.DictWriter(f, fieldnames=fields); w.writeheader(); w.writerows(output)
    print(f'wrote {len(output)} review rows to {OUT}')

if __name__ == '__main__': main()
