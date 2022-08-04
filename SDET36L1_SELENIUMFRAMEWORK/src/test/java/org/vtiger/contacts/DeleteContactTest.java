package org.vtiger.contacts;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.List;
import java.util.Properties;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;

public class DeleteContactTest {

	public static void main(String[] args) throws IOException {

		FileInputStream fis=new FileInputStream("./src/test/resources/commonData.properties");
		Properties properties=new Properties();
		properties.load(fis);
		String url = properties.getProperty("url");
		String userName = properties.getProperty("username").trim();
		String passWord = properties.getProperty("password").trim();
		String browser = properties.getProperty("browser").trim();
		String timeouts = properties.getProperty("timeout").trim();
		WebDriver driver=null;
		long longTimeout = Long.parseLong(timeouts);
		if(browser.equals("chrome")) {
			WebDriverManager.chromedriver().setup();
			driver=new ChromeDriver();
		}
		else if(browser.equals("firefox"))
		{
			WebDriverManager.firefoxdriver().setup();
			WebDriver driver1=new ChromeDriver();
		}
		else if(browser.equals("InternetExplorer"))
		{
			WebDriverManager.iedriver().setup();
			WebDriver driver1=new InternetExplorerDriver();
		}
		else {
			System.out.println("you are not connected to any browser");
		}
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(longTimeout));
		driver.manage().window().maximize();
		Random ran=new Random();
		int randomNumber = ran.nextInt(1000);
		String expectedLastName="SDET"+randomNumber;
		driver.get(url);
		driver.findElement(By.xpath("//input[@name='user_name']")).sendKeys(userName);
		driver.findElement(By.xpath("//input[@name='user_password']")).sendKeys(passWord);
		driver.findElement(By.xpath("//input[@id='submitButton']")).click();
		driver.findElement(By.xpath("//a[text()='Contacts']")).click();
		driver.findElement(By.xpath("//img[@title='Create Contact...']")).click();
		driver.findElement(By.xpath("//input[@name='lastname']")).sendKeys(expectedLastName);
		driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();
		
		//After saving updated date by Administrator
		WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(longTimeout));
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[contains(text(),'By  Administrator')]")));
		
		//click on contacts [corner]
		driver.findElement(By.xpath("//a[@class='hdrLink']")).click();

		//	wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//a[text()='Go to Advanced Search']")));
		//driver.findElement(By.xpath(""))
		
        // click on that forward button like image which takes to last page
		driver.findElement(By.xpath("//a[@alt='Last']//img[@src='themes/images/end.gif']")).click();
		
		//Inspect the status bar when navigating from table to table
		WebElement invisibleStatusBar = driver.findElement(By.xpath("//div[@id='status']/img"));
		//use WebDriverWait for that status bar
		WebDriverWait wait1=new WebDriverWait(driver, Duration.ofSeconds(longTimeout));
		wait1.until(ExpectedConditions.visibilityOf(invisibleStatusBar));
		
		WebElement text = driver.findElement(By.xpath("//a[text()='"+expectedLastName+"']"));
		
		WebDriverWait wait2=new WebDriverWait(driver, Duration.ofSeconds(longTimeout));
		wait1.until(ExpectedConditions.visibilityOf(text));
		
		//finding all the LastNames present in the table
		List<WebElement> listOfLastNames = driver.findElements(By.xpath("//table[@class='lvt small']/tbody/tr/td[4]/a"));

		//Iterating all the LastNames
		for(WebElement totalNames:listOfLastNames) {
			//printing all the LastNames
			System.out.println(totalNames.getText());
			if(totalNames.getText().equals(expectedLastName)) {
				
				//Exact LastName in that Table that need to be deleted checkbox
				driver.findElement(By.xpath("//a[text()='"+expectedLastName+"']/ancestor::td[1]/ancestor::tr[1]/td/input")).click();
				break;
			}
		}
		
		//Delete button
		driver.findElement(By.xpath("//input[@value='Delete']")).click();
		
		//Handling Alert Pop-up
		driver.switchTo().alert().accept();
		
		System.out.println("-------------->");
		System.out.println("contact deleted successfully");
		
		//SignOut

		WebElement administratorIcon = driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']"));
		Actions act=new Actions(driver);
		act.moveToElement(administratorIcon).perform();
		driver.findElement(By.xpath("//a[text()='Sign Out']")).click();
		driver.quit();

	}

}
