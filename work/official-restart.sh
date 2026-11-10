#!/bin/sh
set -eu
JAVA_HOME=/Library/Java/JavaVirtualMachines/temurin-17.jdk/Contents/Home
export JAVA_HOME
export PATH="$JAVA_HOME/bin:$PATH"
SUT=/Users/sorenseng/Documents/ic/API/Dataset/jdk_17_maven/cs/rest/spring-rest-example
LOG_DIR=/Users/sorenseng/Documents/Codex/2026-11-09/files-pasted-by-the-user-implementa/results/sut-logs
mkdir -p "$LOG_DIR"
old_pid="$(lsof -ti tcp:8080 2>/dev/null || true)"
if [ -n "$old_pid" ]; then kill $old_pid; sleep 2; fi
osascript -e 'tell application "Terminal" to do script "cd /Users/sorenseng/Documents/Codex/2026-11-09/files-pasted-by-the-user-implementa; sh work/launch-sut-terminal.sh > /Users/sorenseng/Documents/Codex/2026-11-09/files-pasted-by-the-user-implementa/results/sut-logs/api.log 2>&1"' >/dev/null
