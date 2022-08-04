package org.vtiger.ObjectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {

	@FindBy(xpath="//input[@name='user_name']")
	private WebElement usernameTextbox;
	
	@FindBy(xpath="//input[@name='user_password']")
	private WebElement passwordTextBox;
	
	
	@FindBy(xpath="//input[@id='submitButton']")
	private WebElement loginBtn;
	
	
	public LoginPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}
	
	
	public void setLoginPage(String userName ,String password) {
		usernameTextbox.sendKeys(userName);
		passwordTextBox.sendKeys(password);
		loginBtn.click();
		
		
	}
}
