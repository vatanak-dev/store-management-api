## Features

- Create Product
- Get All Products
- Get Product by ID
- Update Product
- Delete Product
- Request Validation using Jakarta Validation
- Global Exception Handling
- DTO-based API Architecture
- Layered Architecture (Controller → Service → Repository)

## Technologies

- Java 21
- Spring Boot
- Spring Data JPA
- Hibernate
- MySQL Database
- Jakarta Validation
- REST API
- Maven

## Project Architecture

Client
↓
Request DTO
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
↑
Entity
↑
Service
↑
Response DTO
↑
Controller
↑
Client