# Migração das respostas da coleta

Os arquivos foram encontrados no clone local `/Users/sorenseng/Documents/ic/llm-rest-api-test-evaluation` com os nomes históricos `gemini-3.6-flash` e `gpt-5.6-luna`. O pesquisador responsável declarou que a coleta foi realizada com Google Gemini 3.6 Think (`gemini-3.6-think`) e OpenAI GPT-6 (`gpt-6`). Essa identificação é uma declaração do pesquisador e não foi inferida automaticamente do conteúdo dos arquivos.

Os arquivos foram copiados byte a byte para os diretórios atuais, mantendo os números das repetições. Os diretórios históricos foram preservados. A integridade foi verificada comparando cada cópia com sua origem por `cmp` e SHA-256.

Há dez arquivos disponíveis para cada modelo atual, `run-01.txt` a `run-10.txt`. A confirmação local do prompt congelado passou, com SHA-256 `6b7d7e6c97703a79a10a9a7de600ab36c0d176669f9ec8835c3793afb4e857ed`. Não existem metadados suficientes nos arquivos para comprovar que cada resposta foi gerada com esse prompt; essa confirmação permanece pendente de declaração ou evidência adicional do pesquisador.

Nenhum teste ou conjunto oficial foi executado durante a migração.
