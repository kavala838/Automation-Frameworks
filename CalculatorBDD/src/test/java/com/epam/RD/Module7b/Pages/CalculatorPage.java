package com.epam.RD.Module7b.Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CalculatorPage {
	WebDriver driver;
	@FindBy(id = "displayField")
	private WebElement displayField;
	public CalculatorPage(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	public boolean hasButtonWithText(String string) {
		// TODO Auto-generated method stub
		System.out.println(string);
		return driver.findElement(By.xpath("//button[text()=\""+string+"\"]")).isDisplayed();
	
	}
	public boolean hasElementWithId(String string) {
		// TODO Auto-generated method stub
		return driver.findElement(By.id(string)).isDisplayed();
	}
	public WebElement getWebelementById(String string) {
		// TODO Auto-generated method stub
		return driver.findElement(By.id(string));
	}
	public WebElement getWebelementByText(String string) {
		// TODO Auto-generated method stub
		return driver.findElement(By.xpath("//button[text()=\""+string+"\"]"));
	}
	public boolean hasDisplayField() {
		// TODO Auto-generated method stub
		return displayField.isDisplayed();
	}
	public WebElement getDisplayField() {
		// TODO Auto-generated method stub
		displayField.isDisplayed();
		return displayField;
	}
}
