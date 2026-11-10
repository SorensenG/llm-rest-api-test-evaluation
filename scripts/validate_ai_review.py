#!/usr/bin/env python3
import csv, hashlib
from pathlib import Path

ROOT=Path(__file__).resolve().parents[1]
SRC=ROOT/'results/final-analysis/human-review.csv'; OUT=ROOT/'results/ai-review'
def read(p):
    with p.open(encoding='utf-8',newline='') as f:return list(csv.DictReader(f))
def main():
    source=read(SRC); judgments=read(OUT/'ai-judgments.csv'); uncertain=read(OUT/'uncertain-cases.csv'); sample=read(OUT/'manual-sample-40.csv')
    ids={(r['model'],r['run'],r['test_method']) for r in source}
    assert len(source)==817 and len(ids)==817
    assert len(judgments)==len(uncertain)==817
    assert { (r['model'],r['run'],r['test_method']) for r in judgments }==ids
    assert all(r['evaluation_status']=='NOT_EVALUATED' for r in judgments)
    assert all(r['review_status']=='PENDING_HUMAN_REVIEW' for r in judgments+uncertain)
    assert 30 <= len(sample) <= 50
    print('AI review validation: PASS')
    print('source=817 judgments=817 uncertain=817 evaluated=0 manual_sample='+str(len(sample)))
    print('human-review sha256='+hashlib.sha256(SRC.read_bytes()).hexdigest())
if __name__=='__main__':main()
