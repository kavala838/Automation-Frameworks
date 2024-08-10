import { BasePage } from './BasePage';

export class LoginPage extends BasePage {

  async open() {
    await super.open('/index.php?controller=authentication&back=my-account'); // Login page URL
  }
  
  async login(email: string, password: string) {
    await this.type('#email', email);
    await this.type('#passwd', password);
    await this.click('#SubmitLogin');
  }
}
