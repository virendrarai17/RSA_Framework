package rsa.reusablecomponents;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ReusableFunctions {

	public WebDriver driver;
	
	// Initialize the driver using constructor
	public ReusableFunctions(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);// All the locators @FindBy initialized with driver
	}

	@FindBy(css = "[routerlink*='cart']")
	WebElement cartButton;

	// Wait until element to visible
	public void waitForElementToAppear(By Findby) {

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOfElementLocated(Findby));
	}
	
	
	public void waitForWebElementToAppear(WebElement Findby) {

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOf(Findby));
	}
	// Wait until Web Element to disappear
	public void waitForElementToDisappear(WebElement ele) throws InterruptedException {
		Thread.sleep(1000);
		// WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(10));
		// wait.until(ExpectedConditions.invisibilityOf(ele));
	}

	// Click on cartButton from cart Header which is common
	public void goToCartPage() {
		cartButton.click();
	}

}
