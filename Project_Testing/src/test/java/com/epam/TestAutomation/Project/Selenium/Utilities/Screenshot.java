package com.epam.TestAutomation.Project.Selenium.Utilities;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class Screenshot {
	public static String getScreenShot(WebDriver driver,String name) {
		TakesScreenshot screenShot = (TakesScreenshot)driver;
		File screenShotFile = screenShot.getScreenshotAs(OutputType.FILE);
		String dateName = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
		String destination = System.getProperty("user.dir")+"//src//main//resources//screenshots//"+name+dateName+".png";
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
