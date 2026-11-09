#!/usr/bin/env python3
import argparse, json, os, shutil, subprocess, sys, time
from pathlib import Path
ROOT = Path(__file__).resolve().parents[1]

def main():
    ap = argparse.ArgumentParser(); ap.add_argument('--model', required=True); ap.add_argument('--run', type=int, required=True); a = ap.parse_args()
    source = ROOT / 'tests' / a.model / f'run-{a.run:02d}.java'
    if not source.exists(): raise SystemExit(f'extracted Java not found: {source}')
    subprocess.run([sys.executable, str(ROOT/'scripts/reset_api.py')], check=True)
    base = os.environ.get('API_BASE_URL', 'http://localhost:8080')
    deadline = time.time() + int(os.environ.get('API_WAIT_SECONDS', '60'))
    while time.time() < deadline:
        try:
            import urllib.request; urllib.request.urlopen(base + '/v3/api-docs', timeout=2); break
        except Exception: time.sleep(2)
    else: raise SystemExit(f'API did not become available at {base}/v3/api-docs')
    gen = ROOT / '.generated-test-src'; gen.mkdir(exist_ok=True)
    for p in gen.glob('*.java'): p.unlink()
    shutil.copy2(source, gen / 'GeneratedApiTest.java')
    env = os.environ.copy(); env.update({'API_BASE_URL': base, 'model': a.model, 'run': f'{a.run:02d}'})
    logdir = ROOT / 'results/execution-logs' / a.model / f'run-{a.run:02d}'; logdir.mkdir(parents=True, exist_ok=True)
    result = subprocess.run(['mvn', '-q', 'test', '-Dmodel='+a.model, '-Drun='+str(a.run)], cwd=ROOT, env=env, text=True, capture_output=True)
    (logdir/'stdout.txt').write_text(result.stdout, encoding='utf-8'); (logdir/'stderr.txt').write_text(result.stderr, encoding='utf-8')
    (logdir/'exit-code.txt').write_text(str(result.returncode)+'\n', encoding='utf-8')
    print(json.dumps({'model': a.model, 'run': a.run, 'exit_code': result.returncode, 'status': 'passed' if result.returncode == 0 else 'failed'}))
    sys.exit(result.returncode)
if __name__ == '__main__': main()
