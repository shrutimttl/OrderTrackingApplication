# OrderTrackingApplication

A full-stack order management application using **Spring Boot**, **Apache Kafka**, **MySQL**, and **React.js**. Designed to simulate an order processing pipeline with real-time Kafka-based event messaging between services.

---

## 📌 Features

- Place and track product orders
- Kafka-powered event-driven architecture
- Order creation triggers Kafka messages
- MySQL-backed data persistence
- RESTful API with Swagger documentation
- React frontend with Axios integration
- Bootstrap UI for clean design

---

## 🧰 Tech Stack

### Backend
- Java 17 + Spring Boot
- Spring Data JPA + MySQL
- Apache Kafka
- Spring for Apache Kafka
- Lombok
- Swagger / OpenAPI
- Maven

### Frontend
- React.js (via Create React App)
- Axios
- Bootstrap

### DevOps
- Docker (optional)
- Docker Compose for Kafka + MySQL setup

---

## 📦 Architecture

```text
[ React Frontend ]
        |
    [Axios]
        |
[Spring Boot Backend]
        |
[MySQL] <--- Data Persistence
   |
[Kafka Producer] ---> [Kafka Topic: order-events] ---> [Kafka Consumer]
                                                       (Log event or simulate notification)
