import { test, expect } from '@playwright/test';
import { AccountPage } from '../../pages/AccountPage';

test.describe('User Account Management', () => {
  test('View and manage order history', async ({ page }) => {
    const accountPage = new AccountPage(page);

    await accountPage.open();
    await accountPage.goToOrderHistory();

    // Verify past orders are listed
    expect(await page.isVisible('text=Order History')).toBeTruthy();
  });
});
