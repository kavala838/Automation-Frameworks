package com.epam.TestAutomation.Project.Selenium.Tests;

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

import java.net.MalformedURLException;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.epam.TestAutomation.Project.Selenium.PomPages.DashBoardPage;
import com.epam.TestAutomation.Project.Selenium.PomPages.LogInPage;
import com.epam.TestAutomation.Project.Selenium.PomPages.MyAttendanceSheetPage;
import com.epam.TestAutomation.Project.Selenium.PomPages.PunchInOutPage;
import com.epam.TestAutomation.Project.Selenium.Utilities.setUpClass;
import com.epam.TestAutomation.Project.Selenium.WebFactory.DriverFactoryProvider;



public class TestAttendanceFeatures {
	
	WebDriver driver;
	DashBoardPage dashBoardPage;
	MyAttendanceSheetPage myAttendanceSheetPage;
	PunchInOutPage punchInOutPage;
	
	@BeforeClass
	public void setUp(ITestContext context) throws MalformedURLException {
		
		driver=setUpClass.setTillDashBoardPage(context);
		
		dashBoardPage=new DashBoardPage(driver);
		myAttendanceSheetPage=new MyAttendanceSheetPage(driver);
		punchInOutPage=new PunchInOutPage(driver);
		
	}
	
	@Test(priority=1)
	public void TestNavigationToAttendanceSheetPage() {
		System.out.println("start 1");
		dashBoardPage.clickOnElementInMenuContent("Attendance")
					 .clickOnElementInSubMenuContent("My Attendance Sheet");
		
		assertTrue(myAttendanceSheetPage.isInThisPage());
	}
	
	@Test(priority=2)
	public void TestNavigationToPunchInOutPage() {
		System.out.println("start 2");
		dashBoardPage.clickOnElementInMenuContent("Attendance")
		 .clickOnElementInSubMenuContent("Punch In/Out");
		
		assertTrue(punchInOutPage.isInThisPage());
	}
	
	//Pre-Condition--->No Active Punch In.
	@Test(priority=3)
	public void TestPunchInIconVisibility() {
		System.out.println("start 3");
		dashBoardPage.clickOnElementInMenuContent("Attendance")
		             .clickOnElementInSubMenuContent("My Attendance Sheet");
		
		assertTrue(myAttendanceSheetPage.isPunchIconInPunchInState());
		
		
	}
	
	@Test(priority=4)
	public void TestNavigationToPunchInOutPageWithPunchInCardFromAttendanceSheetPageOnClickingPunchInIcon()
			throws InterruptedException {
		System.out.println("start 4");
		myAttendanceSheetPage.clickOnPunchIcon();
		
		assertTrue(punchInOutPage.isInThisPage());
		
		assertTrue(punchInOutPage.isAskingPunchInData());
		
	}
	
	//public void TestPunchInCardVisibilityInPunchInOutPage() {}
	@Test(priority=5)
	public void TestPunchInDataAppearsInTimeSheetDetailsAfterPunchIn() {
		System.out.println("start 5");
		punchInOutPage.punchIn("01:40");
		boolean isDisplayed=myAttendanceSheetPage
				             .clickOnDate("10 Mar")
				             .isThisPunchInDataDisplayed("Thu, 10 Mar 2022 01:40");
		assertTrue(isDisplayed);
	}
	@Test(priority=6)
	public void TestPunchOutIconVisibilityAfterValidPunchIn() {
		assertFalse(myAttendanceSheetPage.isPunchIconInPunchInState());
	}
	@Test(priority=7)
	public void TestNavigationToPunchInOutPageWithPunchOutCardFromAttendanceSheetPageOnClickingPunchOutIcon() throws InterruptedException {
		myAttendanceSheetPage.clickOnPunchIcon();
		
		assertTrue(punchInOutPage.isInThisPage());
		
		assertTrue(punchInOutPage.isAskingPunchOutData());
	}
	
	@Test(priority=8)
	public void TestPunchInDataPresentInPunchOutCard() {
		String data=punchInOutPage.getPunchInData();
		assertTrue(data.equals("Thu, 10 Mar 2022 01:40"));
	}
	@Test(priority=9)
	public void TestPunchOutDataAppearsInTimeSheetDetailsAfterPunchOut() {
		punchInOutPage.punchOut("01:50");
		boolean isDisplayed=myAttendanceSheetPage
				             .clickOnDate("10 Mar")
				             .isThisPunchOutDataDisplayed("Thu, 10 Mar 2022 01:50");
		assertTrue(isDisplayed);
	}
	@Test(priority=10)
	public void TestPunchInOverlappingErrorMessageDisplay() throws InterruptedException {
		myAttendanceSheetPage.clickOnPunchIcon();
		boolean isDisplayed=punchInOutPage.punchIn("01:45").isOverlappingErrMsgDisplayed();
		assertTrue(isDisplayed);
	}
	@Test(priority=11)
	public void TestPunchOutTimeLaterThanIntimeErrorMessageDisplay() throws InterruptedException {
		punchInOutPage.punchIn("02:00");
		myAttendanceSheetPage.clickOnPunchIcon();
		boolean isDisplayed=punchInOutPage.punchOut("01:55").isTimeLaterThanInnTimeIsDisplayed();
		assertTrue(isDisplayed);
	}
	@Test(priority=12)
	public void TestValidTimeFormatErrorMessage() {
		boolean isDisplayed=punchInOutPage.punchOut("1q4r").isInvalidFormatErrMsgDisplayed();
		assertTrue(isDisplayed);
	}
	@Test(priority=13)
	public void TestPunchIconToggleAfterDeletingPunchInData() {
		punchInOutPage.clickOnElementInMenuContent("Attendance")
                     .clickOnElementInSubMenuContent("My Attendance Sheet");
		boolean isInPunchIn=myAttendanceSheetPage
							.deleteTheSessionRecord("10 Mar","Thu, 10 Mar 2022 02:00")
							.isPunchIconInPunchInState();
		assertTrue(isInPunchIn);
	}
	@Test(priority=14)
	public void TestPunchCardToggleToPunchOutWhenGivenDateHasPunchedInSession() throws InterruptedException {
		myAttendanceSheetPage.clickOnPunchIcon();
		punchInOutPage.punchIn("Fri, 11 Mar 2022","10:00");
		myAttendanceSheetPage.clickOnPunchIcon();
		punchInOutPage.setDate("Fri, 11 Mar 2022").isAskingPunchOutData();
		
	}
	public void TestTotalHoursAggregation() {
		
	}
}
