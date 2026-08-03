# Store Management API

A RESTful API built with Java and Spring Boot for managing store products.

## Features

- CRUD operations for Product
- Layered Architecture (Controller → Service → Repository)
- Spring Data JPA integration
- Custom exception handling
- Global exception handling using `@ControllerAdvice`
- Consistent JSON error responses

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
| DELETE | `/products/{id}` | Delete a product | `204 No Content` |

## Error Handling 
When a requested product does not exist, the API throws `ProductNotFoundException` and returns: ` 404 Not Found`

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
