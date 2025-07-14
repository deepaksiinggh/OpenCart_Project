package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObject.AccountRegistration;
import pageObject.HomePage;
import testBase.BaseClass;

public class TC_001_AccountRegistration extends BaseClass {

	@Test(invocationCount = 3)
	public void verifyAccountRegistration() {
		
		HomePage hp = new HomePage(driver);
		
		hp.clickMyAccount();
		hp.clickRegister();
		
		AccountRegistration accountReg = new AccountRegistration(driver);
		
		String password = generatePwd();
		accountReg.setFirstName(generateString())
		.setLastName(generateString())
		.setEmail(generateString()+"@gmail.com")
		.setPhone(generateNumber())
		.setPassword(password)
		.setCnfPassword(password);
		
		accountReg.setPrivicyPolicy();
		accountReg.setContinueBtn();
		
		
		String cnfMsg = accountReg.getConfirmMsg();
		
		Assert.assertEquals(cnfMsg,"Your Account Has Been Created!","Account not created");
		
	}
}
