package tests;

import org.openqa.selenium.WebDriver;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
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
	String use = System.getProperty("user");
	String password = System.getProperty("secret_sauce");

	@Parameters({"browser"})
	@BeforeMethod (alwaysRun = true, description = "Opening browser")
	public void setup(@Optional("chrome") String browser, ITestContext context) {
		if(browser.equals("chrome")){
		options = new ChromeOptions();
		options.addArguments("--incognito");
		driver = new ChromeDriver(options);
		options.addArguments("--disable-notifications");
		options.addArguments("--disable-popup-blocking");
		options.addArguments("--disable-infobars");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().window().maximize();
		} else if (browser.equals("edge")) {
			driver = new EdgeDriver();
		}
		context.setAttribute("driver", driver);
		checkoutPage = new CheckoutPage(driver);
		softAssert = new SoftAssert();
		loginPage = new LoginPage(driver);
		productPage = new ProductPage(driver);
		burgerMenuPage = new BurgerMenuPage(driver);
		cartPage = new CartPage(driver);
	}

	@AfterMethod(alwaysRun = true, description = "Closing browser")
	public void tearDown(ITestResult result) {
		if(ITestResult.FAILURE == result.getStatus()){
			takeScreenshot(driver);
		}
		driver.quit();
	}
}
