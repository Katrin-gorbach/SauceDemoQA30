package tests;

import io.qameta.allure.Description;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class CheckoutTest extends BaseTest {

	@DataProvider(name = "Negative tests for Information")
	public Object[][] loginData() {
		return new Object[][]{
				{"", "", "", "Error: First Name is required"},
				{"", "", "4563", "Error: First Name is required"},
				{"Katsiaryna", "", "", "Error: Last Name is required"},
				{"Katsiaryna", "Horbach", "", "Error: Postal Code is required"}
		};
	}

	@Test(dataProvider = "Negative tests for Information", description = "Validation your information form",
			priority = 2, testName = "emptyYourInformation", groups = {"regression"})
	@Description("Validation your information form")
	public void emptyYourInformation(String firstName, String lastName, String zip, String errorMessage) {
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
