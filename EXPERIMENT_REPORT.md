# Relatório do mini-experimento

## Contexto e objetivo

Este mini-experimento de iniciação científica examina testes para uma API REST gerados por modelos de linguagem. O objetivo é descrever validade e execução dos testes (Q1) e os tipos de cenário que propõem (Q4), sem inferir uma superioridade geral a partir desta única API.

## API e contrato

Foi selecionada `spring-rest-example`, disponível no Web Fuzzing Dataset, por ser uma API Spring REST em Java 17 com operações e persistência em MySQL, adequada para exercitar testes HTTP. A origem e o commit do dataset estão em [api/sut-metadata.json](api/sut-metadata.json). Como não havia OpenAPI versionado no dataset para esta aplicação, a especificação integral em [api/openapi.json](api/openapi.json) foi capturada em execução de `/v3/api-docs`. É o contrato exposto pelo runtime, não uma garantia de que cada comportamento do sistema coincida com ele.

## Modelos e prompt

O pesquisador identificou os modelos como Google Gemini 3.6 Think e OpenAI GPT-6, acessados manualmente por interfaces de chat em configurações de raciocínio. Os nomes dos diretórios são identificadores internos, não IDs oficiais de API. A escolha compara fornecedores diferentes sem presumir equivalência de capacidade ou esforço computacional; parâmetros internos das interfaces não estavam inteiramente sob controle do pesquisador.

O mesmo [prompt](prompts/generation.txt), contendo a especificação e as instruções de geração, foi congelado com SHA-256 `6b7d7e6c97703a79a10a9a7de600ab36c0d176669f9ec8835c3793afb4e857ed`. Foram usadas dez conversas independentes por modelo, preservando a primeira resposta e sem correções iterativas.

## Ambiente e fluxo

Java 17 corresponde à versão do sistema sob teste; JUnit 5 organiza os métodos de teste e REST Assured permite expressar requisições e asserções HTTP. Maven reúne dependências e executa as classes. Docker e MySQL viabilizam o banco usado pela aplicação e sua restauração entre conjuntos. Jackson foi necessário para serializar objetos em JSON nas requisições; Surefire executa os testes e registra relatórios XML para auditoria.

O fluxo foi: coletar respostas, extrair o Java sem reparação, reiniciar a API e restaurar o banco antes de cada conjunto, compilar e executar sequencialmente, preservar logs e relatórios e preparar os resumos. A extração recompôs trechos existentes de algumas respostas Gemini divididas por delimitadores Markdown, sem acrescentar código. A infraestrutura e as evidências permanecem na [branch técnica](https://github.com/SorensenG/llm-rest-api-test-evaluation/tree/archive/complete-experiment-2026-11-18).

## Por que houve segunda execução

Na primeira execução, faltava no classpath de teste o serializador Jackson necessário para certas requisições. A dependência foi adicionada à infraestrutura, sem corrigir testes gerados nem alterar o SUT, e os vinte conjuntos foram reexecutados. A primeira rodada foi preservada como histórico; os resultados abaixo usam a segunda. Diferenças entre rodadas refletem também a correção de infraestrutura e não devem ser atribuídas ao modelo.

## Q1: compilação, execução e aprovação

| Modelo | Métodos gerados | Conjuntos compilados | Executáveis observados | Aprovados | Aprovação entre executáveis |
|---|---:|---:|---:|---:|---:|
| Gemini 3.6 Think | 216 | 8/10 | 152 | 122 | 80,26% |
| GPT-6 | 601 | 10/10 | 601 | 178 | 29,62% |

Os conjuntos Gemini 05 e 06 não compilaram por caracteres de escape ilegais. Seus 44 métodos não têm resultado individual observado e não foram contados como aprovados ou reprovados por método. Entre os conjuntos que compilaram, há 172 métodos Gemini com validade sintática conhecida. Aprovação entre executáveis usa 122/152 e 178/601, respectivamente; a comparação deve considerar o volume diferente de testes e os dados ausentes. O [resumo Q1](results/q1-summary.csv) conserva os valores por repetição.

## Q4: tipos de cenário

Um cenário positivo verifica comportamento esperado com dados válidos; um negativo explora entrada inválida ou rejeição; um de borda usa valores em ou próximos de limites; e um multi-endpoint invoca pelo menos duas operações distintas, considerando método HTTP e caminho. As categorias podem se sobrepor.

As contagens da [tabela Q4](results/q4-preliminary.csv) são propostas automatizadas, não validação humana. Uma revisão manual de 20 testes será documentada separadamente no Notion. A classificação por palavras e caminhos literais pode deixar passar contexto ou interpretar intenção incorretamente.

## Limitações e próximos passos

O contrato documenta HTTP 200 para `POST /api/project`, enquanto uma requisição válida observada retornou HTTP 201. O protocolo mantém uma falha exclusivamente causada por essa diferença como falha de asserção, na categoria erro da API e subcategoria específica; não se corrige retroativamente o teste nem a API. Restrições não documentadas podem gerar divergências de comportamento que o OpenAPI, sozinho, não explica. Asserções permissivas também podem passar sem verificar suficientemente a resposta; aprovação no JUnit não prova capacidade de detectar defeitos.

As interfaces de chat não asseguram controle integral de parâmetros ou estabilidade das versões. Três grupos de respostas Gemini possuem hashes duplicados, reduzindo a diversidade efetiva observada. Falhas, classificações de cenário e causas ainda exigem revisão humana. O próximo passo é discutir os resultados com o orientador e registrar a revisão manual, distinguindo dados observados de interpretações provisórias.
