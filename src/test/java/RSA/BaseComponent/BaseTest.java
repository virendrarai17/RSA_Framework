package RSA.BaseComponent;

import org.testng.annotations.AfterMethod;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.safari.SafariDriver;
import org.testng.annotations.BeforeMethod;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import rsa.pagefactory.LoginPage;

public class BaseTest {

	public WebDriver driver;
	public LoginPage loginpage;

	public WebDriver initialize() throws IOException {

		Properties prop = new Properties();
		FileInputStream fis = new FileInputStream(
				System.getProperty("user.dir") + "//src//main//java//rsa//resources//PropertyFile.properties");
		prop.load(fis);
		
		String browserName=System.getProperty("browser")!=null ? System.getProperty("browser"):prop.getProperty("browser");

		//String browserName=prop.getProperty("browser");
		if (browserName.contains("chrome")) {
			ChromeOptions option = new ChromeOptions();
//			option.addArguments("--remote-allow-origins=*");
			if(browserName.contains("headless")) {
			option.addArguments("--headless=new");
			}
			driver = new ChromeDriver(option);
		} 
		else if (browserName.equals("safari")) {
			driver = new SafariDriver();
		}
		driver.manage().window().maximize();
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(10));
		return driver;
	}

	// To read the data from Json File  
	public List<HashMap<String, String>> getJsonDataToMap(String filePath) throws IOException {

		String jsonContent	=FileUtils.readFileToString(new File (filePath) 
				,StandardCharsets.UTF_8);// ReadFile from Json and convert it to string

		// converting json object into list of hashMap
		ObjectMapper mapper=new ObjectMapper();
		List<HashMap<String, String>> data=mapper.readValue(jsonContent, new TypeReference<List<HashMap<String, String>>>() {
		});
		return data;	
	}

	// Method to capture screenshot when testcase failed
	public String getScreenShot(String testCaseName, WebDriver driver) throws IOException {

		TakesScreenshot ts=(TakesScreenshot)driver;
		File src=ts.getScreenshotAs(OutputType.FILE);

		FileUtils.copyFile(src, new File(System.getProperty("user.dir")+"//reports//"+testCaseName+".png"));
		return System.getProperty("user.dir")+"//reports//"+testCaseName+".png";
	}

	@BeforeMethod(alwaysRun = true)
	public LoginPage launchApplication() throws IOException {
		driver = initialize();
		loginpage = new LoginPage(driver);
		loginpage.goToUrl();
		return loginpage;
	}

	@AfterMethod
	public void tearDown() {
		driver.quit();
	}
}
