#!/usr/bin/env python3
import argparse, hashlib
from pathlib import Path

ROOT = Path(__file__).resolve().parents[1]

def main():
    ap = argparse.ArgumentParser(description='Verify the frozen experimental prompt without modifying it.')
    ap.add_argument('--expected', default='6b7d7e6c97703a79a10a9a7de600ab36c0d176669f9ec8835c3793afb4e857ed')
    args = ap.parse_args()
    prompt = ROOT / 'prompts/generation.txt'
    digest = hashlib.sha256(prompt.read_bytes()).hexdigest()
    required = ['http://localhost:8080', 'Java 17', 'JUnit 5', 'REST Assured', 'Complete OpenAPI specification', 'public Java class named `GeneratedApiTest`', 'Do not use parameterized tests or dynamic tests', 'Do not execute, debug, repair']
    missing = [item for item in required if item not in prompt.read_text(encoding='utf-8')]
    if digest != args.expected or missing:
        raise SystemExit(f'prompt verification failed: sha256={digest}; missing={missing}')
    print(f'prompt frozen: sha256={digest}; bytes={prompt.stat().st_size}')

if __name__ == '__main__': main()
