package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

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

	public String getProductFromCart(int index) {
		return driver.findElements(By.cssSelector(".inventory_item_number")).get(index).getText();
	}

	public ArrayList<String> getProductsName() {
		List<WebElement> allProductsElements = driver.findElements(By.cssSelector(".inventory_item_number"));
		ArrayList<String> names = new ArrayList<>();
		for (WebElement product : allProductsElements) {
			names.add(product.getText());
		}
		return names;
	}

	@Step("Get {product} price")
	public double getProductPrice(String product) {
		return Double.parseDouble(driver.findElement(
						By.xpath(String.format(
								"//*[text() = '%s']/ancestor::div[@class='cart_item']//" +
										"*[@class = 'inventory_item_price']", product)))
				.getText().replace("$", ""));
	}
}
