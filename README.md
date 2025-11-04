# FIAP - Tech Challenge (Fase 1)

## Descrição do Projeto
Um grupo de restaurantes decidiu contratar estudantes para construir um sistema de gestão para seus estabelecimentos. Essa decisão foi motivada pelo alto custo de sistemas individuais, o que levou os restaurantes a se unirem para desenvolver um sistema único e compartilhado.

## Objetivo do projeto
Desenvolver um backend robusto utilizando Spring Boot para gerenciar usuários e atender aos requisitos definidos.

## Descrição da Arquitetura
Stack & Decisões:
- Framework: Spring Boot 3.5.6
- Java: 21 (ambiente local) / 17 (imagem Docker)
- Persistência: Spring Data JPA / Hibernate
- Banco de Dados: PostgreSQL 15
- Empacotamento: JAR (Maven)
- Orquestração de serviços: Docker Compose
- Observabilidade: Spring Actuator (healthcheck exposto)


## Execução com Docker:
Pré-requisitos:
- Docker e Docker Compose instalados.

Comando:

`docker compose up -d –build`

### Acessos padrão:
  - API: http://localhost:8080
  - Swagger UI: http://localhost:8080/swagger-ui/index.html
  - pgAdmin: http://localhost:5050

## Execução local:
#### Pré-requisitos:
- JDK 21, PostgreSQL local.

#### Variáveis de Ambiente da IDE:
```
SPRING_PROFILES_ACTIVE=prod;
SPRING_DATASOURCE_URL=jdbc:postgresql://localhost:5432/techchallenge;
SPRING_DATASOURCE_USERNAME=postgres;
```
**Ajustar SPRING_DATASOURCE_PASSWORD localmente conforme necessário.
