package com.epam.TestAutomation.Project.Selenium.PomPages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

import com.paulhammant.ngwebdriver.NgWebDriver;

public class PunchInOutPage extends MenuContentSection {
	private JavascriptExecutor js;
	private NgWebDriver ngDriver;
	
	@FindBy(xpath = "//span[text()='Punch out time should be later than punch in time']") 
	private WebElement ErrMsg1;
	
	@FindBy(xpath = "//span[text()='Should be a valid time in HH:MM format']") 
	private WebElement ErrMsg2;
	
	@FindBy(xpath = "//span[text()='Overlapping records found']") 
	private WebElement ErrMsg3;
	
	@FindBy(xpath = "//*[@id=\"attendanceMyPunchInOutDiv\"]/h4") 
	private WebElement cardName;
	
	@FindBy(xpath = "//li[text()='Punch In/Out']")
	private WebElement PageTitle;
	
	@FindBy(xpath = "//button[text()='In']") 
	private WebElement InButton;
	
	@FindBy(xpath = "//button[text()='Out']") 
	private WebElement OutButton;
	
	@FindBy(id = "time") 
	private WebElement TimeInput;
	
	@FindBy(id = "date") 
	private WebElement DateInput;
	
	public PunchInOutPage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
		PageFactory.initElements(new AjaxElementLocatorFactory(driver,20), this);
		js=(JavascriptExecutor)driver;
		ngDriver=new NgWebDriver(js);
	}
	public boolean isInThisPage() {
		// TODO Auto-generated method
		
		if(PageTitle.isDisplayed() && driver.getCurrentUrl().contains("my_punch_in_out"))
			return true;
		return false;
	}
	public boolean isAskingPunchInData() {
		// TODO Auto-generated method stub
		ngDriver.waitForAngularRequestsToFinish();
		String cardTitle=cardName.getText();
		System.out.println(cardTitle);
		if(cardTitle.equals("Punch In"))
			return true;
		return false;
	}
	public PunchInOutPage punchIn(String time) {
		// TODO Auto-generated method stub
		TimeInput.clear();
		TimeInput.sendKeys(time);
		ngDriver.waitForAngularRequestsToFinish();
		InButton.click();
		return this;
	}
	public boolean isAskingPunchOutData() {
		// TODO Auto-generated method stub
		ngDriver.waitForAngularRequestsToFinish();
		String cardTitle=cardName.getText();
		System.out.println(cardTitle);
		if(cardTitle.equals("Punch Out"))
			return true;
		return false;
		
	}
	public String getPunchInData() {
		// TODO Auto-generated method stub
		ngDriver.waitForAngularRequestsToFinish();
		
		WebElement PunchInData=driver.findElement(By.xpath("//span[text()=\"Punched in Time\"]/following::span"));
		return PunchInData.getText();
	}
	public PunchInOutPage punchOut(String time) {
		// TODO Auto-generated method stub
		TimeInput.clear();
		TimeInput.sendKeys(time);
		ngDriver.waitForAngularRequestsToFinish();
		OutButton.click();
		return this;
	}
	public boolean isOverlappingErrMsgDisplayed() {
		// TODO Auto-generated method stub
		return ErrMsg3.isDisplayed();
	}
	public boolean isTimeLaterThanInnTimeIsDisplayed() {
		// TODO Auto-generated method stub
		return ErrMsg1.isDisplayed();
	}
	public boolean isInvalidFormatErrMsgDisplayed() {
		// TODO Auto-generated method stub
		return ErrMsg2.isDisplayed();
	}
	public void punchIn(String string, String string2) {
		// TODO Auto-generated method stub
		DateInput.clear();
		DateInput.sendKeys(string);
	
		TimeInput.clear();
		TimeInput.sendKeys(string2);
		ngDriver.waitForAngularRequestsToFinish();
		InButton.click();
	}
	public PunchInOutPage setDate(String string) {
		// TODO Auto-generated method stub
		DateInput.clear();
		DateInput.sendKeys(string);
		String script="document.getElementById('date').blur();";
		js.executeScript(script);
		ngDriver.waitForAngularRequestsToFinish();
		return this;
	}

}
