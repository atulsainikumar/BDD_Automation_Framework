# QA Automation Assignment Framework

## Overview

This framework is designed for both UI and API automation using modern automation practices and reusable framework architecture.

The framework has been upgraded and enhanced from the provided starter framework to improve:
- Maintainability
- Reusability
- Reporting
- Logging
- Stability
- Scalability

The framework follows:
- Page Object Model (POM)
- BDD using Cucumber
- TestNG execution
- Maven dependency management

---

# Tech Stack

- Java 17
- Selenium 4
- Cucumber 7
- TestNG
- Rest Assured
- Maven
- Extent Reports
- Log4j2

---

# Framework Enhancements Done

## UI Automation
- Login Validation
- Add / Remove Cart Items
- Cart Quantity Validation
- Checkout Flow
- Item Total Validation
- Tax Validation

## API Automation
- GET User API
- LIST Users API
- CREATE User API
- LOGIN API
- Delayed Response API
- Unique User ID Validation

---

# Framework Improvements Implemented

## Modernization
The provided framework was upgraded to support:
- Java 17
- Selenium 4
- Cucumber 7
- Latest Maven dependencies

This was required because some of the older framework dependencies were deprecated or incompatible with modern environments.

---

## Reporting
Integrated:
- Extent Reports
- HTML Reports
- JSON Reports

Provides:
- Better execution visibility
- Scenario level reporting
- Failure tracking
- Screenshot support

---

## Logging
Implemented Log4j2 logging for:
- Browser execution
- API execution
- Validation tracking
- Scenario lifecycle
- Debugging support

---

## Retry Mechanism
Implemented automatic retry mechanism for handling intermittent/flaky failures.

---

## Hook Separation
Implemented separate hooks for:
- UI scenarios
- API scenarios

This avoids unnecessary browser launch during API execution.

---

## Reusable Design
Implemented reusable architecture using:
- BasePage
- Page Object Model
- Reusable API methods
- Centralized utilities

---

# Framework Architecture

Feature Files
↓
Step Definitions
↓
Page Classes / API Classes
↓
Base Utilities
↓
Selenium / Rest Assured

---

# Execution

## Run All Tests

mvn clean test

---

# Tags

- @UI
- @API

---

# Reports

Extent Report:
test-output/ExtentReport/ExtentSpark.html

Logs:
logs/application.log

---

# Notes

- Parallel execution has not been implemented currently to avoid unnecessary framework instability during assignment execution.
- However, the framework can be further enhanced using ThreadLocal WebDriver implementation for scalable parallel execution support if required.

---

# AI Assistance Disclosure

A small amount of AI-assisted guidance was used during framework modernization and dependency upgrades, mainly for migration compatibility and framework enhancement suggestions.

All framework implementation, debugging, execution flow understanding, validations, and final integrations were reviewed, customized, and executed manually.