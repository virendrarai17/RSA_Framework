package rsa.pagefactory;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import rsa.reusablecomponents.ReusableFunctions;

public class ProductCatalogPage extends ReusableFunctions{

	WebDriver driver;
	
	// Initialize the driver using constructor in Home page of application
	public ProductCatalogPage(WebDriver driver) {
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);// All the locators @FindBy initialized with driver
	}
	
	// Store all the product in list of WebElement present in product catalog page or homepage
	@FindBy(css=".mb-3")
	List<WebElement> products;
	
	//When we click on add to cart button, spinner and toast appears
	@FindBy(css=".ng-animating")
	WebElement spinner;
	
	By productsBy=By.cssSelector(".mb-3");
	
	By addCart=By.cssSelector(".card-body button:last-of-type");
	
	By toastMessage=By.cssSelector("#toast-container");
	
	// this return list of the products name in home page
	public List<WebElement> getProductList() {
		waitForElementToAppear(productsBy);
		return products;
	}
	// this returns product by name from the list of the product
	public WebElement getProductByName(String productName) {
		
		WebElement prod=getProductList().stream().filter(product->product.findElement(By.cssSelector("b"))
				.getText().equals(productName)).findFirst().orElse(null);
		return prod;
	}
	
	// This method add the product into cart
	public MyCartPage addProductToCart(String productName) throws InterruptedException {
		WebElement prod=getProductByName(productName);
		prod.findElement(addCart).click();

		waitForElementToAppear(toastMessage);
		waitForElementToDisappear(spinner);
		MyCartPage cartPage=new MyCartPage(driver);
		return cartPage;
		
	}
}
