package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import baseTest.BaseClass;
import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import utilities.DataProviders;

/* Data is valid -->Login successful-->test passed-->Logout
 * Data is valid -->Login Unsuccessful-->test failed
 * 
 * Data is Invalid -->Login successful-->test failed-->Logout
 * Data is Invalid -->Login Unsuccessful-->test failed
 */


public class TC003_LoginDDT extends BaseClass {
	
	@Test(dataProvider="LoginData",dataProviderClass=DataProviders.class)
	public void verify_LoginDDT(String email, String pwd,String exp)
	{
		logger.info("********* Starting TC003_LoginDDT Test******** ");
		
		try {
		HomePage hp = new HomePage(driver);
		hp.clickmyAccount();
		hp.clickLogin();
		
		LoginPage lp = new LoginPage(driver);
		lp.entermailid(email);
		lp.enterpassword(pwd);
		lp.clickonLogin();
		
		MyAccountPage map = new MyAccountPage(driver);
		boolean targetpage =map.isMyAccountPageExists();
		
		if(exp.equalsIgnoreCase("Valid"))
		{
			map.clickonlogout();
			Assert.assertTrue(true);
		}
		
		else
		{
			Assert.assertTrue(false);
		}
	
	if(exp.equalsIgnoreCase("Invalid"))
	{
		map.clickonlogout();
		Assert.assertTrue(false);
	}
	else 
	{
		Assert.assertTrue(true);
	}
		}catch(Exception e)
		{
			Assert.fail();
		}
	logger.info("********* Starting TC003_LoginDDT Failed******** ");
}	
	
}
