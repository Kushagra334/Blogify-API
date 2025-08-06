# Blogify - RESTful Blog API

Blogify is a powerful and flexible RESTful backend for a blog application, built with Spring Boot. This project provides a comprehensive set of APIs to manage posts, categories, and comments, along with robust security features.

## Key Features

-   **RESTful Spring Boot Backend:** Developed using Spring Boot for rapid development and easy deployment.
-   **Swagger/OpenAPI Documentation:** Interactive API documentation available via Swagger UI, making it easy to understand and test the endpoints.
-   **Search Posts Feature:** Users can search for posts by keywords in the title (case-insensitive).
-   **H2 In-Memory Database:** Configured to use H2 in-memory database for quick setup and development. The database schema is auto-created on startup, and initial `ROLE_ADMIN` and `ROLE_USER` roles are inserted.
-   **Role-Based Authentication (JWT):** Secure your API with JSON Web Tokens (JWT) for authentication and role-based authorization.
-   **Ready for Deployment:** Designed with deployment in mind, making it suitable for cloud environments.

## Getting Started

### Prerequisites

-   Java 17 or higher
-   Maven 3.x

### Running the Application

To run the Blogify application, use the following Maven command:

```bash
mvn spring-boot:run
```

This will start the application with the H2 in-memory database.

### Accessing Swagger UI

Once the application is running, you can access the interactive Swagger documentation at:

`http://localhost:8080/swagger-ui/index.html` (assuming your application runs on port 8080)

### Accessing H2 Console

You can access the H2 console at:

`http://localhost:8080/h2-console` (assuming your application runs on port 8080)

Use the following credentials:
-   **JDBC URL:** `jdbc:h2:mem:testdb`
-   **Username:** `sa`
-   **Password:** (leave empty)

## API Endpoints

(Details of API endpoints can be found in the Swagger UI)

## Security

This application uses JWT for securing REST APIs. Refer to the Swagger UI for endpoints that require authentication.
