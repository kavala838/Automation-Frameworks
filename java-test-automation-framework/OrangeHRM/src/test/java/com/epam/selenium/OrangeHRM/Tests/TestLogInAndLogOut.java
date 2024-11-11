package com.epam.selenium.OrangeHRM.Tests;

import java.net.MalformedURLException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;


import com.epam.selenium.OrangeHRM.DriverFactory.DriverFactoryProvider;
import com.epam.selenium.OrangeHRM.PomPages.BlankPage;
import com.epam.selenium.OrangeHRM.PomPages.DashBoardPage;
import com.epam.selenium.OrangeHRM.PomPages.LogInPage;
import com.epam.selenium.OrangeHRM.Utilities.ExtentReportsClass;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;


public class TestLogInAndLogOut {
	WebDriver driver;
	LogInPage logInPage;
	DashBoardPage dashBoardPage;
	String platformName;
	@BeforeClass
	public void setUp(ITestContext context) throws MalformedURLException {
		String driverName=(String)context.getCurrentXmlTest().getParameter("driverName");
		platformName=(String)context.getCurrentXmlTest().getParameter("PlatformName");
		driver=DriverFactoryProvider.getDriverFactory(platformName).getWebDriver(driverName);
		String url=context.getCurrentXmlTest().getParameter("OrangeHRMUrl");
		driver.get(url);
		logInPage=new LogInPage(driver);
		ExtentReportsClass.initExtentReports(context);
	}
	
	@Test(priority=1)
	public void testLogInWithValidCredentials(ITestContext context) {
		String userName=context.getCurrentXmlTest().getParameter("userName");
		String password=context.getCurrentXmlTest().getParameter("Password");
		dashBoardPage=logInPage.login(userName, password);
		assert(driver.getCurrentUrl().contains("dashboard"));
	}
	@Test(priority=2)
	public void logOut() {
		dashBoardPage.clickOnMenuDropdown().logout();
		assert(driver.getCurrentUrl().contains("login"));
	}
	@AfterMethod
	public void setStatus(ITestResult result) {
		if (platformName.equalsIgnoreCase("SauceWebDriver")) {
			String resultFortest = result.isSuccess() ? "passed" : "failed";
			((RemoteWebDriver) driver).executeScript("sauce:job-result=" + resultFortest);
		}
		
	}
	@AfterClass
	public void closeTab() {
		driver.quit();
	}
	//Comment
}
