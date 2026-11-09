# Validação inicial

Resultados registrados no momento da preparação:

- `pom.xml`: inspeção estática concluída; execução Maven bloqueada porque Maven não está instalado localmente.
- Java: bloqueado para o requisito, pois o host expõe Java 26, não Java 17.
- Docker/Compose: cliente e arquivo Compose disponíveis; `docker compose config` passou, mas a execução foi bloqueada porque o daemon não estava rodando (`Cannot connect to the Docker daemon ... docker.sock`). MySQL e SUT não foram iniciados.
- OpenAPI: JSON estruturado e validado por parsing JSON; comparação dinâmica com `/v3/api-docs` pendente.
- Prompt: `prepare_prompt.py` gera o prompt completo e seu SHA-256.
- Scripts Python: compilação sintática com `python3 -m py_compile scripts/*.py`.
- Gerações oficiais: não executadas.
- Teste manual de infraestrutura e relatórios Surefire: pendentes até Java 17/Maven e SUT configurado.
