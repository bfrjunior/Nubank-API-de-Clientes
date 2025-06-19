# Nubank - API de Clientes

API REST para gerenciamento de clientes e contatos desenvolvida com Spring Boot.

## Tecnologias

- Java 21
- Spring Boot 3.5.0
- PostgreSQL
- Docker
- Maven

## Executar Localmente

### Pré-requisitos
- Java 21
- Maven
- Docker e Docker Compose

### Com Docker (Recomendado)

```bash
# Build da aplicação
./mvnw clean package -DskipTests

# Executar aplicação + banco
docker-compose up --build
```

### Sem Docker

```bash
# Configurar PostgreSQL local
# Criar database: cadastro

# Executar aplicação
./mvnw spring-boot:run
```

## Endpoints

### Clientes
- `POST /clientes` - Criar cliente
- `GET /clientes` - Listar todos os clientes
- `GET /clientes/{id}/contatos` - Listar contatos do cliente

### Exemplo de Requisição

```json
POST /clientes
{
  "nome": "Fernando",
  "contatos": [
    {
      "telefone": "8548125",
      "email": "teste@gmail.com"
    }
  ]
}
```

## Documentação

Swagger UI disponível em: `http://localhost:8080/swagger-ui.html`

## Portas

- Aplicação: `8080`
- PostgreSQL: `5432`

## 👨‍💻 Autor

Desenvolvido por **Fernando Rocha**  
📍 Fortaleza - CE  
🔗 [LinkedIn](https://www.linkedin.com/in/fernando-rocha-962465200/)
