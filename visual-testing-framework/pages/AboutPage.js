const BasePage = require('./BasePage');
const { baseUrl } = require('../config/config');

class AboutPage extends BasePage {
  constructor(page) {
    super(page);
    this.url = `${baseUrl}/about`;
    this.selectors = {
      header: '//header',
      teamSection: '//*[@id="team"]',
      visionSection: '//*[@id="vision"]',
    };
  }

  async navigateToAbout() {
    await this.navigate(this.url);
  }

  async getTeamSection() {
    const [teamSection] = await this.getElementByXPath(this.selectors.teamSection);
    return teamSection;
  }
}

module.exports = AboutPage;
