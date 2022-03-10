package com.epam.TestAutomation.Project.Selenium.Tests;

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

import java.net.MalformedURLException;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.epam.TestAutomation.Project.API.Utilities.ParseTime;
import com.epam.TestAutomation.Project.Selenium.PomPages.DashBoardPage;
import com.epam.TestAutomation.Project.Selenium.PomPages.LogInPage;
import com.epam.TestAutomation.Project.Selenium.PomPages.MyAttendanceSheetPage;
import com.epam.TestAutomation.Project.Selenium.PomPages.PunchInOutPage;
import com.epam.TestAutomation.Project.Selenium.Utilities.ExtentReportsClass;
import com.epam.TestAutomation.Project.Selenium.Utilities.setUpClass;
import com.epam.TestAutomation.Project.Selenium.WebFactory.DriverFactoryProvider;



public class TestAttendanceFeatures {
	
	String date;
	WebDriver driver;
	DashBoardPage dashBoardPage;
	MyAttendanceSheetPage myAttendanceSheetPage;
	PunchInOutPage punchInOutPage;
	
	@BeforeClass
	public void setUp(ITestContext context) throws MalformedURLException {
		
		driver=setUpClass.setTillDashBoardPage(context);
		
		dashBoardPage=new DashBoardPage(driver);
		punchInOutPage=new PunchInOutPage(driver);
		myAttendanceSheetPage=new MyAttendanceSheetPage(driver,punchInOutPage);
		date=context.getCurrentXmlTest().getParameter("Date");
		
		ExtentReportsClass.initExtentReports(context);
		context.setAttribute("WebDriver", driver);
		
	}
	
	@Test(priority=1)
	public void testNavigationToAttendanceSheetPage() {
		
		dashBoardPage.clickOnElementInMenuContent("Attendance")
					 .clickOnElementInSubMenuContent("My Attendance Sheet");
		
		assertTrue(myAttendanceSheetPage.isInThisPage());
	}
	
	@Test(priority=2)
	public void testNavigationToPunchInOutPage() {
		
		myAttendanceSheetPage.clickOnElementInMenuContent("Attendance")
		             .clickOnElementInSubMenuContent("Punch In/Out");
		
		assertTrue(punchInOutPage.isInThisPage());
	}
	
	//Pre-Condition--->No Active Punch In.
	@Test(priority=3)
	public void testPunchInIconVisibility() {
		
		punchInOutPage.clickOnElementInMenuContent("Attendance")
		              .clickOnElementInSubMenuContent("My Attendance Sheet");
		
		assertTrue(myAttendanceSheetPage.isPunchIconInPunchInState());
		
		
	}
	
	@Test(priority=4)
	public void testNavigationToPunchInOutPageWithPunchInCardFromAttendanceSheetPageOnClickingPunchInIcon()
			throws InterruptedException {
		
		myAttendanceSheetPage.clickOnPunchIcon();
		
		assertTrue(punchInOutPage.isInThisPage());
		
		assertTrue(punchInOutPage.isAskingPunchInData());
		
	}
	
	
	
	@Test(priority=5)
	public void testPunchInDataAppearsInTimeSheetDetailsAfterPunchIn(ITestContext context) {
		
		String punchInTime=(String)context.getCurrentXmlTest().getParameter("PunchInTime");
		String punchInData=context.getCurrentXmlTest().getParameter("PunchInData");
		
		punchInOutPage.punchIn(punchInTime);
		
		
		boolean isDisplayed=myAttendanceSheetPage
				             .clickOnDate(date)
				             .isThisPunchInDataDisplayed(punchInData);
		assertTrue(isDisplayed);
		
	}
	
	
	
	@Test(priority=6)
	public void testPunchOutIconVisibilityAfterValidPunchIn() {
		
		assertFalse(myAttendanceSheetPage.isPunchIconInPunchInState());
		
	}
	
	
	
	@Test(priority=7)
	public void testNavigationToPunchInOutPageWithPunchOutCardFromAttendanceSheetPageOnClickingPunchOutIcon() throws InterruptedException {
		myAttendanceSheetPage.clickOnPunchIcon();
		
		assertTrue(punchInOutPage.isInThisPage());
		
		assertTrue(punchInOutPage.isAskingPunchOutData());
	}
	
	
	
	@Test(priority=8)
	public void testPunchInDataPresentInPunchOutCard(ITestContext context) {
		
		
		String punchInData=context.getCurrentXmlTest().getParameter("PunchInData");
		String data=punchInOutPage.getPunchInData();
		
		assertTrue(data.equals(punchInData));
	}
	
	
	@Test(priority=9)
	public void testPunchOutDataAppearsInTimeSheetDetailsAfterPunchOut(ITestContext context) {
		
		String punchOutTime=context.getCurrentXmlTest().getParameter("PunchOutTime");
		String punchOutData=context.getCurrentXmlTest().getParameter("PunchOutData");
		
		punchInOutPage.punchOut(punchOutTime);
		
		boolean isDisplayed=myAttendanceSheetPage
				             .clickOnDate(date)
				             .isThisPunchOutDataDisplayed(punchOutData);
		
		assertTrue(isDisplayed);
	}
	
	
	
