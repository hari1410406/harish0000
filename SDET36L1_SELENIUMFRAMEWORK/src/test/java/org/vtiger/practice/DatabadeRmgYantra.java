package org.vtiger.practice;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.Duration;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import com.mysql.cj.jdbc.Driver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class DatabadeRmgYantra {

	public static void main(String[] args) throws SQLException {
		WebDriverManager.chromedriver().setup();
		WebDriver driver=new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().window().maximize();
		Random ran=new Random();
		int randomNumber=ran.nextInt(1000);
		String projectName = "SDET36_"+randomNumber;
		driver.get("http://localhost:8084/");
		driver.findElement(By.xpath("//input[@id='usernmae']")).sendKeys("rmgyantra");
		driver.findElement(By.xpath("//input[@id='inputPassword']")).sendKeys("rmgy@9999");
		driver.findElement(By.xpath("//button[text()='Sign in']")).click();
		driver.findElement(By.xpath("//a[text()='Projects']")).click();
		driver.findElement(By.xpath("//span[text()='Create Project']")).click();

		driver.findElement(By.xpath("//input[@name='projectName']")).sendKeys(projectName);
		driver.findElement(By.xpath("//input[@name='createdBy']")).sendKeys("mohan");
		WebElement projectStatusDropDown = driver.findElement(By.xpath("//label[text()='Project Status ']/following-sibling::select"));
		Select select=new Select(projectStatusDropDown);
		select.selectByVisibleText("On Goging");
		driver.findElement(By.xpath("//input[@class='btn btn-success']")).click();

		System.out.println("project succesfully created");

		Driver dbdriver=new Driver();	
		DriverManager.registerDriver(dbdriver);
		ResultSet result = DriverManager.getConnection("jdbc:mysql://localhost:3306/projects","root","root").createStatement().executeQuery("select * from project;");
		while(result.next()) {
			if(result.getString("project_name").equals(projectName)){
				System.out.println("project name is present");
				System.out.println("Actual project name --->"+result.getString("project_name"));
			}
		}

		driver.quit();
	}

}
