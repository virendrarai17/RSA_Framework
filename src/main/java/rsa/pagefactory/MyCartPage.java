package rsa.pagefactory;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import rsa.reusablecomponents.ReusableFunctions;

public class MyCartPage extends ReusableFunctions {

	WebDriver driver;

	public MyCartPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	// List of all products in cart page
	@FindBy(css = ".cartSection h3")
	List<WebElement> cartItem;

	//checkOut button in Cart page
	@FindBy(css = ".totalRow .btn")
	WebElement checkOutBtn;

	// Return list of cart item added in cart page
	public List<WebElement> getCardProduct() {
		List<WebElement> cartItemList = cartItem;
		return cartItemList;
	}

	// Return boolean value if expected product name matches with actual product present on cart page
	public Boolean verifyProductDetails(String productName) {
		Boolean match = getCardProduct().stream()
				.anyMatch(product -> product.getText().equalsIgnoreCase(productName));
		return match;
	}

	// Click on checkOut button in cartPage
	public PaymentPage checkOutFromMyCart() {
		checkOutBtn.click();
		PaymentPage paymentPage = new PaymentPage(driver);
		return paymentPage;
	}
}
