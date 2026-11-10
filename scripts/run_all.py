#!/usr/bin/env python3
import argparse, os, subprocess, sys
from pathlib import Path

ROOT = Path(__file__).resolve().parents[1]
INFRASTRUCTURE_FAILURE = 20

def main():
    ap = argparse.ArgumentParser(description='Extract and execute all 20 official response sets sequentially.')
    ap.add_argument('--api-restart-command', required=True, help='Command that restarts the external SUT after each database reset.')
    a = ap.parse_args()
    official = [(model, run) for model in ('gemini-3.6-flash', 'gpt-5.6-luna') for run in range(1, 11)]
    missing = []
    for model, run in official:
        response = ROOT / 'responses' / model / f'run-{run:02d}.txt'
        if not response.exists() or response.stat().st_size == 0: missing.append(str(response.relative_to(ROOT)))
    if missing:
        print('COLLECTION_PRECONDITION_FAILURE: missing or empty original responses:', file=sys.stderr)
        print('\n'.join(missing), file=sys.stderr)
        return INFRASTRUCTURE_FAILURE
    for model, run in official:
        extract = subprocess.run([sys.executable, str(ROOT/'scripts/extract_java.py'), '--model', model, '--run', str(run)])
        if extract.returncode:
            print(f'COLLECTION_PRECONDITION_FAILURE: extraction failed for {model}/run-{run:02d}', file=sys.stderr)
            return INFRASTRUCTURE_FAILURE
    experimental_failures = []
    for model, run in official:
        result = subprocess.run([sys.executable, str(ROOT/'scripts/run_one.py'), '--model', model, '--run', str(run)], env={**os.environ, 'API_RESTART_COMMAND': a.api_restart_command})
        if result.returncode == 10:
            experimental_failures.append(f'{model}/run-{run:02d}')
            continue
        if result.returncode != 0:
            print(f'INFRASTRUCTURE_FAILURE: stopping collection at {model}/run-{run:02d} (exit={result.returncode})', file=sys.stderr)
            return INFRASTRUCTURE_FAILURE
    print('BATCH_COMPLETE: all 20 sets were attempted.')
    if experimental_failures: print('EXPERIMENTAL_FAILURES: ' + ', '.join(experimental_failures))
    return 0

if __name__ == '__main__': sys.exit(main())
