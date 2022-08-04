package org.vtiger.genericUtility;

import org.openqa.selenium.WebDriver;

public class UtilityObjectsClass {
	
	
	private static ThreadLocal<WebDriver> driver=new ThreadLocal<>();
	private static ThreadLocal<seleniumWebDriverUtility> webdriverUtility=new ThreadLocal<>();
	private static ThreadLocal<JavaUtility> javaUtility=new ThreadLocal<>();
	private static ThreadLocal<ExcelUtility> excelUtility=new ThreadLocal<>();
	private static ThreadLocal<PropertyFileUtility> fileUtility=new ThreadLocal<>();
	//ThreadLocal<PropertyFileUtility> fileUtility=new ThreadLocal<>();
	
	
	public static WebDriver getDriver() {
		return driver.get();
	}
	
	public static void setDriver(WebDriver actdriver) {
		driver.set(actdriver);
	}
	
	public static seleniumWebDriverUtility getWebdriverUtility() {
		return webdriverUtility.get();
	}
	
	public static  void setWebdriverUtility(seleniumWebDriverUtility actwebdriverUtility) {
		 webdriverUtility.set(actwebdriverUtility);
	}
	
	public static JavaUtility getJavaUtility() {
		return javaUtility.get();
	}
	
	
	public static void setJavaUtility(JavaUtility actjavaUtility) {
	javaUtility.set(actjavaUtility);;
	}
	
	
	public static  ExcelUtility getExcelUtility() {
		return excelUtility.get();
	}
	
	
	public  static void setExcelUtility(ExcelUtility actexcelUtility) {
	excelUtility.set(actexcelUtility);;
	}
	
	
	public static PropertyFileUtility getFileUtility() {
		return fileUtility.get();
	}
	
	
	public static void setFileUtility(PropertyFileUtility actfileUtility) {
		 fileUtility.set(actfileUtility);;
	}

}
