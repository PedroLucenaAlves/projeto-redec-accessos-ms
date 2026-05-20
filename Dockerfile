# Usa uma imagem base com Java 21 em uma versão leve do Alpine Linux
FROM amazoncorretto:21-alpine

# Define o diretório de trabalho dentro do container Linux
WORKDIR /app

# Copia o executavel de dentro da minha maquina e coloca dentro do container, renomeando para app.jar
COPY target/*.jar app.jar

# Documenta que a aplicação escuta na porta 8080
EXPOSE 8080

# (botão de ligar) Comando executado quando o container iniciar (na aws ou na minha maquina)
ENTRYPOINT ["java", "-jar", "app.jar"]