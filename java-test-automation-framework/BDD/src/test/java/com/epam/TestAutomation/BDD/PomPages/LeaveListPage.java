package com.epam.TestAutomation.BDD.PomPages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LeaveListPage extends MenuContentSection {
	WebDriver driver;
	private JavascriptExecutor js;
	
	@FindBy(id="searchLeaveList")
	WebElement FormElement;
	
	@FindBy(id="toast-container")
	WebElement ErrDisplayElement;
	
	public LeaveListPage(WebDriver driver) {
		super(driver);
		this.driver=driver;
		PageFactory.initElements(new AjaxElementLocatorFactory(driver,5), this);
		js=(JavascriptExecutor)driver;
	}
	
	public LeaveListPage selectCheckBoxWithGivenLabelName(String label) {
		new WebDriverWait(driver,Duration.ofSeconds(5))
		 .until(ExpectedConditions.presenceOfElementLocated(By.id(label)));
		String script="document.getElementById('"+label+"').checked=true;";
		js.executeScript(script);
		return this;
	}
	
	public LeaveListPage clickOnSearchButtonOfLeaveListForm() {
		//FormElement.submit();
		String script="document.getElementById('searchLeaveList').submit();";
		js.executeScript(script);
		return this;
	}

	public boolean isNoRecordsFoundElementIsInDisplay() {
		return ErrDisplayElement.isDisplayed();
	}
}
