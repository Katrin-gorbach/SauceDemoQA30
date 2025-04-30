import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginTest extends BaseTest {

	@Test
	public void checkLocator() {
		driver.get("https://www.saucedemo.com/");
		driver.findElement(By.id("user-name")).sendKeys("standard_user");
		driver.findElement(By.id("password")).sendKeys("secret_sauce");
		driver.findElement(By.id("login-button")).click();
		String title = driver.findElement(By.cssSelector(".title")).getText();
		Assert.assertEquals(title, "Products", "Логин не выполнен");

	}

	@Test
	public void checkLocatorInvalidPassword() {
		driver.get("https://www.saucedemo.com/");
		driver.findElement(By.id("user-name")).sendKeys("standard_user");
		driver.findElement(By.id("password")).sendKeys("secret_sauce1");
		driver.findElement(By.id("login-button")).click();
		String error = driver.findElement(By.xpath("//h3[@data-test='error']")).getText();
		Assert.assertEquals(error, "Epic sadface: Username and password do not match any user in this service",
				"Нет сообщения об ошибке");
	}

}
