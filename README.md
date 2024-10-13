# Guia de Instalação e Uso do Minikube e Kubernetes

Este guia orienta sobre como instalar e configurar o Minikube e o Kubernetes, além de realizar o deploy de uma aplicação localmente.

## Passo 1: Instalar o Minikube e o Kubernetes

### Windows

1. *Instale o Chocolatey*:
   powershell
   Set-ExecutionPolicy Bypass -Scope Process -Force; 
   [System.Net.ServicePointManager]::SecurityProtocol = [System.Net.SecurityProtocolType]::Tls12; 
   iex ((New-Object System.Net.WebClient).DownloadString('https://chocolatey.org/install.ps1'))
   

2. *Instale o Minikube e o Kubernetes CLI*:
   powershell
   choco install minikube
   choco install kubernetes-cli
   

3. *Inicie o Minikube*:
   powershell
   minikube start
   

### Linux

1. *Atualize o sistema e instale dependências*:
   bash
   sudo apt update
   sudo apt install -y curl apt-transport-https
   

2. *Baixe a versão mais recente do Minikube*:
   bash
   curl -LO https://storage.googleapis.com/minikube/releases/latest/minikube-linux-amd64
   

3. *Mova o binário para o diretório de binários do sistema*:
   bash
   sudo install minikube-linux-amd64 /usr/local/bin/minikube
   

4. *Inicie o Minikube*:
   bash
   minikube start --driver=docker
   

5. *Baixe a versão mais recente do kubectl*:
   bash
   curl -LO "https://storage.googleapis.com/kubernetes-release/release/$(curl -s https://storage.googleapis.com/kubernetes-release/release/stable.txt)/bin/linux/amd64/kubectl"
   

6. *Mova o binário do kubectl*:
   bash
   chmod +x ./kubectl
   sudo mv ./kubectl /usr/local/bin/kubectl
   

---

## Passo 2: Iniciar o Minikube

Execute o seguinte comando para iniciar o Minikube:
bash
minikube start


---

## Passo 3: Criar um túnel para serviços externos

Para acessar os serviços do Kubernetes expostos, execute:
bash
minikube tunnel

Este comando cria um túnel que permite o acesso aos serviços externos.

---

## Passo 4: Fazer o Deploy da Aplicação

Realize o deploy dos arquivos necessários para subir os pods no Kubernetes com o comando:
bash
kubectl apply -f (CAMINHO_DO_ARQUIVO)/tech-challenge/project/src/main/resources/deploy/k8s/

Substitua (CAMINHO_DO_ARQUIVO) pelo caminho apropriado.

---

## Passo 5: Acessar as APIs

Após aplicar os arquivos, as APIs estarão configuradas e rodarão na porta 8080. Mesmo que o projeto não esteja iniciado, os pods serão criados e estarão em execução com a aplicação.

---

### Observações

- Certifique-se de que o Docker está instalado e em execução antes de iniciar o Minikube.
- Para verificar o status dos pods, use o comando:
  bash
  kubectl get pods
