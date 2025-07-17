package testBase;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import utilites.ConfigrationReader;


public class BaseClass {

    public static WebDriver driver;
    public Logger logger;

    @BeforeClass(groups = {"Sanity","Regression","Master","DataDriven"})
    @Parameters({"os","browser"})
    public void setUp(String os,String browser) throws IOException {
    	
    	//logger
    	
    	logger = LogManager.getLogger(this.getClass()); 
    	 logger.info("Browser");
    	 
    	 // browser
    	 
    	switch(browser.toLowerCase()) {
    	case "chrome":driver =new ChromeDriver();break;
    	case "safari":driver=new SafariDriver();break;
    	case "firefox":driver =new FirefoxDriver();break;
    	case "edge":driver=new EdgeDriver();break;
    	default: System.out.println("Invalid browser name..."); return;
    	}
        driver.manage().window().maximize();
        logger.info("Maximized browser window");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
        driver.get(ConfigrationReader.readDataFromConfig("url"));
        logger.info("Navigated to TutorialsNinja Home Page");
    }

    @AfterClass(groups = {"Sanity","Regression","Master","DataDriven"})
    public void tearDown() {
        logger.info("Closing browser");
        driver.quit();
    }

    public String generateString() {
        String randomString = RandomStringUtils.randomAlphabetic(5);
        logger.debug("Generated random string: " + randomString);
        return randomString;
    }

    public String generateNumber() {
        String phoneNumber = RandomStringUtils.randomNumeric(10);
        logger.debug("Generated phone number: " + phoneNumber);
        return phoneNumber;
    }

    public String generatePwd() {
        String pwd = RandomStringUtils.randomAlphanumeric(10);
        logger.debug("Generated password: " + pwd);
        return pwd;
    }

    public String captureScreen(String tName) {
	String timeStamp = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
	
	TakesScreenshot takesScreenshot = (TakesScreenshot) driver;
	File sourceFile = takesScreenshot.getScreenshotAs(OutputType.FILE);
	
	String targetFilePath=System.getProperty("user.dir")+"\\screenshots\\" + tName + "_" + timeStamp + ".png";
	File targetFile=new File(targetFilePath);
	
	sourceFile.renameTo(targetFile);
		
	return targetFilePath; 

}

}
