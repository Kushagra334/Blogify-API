<!-- PROJECT LOGO & BADGES -->
<p align="center">
  <img src="https://img.shields.io/badge/Spring_Boot-3.x-brightgreen?style=flat-square" alt="Spring Boot"/>
  <img src="https://img.shields.io/badge/Java-17%2B-blue?style=flat-square" alt="Java"/>
  <img src="https://img.shields.io/badge/PRs-welcome-brightgreen?style=flat-square"/>
</p>

<h1 align="center">📘 Blogify API</h1>
<p align="center">
  <b>Production-grade RESTful backend for modern blogging, built with Spring Boot.</b><br>
  Seamless CRUD, authentication, validation, and ready for scalable deployment.
</p>

---

## 🚀 Features

- **Modular Architecture:** Controller ➜ Service ➜ Repository for clean code.
- **MySQL Database (production-ready):** JPA/Hibernate auto-creates tables.
- **OpenAPI/Swagger Docs:** Instant API exploration @ [`/swagger-ui/index.html`](http://localhost:8080/swagger-ui/index.html)
- **JWT Authentication:** Secure endpoints, login returns JWT, RBAC (admin/user).
- **Search & Pagination:** Efficient queries by title and page.
- **Validation & Exception Handling:** Robust DTO checks and global handlers.
- **Deployment Ready:** Easily deploy on AWS, Render, Railway, & more.

---

## 📦 Tech Stack

| Framework | Version  | Notes                      |
|-----------|----------|----------------------------|
| Java      | 17+      |                            |
| Spring Boot| 3.x     | + Spring Security (JWT)    |
| Spring Data JPA | —  |                            |
| MySQL     | 8+       | Integration                |
| Lombok    | —        | Boilerplate free           |
| Swagger   | springdoc-openapi | API Docs         |
| Maven     | —        | Build/Dependency Management|


---

## 🛠 Getting Started

### 1️⃣ Prerequisites

- Java 17+
- Maven 3.x
- MySQL running locally

### 2️⃣ Database Setup

```
sql
CREATE DATABASE blogify_api_qa;
```

### 3️⃣ Update MySQL Credentials

**Edit** `src/main/resources/application-local.properties` *(GIT-IGNORED)*

```
properties
spring.datasource.url=jdbc:mysql://localhost:3306/blogify_api_qa?useSSL=false&serverTimezone=UTC
spring.datasource.username=root
spring.datasource.password=YOUR_PASSWORD
spring.profiles.active=local
```

### 4️⃣ Run the Application

```
shell
mvn spring-boot:run
```

---

## 📘 API Documentation

Interactive Swagger UI:<br>
🔗 [`http://localhost:8080/swagger-ui/index.html`](http://localhost:8080/swagger-ui/index.html)

---

## 🗄️ Database Schema

Tables auto-generated via JPA:

```
posts        categories      comments
users        roles           post-category-user relationships
JWT-related authorization tables
```

---

## 🔒 Security

- JWT Authentication (stateless sessions)
- Role-based access: `ROLE_ADMIN`, `ROLE_USER`
- Passwords hashed with BCrypt
- Swagger marks protected endpoints

---

## 🧪 Testing

Use:
- Swagger UI
- Postman
- Thunder Client
- cURL

---

## 🤝 Contributions

PRs and issues welcome! Please check the [issues](../../issues) page.

---