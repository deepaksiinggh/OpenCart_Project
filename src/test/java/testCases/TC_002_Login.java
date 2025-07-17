package testCases;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObject.HomePage;
import pageObject.LoginPage;
import pageObject.MyAccountPage;
import testBase.BaseClass;
import utilites.ConfigrationReader;

public class TC_002_Login extends BaseClass {
    @Test(groups={"sanity","Master"})
	public void verifyLogIn() throws IOException {
    	logger.info("********* Starting TC_002_Login **********");
    	
    	//home page
    	HomePage hp = new HomePage(driver);
    	hp.clickMyAccount();
    	hp.clickLogIn();
    	
    	// login page
    	LoginPage login = new LoginPage(driver);
    	login.setLoginEmail(ConfigrationReader.readDataFromConfig("username"));
    	login.setLoginPwd(ConfigrationReader.readDataFromConfig("password"));
    	login.clickLogin();
    	
    	//My account page
    	
    	MyAccountPage accountPage = new MyAccountPage(driver);
    	boolean  accountExist= accountPage.isMyAccountPageExist();
    	try {
    		Assert.assertTrue(accountExist,"account is not exist");
    	}catch (Exception e) {
			Assert.fail();
		}
    
    	logger.info("********* completed TC_002_Login **********");
	}
	

}
