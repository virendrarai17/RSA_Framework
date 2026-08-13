package rsa.pagefactory;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;

import rsa.reusablecomponents.ReusableFunctions;

public class PaymentPage extends ReusableFunctions {

	WebDriver driver;

	public PaymentPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
	}

	@FindBy(css = "[placeholder='Select Country']")
	WebElement selectCountryDropDown;

	@FindBy(css = ".ta-item:nth-of-type(2)")
	WebElement suggestedCountry;

	@FindBy(css = ".action__submit")
	WebElement placeOrderBtn;

	By autoSuggestBox = By.cssSelector(".ta-results");

	// Selecting country from dropdown in paymentpage
	public void selectCountry(String countryText) {
		Actions act = new Actions(driver);
		act.sendKeys(selectCountryDropDown, countryText).build().perform();

		waitForElementToAppear(autoSuggestBox);

		suggestedCountry.click();
	}

	// click on placeOrder button and it navigate to OrderHistoryPage
	public OrderHistoryPage placeOrder() {
		placeOrderBtn.click();
		return new OrderHistoryPage(driver);

	}

}
