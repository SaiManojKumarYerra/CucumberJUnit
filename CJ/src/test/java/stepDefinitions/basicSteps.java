package stepDefinitions;

import org.openqa.selenium.WebDriver;

import com.relevantcodes.extentreports.ExtentTest;

import driverFactory.DriverFactory;
import driverFactory.TestContext;
import io.cucumber.java.en.Given;

public class basicSteps extends TestContext {
	
	private TestContext testContext;
	WebDriver oDriver;
	ExtentTest test;
	
	public basicSteps(TestContext testContext) {
		
		this.testContext = testContext;
		oDriver = testContext.driver;
		test = testContext.eTest;
	}

	@Given("I want to write a step with precondition")
	public void iWantToWriteAStepWithPrecondition() {
	    System.out.println("Your are inside the step DEfinition....");
	}
}