	@Test(priority=10)
	public void testPunchInOverlappingErrorMessageDisplay(ITestContext context) throws InterruptedException {
		String overlappingPunchInTime=context.getCurrentXmlTest()
											 .getParameter("OverlappingPunchInTime");
		
		
		boolean isDisplayed=myAttendanceSheetPage.clickOnPunchIcon()
												 .punchIn(overlappingPunchInTime)
												 .isOverlappingErrMsgDisplayed();
		assertTrue(isDisplayed);
		
	}
	
	
	
	@Test(priority=11)
	public void testPunchOutTimeLaterThanIntimeErrorMessageDisplay(ITestContext context) throws InterruptedException {
		
		String punchInTimeForDelete=context.getCurrentXmlTest().getParameter("PunchInTimeForDelete");
		String invalidPunchOutTime=context.getCurrentXmlTest().getParameter("InvalidPunchOutTime");
		
		punchInOutPage.punchIn(punchInTimeForDelete);
		
		
		boolean isDisplayed=myAttendanceSheetPage.clickOnPunchIcon()
												 .punchOut(invalidPunchOutTime)
												 .isTimeLaterThanInnTimeIsDisplayed();
		
		assertTrue(isDisplayed);
	}
	
	
	
	@Test(priority=12)
	public void testInValidFormatErrorMessage(ITestContext context) {
		
		String InvalidTime=context.getCurrentXmlTest().getParameter("InvalidTime");
		
		boolean isDisplayed=punchInOutPage.punchOut(InvalidTime)
										  .isInvalidFormatErrMsgDisplayed();
		
		assertTrue(isDisplayed);
	}
	
	@Test(priority=13)
	public void testInValidTimeFormatErrorMessage(ITestContext context) {
		
		String InvalidTime=context.getCurrentXmlTest().getParameter("InvalidTimeFormat");
		
		boolean isDisplayed=punchInOutPage.punchOut(InvalidTime)
										  .isInvalidTimeFormatFormatErrMsgDisplayed();
		
		assertTrue(isDisplayed);
	}
	
	
	@Test(priority=14)
	public void testPunchIconToggleAfterDeletingPunchInData(ITestContext context) {
		
		String punchInDataForDelete=context.getCurrentXmlTest().getParameter("PunchInDataForDelete");
		
		punchInOutPage.clickOnElementInMenuContent("Attendance")
                     .clickOnElementInSubMenuContent("My Attendance Sheet");
		
		boolean isInPunchIn=myAttendanceSheetPage
							.deleteTheSessionRecord(date,punchInDataForDelete)
							.isPunchIconInPunchInState();
		
		assertTrue(isInPunchIn);
	}
	
	
	@Test(priority=15)
	public void testPunchCardToggleToPunchOutWhenGivendateHasPunchedInSession(ITestContext context) throws InterruptedException {
		
		String punchIndate=context.getCurrentXmlTest().getParameter("PunchInDate");
		String punchInTimeOtherDay=context
				.getCurrentXmlTest().getParameter("PunchInTimeOtherDay");
		
		
		
		myAttendanceSheetPage.clickOnPunchIcon()
							 .punchIn(punchIndate,punchInTimeOtherDay);
		
		
		
		assertTrue(myAttendanceSheetPage.clickOnPunchIcon()
										.setDate(punchIndate)
										.isAskingPunchOutData());
		
		
		punchInOutPage.punchOut("10:30");
		
	}
	
	@Test(priority=16)
	public void testTotalHoursAggregation() throws InterruptedException  {
		String InTime="12:00";
		String OutTime="14:00";
		
		
		
		String preSessionTotalTime=myAttendanceSheetPage.getTotalHours();
		
		String postSessionTotalTime=myAttendanceSheetPage.punchInOut(InTime,OutTime).getTotalHours();
		
		int sessionDuration=ParseTime.getDuration(InTime,OutTime);
		int totalTimePreSession=ParseTime.convertIntoMinutes(preSessionTotalTime);
		int totalTimePostSession=ParseTime.convertIntoMinutes(postSessionTotalTime);
		assertTrue(totalTimePreSession+sessionDuration==totalTimePostSession);
	}
	
	@Test(priority=17)
	public void testPunchOutAfterPunchInBeforeValidSession() throws InterruptedException {
		myAttendanceSheetPage.clickOnPunchIcon();
		punchInOutPage.punchIn("09:00");
		
		
		boolean isDisplayed=myAttendanceSheetPage.isPunchIconInPunchInState();
		assertFalse(isDisplayed);
	}
	
	@AfterClass
	public void close() {
		myAttendanceSheetPage.deleteRecords(date);
		myAttendanceSheetPage.deleteRecords("11 Mar");
		ExtentReportsClass.endReport();
		driver.quit();
	}
}
