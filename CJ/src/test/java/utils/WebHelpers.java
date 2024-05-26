package utils;

import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.File;
import java.time.Duration;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.relevantcodes.extentreports.ExtentTest;

import driverFactory.TestContext;
import hooks.Hooks;

public class WebHelpers extends Hooks {
	
	
	public WebHelpers(TestContext testContext) {
		super(testContext);
		// TODO Auto-generated constructor stub
	}


	/***********************************************
	 * Method Name		: clickObject()
	 *
	 ************************************************/
	public static boolean clickObject(WebDriver oDriver,WebElement oEle, ExtentTest test)
	{
		try {

			if(oEle!=null) {
				ReportHelpers.writeResult(oDriver, "Pass", "The element '"+Locator(oEle)+"' was clicked successful",test);
				oEle.click();
				return true;
			}else {
				ReportHelpers.writeResult(oDriver, "Fail", "Failed to find the element '"+Locator(oEle)+"'",test);
				return false;
			}
		}catch(Exception e)
		{
			ReportHelpers.writeResult(oDriver, "Exception", "Exception in clickObject() method. "+ e, test);
			return false;
		}
	}
	
	
	/***********************************************
	 * Method Name		: jsClickObject()
	 *
	 ************************************************/
	public static boolean jsClickObject(WebDriver oDriver, WebElement oEle, ExtentTest test)
	{
		JavascriptExecutor js = null;
		try {
			js = (JavascriptExecutor) oDriver;
			if(oEle!=null) {
				ReportHelpers.writeResult(oDriver, "Pass", "The element '"+Locator(oEle)+"' was clicked successful", test);
				js.executeScript("arguments[0].click();", oEle);
				return true;
			}else {
				ReportHelpers.writeResult(oDriver, "Fail", "Failed to find the element '"+Locator(oEle)+"'", test);
				return false;
			}
		}catch(Exception e)
		{
			ReportHelpers.writeResult(oDriver, "Exception", "Exception in jsClickObject() method. "+ e, test);
			return false;
		}
		finally {
			js = null;
		}
	}

	
	/***********************************************
	 * Method Name		: setObject()
	 *
	 ************************************************/
	public static boolean setObject(WebDriver oDriver, WebElement oEle, String strValue, ExtentTest test)
	{
		try {
			if(oEle!=null) {
				ReportHelpers.writeResult(oDriver, "Pass", "The data '"+strValue+"' was set in the element '"+Locator(oEle)+"' successful", test);
				oEle.sendKeys(strValue);
				return true;
			}else {
				ReportHelpers.writeResult(oDriver, "Fail", "Failed to find the element '"+Locator(oEle)+"'", test);
				return false;
			}
		}catch(Exception e)
		{
			ReportHelpers.writeResult(oDriver, "Exception", "Exception in setObject() method. "+ e, test);
			return false;
		}
	}
	
	
	/***********************************************
	 * Method Name		: clearAndSetObject()
	 *
	 ************************************************/
	public static boolean clearAndSetObject(WebDriver oDriver, WebElement oEle, String strValue,ExtentTest test)
	{
		try {
			
			if(oEle!=null) {
				ReportHelpers.writeResult(oDriver, "Pass", "Cleared & data '"+strValue+"' was set in the element '"+Locator(oEle)+"' successful", test);
				oEle.clear();
				oEle.sendKeys(strValue);
				return true;
			}else {
				ReportHelpers.writeResult(oDriver, "Fail", "Failed to find the element '"+Locator(oEle)+"'", test);
				return false;
			}
		}catch(Exception e)
		{
			ReportHelpers.writeResult(oDriver, "Exception", "Exception in clearAndSetObject() method. "+ e, test);
			return false;
		}
	}
	
	
	/***********************************************
	 * Method Name		: compareValues()
	 *
	 ************************************************/
	public static boolean compareValues(WebDriver oDriver, String actual, String expected,ExtentTest test)
	{
		try {
			if(actual.equalsIgnoreCase(expected)) {
				ReportHelpers.writeResult(oDriver, "Pass", "The actual '"+actual+"' & the expected '"+expected+"' are matching", test);
				return true;
			}else {
				ReportHelpers.writeResult(oDriver, "Fail", "Mis-match in both the actual '"+actual+"' & the expected '"+expected+"' values", test);
				return false;
			}
		}catch(Exception e)
		{
			ReportHelpers.writeResult(oDriver, "Exception", "Exception in compareValues() method. "+ e, test);
			return false;
		}
	}
	
	
	
