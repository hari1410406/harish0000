package org.vtiger.practice;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class DataProviderPractice {
	
	
	@Test(dataProvider="loginData")
	public void TestLogin(String username, String password) {
		WebDriverManager.chromedriver().setup();
		WebDriver driver=new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
	//	driver.get("https://demo.actitime.com/");
		driver.get("http://localhost:8888/");
		driver.findElement(By.xpath("//input[@name='user_name']")).sendKeys(username);
		driver.findElement(By.xpath("//input[@name='user_password']")).sendKeys(password);
		driver.findElement(By.xpath("//input[@id='submitButton']")).click();
		Assert.assertTrue(driver.findElement(By.xpath("//a[text()='Contacts']")).isDisplayed());
		driver.quit();
		
	}
	
	@DataProvider
	public Object[][] loginData() {
		Object[][] data=new Object[3][2];
		data[0][0]="admin";
		data[0][1]="admin";
		
		
		data[1][0]="admin1";
		data[1][1]="admin2";
		
		data[1][0]="admin2";
		data[1][1]="admin3";

		
		return data;
	}
}
