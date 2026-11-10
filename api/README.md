# Sistema sob teste

Esta pasta contém a especificação usada pelo prompt e metadados de proveniência. O código do SUT não é copiado nem modificado: ele é mantido externamente em `work/WebFuzzing-Dataset`.

O `openapi.json` é a captura integral de `http://localhost:8080/v3/api-docs` com o commit indicado em `sut-metadata.json`. O dataset não versiona uma especificação em `openapi-swagger/`; a captura runtime foi feita depois de iniciar o SUT com MySQL.
