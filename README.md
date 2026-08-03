# Store Management API

A RESTful API built with Java and Spring Boot for managing store products.

## Features

- Create Product
- Get All Products
- Get Product by ID
- Update Product
- Handle Product Not Found (HTTP 404)
- Delete Product (Coming Soon)

## Tech Stack

- Java 21
- Spring Boot
- Spring Data JPA
- MySQL
- Maven
- Postman

## Architecture

```text
Client
   ↓
Controller
   ↓
Service
   ↓
Repository
   ↓
Database
```

## API Endpoints

| Method | Endpoint | Description |
|--------|----------|-------------|
| GET | `/products` | Get all products |
| GET | `/products/{id}` | Get one product by ID |
| POST | `/products` | Create a product |
| PUT | `/products/{id}` | Update a product |

## Learning Documentation

Detailed lesson notes are available in the `docs/` folder.

## Completed Concepts

- Layered Architecture
- REST API Basics
- CRUD (Create, Read, Update)
- Spring Data JPA
- Dependency Injection
- Optional
- Custom Exception
- `@ResponseStatus`
- HTTP Status Codes (200, 404)

## Future Features

- Delete Product
- Global Exception Handling (`@ControllerAdvice`)
- Validation (`@Valid`)
- DTO Pattern
- Pagination & Sorting
- Swagger / OpenAPI
- Docker
- Authentication (JWT)

## Author

**Vatanak Vut**

Computer Science Student | Backend Developer (Java & Spring Boot)
