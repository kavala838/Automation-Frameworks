import { BasePage } from './BasePage';

export class CartPage extends BasePage {
  async open() {
    await super.open('/index.php?controller=order'); // Cart page URL
  }

  async isProductInCart(productName: string): Promise<boolean> {
    // Checks if the product is present in the cart by finding the product name
    const productSelector = `//td[@class="cart_description"]//a[contains(text(), "${productName}")]`;
    return await this.isVisible(productSelector);
  }

  async updateQuantity(productName: string, quantity: number) {
    // Locate the quantity input based on the product name and update its value
    const quantityInputSelector = `//td[@class="cart_description"]//a[contains(text(), "${productName}")]/ancestor::tr//input[@type="text" and contains(@class, "cart_quantity_input")]`;
    await this.page.fill(quantityInputSelector, quantity.toString());
    await this.page.keyboard.press('Enter'); // Submit the change, if necessary
  }

  async isQuantityUpdated(productName: string, expectedQuantity: number): Promise<boolean> {
    // Verify the quantity of the specified product in the cart
    const quantitySelector = `//td[@class="cart_description"]//a[contains(text(), "${productName}")]/ancestor::tr//input[@type="text" and contains(@class, "cart_quantity_input")]`;
    const quantityValue = await this.page.inputValue(quantitySelector);
    return parseInt(quantityValue, 10) === expectedQuantity;
  }
}
