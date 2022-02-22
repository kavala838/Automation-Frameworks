package com.epam.selenium.SeleniumAssignment.Listeners;

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
		String destination=this.getScreenShot(driver);
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
		String destination=this.getScreenShot(driver);
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
	public String getScreenShot(WebDriver driver) {
		TakesScreenshot screenShot = (TakesScreenshot)driver;
		File screenShotFile = screenShot.getScreenshotAs(OutputType.FILE);
		String dateName = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
		String destination = System.getProperty("user.dir")+"//resources//"+"namePicture"+dateName+".png";
		java.io.File finalDestination = new File(destination);
		try {
			FileUtils.copyFile(screenShotFile, finalDestination);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return destination;
	}

}
