package com.epam.selenium.SeleniumAssignment.SeleniumFeatures;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.epam.selenium.SeleniumAssignment.WebDriverFactory.webDriverFactory;

public class DragAnddrop {
	
	WebDriver driver;
	
	@FindBy(id ="credit2") 
	private WebElement BankElement;
	
	@FindBy(xpath ="//ol[@id=\"bank\"]/li") 
	private WebElement dropElement;
	
	@Parameters({"driverName"})
	@Test
	public void setUp(String driverName) {
		
		driver =webDriverFactory.getDriver(driverName);
		driver.manage().window().maximize();
		PageFactory.initElements(driver, this);
		driver.get("https://demo.guru99.com/test/drag_drop.html");
	}
	@Test
	public void testDragAndDrop() {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,250)");
		
		Actions action = new Actions(driver);
		action.dragAndDrop(BankElement, dropElement).build().perform();
	}
}
