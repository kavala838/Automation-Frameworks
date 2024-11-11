package com.epam.TestAutomation.Project.Selenium.WebFactory;

import java.net.MalformedURLException;

import org.openqa.selenium.WebDriver;

 public abstract class DriverFactory{
	public abstract WebDriver getWebDriver(String DriverName) throws MalformedURLException;
}
