# Store Management API

A RESTful API built with **Java**, **Spring Boot**, **Spring Data JPA**, and **MySQL** for managing store products. This project is part of my backend development learning journey and follows REST API and layered architecture best practices.

---

## Features

- ✅ Create Product
- ✅ Get All Products
- ✅ Get Product by ID
- ✅ Update Product
- ✅ Delete Product
- ✅ Request Validation using Jakarta Bean Validation
- ✅ Global Exception Handling
- ✅ DTO-based API Architecture
- ✅ DTO-based API responses to separate internal entities from client-facing data.
- ✅ Reusable DTO mapping method to reduce duplicate code and improve maintainability.
- ✅ Refactored DTO mapping using Java Stream API for cleaner and more maintainable code.
- ✅ Automatic MapStruct with DTO mapping
- ✅ API documentation and testing with Swagger/OpenAPI


---

## Tech Stack

- Java 21
- Spring Boot
- Spring Data JPA
- Hibernate
- MySQL
- Jakarta Bean Validation
- Maven
- REST API
- MapStruct
- Pagination & Sorting
- Swagger/OpenAPI
- Testcontainer
- Docker

---

## Project Structure

```text
src
├── controller
├── dto
├── entity
├── exception
├── repository
├── service
└── resources
```

---

## Request Lifecycle

```text
                 CREATE / UPDATE REQUEST

Client
   ↓
ProductRequestDTO
   ↓
Controller
   ↓
Service
   ↓
Entity
   ↓
Repository
   ↓
Database

                      RESPONSE

Database
   ↑
Repository
   ↑
Entity
   ↑
Service
   ↑
ProductResponseDTO
   ↑
Controller
   ↑
Client
```

---

## API Endpoints

| Method | Endpoint | Description |
|---------|----------|-------------|
| GET | `/products` | Get all products |
| GET | `/products/{id}` | Get product by ID |
| POST | `/products` | Create a product |
| PUT | `/products/{id}` | Update a product |
| DELETE | `/products/{id}` | Delete a product |

---

## Validation & Exception Handling

### Request Validation

- `@Valid`
- `@NotBlank`
- `@NotNull`
- `@Positive`
- `@Size`

### Exception Handling

- Global exception handling using `@RestControllerAdvice`
- Custom `ProductNotFoundException`
- Consistent JSON error responses
- HTTP `400 Bad Request`
- HTTP `404 Not Found`

---

## Future Improvements

- Spring Security
- JWT Authentication
- Unit Testing
- Docker
- PostgreSQL

---

## Author

**Vatanak VUT**

Computer Science Student | Aspiring Java Backend Developer
