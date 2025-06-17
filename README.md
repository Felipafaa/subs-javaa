# Catálogo de Livros API RESTful

Felipe Rosa Peres rm557636 2tdspx

## Descrição do Projeto

Este projeto implementa uma API RESTful completa para a gestão de um catálogo de livros. [cite_start]Desenvolvida utilizando o framework Spring Boot, a API permite realizar as operações básicas de CRUD (Criação, Leitura, Atualização e Deleção) de livros, além de oferecer funcionalidades avançadas como paginação para a listagem de livros e filtros por título, autor e intervalo de ano de publicação.

[cite_start]A API inclui documentação interativa utilizando SpringDoc para geração automática do Swagger UI, facilitando o teste e a compreensão dos endpoints disponíveis.

## Tecnologias Utilizadas

* **Java**: Linguagem de programação (JDK 17+, compatível com Java 18).
* **Spring Boot**: Framework principal (versão 3.5.0).
    * `spring-boot-starter-web`: Para construção de APIs RESTful.
    * `spring-boot-starter-data-jpa`: Para persistência de dados com JPA.
    * `spring-boot-starter-validation`: Para validação de dados (Bean Validation).
* **H2 Database**: Banco de dados relacional em memória, ideal para desenvolvimento e testes.
* **Maven**: Ferramenta de automação de build e gerenciamento de dependências.
* **SpringDoc OpenAPI (Swagger UI)**: Para documentação e testes interativos da API.

## Pré-requisitos

Para rodar este projeto, você precisará ter instalado:

* Java Development Kit (JDK) 17 ou superior.
* Apache Maven.

## Como Rodar a Aplicação

Siga os passos abaixo para baixar, compilar e executar a aplicação:

1.  **Clonar o Repositório:**
    ```bash
    git clone [https://github.com/Felipafaa/catalogo-livros.git](https://github.com/Felipafaa/catalogo-livros.git)
    cd catalogo-livros
    ```
