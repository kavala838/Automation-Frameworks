package com.epam.selenium.SeleniumAssignment;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.epam.selenium.SeleniumAssignment.PageFactory.LogInPage;
import com.epam.selenium.SeleniumAssignment.PageFactory.LogInPageWithJSExecutor;
import com.epam.selenium.SeleniumAssignment.WebDriverFactory.webDriverFactory;

public class TestLogInFunctionalityWithDifferentScenarios {
	WebDriver driver;
	LogInPageWithJSExecutor logInPageWithJSExecutor;
	@Parameters({"driverName"})
	@BeforeClass
	public void setUp(String driverName) {
		
		driver =webDriverFactory.getDriver(driverName);
		driver.manage().window().maximize();
		
		driver.get("https://prasoonr-trials73.orangehrmlive.com/");
	}
	@Test
	public void testLogIn() {
		logInPageWithJSExecutor=new LogInPageWithJSExecutor(driver);
		
		logInPageWithJSExecutor.login("Admin", "U@qBLVtm09");
	}
}
