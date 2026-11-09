#!/usr/bin/env python3
import argparse, hashlib, json, re
from pathlib import Path
ROOT = Path(__file__).resolve().parents[1]

def main():
    ap = argparse.ArgumentParser(); ap.add_argument('--model', required=True); ap.add_argument('--run', type=int, required=True)
    a = ap.parse_args(); src = ROOT / 'responses' / a.model / f'run-{a.run:02d}.txt'
    if not src.exists(): raise SystemExit(f'original response not found: {src}')
    raw = src.read_text(encoding='utf-8')
    code = raw
    fences = re.findall(r'```(?:java)?\s*\n?(.*?)```', raw, flags=re.S|re.I)
    if fences: code = fences[0]
    if not code.strip(): raise SystemExit('response contains no extractable text')
    out = ROOT / 'tests' / a.model / f'run-{a.run:02d}.java'; out.write_text(code, encoding='utf-8')
    meta = {'model': a.model, 'run': a.run, 'source': str(src.relative_to(ROOT)), 'response_sha256': hashlib.sha256(raw.encode()).hexdigest(), 'extracted_sha256': hashlib.sha256(code.encode()).hexdigest(), 'transformation': 'Removed only the first Markdown code fence delimiters if present; no code repair or import/assertion changes.'}
    (out.with_suffix('.metadata.json')).write_text(json.dumps(meta, indent=2) + '\n', encoding='utf-8')
    print(out)
if __name__ == '__main__': main()
