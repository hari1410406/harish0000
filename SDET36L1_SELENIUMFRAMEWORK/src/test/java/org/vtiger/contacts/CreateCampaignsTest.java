package org.vtiger.contacts;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import io.github.bonigarcia.wdm.WebDriverManager;

public class CreateCampaignsTest {
	
	public static void main(String[] args) {
		WebDriverManager.chromedriver().setup();
		WebDriver driver=new ChromeDriver();
		driver.get("http://localhost:8888");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().window().maximize();
		driver.findElement(By.xpath("//input[@name='user_name']")).sendKeys("admin");
		driver.findElement(By.xpath("//input[@name='user_password']")).sendKeys("admin");
		driver.findElement(By.xpath("//input[@id='submitButton']")).click();
		WebElement m = driver.findElement(By.xpath("//a[text()='More']"));
		Actions a=new Actions(driver);
		a.moveToElement(m).perform();
		driver.findElement(By.xpath("//a[text()='Campaigns']")).click();
	
		driver.findElement(By.xpath("//img[@title='Create Campaign...']")).click();
		driver.findElement(By.xpath("//input[@name='campaignname']")).sendKeys("Hari");
		driver.findElement(By.xpath("(//input[@title='Save [Alt+S]'])[1]")).click();
		String act = driver.findElement(By.id("dtlview_Campaign Name")).getText();
		if(act.equals("Hari")) {
			System.out.println("pass");
		}
		else {
			System.out.println("fail");
		}
		WebElement ad = driver.findElement(By.xpath("(//td[@valign='bottom'])[2]"));
		
		Actions b=new Actions(driver);
		a.moveToElement(ad).perform();
		driver.findElement(By.xpath("//a[text()='Sign Out']")).click();
		driver.quit();
	}
		
		
		
		
		

	}


