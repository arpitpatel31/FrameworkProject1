package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import base.TestBase;

public class LoginPage extends TestBase{
	
	//Here, we will be writing Page Factory or Object Repo to find the element 
	//and all the necessary method we need to testing.
	
	//We will be using @FindBy instead of findElement.
	@FindBy(name="email")
	WebElement email;
	
	@FindBy(name="password")
	WebElement password;
	
	@FindBy(xpath="//div[contains(text(),'Login')]")
	WebElement logIn;
	
	@FindBy(xpath="//div[contains(text(),'Something went wrong...')]")
	WebElement actualError;
	
	@FindBy(linkText="Forgot your password?")
	WebElement forgotPassword;
	
	@FindBy(linkText="Classic CRM")
	WebElement classicCRMLogin;
	
	@FindBy(linkText="Sign Up")
	WebElement signUp;
	
	public LoginPage() {
		PageFactory.initElements(driver, this);
	}
	
	public String getLoginTitle() {
		return driver.getTitle();
	}
	
	public void logIn(String username, String pwd) {
		email.sendKeys(username);
		password.sendKeys(pwd);
		logIn.click();
	}
	
	public String errorMessage() {
		return actualError.getText();
	}
	

}
