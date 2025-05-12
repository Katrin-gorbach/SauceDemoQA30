package tests;

import org.openqa.selenium.WebDriver;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.asserts.SoftAssert;
import pages.*;

import java.time.Duration;

public class BaseTest {
	WebDriver driver;
	SoftAssert softAssert;
	LoginPage loginPage;
	ProductPage productPage;
	BurgerMenuPage burgerMenuPage;
	ChromeOptions options;
	CartPage cartPage;
	CheckoutPage checkoutPage;

	@BeforeMethod
	public void setup() {
		options = new ChromeOptions();
		options.addArguments("--incognito");
		driver = new ChromeDriver(options);
		options.addArguments("--disable-notifications");
		options.addArguments("--disable-popup-blocking");
		options.addArguments("--disable-infobars");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().window().maximize();
		softAssert = new SoftAssert();
		loginPage = new LoginPage(driver);
		productPage = new ProductPage(driver);
		burgerMenuPage = new BurgerMenuPage(driver);
		cartPage = new CartPage(driver);
		checkoutPage = new CheckoutPage(driver);
	}

	@AfterMethod(alwaysRun = true)
	public void tearDown() {
		driver.quit();
	}
}
