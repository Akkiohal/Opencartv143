package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends BasePage{
	
	public LoginPage(WebDriver driver)
	{
		super(driver);
	}
	
	@FindBy(xpath="//input[@id='input-email']")
	WebElement txtentermail;
	
	@FindBy(xpath="//input[@id='input-password']")
	WebElement txtenterpassword;
	
	@FindBy(xpath="//input[@value='Login']")
	WebElement btnlogin;
	
	
	public void entermailid(String mail)
	{
		txtentermail.sendKeys(mail);
		
	}
	
	public void enterpassword(String pwd)
	{
		txtenterpassword.sendKeys(pwd);
		
	}
	
	public void clickonLogin()
	{
		btnlogin.click();
		
	}

}
