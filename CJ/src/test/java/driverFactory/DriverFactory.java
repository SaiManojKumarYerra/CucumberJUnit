package driverFactory;

import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.relevantcodes.extentreports.ExtentTest;

import hooks.Hooks;
import utils.ReportHelpers;

public class DriverFactory extends Hooks {


	public DriverFactory(TestContext testContext) {
		super(testContext);
		// TODO Auto-generated constructor stub
	}

	static WebDriver driver;
	
	public static void InitializeDriver(String browserName) {
		try {
			switch(browserName.toLowerCase()) {
			case "chrome":
				Map<String, Object> prefs = new HashMap<String,Object>();
//				prefs.put("download.default_directory", downloadsPath);
				prefs.put("download.prompt_for_download", false);
				ChromeOptions options = new ChromeOptions();
				options.setExperimentalOption("prefs", prefs);
				options.addArguments("--remote-allow-origins=*");
				driver = new ChromeDriver(options);
				break;
				
			case "firefox":
				driver = new FirefoxDriver();
				break;
				
			case "edge":
				driver = new EdgeDriver();
				break;
				
			default:
				ReportHelpers.writeResult(driver, "Fail", "Invalid browser type '"+browserName+"'", test);	
		}
		
		if(driver!=null) {
			ReportHelpers.writeResult(driver, "Pass", "The '"+browserName+"' has launched successful", test);
			driver.manage().window().maximize();

		}else {
			ReportHelpers.writeResult(driver, "Fail", "Failed to launch the '"+browserName+"'", test);
		}
	}catch(Exception e)
	{
		ReportHelpers.writeResult(driver, "Exception", "Exception in launchBrowser() method. "+ e, test);
	}
	}
	
	
	public static WebDriver GetDriver() {
		return driver;
	}
	
	/******************************************
	 * Method Name		: closeBrowser()
	 * 
	 *******************************************/
	public static void closeBrowser(WebDriver oDriver,ExtentTest test)
	{
		try {
			oDriver.close();
			oDriver = null;
		}catch(Exception e)
		{
			ReportHelpers.writeResult(oDriver, "Exception", "Exception in closeBrowser() method. "+ e, test);
		}
	}
}
