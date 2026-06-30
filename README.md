# 🐳 Infraestrutura Docker - Accesos Terceros MS

Este repositório contém a configuração base de infraestrutura via Docker para o microsserviço `accesos-terceros-ms`, aplicando os padrões arquiteturais Cloud Native e boas práticas de deploy (simulando a esteira de CI/CD para AWS).

## 🚀 Como rodar a aplicação localmente

### 1. Pré-requisitos
* Java 21
* Docker e Docker Compose rodando na máquina (Docker Desktop ou Engine Nativo).

### 2. Passo a Passo para Deploy Local
Antes de subir o container, é obrigatório gerar o executável (`.jar`) atualizado:
> **Obs:** Sempre que atualizar o código Java, é necessário gerar o `.jar` novamente antes de reiniciar o Docker. Você também pode fazer isso pelo painel do Maven na IDE (clean > package).

```bash
# 1. Empacota a aplicação ignorando os testes
./mvnw clean package -DskipTests

# 2. Derruba os containers antigos (caso estejam rodando)
docker compose -f docker-compose.dev.yml down

# 3. Sobe a infraestrutura forçando o build da nova imagem
docker compose --env-file .env.dev -f docker-compose.dev.yml up -d --build