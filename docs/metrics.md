# Métricas

`summary.csv` tem uma linha por conjunto. Percentuais são `contagem / total_tests * 100`; se total for zero, ficam vazios. Medianas são calculadas por modelo sobre as dez repetições disponíveis, sem converter ausentes em zero. `individual-tests.csv` é a fonte de auditoria e deve ser revisado antes de resumir.
