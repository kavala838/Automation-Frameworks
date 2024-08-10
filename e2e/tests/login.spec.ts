import { test, expect } from '@playwright/test';
import { LoginPage } from '../pages/LoginPage';

test('User can login successfully', async ({ page }) => {
  const loginPage = new LoginPage(page);

  await page.goto('/login');
  await loginPage.login('testUser', 'password123');
  
  expect(await page.isVisible('text=Welcome')).toBeTruthy();
});
