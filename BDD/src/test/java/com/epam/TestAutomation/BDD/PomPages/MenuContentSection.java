package com.epam.TestAutomation.BDD.PomPages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

public class MenuContentSection {
	private final WebDriver driver;
	private JavascriptExecutor js;
	
	@FindBy(id = "menu-content")
	private WebElement menuContentDivElement;
	
	@FindBy(id = "logoutLink")
	private WebElement LogOutElement;
	
	@FindBy(xpath="//i[text()=\"keyboard_arrow_down\"]")
	private WebElement MenuDropdown;
	
	public MenuContentSection(WebDriver driver) {
		this.driver=driver;
		js=(JavascriptExecutor)driver;
		PageFactory.initElements(new AjaxElementLocatorFactory(driver,10), this);
	}
	
	public MenuContentSection clickOnElementInMenuContent(String elementName) {
		
		WebElement Menu=menuContentDivElement.findElement(By.xpath("//div/ul/li/a/span[text()=\""+elementName+"\"]"));
		if(!Menu.getAttribute("class").contains("active")) {
			Menu.click();
		}
		return this;
	}
	
	public MenuContentSection clickOnElementInSubMenuContent(String subElementName) {
		menuContentDivElement.findElement(By.xpath("//div/ul/li/div/ul/li/a/span[text()=\""+subElementName+"\"]"))
		.click();
		return this;
	}
	
	public MenuContentSection clickOnMenuDropdown() {
		MenuDropdown.click();
		return this;
	}
	public void logout() {
		LogOutElement.click();
	}
	
}
