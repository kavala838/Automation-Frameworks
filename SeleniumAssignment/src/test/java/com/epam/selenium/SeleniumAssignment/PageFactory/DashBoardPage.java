package com.epam.selenium.SeleniumAssignment.PageFactory;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class DashBoardPage extends MenuContentSection{
	private final WebDriver driver;
	 
	
	public DashBoardPage(WebDriver driver) {
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
		
	}
	
	
	public DashBoardPage clickOnElementInQuickAccessSection(String QuickAccesselementName) {
		
		driver.findElement(By.xpath("//div[text()=\""+QuickAccesselementName+"\"]/parent::div/preceding-sibling::div/span/span/img"))
		.click();
		
		return this;
	}
	
	
	
}
