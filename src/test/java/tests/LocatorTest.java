package tests;

import io.qameta.allure.Description;
import org.openqa.selenium.By;
import org.testng.annotations.Test;

public class LocatorTest extends BaseTest {

	@Test
	@Description("Check Locator")
	public void checkLocator() {
		driver.get("https://www.saucedemo.com/");
		driver.findElement(By.id("user-name"));
		driver.findElement(By.name("user-name"));
		driver.findElement(By.className("error-message-container"));
		//driver.findElement(By.tagName());
	}
}
