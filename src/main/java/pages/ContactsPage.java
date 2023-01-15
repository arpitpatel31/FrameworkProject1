package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import base.TestBase;

public class ContactsPage extends TestBase{
	
	@FindBy(xpath="//span[contains(text(),'Contacts')]")
	WebElement contactsLabel;
	
	@FindBy(className="selectable")
	WebElement createNewContatsLabel;
	
	@FindBy(name="first_name")
	WebElement firstName;
	
	@FindBy(name="last_name")
	WebElement lastName;
	
	@FindBy(name="position")
	WebElement position;
	
	@FindBy(name="department")
	WebElement department;
	
	@FindBy(xpath="//button[@class='ui linkedin button']")
	WebElement saveBtn;
	
	public ContactsPage() {
		PageFactory.initElements(driver, this);
	}
	
	public boolean verifyContactLabel() {
		return contactsLabel.isDisplayed();
	}
	
	//customize xpath to click the check box according
	//It is a dynamic web table so we can do it with for loop it will be time consuming will running
	//and bit complex to understand so we will use customize xpath method.
	//We will use the sibiling concept in xpath.
	//a[contains(text(),'Alex Patel')]/parent::td//preceding-sibling::td//input[@class = 'id']
	
	public void contactsPresent(String name) {
		driver.findElement(By.xpath("//a[contains(text(),'"+ name +"')]/parent::td"
				+ "//preceding-sibling::td//input[@class = 'id']"));
	}
	
	public boolean verifyNewContactsLabel() {
		return createNewContatsLabel.isDisplayed();
	}
	
	public void newContactDetails(String ftName, String ltName, String pos, String dept) {
		
		firstName.sendKeys(ftName);
		lastName.sendKeys(ltName);
		position.sendKeys(pos);
		department.sendKeys(dept);
		
		saveBtn.click();
		
	}
	
	public boolean newContactCreated(String name) {
		WebElement contactAdded =  driver.findElement(By.xpath("//a[contains(text(),'"+name+"']"));
		return contactAdded.isDisplayed();
		
	}

}
