const { toMatchImageSnapshot } = require('jest-image-snapshot');

async function captureAndMatchSnapshot(element, testName) {
  const screenshot = await element.screenshot();
  expect(screenshot).toMatchImageSnapshot({ customSnapshotIdentifier: testName });
}

module.exports = { captureAndMatchSnapshot };
