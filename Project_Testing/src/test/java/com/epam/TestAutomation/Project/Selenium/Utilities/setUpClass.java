package com.epam.TestAutomation.Project.Selenium.Utilities;

import java.net.MalformedURLException;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;

import com.epam.TestAutomation.Project.Selenium.PomPages.DashBoardPage;
import com.epam.TestAutomation.Project.Selenium.PomPages.LogInPage;
import com.epam.TestAutomation.Project.Selenium.WebFactory.DriverFactoryProvider;

public class setUpClass {
	static WebDriver driver;
	public static WebDriver setTillDashBoardPage(ITestContext context) throws MalformedURLException {
		// TODO Auto-generated method stub
		String driverName=(String)context.getCurrentXmlTest().getParameter("driverName");
		String platformName=(String)context.getCurrentXmlTest().getParameter("PlatformName");
		driver=DriverFactoryProvider.getDriverFactory(platformName).getWebDriver(driverName);
		String url=context.getCurrentXmlTest().getParameter("OrangeHRMUrl");
		driver.get(url);
		LogInPage logInPage=new LogInPage(driver);
		//ExtentReportsClass.initExtentReports(context);
		String userName=context.getCurrentXmlTest().getParameter("userName");
		String password=context.getCurrentXmlTest().getParameter("Password");
		DashBoardPage dashBoardPage=logInPage.login(userName, password);
		return driver;
	}
	
}
