package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class BurgerMenuPage extends BasePage{

	public BurgerMenuPage(WebDriver driver) {
		super(driver);
	}

	private static final By MENU_BUTTON = By.xpath("//button[text() = 'Open Menu']");
	private static final By LOGOUT_BUTTON = By.id("logout_sidebar_link");
	private static final By AlLITEMS_BUTTON = By.id("inventory_sidebar_link");

	public void openMenu() {
		driver.findElement(MENU_BUTTON).click();
	}

	public void logout() {
		driver.findElement(LOGOUT_BUTTON).click();
	}

	public void items() {
		driver.findElement(AlLITEMS_BUTTON).click();
	}
}
