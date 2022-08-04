package org.vtiger.practice;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class JavaScriptExePractice {
	
	public static void main(String[] args) {
		
		WebDriverManager.chromedriver().setup();
		WebDriver driver=new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://www.amazon.in/");
		
		JavascriptExecutor js=(JavascriptExecutor) driver;
		
		/*
		 * 1. Scroll to specific co-ordinates(x,Y co-ordinates)
		 * Window.scroll()  
		 * window.scrollBy()
		 * window.scrollTo(0,2500) first Zero [0] then value means vertical scrolling 
		 */
		
	//	js.executeScript("window.scrollTo(0,2500);");
		
	//	WebElement essentialNeeds = driver.findElement(By.xpath("//span[@class='a-size-small a-color-base truncate-2line']/parent::div[@class='a-section a-spacing-none image-label aok-inline-block aok-align-center']/child::span[text()='Daily essentials']"));
	//	js.executeScript("arguments[0].scrollIntoView(true)",essentialNeeds);
		
		WebElement uptoPerc = driver.findElement(By.xpath("//h2[.='Save 10% with less effort']"));
		js.executeScript("arguments[0].scrollIntoView(true)", uptoPerc);
		System.out.println(uptoPerc.getText());
	}

}
