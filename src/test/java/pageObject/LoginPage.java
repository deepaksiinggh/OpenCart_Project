package pageObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;


public class LoginPage extends BasePage {

	public LoginPage(WebDriver driver) {
		super(driver);
	}
	@FindBy(id = "input-email")
	WebElement txtloginEmail;
	
	@FindBy(id="input-password")
	WebElement txtloginPassword;
	
	@FindBy(xpath = "//input[@value='Login']")
	WebElement btnLogin;
	
	public LoginPage setLoginEmail(String email) {
		txtloginEmail.sendKeys(email);
		return this;
	}
	
	public LoginPage setLoginPwd(String pwd) {
		txtloginPassword.sendKeys(pwd);
		return this;
	}
	
	public void clickLogin() {
		btnLogin.click();
	}
	
}
