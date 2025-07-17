package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObject.AccountRegistration;
import pageObject.HomePage;
import testBase.BaseClass;

public class TC_001_AccountRegistration extends BaseClass {

	@Test(groups = {"Regression","Master"})
	public void verifyAccountRegistration() {
		
		logger.info("*******TC001_AccountRegistrationtest *******");
		
		try {
		HomePage hp = new HomePage(driver);
		
		hp.clickMyAccount();
		logger.info("clicked on my account link");
		hp.clickRegister();
		logger.info("clicked on register link");
		AccountRegistration accountReg = new AccountRegistration(driver);
		
		String password = generatePwd();
		
		logger.info("providing customer details");
		accountReg.setFirstName(generateString())
		.setLastName(generateString())
		.setEmail(generateString()+"@gmail.com")
		.setPhone(generateNumber())
		.setPassword(password)
		.setCnfPassword(password);
		
		accountReg.setPrivicyPolicy();
		accountReg.setContinueBtn();
		
		logger.info("validating expected message");
		String cnfMsg = accountReg.getConfirmMsg();
		if(cnfMsg.equals("Your Account Has Been Created!")) {
			Assert.assertTrue(true);
		}else {
			logger.error("test failed");
			logger.debug("debug logs");
			Assert.assertTrue(false);
		}
//		Assert.assertEquals(cnfMsg,"","Account not created");
		
		} catch (Exception e) {
		
			Assert.fail();
		}
		logger.info("******* Finished TC001_AccountRegistrationTest *******");
	}
}
