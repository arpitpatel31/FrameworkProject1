package testcases;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import base.TestBase;
import pages.HomePage;
import pages.LoginPage;
import pages.StartscreenPage;

public class HomePageTest extends TestBase{
	
	StartscreenPage startscreenPage;
	LoginPage loginPage;
	HomePage homePage;
	
	public HomePageTest() {
		super();
	}
	
	@BeforeMethod
	public void setUp() {
		initialization();
		startscreenPage = new StartscreenPage();
		startscreenPage.openSignIn();
		loginPage = new LoginPage();
		loginPage.logIn(prop.getProperty("username"), prop.getProperty("password"));
		homePage = new HomePage();
		
	}
	
	@Test(priority = 1)
	public void verifyHomePageTitle() {
		String homeTitle = homePage.getHomeTitle();
		Assert.assertEquals(homeTitle, prop.getProperty("homePageTitle"));
	}
	
	@Test(priority = 2)
	public void checkUserLabel() {
		boolean userNameLabel = homePage.verifyUserLabel();
		Assert.assertTrue(userNameLabel);
	}
	
	@Test(priority = 3)
	public void checkTrialLabel() {
		boolean trialLabel = homePage.verifyTrialLabel();
		Assert.assertTrue(trialLabel);
	}
	
	@AfterMethod
	public void closeBrowser() {
		driver.close();
	}

}
