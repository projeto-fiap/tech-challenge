#!/bin/bash
if ! minikube status | grep -q "Running"; then
  echo "Iniciando o Minikube..."
  minikube start
else
  echo "Minikube já está em execução."
fi

echo "Aplicando as configurações do Kubernetes..."
kubectl apply -f /app/tech-challenge/project/src/main/java/tech/fiap/project/infra/configuration/deploy/app-deployment.yaml
kubectl apply -f /app/tech-challenge/project/src/main/java/tech/fiap/project/infra/configuration/deploy/hpa.yaml
kubectl apply -f /app/tech-challenge/project/src/main/java/tech/fiap/project/infra/configuration/deploy/db-deployment.yaml

