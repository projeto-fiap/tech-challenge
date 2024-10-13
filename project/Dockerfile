# Usa a imagem base do OpenJDK
FROM openjdk:17-jdk-slim

# Diretório de trabalho da aplicação
WORKDIR /app

# Atualiza o apt-get e instala dependências básicas, kubectl e minikube
RUN apt-get update && apt-get install -y \
    git \
    curl \
    wget \
    apt-transport-https \
    ca-certificates \
    gnupg \
    lsb-release

# Instala o Maven
RUN apt-get install -y maven

# Instala o kubectl
RUN curl -LO "https://dl.k8s.io/release/$(curl -L -s https://dl.k8s.io/release/stable.txt)/bin/linux/amd64/kubectl" \
    && install -o root -g root -m 0755 kubectl /usr/local/bin/kubectl

# Instala o Minikube
RUN curl -LO https://storage.googleapis.com/minikube/releases/latest/minikube-linux-amd64 \
    && install minikube-linux-amd64 /usr/local/bin/minikube

# Configurações do Git
RUN git config --global user.name "Leonardo Soares" \
    && git config --global user.email "leonardo.soares@sptech.school.com.br"

# Clona o repositório do projeto
RUN git clone https://github.com/projeto-fiap/tech-challenge.git

WORKDIR /app/tech-challenge/project

# Compila o projeto com Maven
RUN mvn clean install

# Exposição da porta 8080
EXPOSE 8080

# Comando de inicialização do Minikube e aplicação do Kubernetes
CMD minikube start --driver=docker && \
    kubectl apply -f src/main/resources/deploy/k8s/db-deployment.yaml && \
    kubectl apply -f src/main/resources/deploy/k8s/app-deployment.yaml && \
    kubectl apply -f src/main/resources/deploy/k8s/service.yaml && \
    kubectl apply -f src/main/resources/deploy/k8s/hpa.yaml && \
    java -jar target/project-0.0.1-SNAPSHOT.jar --spring.profiles.active=prd
