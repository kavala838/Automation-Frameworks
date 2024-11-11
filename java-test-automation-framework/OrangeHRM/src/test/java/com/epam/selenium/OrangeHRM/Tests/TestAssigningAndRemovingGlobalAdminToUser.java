package com.epam.selenium.OrangeHRM.Tests;

import static org.testng.Assert.assertFalse;

import java.net.MalformedURLException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.epam.selenium.OrangeHRM.DriverFactory.DriverFactoryProvider;
import com.epam.selenium.OrangeHRM.PomPages.DashBoardPage;
import com.epam.selenium.OrangeHRM.PomPages.LogInPage;
import com.epam.selenium.OrangeHRM.PomPages.UsersPage;
import com.epam.selenium.OrangeHRM.Utilities.ExtentReportsClass;



public class TestAssigningAndRemovingGlobalAdminToUser {
	  WebDriver driver;
	  LogInPage logInPage;
	  DashBoardPage dashBoardPage;
	  UsersPage usersPage;
	  String platformName;
	  
	    //SetUp Navigates to DashBoard Page
		@BeforeClass
		public void setUp(ITestContext context) throws MalformedURLException {
			String driverName=(String)context.getCurrentXmlTest().getParameter("driverName");
			platformName=(String)context.getCurrentXmlTest().getParameter("PlatformName");
			driver=DriverFactoryProvider.getDriverFactory(platformName).getWebDriver(driverName);
			
			String url=context.getCurrentXmlTest().getParameter("OrangeHRMUrl");
			driver.manage().window().maximize();
			driver.get(url);
			
			logInPage=new LogInPage(driver);
			
			ExtentReportsClass.initExtentReports(context);
			
			String userName=context.getCurrentXmlTest().getParameter("userName");
			String password=context.getCurrentXmlTest().getParameter("Password");
			dashBoardPage=logInPage.login(userName, password);
			usersPage=new UsersPage(driver);
		
			context.setAttribute("WebDriver", driver);
		}
		
		@Test(priority=1)
		public void testNavigationToUsersPageFromDashBoardPage() {
			
			dashBoardPage.clickOnElementInMenuContent("Admin")
				         .clickOnElementInSubMenuContent("User Management")
				         .clickOnElementInSubMenuContent("Users");
			
			assert(driver.getCurrentUrl().contains("systemUsers"));
		}
		
		@Test(priority=2)
		public void testAssigningGlobalAdminRole(ITestContext context) throws InterruptedException {
			String username=context.getCurrentXmlTest().getParameter("user");
			
			boolean isGlobalAdminAssigned=
		    usersPage.AssignGlobalAdminRoleToTheUser(username)
					 .checkTheUserWhetherAssignedToGivenRole(username, "Global Admin");
			
			assert(isGlobalAdminAssigned);
		}
		
		@Test(priority=3)
		public void testRemovingGlobalAdminRole(ITestContext context) throws InterruptedException {
			String username=context.getCurrentXmlTest().getParameter("user");
			
			boolean isGlobalAdminAssigned=
			usersPage.RemoveGlobalAdminRoleToTheUser(username)
					 .checkTheUserWhetherAssignedToGivenRole(username, "Global Admin");
			
			assertFalse(isGlobalAdminAssigned);
		}
		@AfterMethod
		public void setStatus(ITestResult result) {
			if (platformName.equalsIgnoreCase("SauceWebDriver")) {
				String resultFortest = result.isSuccess() ? "passed" : "failed";
				((RemoteWebDriver) driver).executeScript("sauce:job-result=" + resultFortest);
			}
			
		}
		
		  @AfterClass 
		  public void EndReportTest() { 
			  ExtentReportsClass.endReport();
			  driver.quit();
			  }
		 
}
