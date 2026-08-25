package cucumber;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.DataProvider;

//@CucumberOptions(features = "src/test/java/cucumber",glue = "RSA.StepDefination", monochrome = true,
//tags = "@ErrorValidationTest",plugin = {"html:target/cucumber.html"})

@CucumberOptions(features = "src/test/java/cucumber",glue = "RSA.StepDefination", monochrome = true,
plugin= {"pretty", "html:target/cucumber.html", "json:target/cucumber.json"}
,publish = true)
public class TestNGTestRunner extends AbstractTestNGCucumberTests {

    @Override
    @DataProvider(parallel = true)
    public Object[][] scenarios() {
       return super.scenarios();
    }
}
