package org.vtiger.generictestng;

import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Parameters;
import org.vtiger.ObjectRepository.LoginPage;
import org.vtiger.ObjectRepository.LogoutPage;
import org.vtiger.genericUtility.ExcelUtility;
import org.vtiger.genericUtility.Iconstants;
import org.vtiger.genericUtility.JavaUtility;
import org.vtiger.genericUtility.PropertyFileUtility;
import org.vtiger.genericUtility.seleniumWebDriverUtility;

public class BaseClass extends InstanceClass{

	@BeforeSuite
	public void genericUtilityTest() {
		//open db connection
		
	}
	
    
	@BeforeClass
	@Parameters("browser")
	public void setupBrowser(String browser) {
		fileUtility=new PropertyFileUtility();
		excelUtility=new ExcelUtility();
		 webutil=new seleniumWebDriverUtility();
		javaUtility=new JavaUtility();

		fileUtility.initializePropertyFile(Iconstants.VTIGERPROPERTYFILEPATH);

		//Generate the Random Number
		randomNumber = javaUtility.getRandomNumber();

		// Get the control for particular sheet in Excel File
		excelUtility.initializeExcelFile(Iconstants.VTIGEREXCELPATH);

		// fetch the data from Property file
		url= fileUtility.getDataFromProperty("url");
		userName = fileUtility.getDataFromProperty("username");
		passWord = fileUtility.getDataFromProperty("password");
	//	browser = fileUtility.getDataFromProperty("browser");
		timeouts = fileUtility.getDataFromProperty("timeout");

		 driver = webutil.setUpDriver(browser);

		//pre-setting the browser
		webutil.maximizeBrowser();
		//convert String to Long
		longTimeout = javaUtility.convertStringToLong(timeouts);

		webutil.implicitlyWait(longTimeout);

		//initialize the Actions class
		webutil.intializeActions();
		
		//creating pom objects
		 loginPage=new LoginPage(driver);
		 logoutPage=new LogoutPage(driver);
		

	}
	@BeforeMethod
	public void loginTest() {

		webutil.openApplication(url);
		loginPage.setLoginPage(userName, passWord);


	}

	@AfterMethod
	public void LogoutTest() {
		logoutPage.logoutAction(webutil);
		System.out.println("logout done");
		
	//	WebElement administratorIcon = driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']"));
		//webdriverUtility1.mouseHoverOnElement(administratorIcon);
	//	driver.findElement(By.xpath("//a[text()='Sign Out']")).click();
	}
	@AfterClass
	public void closeBrowser() {
		webutil.closeBrowser();
	}
	
	@AfterSuite
	public void suiteteardown()
	{
		//close db connection
	}
}
