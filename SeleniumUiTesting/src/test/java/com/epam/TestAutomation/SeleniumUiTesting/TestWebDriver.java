package com.epam.TestAutomation.SeleniumUiTesting;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.Test;

public class TestWebDriver {
	@Test
	public static void testDriver() {
		String driverPath="C:\\BrowserDrivers\\geckodriver\\geckodriver.exe";
		System.setProperty("webdriver.gecko.driver", driverPath);
		WebDriver driver=new FirefoxDriver();
		
		String chromeDriverPath="C:\\BrowserDrivers\\chromedriver_win32\\chromedriver.exe";
		System.setProperty("webdriver.chrome.driver", chromeDriverPath);
		WebDriver chromeDriver=new ChromeDriver();
	}
}
