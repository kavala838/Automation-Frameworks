import { test, expect } from '@playwright/test';
import { HomePage } from '../../pages/HomePage';
import { LoginPage } from '../../pages/LoginPage';
import { RegistrationPage } from '../../pages/RegistrationPage';
import { userData } from '../../fixtures/userData';

test.describe('User Registration and Login', () => {
  test('Register with valid and invalid data', async ({ page }) => {
    const homePage = new HomePage(page);
    const registrationPage = new RegistrationPage(page);

    await homePage.open();
    await homePage.goToSignIn();

    // Test valid registration
    await registrationPage.register(userData.validUser);
    expect(await page.isVisible('text=Welcome')).toBeTruthy();

    // Test invalid registration
    await registrationPage.register(userData.invalidUser);
    expect(await page.isVisible('text=Invalid email')).toBeTruthy();
  });

  test('Login with correct and incorrect credentials', async ({ page }) => {
    const homePage = new HomePage(page);
    const loginPage = new LoginPage(page);

    await homePage.open();
    await homePage.goToSignIn();

    // Test correct login
    await loginPage.login(userData.loginUser.email, userData.loginUser.password);
    expect(await page.isVisible('text=My account')).toBeTruthy();

    // Test incorrect login
    await loginPage.login(userData.invalidUser.email, userData.invalidUser.password);
    expect(await page.isVisible('text=Authentication failed')).toBeTruthy();
  });
});
