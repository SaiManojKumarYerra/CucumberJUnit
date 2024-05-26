package utils;

import java.io.File;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.io.FileHandler;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import driverFactory.TestContext;
import hooks.Hooks;


public class ReportHelpers extends Hooks{
	
	
		public ReportHelpers(TestContext testContext) {
		super(testContext);
		// TODO Auto-generated constructor stub
	}

		/*****************************************************
		 * Method Name				: startExtentReport()
		 * 
		 *****************************************************/
		public static void startExtentReport(String fileName, String buildName) {
			String resultPath = null;
			File objresultfilePath = null;
			File objscreenshotfilePath = null;
			File objArchieve = null;
			File objExistingReport = null;
			try
			{
				
				resultPath = System.getProperty("user.dir")+"\\Results\\"+buildName;
				objresultfilePath = new File(resultPath);
				if(!objresultfilePath.exists()) {
					objresultfilePath.mkdirs();
				}
				screenshotLocation = resultPath+"\\screenshot";
				objscreenshotfilePath = new File(screenshotLocation);
				if(!objscreenshotfilePath.exists()) {
					objscreenshotfilePath.mkdirs();
				}
				
				objArchieve = new File(resultPath+"\\Archieve");
				if(!objArchieve.exists()) {
					objArchieve.mkdirs();
				}
				
				objExistingReport = new File(resultPath+"\\"+fileName+".html");
				if(objExistingReport.exists())
				{
					objExistingReport.renameTo(new File(objArchieve+"\\_"+fileName+"_"+GenericHelpers.getDateTime("ddMMyyyyhhmmss")+".html"));
				}
				
				extent = new ExtentReports(resultPath+"\\"+fileName+".html",false);
				extent.addSystemInfo("Host Name", System.getProperty("os.name"));
				extent.addSystemInfo("Environment", GenericHelpers.readPropFile("config.properties", "Environment"));
				extent.addSystemInfo("User Name", System.getProperty("user.name"));
				extent.loadConfig(new File(System.getProperty("user.dir")+"\\extent-config.xml"));
				
			}catch(Exception e)
			{
				System.out.println("Exception in startExtentReport() : "+e);
			}finally {
				resultPath = null;
				objresultfilePath = null;
				objscreenshotfilePath = null;
			}
	}
	
	/*****************************************************
	 * Method Name				: endExtentReport()
	 * 
	 *****************************************************/
	
	public static void endExtentReport(ExtentTest test ) {		
		try
		{
			extent.endTest(test);
			extent.flush();
			
		}catch(Exception e)
		{
			System.out.println("Exception in endExtentReport() : "+e);
		}
	}
	
	/*****************************************************
	 * Method Name				: startExtentTest()
	 * 
	 *****************************************************/
	
	public static ExtentTest startExtentTest(String scenarioTitle) {
		return extent.startTest(scenarioTitle);
	}
	
	public static ExtentTest getExtentTest() {
		return test;
	}
	/*****************************************************
	 * Method Name				: captureScreenshot()
	 * 
	 *****************************************************/
	
	public static String captureScreenshot(WebDriver oBrowser) {
		String strDestination = null;
		File objSource = null;
		File objDestination = null;
		try
		{
			strDestination = screenshotLocation+"\\"+"Screenshot_"+GenericHelpers.getDateTime("ddMMYYYY_hhmmss")+".png";
			TakesScreenshot ts = (TakesScreenshot) oBrowser;
			objSource = ts.getScreenshotAs(OutputType.FILE);
			objDestination = new File(strDestination);
			FileHandler.copy(objSource, objDestination);
			return strDestination;
			
		}catch(Exception e)
		{
			System.out.println("Exception in captureScreenshot() : "+e);
			return null;
		}finally
		{
			strDestination = null;
			objDestination = null;
			objSource = null;
		}
	}
	
	/*****************************************************
	 * Method Name				: writeResult()
	 * 
	 *****************************************************/
	
	public static void writeResult(WebDriver oBrowser, String status, String message, ExtentTest test) {
		
		try {
			switch(status.toLowerCase()) {
			case "pass":
				test.log(LogStatus.PASS, message);
				break;
			case "fail":
				test.log(LogStatus.FAIL, message+" : " +test.addScreenCapture(captureScreenshot(oBrowser)));
				break;
			case "info":
				test.log(LogStatus.INFO, message);
				break;
			case "warning":
				test.log(LogStatus.WARNING, message);
				break;
			case "exception":
				test.log(LogStatus.FATAL, message);
			case "screenshot":
				test.log(LogStatus.PASS, message+" : " +test.addScreenCapture(captureScreenshot(oBrowser)));
				break;
			case "bold" :
				test.log(LogStatus.PASS, "<h6><b>"+message+"</h6></b>");
				break;
			default:
				System.out.println("Invalid status '"+status+"' for the Result.Please provide Appropriate Status");	
			}
		}catch(Exception e)
		{
			System.out.println("Exception in writeResult() : "+e);
		}
	}
}