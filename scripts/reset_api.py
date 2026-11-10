#!/usr/bin/env python3
import os, subprocess, sys
from pathlib import Path
ROOT = Path(__file__).resolve().parents[1]
compose = ROOT / 'infrastructure/docker-compose.yml'
cmd = ['docker', 'compose', '-f', str(compose), 'up', '-d', 'mysql']
if subprocess.run(cmd).returncode: sys.exit(1)
reset_sql = 'DROP DATABASE IF EXISTS project_api; CREATE DATABASE project_api;'
if subprocess.run(['docker', 'exec', 'infrastructure-mysql-1', 'mysql', '-uroot', '-proot', '-e', reset_sql]).returncode:
    sys.exit(1)
restart = os.environ.get('API_RESTART_COMMAND')
if restart:
    print('Executing API_RESTART_COMMAND to restart the external SUT.')
    if subprocess.run(restart, shell=True).returncode: sys.exit(1)
print('MySQL database recreated. The external SUT may need a restart for Hibernate schema recreation; no SUT files were modified.')
