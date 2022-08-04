package org.vtiger.organizations;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.time.Duration;
import java.util.Properties;
import java.io.IOException;
import java.util.Random;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.vtiger.genericUtility.ExcelUtility;
import org.vtiger.genericUtility.Iconstants;
import org.vtiger.genericUtility.JavaUtility;
import org.vtiger.genericUtility.PropertyFileUtility;
import org.vtiger.genericUtility.seleniumWebDriverUtility;

import io.github.bonigarcia.wdm.WebDriverManager;

public class CreateContactWithOrganizationTest {
	
	public static void main(String[] args) throws FileNotFoundException ,IOException{
		
		PropertyFileUtility fileUtility=new PropertyFileUtility();
		ExcelUtility excelUtility=new ExcelUtility();
		JavaUtility javaUtility=new JavaUtility();
		seleniumWebDriverUtility webdriverUtility=new seleniumWebDriverUtility();

		fileUtility.initializePropertyFile(Iconstants.VTIGERPROPERTYFILEPATH);

		int randomNumber = javaUtility.getRandomNumber();

		excelUtility.initializeExcelFile(Iconstants.VTIGEREXCELPATH);

		String url = fileUtility.getDataFromProperty("url").trim();
		String userName = fileUtility.getDataFromProperty("username").trim();
		String passWord = fileUtility.getDataFromProperty("password").trim();
		String browser = fileUtility.getDataFromProperty("browser").trim();
		String timeouts = fileUtility.getDataFromProperty("timeout").trim();
		
		String expectedOrganizationName=excelUtility.getExcelData(2, 1, "organization")+randomNumber;

		String expectedLastName=excelUtility.getExcelData(2, 1, "contact")+randomNumber;

		WebDriver driver = webdriverUtility.setUpDriver(browser);
		webdriverUtility.maximizeBrowser();

		long longTimeout = javaUtility.convertStringToLong(timeouts);

		webdriverUtility.implicitlyWait(longTimeout);

		webdriverUtility.intializeActions();
	
		webdriverUtility.openApplication(url);
		driver.findElement(By.xpath("//input[@name='user_name']")).sendKeys(userName);
		driver.findElement(By.xpath("//input[@name='user_password']")).sendKeys(passWord);
		driver.findElement(By.xpath("//input[@id='submitButton']")).click();
		driver.findElement(By.xpath("//a[text()='Organizations']")).click();
		driver.findElement(By.xpath("//img[@title='Create Organization...']")).click();
		driver.findElement(By.xpath("//input[@name='accountname']")).sendKeys(expectedOrganizationName);
		driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();
		
		WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[contains(text(),'By  Administrator')]")));
		
		
		driver.findElement(By.xpath("//table[@class='hdrTabBg']//a[text()='Contacts']")).click();
		driver.findElement(By.xpath("//img[@title='Create Contact...']")).click();
		driver.findElement(By.xpath("//input[@name='lastname']")).sendKeys(expectedLastName);
		driver.findElement(By.xpath("//input[@name='account_id']/following-sibling::img[@alt='Select']")).click();
		
		String mainPageUrl = "http://localhost:8888/index.php?module=Contacts&action=EditView&return_action=DetailView&parenttab=Marketing";
		String popupPageUrl = "http://localhost:8888/index.php?module=Accounts&action=Popup&popuptype=specific_contact_account_address&form=TasksEditView&form_submit=false&fromlink=&recordid=";
		String mainId = driver.getWindowHandle();
		Set<String> allId = driver.getWindowHandles();
		for(String Id:allId) {
			driver.switchTo().window(Id);
			System.out.println(Id);
		}
		driver.findElement(By.xpath("//input[@name='search_text']")).sendKeys(expectedOrganizationName);
		driver.findElement(By.xpath("//input[@name='search']")).click();
		driver.findElement(By.linkText(expectedOrganizationName)).click();
		driver.switchTo().window(mainId);
		driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();
		String actualLastName = driver.findElement(By.xpath("//span[@id='dtlview_Last Name']")).getText();
		String actualOrganizationName = driver.findElement(By.xpath("//td[@id='mouseArea_Organization Name']/child::a")).getText();
		if(actualLastName.equals(expectedLastName)) {
			System.out.println("Camparison done ==>Test case is passed");
		}
		else {
			System.out.println("fail");
		}
		if(actualOrganizationName.equals(expectedOrganizationName)) {
			System.out.println("comparison done ===> Test case is passed");
		}
		else {
			System.out.println("comparison done ==> test case is failed");
		}
		WebElement administratorIcon = driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']"));
		webdriverUtility.mouseHoverOnElement(administratorIcon);
		driver.findElement(By.xpath("//a[text()='Sign Out']")).click();
		driver.quit();
	}
	
}



