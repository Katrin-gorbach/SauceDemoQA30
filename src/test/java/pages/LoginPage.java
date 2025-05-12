package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage extends BasePage {

	//Объявление переменной, содержащей селектор для поиска элемента на веб-странице.
	// By – это класс из библиотеки Selenium, который используется для поиска элементов на странице
	//USER_NAME_FIELD хранит локатор элемента с ID "user-name"
	private static final By USER_NAME_FIELD = By.id("user-name");
	private static final By PASSWORD_FIELD = By.id("password");
	private static final By LOGIN_BUTTON = By.id("login-button");
	private static final By ERROR_MESSAGE = By.xpath("//h3[@data-test='error']");

	//конструктор класса LoginPage
	public LoginPage(WebDriver driver) {
		super(driver); //вызывает конструктор родительского класса (суперкласса) и передает ему driver
	}

	public void open() {
		driver.get(BASE_URL);
	}

	public void login(String user, String password) {
		driver.findElement(USER_NAME_FIELD).sendKeys(user);
		driver.findElement(PASSWORD_FIELD).sendKeys(password);
		driver.findElement(LOGIN_BUTTON).click();
	}

	public String getErrorMessage() {
		return driver.findElement(ERROR_MESSAGE).getText();
	}
}
