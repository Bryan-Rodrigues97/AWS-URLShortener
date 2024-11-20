# AWS URL Shortener

Um projeto Java simples para encurtamento de URLs, implementado utilizando **AWS Lambda** para execução de funções em nuvem e **AWS S3** para armazenamento de dados.

## Funcionalidades

Este projeto implementa uma **API de encurtamento de URLs**, onde as URLs originais são salvas em um bucket S3. As funções principais são executadas em **AWS Lambda**, tornando o processo escalável e eficiente.

- **AWS Lambda** para executar os métodos de encurtamento.
- **AWS S3** para armazenar os dados das URLs encurtadas.
- **API RESTful** para criar e buscar URLs encurtadas.

## Arquitetura

A aplicação segue uma arquitetura serverless utilizando os seguintes serviços:

- **AWS Lambda**: Responsável pela execução do código sem a necessidade de provisionar ou gerenciar servidores.
- **AWS S3**: Usado para armazenar as URLs originais e suas versões encurtadas de forma persistente.
- **API Gateway (se necessário)**: Para criar endpoints HTTP para interagir com as Lambdas.

## Tecnologias Utilizadas

- **Java 17** (ou versão mais recente)
- **AWS Lambda**
- **AWS S3**
- **AWS SDK**
- **Maven** (para gerenciamento de dependências)

## Como Rodar o Projeto

### Pré-requisitos

Antes de rodar o projeto, você precisa ter as seguintes ferramentas instaladas:

- [Java 17 ou superior](https://adoptopenjdk.net/)
- [Maven](https://maven.apache.org/install.html)

### Passos

1. Clone o repositório para sua máquina local:

    ```bash
    git clone https://github.com/Bryan-Rodrigues97/AWS-URLShortener.git
    cd AWS-URLShortener
    ```

2. Instale as dependências:

    ```bash
    mvn clean install
    ```

3. **Configuração de variáveis de ambiente no AWS Lambda**:

    Ao realizar o deploy do projeto para AWS Lambda, você deve configurar as variáveis de ambiente diretamente na AWS Lambda. Não é necessário um arquivo `.env` local.

    As variáveis de ambiente necessárias são:

    - `BUCKET_NAME`: Nome do bucket S3 onde as URLs encurtadas serão armazenadas.

    Essas variáveis podem ser configuradas diretamente na AWS Lambda, no painel de configurações da função Lambda, na seção **Environment variables**.

4. Para testar localmente, você pode usar a AWS SAM CLI ou AWS Lambda Local, mas também é possível testar apenas a lógica Java, sem a AWS Lambda, utilizando um servidor local.

### Deploy na AWS

Para fazer o deploy da função Lambda:

1. **Empacote o projeto** com Maven:

    ```bash
    mvn clean package
    ```

2. **Deploy na AWS Lambda** utilizando a AWS CLI ou AWS Console, e configure os gatilhos apropriados (API Gateway, S3, etc.).

### API

- **POST /create**: Recebe uma URL e retorna a URL encurtada.
- **GET /{shortUrlCode}**: Recebe o código da URL encurtada e retorna a URL original.
