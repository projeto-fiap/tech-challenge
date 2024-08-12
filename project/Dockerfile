# Use uma imagem base com JDK 11
FROM openjdk:17-jdk-slim

# Defina o diretório de trabalho dentro do contêiner
WORKDIR /app

# Copie o arquivo JAR da aplicação para o contêiner
CMD ["git", "clone" ,"https://github.com/projeto-fiap/tech-challenge.git"]
CMD ["mvn", "clean" ,"package"]
COPY target/project-0.0.1-SNAPSHOT.jar .

# Exponha a porta 8080
EXPOSE 8080

# Comando para executar a aplicação
CMD ["java", "-jar", "project-0.0.1-SNAPSHOT.jar"]
