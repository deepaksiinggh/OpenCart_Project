package utilites;
import java.awt.Desktop;
import java.io.File;
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
import testBase.BaseClass;


public class ExtentReportManager implements ITestListener {
    public ExtentReports extentReports;
	public ExtentSparkReporter sparkReporter;
	String reportName;
	public ExtentTest test;
	
	
	@Override
	public void onStart(ITestContext context) {
		 String timeStamp = new SimpleDateFormat("yyyy.mm.dd.hh.mm.ss").format(new Date());
		  reportName = "Test_Report_"+timeStamp+".html";
		  sparkReporter= new ExtentSparkReporter(".\\reports\\" +reportName);
		  
		  sparkReporter.config().setDocumentTitle("openCart Automation Report");
		  sparkReporter.config().setReportName("openCart functional Testing");
		  sparkReporter.config().setTheme(Theme.DARK);
		  
		  extentReports = new ExtentReports();
		  extentReports.attachReporter(sparkReporter);
		  extentReports.setSystemInfo("Application", "openCart");
		  extentReports.setSystemInfo("Module", "Admin");
		  extentReports.setSystemInfo("Sub-Module", "Customers");
		  extentReports.setSystemInfo("User Name", "Deepak Singh");
		  extentReports.setSystemInfo("Enviroment", "QA");
		  
		  String os = context.getCurrentXmlTest().getParameter("os");
		  extentReports.setSystemInfo("Operating System", os);
		  
		  String browser = context.getCurrentXmlTest().getParameter("browser");
		  extentReports.setSystemInfo("Browser", browser);
		  List<String> includeGroups= context.getCurrentXmlTest().getIncludedGroups();
		  if(!includeGroups.isEmpty()) {
			  extentReports.setSystemInfo("Groups", includeGroups.toString());
		  }
	}
	
	
	@Override
	public void onTestSuccess(ITestResult result) {
	test=extentReports.createTest(result.getTestClass().getName());
	test.assignCategory(result.getMethod().getGroups());
	test.log(Status.PASS,result.getName()+"got sucessfully executed");
	
	}
	
	
	@Override
	public void onTestFailure(ITestResult result) {
		test=extentReports.createTest(result.getTestClass().getName());
		test.assignCategory(result.getMethod().getGroups());
		test.log(Status.FAIL,result.getName()+"got failed");
		test.log(Status.INFO,result.getThrowable().getMessage());
		
		try {
			String imgPath = new BaseClass().captureScreen(result.getName());
			test.addScreenCaptureFromPath(imgPath);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	@Override
	public void onTestSkipped(ITestResult result) {
		
		test=extentReports.createTest(result.getTestClass().getName());
		test.assignCategory(result.getMethod().getGroups());
		test.log(Status.SKIP,result.getName()+"got failed");
		test.log(Status.INFO,result.getThrowable().getMessage());
	}
	
	
	@Override
	public void onFinish(ITestContext context) {
	extentReports.flush();
	String pathofExtentsReport = System.getProperty("user.dir")+"\\reports\\"+reportName;
	
	File exReport = new File(pathofExtentsReport);
	try {
		Desktop.getDesktop().browse(exReport.toURI());
	}catch (Exception e) {
		e.printStackTrace();
	}
	}
	
	
	
	
}
