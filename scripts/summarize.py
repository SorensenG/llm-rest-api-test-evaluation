#!/usr/bin/env python3
import csv, statistics
from pathlib import Path
ROOT=Path(__file__).resolve().parents[1]
def num(v):
    try: return float(v)
    except (TypeError,ValueError): return None
def main():
    src=ROOT/'results/summary.csv'; rows=list(csv.DictReader(src.open(encoding='utf-8')))
    fields=['total_tests','syntactically_valid_count','syntactically_valid_pct','executable_count','executable_pct','approved_count','approved_pct','positive_tests','negative_tests','boundary_tests','multi_endpoint_tests','assertion_errors','out_of_spec_requests','api_errors','other_errors']
    with (ROOT/'results/medians.csv').open('w',newline='',encoding='utf-8') as f:
        w=csv.writer(f); w.writerow(['model','metric','median','n_observations'])
        for model in ('gemini-3.6-flash','gpt-5.6-luna'):
            subset=[r for r in rows if r.get('model')==model]
            for field in fields:
                vals=[num(r.get(field)) for r in subset]; vals=[v for v in vals if v is not None]
                if vals: w.writerow([model,field,statistics.median(vals),len(vals)])
if __name__=='__main__': main()
