# Auditoria e segunda execução

A primeira execução oficial corresponde ao commit `b3dbfa1575b48694eb025a1b8777983b925ec116` e foi preservada em `results/official-run-1/`. A segunda execução está em `results/official-run-2/`; os diretórios não são misturados.

## Correções de infraestrutura

Foi adicionada a dependência de teste `jackson-databind` 2.15.3 ao `pom.xml`. Ela fornece o serializador JSON ausente no primeiro lote e não altera fontes Java gerados, asserções ou requisições. Um fixture separado que envia `Map<String, String>` via REST Assured passou após a alteração.

O executor passou a aceitar `--results-dir`, remove `target/` e relatórios somente da rodada corrente, e mantém os resultados anteriores. A extração continua determinística: remove delimitadores Markdown e recompõe apenas prefixo, corpo e sufixo que já estavam presentes na resposta Gemini; não adiciona código.

## Validação metodológica

Foram executados 20 conjuntos na segunda execução. As rodadas Gemini 05 e 06 continuam sem compilação porque o Java original contém caracteres de escape ilegais. Como a compilação da classe inteira falhou, validade sintática, execução e aprovação desses métodos ficam desconhecidas nos CSVs; não são convertidas em zero nem recebem linhas artificiais em `individual-tests.csv`.

O classificador de multi-endpoint agora exige pelo menos duas operações HTTP distintas no mesmo método. As classificações continuam propostas automatizadas e marcadas para revisão humana. A subcategoria `contrato-openapi-status-http-200-201` só é atribuída quando há evidência simultânea de `POST /api/project`, requisição considerada válida e expectativa/observação 200/201 no relatório; não é aplicada por mera coincidência numérica.

## Comparação

`results/official-comparison.csv` compara os valores por modelo e repetição. A leitura correta é uma comparação de infraestrutura entre execuções, não uma melhoria atribuível aos modelos.

Na segunda execução, o total gerado permaneceu 216 para Gemini e 601 para GPT-6. O Gemini passou de 108 para 152 métodos executáveis e de 84 para 122 aprovados; as duas falhas de compilação permaneceram. O GPT-6 passou de 433 para 601 métodos executáveis e de 161 para 178 aprovados. A alteração é compatível com a remoção da falha de serialização JSON do classpath.

As respostas, o prompt, o OpenAPI, o SUT e o protocolo foram preservados. As métricas e causas ainda requerem revisão humana antes de qualquer conclusão científica.
