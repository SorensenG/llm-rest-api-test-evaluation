#!/bin/sh
set -eu
JAVA_HOME=/Library/Java/JavaVirtualMachines/temurin-17.jdk/Contents/Home
export JAVA_HOME
SUT=/Users/sorenseng/Documents/ic/API/Dataset/jdk_17_maven/cs/rest/spring-rest-example
exec "$JAVA_HOME/bin/java" -jar "$SUT/target/spring-rest-example-sut.jar" \
  --server.port=8080 \
  --spring.datasource.url='jdbc:mysql://127.0.0.1:3306/project_api?useSSL=false&allowPublicKeyRetrieval=true' \
  --spring.datasource.username=project \
  --spring.datasource.password=project \
  --spring.jpa.hibernate.ddl-auto=update
