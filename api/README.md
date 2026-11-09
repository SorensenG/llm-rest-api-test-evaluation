# Sistema sob teste

Esta pasta contém a especificação usada pelo prompt e metadados de proveniência. O código do SUT não é copiado nem modificado: ele é mantido externamente em `work/WebFuzzing-Dataset`.

O `openapi.json` foi derivado dos controllers, entidades e validações do commit do dataset indicado em `sut-metadata.json`. O dataset não versiona uma especificação em `openapi-swagger/`; a especificação runtime deverá ser comparada com `/v3/api-docs` quando o SUT for iniciado com MySQL.
