package rsa.pagefactory;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import rsa.reusablecomponents.ReusableFunctions;
public class LoginPage extends ReusableFunctions {

	WebDriver driver;
	
	// Initialize the driver using constructor
	public LoginPage(WebDriver driver) {
		super(driver); // Sending driver to Super ReusableFunctions class
		this.driver=driver;
		PageFactory.initElements(driver, this); // All the locators @FindBy initialized with driver
	}

	@FindBy(id="userEmail")
	private WebElement email;
	
	@FindBy(id="userPassword")
	private WebElement password;
	
	@FindBy(id="login")
	private WebElement loginBtn;
	
	@FindBy(css="[class*='flyInOut']")
	private WebElement errorElement;
	
	// Login to application by entering username and password
	public ProductCatalogPage loginApplication(String userEmail, String userPassword) {
		email.sendKeys(userEmail);
		password.sendKeys(userPassword);
		loginBtn.click();
		ProductCatalogPage productcatalog=new ProductCatalogPage(driver);
		return productcatalog;
	}
	
	public String getErrorMessage() {
		waitForWebElementToAppear(errorElement);
		return errorElement.getText();		
	}
	
	// Navigate to Login URL
	public void goToUrl() {
		driver.get("https://rahulshettyacademy.com/client/");
	}
	
}