	/***********************************************
	 * Method Name		: verifyText()
	 *
	 ************************************************/
	public static boolean verifyText(WebDriver oDriver, WebElement oEle, String strObjectType, String expectedText,ExtentTest test)
	{
		Select oSel = null;
		String actual = null;
		try {
			
			if(oEle!=null) {
				switch(strObjectType.toLowerCase()) {
					case "text":
						actual = oEle.getText();
						break;
					case "value":
						actual = oEle.getAttribute("value");
						break;
					case "dropdown":
						oSel = new Select(oEle);
						actual = oSel.getFirstSelectedOption().getText();
						break;
					default:
						ReportHelpers.writeResult(oDriver, "Fail", "Invalid name for the objectType '"+strObjectType+"'", null);
						return false;
				}
				
				if(compareValues(oDriver, actual, expectedText, test)) {
					return true;
				}else {
					return false;
				}
			}else {
				ReportHelpers.writeResult(oDriver, "Fail", "Failed to find the element '"+Locator(oEle)+"'", test);
				return false;
			}
		}catch(Exception e)
		{
			ReportHelpers.writeResult(oDriver, "Exception", "Exception in verifyText() method. "+ e, test);
			return false;
		}
		finally {
			oSel = null;
		}
	}
	
	/***********************************************
	 * Method Name		: verifyElementExist()
	 *
	 ************************************************/
	public static boolean verifyElementExist(WebDriver oDriver, WebElement oEle,ExtentTest test)
	{
		try {
			if(oEle!=null) {
				ReportHelpers.writeResult(oDriver, "Pass", "The element '"+Locator(oEle)+"' exist in the DOM", test);
				return true;
			}else {
				ReportHelpers.writeResult(oDriver, "Fail", "Failed to find the element '"+Locator(oEle)+"' in the DOM", test);
				return false;
			}
		}catch(Exception e)
		{
			ReportHelpers.writeResult(oDriver, "Exception", "Exception in verifyElementExist() method. "+ e, test);
			return false;
		}
	}
	
	/***********************************************
	 * Method Name		: verifyElementExistAndMoveToElement()
	 *
	 ************************************************/
	public static boolean verifyElementExistAndMoveToElementWithSS(WebDriver oDriver, WebElement oEle,ExtentTest test)
	{
		Actions action =null;
		try {
			action = new Actions(oDriver);
			if(oEle!=null) {
				action.moveToElement(oEle).build().perform();
				
				ReportHelpers.writeResult(oDriver, "Screenshot", "The element '"+Locator(oEle)+"' exist in the DOM and moved to particular element", test);
				return true;
			}else {
				ReportHelpers.writeResult(oDriver, "Fail", "Failed to find the element '"+Locator(oEle)+"' in the DOM", test);
				return false;
			}
		}catch(Exception e)
		{
			ReportHelpers.writeResult(oDriver, "Exception", "Exception in verifyElementExistAndMoveToElementWithSS() method. "+ e, test);
			return false;
		}
	}
	
	/***********************************************
	 * Method Name		: verifyElementNotExist()
	 *
	 ************************************************/
	public static boolean verifyElementNotExist(WebDriver oDriver, WebElement oEle,ExtentTest test)
	{
		try {
			
			if(oEle!=null) {
				ReportHelpers.writeResult(oDriver, "Fail", "The element '"+Locator(oEle)+"' exist in the DOM", test);
				return false;
			}else {
				ReportHelpers.writeResult(oDriver, "Pass", "The element '"+Locator(oEle)+"' was removed form the DOM", test);
				return true;
			}
		}catch(Exception e)
		{
			ReportHelpers.writeResult(oDriver, "Exception", "Exception in verifyElementNotExist() method. "+ e, test);
			return false;
		}
	}
	
	
	
