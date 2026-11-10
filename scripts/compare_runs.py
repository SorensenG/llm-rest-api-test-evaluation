#!/usr/bin/env python3
import argparse, csv
from pathlib import Path

FIELDS = ('total_tests', 'syntactically_valid_count', 'executable_count', 'approved_count', 'assertion_errors', 'api_errors', 'other_errors')

def read(path):
    with path.open(encoding='utf-8', newline='') as handle:
        return {(r['model'], r['run']): r for r in csv.DictReader(handle)}

def main():
    ap = argparse.ArgumentParser(); ap.add_argument('--first', required=True); ap.add_argument('--second', required=True); ap.add_argument('--output', required=True); a = ap.parse_args()
    first, second = read(Path(a.first)), read(Path(a.second))
    rows = []
    for key in sorted(second):
        model, run = key; old, new = first[key], second[key]
        for field in FIELDS:
            old_value, new_value = old.get(field, ''), new.get(field, '')
            delta = ''
            if old_value != '' and new_value != '': delta = str(float(new_value) - float(old_value))
            rows.append({'model': model, 'run': run, 'metric': field, 'first_run': old_value, 'second_run': new_value, 'delta': delta})
    with Path(a.output).open('w', encoding='utf-8', newline='') as handle:
        writer = csv.DictWriter(handle, fieldnames=['model', 'run', 'metric', 'first_run', 'second_run', 'delta']); writer.writeheader(); writer.writerows(rows)

if __name__ == '__main__': main()
