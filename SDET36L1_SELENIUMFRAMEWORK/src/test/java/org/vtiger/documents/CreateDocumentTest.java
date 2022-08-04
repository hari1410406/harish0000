package org.vtiger.documents;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;
import java.util.Random;

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

public class CreateDocumentTest {

	public static void main(String[] args) throws IOException {
		
		PropertyFileUtility fileUtility=new PropertyFileUtility();
		JavaUtility javaUtility=new JavaUtility();
		seleniumWebDriverUtility webdriverUtility=new seleniumWebDriverUtility();
		ExcelUtility excelUtility=new ExcelUtility();
		
		fileUtility.initializePropertyFile(Iconstants.VTIGERPROPERTYFILEPATH);
		
		int randomNumber = javaUtility.getRandomNumber();
		
		excelUtility.initializeExcelFile(Iconstants.VTIGEREXCELPATH);
		

		String url = fileUtility.getDataFromProperty("url");
		String userName = fileUtility.getDataFromProperty("username");
		String passWord = fileUtility.getDataFromProperty("password");
		String browser = fileUtility.getDataFromProperty("browser");
		String timeouts = fileUtility.getDataFromProperty("timeout");
		
		
		String expectedDocumentTitle=excelUtility.getExcelData(2, 1, "document")+randomNumber;
		
		WebDriver driver = webdriverUtility.setUpDriver(browser);
		
		webdriverUtility.maximizeBrowser();
		
		long longTimeout = javaUtility.convertStringToLong(timeouts);

		webdriverUtility.implicitlyWait(longTimeout);
		
		webdriverUtility.intializeActions();

		
		String expectedText=excelUtility.getExcelData(2, 2, "document");
		String expectedFileName=excelUtility.getExcelData(2, 3, "document");
		webdriverUtility.openApplication(url);
		driver.findElement(By.xpath("//input[@name='user_name']")).sendKeys(userName);
		driver.findElement(By.xpath("//input[@name='user_password']")).sendKeys(passWord);
		driver.findElement(By.xpath("//input[@id='submitButton']")).click();
		driver.findElement(By.xpath("//a[text()='Documents']")).click();
		driver.findElement(By.xpath("//img[@title='Create Document...']")).click();
		driver.findElement(By.xpath("//input[@name='notes_title']")).sendKeys(expectedDocumentTitle);
		
		WebElement childFrame = driver.findElement(By.xpath("//iframe"));
		driver.switchTo().frame(childFrame);
		driver.findElement(By.xpath("//body[@class='cke_show_borders']")).sendKeys(expectedText);
		driver.switchTo().defaultContent();
		File file=new File("./src/test/resources/Avula Harish.resume.docx");
		String absolutePath = file.getAbsolutePath();
		driver.findElement(By.xpath("//input[@id='filename_I__']")).sendKeys(absolutePath);
		driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();
		String actualDocumenttitle = driver.findElement(By.xpath("//span[@id='dtlview_Title']")).getText();
		String actualText = driver.findElement(By.xpath("//p")).getText();
		String actualFileName = driver.findElement(By.xpath("//td[@class='dvtCellInfo']/a")).getText();
		if(actualDocumenttitle.equals(expectedDocumentTitle)) {
			System.out.println("test case is passed");
		}
		else {
			System.out.println("test case is failed");
		}
		if(actualText.equals(expectedText)) {
			System.out.println("test case is passed");
		}
		else {
			System.out.println("test case is failed");
		}
		if(actualFileName.equals(expectedFileName)) {
			System.out.println("pass");
		}
		else {
			System.out.println("fail");
		}
		WebElement administratorIcon = driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']"));
		webdriverUtility.mouseHoverOnElement(administratorIcon);
		driver.findElement(By.xpath("//a[text()='Sign Out']")).click();
		
		excelUtility.closeWorkbook();
		webdriverUtility.closeBrowser();
	}

}
