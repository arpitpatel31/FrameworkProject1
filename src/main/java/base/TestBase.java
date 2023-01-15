package base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;

import utiles.WebEventListener;

import utiles.TestUtil;

public class TestBase {
	
	//Here we will add all the methods that we need each and every test case like WebDriver,
	//read properties, wait, deleteAllCookies , etc.
	
	//We can use this global variable in any child class. 
	//we will create a global variable of WebDriver.
	public static WebDriver driver;
	//we will create a global variable of Properties.
	public static Properties prop;
	public  static EventFiringWebDriver e_driver;
	public static WebEventListener eventListener;
	
	
	//We will create a constructor to read the data from the properties file.
	public TestBase() {
		//we will use the try catch block to catch the exception 
		try {
			prop = new Properties();
			FileInputStream fileInputStream = new FileInputStream("C:\\Users\\arpit\\Desktop\\QA\\Selenium_workspace\\FrameworkProject\\src\\main\\java\\config\\config.properties");
			prop.load(fileInputStream);
		} catch(FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	//we will initialize which browser we will be using for testing
	//we will set the properties of the browser as well
	public static void initialization() {
		//we will read that which browser is in the config.properties
		String browserName = prop.getProperty("browser");
		
		//we will be using if else to check which browser is mention so that system will configure 
		//the property according to the mention name.
		if(browserName.equals("firefox")) {
			System.setProperty("webdriver.gecko.driver", "C:\\Users\\arpit\\Desktop\\QA\\SeleniumJars\\geckodriver.exe");
			driver = new FirefoxDriver();
		} else if(browserName.equals("chrome")) {
			System.setProperty("webdriver.chrome.driver", "C:\\Users\\arpit\\Desktop\\QA\\SeleniumJars\\chromedriver.exe");
			driver = new ChromeDriver();
		}
		
		e_driver = new EventFiringWebDriver(driver);
		// Now create object of EventListerHandler to register it with EventFiringWebDriver
		eventListener = new WebEventListener();
		e_driver.register(eventListener);
		driver = e_driver;
		
		//Now we will put all the other code like wait, maximize page, deleteAllCookies, etc.
		driver.manage().window().maximize();
		
		driver.manage().deleteAllCookies();
		
		//We gave the timeout but it can change frequently so we will create a variable of wait
		//instead of hardcoding it here we will add it in testUtil class and create a global variable.
		driver.manage().timeouts().pageLoadTimeout(TestUtil.PAGE_LOAD_TIMEOUT, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(TestUtil.IMPLICIT_WAIT, TimeUnit.SECONDS);
		
		//At last we will get the url of the website from the properties file.
		driver.get(prop.getProperty("url"));
	}

}
