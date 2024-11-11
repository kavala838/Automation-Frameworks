package com.epam.selenium.SeleniumSauce.Tests;

import java.net.MalformedURLException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.testng.ITestContext;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.epam.selenium.SeleniumSauce.PageFactory.LogInPageWithJSExecutor;
import com.epam.selenium.SeleniumSauce.WebsauceFactory.WebSauceDriverFactory;



public class TestLogInScenariosOrangeHRM {
	WebDriver driver;
	 
	 
	@FindBy(xpath="//div[text()=\"Retry Login\"]")
	private WebElement RetryLoginElement;
	
	LogInPageWithJSExecutor logInPageWithJSExecutor;
	@Parameters({"driverName"})
	@BeforeClass
	public void setUp(String driverName,ITestContext context) throws MalformedURLException {
		
		driver =WebSauceDriverFactory.getWebDriver(driverName);
		driver.manage().window().maximize();
		logInPageWithJSExecutor=new LogInPageWithJSExecutor(driver);
		PageFactory.initElements(new AjaxElementLocatorFactory(driver,5), this);
		//driver.get("https://prasoonr-trials73.orangehrmlive.com/");
		context.setAttribute("WebDriver", driver);
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
	@AfterClass
	public void closeTab() {
		driver.quit();
	}
}
