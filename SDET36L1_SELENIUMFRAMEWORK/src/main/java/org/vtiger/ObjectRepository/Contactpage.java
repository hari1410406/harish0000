package org.vtiger.ObjectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Contactpage {
	
	@FindBy(xpath="//a[text()='Contacts']")
	private WebElement contactModuleClick;
	
	@FindBy(xpath="//img[@title='Create Contact...']")
	private WebElement createContactButton;
	
	@FindBy(xpath="//input[@name='lastname']")
	private WebElement ContactlastName;
	
	@FindBy(xpath="//input[@title='Save [Alt+S]']")
	private WebElement saveButton;
	
	@FindBy(xpath="//span[@id='dtlview_Last Name']")
	private WebElement savedContactLastName;
	
	
	public Contactpage(WebDriver driver) {
		PageFactory.initElements(driver, this);
		
	}
	
	public void contactModule() {
		
		contactModuleClick.click();
		createContactButton.click();
		
	}
	
	public void contactLastName(String lastName) {
		ContactlastName.sendKeys(lastName);
	}
	
	public void savebutton() {
		saveButton.click();
		
	}
	
	public String actualContactSavedLastName() {
		return savedContactLastName.getText();
	}

}
