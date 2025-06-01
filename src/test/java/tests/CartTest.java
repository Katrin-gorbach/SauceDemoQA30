package tests;

import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class CartTest extends BaseTest {

	@Test(description = "Validation shopping cart value", priority = 1, testName = "checkShoppingCartItemValues",
			groups = "smoke")
	public void checkShoppingCartItemValues() {
		loginPage.open();
		loginPage.login("standard_user", "secret_sauce");
		productPage.addProduct("Sauce Labs Backpack");
		productPage.openCart();
		assertEquals(cartPage.getProductFromCart(0), "Sauce Labs Backpack", "SO BAD");
	}

	@Test(retryAnalyzer = Retry.class, description = "Validation continue button", priority = 1,
			testName = "checkContinueShopping", groups = {"regression"})
	public void checkContinueShopping() {
		loginPage.open();
		loginPage.login("standard_user", "secret_sauce");
		productPage.openCart();
		cartPage.continueShopping();
		assertEquals(productPage.getTitle(), "Products",
				"Not Main Page");
	}

	@Test(description = "Validation item form", priority = 2, testName = "openProductItemFromCart", groups = {"regression"})
	public void openProductItemFromCart() {
		loginPage.open();
		loginPage.login("standard_user", "secret_sauce");
		productPage.addProduct("Sauce Labs Backpack");
		productPage.openCart();
		cartPage.productInCart("Sauce Labs Backpack");
		// Добавляем паузу 3 секунды, чтобы успеть увидеть, что происходит
		//Thread.sleep(3000);
		assertEquals(productPage.productForm(), "Sauce Labs Backpack", "Incorrect Product");
		assertTrue(cartPage.getProductsName().contains("Sauce Labs Backpack"));
		assertEquals(cartPage.getProductPrice("Sauce Labs Backpack"), 29.9);
	}


}
