package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ProductPage extends BasePage {

	public ProductPage(WebDriver driver) {
		super(driver);
	}

	//private static final By ADD_BUTTON = By.id("add-to-cart-sauce-labs-backpack");
	private static final By CART_BUTTON = By.xpath("//a[@data-test=\"shopping-cart-link\"]");
	private static final By TITLE = By.cssSelector("[data-test = title]");

	private static final String ADD_TO_CART_PATTERN = "//*[text() = '%s']/ancestor::div[@class = 'inventory_item']" +
			"//button";
	private static final String OPEN_PRODUCT_PATTERN = "//div[text()='%s']";
	private static final String OPEN_PRODUCT_FORM = "//div[@class='inventory_details_name large_size']";

	public String getTitle() {
		return driver.findElement(TITLE).getText();
	}

	@Step("Adding item with name {product} to Shopping Cart")
	public void addProduct(String product) {
		driver.findElement(By.xpath(String.format(ADD_TO_CART_PATTERN, product))).click();
	}

	@Step("Open product cart")
	public void openCart() {
		driver.findElement(CART_BUTTON).click();
	}

	public void openProduct(String product) {
		driver.findElement(By.xpath(String.format(OPEN_PRODUCT_PATTERN, product))).click();
	}

	public String productForm() {
		return driver.findElement(By.xpath(OPEN_PRODUCT_FORM)).getText();
	}

}
