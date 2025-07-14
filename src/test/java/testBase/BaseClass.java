package testBase;

import java.time.Duration;

import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;


public class BaseClass {

	public static WebDriver driver;
	
	@BeforeClass
	public void setUp() {
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
		driver.get("https://tutorialsninja.com/demo/index.php?route=common/home");
	}
	
	@AfterClass
	public void tearDown() {
		driver.quit();
	}
	
	public String generateString() {
		String randomString = RandomStringUtils.randomAlphabetic(5);
		return randomString;
	}
	
	public String generateNumber() {
		String phoneNumber = RandomStringUtils.randomNumeric(10);
		return phoneNumber;
	}
	
	public String generatePwd() {
		String pwd = RandomStringUtils.randomAlphanumeric(10);
		return pwd;
	}
}
