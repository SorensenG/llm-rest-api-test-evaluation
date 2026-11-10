# Protocolo experimental

Uma resposta inicial a um prompt único é uma unidade experimental. Cada modelo terá dez conversas novas, sem histórico, usando exatamente o mesmo `prompts/generation.txt`; somente a primeira resposta será preservada e nenhuma correção/refinamento será solicitado.

O prompt está congelado com SHA-256 `6b7d7e6c97703a79a10a9a7de600ab36c0d176669f9ec8835c3793afb4e857ed`. Antes de cada geração, executar `python3 scripts/verify_prompt.py`; o comando é somente de leitura e falha se o conteúdo ou o hash esperado mudar.

Cada método Java anotado com `@Test` é uma unidade de teste. Métodos auxiliares/configuração não contam. Testes parametrizados e dinâmicos são proibidos no prompt. Um método pode executar várias requisições.

## Q1 — validade e execução

Para cada conjunto, registrar total gerado, sintaticamente válido, executável até o fim e aprovado. Cada porcentagem usa total gerado como denominador; zero gera porcentagem indefinida, não zero. Separar erro sintático, erro semântico de compilação, dependência, falha durante execução e falha de asserção. Falha de asserção é executável, mas não aprovado.

As causas de não aprovação são erro de asserção, requisição fora da especificação, erro da API ou outro. A classificação exige evidência no log, relatório Surefire e OpenAPI; HTTP 500 não é automaticamente erro da API e entrada negativa intencional não é automaticamente fora da especificação.

### Divergência HTTP 200/201 do SUT

Para `POST /api/project`, o OpenAPI runtime documenta resposta `200`, enquanto uma requisição válida observada durante a validação retornou `201 Created`. Essa divergência é parte do comportamento observado e não deve ser corrigida no SUT, no OpenAPI ou nos testes gerados. Se uma requisição válida falhar exclusivamente porque o teste espera 200 e recebe 201 (ou vice-versa), classificar como falha de asserção causada por inconsistência entre SUT e contrato OpenAPI. Em Q1, contabilizar na categoria `erro da API`, com a subcategoria `contrato-openapi-status-http-200-201`.

## Q4 — cenários

Positivo verifica comportamento esperado com dados válidos; negativo verifica entrada inválida/rejeição; borda usa valores próximos ou nos limites especificados; multi-endpoint invoca pelo menos duas operações REST distintas no mesmo método. As classificações podem se sobrepor. Dados e justificativas ficam em `individual-tests.csv` para auditoria humana.

O teste de infraestrutura manual é separado e nunca entra nos dados científicos. Dados ausentes permanecem pendentes.
