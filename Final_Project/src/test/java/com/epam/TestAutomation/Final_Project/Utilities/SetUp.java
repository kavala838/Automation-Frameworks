package com.epam.TestAutomation.Final_Project.Utilities;

import java.net.MalformedURLException;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;

import com.epam.TestAutomation.Final_Project.DriverFactory.DriverFactoryProvider;
import com.epam.TestAutomation.Final_Project.PomPages.DashBoardPage;
import com.epam.TestAutomation.Final_Project.PomPages.SignInPage;
import com.epam.TestAutomation.Final_Project.PomPages.SignUpPage;
import com.paulhammant.ngwebdriver.NgWebDriver;





public class SetUp {
	static WebDriver driver;
	private static JavascriptExecutor js;
	private static NgWebDriver ngDriver;
	public static WebDriver setTillDashBoardPage(ITestContext context) throws MalformedURLException, InterruptedException {
		// TODO Auto-generated method stub
		
		String driverName=(String)context.getCurrentXmlTest().getParameter("driverName");
		String platformName=(String)context.getCurrentXmlTest().getParameter("PlatformName");
		driver=DriverFactoryProvider.getDriverFactory(platformName).getWebDriver(driverName);
		String url=context.getCurrentXmlTest().getParameter("Url");
		driver.get(url);
		
		js=(JavascriptExecutor)driver;
		ngDriver=new NgWebDriver(js);
		
		String emailID=context.getCurrentXmlTest().getParameter("EmailID");
		String password=context.getCurrentXmlTest().getParameter("Password");
		
		SignInPage signInPage=new SignInPage(driver);
		signInPage.clickOnSignUp();
		ngDriver.waitForAngularRequestsToFinish();
		
		SignUpPage signUpPage=new SignUpPage(driver);
		signUpPage.signUp(emailID, password);
		ngDriver.waitForAngularRequestsToFinish();
		
	
		
		
		signInPage.login(emailID, password)
				  .clickOnElementInMenuContent("Master Data management")
				  .clickOnElementInSubMenuContent("Speakers");
		
		return driver;
	}

}
