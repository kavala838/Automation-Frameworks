import { test, expect } from '@playwright/test';
import { ProductPage } from '../../pages/ProductPage';
import { CartPage } from '../../pages/CartPage';
import { cartData } from '../../fixtures/cartData';

test.describe('Shopping Cart Operations', () => {
  for (const data of cartData) {
    test(`${data.description}`, async ({ page }) => {
      const productPage = new ProductPage(page);
      const cartPage = new CartPage(page);

      await productPage.open();
      await productPage.addToCart();

      await cartPage.open();
      expect(await cartPage.isProductInCart('Sample Product')).toBeTruthy();

      // Update quantity and verify
      await cartPage.updateQuantity('Sample Product', data.quantity);
      expect(await cartPage.isQuantityUpdated('Sample Product', data.expectedQuantity)).toBeTruthy();
    });
  }
});
