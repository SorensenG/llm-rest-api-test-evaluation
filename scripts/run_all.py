#!/usr/bin/env python3
import argparse, subprocess, sys
from pathlib import Path
ROOT = Path(__file__).resolve().parents[1]
def main():
    ap=argparse.ArgumentParser(); ap.add_argument('--allow-missing', action='store_true'); a=ap.parse_args()
    failures=[]
    for model in ('gemini-3.6-flash','gpt-5.6-luna'):
        for run in range(1,11):
            if not (ROOT/'tests'/model/f'run-{run:02d}.java').exists():
                if a.allow_missing: continue
                raise SystemExit(f'missing extracted test: {model} run {run:02d}')
            r=subprocess.run([sys.executable,str(ROOT/'scripts/run_one.py'),'--model',model,'--run',str(run)])
            if r.returncode: failures.append(f'{model}/run-{run:02d}')
    if failures: print('failed: '+', '.join(failures)); sys.exit(1)
if __name__ == '__main__': main()
