package pageObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AccountRegistration extends BasePage {

	public AccountRegistration(WebDriver driver) {
		super(driver);
	}

   @FindBy(id = "input-firstname")
   WebElement txtFirstName;
   
   @FindBy(id ="input-lastname")
   WebElement txtLAstName;
   
   @FindBy(id="input-email")
   WebElement txtEmail;
   
   @FindBy(id="input-telephone")
   WebElement txtPhoneNumber;
   
   @FindBy(id = "input-password")
   WebElement txtPassword;
   
   
   @FindBy(id = "input-confirm")
   WebElement txtCnfPassword;
   
   @FindBy(xpath = "//input[@name='agree']")
   WebElement chkdprivicy;
   
   @FindBy(xpath = "//input[@class='btn btn-primary']")
   WebElement btnContinue;
   
   @FindBy(xpath = "//h1[normalize-space()='Your Account Has Been Created!']")
   WebElement msgAccountConf;
   
   
   
   public AccountRegistration setFirstName(String fname) {
	   txtFirstName.sendKeys(fname);
	   return this;
   }
   
   
   public AccountRegistration setLastName(String lname) {
	   txtLAstName.sendKeys(lname);
	   return this;
   }
   
   public AccountRegistration setEmail(String email) {
	   txtEmail.sendKeys(email);
	   return this;
   }
   
   public AccountRegistration setPhone(String phone) {
	   txtPhoneNumber.sendKeys(phone);
	   return this;
   }
   
   public AccountRegistration setPassword(String pwd) {
	   txtPassword.sendKeys(pwd);
	   return this;
   }
   
   public AccountRegistration setCnfPassword(String cnfPwd) {
	   txtCnfPassword.sendKeys(cnfPwd);
	   return this;
   }
   
   public void setPrivicyPolicy() {
	   chkdprivicy.click();
   }
   
   public void setContinueBtn() {
	   btnContinue.click();
   }
   
   public String getConfirmMsg() {
	   try {
		return msgAccountConf.getText();
	} catch (Exception e) {
		return e.getMessage();
	}
   }
	
}
