package driverFactory;

import org.openqa.selenium.WebDriver;

import com.relevantcodes.extentreports.ExtentTest;

import utils.ReportHelpers;


public class TestContext {
	public WebDriver driver;
	public static ExtentTest eTest;
//private PicoFactory objFactory;
	
	public TestContext() {
//		 objFactory = new PicoFactory();
//		objFactory.getInstance(String.class);
//		objFactory.start();
		
	}
	
	public WebDriver getWebDriver() {
		if(driver==null) {
			driver = DriverFactory.GetDriver();
		}
	return driver;	
	}
	
	public ExtentTest getExtentTestObj() {
		if(eTest==null) {
		eTest = ReportHelpers.getExtentTest();
		}
		return eTest;
	}
}