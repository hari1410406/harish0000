package org.vtiger.organizations;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.vtiger.genericUtility.ExcelUtility;
import org.vtiger.genericUtility.Iconstants;
import org.vtiger.genericUtility.JavaUtility;
import org.vtiger.genericUtility.PropertyFileUtility;
import org.vtiger.genericUtility.seleniumWebDriverUtility;

public class CreateOrganizationTest {

	public static void main(String[] args)  {
		
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



		WebDriver driver = webdriverUtility.setUpDriver(browser);
		webdriverUtility.maximizeBrowser();

		long longTimeout = javaUtility.convertStringToLong(timeouts);

		webdriverUtility.implicitlyWait(longTimeout);

		webdriverUtility.intializeActions();

		String expectedOrganizationName=excelUtility.getExcelData(2, 1, "organization")+randomNumber;
		webdriverUtility.openApplication(url);
		driver.findElement(By.xpath("//input[@name='user_name']")).sendKeys(userName);
		driver.findElement(By.xpath("//input[@name='user_password']")).sendKeys(passWord);
		driver.findElement(By.xpath("//input[@id='submitButton']")).click();
		driver.findElement(By.xpath("//a[text()='Organizations']")).click();
		driver.findElement(By.xpath("//img[@title='Create Organization...']")).click();
		driver.findElement(By.xpath("//input[@name='accountname']")).sendKeys(expectedOrganizationName);
		driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();
		String actualOrganizationName = driver.findElement(By.xpath("//span[@id='dtlview_Organization Name']")).getText();
		if(actualOrganizationName.equals(expectedOrganizationName))
		{
			System.out.println("Test case is passed when actual organization name and expected organization name is matched");
		}
		else {
			System.out.println("Test case is failed when actual organization name and expected organization name is not matched");
		}
		WebElement administratorIcon = driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']"));
		webdriverUtility.mouseHoverOnElement(administratorIcon);

		driver.findElement(By.xpath("//a[text()='Sign Out']")).click();
		excelUtility.closeWorkbook();
		webdriverUtility.closeBrowser();
	}

}
