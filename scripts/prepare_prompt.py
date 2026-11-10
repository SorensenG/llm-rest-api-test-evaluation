#!/usr/bin/env python3
import argparse, hashlib, json
from pathlib import Path

ROOT = Path(__file__).resolve().parents[1]

def main():
    ap = argparse.ArgumentParser()
    ap.add_argument('--verify', action='store_true')
    args = ap.parse_args()
    spec = (ROOT / 'api/openapi.json').read_text(encoding='utf-8')
    template = (ROOT / 'prompts/generation.template.txt').read_text(encoding='utf-8')
    prompt = template.replace('${OPENAPI_SPEC}', spec)
    out = ROOT / 'prompts/generation.txt'
    digest = hashlib.sha256(prompt.encode()).hexdigest()
    if args.verify and out.exists() and out.read_text(encoding='utf-8') != prompt:
        raise SystemExit('generation.txt differs from the frozen template/specification')
    out.write_text(prompt, encoding='utf-8')
    (ROOT / 'prompts/generation.sha256').write_text(digest + '  generation.txt\n', encoding='utf-8')
    print(json.dumps({'prompt': str(out), 'sha256': digest}, indent=2))

if __name__ == '__main__': main()
