module.exports = {
    preset: 'jest-puppeteer',
    setupFilesAfterEnv: ['./jest.setup.js'],
    testMatch: ['**/tests/**/*.test.js'],
    testTimeout: 30000,
  };
  