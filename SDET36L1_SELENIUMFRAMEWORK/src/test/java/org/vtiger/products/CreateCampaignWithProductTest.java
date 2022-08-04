package org.vtiger.products;

import java.time.Duration;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.vtiger.genericUtility.ExcelUtility;
import org.vtiger.genericUtility.Iconstants;
import org.vtiger.genericUtility.JavaUtility;
import org.vtiger.genericUtility.PropertyFileUtility;
import org.vtiger.genericUtility.seleniumWebDriverUtility;

public class CreateCampaignWithProductTest {

	public static void main(String[] args) {
		
		
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
		
		String expectedCampaignName=excelUtility.getExcelData(2, 1, "campaign")+randomNumber;
		webdriverUtility.openApplication(url);
		driver.findElement(By.xpath("//input[@name='user_name']")).sendKeys(userName);
		driver.findElement(By.xpath("//input[@name='user_password']")).sendKeys(passWord);
		driver.findElement(By.xpath("//input[@id='submitButton']")).click();
		driver.findElement(By.xpath("//a[text()='Products']")).click();
		driver.findElement(By.xpath("//img[@title='Create Product...']")).click();
		driver.findElement(By.xpath("//input[@name='productname']")).sendKeys(expectedProductName);
		driver.findElement(By.xpath("//input[@class='crmbutton small save']")).click();
		
		WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(longTimeout));
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[contains(text(),'By  Administrator')]")));
		
		WebElement moreButton = driver.findElement(By.xpath("//a[text()='More']"));
		webdriverUtility.mouseHoverOnElement(moreButton);
	
		driver.findElement(By.xpath("//a[text()='Campaigns']")).click();
		driver.findElement(By.xpath("//img[@title='Create Campaign...']")).click();
		driver.findElement(By.xpath("//input[@name='campaignname']")).sendKeys(expectedCampaignName);
		driver.findElement(By.xpath("//input[@name='product_id']/following-sibling::img[@alt='Select']")).click();
		String mainpageUrl = "http://localhost:8888/index.php?module=Campaigns&action=EditView&return_action=DetailView&parenttab=Marketing";
		String popupPageUrl = "http://localhost:8888/index.php?module=Products&action=Popup&html=Popup_picker&form=HelpDeskEditView&popuptype=specific&fromlink=";
		String mainPageId = driver.getWindowHandle();
		Set<String> allPageId = driver.getWindowHandles();
		for(String Id:allPageId) {
			driver.switchTo().window(Id);
		}
		driver.findElement(By.xpath("//input[@name='search_text']")).sendKeys(expectedProductName);
		driver.findElement(By.xpath("//input[@name='search']")).click();
		driver.findElement(By.linkText(expectedProductName)).click();
		driver.switchTo().window(mainPageId);
		driver.findElement(By.xpath("//input[@class='crmbutton small save']")).click();
		String actualCampaignName = driver.findElement(By.xpath("//span[@id='dtlview_Campaign Name']")).getText();
		String actualProductName = driver.findElement(By.xpath("//td[@id='mouseArea_Product']/span/a")).getText();
		
		if(actualCampaignName+actualProductName.equals(expectedCampaignName+expectedProductName) != null) {
			System.out.println("comparison done ==>Test case is passed");
		}
		else {
			System.out.println("comparison is not done ===> test case is failed");
		}
		WebElement administratorIcon = driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']"));
		webdriverUtility.mouseHoverOnElement(administratorIcon);
		
		driver.findElement(By.xpath("//a[text()='Sign Out']")).click();
		webdriverUtility.closeBrowser();

	}

}
