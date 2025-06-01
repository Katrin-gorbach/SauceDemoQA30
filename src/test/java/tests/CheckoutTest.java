package tests;

import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class CheckoutTest extends BaseTest {

	@Test(description = "Validation your information form", priority = 2, testName = "emptyYourInformation",
			groups = {"regression"})
	public void emptyYourInformation() {
		loginPage.open();
		loginPage.login("standard_user", "secret_sauce");
		productPage.addProduct("Sauce Labs Backpack");
		productPage.openCart();
		cartPage.checkoutButton();
		checkoutPage.yourInformation("", "", "");
		// Добавляем паузу 3 секунды, чтобы успеть увидеть, что происходит
		//Thread.sleep(3000);
		assertEquals(checkoutPage.getErrorMessage(), "Error: First Name is required", "No errors");
	}
}
