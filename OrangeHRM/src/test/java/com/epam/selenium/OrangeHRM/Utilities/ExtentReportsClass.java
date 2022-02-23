package com.epam.selenium.OrangeHRM.Utilities;

import org.testng.ITestContext;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;

public class ExtentReportsClass {
	private static String filePath = ".//src/main/resources//ExtentReportResults.html";
	private static ExtentReports report= new ExtentReports(filePath);
	private static ExtentTest test=report.startTest("LoginTest");
	public static void initExtentReports(ITestContext context) {
		context.setAttribute("ExtentTest", test);
	}
}
