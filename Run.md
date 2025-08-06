# Steps to Run Blogify Application
## 1. Maven Build the Project
If you have installed Maven on your machine then use the below command:
```
mvn clean package
```
If you haven't insatlled Maven on your machine then use below command:
```
./mvnw clean package
```
Note: Go to root directory of the project and execute above command.

### Run the Application

To run the Spring Boot Blog Application, you can use the following Maven command:

```bash
mvn spring-boot:run
```

This will start the application with the H2 in-memory database configuration.

### Access H2 Console

Once the application is running, you can access the H2 console at:

`http://localhost:8080/h2-console` (assuming your application runs on port 8080)

Use the following credentials:
- **JDBC URL:** `jdbc:h2:mem:testdb`
- **Username:** `sa`
- **Password:** (leave empty)

## Project Structure
