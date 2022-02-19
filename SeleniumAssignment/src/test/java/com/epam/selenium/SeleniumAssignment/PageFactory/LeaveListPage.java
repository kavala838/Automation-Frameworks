package com.epam.selenium.SeleniumAssignment.PageFactory;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

public class LeaveListPage extends MenuContentSection {
	WebDriver driver;
	private JavascriptExecutor js;
	
	@FindBy(id="searchLeaveList")
	WebElement FormElement;
	
	public LeaveListPage(WebDriver driver) {
		super(driver);
		this.driver=driver;
		PageFactory.initElements(new AjaxElementLocatorFactory(driver,5), this);
		js=(JavascriptExecutor)driver;
	}
	
	public LeaveListPage selectCheckBoxWithGivenLabelName(String label) {
		
		String script="document.getElementById('statusAll').checked=true;";
		js.executeScript(script);
		return this;
	}
	
	public LeaveListPage clickOnSearchButtonOfLeaveListForm() {
		
		String script="document.getElementById('searchLeaveList').submit();";
		js.executeScript(script);
		return this;
	}
}
