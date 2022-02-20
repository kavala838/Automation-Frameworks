package com.epam.selenium.SeleniumAssignment;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.epam.selenium.SeleniumAssignment.PageFactory.LogInPage;
import com.epam.selenium.SeleniumAssignment.PageFactory.LogInPageWithJSExecutor;
import com.epam.selenium.SeleniumAssignment.WebDriverFactory.webDriverFactory;

public class TestLogInFunctionalityWithDifferentScenarios {
	WebDriver driver;
	
	@FindBy(xpath="//div[text()=\"Retry Login\"]")
	private WebElement RetryLoginElement;
	
	LogInPageWithJSExecutor logInPageWithJSExecutor;
	@Parameters({"driverName"})
	@BeforeClass
	public void setUp(String driverName) {
		
		driver =webDriverFactory.getDriver(driverName);
		driver.manage().window().maximize();
		logInPageWithJSExecutor=new LogInPageWithJSExecutor(driver);
		PageFactory.initElements(new AjaxElementLocatorFactory(driver,5), this);
		//driver.get("https://prasoonr-trials73.orangehrmlive.com/");
		
	}
	@Test(enabled=false)
	public void testLogIn() {
		driver.get("https://prasoonr-trials73.orangehrmlive.com/");
		
		logInPageWithJSExecutor.login("Admin", "U@qBLVtm09");
		
		System.out.println(driver.getCurrentUrl());
		
		assert(driver.getCurrentUrl().contains("dashboard"));
		
	}
	@Test(enabled=true)
	public void testLogInWithInvalidCredentials() {
		driver.get("https://prasoonr-trials73.orangehrmlive.com/");
		
		logInPageWithJSExecutor.login("Admin1", "U@qBLVtm09");
		
		assert(RetryLoginElement.isDisplayed());
	}
}
