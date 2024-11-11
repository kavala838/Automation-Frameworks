import { test, expect } from '@playwright/test';
import { HomePage } from '../../pages/HomePage';
import { ProductPage } from '../../pages/ProductPage';
import { productData } from '../../fixtures/productData';

test.describe('Product Search and Navigation', () => {
  test('Search for products with valid and invalid keywords', async ({ page }) => {
    const homePage = new HomePage(page);
    
    await homePage.open();
    
    // Valid search
    await homePage.searchProduct(productData.searchKeyword);
    expect(await page.isVisible('text=Search results')).toBeTruthy();

    // Invalid search
    await homePage.searchProduct(productData.invalidSearchKeyword);
    expect(await page.isVisible('text=No results found')).toBeTruthy();
  });
});
