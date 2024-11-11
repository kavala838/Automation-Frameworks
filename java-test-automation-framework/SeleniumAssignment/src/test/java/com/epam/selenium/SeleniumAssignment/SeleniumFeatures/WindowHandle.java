package com.epam.selenium.SeleniumAssignment.SeleniumFeatures;

import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.epam.selenium.SeleniumAssignment.WebDriverFactory.webDriverFactory;

public class WindowHandle {
	@FindBy(xpath ="//a[text()=\"Click Here\"]") 
	private WebElement clickHereElement;
	
	//@FindBy(xpath =) 
	//private WebElement InputElement;
	
	String page1Id;
	String page2Id;
	WebDriver driver;
	
	@Parameters({"driverName"})
	@Test(priority=1)
	public void setUp(String driverName) {
		
		driver =webDriverFactory.getDriver(driverName);
		driver.manage().window().maximize();
		PageFactory.initElements(driver, this);
		driver.get("https://demo.guru99.com/popup.php");
		page1Id=driver.getWindowHandle();
		System.out.println(page1Id);
	}
	
	@Test(priority=2)
	public void testClickHere() throws InterruptedException {
		Thread.sleep(1000);
		clickHereElement.click();
		
		  Thread.sleep(5000); 
		  Set<String> windowHandles = driver.getWindowHandles();
			for (String handle : windowHandles) {
				if (!handle.equals(page1Id)) {
					driver.switchTo().window(handle);
				}
			}

		  driver.findElement(By.xpath("//input[@type=\"text\"]")).sendKeys("abc@gmail.com");
		  Thread.sleep(5000);
		 
	}
	
	
	@Test(priority = 3)
	public void testNaviagteToParentWindow() {
		Set<String> windowHandles = driver.getWindowHandles();
		for (String handle : windowHandles) {
			if (handle.equals(page1Id)) {
				driver.switchTo().window(handle);
			}
		}
	}
	 
	
}
