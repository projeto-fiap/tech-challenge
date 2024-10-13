FROM openjdk:17-jdk-slim

WORKDIR /app

# Atualize e instale o Git, curl e outras dependências necessárias
RUN apt-get update && apt-get install -y git curl

# Configure o git
RUN git config --global user.name "Leonardo Soares" \
    && git config --global user.email "leonardo.soares@sptech.school.com.br"

# Clone o repositório
RUN git clone https://github.com/projeto-fiap/tech-challenge.git

WORKDIR /app/tech-challenge/project

# Instale o Maven
RUN apt-get install -y maven

# Compile o projeto
RUN mvn clean install

# Instale o kubectl
RUN curl -LO "https://storage.googleapis.com/kubernetes-release/release/$(curl -s https://storage.googleapis.com/kubernetes-release/release/stable.txt)/bin/linux/amd64/kubectl" \
    && chmod +x ./kubectl \
    && mv ./kubectl /usr/local/bin/kubectl

# Copie seus arquivos de configuração do Kubernetes
COPY ./k8s-config /app/k8s-config

# Exponha a porta da aplicação
EXPOSE 8080

# Crie um script de inicialização
RUN echo '#!/bin/sh\n\
kubectl apply -f /app/k8s-config/hpa.yaml\n\
kubectl apply -f /app/k8s-config/deployment.yaml\n\
java -jar target/project-0.0.1-SNAPSHOT.jar --spring.profiles.active=prd' > /app/start.sh \
    && chmod +x /app/start.sh

# Defina o comando de inicialização
CMD ["/app/start.sh"]
