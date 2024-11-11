package com.epam.TestAutomation.Project.Selenium.PomPages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

public class DashBoardPage extends OrangeHRMPage{
	private final WebDriver driver;
	 
	
	public DashBoardPage(WebDriver driver) {
		super(driver);
		this.driver=driver;
		PageFactory.initElements(new AjaxElementLocatorFactory(driver,20), this);
		
	}
	
	
	public DashBoardPage clickOnElementInQuickAccessSection(String QuickAccesselementName) {
		
		driver.findElement(By.xpath("//div[text()=\""+QuickAccesselementName+"\"]/parent::div/preceding-sibling::div/span/span/img"))
		.click();
		
		return this;
	}
	
	
	
}
