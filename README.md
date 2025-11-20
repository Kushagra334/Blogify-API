📘 Blogify - RESTful Blog API

Blogify is a scalable and production-ready RESTful backend for a blogging application, built using Spring Boot.
It provides robust CRUD APIs for posts, categories, comments, along with authentication, authorization, validation, and full MySQL integration.

🚀 Key Features
✔ Spring Boot REST API

Clean and modular backend with Controller-Service-Repository architecture.

✔ MySQL Persistent Database (Production-ready)

The application now uses MySQL instead of H2.
Tables auto-create on first run using JPA/Hibernate.

✔ Swagger / OpenAPI Documentation

Test and explore APIs interactively at:

http://localhost:8080/swagger-ui/index.html

✔ JWT Authentication & Role-Based Authorization

Login endpoint returns JWT

Role-based access for admin/user

Secure endpoints marked in Swagger

✔ Search & Pagination

Search posts by title (case-insensitive) and paginate results.

✔ Exception Handling & Validation

Custom global exception handler and DTO validation.

✔ Modular & Deployment Ready

Structured for easy deployment on AWS, Render, Railway, etc.

📦 Tech Stack

Java 17+

Spring Boot 3.x

Spring Security (JWT)

Spring Data JPA

MySQL 8+

Lombok

Swagger (springdoc-openapi)

Maven

🛠 Getting Started
1️⃣ Prerequisites

Java 17+

Maven 3.x

MySQL installed & running

2️⃣ Database Setup

Create the database:

CREATE DATABASE blogify_api_qa;

3️⃣ Update MySQL Credentials

The main application.properties contains safe config:

spring.datasource.url=jdbc:mysql://localhost:3306/blogify_api_qa?useSSL=false&serverTimezone=UTC
spring.profiles.active=local


Add your local credentials in:

src/main/resources/application-local.properties  (GIT-IGNORED)


Example:

spring.datasource.username=root
spring.datasource.password=YOUR_PASSWORD

4️⃣ Run the Application
mvn spring-boot:run

📘 API Documentation

Swagger UI:

http://localhost:8080/swagger-ui/index.html

🗄️ Database Schema

MySQL tables are auto-generated via JPA:

posts

categories

comments

users

roles

post-category-user relationships

JWT-related authorization tables

🔒 Security

Blogify uses:

JWT Authentication

Role-based access (ROLE_ADMIN / ROLE_USER)

Password hashing with BCrypt

Secured endpoints marked in Swagger

🧪 Testing

Use Swagger UI or tools like:

Postman

Thunder Client

cURL
