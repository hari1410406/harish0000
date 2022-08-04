package org.vtiger.practice;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class FetchTheDataFromPropertyFile {

	public static void main(String[] args) throws IOException {
		FileInputStream fis=new FileInputStream("./src/test/resources/commonData.properties");
		Properties properties=new Properties();
		properties.load(fis);
		String url = properties.getProperty("url").trim();
		String userName = properties.getProperty("username").trim();
		String passWord = properties.getProperty("password").trim();
		String browser = properties.getProperty("browser").trim();
		String timeouts = properties.getProperty("timeout").trim();
		System.out.println(url);
		System.out.println(userName);
		System.out.println(passWord);
		System.out.println(browser);
		System.out.println(timeouts);

	}

}
