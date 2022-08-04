package org.vtiger.campaigns;

import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import org.vtiger.ObjectRepository.CampaignPage;
import org.vtiger.generictestng.BaseClass;

public class CreateCampainTestngTest extends BaseClass {
	
	@Test(description="This is campaign test case")
	
	public void createCampainTestngTest() {

		CampaignPage campaign=new CampaignPage(driver);
		campaign.setClickOnCampaign(webutil);

		//WebElement moreButton = driver.findElement(By.xpath("//a[text()='More']"));
		//	webdriverUtility.mouseHoverOnElement(moreButton);

		String expectedCampaignName=excelUtility.getExcelData(2, 1, "campaign")+randomNumber;


		campaign.campaignClick();
		campaign.enterCampaignName(expectedCampaignName);
		campaign.clickSaveButton();
		String actualCampaignName = campaign.getActualCampaignName();
		SoftAssert soft=new SoftAssert();
		soft.assertEquals(actualCampaignName, expectedCampaignName);
		soft.assertAll();

		/*	if(actualCampaignName.equals(expectedCampaignName)) {
		System.out.println("test case is passed when actual campaign name and expected campaign name is matched");
	}
	else {
		System.out.println("test case is failed when actual campaign name and expected campaign name is not matched");
	}*/

	}
}
