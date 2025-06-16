package tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.annotations.*;
import org.testng.asserts.SoftAssert;
import pages.*;

import java.time.Duration;

import static tests.AllureUtils.takeScreenshot;

@Listeners(TestListener.class)
public class BaseTest {
	WebDriver driver;
	SoftAssert softAssert;
	LoginPage loginPage;
	ProductPage productPage;
	BurgerMenuPage burgerMenuPage;
	ChromeOptions options;
	CartPage cartPage;
	CheckoutPage checkoutPage;
	String user = System.getProperty("user", PropertyReader.getProperty("user"));
	String password = System.getProperty("password", PropertyReader.getProperty("password"));

	@Parameters({"browser"})
	@BeforeMethod(alwaysRun = true, description = "Opening browser")
	public void setup(@Optional("chrome") String browser, ITestContext context) {
		if (browser.equalsIgnoreCase("chrome")) {
			options = new ChromeOptions();
			options.addArguments("--incognito");
			options.addArguments("--disable-notifications");
			options.addArguments("--disable-popup-blocking");
			options.addArguments("--disable-infobars");
			options.addArguments("--headless");

			driver = new ChromeDriver(options);
		} else if (browser.equalsIgnoreCase("edge")) {
			EdgeOptions options = new EdgeOptions();
			options.addArguments("--headless");

			driver = new EdgeDriver(options);
		}

		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().window().maximize();

		context.setAttribute("driver", driver);

		checkoutPage = new CheckoutPage(driver);
		softAssert = new SoftAssert();
		loginPage = new LoginPage(driver);
		productPage = new ProductPage(driver);
		burgerMenuPage = new BurgerMenuPage(driver);
		cartPage = new CartPage(driver);

		System.out.println(user);
		System.out.println(password);

	}

	@AfterMethod(alwaysRun = true, description = "Closing browser")
	public void tearDown(ITestResult result) {
		if (ITestResult.FAILURE == result.getStatus()) {
			takeScreenshot(driver);
		}
		if (driver != null) {
			driver.quit();
		}
	}
}
