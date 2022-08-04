package org.vtiger.practice;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

// BROKEN LINKS are the hyper-links of a web-site which is linked to an empty or non-EXistent links in external Web-pages, when broken link is clicked ,an error message is displayed.
//It is usually because of two causes   1.The Web-site has been renamed or moved
								//		2. Its structure has changed and internal links weren't  modified accordingly.
//hi hello



public class BrokenLinks {

	public static void main(String[] args) {

		WebDriverManager.chromedriver().setup();
		WebDriver driver=new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.get("https://www.facebook.com/");
		
		// Step1: What is the tag which has the link -  a/img
		
		List<WebElement> links = driver.findElements(By.xpath("//a"));
		System.out.println("All links in amazon page  " +"=====>"+ links.size());
		
		//Extract the URL from the tag [a/img]
		
		Set<String> s=new HashSet<>();
		
		for(WebElement attribute:links) {
			String link = attribute.getAttribute("href");
			System.out.println("links =====>"+link);
			
			//Eliminate the duplicate URL's
			
			if(link!=null && !(link.isEmpty())) {
				s.add(link);
				//System.out.println("print it");
			}
		}
		System.out.println(s.size());
		
		HttpURLConnection connection = null;
		int noofBrokenLinks=0;
		for(String url:s) {
			try {
				connection=(HttpURLConnection) new URL(url).openConnection();
				connection.connect();
				int code = connection.getResponseCode();
				if(  code>=400) {
					noofBrokenLinks++;
					System.out.println("Broken link: "+url);
				}
			} catch (MalformedURLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
			System.out.println("number of broken links: "+noofBrokenLinks);
			connection.disconnect();
		
		driver.quit();
		
	}

}
