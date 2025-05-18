package tests;

import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class CartTest extends BaseTest {

	@Test
	public void checkShoppingCartItemValues() {
		loginPage.open();
		loginPage.login("standard_user", "secret_sauce");
		productPage.addProduct("Sauce Labs Backpack");
		productPage.openCart();
	}

	@Test
	public void checkContinueShopping() {
		loginPage.open();
		loginPage.login("standard_user", "secret_sauce");
		productPage.openCart();
		cartPage.continueShopping();
		assertEquals(productPage.getTitle(), "Products",
				"Not Main Page");
	}

	@Test
	public void openProductItemFromCart() {
		loginPage.open();
		loginPage.login("standard_user", "secret_sauce");
		productPage.addProduct("Sauce Labs Backpack");
		productPage.openCart();
		cartPage.productInCart("Sauce Labs Backpack");
		// Добавляем паузу 3 секунды, чтобы успеть увидеть, что происходит
		//Thread.sleep(3000);
		assertEquals(productPage.productForm(), "Sauce Labs Backpack", "Incorrect Product");
	}
}
