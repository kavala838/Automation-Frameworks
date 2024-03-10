#Visual Regression Testing Framework

This project is a visual regression testing framework built with Puppeteer and Jest. It captures screenshots of pages and elements on a website, then compares them to stored reference images to detect visual changes.

##Features
- **Screenshot Comparison** : Captures and validates screenshots against reference images to detect changes.
- **Page Object Model** : Organizes code by page, with each page having its own selectors and actions.
- **Configuration Management** : Centralizes settings like URLs, viewport dimensions, etc.
- **Clean Code Structure** : Ensures modular, maintainable, and reusable code.

##Project Structure


visual-regression-framework/
├── config/               - Configuration settings for the framework.
├── pages/                - Page objects, with selectors and actions for each page.
├── tests/                - Test suite that performs visual regression checks.
├── utils/                - Utility functions for handling screenshots and comparisons.
├── __image_snapshots__/  - Directory for storing reference images.
├── jest.config.js        - Jest configuration file.
└── jest.setup.js         - Jest setup file for image snapshot matching.

##Getting Started
####1.Installation
Clone the repository:
`git clone https://github.com/yourusername/visual-regression-framework.git`
####2.Install dependencies:
`npm install`
###Configuration
- Open config/config.js and set the baseUrl and viewport dimensions as needed for the target website.
##Running Tests
- To Capture Reference Screenshots (first-time setup):
`npx jest --updateSnapshot`
- To Run Visual Regression Tests:

`npx jest`
##Adding New Tests
- Add a New Page Object: Create a new file in the pages/ folder, adding page-specific selectors and actions for the new page.

- Write a Test: In tests/visualTests.test.js, add tests for the new page by capturing and comparing screenshots of elements.