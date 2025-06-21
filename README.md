# JavaAssessment

# 📚 Book Management API (Spring Boot)

A RESTful Spring Boot application for managing books and their authors. This project demonstrates CRUD operations, service layer abstraction, external API calling using `RestTemplate`, and unit testing using JUnit 5 and Mockito.

---

## 🚀 Features

- Create, Update, Delete, Get single or all books
- Fetch paginated books
- Call an external API to retrieve author info
- Unit tests for service layer using Mockito and JUnit
- RESTful API design

---

## 🛠️ Technologies Used

- Java 17+
- Spring Boot
- Spring Data JPA
- H2 / MySQL (your choice)
- Lombok
- RestTemplate
- JUnit 5 & Mockito
- Maven

---

## 📦 Project Structure

src
├── main
│ ├── java
│ │ └── com.java.assessment
│ │ ├── controller # REST Controllers
│ │ ├── entity # JPA Entities (Book, Author)
│ │ ├── repository # Spring Data JPA Repositories
│ │ ├── service # Services and interfaces
│ │ └── component # Supporting components (e.g., checkExisting)
│ └── resources
│ └── application.properties # DB config
├── test
│ └── java
│ └── com.java.assessment
│ └── service # Unit tests for BookService

yaml
Copy
Edit

---

## 📖 API Endpoints

| Method | Endpoint                                    | Description                    |
|--------|---------------------------------------------|--------------------------------|
| GET    | `/api/books`                                | Get all books                  |
| GET    | `/api/books/page?page=0`                    | Get paginated books (page size 10) |
| GET    | `/api/books/selectedbyid?Id=1`              | Get book by ID                 |
| POST   | `/api/books`                                | Create a book                  |
| PUT    | `/api/books`                                | Update a book                  |
| DELETE | `/api/books?Id=1`                           | Delete a book by ID            |
| GET    | `/api/books/nestedcallinganotherapifrom3rdparty?Id=1` | Call another API to get author info |

---

Swager UI

http://localhost:8080/swagger-ui/index.html

---

## 🧪 Running Tests

```bash
mvn test