# Catálogo de Livros - API RESTful

Este projeto é uma API RESTful para o gerenciamento de um catálogo de livros e aluguel de livros, desenvolvido com **Spring Boot**. A API permite criar, listar, atualizar, consultar e remover livros do catálogo, além de alugar livros para usuários.

A aplicação utiliza **Spring Data JPA** com **H2 Database** para persistência, **Springdoc OpenAPI** para documentação (Swagger UI), e **Spring Security** com autenticação.

---

## 1. Tecnologias Utilizadas

- Java 17
- Spring Boot
- Spring Data JPA
- H2 Database (banco de dados em memória)
- Spring Security
- Springdoc OpenAPI (Swagger)
- Spring Boot Test (para testes de integração)

---

## 2. Funcionalidades

- **Gerenciamento de Livros**: Endpoints para criar, listar, consultar, atualizar e remover livros.
- **Aluguel de Livros**: Marcação de livros como "indisponíveis" ao serem alugados.
- **Documentação da API**: Disponível via Swagger UI.
- **Autenticação**: Proteção dos endpoints utilizando Spring Security.

---

## 3. Pré-requisitos

- **Java 17**: Certifique-se de que o Java 17 está instalado.
- **Gradle**: Para gerenciar as dependências e o ciclo de vida da aplicação.

---

## 4. Iniciando a Aplicação

### 4.1. Clone o repositório

git clone https://github.com/seu-usuario/seu-repositorio.git  
cd seu-repositorio

### 4.2. Compile e instale as dependências

No diretório raiz do projeto, execute:

./gradlew clean build

### 4.3. Execute a aplicação

Para iniciar a aplicação localmente, utilize o seguinte comando:

./gradlew bootRun

A aplicação estará disponível em `http://localhost:8080`.

---

## 5. Documentação da API (Swagger UI)

Após iniciar a aplicação, acesse a documentação interativa da API no **Swagger UI**:

- URL do Swagger UI: [http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html)
- Documentação OpenAPI em JSON: [http://localhost:8080/v3/api-docs](http://localhost:8080/v3/api-docs)

---

## 6. Executando os Testes

O projeto inclui testes de integração para verificar o comportamento dos endpoints principais.

### Para rodar os testes:

./gradlew test

Os testes de integração são configurados para usar o perfil `test`, que desativa o Spring Security e utiliza um banco de dados H2 em memória.

---

## 7. Estrutura do Projeto

- `src/main/java`: Código fonte da aplicação.
- `src/main/resources`: Arquivos de configuração, incluindo `application.yml`.
- `src/test`: Testes de integração e unitários para a API.

---

## 8. Perfis de Execução

- **Default**: Perfil padrão para execução da aplicação (`application.yml`).
- **Test**: Utilizado para execução dos testes (`application-test.yml`). Esse perfil desativa a autenticação e usa um banco de dados em memória.

---

## 9. Endpoints Principais

### Livros
- **POST /api/v1/livros**: Adiciona um novo livro.
- **GET /api/v1/livros**: Lista os livros com paginação.
- **GET /api/v1/livros/{id}**: Consulta um livro específico.
- **PUT /api/v1/livros/{id}**: Atualiza os dados de um livro.
- **DELETE /api/v1/livros/{id}**: Remove um livro do catálogo.

### Aluguel de Livro
- **POST /api/v1/livros/{id}/alugar**: Aluga um livro, tornando-o indisponível e enviando um e-mail de confirmação.

---

## 10. Observações

- A configuração do Swagger permite acesso público aos endpoints de documentação.
- O Spring Security está ativo para proteger os endpoints da aplicação, exceto no perfil de teste.

---

## 11. Contato

Para dúvidas ou sugestões, entre em contato:

- **Nome**: Lucas Oliveira
- **E-mail**: ldoliveira91@outlook.com
- **GitHub**: [GitHub](https://github.com/lucasOliveira91)
