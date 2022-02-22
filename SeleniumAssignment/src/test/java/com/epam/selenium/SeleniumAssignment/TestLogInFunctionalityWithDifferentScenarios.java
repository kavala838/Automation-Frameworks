package com.epam.selenium.SeleniumAssignment;

import java.net.MalformedURLException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.testng.ITestContext;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.epam.selenium.SeleniumAssignment.ExtentReport.ExtentReportsClass;
import com.epam.selenium.SeleniumAssignment.Listeners.TestListenerImpl;
import com.epam.selenium.SeleniumAssignment.PageFactory.LogInPage;
import com.epam.selenium.SeleniumAssignment.WebDriverFactory.GridDriverFactory;
import com.epam.selenium.SeleniumAssignment.WebDriverFactory.webDriverFactory;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import com.epam.selenium.SeleniumAssignment.PageFactory.*;
@Listeners(TestListenerImpl.class)
public class TestLogInFunctionalityWithDifferentScenarios {
	WebDriver driver;
	 
	 
	@FindBy(xpath="//div[text()=\"Retry Login\"]")
	private WebElement RetryLoginElement;
	ExtentReports report;
	ExtentTest test;
	LogInPageWithJSExecutor logInPageWithJSExecutor;
	@Parameters({"driverName"})
	@BeforeClass
	public void setUp(String driverName,ITestContext context) throws MalformedURLException {
		
		driver =GridDriverFactory.getWebDriver(driverName);
		driver.manage().window().maximize();
		logInPageWithJSExecutor=new LogInPageWithJSExecutor(driver);
		PageFactory.initElements(new AjaxElementLocatorFactory(driver,10), this);
		//driver.get("https://prasoonr-trials73.orangehrmlive.com/");
		context.setAttribute("WebDriver", driver);
		

		report = ExtentReportsClass.getExtentReportsObject();
		test = report.startTest("ExtentDemo");
		context.setAttribute("ExtentTest", test);
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
		/*
		 * if(true) { test.log(LogStatus.PASS,"Error Message Displayed"); } else {
		 * test.log(LogStatus.ERROR, "Error Message Not Displayed"); }
		 */
	}
	@AfterClass
	public void closetab() throws InterruptedException {
		Thread.sleep(3000);
		report.endTest(test);
		report.flush();
		driver.quit();
	}
}
