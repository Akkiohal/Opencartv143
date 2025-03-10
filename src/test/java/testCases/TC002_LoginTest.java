package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import baseTest.BaseClass;
import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;

public class TC002_LoginTest extends BaseClass{
	
	@Test(groups={"Sanity","Master"})
	public void verify_login()
	{
		logger.info("****** Starting TC002_LoginTest ****** ");
		try
		{
		HomePage hp = new HomePage(driver);
		hp.clickmyAccount();
		hp.clickLogin();
		
		LoginPage lp = new LoginPage(driver);
		lp.entermailid(p.getProperty("email"));
		lp.enterpassword(p.getProperty("password"));
		lp.clickonLogin();
		
		MyAccountPage map = new MyAccountPage(driver);
		boolean targetpage =map.isMyAccountPageExists();
		//Assert.assertEquals(targetpage, true , "Login failed");
		Assert.assertTrue(targetpage);
		} 
		catch(Exception e)
		{
			Assert.fail();
		}
		logger.info("****** Finished TC002_LoginTest ****** ");
	}
	
	

}
