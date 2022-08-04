package org.vtiger.practice;

import java.time.Duration;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class ShadowRootSearch {

	public static void main(String[] args) {
		WebDriverManager.chromedriver().setup();
		WebDriver driver=new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		driver.get("chrome://downloads/");
		JavascriptExecutor js=(JavascriptExecutor) driver;
		WebElement searchText = (WebElement)js.executeScript(" return document.querySelector('downloads-manager').shadowRoot.querySelector('downloads-toolbar').shadowRoot.querySelector('cr-toolbar').shadowRoot.querySelector('cr-toolbar-search-field').shadowRoot.querySelector('input')");

		//String jse = "argurments[0].setAttribute('value','hii')";

		js.executeScript("arguements[0].value=arguements[1]",searchText,"SDET36L1");
	}

}
