import { BasePage } from './BasePage';

export class CheckoutPage extends BasePage {
  async open() {
    await super.open('/index.php?controller=order&step=1'); // Navigate to checkout page
  }

  async enterAddress(address: string, city: string, zip: string) {
    // Fill out the address form fields
    await this.type('#address1', address); // Address field selector
    await this.type('#city', city); // City field selector
    await this.type('#postcode', zip); // Zip code field selector

    // Select a state (assuming dropdown)
    await this.page.selectOption('#id_state', '1'); // Replace '1' with the desired state option
    await this.page.selectOption('#id_country', '21'); // Assuming '21' represents a default country option
  }

  async proceedToShipping() {
    await this.click('button[name="processAddress"]'); // Proceed to shipping step
  }

  async selectPaymentMethod(method: string) {
    // Choose payment method based on input
    if (method === 'Credit Card') {
      await this.click('a.bankwire'); // Placeholder selector for bank wire option
    } else if (method === 'Bank Wire') {
      await this.click('a.cheque'); // Placeholder selector for check option
    }
  }

  async confirmOrder() {
    // Agree to terms (if required) and confirm order
    await this.click('#cgv'); // Selector for terms and conditions checkbox
    await this.click('button[name="processCarrier"]'); // Proceed to payment
    await this.click('button.button-medium'); // Final confirmation button
  }
}
