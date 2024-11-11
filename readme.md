# Automation Practice Website UI Testing Framework

This project is a comprehensive UI testing framework for the Automation Practice Website, built with Playwright and TypeScript. It follows the Page Object Model (POM) and includes various features for robust, production-level testing.

## Table of Contents

- [Project Structure](#project-structure)
- [Features](#features)
- [Prerequisites](#prerequisites)
- [Setup Instructions](#setup-instructions)
- [Project Components](#project-components)
- [Key Commands](#key-commands)
- [Writing a New Test](#writing-a-new-test)
- [Running Data-Driven Tests](#running-data-driven-tests)
- [Reporting](#reporting)
- [Troubleshooting](#troubleshooting)
- [Contributing](#contributing)

## Project Structure

```plaintext
project-root/
├── e2e/
│   ├── tests/               # All test files
│   ├── pages/               # Page Object files
│   ├── fixtures/            # Test data for data-driven testing
│   ├── helpers/             # Helper functions for utility tasks
├── config/
│   ├── playwright.config.ts # Playwright configuration file
├── reports/                 # Directory for storing test reports
├── utils/
│   ├── constants.ts         # Project-wide constants
│   ├── xpaths.ts            # Reusable XPath selectors
├── .env                     # Environment variables
├── Dockerfile               # Docker configuration for containerization
├── docker-compose.yml       # Docker Compose file for managing services
├── package.json             # Node dependencies
├── tsconfig.json            # TypeScript configuration
└── README.md                # Project documentation

## Features
- **Playwright with TypeScript** - A powerful setup for cross-browser testing.
- **Page Object Model (POM)** - Modular design that separates test logic from page interactions.
- **Data-Driven Testing** - Fixtures are used for multiple data sets in a single test.
- **Parallel Testing and Cross-Browser Support** - Run tests concurrently across Chromium, Firefox, and WebKit.
- **Custom HTML Reports** - Detailed HTML reports generated for each test run.
- **Dockerization** - Containerized environment for consistent and isolated testing.
- **CI/CD Support** - Configured for integration with Jenkins.

## Prerequisites

- **Node.js** - Install Node.js (version >= 12).
- **Docker** - Install Docker for containerization (optional, but recommended for isolated runs).

## Setup Instructions
### 1. Install Dependencies
`npm install   `


### 2. Configure Environment Variables
Create a .env file to set environment-specific values. Here’s an example:
BASE_URL=https://automationpractice.com
### 3. Run Tests Locally
To execute tests locally, use the following command:


`npx playwright test`
### 4. Run Tests with Docker
Build and run the tests in Docker:


`docker-compose up --build`
### 5. Running Tests on Jenkins
Set up a Jenkins pipeline using the Jenkinsfile included in the project.
Configure environment variables in Jenkins as needed.
Trigger the pipeline to execute tests within the Jenkins environment.
## Project Components
### 1. Pages (Page Objects)
The e2e/pages/ directory contains page classes that abstract interactions for each page on the site. Key methods include:

open(): Navigates to the specific page.
isProductInCart(): Verifies if a product is in the cart.
updateQuantity(): Modifies product quantity in the cart.
confirmOrder(): Completes an order.
### 2. Tests
The e2e/tests/ directory contains organized test suites that cover major functionalities:

registration_login.spec.ts: User registration and login.
product_search_navigation.spec.ts: Product search and navigation.
shopping_cart.spec.ts: Shopping cart operations.
checkout.spec.ts: Checkout process.
account_management.spec.ts: User account management.
Each test uses fixtures for data-driven testing, ensuring thorough coverage with multiple data sets.

### 3. Fixtures
Fixtures are located in e2e/fixtures/ and store reusable data sets, such as:

registrationData.ts: Registration test data.
loginData.ts: Login credentials.
searchKeywords.ts: Keywords for search tests.
checkoutData.ts: Address and payment details for checkout.
Fixtures enable data-driven testing, allowing the same test to run with different inputs.

### 4. Helpers
Helper functions, such as isElementVisible, are located in e2e/helpers/ and simplify repetitive tasks across tests.

### 5. Configuration
The Playwright configuration file, playwright.config.ts, enables parallel testing, cross-browser support, and custom HTML reports.

## Key Commands
Run Tests:` npx playwright test`
Run Tests in Headed Mode: `npx playwright test --headed`
Run Tests with Specific Project (e.g., Firefox): `npx playwright test --project=firefox`
View HTML Report: `npx playwright show-report`
## Writing a New Test
Create a new .spec.ts file in the e2e/tests/ directory.
Import necessary page objects and fixtures.
Use page object methods to interact with the application and expect statements to verify outcomes.
## Running Data-Driven Tests
Fixtures allow tests to run with multiple data sets. Define test data in fixtures and import them into the test files to iterate over multiple data sets for thorough testing.

## Reporting
HTML reports are generated in the reports/ directory. To view the report, run:


`npx playwright show-report`

## Contributing
Fork the repository.
Create a new branch (git checkout -b feature/new-feature).
Commit changes (git commit -m 'Add new feature').
Push to the branch (git push origin feature/new-feature).
Open a pull request.