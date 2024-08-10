import { BasePage } from './BasePage';

export class ProductPage extends BasePage {
    async open() {
        await super.open('/index.php?id_product=1&controller=product'); // Replace with actual product URL
      }
  async addToCart() {
    await this.click('button[name="Submit"]');
  }
}
