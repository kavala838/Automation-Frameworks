package com.epam.selenium.SeleniumAssignment;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.epam.selenium.SeleniumAssignment.PageFactory.ApplyLeavePage;
import com.epam.selenium.SeleniumAssignment.PageFactory.DashBoardPage;
import com.epam.selenium.SeleniumAssignment.PageFactory.LeaveListPage;
import com.epam.selenium.SeleniumAssignment.PageFactory.LogInPage;
import com.epam.selenium.SeleniumAssignment.PageFactory.UsersPage;
import com.epam.selenium.SeleniumAssignment.WebDriverFactory.webDriverFactory;

public class TestAssigningAndRemovingRolesToUsers {
	  WebDriver driver;
	  LogInPage logInPage;
	  DashBoardPage dashBoardPage;
	  UsersPage usersPage;
	  
		@Parameters({"driverName"})
		@BeforeClass
		public void setUp(String driverName) {
			
			driver =webDriverFactory.getDriver(driverName);
			driver.manage().window().maximize();
			
			driver.get("https://prasoonr-trials73.orangehrmlive.com/");
			
			logInPage=new LogInPage(driver);
			
			logInPage.login("Admin", "U@qBLVtm09");
			
			dashBoardPage= new DashBoardPage(driver);
			usersPage=new UsersPage(driver);
		}
		@Test(priority=1)
		public void testNavigationToUsersPageFromDashBoardPage() {
			dashBoardPage.clickOnElementInMenuContent("Admin")
				         .clickOnElementInSubMenuContent("User Management")
				         .clickOnElementInSubMenuContent("Users");
			
			assert(driver.getCurrentUrl().contains("systemUsers"));
		}
		
		@Test(priority=2)
		public void testAssigningGlobalAdminRole() throws InterruptedException {
			boolean isGlobalAdminAssigned=usersPage.AssignGlobalAdminRoleToTheUser("amanda")
					 .checkTheUserWhetherAssignedToGivenRole("amanda", "Global Admin");
			assert(isGlobalAdminAssigned);
		}
		@Test(priority=3)
		public void testRemovingGlobalAdminRole() throws InterruptedException {
			boolean isGlobalAdminAssigned=usersPage.RemoveGlobalAdminRoleToTheUser("amanda")
					  .checkTheUserWhetherAssignedToGivenRole("amanda", "Global Admin");
			assertFalse(isGlobalAdminAssigned);
		}
		
}
