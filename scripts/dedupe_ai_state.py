#!/usr/bin/env python3
import argparse, json
from pathlib import Path
def main():
    ap=argparse.ArgumentParser(); ap.add_argument('path',type=Path); args=ap.parse_args(); seen=set(); keep=[]
    for line in args.path.read_text(encoding='utf-8').splitlines():
        if not line.strip(): continue
        row=json.loads(line); ident=row['source_identifier']
        if ident not in seen: seen.add(ident); keep.append(line)
    args.path.write_text('\n'.join(keep)+'\n',encoding='utf-8'); print(f'kept {len(keep)} unique records')
if __name__=='__main__':main()
