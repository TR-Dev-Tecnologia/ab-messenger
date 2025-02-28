# Etapa 1: Construção da aplicação
FROM maven:3-amazoncorretto-21 AS build

# Definir o diretório de trabalho dentro do container
WORKDIR /app

# Copiar o pom.xml e os arquivos necessários para o container
COPY pom.xml .

# Baixar as dependências Maven (sem copiar o código ainda)
RUN mvn dependency:go-offline

# Copiar o código-fonte do projeto para dentro do container
COPY src ./src

# Gerar os stubs a partir dos arquivos .proto
RUN mvn compile

# Construir o projeto e empacotar o JAR
RUN mvn clean package -DskipTests

# Etapa 2: Imagem final para execução
FROM openjdk:21-jdk-slim

# Definir o diretório de trabalho dentro do container
WORKDIR /app

# Copiar o JAR gerado na etapa de construção
COPY --from=build /app/target/messenger-0.0.1-SNAPSHOT.war /app/messenger-0.0.1-SNAPSHOT.war

# Expor a porta em que a aplicação vai rodar (ajuste conforme necessário)
EXPOSE 9090

# Comando para rodar a aplicação
ENTRYPOINT ["java", "-jar", "/app/messenger-0.0.1-SNAPSHOT.war"]
