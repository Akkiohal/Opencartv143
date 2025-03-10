package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class RegistrationPage extends BasePage{
	
WebDriver driver;
	
	public RegistrationPage(WebDriver driver)
	{
		super(driver);
	}
	
	@FindBy(xpath="//input[@id='input-firstname']")
	WebElement txtFirstname;
	
	@FindBy(xpath="//input[@id='input-lastname']")
	WebElement txtLastname;
	
	@FindBy(xpath="//input[@id='input-email']")
	WebElement txtEmail;
	
	@FindBy(xpath="//input[@id='input-telephone']")
	WebElement txtTelephone;
	
	@FindBy(xpath="//input[@id='input-password']")
	WebElement txtPassword;
	
	@FindBy(xpath="//input[@id='input-confirm']")
	WebElement txtConfirmPassword;
	
	@FindBy(xpath="//input[@name='agree']")
	WebElement chkAgree;
	
	@FindBy(xpath="//input[@value='Continue']")
	WebElement btnContinue;
	
	@FindBy(xpath="//h1[normalize-space()='Your Account Has Been Created!']")
	WebElement msgConfirmation;
	
	
	public void enterfirstname(String Fname)
	{
		txtFirstname.sendKeys(Fname);
		
	}
	
	public void enterlirstname(String Lname)
	{
		txtLastname.sendKeys(Lname);
		
	}
	
	public void enteremail(String mail)
	{
		txtEmail.sendKeys(mail);
		
	}
	public void entertelephone(String number)
	{
		txtTelephone.sendKeys(number);
		
	}
	public void entertpassword(String pwd)
	{
		txtPassword.sendKeys(pwd);
		
	}
	public void entertCpassword(String pwd)
	{
		txtConfirmPassword.sendKeys(pwd);
		
	}
	
	public void clickchebox()
	{
		chkAgree.click();
		
	}
	public void clickcontinue()
	{
		btnContinue.click();
		
	}
	
	public String getconfirmationmsg()
	{
		try {
			return(msgConfirmation.getText());
		}catch (Exception e) {
			return(e.getMessage());
		}
	}
	
	}
