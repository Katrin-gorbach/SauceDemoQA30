package tests;

import org.openqa.selenium.By;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class LoginTest extends BaseTest {

	@Test
	public void checkSuccessLogin() {
		loginPage.open();
		loginPage.login("standard_user", "secret_sauce");
		assertEquals(productPage.getTitle(), "Products",
				"Логин не выполнен");
	}

	@Test
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

	@Test
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
}
