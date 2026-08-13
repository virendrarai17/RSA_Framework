package RSA.StepDefination;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;

import RSA.BaseComponent.BaseTest;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import rsa.pagefactory.LoginPage;
import rsa.pagefactory.MyCartPage;
import rsa.pagefactory.OrderHistoryPage;
import rsa.pagefactory.PaymentPage;
import rsa.pagefactory.ProductCatalogPage;

public class StepDefination extends BaseTest {
	
	public LoginPage loginPage;
	public ProductCatalogPage productcatalog;
	public MyCartPage cartPage;
	public OrderHistoryPage historyPage;
	public PaymentPage paymentPage;
	
	@Given ("I landed on ecommerce website")
	public void I_landed_on_ecommerce_website() throws IOException {
		loginPage=launchApplication();
	}
	
	@Given ("^Logged in with username (.+) and password (.+)$")
	public void login_with_username_password(String username, String password) {
		 productcatalog = loginpage.loginApplication(username, password);
	}
	
	@When ("^I add product (.+) to cart$")
	public void add_product_to_cart(String productname) throws InterruptedException {
		List<WebElement> productList = productcatalog.getProductList();

		 cartPage = productcatalog.addProductToCart(productname);
	}
	
	@When ("^checkout (.+) and submit the order$")
	public void checkout_submit_order(String productname) {
		productcatalog.goToCartPage();

		Assert.assertTrue(cartPage.verifyProductDetails(productname));
		 paymentPage = cartPage.checkOutFromMyCart();

		paymentPage.selectCountry("india");
		 historyPage = paymentPage.placeOrder();
	}
	
	@Then ("I verify the {string} confirmation message on confirmation page")
	public void confirm_message(String string) throws InterruptedException {
		Thread.sleep(1000);
		String confirmText = historyPage.verifyConfirmOrder();
		Assert.assertTrue(confirmText.equalsIgnoreCase(string));
		driver.close();
	}
	
	@Then("^\"([^\"]*)\" error message displayed$")
    public void error_message_displayed(String errorMessage) {
		Assert.assertEquals(errorMessage, loginpage.getErrorMessage());
		driver.close();
    }
}
