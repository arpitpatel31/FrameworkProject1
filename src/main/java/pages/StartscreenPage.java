package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import base.TestBase;

public class StartscreenPage extends TestBase{
	
	//Here, we will be writing Page Factory or Object Repo to find the element 
	//and all the necessary method we need to testing.
	
	//We will be using @FindBy instead of findElement.
	@FindBy(xpath="//span[contains(text(),'Log In')]")
	WebElement signIn;
	
	@FindBy(linkText="sign up")
	WebElement signUp;
	
	public StartscreenPage() {
		PageFactory.initElements(driver, this);
	}
	
	public void openSignIn() {
		signIn.click();
	}
	
	public void openSignUp() {
		signUp.click();
	}
	
	public String getStartingTitle() {
		return driver.getTitle();
	}

}
