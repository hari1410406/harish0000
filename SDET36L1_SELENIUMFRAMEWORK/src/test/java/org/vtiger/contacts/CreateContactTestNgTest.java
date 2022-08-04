package org.vtiger.contacts;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import org.vtiger.ObjectRepository.Contactpage;
import org.vtiger.generictestng.BaseClass;

public class CreateContactTestNgTest extends BaseClass {
	
	@Test(description="This is contact module test case ")
	
	public void createContactTest() {
		
		Contactpage contactLastname=new Contactpage(driver);
		SoftAssert soft=new SoftAssert();
		
		String expectedLastName=excelUtility.getExcelData(2, 1, "contact")+randomNumber;
		
		contactLastname.contactModule();
		contactLastname.contactLastName(expectedLastName);
		contactLastname.savebutton();
		String actualContactLastName = contactLastname.actualContactSavedLastName();
		soft.assertEquals(actualContactLastName, expectedLastName);
		soft.assertAll();
	
	}
}
		
	/*	if(actualLastName.equals(expectedLastName)) {
			System.out.println("Contact created successfully ---> test case is passed");
		}
		else {
			System.out.println(" contact is not created ---> failed");
		}*/
	/*	WebElement administratorIcon = driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']"));
		webdriverUtility.mouseHoverOnElement(administratorIcon);

		driver.findElement(By.xpath("//a[text()='Sign Out']")).click();

		excelUtility.closeWorkbook();

		webdriverUtility.closeBrowser();

	}*/


