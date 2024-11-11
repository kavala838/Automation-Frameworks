package com.epam.TestAutomation.Project.Selenium.Listeners;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.epam.TestAutomation.Project.Selenium.Utilities.Screenshot;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class TestListenerImpl implements ITestListener {
	private WebDriver driver;
	private ExtentTest test;
	@Override
	public void onTestStart(ITestResult result) {
		// TODO Auto-generated method stub
		ITestListener.super.onTestStart(result);
		test=(ExtentTest)result.getTestContext().getAttribute("ExtentTest");
		
		driver=(WebDriver) result.getTestContext().getAttribute("WebDriver");
		String destination=Screenshot.getScreenShot(driver,result.getMethod().getMethodName()+"_Start_");
		test.log(LogStatus.INFO, result.getMethod().getMethodName()+" is started",test.addScreenCapture(destination));
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		// TODO Auto-generated method stub
		ITestListener.super.onTestSuccess(result);
		test=(ExtentTest)result.getTestContext().getAttribute("ExtentTest");
		test.log(LogStatus.PASS, result.getMethod().getMethodName()+" is passed");
	}

	@Override
	public void onTestFailure(ITestResult result) {
		// TODO Auto-generated method stub
		
		ITestListener.super.onTestFailure(result);
		test=(ExtentTest)result.getTestContext().getAttribute("ExtentTest");
		driver=(WebDriver) result.getTestContext().getAttribute("WebDriver");
		String destination=Screenshot.getScreenShot(driver,result.getMethod().getMethodName()+"_Failed_");
		test.log(LogStatus.FAIL, result.getMethod().getMethodName()+" is failed",test.addScreenCapture(destination));
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		// TODO Auto-generated method stub
		ITestListener.super.onTestSkipped(result);
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub
		ITestListener.super.onTestFailedButWithinSuccessPercentage(result);
	}

	@Override
	public void onTestFailedWithTimeout(ITestResult result) {
		// TODO Auto-generated method stub
		ITestListener.super.onTestFailedWithTimeout(result);
	}

	@Override
	public void onStart(ITestContext context) {
		// TODO Auto-generated method stub
		ITestListener.super.onStart(context);
	}

	@Override
	public void onFinish(ITestContext context) {
		// TODO Auto-generated method stub
		ITestListener.super.onFinish(context);
	}
	

}
