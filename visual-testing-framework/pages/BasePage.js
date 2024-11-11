class BasePage {
    constructor(page) {
      this.page = page;
    }
  
    async navigate(url) {
      await this.page.goto(url, { waitUntil: 'networkidle2' });
    }
  
    async getElementByXPath(xpath) {
      return await this.page.$x(xpath);
    }
  
    async takeElementScreenshot(element, filePath) {
      await element.screenshot({ path: filePath });
    }
  }
  
  module.exports = BasePage;
  