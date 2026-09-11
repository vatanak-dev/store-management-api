# Store Management API

A Store Management API built with Java 21,
Spring Boot, Spring Data JPA, MySQL, and MapStruct.

## Features

- Product CRUD operations
- DTO-based request/response handling
- MapStruct entity-to-DTO mapping
- Request validation and global exception handling
- Product search and dynamic filtering
- Pagination and sorting
- Product–Category relationships
- OpenAPI/Swagger documentation
- Category & Product relationships
- Relationship DTOs with MapStruct
- Circular JSON prevention through DTO design

## Tech Stack

- Java 21
- Spring Boot
- Spring Data JPA / Hibernate
- MySQL
- MapStruct
- Maven
- Postman
- Git / GitHub

## Architecture

Controller → Service → Repository → MySQL

DTO ↔ MapStruct ↔ Entity

## API Documentation

Swagger UI is available for exploring and testing the API.

## Getting Started

1. Clone the repository
2. Configure MySQL database
3. Update application properties
4. Run the application with Maven

## Future Improvements

- Automated testing
- Stock management
- Transactions
- Authentication & authorization
- Order management
- Docker & CI/CD
