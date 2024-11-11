const puppeteer = require('puppeteer');
const HomePage = require('../pages/HomePage');
const AboutPage = require('../pages/AboutPage');
const { captureAndMatchSnapshot } = require('../utils/screenshotUtils');

describe('Visual Regression Framework Tests', () => {
  let browser;
  let page;
  let homePage;
  let aboutPage;

  beforeAll(async () => {
    browser = await puppeteer.launch();
    page = await browser.newPage();
    await page.setViewport({ width: 1280, height: 800 });
    homePage = new HomePage(page);
    aboutPage = new AboutPage(page);
  });

  afterAll(async () => {
    await browser.close();
  });

  test('Home Page - Header Snapshot', async () => {
    await homePage.navigateToHome();
    const header = await homePage.getHeader();
    await captureAndMatchSnapshot(header, 'home-page-header');
  });

  test('Home Page - Footer Snapshot', async () => {
    await homePage.navigateToHome();
    const footer = await homePage.getFooter();
    await captureAndMatchSnapshot(footer, 'home-page-footer');
  });

  test('Home Page - Login Button Snapshot', async () => {
    await homePage.navigateToHome();
    const loginButton = await homePage.getLoginButton();
    await captureAndMatchSnapshot(loginButton, 'home-page-login-button');
  });

  test('About Page - Team Section Snapshot', async () => {
    await aboutPage.navigateToAbout();
    const teamSection = await aboutPage.getTeamSection();
    await captureAndMatchSnapshot(teamSection, 'about-page-team-section');
  });
});