	/***********************************************
	 * Method Name		: verifyOptionalElementExist()
	 *
	 ************************************************/
	public static boolean verifyOptionalElementExist(WebDriver oDriver, WebElement oEle,ExtentTest test)
	{
		try {
			
			if(oEle!=null) {
				return true;
			}else {
				return false;
			}
		}catch(Exception e)
		{
			ReportHelpers.writeResult(oDriver, "Exception", "Exception in verifyOptionalElementExist() method. "+ e, test);
			return false;
		}
	}
	
	
	/***********************************************
	 * Method Name		: scrollToBottom()
	 *
	 ************************************************/
	
	public static boolean scrollToBottom(WebDriver oDriver) {
		JavascriptExecutor js = null;
		try {
			js = (JavascriptExecutor) oDriver;
			js.executeScript("window.scrollBy(0,document.body.scrollHeight)");
			return true;
		}catch(Exception e)
		{
			ReportHelpers.writeResult(oDriver, "Exception", "Exception in scrollToBottom() method. "+ e, null);
			return false;
		}
	}
	
	
	/********************************************************
	 * Method Name		:SetObject_Robot()
	 * Purpose			:
	 * 
	 * 
	 ********************************************************/
	public static void SetObject_Robot(WebDriver oDriver, String FilePath) {
		Robot robot =null;
		try {
			robot = new Robot();
			File file = new File(FilePath);
			StringSelection path = new StringSelection(file.getAbsolutePath());
			
			Toolkit.getDefaultToolkit().getSystemClipboard().setContents(path, null);
			
			robot.keyPress(KeyEvent.VK_CONTROL);
			robot.keyPress(KeyEvent.VK_V);
			robot.keyRelease(KeyEvent.VK_V);
			robot.keyRelease(KeyEvent.VK_CONTROL);
			
			Thread.sleep(3000);
			
			robot.keyPress(KeyEvent.VK_ENTER);
			robot.keyRelease(KeyEvent.VK_ENTER);
			
			Thread.sleep(5000);
		}catch(Exception e) {
			ReportHelpers.writeResult(oDriver, "Exception", "Exception in SetObject_Robot() method : "+e, test);
			
		}finally {
			robot = null;
		}
	}

	/********************************************************
	 * Method Name		: waitForElement()
	 * Purpose			:
	 * 
	 * 
	 ********************************************************/
	public static boolean waitForElement(WebDriver oDriver, WebElement oEle, String strWaitReason, String strExpectedVal, Duration timeout)
	{
		WebDriverWait oWait = null;
		try {
			oWait = new WebDriverWait(oDriver, timeout);
			
			switch(strWaitReason.toLowerCase())
			{
				case "text":
					oWait.until(ExpectedConditions.textToBePresentInElement(oEle, strExpectedVal));
					break;
				case "value":
					oWait.until(ExpectedConditions.textToBePresentInElementValue(oEle, strExpectedVal));
					break;
				case "clickable":
					oWait.until(ExpectedConditions.elementToBeClickable(oEle));
					break;
				case "visible":
					oWait.until(ExpectedConditions.visibilityOf(oEle));
					break;
				case "invisible":
					oWait.until(ExpectedConditions.invisibilityOf(oEle));
					break;
				default:
					System.out.println("Invalid wait condition '"+strWaitReason+"' was provided");
			}
			return true;
		}catch(Exception e)
		{
			ReportHelpers.writeResult(oDriver, "Exception", "Exception in waitForElement() method : " + e, test);
			return false;
		}
		finally {
			oWait = null;
		}
	}
	
	/******************************
	 * Method Name : Locator
	 ******************************/
	public static String Locator(WebElement oEle) {
		try {
			String s = oEle.toString();
			return s.substring(s.indexOf('>')+2, s.length()-1);
		}catch(Exception e) {
			System.out.println("Exception in Locator() Method : "+e);
			return null;
		}
	}

	
	
}