package testcases;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import base.TestBase;
import pages.ContactsPage;
import pages.HomePage;
import pages.LoginPage;
import pages.StartscreenPage;
import utiles.TestUtil;

public class ContactsPageTest extends TestBase{
	
	StartscreenPage startscreenPage;
	LoginPage loginPage;
	HomePage homePage;
	ContactsPage contactsPage;
	TestUtil testUtil;
	
	public ContactsPageTest() {
		super();
	}
	
	@BeforeMethod
	public void setUp() {
		initialization();
		testUtil = new TestUtil();
		startscreenPage = new StartscreenPage();
		startscreenPage.openSignIn();
		loginPage = new LoginPage();
		loginPage.logIn(prop.getProperty("username"), prop.getProperty("password"));
		homePage = new HomePage();
		homePage.clickOnContacts();
		contactsPage = new ContactsPage();
	}
	
	@Test(priority = 1)
	public void verifyContactsLabel() {
		boolean contactsLabel = contactsPage.verifyContactLabel();
		Assert.assertTrue(contactsLabel);
	}
	
	//@Test(priority = 2)
	public void selectContact() {
		homePage.clickOnContacts();
		contactsPage.contactsPresent("Alex Patel");
	}
	
	//@Test(priority = 3)
	public void createNewContact() {
		homePage.mouseHoverContact();
		Assert.assertTrue(contactsPage.verifyNewContactsLabel());
		
		contactsPage.newContactDetails("Andrew", "shaw", "Team Leader", "IT");
		Assert.assertTrue(contactsPage.newContactCreated("Andrew shaw"));
	}
	
	@DataProvider
	public Object[][] getContactsTestData(){
		Object data[][] = null;
		try {
			data = TestUtil.getTestData("Contacts");
		} catch (InvalidFormatException e) {
			e.printStackTrace();
		}
		return data;
	}
	
	@Test(priority = 4, dataProvider = "getContactsTestData")
	public void createNewContact(String firstName, String lastName, String position, String department) {
		homePage.mouseHoverContact();
		Assert.assertTrue(contactsPage.verifyNewContactsLabel());
		
		contactsPage.newContactDetails(firstName, lastName, position, department);
	}
	
	@AfterMethod
	public void closeBrowser() {
		driver.close();
	}

}
