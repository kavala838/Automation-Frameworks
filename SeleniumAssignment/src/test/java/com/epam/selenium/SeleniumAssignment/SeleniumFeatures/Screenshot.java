package com.epam.selenium.SeleniumAssignment.SeleniumFeatures;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.epam.selenium.SeleniumAssignment.PageFactory.ApplyLeavePage;
import com.epam.selenium.SeleniumAssignment.PageFactory.DashBoardPage;
import com.epam.selenium.SeleniumAssignment.PageFactory.LogInPage;
import com.epam.selenium.SeleniumAssignment.WebDriverFactory.webDriverFactory;

public class Screenshot {
	WebDriver driver;
	LogInPage logInPage;
	@Parameters({"driverName"})
	@BeforeClass
	public void setUp(String driverName) {
		
		driver =webDriverFactory.getDriver(driverName);
		driver.manage().window().maximize();
		
		driver.get("https://prasoonr-trials73.orangehrmlive.com/");
		
		logInPage=new LogInPage(driver);
		
		logInPage.login("Admin", "U@qBLVtm09");
	}
	@Test
	public void testScreenshot() throws IOException {
		TakesScreenshot screenShot = (TakesScreenshot)driver;
		File screenShotFile = screenShot.getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(screenShotFile, new File("./loginPage.jpg"));
	}
}
