package com.epam.selenium.SeleniumAssignment.PageFactory;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class MenuContentSection {
	private final WebDriver driver;
	
	@FindBy(id = "menu-content")
	private WebElement menuContentDivElement;
	
	public MenuContentSection(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	public MenuContentSection clickOnElementInMenuContent(String elementName) {
		menuContentDivElement.findElement(By.xpath("//div/ul/li/a/span[text()=\""+elementName+"\"]"))
		.click();
		return this;
	}
	
	public MenuContentSection clickOnElementInSubMenuContent(String subElementName) {
		menuContentDivElement.findElement(By.xpath("//div/ul/li/div/ul/li/a/span[text()=\""+subElementName+"\"]"))
		.click();
		return this;
	}
	
}
