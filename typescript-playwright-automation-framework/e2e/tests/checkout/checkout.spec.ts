import { test, expect } from '@playwright/test';
import { CheckoutPage } from '../../pages/CheckoutPage';

test.describe('Checkout Process', () => {
  test('Complete order with address and payment details', async ({ page }) => {
    const checkoutPage = new CheckoutPage(page);

    await checkoutPage.open();
    await checkoutPage.enterAddress('123 Test St', 'City', '12345');
    await checkoutPage.selectPaymentMethod('Credit Card');
    await checkoutPage.confirmOrder();

    expect(await page.isVisible('text=Order confirmation')).toBeTruthy();
  });
});
