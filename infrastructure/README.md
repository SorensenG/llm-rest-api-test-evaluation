# Infraestrutura

O Compose fornece somente o MySQL, sem substituir ou modificar o SUT. A API original é executada a partir do clone externo do dataset com Java 17/Maven e propriedades de conexão documentadas em `docs/execution-guide.md`. O volume pode ser removido apenas quando o pesquisador quiser um reset total; o script de reset usa `docker compose down -v` somente se solicitado via `RESET_COMPOSE_VOLUMES=1`.
