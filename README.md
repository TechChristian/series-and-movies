# 🎬 Series and Movies API

API REST para gerenciamento de **filmes e séries**, desenvolvida com **Java 21** e **Spring Boot**.

O projeto foi desenvolvido seguindo boas práticas de arquitetura em camadas, utilizando autenticação com **JWT**, controle de permissões com **Spring Security**, cache com **Redis**, persistência de dados com **MySQL**, conteinerização com **Docker** e documentação da API através do **Swagger/OpenAPI**.

---

## 🚀 Tecnologias

![Java 21](https://img.shields.io/badge/Java_21-2D2D2D?style=flat&logo=openjdk&logoColor=white)
![Spring Boot](https://img.shields.io/badge/Spring_Boot-2D2D2D?style=flat&logo=springboot&logoColor=6DB33F)
![Spring Web](https://img.shields.io/badge/Spring_Web-2D2D2D?style=flat&logo=spring&logoColor=6DB33F)
![Spring Security](https://img.shields.io/badge/Spring_Security-2D2D2D?style=flat&logo=springsecurity&logoColor=6DB33F)
![Spring Data JPA](https://img.shields.io/badge/Spring_Data_JPA-2D2D2D?style=flat&logo=spring&logoColor=6DB33F)
![Spring Validation](https://img.shields.io/badge/Spring_Validation-2D2D2D?style=flat&logo=spring&logoColor=6DB33F)
![JWT](https://img.shields.io/badge/JWT-2D2D2D?style=flat&logo=jsonwebtokens&logoColor=white)
![Redis](https://img.shields.io/badge/Redis-2D2D2D?style=flat&logo=redis&logoColor=DC382D)
![MySQL](https://img.shields.io/badge/MySQL-2D2D2D?style=flat&logo=mysql&logoColor=4479A1)
![Docker](https://img.shields.io/badge/Docker-2D2D2D?style=flat&logo=docker&logoColor=2496ED)
![Swagger](https://img.shields.io/badge/Swagger-2D2D2D?style=flat&logo=swagger&logoColor=85EA2D)
![JUnit 5](https://img.shields.io/badge/JUnit_5-2D2D2D?style=flat&logo=junit5&logoColor=25A162)
![MockMvc](https://img.shields.io/badge/MockMvc-2D2D2D?style=flat&logo=spring&logoColor=6DB33F)
![Mockito](https://img.shields.io/badge/Mockito-2D2D2D?style=flat)
![Lombok](https://img.shields.io/badge/Lombok-2D2D2D?style=flat)
![Maven](https://img.shields.io/badge/Maven-2D2D2D?style=flat&logo=apachemaven&logoColor=C71A36)
![Postman](https://img.shields.io/badge/Postman-2D2D2D?style=flat&logo=postman&logoColor=FF6C37)

---

##  Funcionalidades

- Autenticação com JWT
- Cadastro de usuários
- Controle de permissões (ADMIN e USER)
- Cadastro de filmes e séries
- Atualização de conteúdos
- Remoção de conteúdos
- Busca por título
- Busca por gênero
- Busca por tipo (Filme ou Série)
- Cadastro de avaliações
- Atualização automática da média das avaliações
- Cache utilizando Redis
- Documentação via Swagger/OpenAPI
- Testes unitários e de integração

---

## Arquitetura

O projeto segue uma arquitetura em camadas:

```text
Controller
    ↓
Service
    ↓
Repository
    ↓
Database
```

Também utilizo:

- DTOs
- Mapper
- Exceptions personalizadas
- Bean Validation
- Spring Security
- JWT
- Redis Cache

---

## Autenticação

A autenticação da API é realizada através de **JWT (JSON Web Token)**.

Após realizar o login, envie o token no header das requisições protegidas.

```http
Authorization: Bearer {seu_token}
```

---

## 📌 Endpoints

### Autenticação

| Método | Endpoint | Descrição |
| :--- | :--- | :--- |
| `POST` | `/v1/api/auth/register` | Cadastro de usuários |
| `POST` | `/v1/api/auth/login` | Login e geração do JWT |

---

### Filmes e Séries

| Método | Endpoint | Descrição |
| :--- | :--- | :--- |
| `POST` | `/v1/api/movies` | Cadastro de filme/série (ADMIN) |
| `GET` | `/v1/api/movies` | Lista todos os conteúdos |
| `GET` | `/v1/api/movies/{title}` | Busca conteúdo pelo título |
| `GET` | `/v1/api/movies/genre/{genre}` | Busca conteúdos por gênero |
| `GET` | `/v1/api/movies/type/{contentType}` | Busca conteúdos por tipo |
| `PATCH` | `/v1/api/movies/{id}` | Atualiza um conteúdo (ADMIN) |
| `DELETE` | `/v1/api/movies/{id}` | Remove um conteúdo (ADMIN) |

---

### Avaliações

| Método | Endpoint | Descrição |
| :--- | :--- | :--- |
| `POST` | `/v1/api/movies/{movieId}/reviews` | Cria uma avaliação |
| `GET` | `/v1/api/movies/reviews` | Lista todas as avaliações |

---

### Usuários

| Método | Endpoint | Descrição |
| :--- | :--- | :--- |
| `GET` | `/v1/api/users` | Lista todos os usuários (ADMIN) |
| `DELETE` | `/v1/api/users/{id}` | Remove um usuário (ADMIN) |

---

## 🐳 Executando com Docker

### 1. Clone o repositório

```bash
git clone https://github.com/seu-usuario/series-and-movies-api.git
```

### 2. Acesse a pasta do projeto

```bash
cd series-and-movies-api
```

### 3. Construa e inicie os containers

```bash
docker compose up --build
```

Serão iniciados os seguintes serviços:

-  API Spring Boot
-  MySQL
-  Redis

---


## 📖 Documentação

Após iniciar a aplicação:

### API

```
http://localhost:8080/v1/api
```

### Swagger

```
http://localhost:8080/swagger-ui/index.html
```

---



## Cache

A listagem de filmes e séries utiliza **Redis** para armazenamento em cache, reduzindo consultas ao banco de dados e melhorando o desempenho da aplicação.

---

### Estrutura

```text
src
├── controller        # Endpoints da API
├── cache             # Configuração do Redis
├── service           # Regras de negócio
├── database
│   ├── entity        # Entidades JPA
│   ├── enums         # Enumerações da aplicação
│   └── repository    # Acesso aos dados
├── dto               # Objetos de transferência de dados
├── mappers           # Conversão entre entidades e DTOs
├── config            # Configurações da aplicação
├── jwt               # Autenticação e autorização (JWT)
├── exception         # Exceções personalizadas
├── handler           # Tratamento global de exceções
└── openapi           # Documentação Swagger/OpenAPI
```
---

## Autor

Desenvolvido por **Christian**

[![LinkedIn](https://img.shields.io/badge/LinkedIn-0077B5?style=flat&logo=linkedin&logoColor=white)](https://www.linkedin.com/in/christianlsv/)
