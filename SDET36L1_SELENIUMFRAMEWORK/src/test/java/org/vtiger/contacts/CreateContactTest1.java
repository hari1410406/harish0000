package org.vtiger.contacts;

import java.time.Duration;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import io.github.bonigarcia.wdm.WebDriverManager;

public class CreateContactTest1 {

	public static void main(String[] args) {
		
		WebDriverManager.chromedriver().setup();
		WebDriver driver=new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().window().maximize();
		Random ran=new Random();
		int randomNumber=ran.nextInt(100);
		String expectedlastName="Hari"+randomNumber;
		driver.get("http://localhost:8888/");
		driver.findElement(By.xpath("//input[@name='user_name']")).sendKeys("admin");
		driver.findElement(By.xpath("//input[@name='user_password']")).sendKeys("admin");
		driver.findElement(By.xpath("//input[@id='submitButton']")).click();
		driver.findElement(By.xpath("//a[text()='Contacts']")).click();
		driver.findElement(By.xpath("//img[@title='Create Contact...']")).click();
		driver.findElement(By.xpath("//input[@name='lastname']")).sendKeys(expectedlastName);
		driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();
		String actualLastName = driver.findElement(By.xpath("//span[@id='dtlview_Last Name']")).getText();
		//validate the  actual and expected names
		if(actualLastName.equals(expectedlastName)) {
			System.out.println("Test case is passed when actual and expected last name is matched");
		}
		else {
			System.out.println("test case is failed when actual and expected last name is not matched");
		}
		
		WebElement administratorIcon = driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']"));
		Actions act=new Actions(driver);
		act.moveToElement(administratorIcon).perform();
		driver.findElement(By.xpath("//a[text()='Sign Out']")).click();
		driver.quit();
		
		

	}

}
