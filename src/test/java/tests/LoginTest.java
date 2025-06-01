package tests;

import org.openqa.selenium.By;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class LoginTest extends BaseTest {

	@Test(groups = {"smoke"}, description = "Validation login", priority = 1, testName = "checkSuccessLogin")
	public void checkSuccessLogin() {
		loginPage.open();
		loginPage.login("standard_user", "secret_sauce");
		assertEquals(productPage.getTitle(), "Products",
				"Логин не выполнен");
	}

	@Test(enabled = false)
	public void checkLoginWithEmptyPassword() {
		loginPage.open();
		loginPage.login("standard_user", "");
		//метод assertEquals импортирован статически из библиотеки JUnit или TestNG,
		// что позволяет использовать его напрямую без указания класса.
		assertEquals(loginPage.getErrorMessage(), "Epic sadface: Password is required",
				"Нет сообщения об ошибке");
	}

	@Test
	public void checkLocator() {
		driver.get("https://www.saucedemo.com/");
		driver.findElement(By.id("user-name")).sendKeys("standard_user");
		driver.findElement(By.id("password")).sendKeys("secret_sauce");
		driver.findElement(By.id("login-button")).click();
		String title = driver.findElement(By.cssSelector(".title")).getText();
		assertEquals(title, "Products", "Логин не выполнен");
	}

	@Test(description = "Invalid password", invocationCount = 5)
	public void checkLocatorInvalidPassword() {
		driver.get("https://www.saucedemo.com/");
		driver.findElement(By.id("user-name")).sendKeys("standard_user");
		driver.findElement(By.id("password")).sendKeys("secret_sauce1");
		driver.findElement(By.id("login-button")).click();
		String error = driver.findElement(By.xpath("//h3[@data-test='error']")).getText();
		assertEquals(error, "Epic sadface: Username and password do not match any user in this " +
						"service",
				"Нет сообщения об ошибке");
	}

	@DataProvider(name = "Negative tests for Login")
	public Object[][] loginData() {
		return new Object[][]{
				{"standard_user", "", "Epic sadface: Password is required"},
				{"standard_user", "123", "Epic sadface: Username and password do not match any user in this service"}
		};
	}

	@Test(dataProvider = "Negative tests for Login")
	public void login(String user, String password, String errorMessage) {
		loginPage.open();
		loginPage.login(user, password);
		assertEquals(loginPage.getErrorMessage(), errorMessage, "!!!");
	}
}
