import { BasePage } from './BasePage';

export class RegistrationPage extends BasePage {
  async register(userData: any) {
    await this.type('#customer_firstname', userData.firstName);
    await this.type('#customer_lastname', userData.lastName);
    await this.type('#passwd', userData.password);
    // Fill other required fields
    await this.click('#submitAccount');
  }
}
