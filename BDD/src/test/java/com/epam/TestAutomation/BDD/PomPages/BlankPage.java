package com.epam.TestAutomation.BDD.PomPages;

import org.openqa.selenium.WebDriver;

public class BlankPage {
	private final WebDriver driver;
	public BlankPage(WebDriver driver) {
		this.driver=driver;
	}
	public LogInPage navigateToLogInPage(String url) {
		driver.get(url);
		return new LogInPage(driver);
	}
}
