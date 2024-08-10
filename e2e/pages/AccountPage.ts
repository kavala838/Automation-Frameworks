import { BasePage } from './BasePage';

export class AccountPage extends BasePage {
    async open() {
        await super.open('/index.php?controller=my-account'); // User account page URL
      }
  async goToOrderHistory() {
    await this.click('a[title="Orders"]');
  }
}
