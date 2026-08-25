package RSA.SeleniumFrameworkDesign;

import org.testng.Assert;
import org.testng.annotations.Test;
import RSA.BaseComponent.BaseTest;
import RSA.BaseComponent.Retry;

public class LoginErrorValidationTest extends BaseTest{

	@Test(groups = "ErrorHandling", retryAnalyzer = Retry.class)
	public void errorValidation() {
		loginpage.loginApplication("virendrarai17@gmail.com", "TimeValuewrong@22");	
		
		Assert.assertEquals("Incorrect email or password.", loginpage.getErrorMessage());
	}
}
