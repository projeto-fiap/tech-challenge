FROM openjdk:17-jdk-slim

WORKDIR /app

RUN apt-get update
RUN apt-get install -y git
RUN git config --global user.name "Leonardo Soares"
RUN git config --global user.email "leonardo.soares@sptech.school.com.br"
RUN git clone https://github.com/projeto-fiap/tech-challenge.git

WORKDIR /app/tech-challenge/project

RUN apt-get install -y maven
RUN mvn clean install

# Copia o script de configuração do Kubernetes
COPY src/main/java/tech/fiap/project/infra/setup-k8s.sh /app/setup-k8s.sh
RUN chmod +x /app/setup-k8s.sh

EXPOSE 8080

# O comando abaixo foi comentado
# RUN /app/setup-k8s.sh

CMD ["java", "-jar", "target/project-0.0.1-SNAPSHOT.jar", "--spring.profiles.active=prd"]
