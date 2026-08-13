package rsa.pagefactory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import rsa.reusablecomponents.ReusableFunctions;

public class OrderHistoryPage extends ReusableFunctions {

	WebDriver driver;
	public OrderHistoryPage(WebDriver driver) {
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	// Text "Thank You For The Order" element
	@FindBy(css=".hero-primary")
	WebElement confirmationTextElement;
	
	// Return the confirmation text from Order History page
	public String verifyConfirmOrder() {
		String orderConfirmText=confirmationTextElement.getText();
		return orderConfirmText;
	}
	
}
