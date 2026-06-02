# ThoughNest

A modern full-stack blogging platform built to help users create, manage, and share content seamlessly across web and mobile platforms.

The project currently follows a modular monorepo architecture and is designed with future scalability in mind, including a planned transition to a microservices-based architecture.

---

## Project Structure

```text
ThoughNest/
│
├── .git/
│
├── backend/
│   └── Spring Boot API
│
├── frontend/
│   ├── web/
│   │   └── Angular Application
│   │
│   └── mobile/
│       └── React Native Application
│
├── docs/
│
├── README.md
└── LICENSE
```

---

## Features

- Secure Authentication & Authorization
- Blog Creation & Management
- Responsive Web Experience
- Cross-Platform Mobile Support
- User Profiles
- Blog Search & Discovery
- RESTful API Architecture
- Scalable Project Structure
- Future Microservices Ready

---

## Technology Stack

### Backend

- Java
- Spring Boot
- Spring Security
- Spring Data JPA
- Hibernate
- Maven
- MySQL

### Frontend (Web)

- Angular
- TypeScript
- RxJS
- Angular Router
- Angular Material

### Frontend (Mobile)

- React Native
- TypeScript
- React Navigation

### Development Tools

- Git
- GitHub
- Postman
- IntelliJ IDEA
- VS Code

---

## Architecture

### Current Architecture

```text
Angular Web
      │
      │
React Native App
      │
      ▼
Spring Boot Monolith API
      │
      ▼
    MySQL
```

### Planned Architecture (Future)

```text
Angular Web
      │
React Native App
      │
      ▼
   API Gateway
      │
 ┌────┼────┬─────┐
 ▼    ▼    ▼     ▼
Auth Blog User Notification
Service Service Service Service
      │
      ▼
 Databases
```

Future migration may include:

- Spring Cloud
- API Gateway
- Service Discovery
- Distributed Configuration
- Docker
- Kubernetes
- Message Queues (Kafka/RabbitMQ)
- CI/CD Pipelines

---

## Getting Started

### Prerequisites

- Java 21+
- Maven
- Node.js
- Angular CLI
- React Native Environment
- MySQL
- Git

---

## Backend Setup

```bash
cd backend
mvn clean install
mvn spring-boot:run
```

---

## Web Setup

```bash
cd frontend/web
npm install
ng serve
```

---

## Mobile Setup

```bash
cd frontend/mobile
npm install

# Android
npx react-native run-android

# iOS
npx react-native run-ios
```

---

## Roadmap

### Version 1

- Authentication
- User Profiles
- Blog CRUD
- Search Functionality
- Mobile Application

### Version 2

- Comments
- Likes & Reactions
- Bookmarks
- Rich Text Editor
- Notifications

### Version 3

- Microservices Migration
- API Gateway
- Docker Support
- Kubernetes Deployment
- Event-Driven Communication
- Advanced Analytics

---

## License

Licensed under the MIT License.

## Author

Mohd Uzair Khan

Full-Stack Developer
