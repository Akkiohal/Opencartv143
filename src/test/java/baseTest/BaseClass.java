package baseTest;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Properties;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.logging.log4j.LogManager; //Log4j2
import org.apache.logging.log4j.Logger;  //Log4j2
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Platform;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

public class BaseClass {
	
	public static WebDriver driver;
	public Logger logger;
	public Properties p;
	
	@BeforeClass(groups={"Sanity","Regression","Master"})
	@Parameters({"os","browser"})
	public void initbrowser(String os, String br) throws IOException
	{
		//Loading config.properties file
		
		FileReader File = new FileReader("./src//test//resources//config.properties");
		p= new Properties();
		p.load(File);
		
		logger=LogManager.getLogger(this.getClass()); //Log4j2
		
		if(p.getProperty("execution_env").equalsIgnoreCase("remote"))
		{
		DesiredCapabilities cap = new DesiredCapabilities();
		
		if(os.equalsIgnoreCase("windows"))
		{
		cap.setPlatform(Platform.WIN11);
		}
		else if (os.equalsIgnoreCase("Linux"))
		{
			cap.setPlatform(Platform.LINUX);
		}
		else if (os.equalsIgnoreCase("mac"))
		{
			cap.setPlatform(Platform.MAC);
		}
		else
		{
			System.out.println("NO matching os");
			return;
		}
		
		//browser
		
		switch(br.toLowerCase())
		{
		case "chrome" : cap.setBrowserName("chrome");break;
		case "firefox" : cap.setBrowserName("firefox");break;
		default: System.out.println("No matching browser"); return;
		
		   
		}
		driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"),cap);
	}
		
		if(p.getProperty("execution_env").equalsIgnoreCase("local"))
		{
			switch(br.toLowerCase())
			{
			case "chrome"  : driver=new ChromeDriver(); break;
			case "edge"  : driver=new EdgeDriver(); break;
			case "firefox"  : driver=new FirefoxDriver(); break;
			default : System.out.println("Invalid browser name.."); return;
			}
			
		}
		
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.get(p.getProperty("appURL"));
		driver.manage().window().maximize();
	}
	
	@AfterClass(groups= {"Sanity","Regression","Master"})
	public void tearDown()
	{
		driver.quit();
	}
	
	public String randomString()
	{
		String generatedstring = RandomStringUtils.randomAlphabetic(5);
		return generatedstring;
		
	}
	
	public String randomNumber()
	{
		String generatednumber = RandomStringUtils.randomNumeric(10);
		return generatednumber;
		
	}
	
	public String randomAlphaNumeric()
	{
		String generatedstring = RandomStringUtils.randomAlphabetic(3);
		String generatednumber = RandomStringUtils.randomNumeric(3);
		return (generatedstring+"@"+generatednumber);

}
	/*public String captureScreen(String tname) throws IOException
	{
		String timestamp = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
	TakesScreenshot src=(TakesScreenshot)driver;
	
	File sourcefile=src.getScreenshotAs(OutputType.FILE);
	
	String targetFilePath=System.getProperty("user.dir")+"\\Screenshots\\" + tname +"_" + timestamp + ".png";
	File targetfile= new File(targetFilePath);
	
	sourcefile.renameTo(targetfile);
	
	return targetFilePath;
	
	
	}
	*/
}
