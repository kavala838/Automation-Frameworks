const BasePage = require('./BasePage');
const { baseUrl } = require('../config/config');

class HomePage extends BasePage {
  constructor(page) {
    super(page);
    this.url = `${baseUrl}/`;
    this.selectors = {
      header: '//header',
      footer: '//footer',
      mainBanner: '#main-banner',
      loginButton: '//*[@id="login-button"]',
    };
  }

  async navigateToHome() {
    await this.navigate(this.url);
  }

  async getHeader() {
    const [header] = await this.getElementByXPath(this.selectors.header);
    return header;
  }

  async getFooter() {
    const [footer] = await this.getElementByXPath(this.selectors.footer);
    return footer;
  }

  async getLoginButton() {
    const [loginButton] = await this.getElementByXPath(this.selectors.loginButton);
    return loginButton;
  }
}

module.exports = HomePage;
