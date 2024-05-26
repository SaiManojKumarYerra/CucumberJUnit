package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import driverFactory.TestContext;


public class LoginPage{
private WebDriver oDriver;
	public LoginPage(WebDriver driver) {
		this.oDriver = driver;
		PageFactory.initElements(oDriver, this);
	}
	
	
//	@FindBy(xpath = "")
//	private WebElement abc;
	
	public void LoginWait() throws InterruptedException {
		Thread.sleep(10000);
		oDriver.navigate().to("https://www.facebook.com");
//		oDriver.navigate().to(null);
	}
}
