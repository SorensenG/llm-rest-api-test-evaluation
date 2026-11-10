# Métricas

`summary.csv` tem uma linha por conjunto. Percentuais são `contagem / total_tests * 100`; se total for zero, ficam vazios. Medianas são calculadas por modelo sobre as dez repetições disponíveis, sem converter ausentes em zero. `individual-tests.csv` é a fonte de auditoria e deve ser revisado antes de resumir.

Para a divergência de `POST /api/project`, uma falha exclusiva da expectativa 200 versus resposta observada 201 é executável, não aprovada e classificada como falha de asserção. Em Q1, entra em `api_errors`, com a subcategoria `contrato-openapi-status-http-200-201`; o status e os logs originais devem ser preservados. Não alterar a asserção nem normalizar o código HTTP.
