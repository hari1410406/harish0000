package org.vtiger.practice;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.Duration;
import java.util.List;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import com.mysql.jdbc.Driver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class ValidateGuiWrto_YmgTestYantra {

	public static void main(String[] args) throws SQLException {
		Random ran=new Random();
		int randomNumber=ran.nextInt(100);
		String expectedProjectName="SDET_7"+randomNumber;
		System.out.println("ExpectedProjectName ==>"+expectedProjectName);
		Driver driver=new Driver();
		DriverManager.registerDriver(driver);
		Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/projects","root","root");
		Statement statement = connection.createStatement();
		statement.executeUpdate("insert into project values('TY_proj_7"+randomNumber+"','Mohan','23/06/2022','"+expectedProjectName+"', 'still not completed', 7)");
		System.out.println("project updated into database sucessfully");
		WebDriverManager.chromedriver().setup();
		WebDriver databasedriver=new ChromeDriver();
		System.out.println("Browser launched sucessfully");
		databasedriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		databasedriver.manage().window().maximize();
		databasedriver.get("http://localhost:8084/");
		databasedriver.findElement(By.xpath("//input[@id='usernmae']")).sendKeys("rmgyantra");
		databasedriver.findElement(By.xpath("//input[@id='inputPassword']")).sendKeys("rmgy@9999");
		databasedriver.findElement(By.xpath("//button[text()='Sign in']")).click();
		databasedriver.findElement(By.xpath("//a[text()='Projects']")).click();
		databasedriver.findElement(By.xpath("//span[text()='Create Project']")).click();
		List<WebElement> listOfProjects = databasedriver.findElements(By.xpath("//table[@class='table table-striped table-hover']/tbody/tr/td[2]"));

		for(WebElement project:listOfProjects) {
			String actualProjectName=project.getText();
			if(actualProjectName.equals(expectedProjectName)) {
				System.out.println("project is present in the list of projects");
				System.out.println("ActualProjectName ==>"+actualProjectName);
			}
		}
		databasedriver.quit();
	}

}
