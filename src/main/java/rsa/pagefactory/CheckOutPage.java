package rsa.pagefactory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import rsa.reusablecomponents.ReusableFunctions;

public class CheckOutPage extends ReusableFunctions {

	WebDriver driver;

	public CheckOutPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	// Add @FindBy locators here
	// @FindBy(id="example")
	// private WebElement exampleElement;

	// Add page methods here
	// public void exampleMethod() {
	// }

}
