package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CartPage extends BasePage {

	public CartPage(WebDriver driver) {
		super(driver);
	}

	private static final By CONTINUE_SHOPPING_BUTTON = By.xpath("//button[text()='Continue Shopping']");
	private static final String OPEN_PRODUCT_FROM_CART_PATTERN = "//div[@class='inventory_item_name'][text()='%s']";
	private static final By CHECKOUT_BUTTON = By.id("checkout");

	public void checkoutButton() {
		driver.findElement(CHECKOUT_BUTTON).click();
	}

	public void open() {
		driver.get(BASE_URL + "/cart.html");
	}

	public void continueShopping() {
		driver.findElement(CONTINUE_SHOPPING_BUTTON).click();
	}

	public void productInCart(String product) {
		driver.findElement(By.xpath(String.format(OPEN_PRODUCT_FROM_CART_PATTERN, product))).click();
	}
}
