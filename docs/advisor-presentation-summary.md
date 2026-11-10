# Resumo executivo para apresentação ao orientador

## Objetivo e método

Avaliar a geração de testes Java para uma API REST por Gemini 3.6 Think e GPT-6, acessados por interfaces de chat, com o mesmo prompt e dez repetições independentes por modelo. O protocolo usou Java 17, JUnit 5, REST Assured, Maven, reinicialização do SUT e restauração do banco antes de cada conjunto. Respostas, fontes Java, logs e relatórios Surefire foram preservados. A análise usa `results/official-run-2/`; a primeira execução é histórica.

## Resultados observados

- Gemini: 8/10 conjuntos compilados; 172 métodos sintaticamente válidos conhecidos, 152 executáveis e 122 aprovados. Aprovação entre executáveis: 80,26%.
- GPT-6: 10/10 conjuntos compilados; 601 métodos sintaticamente válidos e executáveis, 178 aprovados. Aprovação entre executáveis: 29,62%.
- Q4 é proposta estática: Gemini 15 positivos, 192 negativos, 116 de borda e 43 multi-endpoint; GPT-6 48, 275, 223 e 81. As categorias podem se sobrepor.
- Gemini teve três grupos de respostas com hash duplicado; GPT-6 não teve duplicações.
- Gemini 05/06 deixaram 44 métodos sem resultado individual determinável por falha antes do Surefire.

## Interpretação e limitações

Os dados mostram diferenças observadas de volume, compilabilidade, execução e aprovação, mas não sustentam uma afirmação de superioridade geral. A comparação é afetada pelo número desigual de métodos, pela perda de observação por compilação no Gemini e pela pendência de revisão humana de Q4. A divergência HTTP 200 documentado/201 observado em `POST /api/project` foi isolada em 10 falhas compatíveis, sem corrigir API ou testes.

## Próximos passos

Revisar as 817 linhas de `results/final-analysis/human-review.csv`, começando pelos casos HIGH; confirmar Q4 e categorias de falha; então atualizar as métricas confirmadas e discutir as hipóteses com o orientador.
