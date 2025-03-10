package utilities;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import baseTest.BaseClass;

public class Extentreportmanager implements ITestListener 
{

	public ExtentSparkReporter sparkReporter;  //UI of the report
	public ExtentReports extent;   //populate common info on the report
	public ExtentTest test; // Creating Test cases in the report and update status of the test methods
	
	String repName;
	public void onStart(ITestContext testContext) {
		
		/* SmipleDateFormat df=new SmipleDateFormat("yyyy.MM.dd.HH.mm.ss");
		 * Date dt = new Date();
		 * String currentdatetimestamp=df.format(dt) ;
		 */
		
		String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date()); //timestamp
		repName="Test-Report-" +timeStamp+ ".html";
		sparkReporter= new ExtentSparkReporter(".\\reports\\" + repName);   //specify location of the report
		
		//sparkReporter=new ExtentSparkReporter(System.getProperty("user.dir")+ "/Reports/myreport.html");
		sparkReporter.config().setDocumentTitle("Opencart Automation Report");
		sparkReporter.config().setReportName("Opencart Functional Testing");
		sparkReporter.config().setTheme(Theme.DARK);
		
		extent= new ExtentReports();
		extent.attachReporter(sparkReporter);
		
		extent.setSystemInfo("Application", "Opencart");
		extent.setSystemInfo("module", "Admin");
		extent.setSystemInfo("Sub Module", "Customers");
		extent.setSystemInfo("Username", System.getProperty("user.name"));
		extent.setSystemInfo("Environment", "QA");
		
		String os = testContext.getCurrentXmlTest().getParameter("os");
		extent.setSystemInfo("Operating System", os);
		
		String browser = testContext.getCurrentXmlTest().getParameter("browser");
		extent.setSystemInfo("browser", browser);
		
		List<String> includedGroups = testContext.getCurrentXmlTest().getIncludedGroups();
		if(!includedGroups.isEmpty()) {
			
			extent.setSystemInfo("Groups", includedGroups.toString());
		}
		
		
		}
	
	public void onTestSuccess(ITestResult result) 
	{
		test=extent.createTest(result.getTestClass().getName());
		test.assignCategory(result.getMethod().getGroups());            //to display groups in report
		test.log(Status.PASS,result.getName()+ "got successfully executed");
    }
	
	public void onTestFailure(ITestResult result) 
	{
		test=extent.createTest(result.getTestClass().getName());
		test.assignCategory(result.getMethod().getGroups());
		
		test.log(Status.FAIL, result.getName()+ "got failed");
		test.log(Status.INFO, result.getThrowable().getMessage());
		
		/*try {
			String imgPath = new BaseClass().captureScreen(result.getName());
			test.addScreenCaptureFromPath(imgPath);
			
		}
		catch(IOException el)
		{
			el.printStackTrace();
		}
		*/
		
    }
	
	public void onTestSkipped(ITestResult result) 
	{
		test=extent.createTest(result.getTestClass().getName());
		test.assignCategory(result.getMethod().getGroups());
		test.log(Status.SKIP,result.getName() + "got skipped");
		test.log(Status.INFO, result.getThrowable().getMessage());
    }
	public void onFinish(ITestResult result) 
	{
		extent.flush();
		
		/*String pathOfExtentReport= System.getProperty("user.dir")+"\\reports\\"+repName; //For directly opening report this method is there
		File extentReport = new File(pathOfExtentReport);
		
		try {
			Desktop.getDesktop().browse(extentReport.toURI());
		}catch(IOException e) {
			e.printStackTrace();
		}
		*/
    }
}
