package com.epam.selenium.SeleniumAssignment;
import com.epam.selenium.SeleniumAssignment.Listeners.*;
import java.io.IOException;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotVisibleException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestContext;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.epam.selenium.SeleniumAssignment.PageFactory.ApplyLeavePage;
import com.epam.selenium.SeleniumAssignment.PageFactory.DashBoardPage;
import com.epam.selenium.SeleniumAssignment.PageFactory.LeaveListPage;
import com.epam.selenium.SeleniumAssignment.PageFactory.LogInPage;
import com.epam.selenium.SeleniumAssignment.WebDriverFactory.webDriverFactory;
@Listeners(TestListenerImpl.class)
public class OrangeHrmApplyLeaveFunctionalityWithDifferentScenarios {
 ITestContext context;
 public WebDriver driver;
  LogInPage logInPage;
  DashBoardPage dashBoardPage;
  ApplyLeavePage applyLeavePage;
  LeaveListPage leaveListPage;
  public WebDriver getDriver() {
	  return this.driver;
  }
	@Parameters({"driverName"})
	@BeforeClass
	public void setUp(String driverName) {
		
		driver =webDriverFactory.getDriver(driverName);
		driver.manage().window().maximize();
		
		driver.get("https://prasoonr-trials73.orangehrmlive.com/");
		
		logInPage=new LogInPage(driver);
		
		logInPage.login("Admin1", "U@qBLVtm09");
		
		dashBoardPage= new DashBoardPage(driver);
		applyLeavePage=new ApplyLeavePage(driver);
		leaveListPage=new LeaveListPage(driver);
		context.setAttribute("WebDriver", driver);
	}
	
	
	@Test(priority=1)
	public void testNavigationToApplyLeavePageFromDashBoardPage() {
		
		dashBoardPage.clickOnElementInMenuContent("Leave")
			         .clickOnElementInSubMenuContent("Apply");
		
		assert(driver.getCurrentUrl().contains("leave/apply"));
	}
	
	@Test(priority=2)
	public void testSelectLeaveTypeInDropDownInApplyLeavePage() {
		
		String SelectedType=applyLeavePage.setLeaveTypeTo("Sick Leave - US")
				                          .getLeaveTypeSelectedInDropDown();
		
		
		assert(SelectedType.equals("Sick Leave - US"));
		
	}
	@Test(priority=3)
	public void testErrorMessageWhenFromDateIsAfterToDate() throws IOException {
		
		WebElement ErrorMessageElement=applyLeavePage
				      .setFromDate("Tue, 01 Mar 2022")
					  .setToDate("Mon, 07 Feb 2022")
					  .getDateErrorMessageElement();
		
		
		String colorName=ErrorMessageElement.getCssValue("color");
		
		//System.out.println(colorName);
		
		assert(colorName.equals("rgb(244, 67, 54)"));
		
	}
	
	@Test(enabled=true,priority=4,expectedExceptions=NoSuchElementException.class)
	public void testErrorMessageDisappearanceAfterToDateModified() throws IOException {
		
		WebElement Err=applyLeavePage.setToDate("Fri, 04 Mar 2022")
					  .getDateErrorMessageElement();
		System.out.println(Err.isDisplayed());
	}
	
	@Test(enabled=true,priority=5)
	public void testLeaveBalnceAvailable() {
		double days=applyLeavePage.clickOnCheckBalanceLeavesHyperText()
			          .checkAvailableBalanceDaysInPopUp();

		applyLeavePage.closeBalanceLeavesPopUp();
		
		assert((int)days==0);
	}
	@Test(enabled=true,priority=6)
	public void testNavigateToLeaveListPageFromApplyLeavePage() {
		applyLeavePage.clickOnElementInMenuContent("Leave")
					  .clickOnElementInSubMenuContent("Leave List");
		
		assert(driver.getCurrentUrl().contains("view_leave_list"));
	}
	@Test(enabled=true,priority=7)
	public void FillTheLeaveListForm() {
		leaveListPage.selectCheckBoxWithGivenLabelName("statusAll")
		             .clickOnSearchButtonOfLeaveListForm();
	}
	@Test(enabled=false,priority=8)
	public void testNoRecordsFoundMessageDisplay() {
		assert(leaveListPage.isNoRecordsFoundElementIsInDisplay());
	}
	
	@Test(enabled=false,priority=9)
	//public void 
	@AfterClass
	public void closeBrowser() {
		driver.quit();
	}
	
}
