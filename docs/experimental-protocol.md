# Protocolo experimental

Uma resposta inicial a um prompt único é uma unidade experimental. Cada modelo terá dez conversas novas, sem histórico, usando exatamente o mesmo `prompts/generation.txt`; somente a primeira resposta será preservada e nenhuma correção/refinamento será solicitado.

Cada método Java anotado com `@Test` é uma unidade de teste. Métodos auxiliares/configuração não contam. Testes parametrizados e dinâmicos são proibidos no prompt. Um método pode executar várias requisições.

## Q1 — validade e execução

Para cada conjunto, registrar total gerado, sintaticamente válido, executável até o fim e aprovado. Cada porcentagem usa total gerado como denominador; zero gera porcentagem indefinida, não zero. Separar erro sintático, erro semântico de compilação, dependência, falha durante execução e falha de asserção. Falha de asserção é executável, mas não aprovado.

As causas de não aprovação são erro de asserção, requisição fora da especificação, erro da API ou outro. A classificação exige evidência no log, relatório Surefire e OpenAPI; HTTP 500 não é automaticamente erro da API e entrada negativa intencional não é automaticamente fora da especificação.

## Q4 — cenários

Positivo verifica comportamento esperado com dados válidos; negativo verifica entrada inválida/rejeição; borda usa valores próximos ou nos limites especificados; multi-endpoint invoca pelo menos duas operações REST distintas no mesmo método. As classificações podem se sobrepor. Dados e justificativas ficam em `individual-tests.csv` para auditoria humana.

O teste de infraestrutura manual é separado e nunca entra nos dados científicos. Dados ausentes permanecem pendentes.
