package org.vtiger.ObjectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.vtiger.genericUtility.seleniumWebDriverUtility;

public class LogoutPage {
	
	@FindBy(xpath="//img[@src='themes/softed/images/user.PNG']")
	private WebElement administratorIcon;
	
	@FindBy(xpath="//a[text()='Sign Out']")
	private WebElement signoutBtn;
	
	public LogoutPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}
	
	//public LogoutPage(WebDriver driver) {
		// TODO Auto-generated constructor stub
//	}

	public void logoutAction(seleniumWebDriverUtility webutil) {
		 
		 webutil.mouseHoverOnElement(administratorIcon);
		 signoutBtn.click();
		 
			
		}

}
