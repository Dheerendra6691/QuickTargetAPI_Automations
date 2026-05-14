# EHES API Automation Framework

## Overview

The EHES API Automation Framework is a production-grade test automation solution designed for validating enterprise backend services, REST APIs, authentication workflows, and business-critical microservices.

This framework has been engineered using enterprise automation principles with a strong focus on:

- Scalability
- Maintainability
- Reusability
- Parallel execution
- Observability
- CI/CD readiness

The framework currently validates authentication services, request-on-demand workflows, and API contract behavior, while being architected for future integration with messaging systems, databases, and distributed services.

---

## Key Features

### API Validation

- REST API functional validation
- Authentication and authorization testing
- Request/response contract validation
- Status code verification
- Schema validation
- Negative scenario validation
- Error handling verification

### Enterprise Automation Features

- Parallel test execution
- Data-driven testing
- Modular architecture
- Environment-based configuration
- Custom listeners
- Centralized assertions
- Reusable API clients
- Failure diagnostics
- Execution logging

### Reporting and Observability

- Allure reporting integration
- Request and response traceability
- Detailed execution logs
- Test metadata tracking
- Failure analysis support

---

## Technology Stack

### Core Technologies

- Java 17
- Maven
- TestNG
- Rest Assured

### Reporting

- Allure Reports

### Supporting Libraries

- Jackson
- Lombok
- Apache Commons

### Planned Integrations

- RabbitMQ
- Kafka
- Redis
- MQTT
- PostgreSQL
- Docker
- Jenkins
- GitHub Actions

---

## System Prerequisites

Ensure the following tools are installed before running the framework:

| Component | Version |
|-----------|---------|
| Java | 17+ |
| Maven | 3.9+ |
| Python | 3.10+ |
| Git | Latest |
| Allure CLI | Latest |

Verify installation:

```bash
java -version
mvn -version
python --version
```

---

## Local Application Setup

This framework validates APIs exposed by a locally running backend service.

The backend application must be running before executing automation suites.

### Start Backend Application

Execute the following command:

```bash id="eq71ak"
python -m uvicorn app.main:app --reload
```

### Application Endpoints

| Service | URL |
|---------|-----|
| Application | http://127.0.0.1:8000 |
| Swagger Documentation | http://127.0.0.1:8000/docs |
| Health Check | http://127.0.0.1:8000/health |

---

## Project Setup

### Clone Repository

```bash
git clone <repository-url>
cd api_automations
```

### Install Dependencies

```bash id="91gxsk"
mvn clean install -DskipTests
```

---

## Project Structure

```text id="s0rlst"
api_automations
│
├── src
│   ├── main
│   │   └── java
│   │       └── com/restassured
│   │           ├── client
│   │           ├── config
│   │           ├── constants
│   │           ├── models
│   │           └── utils
│   │
│   └── test
│       └── java
│           └── com/restassured
│               ├── tests
│               ├── listeners
│               ├── assertions
│               └── dataproviders
│
├── testng.xml
├── pom.xml
└── README.md
```

---

## Test Execution

### Execute Complete Test Suite

```bash id="zyjlwm"
mvn clean test
```

### Execute TestNG Suite

```bash id="w8jxw7"
mvn clean test -DsuiteXmlFile=testng.xml
```

### Execute Individual Test Class

```bash id="x04v0w"
mvn test -Dtest=TokenApiTest
```

### Execute Individual Test Method

```bash id="x1zrxs"
mvn test -Dtest=TokenApiTest#verifyTokenGeneration
```

---

## Reporting

### Generate Allure Report

Execute:

```bash id="j6x1ts"
allure serve target/allure-results
```

The report includes:

- Execution summary
- Test history
- Failure diagnostics
- Request/response details
- Execution trends

---

## Current Test Coverage

### Authentication Module

#### Token API Validation

- Valid credential validation
- Invalid username validation
- Invalid password validation
- Empty username validation
- Empty password validation
- Empty payload validation

### Request On Demand Module

#### Business Workflow Validation

- Valid request submission
- Invalid request validation
- Authorization validation
- Response contract validation
- Error handling validation

---

## Framework Design Principles

### Scalability

Designed to support large enterprise automation suites with modular expansion.

### Maintainability

Reusable components and abstraction layers reduce duplication and improve long-term maintainability.

### Reliability

Deterministic assertions, logging, and diagnostics ensure consistent execution.

### Extensibility

Supports seamless integration with distributed systems, databases, and event-driven architectures.

### CI/CD Readiness

Optimized for Jenkins, GitHub Actions, Docker, and distributed execution pipelines.

---

## Future Enhancements

### Planned Capabilities

- Database validation layer
- RabbitMQ message validation
- Kafka event validation
- MQTT workflow automation
- Dockerized execution
- Distributed test execution
- Contract testing
- Performance validation

---

## Author

### Dheerendra Vishwakarma

Senior QA Automation Engineer

Specializations:

- API Automation
- Backend Validation
- Messaging Systems
- Distributed Systems Testing
- Enterprise Test Architecture
- CI/CD Quality Engineering
