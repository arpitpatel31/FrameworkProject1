package testcases;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import base.TestBase;
import pages.HomePage;
import pages.LoginPage;
import pages.StartscreenPage;

public class LoginPageTest extends TestBase{
	
	StartscreenPage startscreenPage;
	LoginPage loginPage;
	HomePage homePage;
	
	public LoginPageTest() {
		super();
	}
	
	@BeforeMethod
	public void setUp() {
		initialization();
		startscreenPage = new StartscreenPage();
		startscreenPage.openSignIn();
		loginPage = new LoginPage();
		homePage = new HomePage();
	}
	
	@Test(priority = 1)
	public void getLoginTitle() {
		String loginTitle = loginPage.getLoginTitle();
		Assert.assertEquals(loginTitle, prop.getProperty("loginPageTitle"));
	}
	
	@Test(priority = 2)
	public void loginWithoutEmail() {
		loginPage.logIn(" ", prop.getProperty("password"));
		
		//WebElement actualError = driver.findElement(By.xpath("//div[contains(text(),'Something went wrong...')]"));
		Assert.assertEquals(loginPage.errorMessage(), prop.getProperty("loginErrorMessage"));
		
	}
	
	@Test(priority = 3)
	public void loginWithoutPassword() {
		loginPage.logIn(prop.getProperty("username"), " ");
		
		Assert.assertEquals(loginPage.errorMessage(), prop.getProperty("loginErrorMessage"));
	}
	
	@Test(priority = 4)
	public void loginWithoutEmailAndPassword() {
		loginPage.logIn(" ", " ");
		Assert.assertEquals(loginPage.errorMessage(), prop.getProperty("loginErrorMessage"));
	}
	
	@Test(priority = 5)
	public void loginWithCorrectEmailAndPassword() {
		loginPage.logIn(prop.getProperty("username"), prop.getProperty("password"));
		
		String homeTitle = homePage.getHomeTitle();
		Assert.assertEquals(homeTitle, prop.getProperty("homePageTitle"));
	}
	
	@AfterMethod
	public void closebrowser() {
		driver.quit();
	}
	

}
