package org.vtiger.genericUtility;

import java.io.File;
import java.io.IOException;
import java.time.Duration;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

import io.github.bonigarcia.wdm.WebDriverManager;

/**
 * This class contains all the webDriver Actions
 * @author avula
 *
 */
public final class seleniumWebDriverUtility {

	private WebDriver driver;
	private Actions act;


	/**
	 * This method is used to setup the driver instance
	 * @param browser
	 * @return 
	 */
	public WebDriver setUpDriver(String browser) {
		
		if(browser.equals("chrome")) {
			WebDriverManager.chromedriver().setup();
			driver=new ChromeDriver();
		}
		else if(browser.equals("firefox"))
		{
			WebDriverManager.firefoxdriver().setup();
			 driver=new ChromeDriver();
		}
		else if(browser.equals("InternetExplorer"))
		{
			WebDriverManager.iedriver().setup();
			 driver=new InternetExplorerDriver();
		}
		else {
			System.out.println("you are not connected to any browser");
		}
		return driver;

	}
	/**
	 * This method is used to maximize the browser
	 */

	public void maximizeBrowser() {
		driver.manage().window().maximize();
	}

	/**
	 * This method is used to wait the page by using implicit wait
	 * @param longTimeout
	 */

	public void implicitlyWait(long longTimeout) {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(longTimeout));
	}
	/**
	 * This method is used to navigate the application
	 * @param url
	 */

	public void openApplication(String url) {
		driver.get(url);
	}
	/**
	 * This method is used to initialize the Actions class
	 */

	public void intializeActions() {
		act=new Actions(driver);
	}
	
	/**
	 * This method is used to Mouse hover on Element
	 * @param element
	 */

	public void mouseHoverOnElement(WebElement element) {
		act.moveToElement(element).perform();
	}
	
	/**
	 * This method is used to perform right click action on current mouse location
	 */
	
	public void rightClickAction() {
		act.contextClick().perform();
	}
	/**
	 * This method is used to perform right click action on WebElement
	 * @param element
	 */
	
	public void rightClickAction(WebElement element) {
		act.contextClick(element).perform();
	}
	
	
	
	/**
	 * This method is used to switch Frame based on index
	 * @param indexNumber
	 */

	public void switchFrameByIndex(int indexNumber) {
		driver.switchTo().frame(indexNumber);
	}
	/**
	 * This method is used to switch Frame based on WebElement
	 * @param nameOrId
	 */
	public void switchFrameByWebElement(String nameOrId) {
		driver.switchTo().frame(nameOrId);
	}
	/**
	 * This method is used 
	 * @param strategy
	 */

	public void switchBackFromFrame(String strategy) {
		switch(strategy.toLowerCase().trim()) {
		case "default" :
			driver.switchTo().defaultContent();
			break;
		case "parent":
			driver.switchTo().parentFrame();
			break;
		default:
			System.out.println("please Enter valid strategy either <default or parent>");
		}
	}
	
	/**
	 * This method is used to handle <select> tag Dropdown by using Visible Text
	 * @param dopDownElement
	 * @param visibleText
	 */
	
	 public void handleSelectDropDown(WebElement dopDownElement, String visibleText) {
		   Select select=new Select(dopDownElement);
		   select.selectByVisibleText(visibleText);
	   }
	 
	 
	/**
	 * This method is used to handle <select> tag Dropdown by using Attribute Value of <options> Tag
	 * @param value
	 * @param dopDownElement
	 */
   public void handleSelectDropDown(String value,WebElement dropDownElement) {
	   Select select=new Select(dropDownElement);
	   select.selectByValue(value);
   }
   
   /**
    * This method is used to take screenshot opf failed test scripts
    * @param currentClass
    * @param javaUtility
    */
   public void TakesScreenShot(Object currentClass,JavaUtility javaUtility) {
	   
	   TakesScreenshot ts=(TakesScreenshot) driver;
	  File src = ts.getScreenshotAs(OutputType.FILE);
	  File target=new File("./errorshots/"+currentClass.getClass().getSimpleName()+javaUtility.getCurrentDate("dd_MM_yyyy_HH_mm_sss")+".png");
	  try {
		FileUtils.copyFile(src, target);
	} catch (IOException e) {
		e.printStackTrace();
	}
   }
   
  public String TakesScreenShotElement(Object currentClass,JavaUtility javaUtility, WebElement element) {
	   
	   TakesScreenshot tst=(TakesScreenshot) driver;
	  File src = element.getScreenshotAs(OutputType.FILE);
	  File target=new File("./errorshots/"+currentClass.getClass().getSimpleName()+javaUtility.getCurrentDate("dd_MM_yyyy_HH_mm_sss")+".png");
	  try {
		FileUtils.copyFile(src, target);
	} catch (IOException e) {
		e.printStackTrace();
	}
	  return target.getAbsolutePath();
   }

   
   
   /**
    * This method  is used to take path of screenshot
    * @param driver
    * @return
    */
   
   public String  takesScreenShotPage(WebDriver driver) {
	   TakesScreenshot tss=(TakesScreenshot) driver;
	   String path = tss.getScreenshotAs(OutputType.BASE64);
	   return path; 
   }
   
   
   
   /**
    * This method is used to handle <select> tag dropdown by using Index Number
    * @param dopDownElement
    * @param indexNumber
    */
   public void handleSelectDropDown(WebElement dropDownElement,int indexNumber) {
	   Select select=new Select(dropDownElement);
	   select.selectByIndex(indexNumber);
   }

	/**
	 * This method is used to close particular browser
	 */
	public void closeBrowser() {
		driver.quit();
	}
	/**
	 * This method is used to close particular tab
	 */

	public void closeTab() {
		driver.close();

	}
	


}
