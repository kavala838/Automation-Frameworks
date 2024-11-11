package com.epam.TestAutomation.Final_Project.DriverFactory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

 public class WebDriverFactory extends DriverFactory{
	 public WebDriver getWebDriver(String driverName) {
		if(driverName.equals("firefox"))
		{
			String driverPath = "C:\\BrowserDrivers\\geckodriver\\geckodriver.exe";
			System.setProperty("webdriver.gecko.driver", driverPath);
			return new ProxyWebDriver(new FirefoxDriver());
		}
		String chromeDriverPath="C:\\BrowserDrivers\\chromedriver_win32\\chromedriver.exe";
		System.setProperty("webdriver.chrome.driver", chromeDriverPath); 
		return new ProxyWebDriver(new ChromeDriver());
	}
}
