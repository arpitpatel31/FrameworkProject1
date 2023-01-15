package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import base.TestBase;

public class HomePage extends TestBase{
	
	@FindBy(xpath="//span[@class='user-display']")
	WebElement userLabel;
	
	@FindBy(xpath="//span[@class='trial-indicator']")
	WebElement trialIndicator;
	
	@FindBy(xpath="//i[@class='users icon']")
	WebElement contacts;
	
	@FindBy(xpath="(//div[@class='menu-item-wrapper'])[3]//button")
	WebElement addNewContacts;
	
	@FindBy(xpath="//i[@class='money icon']")
	WebElement deals;
	
	@FindBy(xpath="//i[@class='tasks icon']")
	WebElement tasks;
	
	public HomePage() {
		PageFactory.initElements(driver, this);
	}
	
	public String getHomeTitle() {
		return driver.getTitle();
	}
	
	public boolean verifyUserLabel() {
		return userLabel.isDisplayed();
	}
	
	public boolean verifyTrialLabel() {
		return trialIndicator.isDisplayed();
	}
	
	public ContactsPage clickOnContacts() {
		contacts.click();
		return new ContactsPage();
	}
	
	public DealsPage clickOnDeals() {
		deals.click();
		return new DealsPage();
	}
	
	public TasksPage clickOnTasks() {
		tasks.click();
		return new TasksPage();
	}
	
	public void mouseHoverContact() {
		Actions action = new Actions(driver);
		action.moveToElement(contacts).build().perform();
		addNewContacts.click();
	}
	
}
