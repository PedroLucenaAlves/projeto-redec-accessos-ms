# 🐳 Infraestrutura Docker - Accesos Terceros MS

Este repositório contém a configuração base de infraestrutura via Docker para o microsserviço `accesos-terceros-ms`, aplicando os padrões arquiteturais Cloud Native e boas práticas de deploy (simulando a esteira de CI/CD para AWS).

## 🚀 Como rodar a aplicação localmente

### 1. Pré-requisitos
* Java 21
* Docker e Docker Compose rodando na máquina (Docker Desktop ou Engine Nativo).

### 2. Passo a Passo para Deploy Local
Antes de subir o container, é obrigatório gerar o executável (`.jar`) atualizado:
```bash
./mvnw clean package -DskipTests