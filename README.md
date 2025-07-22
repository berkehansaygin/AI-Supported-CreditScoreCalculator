# AI-Supported - CreditScoreCalculator  
**Empowering Smarter Credit Decisions Instantly**

---

## 📑 Table of Contents
- [Overview]
- [Why AISupported-CreditScoreCalculator?]
- [Getting Started]
  - [Prerequisites]
  - [Installation]
  - [Usage]
  - [Testing]

---

## 🧠 Overview

**AI-Supported-CreditScoreCalculator** is a developer-focused framework that facilitates building scalable, data-driven credit scoring and loan approval systems.  
It integrates seamlessly with Spring Boot, offering robust data access, feature engineering, and machine learning prediction capabilities to automate and enhance lending workflows.

---

## ❓ Why AI-Supported-CreditScoreCalculator?

This project empowers developers to create efficient, maintainable credit evaluation applications. Key features include:

- **Data Access Layer**  
  Repositories for managing `Person`, `Collateral`, `CreditHistory`, and `LoanApplication` entities with efficient database operations.

- **Machine Learning Integration**  
  Support for predictive models used in credit risk assessment.

- **RESTful APIs**  
  Endpoints for interacting with loan and credit data, suitable for client-side integration.

- **Feature Engineering**  
  Transforms user inputs and raw data into meaningful features for accurate credit score evaluation.

- **Data Transformation**  
  Uses MapStruct to ensure consistent mapping between internal models and external DTOs.

---

## 🚀 Getting Started

### ✅ Prerequisites

- Java 17+
- Maven 3.8+
- 

### 📥 Installation

git clone https://github.com/berkehansaygin/AISupported-CreditScoreCalculator
cd AISupported-CreditScoreCalculator
mvn install

▶️ Usage
To run the project:
mvn exec:java
Ensure that your Spring Boot configuration (e.g. application.properties or application.yml) is correctly set.

🧪 Testing
To run tests:
mvn test
