package org.vtiger.products;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.vtiger.genericUtility.ExcelUtility;
import org.vtiger.genericUtility.Iconstants;
import org.vtiger.genericUtility.JavaUtility;
import org.vtiger.genericUtility.PropertyFileUtility;
import org.vtiger.genericUtility.seleniumWebDriverUtility;

public class CreateProductTest {
	
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
		
		String expectedProductName=excelUtility.getExcelData(2, 1 , "product")+randomNumber;
		webdriverUtility.openApplication(url);
		driver.findElement(By.xpath("//input[@name='user_name']")).sendKeys(userName);
		driver.findElement(By.xpath("//input[@name='user_password']")).sendKeys(passWord);
		driver.findElement(By.xpath("//input[@id='submitButton']")).click();
		driver.findElement(By.xpath("//a[text()='Products']")).click();
		driver.findElement(By.xpath("//img[@title='Create Product...']")).click();
		driver.findElement(By.xpath("//input[@name='productname']")).sendKeys(expectedProductName);
		driver.findElement(By.xpath("//input[@class='crmbutton small save']")).click();
		String actualProductName = driver.findElement(By.xpath("//span[@id='dtlview_Product Name']")).getText();
		if(actualProductName.equals(expectedProductName)) {
			System.out.println("product name is matched ==> Test case is passed");
		}
		else {
			System.out.println("product name is not matched ==> Test case is Failed");
		}
		WebElement administratorIcon = driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']"));
          webdriverUtility.mouseHoverOnElement(administratorIcon);
		driver.findElement(By.xpath("//a[text()='Sign Out']")).click();
		webdriverUtility.closeBrowser();

	}
}


