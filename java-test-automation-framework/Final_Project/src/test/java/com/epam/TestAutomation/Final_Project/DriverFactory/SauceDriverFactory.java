package com.epam.TestAutomation.Final_Project.DriverFactory;

import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

 public class SauceDriverFactory extends DriverFactory {
	private static final String hubURL="http://localhost:4444/wd/hub";
	static RemoteWebDriver driver;
	public  WebDriver getWebDriver(String drivername) throws  MalformedURLException{
		// setting up SAUCE properties/credenials
				MutableCapabilities sauceOptions = new MutableCapabilities();
				sauceOptions.setCapability("username", "oauth-Vinaykumar_Kavala-4d0bf");
				sauceOptions.setCapability("accessKey", "d33937d5-ec39-44c8-9c8f-f732e32e93cd");
				sauceOptions.setCapability("browserVersion", "latest");
				

				URL url = new URL("https://oauth-Vinaykumar_Kavala-4d0bf:d33937d5-ec39-44c8-9c8f-f732e32e93cd@ondemand.eu-central-1.saucelabs.com:443/wd/hub");
				
				if(drivername.equalsIgnoreCase("chrome")) {
					ChromeOptions options = new ChromeOptions();
					options.setCapability("sauce:options", sauceOptions);
					driver = new RemoteWebDriver(url, options);
					return driver;
				}
				else if(drivername.equalsIgnoreCase("firefox")){
					FirefoxOptions options = new FirefoxOptions();
					options.setCapability("sauce:options", sauceOptions);
					driver = new RemoteWebDriver(url, options);
					return driver;
				}
				return new ProxyWebDriver(driver);
	}
}
