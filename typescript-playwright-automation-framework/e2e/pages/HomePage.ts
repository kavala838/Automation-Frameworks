import { BasePage } from './BasePage';

export class HomePage extends BasePage {

    async open() {
        await super.open('/'); // Homepage URL
      }
      
  async goToSignIn() {
    await this.click('a.login');
  }

  async searchProduct(productName: string) {
    await this.type('input.search_query', productName);
    await this.click('button[name="submit_search"]');
  }
}
