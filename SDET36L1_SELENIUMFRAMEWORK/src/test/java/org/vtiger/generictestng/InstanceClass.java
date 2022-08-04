package org.vtiger.generictestng;

import org.openqa.selenium.WebDriver;
import org.vtiger.ObjectRepository.LoginPage;
import org.vtiger.ObjectRepository.LogoutPage;
import org.vtiger.genericUtility.ExcelUtility;
import org.vtiger.genericUtility.JavaUtility;
import org.vtiger.genericUtility.PropertyFileUtility;
import org.vtiger.genericUtility.seleniumWebDriverUtility;

public class InstanceClass {
	
	public PropertyFileUtility fileUtility;
	public	ExcelUtility excelUtility;
	public seleniumWebDriverUtility webutil;
	public JavaUtility javaUtility;
	public int randomNumber ;
	public	String url;
	public	String userName ;
	public	String passWord;
	public	String browser;
	public	String timeouts ;
	public	long longTimeout;
	public WebDriver driver;
	public LoginPage loginPage;
	public LogoutPage logoutPage;


}
