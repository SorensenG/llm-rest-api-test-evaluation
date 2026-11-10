#!/usr/bin/env python3
import os, subprocess, sys, time, urllib.request
from pathlib import Path
ROOT = Path(__file__).resolve().parents[1]
compose = ROOT / 'infrastructure/docker-compose.yml'
class InfrastructureError(RuntimeError): pass

def fail(message):
    print(f'INFRASTRUCTURE_FAILURE: {message}', file=sys.stderr)
    return 20

def main():
    restart = os.environ.get('API_RESTART_COMMAND')
    if not restart:
        return fail('API_RESTART_COMMAND is mandatory; refusing to continue without restarting the SUT')
    base = os.environ.get('API_BASE_URL', 'http://localhost:8080')
    cmd = ['docker', 'compose', '-f', str(compose), 'up', '-d', 'mysql']
    if subprocess.run(cmd).returncode: return fail('Docker/MySQL could not be started')
    container = subprocess.run(['docker', 'compose', '-f', str(compose), 'ps', '-q', 'mysql'], text=True, capture_output=True).stdout.strip()
    if not container: return fail('MySQL container ID could not be resolved')
    for _ in range(30):
        health = subprocess.run(['docker', 'inspect', '-f', '{{.State.Health.Status}}', container], text=True, capture_output=True).stdout.strip()
        if health == 'healthy': break
        time.sleep(2)
    else: return fail('MySQL health check did not become healthy')
    reset_sql = 'DROP DATABASE IF EXISTS project_api; CREATE DATABASE project_api;'
    if subprocess.run(['docker', 'exec', container, 'mysql', '-uroot', '-proot', '-e', reset_sql]).returncode:
        return fail('database reset failed')
    print('Executing API_RESTART_COMMAND to restart the external SUT.')
    if subprocess.run(restart, shell=True).returncode: return fail('SUT restart command failed')
    for _ in range(60):
        try:
            with urllib.request.urlopen(base + '/v3/api-docs', timeout=2) as response:
                if response.status == 200: break
        except Exception: pass
        time.sleep(1)
    else: return fail(f'SUT health check failed at {base}/v3/api-docs')
    for _ in range(30):
        result = subprocess.run(['docker', 'exec', container, 'mysql', '-uroot', '-proot', '-Nse', 'SHOW TABLES FROM project_api'], text=True, capture_output=True)
        tables = set(result.stdout.split())
        if {'project', 'problem', 'subproblem'}.issubset(tables): break
        time.sleep(1)
    else: return fail(f'database was not restored; tables={sorted(tables)}')
    print('RESET_OK: MySQL restored, SUT restarted, API healthy, Hibernate tables present.')
    return 0

if __name__ == '__main__': sys.exit(main())
