# Spring Boot Microservices Demo: Quiz Application

This repository contains a demo application implementing a **Quiz Management System** using **Spring Boot Microservices Architecture**. It consists of the following components:

---

## Microservices Overview

### 1. **Question Service**
Manages the quiz questions and provides endpoints for adding, retrieving, and managing questions. It also supports quiz score calculation.

**Key Endpoints:**
- `GET /question/allQuestions`: Get all questions.
- `GET /question/category/{category}`: Fetch questions by category.
- `POST /question/add`: Add a new question.
- `POST /question/addMultiple`: Add multiple questions at once.
- `GET /question/generate`: Generate a set of question IDs for a quiz.
- `POST /question/getQuestions`: Fetch questions by their IDs.
- `POST /question/getScore`: Calculate the score for a submitted quiz.

---

### 2. **Quiz Service**
Manages quiz creation and submission. Communicates with the **Question Service** using **Feign Clients** for question retrieval and scoring.

**Key Endpoints:**
- `POST /quiz/create`: Create a new quiz by specifying category and number of questions.
- `GET /quiz/get/{id}`: Fetch questions for a specific quiz.
- `POST /quiz/submit/{id}`: Submit a quiz and calculate the score.

---

### 3. **API Gateway**
Acts as a single entry point for all services, implementing routing and load balancing using **Spring Cloud Gateway**.

**Configuration Highlights:**
- Dynamically routes requests based on **Eureka service discovery**.
- Service names are converted to lowercase.

---

### 4. **Service Registry**
Implements **Eureka Server** for service discovery, allowing dynamic registration and discovery of microservices.

**Configuration:**
- **Port:** 8761
- Hosts service metadata for routing through the API Gateway.

---

## Technology Stack
- **Spring Boot**: Core framework for microservices.
- **Spring Cloud Netflix Eureka**: Service registry and discovery.
- **Spring Cloud Gateway**: API Gateway implementation.
- **Spring Data JPA**: Data persistence layer.
- **Feign Client**: Simplifies inter-service communication.
- **H2 Database**: In-memory database for testing.

---

## How to Run the Application

### 1. Clone the Repository:
```bash
git clone https://github.com/Fetulhak/Spring-Boot-Microservices-Demo.git
cd Spring-Boot-Microservices-Demo
