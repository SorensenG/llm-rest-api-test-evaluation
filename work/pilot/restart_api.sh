#!/bin/sh
set -eu
JAVA_HOME=/Library/Java/JavaVirtualMachines/temurin-17.jdk/Contents/Home
export JAVA_HOME
export PATH="$JAVA_HOME/bin:$PATH"
SUT=/Users/sorenseng/Documents/ic/API/Dataset/jdk_17_maven/cs/rest/spring-rest-example
old_pid="$(lsof -ti tcp:8080 2>/dev/null || true)"
if [ -n "$old_pid" ]; then kill "$old_pid"; sleep 2; fi
nohup java -jar "$SUT/target/spring-rest-example-sut.jar" --server.port=8080 --spring.datasource.url='jdbc:mysql://127.0.0.1:3306/project_api?useSSL=false&allowPublicKeyRetrieval=true' --spring.datasource.username=project --spring.datasource.password=project --spring.jpa.hibernate.ddl-auto=update >work/pilot/api.log 2>&1 </dev/null &
