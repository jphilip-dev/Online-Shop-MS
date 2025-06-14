# Online Shop Microservices

A microservices-based online shop application built with Java, Spring Boot, and Maven.

## Features

- User authentication and management
- Item catalog management
- Shopping cart functionality
- Checkout process
- Gateway filtering and redirections
- JWT security
- Service to Service Connection (OpenFeign)
- Docker build and compose setups
- Aggregated swagger docs 

## Project Structure

- `api-gateway/`  - Handles redirection and filtering of requests
- `auth-service/` - Handles authentication and user management
- `item-service/` - Manages items and catalog
- `cart-service/` - Manages user shopping carts
- `shared`        - Holds shared data and centralized error handling (Controller Advice)

## Technologies Used

- Spring Boot
- JWT
- OpenFeign
- Maven
- Spring Data JPA
- Lombok
- Swagger/OpenAPI (Aggregated in the gateway)
- Docker
- Jakarta Validation


## License

This project is licensed under the [MIT License](LICENSE).