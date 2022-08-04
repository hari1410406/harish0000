package org.vtiger.campaigns;

import org.openqa.selenium.WebDriver;
import org.vtiger.ObjectRepository.CampaignPage;
import org.vtiger.ObjectRepository.LoginPage;
import org.vtiger.genericUtility.ExcelUtility;
import org.vtiger.genericUtility.Iconstants;
import org.vtiger.genericUtility.JavaUtility;
import org.vtiger.genericUtility.PropertyFileUtility;
import org.vtiger.genericUtility.seleniumWebDriverUtility;

public class CreateCampaignPom {

	public static void main(String[] args) {

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

		//	webdriverUtility.openApplication(url);

		CampaignPage campaignPage=new CampaignPage(driver);
		LoginPage login=new LoginPage(driver);

		webdriverUtility.openApplication(url);
		login.setLoginPage(userName, passWord);
		campaignPage.setClickOnCampaign(webdriverUtility);
		campaignPage.campaignClick();

		String campaignLastName = excelUtility.getExcelData(2, 1, "campaign")+randomNumber;
		campaignPage.enterCampaignName(campaignLastName);
		campaignPage.clickSaveButton();
		String actualCampaignLastName = campaignPage.getActualCampaignName();
		if(actualCampaignLastName.equals(expectedCampaignName)) {
			System.out.println("test case is passed when actual campaign name and expected campaign name is matched");
		}
		else {
			System.out.println("test case is failed when actual campaign name and expected campaign name is not matched");
		}
		campaignPage.logoutAction(webdriverUtility);
		webdriverUtility.closeBrowser();


	}

}
