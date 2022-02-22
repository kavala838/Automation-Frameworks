package com.epam.selenium.SeleniumAssignment.ExtentReport;

import com.relevantcodes.extentreports.ExtentReports;

public class ExtentReportsClass {
	static String filePath = ".//src/main/resources//ExtentReportResults.html";
	private static ExtentReports report= new ExtentReports(filePath);;
	public static ExtentReports getExtentReportsObject() {
		return report;
	}
	 
}
