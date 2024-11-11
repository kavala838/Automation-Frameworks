package com.epam.TestAutomation.BDD.DriverFactory;

import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

 public class GridDriverFactory extends DriverFactory{
	private static final String hubURL="http://localhost:4444/wd/hub";
	 public WebDriver getWebDriver(String DriverName) throws MalformedURLException{
		WebDriver driver=null;
		DesiredCapabilities capabilities=new DesiredCapabilities();
		capabilities.setPlatform(Platform.WIN10);
		if(DriverName.equalsIgnoreCase("chrome")) {
			capabilities.setBrowserName("chrome");
			driver=new RemoteWebDriver(new URL(hubURL),capabilities);
		}
		else if(DriverName.equalsIgnoreCase("firefox")) {
			capabilities.setBrowserName("firefox");
			driver=new RemoteWebDriver(new URL(hubURL),capabilities);
		}
		return new ProxyWebDriver(driver);
	}
}