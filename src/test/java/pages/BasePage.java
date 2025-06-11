package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.time.Duration;
import java.util.List;

import static org.testng.Assert.assertEquals;

public abstract class BasePage {

	WebDriver driver;
	public static final String BASE_URL = "https://www.saucedemo.com/";

	public BasePage(WebDriver driver) {
		this.driver = driver;
	}

	public void isElementPresent(By locator) {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(0));
		List<WebElement> numberOfElements = driver.findElements(locator);
		assertEquals(numberOfElements.size(), 0);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	}
}
