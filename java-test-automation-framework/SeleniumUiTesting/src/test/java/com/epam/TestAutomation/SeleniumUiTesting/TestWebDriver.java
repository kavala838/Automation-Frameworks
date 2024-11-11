package com.epam.TestAutomation.SeleniumUiTesting;

import javax.lang.model.element.Element;

import org.checkerframework.checker.units.qual.Time;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class TestWebDriver {
	WebDriver driver;
	@BeforeClass
	public void setUp() {
		String driverPath="C:\\BrowserDrivers\\geckodriver\\geckodriver.exe";
		System.setProperty("webdriver.gecko.driver", driverPath);
		driver=new FirefoxDriver();
		driver.manage().window().maximize();
	}
	@Test(priority=1)
	public void testDriver() {
		/*
		 * String driverPath="C:\\BrowserDrivers\\geckodriver\\geckodriver.exe";
		 * System.setProperty("webdriver.gecko.driver", driverPath); driver=new
		 * FirefoxDriver();
		 */
		
		/*
		 * String
		 * chromeDriverPath="C:\\BrowserDrivers\\chromedriver_win32\\chromedriver.exe";
		 * System.setProperty("webdriver.chrome.driver", chromeDriverPath); WebDriver
		 * chromeDriver=new ChromeDriver();
		 */
		
		driver.get("https://www.orangehrm.com/");
		WebElement input=driver.findElement(By.id("myText"));
		input.sendKeys("123@yahoo.com");
		WebElement clickButton=driver.findElement(By.id("linkadd"));
		clickButton.click();
	}
	@Test(priority=2)
	public void testContactSalesForm() {
		driver.findElement(By.xpath("/html/body/nav/div/div[2]/ul[3]/li[2]/a"))
			  .click();
		
		driver.findElement(By.id("Form_submitForm_FullName"))
		      .sendKeys("Ram");
		
		driver.findElement(By.id("Form_submitForm_CompanyName"))
	      .sendKeys("epam");
		
		driver.findElement(By.id("Form_submitForm_JobTitle"))
	      .sendKeys("Test Automation Engineer");
		
		WebElement selectElement = driver.findElement(By.id("Form_submitForm_NoOfEmployees"));
		Select selectObject = new Select(selectElement);
	    selectObject.selectByValue("16 - 20");
		  
	    driver.findElement(By.xpath("/html/body/div[4]/div/div/div/div[2]/form/fieldset/div[6]/div/div/div/div"))
		      .click();
	    driver.findElement(By.id("iti-0__item-in"))
	      .click();
	    driver.findElement(By.xpath("/html/body/div[1]/div[2]/div[4]/div[2]/div/a"))
	      .click();
	    
	    driver.findElement(By.id("Form_submitForm_Contact"))
	      .sendKeys("1234567890");
	    
	    
	    driver.findElement(By.id("Form_submitForm_Email"))
	      .sendKeys("abc@gmail.com");
	    
	    
	    driver.findElement(By.id("Form_submitForm_Comment"))
	      .sendKeys("Message here");
	    
		/*
		 * driver.findElement(By.xpath(
		 * "/html/body/div[2]/div[3]/div[1]/div/div/span/div[1]")) .click();
		 */
	    
	    
	  
	    driver.findElement(By.xpath("//*[@id=\"Form_submitForm_action_request\"]"))
	      .click();
	    
	    
	    
		
	}
}
