# Processo Seletivo - API de Gestão de Servidores

Este projeto é uma API REST desenvolvida com **Spring Boot 3**, **Java 21**, **JPA**, **MapStruct**, **Lombok** e **Min.IO**, com autenticação baseada em JWT.
A solução propõe um CRUD sobre servidores efetivos e temporários, suas lotações, unidades, e entidades relacionadas.

## PSS 02/2025/SEPLAG (Analista de TI - Perfil Júnior, Pleno, Sênior)
## 👤 Dados do Candidato
- **Nome:** Valdinilson Lourenço da Cunha
- **CPF:** 041.XXX.XXX-86
- **E-mail:** valdinilson@gmail.com

| Inscrição | Perfil                                    | Nível  |
|:----------|:------------------------------------------|:-------|
| `9258`    | `Desenvolvedor Java (Back-End)`           | Sênior |
| `9341`    | `Desenvolvedor Full Stack` | Sênior |
| `9353` | `Desenvolvedor Java (Back-End)`           | Pleno  |

---

## 🚀 Tecnologias Utilizadas

- Java 21
- Spring Boot 3.2.10
- Spring Data JPA
- Spring Security
- Lombok
- MapStruct
- Maven
- JWT (Json Web Token)
- Swagger / OpenAPI (Springdoc)
- PostgreSQL (em container Docker)
- Min.IO (em container Docker)
- Docker & Docker Compose

---

## 📦 Como Executar a Aplicação via Docker Compose

Esta aplicação será executada com **todos os serviços em containers**:

#### Pré-requisitos

- Docker e Docker Compose instalados
- Arquivo `.env` configurado com as variáveis necessárias (incluso no projeto)

#### Passos:

1. Clone o projeto

```bash
git clone https://github.com/valdinilson/processo-seletivo.git
cd processo-seletivo
```

2. Suba os containers

```bash
# Suba toda a stack
docker compose up --build
```

A seguinte stack será criada:

- **API Spring Boot**: http://localhost:8080/api
- **MinIO Console**: http://localhost:9001
- **PostgreSQL**: acessível via `localhost:5432`

#### Observações:
- O bucket Min.IO (`processo-seletivo-seplag`) é **criado automaticamente pela aplicação**.
- O schema do banco de dados é criado automaticamente com base no script `scripts/init.sql`.

---

## 🧪 Como Testar a API

Utilize sua ferramenta de preferência, como: Postman, Insomnia, entre outras. 

### Swagger

Acesse a documentação interativa da API:

```
http://localhost:8080/swagger-ui.html
```

### Pré-requisito

- Ter um usuário cadastrado no sistema. Caso não tenha, para fins de teste, use o endpoint `/api/usuarios/registrar` para cadastro.

### Autenticação

1. Use o endpoint `/api/autenticar` para obter o token JWT.
2. Em seguida, clique em **Authorize** no Swagger e insira em **Value**:

```
<token_gerado>
```

3. Caso necessite renovar o token, solicite o novo token pelo endpoint `/api/token`. Desde que, o atual esteja válido. Caso contrário, uma nova autenticação é necessária.

### Endpoints:

#### Unidades

- **GET** `/api/unidades` - Listar todas as unidades cadastradas
- **GET** `/api/unidades/{id}` - Buscar uma Unidade pelo ID
- **POST** `/api/unidades` - Inserir uma Unidade 
- **PUT** `/api/unidades/{id}` - Atualizar uma Unidade
- **GET** `/api/unidades/{id}/enderecos` - Listar todos os endereços cadastrados para a Unidade
- **POST** `/api/unidades/{id}/enderecos` - Inserir um endereço da Unidade
- **PUT** `/api/unidades/{idUnidade}/enderecos/{idEndereco}` - Atualizar um endereço da Unidade
- **GET** `/api/unidades/{id}/efetivos` - Listar Servidores Efetivos lotados na Unidade

#### Servidores

- **GET** `/api/servidores/{id}/enderecos` - Listar todos os endereços cadastrados para o Servidor
- **POST** `/api/servidores/{id}/enderecos` - Inserir um endereço do Servidor
- **PUT** `/api/servidores/{idPessoa}/enderecos/{idEndereco}` - Atualizar um endereço do Servidor
- **POST** `/api/servidores/{id}/fotos` - Registrar o upload de um ou mais fotos para o Servidor

#### Servidores Temporários

- **GET** `/api/servidores/temporarios` - Listar todos os Servidores Temporários
- **GET** `/api/servidores/temporarios/{id}` - Buscar um Servidor Temporário pelo ID
- **POST** `/api/servidores/temporarios` - Inserir um Servidor Temporário
- **PUT** `/api/servidores/temporarios/{id}` - Atualizar um Servidor Temporário

#### Servidores Efetivos

- **GET** `/api/servidores/efetivos` - Listar todos os Servidores Efetivos
- **GET** `/api/servidores/efetivos/{id}` - Buscar um Servidor Efetivo pelo ID
- **POST** `/api/servidores/efetivos` - Inserir um Servidor Efetivo
- **PUT** `/api/servidores/efetivos/{id}` - Atualizar um Servidor Efetivo
- **GET** `/api/servidores/efetivos/{nome}/enderecos-funcionais` - Listar endereço funcional pelo nome ou parte do nome do Servidor Efetivo

#### Lotações

- **GET** `/api/lotacoes` - Listar todas as Lotações cadastradas
- **GET** `/api/lotacoes/{id}` - Buscar uma lotação pelo ID
- **POST** `/api/lotacoes` - Inserir uma lotação
- **PUT** `/api/lotacoes/{id}` - Atualizar uma lotação

#### Usuários

- **POST** `/api/usuarios/registrar` - Registrar novo usuário no sistema

#### Autenticação

- **POST** `/api/autenticar` - Autenticar usuário cadastrado no sistema
- **GET** `/api/token` - Solicitar novo token

#### Endereços

- **GET** `/api/enderecos` - Listar todos os endereços cadastrados
- **GET** `/api/enderecos/{id}` - Buscar um endereço pelo ID

---

## 🛡️ Segurança

A autenticação e autorização são feitas com base em JWT, protegendo os endpoints.

---

## 📂 Estrutura do Projeto

```
br.gov.mt.seplag
├── config              # Configurações de segurança e aplicação
├── controller          # Controllers REST
├── domain              # Entidades JPA
├── exception           # Global Exception Handler
├── filter              # Filters aplicados nas requisições
├── helper              # Classes auxiliares
├── mapper              # MapStruct Mappers
├── repository          # Repositórios JPA
├── request             # DTOs de Requisição
├── response            # DTOs de Resposta
├── security            # Classes utilizadas pelo JWT
├── service             # Regras de negócio
├── util                # Utilitários diversos

```

---

## 🐳 Containers e Orquestração

A orquestração está feita com **Docker Compose**, e inclui:

- API Java (Spring Boot)
- PostgreSQL (última versão)
- MinIO (armazenamento de fotos)

Todos os containers estão conectados por redes dedicadas, com variáveis centralizadas no arquivo `.env`.

---

## 📌 Considerações

- Projeto construído com **camadas bem definidas**, **validações**, **tratamento de erros** e documentação via Swagger.
- Uso de DTOs e Mappers (MapStruct) para separação entre domínio e API.
- Persistência com JPA e banco PostgreSQL.
- Upload e armazenamento de imagens com MinIO.