#!/usr/bin/env python3
import argparse, csv, hashlib, json, os, subprocess, tempfile
from pathlib import Path
from ai_judge import ROOT, PROMPT, SOURCE, build_context, parse_judgment

SCHEMA=ROOT/'prompts/ai-judge.schema.json'
def rows(p):
    with p.open(encoding='utf-8',newline='') as f:return list(csv.DictReader(f))
def available_models():
    p=Path.home()/'.codex/models_cache.json'
    if not p.exists(): return []
    try:return [x.get('slug') for x in json.loads(p.read_text()).get('models',[])]
    except Exception:return []
def main():
    ap=argparse.ArgumentParser(); ap.add_argument('--model',required=True); ap.add_argument('--source',type=Path,default=SOURCE); ap.add_argument('--output-dir',type=Path,default=ROOT/'results/ai-review-codex'); ap.add_argument('--max-cases',type=int); ap.add_argument('--dry-run',action='store_true'); ap.add_argument('--execute',action='store_true'); args=ap.parse_args()
    if args.dry_run==args.execute: ap.error('choose exactly one of --dry-run or --execute')
    models=available_models()
    if args.model not in models: raise SystemExit('model is not present in the local Codex model catalog: '+args.model)
    data=rows(args.source); ids=[f"model={r['model']};run={r['run']};test_method={r['test_method']}" for r in data]
    if len(ids)!=len(set(ids)): raise SystemExit('duplicate case identifiers')
    if args.max_cases: data=data[:args.max_cases]
    if args.dry_run:
        tokens=sum(max(1,(len(build_context(r))+3)//4) for r in data)
        print(json.dumps({'mode':'dry-run','model':args.model,'cases':len(data),'estimated_input_tokens':tokens,'prompt_sha256':hashlib.sha256(PROMPT.read_bytes()).hexdigest()})); return
    out=args.output_dir; out.mkdir(parents=True,exist_ok=True); state=out/'ai-judgments.jsonl'; raw=out/'raw.jsonl'; errors=out/'errors.jsonl'; done={json.loads(x)['source_identifier'] for x in state.read_text().splitlines() if x.strip()} if state.exists() else set()
    for r in data:
        ident=f"model={r['model']};run={r['run']};test_method={r['test_method']}"
        if ident in done: continue
        with tempfile.TemporaryDirectory(prefix='codex-judge-') as td:
            result=Path(td)/'result.json'; prompt=Path(td)/'prompt.txt'; prompt.write_text(build_context(r),encoding='utf-8')
            cmd=['codex','exec','--ephemeral','--sandbox','read-only','--model',args.model,'--output-schema',str(SCHEMA),'-C',str(ROOT),'--output-last-message',str(result),'-']
            proc=subprocess.run(cmd,input=prompt.read_text(encoding='utf-8'),text=True,capture_output=True)
            if proc.returncode!=0:
                errors.open('a',encoding='utf-8').write(json.dumps({'source_identifier':ident,'returncode':proc.returncode,'stderr':proc.stderr[-4000:]},ensure_ascii=False)+'\n'); continue
            try: text=result.read_text(encoding='utf-8'); judgment=parse_judgment(text)
            except Exception as exc:
                errors.open('a',encoding='utf-8').write(json.dumps({'source_identifier':ident,'error':type(exc).__name__+': '+str(exc),'raw_response':result.read_text(encoding='utf-8')[:4000] if result.exists() else ''},ensure_ascii=False)+'\n'); continue
            raw.open('a',encoding='utf-8').write(json.dumps({'source_identifier':ident,'raw_response':text},ensure_ascii=False)+'\n')
            state.open('a',encoding='utf-8').write(json.dumps({'source_identifier':ident,'model':r['model'],'run':r['run'],'test_method':r['test_method'],'model_evaluator':args.model,'evaluator_configuration':{'interface':'codex exec','sandbox':'read-only','prompt_sha256':hashlib.sha256(PROMPT.read_bytes()).hexdigest()},'judgment':judgment,'evaluation_status':'AI_REVIEWED','review_status':'PENDING_HUMAN_REVIEW'},ensure_ascii=False)+'\n')
if __name__=='__main__':main()
