package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CheckoutPage extends BasePage {

	public CheckoutPage(WebDriver driver) {
		super(driver);
	}

	private static final By CONTINUE_BUTTON = By.id("continue");
	private static final By FIRST_NAME_FIELD = By.id("first-name");
	private static final By LAST_NAME_FIELD = By.id("last-name");
	private static final By ZIP_FIELD = By.id("postal-code");
	private static final By CHECkOUT_ERROR_MESSAGE = By.xpath("//h3[@data-test='error']");

	public void yourInformation(String firstName, String lastName, String zip) {
		driver.findElement(FIRST_NAME_FIELD).sendKeys(firstName);
		driver.findElement(LAST_NAME_FIELD).sendKeys(lastName);
		driver.findElement(ZIP_FIELD).click();
		driver.findElement(CONTINUE_BUTTON).click();
	}

	public String getErrorMessage() {
		return driver.findElement(CHECkOUT_ERROR_MESSAGE).getText();
	}
}
