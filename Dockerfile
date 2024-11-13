# Usando a imagem base do OpenJDK
FROM openjdk:17-jdk-slim

# Informações sobre a autora
LABEL authors="miri"

# Definindo o diretório de trabalho dentro do contêiner
WORKDIR /app

# Copiando o arquivo JAR da aplicação para o contêiner
COPY target/backend.uni-0.0.1-SNAPSHOT.jar app.jar

# Comando para executar a aplicação
ENTRYPOINT ["java", "-jar", "app.jar"]


