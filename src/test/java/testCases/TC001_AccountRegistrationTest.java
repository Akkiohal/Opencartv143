package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import baseTest.BaseClass;
import pageObjects.HomePage;
import pageObjects.RegistrationPage;

public class TC001_AccountRegistrationTest extends BaseClass {
	
	@Test(groups={"Regression","Master"})
	public void createObject()
	{
		logger.info("****** Starting TC001_AccountRegistrationTest *******");
		try {
		HomePage hp = new HomePage(driver);
		 hp.clickmyAccount();
		 logger.info("Clicked on MyAccount Link....");
		 hp.clickRegister();
		 logger.info("Clicked on my Register Link....");
		 
		 RegistrationPage regpage = new RegistrationPage(driver);
		 
		 logger.info("Providing Customer details....");
		 regpage.enterfirstname(randomString().toUpperCase());
		 regpage.enterlirstname(randomString().toUpperCase());
		 regpage.enteremail(randomString()+"@gmail.com");
		 regpage.entertelephone(randomNumber());
		 
		 String password =randomAlphaNumeric();
		 
		 regpage.entertpassword(password);
		 regpage.entertCpassword(password);
		 
		 regpage.clickchebox();
		 regpage.clickcontinue();
		 
		 logger.info("Validating expected message...");
		 String confmsg= regpage.getconfirmationmsg();
		 if(confmsg.equals("Your Account Has Been Created!"))
		 {
			 Assert.assertTrue(true);
		 }
		 
		 else
		 {
			 logger.error("Test Failed....");
			logger.debug("Debug logs...");
			 Assert.assertTrue(false);
		 }
		// Assert.assertEquals(confmsg, "Your Account Has Been Created!!!");
		}
		catch(Exception e)
		{
			Assert.fail();
		}
	}
	
	
	
	
}
