package tests;

import org.testng.annotations.Test;
import pages.BasePage;

import static org.testng.Assert.assertEquals;

public class BurgerMenuTest extends BaseTest {

	@Test
	public void checkLogout() {
		loginPage.open();
		loginPage.login("standard_user", "secret_sauce");
		burgerMenuPage.openMenu();
		// Добавляем паузу 3 секунды, чтобы успеть увидеть, что происходит
		//Thread.sleep(3000);
		burgerMenuPage.logout();
		assertEquals(driver.getCurrentUrl(), BasePage.BASE_URL, "Still in system");
	}

	@Test
	public void checkAllItemsFromProductCart() {
		loginPage.open();
		loginPage.login("standard_user", "secret_sauce");
		productPage.openProduct("Sauce Labs Fleece Jacket");
		burgerMenuPage.openMenu();
		burgerMenuPage.items();
		// Добавляем паузу 3 секунды, чтобы успеть увидеть, что происходит
		//Thread.sleep(3000);
		assertEquals(driver.getCurrentUrl(), BasePage.BASE_URL + "inventory.html", "Not Main Page");
	}
}
