package com.epam.selenium.SeleniumAssignment;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotVisibleException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.epam.selenium.SeleniumAssignment.PageFactory.ApplyLeavePage;
import com.epam.selenium.SeleniumAssignment.PageFactory.DashBoardPage;
import com.epam.selenium.SeleniumAssignment.PageFactory.LogInPage;
import com.epam.selenium.SeleniumAssignment.WebDriverFactory.webDriverFactory;

public class OrangeHrmApplyLeaveFunctionalityWithDifferentScenarios {
  WebDriver driver;
  LogInPage logInPage;
  DashBoardPage dashBoardPage;
  ApplyLeavePage applyLeavePage;
	
	@Parameters({"driverName"})
	@BeforeClass
	public void setUp(String driverName) {
		
		driver =webDriverFactory.getDriver(driverName);
		driver.manage().window().maximize();
		
		driver.get("https://prasoonr-trials73.orangehrmlive.com/");
		
		logInPage=new LogInPage(driver);
		
		logInPage.login("Admin", "U@qBLVtm09");
		
		dashBoardPage= new DashBoardPage(driver);
		applyLeavePage=new ApplyLeavePage(driver);
	}
	
	
	@Test(priority=1)
	public void testNavigationToApplyLeavePageFromDashBoardPage() {
		
		dashBoardPage.clickOnElementInMenuContent("Leave")
			         .clickOnElementInSubMenuContent("Apply");
		
		assert(driver.getCurrentUrl().contains("leave/apply"));
	}
	
	@Test(priority=2)
	public void testSelectLeaveTypeInDropDownInApplyLeavePage() {
		
		applyLeavePage.setLeaveTypeTo("Sick Leave - US");
		
		String SelectedType=applyLeavePage.getLeaveTypeSelectedInDropDown();
		
		assert(SelectedType.equals("Sick Leave - US"));
		
	}
	@Test(priority=3)
	public void testErrorMessageWhenFromDateIsAfterToDate() {
		applyLeavePage.setFromDate("Tue, 01 Mar 2022")
					  .setToDate("Tue, 08 Feb 2022");
		
		WebElement ErrorMessageElement=applyLeavePage.getDateErrorMessageElement();
		
		String colorName=ErrorMessageElement.getCssValue("color");
		
		assert(colorName.equals("red"));
		
	}
	
	@Test(priority=4,expectedExceptions=ElementNotVisibleException.class)
	public void testErrorMessageDisappearanceAfterToDateModified() {
		applyLeavePage.setToDate("Fri, 04 Mar 2022");
		applyLeavePage.getDateErrorMessageElement();
	}
	
	@Test(priority=5)
	public void testLeaveBalnceAvailable() {
		double days=applyLeavePage.clickOnCheckBalanceLeavesHyperText()
			          .checkAvailableBalanceDaysInPopUp();

		applyLeavePage.closeBalanceLeavesPopUp();
		
		assert((int)days==0);
	}
	
	@Test(priority=6)
	//public void 
	@AfterClass
	public void closeBrowser() {
		driver.quit();
	}
	
}
