package stepDefinitions;

import org.openqa.selenium.WebDriver;

import com.relevantcodes.extentreports.ExtentTest;

import driverFactory.DriverFactory;
import driverFactory.TestContext;
import io.cucumber.java.en.Given;
import pages.LoginPage;

public class basicSteps extends TestContext {
	
	private TestContext testContext;
	WebDriver oDriver;
	ExtentTest test;
	LoginPage loginPage;
	
	public basicSteps(TestContext testContext) {
		
		this.testContext = testContext;
		oDriver = testContext.driver;
		test = testContext.eTest;
		loginPage = new LoginPage(oDriver);
	}

	@Given("I want to write a step with precondition")
	public void iWantToWriteAStepWithPrecondition() throws InterruptedException {
	    System.out.println("Your are inside the step DEfinition....");
	    oDriver.navigate().to("https://www.google.com");
	    loginPage.LoginWait();
	}
}
