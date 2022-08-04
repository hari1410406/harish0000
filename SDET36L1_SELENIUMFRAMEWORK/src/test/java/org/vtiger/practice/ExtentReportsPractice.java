package org.vtiger.practice;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import org.vtiger.genericUtility.JavaUtility;
import org.vtiger.genericUtility.ListenerImplementationClass;
import org.vtiger.genericUtility.UtilityObjectsClass;
import org.vtiger.genericUtility.seleniumWebDriverUtility;

import io.github.bonigarcia.wdm.WebDriverManager;
@Listeners(org.vtiger.genericUtility.ListenerImplementationClass.class)

public class ExtentReportsPractice {
	//public  WebDriver driver;
	@Test
	public void extentReport() {
		
	
	seleniumWebDriverUtility webdriverUtility=new seleniumWebDriverUtility();
	UtilityObjectsClass.getDriver();
	UtilityObjectsClass.setWebdriverUtility(webdriverUtility);
	UtilityObjectsClass.setJavaUtility(new JavaUtility());
	WebDriverManager.chromedriver().setup();
    ListenerImplementationClass.testlog.info("browser executable files intialized successfully");
 // WebDriver driver;
	 WebDriver driver =new ChromeDriver();
    UtilityObjectsClass.setDriver(driver);
    ListenerImplementationClass.testlog.info("Browser launched successfully");
    driver.manage().window().maximize();
    ListenerImplementationClass.testlog.info("Browser maximized successfully");
    driver.get("https://google.com");
   // Assert.fail();
    driver.findElement(By.name("q")).sendKeys("maldives", Keys.ENTER);
    Assert.fail();
    driver.findElement(By.xpath("//div[@id='hdtb-tls']")).isDisplayed();
    driver.quit();
    
	}

}
