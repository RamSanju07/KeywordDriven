package com.base;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class BasePage {

	public WebDriver driver;
	public Properties prop;

	public WebDriver driverLaunch(String browserName) {

		if (browserName.equals("chrome")) {

			System.setProperty("webdriver.chrome.driver",
					"C:\\Users\\ramac\\eclipse-neon\\DummyKeywordDrivenFramework\\Driver\\chromedriver.exe");

				driver = new ChromeDriver();
				driver.manage().window().maximize();
			}
		return driver;
		}


	

	public Properties propertiesInit() throws IOException {

		prop = new Properties();
		FileInputStream fi = new FileInputStream("C:\\Users\\ramac\\eclipse-neon\\DummyKeywordDrivenFramework\\"
				+ "src\\main\\java\\com\\config\\config.properties");

		prop.load(fi);

		return prop;

	}

}
