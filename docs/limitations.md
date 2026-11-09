# Limitações

O SUT original usa MySQL com placeholders no `application.yml` e não inclui OpenAPI versionado; a especificação do repositório é uma reconstrução estática até validação no endpoint runtime. A aplicação pode ter bugs e dados persistentes; reset e logs são necessários. Chat interfaces não garantem controle de temperatura, seed ou mudanças internas. A análise de validade, execução e causa de falhas exige revisão humana.
