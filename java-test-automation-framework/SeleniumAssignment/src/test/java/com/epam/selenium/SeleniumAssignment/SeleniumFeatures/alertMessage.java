package com.epam.selenium.SeleniumAssignment.SeleniumFeatures;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.epam.selenium.SeleniumAssignment.PageFactory.LogInPage;
import com.epam.selenium.SeleniumAssignment.WebDriverFactory.webDriverFactory;

public class alertMessage {
	WebDriver driver;
	
	@FindBy(xpath ="//input[@type=\"text\"]") 
	private WebElement InputElement;
	
	@FindBy(xpath ="//input[@type=\"submit\"]") 
	private WebElement submitButton;
	
	
	@Parameters({"driverName"})
	@Test
	public void setUp(String driverName) {
		
		driver =webDriverFactory.getDriver(driverName);
		driver.manage().window().maximize();
		PageFactory.initElements(driver, this);
		driver.get("https://demo.guru99.com/test/delete_customer.php");
	}
	
	@Test(priority=2)
	public void testGiveIdAndSubmit() {
		InputElement.sendKeys("2345");
		submitButton.click();
	}
	
	
	@Test(priority=3)
	public void testAlertMessage() {
		String alertMessage=driver.switchTo().alert().getText();
		System.out.println(alertMessage);
		driver.switchTo().alert().accept();
	}
	
}
