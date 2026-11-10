#!/usr/bin/env python3
import argparse, hashlib, json, re
from pathlib import Path
ROOT = Path(__file__).resolve().parents[1]

def main():
    ap = argparse.ArgumentParser(); ap.add_argument('--model', required=True); ap.add_argument('--run', type=int, required=True); ap.add_argument('--response'); ap.add_argument('--output')
    a = ap.parse_args(); src = (Path(a.response).resolve() if a.response else ROOT / 'responses' / a.model / f'run-{a.run:02d}.txt')
    if not src.exists(): raise SystemExit(f'original response not found: {src}')
    raw = src.read_text(encoding='utf-8')
    code = raw
    fences = re.findall(r'```(?:java)?\s*\n?(.*?)```', raw, flags=re.S|re.I)
    if fences: code = fences[0]
    if not code.strip(): raise SystemExit('response contains no extractable text')
    out = Path(a.output) if a.output else ROOT / 'tests' / a.model / f'run-{a.run:02d}.java'; out.parent.mkdir(parents=True, exist_ok=True); out.write_text(code, encoding='utf-8')
    try: source_name = str(src.relative_to(ROOT))
    except ValueError: source_name = str(src)
    meta = {'model': a.model, 'run': a.run, 'source': source_name, 'response_sha256': hashlib.sha256(raw.encode()).hexdigest(), 'extracted_sha256': hashlib.sha256(code.encode()).hexdigest(), 'transformation': 'Removed only the first Markdown code fence delimiters if present; no code repair or import/assertion changes.'}
    (out.with_suffix('.metadata.json')).write_text(json.dumps(meta, indent=2) + '\n', encoding='utf-8')
    print(out)
if __name__ == '__main__': main()
