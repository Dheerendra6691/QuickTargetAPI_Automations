# EHES API Automation Framework

A production-grade API automation framework engineered for enterprise backend validation, microservices testing, and scalable continuous quality delivery.

This framework has been designed with the same architectural principles used in large-scale distributed systems—focusing on maintainability, extensibility, observability, and parallel execution.

It supports authentication workflows, business API validation, data-driven testing, reporting, and is built to integrate seamlessly with CI/CD pipelines.

---

# System Prerequisites

Ensure the following are installed:

- Java 17+
- Maven 3.9+
- Python 3.10+
- Git
- Allure CLI

Verify installation:

```bash
java -version
mvn -version
python --version
Local Application Setup

This automation framework validates APIs exposed by a locally running backend service.

Before executing automation suites, start the application locally.

Start Backend Service
python -m uvicorn app.main:app --reload

Once the server is up:

Application URL:

http://127.0.0.1:8000

Swagger Documentation:

http://127.0.0.1:8000/docs

Health Check:

http://127.0.0.1:8000/health
Framework Capabilities
API Validation
Authentication workflows
Token generation and lifecycle validation
Request-on-demand business flows
Schema validation
Status code validation
Negative scenario validation
Error contract verification
Enterprise Testing Features
Parallel execution
Data-driven execution
Environment-based configuration
Request/response logging
Custom listeners
Failure diagnostics
Retry handling
Centralized assertions
Reporting & Observability
Allure reporting
Execution logs
Request/response traceability
Failure snapshots
Test metadata tracking
Technology Stack
Java 17
TestNG
Rest Assured
Maven
Allure
Jackson
Git

Framework is designed for future integration with:

RabbitMQ
Kafka
Redis
MQTT
PostgreSQL
Docker
Jenkins
GitHub Actions
Project Structure
api_automations
│
├── src
│   ├── main
│   │   └── java
│   │       └── com/restassured
│   │           ├── client
│   │           ├── models
│   │           ├── constants
│   │           ├── config
│   │           └── utils
│   │
│   └── test
│       └── java
│           └── com/restassured
│               ├── tests
│               ├── listeners
│               ├── dataproviders
│               └── assertions
│
├── testng.xml
├── pom.xml
└── README.md
Execution
Execute Complete Test Suite
mvn clean test
Execute Specific Test Suite
mvn clean test -DsuiteXmlFile=testng.xml
Execute Individual Test Class
mvn test -Dtest=TokenApiTest
Reporting

Generate interactive Allure report:

allure serve target/allure-results
Current Test Coverage
Authentication Module
Valid credentials
Invalid credentials
Empty payload validation
Security validation
Token generation verification
Request On Demand Module
Valid request submission
Business payload validation
Unauthorized access validation
Invalid contract handling
Response contract verification
Framework Design Principles

This framework follows:

Scalability

Supports enterprise-level suite expansion with modular service abstraction.

Maintainability

Encapsulated client layers and reusable utilities minimize duplication.

Reliability

Deterministic validations, centralized assertions, and failure diagnostics.

Extensibility

Designed for seamless integration with messaging systems, databases, and distributed workflows.

CI/CD Readiness

Compatible with modern DevOps pipelines and distributed execution agents.

Roadmap

Upcoming integrations:

Database validation layer
Kafka event validation
RabbitMQ message assertions
MQTT workflow testing
Dockerized execution
Distributed test execution
Performance hooks
Contract testing
Author

Dheerendra Vishwakarma

Senior QA Automation Engineer
API Automation | Distributed Systems | Messaging Platforms | Enterprise Quality Engineering
