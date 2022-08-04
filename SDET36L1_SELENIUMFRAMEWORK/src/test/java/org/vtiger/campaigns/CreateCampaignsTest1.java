package org.vtiger.campaigns;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.vtiger.genericUtility.ExcelUtility;
import org.vtiger.genericUtility.Iconstants;
import org.vtiger.genericUtility.JavaUtility;
import org.vtiger.genericUtility.PropertyFileUtility;
import org.vtiger.genericUtility.seleniumWebDriverUtility;

public class CreateCampaignsTest1 {

	public static void main(String[] args) throws Throwable {

		//create objects  for Generic utility
		PropertyFileUtility fileUtility=new PropertyFileUtility();
		ExcelUtility excelUtility=new ExcelUtility();
		seleniumWebDriverUtility webdriverUtility=new seleniumWebDriverUtility();
		JavaUtility javaUtility=new JavaUtility();

		//initialize data from property file
		fileUtility.initializePropertyFile(Iconstants.VTIGERPROPERTYFILEPATH);
		
		//Generate the Random Number
		int randomNumber = javaUtility.getRandomNumber();

		// Get the control for particular sheet in Excel File
		excelUtility.initializeExcelFile(Iconstants.VTIGEREXCELPATH);
		
         // fetch the data from Property file
		String url= fileUtility.getDataFromProperty("url");
		String userName = fileUtility.getDataFromProperty("username");
		String passWord = fileUtility.getDataFromProperty("password");
		String browser = fileUtility.getDataFromProperty("browser");
		String timeouts = fileUtility.getDataFromProperty("timeout");
		
		// Fetch the data from the Excel File
		String expectedCampaignName=excelUtility.getExcelData(2, 1, "campaign")+randomNumber;

		//launching the browser in run time based on browser key
		WebDriver driver = webdriverUtility.setUpDriver(browser);
		
		//pre-setting the browser
		webdriverUtility.maximizeBrowser();
		//convert String to Long
		long longTimeout = javaUtility.convertStringToLong(timeouts);

		webdriverUtility.implicitlyWait(longTimeout);
		
		//initialize the Actions class
		webdriverUtility.intializeActions();
		

		
		webdriverUtility.openApplication(url);
		driver.findElement(By.xpath("//input[@name='user_name']")).sendKeys(userName);
		driver.findElement(By.xpath("//input[@name='user_password']")).sendKeys(passWord);
		driver.findElement(By.xpath("//input[@id='submitButton']")).click();
		
		WebElement moreButton = driver.findElement(By.xpath("//a[text()='More']"));
		webdriverUtility.mouseHoverOnElement(moreButton);
	
		driver.findElement(By.xpath("//a[text()='Campaigns']")).click();
		driver.findElement(By.xpath("//img[@title='Create Campaign...']")).click();
		driver.findElement(By.xpath("//input[@name='campaignname']")).sendKeys(expectedCampaignName);
		driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();
		String actualCampaignName = driver.findElement(By.xpath("//span[@id='dtlview_Campaign Name']")).getText();
		
		if(actualCampaignName.equals(expectedCampaignName)) {
			System.out.println("test case is passed when actual campaign name and expected campaign name is matched");
		}
		else {
			System.out.println("test case is failed when actual campaign name and expected campaign name is not matched");
		}
		WebElement administratorIcon = driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']"));
		webdriverUtility.mouseHoverOnElement(administratorIcon);
		driver.findElement(By.xpath("//a[text()='Sign Out']")).click();
		
		excelUtility.closeWorkbook();
		webdriverUtility.closeBrowser();
	}

}
