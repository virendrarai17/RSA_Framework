package RSA.SeleniumFrameworkDesign;

import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriver.Options;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import RSA.BaseComponent.BaseTest;
import io.github.bonigarcia.wdm.WebDriverManager;
import rsa.pagefactory.LoginPage;
import rsa.pagefactory.MyCartPage;
import rsa.pagefactory.OrderHistoryPage;
import rsa.pagefactory.PaymentPage;
import rsa.pagefactory.ProductCatalogPage;

public class EcommerceTest extends BaseTest  {

	//String productName="IPHONE 13 PRO";

	@Test(dataProvider = "getData")
	public void login(HashMap<String, String> input) throws InterruptedException, IOException  {

		//LoginPage loginpage=new LoginPage(driver);
		//LoginPage loginpage	=launchApplication();
		ProductCatalogPage productcatalog = loginpage.loginApplication(input.get("email"), input.get("pass"));

		List<WebElement> productList = productcatalog.getProductList();

		MyCartPage cartPage = productcatalog.addProductToCart(input.get("product"));

		productcatalog.goToCartPage();

		Assert.assertTrue(cartPage.verifyProductDetails(input.get("product")));
		PaymentPage paymentPage = cartPage.checkOutFromMyCart();

		paymentPage.selectCountry("india");
		OrderHistoryPage historyPage = paymentPage.placeOrder();

		Thread.sleep(1000);
		String confirmText = historyPage.verifyConfirmOrder();
		Assert.assertTrue(confirmText.equalsIgnoreCase("Thankyou for the order."));

	}

	//pass hardcoded value in the form of key value pair in two dimension array using Hashmap in dataprovider
	@DataProvider
	public Object[][] getData() throws IOException {



		List<HashMap<String, String>> data=getJsonDataToMap(System.getProperty("user.dir")+"//src//test//java//INPUT_DATA//ECOMMERCETEST.json");

		//return new Object [][] {{data.get(0)}, {data.get(1)}};
		return new Object [][] {{data.get(0)}};
	}

	// This is one way to pass hardcoded value in the form of key value pair in Array using DataProvider 
	//		@DataProvider
	//		public Object[][] getData() {
	//			return new Object [][] {{"virendrarai17@gmail.com","TimeValue@22","IPHONE 13 PRO"}, {"virendrarai765@gmail.com","Test@123", "ADIDAS ORIGINAL"}};
	//		}

	// This is 2nd way down
	//	HashMap<String, String> map=new HashMap<String, String>();
	//	map.put("email", "virendrarai17@gmail.com");
	//	map.put("pass", "TimeValue@22");
	//	map.put("product", "IPHONE 13 PRO");
	//	
	//	HashMap<String, String> map1=new HashMap<String, String>();
	//	map1.put("email", "virendrarai765@gmail.com");
	//	map1.put("pass", "Test@123");
	//	map1.put("product", "ADIDAS ORIGINAL");
}
