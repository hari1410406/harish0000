package org.vtiger.ObjectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.vtiger.genericUtility.seleniumWebDriverUtility;

public class CampaignPage {
	
	@FindBy(xpath="//a[text()='More']")
	private WebElement moreTab;
	
	@FindBy(xpath="//a[text()='Campaigns']")
	private WebElement campaingsClick;	
	
	@FindBy(xpath="//img[@title='Create Campaign...']")
	private WebElement createCampaignClick;
	
	
	@FindBy(xpath="//input[@name='campaignname']")
	private WebElement campaignLastNameTextBox;
	
	@FindBy(xpath="//input[@title='Save [Alt+S]']")
	private WebElement saveButton;
	
	@FindBy(xpath="//span[@id='dtlview_Campaign Name']")
	private WebElement actualCampaignName;
	
	@FindBy(xpath="//img[@src='themes/softed/images/user.PNG']")
	private WebElement administratorIcon;
	
	@FindBy(xpath="//a[text()='Sign Out']")
	private WebElement signoutBtn;
	
	
	public CampaignPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}
	
	public void setClickOnCampaign(seleniumWebDriverUtility webutil) {
		webutil.mouseHoverOnElement(moreTab);
		campaingsClick.click();
	}
	public void campaignClick() {
		//campaingsClick.click();
		createCampaignClick.click();
	}
		
	

	
	public void enterCampaignName(String CampaignName) {
		 campaignLastNameTextBox.sendKeys(CampaignName);
		
	}
	
	public void clickSaveButton() {
		saveButton.click();
	}
	
	public String getActualCampaignName() {
		return actualCampaignName.getText();
	}
	
	
	public void logoutAction(seleniumWebDriverUtility webutil) {
	 
	 webutil.mouseHoverOnElement(administratorIcon);
	 signoutBtn.click();
	 
		
	}
	}