2.  **Compilar e Executar:**
    Abra seu terminal na raiz do projeto (onde o arquivo `pom.xml` está localizado) e execute os comandos Maven:
    ```bash
    mvn clean install
    mvn spring-boot:run
    ```
    A aplicação será iniciada e estará disponível na porta `8080` (http://localhost:8080).

## Como Usar a API

A API expõe diversos endpoints RESTful para gerenciar o catálogo de livros.

### Documentação Interativa (Swagger UI)

[cite_start]A forma mais fácil de interagir e testar a API é através da interface do Swagger UI.

1.  Com a aplicação rodando, acesse em seu navegador:
    ```
    http://localhost:8080/docs
    ```
    Você verá uma interface com todos os endpoints documentados, onde você pode enviar requisições e ver as respostas.

### Endpoints da API

A API de Catálogo de Livros oferece os seguintes endpoints, baseados no `LivroController.java`:

#### 1. Criar um Novo Livro

* [cite_start]**`POST /livros`** 
* **Descrição**: Cria um novo registro de livro no catálogo.
* **Headers**: `Content-Type: application/json`
* **Corpo da Requisição (JSON Exemplo)**:
    ```json
    {
      "titulo": "O Guia do Mochileiro das Galáxias",
      "autor": "Douglas Adams",
      "anoPublicacao": 1979,
      "isbn": "978-8575080004"
    }
    ```
* **Resposta (JSON Exemplo)**:
    ```json
    {
      "id": 1,
      "titulo": "O Guia do Mochileiro das Galáxias",
      "autor": "Douglas Adams",
      "anoPublicacao": 1979,
      "isbn": "978-8575080004"
    }
    ```
    *Retorna o livro criado com o ID gerado.*

#### 2. Obter Detalhes de um Livro

* [cite_start]**`GET /livros/{id}`** 
* **Descrição**: Recupera os detalhes de um livro específico pelo seu ID.
* **Parâmetros de Path**:
    * `id` (Long): O identificador único do livro.
* **Exemplo de URL**: `http://localhost:8080/livros/1`
* **Resposta (JSON Exemplo)**:
    ```json
    {
      "id": 1,
      "titulo": "O Guia do Mochileiro das Galáxias",
      "autor": "Douglas Adams",
      "anoPublicacao": 1979,
      "isbn": "978-8575080004"
    }
    ```
    *Retorna 404 Not Found se o livro não for encontrado.*

#### 3. Atualizar Dados de um Livro Existente

* [cite_start]**`PUT /livros/{id}`** 
* **Descrição**: Atualiza todas as informações de um livro existente.
* **Parâmetros de Path**:
    * `id` (Long): O identificador único do livro a ser atualizado.
* **Headers**: `Content-Type: application/json`
* **Corpo da Requisição (JSON Exemplo)**:
    ```json
    {
      "titulo": "O Guia do Mochileiro das Galáxias (Nova Edição)",
      "autor": "Douglas Adams",
      "anoPublicacao": 2020,
      "isbn": "978-8575080004"
    }
    ```
* **Resposta (JSON Exemplo)**:
    ```json
    {
      "id": 1,
      "titulo": "O Guia do Mochileiro das Galáxias (Nova Edição)",
      "autor": "Douglas Adams",
      "anoPublicacao": 2020,
      "isbn": "978-8575080004"
    }
    ```
    *Retorna o livro atualizado. Retorna 404 Not Found se o livro não for encontrado.*

#### 4. Deletar um Livro

* [cite_start]**`DELETE /livros/{id}`** 
* **Descrição**: Remove um livro do catálogo pelo seu ID.
* **Parâmetros de Path**:
    * `id` (Long): O identificador único do livro a ser deletado.
* **Exemplo de URL**: `http://localhost:8080/livros/1`
* **Resposta**: `204 No Content` (se a exclusão for bem-sucedida).
    *Retorna 404 Not Found se o livro não for encontrado.*

#### 5. Listar Livros com Paginação e Filtros

* [cite_start]**`GET /livros`** 
* [cite_start]**Descrição**: Lista todos os livros disponíveis com suporte a paginação e filtros opcionais. 
* **Parâmetros de Query (Opcionais)**:
    * [cite_start]`titulo` (String): Filtra livros que contêm o título especificado (case-insensitive). 
    * [cite_start]`autor` (String): Filtra livros que contêm o nome do autor especificado (case-insensitive). 
    * [cite_start]`anoInicial` (Integer): Filtra livros publicados a partir deste ano. 
    * [cite_start]`anoFinal` (Integer): Filtra livros publicados até este ano. 
    * [cite_start]`page` (Integer): Número da página (padrão é 0). 
    * [cite_start]`size` (Integer): Número de elementos por página (padrão é 20). 
    * `sort` (String): Critério de ordenação (ex: `titulo,asc` para título ascendente; `anoPublicacao,desc` para ano descendente).
* **Exemplo de URL**: `http://localhost:8080/livros?titulo=Guia&autor=Adams&anoInicial=1970&anoFinal=1980&page=0&size=5&sort=anoPublicacao,asc`
* **Resposta (JSON Exemplo - Objeto Page)**:
    ```json
    {
      "content": [
        {
          "id": 1,
          "titulo": "O Guia do Mochileiro das Galáxias",
          "autor": "Douglas Adams",
          "anoPublicacao": 1979,
          "isbn": "978-8575080004"
        }
      ],
      "pageable": {
        "pageNumber": 0,
        "pageSize": 5,
        "sort": {
          "empty": false,
          "sorted": true,
          "unsorted": false
        },
        "offset": 0,
        "paged": true,
        "unpaged": false
      },
      "last": true,
      "totalElements": 1,
      "totalPages": 1,
      "size": 5,
      "number": 0,
      "sort": {
        "empty": false,
        "sorted": true,
        "unsorted": false
      },
      "first": true,
      "numberOfElements": 1,
      "empty": false
    }
    ```

## Acesso ao H2 Console

[cite_start]O banco de dados H2 está configurado para ser acessado via console web.

1.  Com a aplicação rodando, acesse em seu navegador:
    ```
    http://localhost:8080/h2-console
    ```
2.  Preencha as credenciais:
    * **JDBC URL**: `jdbc:h2:mem:livrosdb`
    * **User Name**: `sa`
    * **Password**: (Deixe em branco)
3.  Clique em "Connect". Você poderá visualizar as tabelas e dados no banco de dados em memória.

## Como Rodar os Testes

Este projeto inclui testes automatizados para garantir a funcionalidade da API.

1.  Na raiz do projeto, execute o comando Maven:
    ```bash
    mvn test
    ```
    Isso executará a classe de teste `CatalogoLivrosApplicationTests.java` e quaisquer outros testes que possam ter sido adicionados.