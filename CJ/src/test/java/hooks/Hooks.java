package hooks;

import java.time.Duration;

import org.openqa.selenium.WebDriver;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;

import driverFactory.DriverFactory;
import driverFactory.TestContext;
import io.cucumber.java.After;
import io.cucumber.java.AfterAll;
import io.cucumber.java.Before;
import io.cucumber.java.BeforeAll;
import io.cucumber.java.Scenario;
import utils.ReportHelpers;

public class Hooks {
	public static ExtentReports extent = null;
	public static ExtentTest test = null;
	public static String screenshotLocation = null;
	public static String downloadsPath = null;
	public static WebDriver oDriver = null;
	
	Scenario scenario;
	
	private TestContext testContext;
	
	public Hooks(TestContext testContext) {
		this.testContext = testContext;
		
	}
	
	@BeforeAll
	public static void InitializeReport() {
		ReportHelpers.startExtentReport("AutomationTestReport", "Build001");
	}
	
	@Before
	public void Setup(Scenario scenario) {
		this.scenario = scenario;
		test = ReportHelpers.startExtentTest(scenario.getName());
		TestContext.eTest = test;
		DriverFactory.InitializeDriver("chrome");
		oDriver = DriverFactory.GetDriver();
		testContext.driver = oDriver;
		oDriver.manage().window().maximize();
		oDriver.manage().deleteAllCookies();
		oDriver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(20));
		oDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));		
	}
	
	@After
	public void TearDown() {
		DriverFactory.closeBrowser(oDriver, test);
	}
	
	@AfterAll
	public static void FinishReport() {
		ReportHelpers.endExtentReport(test);
	}
}
