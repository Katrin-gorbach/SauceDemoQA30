package tests;

import org.testng.annotations.Test;

public class CartTest extends BaseTest {
	@Test
	public void checkShoppingCartItemValues() {
		loginPage.open();
		loginPage.login("standard_user", "secret_sauce");
		productPage.addProduct("Sauce Labs Backpack");
		productPage.openCart();
	}
}
