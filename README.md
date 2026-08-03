# Store Management API

A RESTful API built with Java and Spring Boot for managing store products.

## Features

- CRUD operations for Product
- Layered Architecture (Controller → Service → Repository)
- Spring Data JPA integration
- Custom exception handling
- Global exception handling using `@ControllerAdvice`
- Consistent JSON error responses
### Validation & Error Handling

- Request validation using Jakarta Bean Validation (`@Valid`)
- Field validation with `@NotBlank`, `@NotNull`, `@Positive`, and `@Size`
- Centralized exception handling with `@RestControllerAdvice`
- Consistent JSON error responses for `404 Not Found`
- Consistent JSON validation responses for `400 Bad Request`

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
- Global exception handling and @Valid

## Future Features

- DTO Pattern
- Pagination & Sorting
- Swagger / OpenAPI
- Docker
- Authentication (JWT)

## Author

**Vatanak Vut**

Computer Science Student | Backend Developer (Java & Spring Boot)
