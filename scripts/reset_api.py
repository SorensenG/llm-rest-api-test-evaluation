#!/usr/bin/env python3
import os, subprocess, sys
from pathlib import Path
ROOT = Path(__file__).resolve().parents[1]
compose = ROOT / 'infrastructure/docker-compose.yml'
cmd = ['docker', 'compose', '-f', str(compose), 'up', '-d', 'mysql']
if subprocess.run(cmd).returncode: sys.exit(1)
if os.environ.get('RESET_COMPOSE_VOLUMES') == '1':
    print('RESET_COMPOSE_VOLUMES=1 was requested; recreating MySQL volume.')
    if subprocess.run(['docker', 'compose', '-f', str(compose), 'down', '-v']).returncode: sys.exit(1)
    if subprocess.run(cmd).returncode: sys.exit(1)
print('MySQL dependency is running. Restart the SUT if schema recreation is required; no SUT files were modified.')
