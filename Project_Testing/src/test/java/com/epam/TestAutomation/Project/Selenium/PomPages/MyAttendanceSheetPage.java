package com.epam.TestAutomation.Project.Selenium.PomPages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.paulhammant.ngwebdriver.NgWebDriver;

public class MyAttendanceSheetPage extends MenuContentSection {
	
	private JavascriptExecutor js;
	private NgWebDriver ngDriver;
	PunchInOutPage punchInOutPage;
	
	@FindBy(id = "save-button") 
	private WebElement DeleteEle;
	
	@FindBy(className = "punch-icons-wrapper")
	private WebElement PunchIcon;
	
	@FindBy(xpath = "//li[text()='Attendance Sheet']")
	private WebElement PageTitle;
	
	@FindBy(xpath = "//div[text()='Total Time']/following::div")
	private WebElement TotalTime;
	
	
	public MyAttendanceSheetPage(WebDriver driver,PunchInOutPage punchInOutPage) {
		super(driver);
		PageFactory.initElements(new AjaxElementLocatorFactory(driver,20), this);
		js=(JavascriptExecutor)driver;
		ngDriver=new NgWebDriver(js);
		this.punchInOutPage=punchInOutPage;
		// TODO Auto-generated constructor stub
	}
	
	
	public boolean isPunchIconInPunchInState() {
		// TODO Auto-generated method stub
		String state=PunchIcon.getAttribute("data-original-title");
		if(state.equals("Punch In"))
			return true;
		return false;
	}
	
	public PunchInOutPage clickOnPunchIcon() throws InterruptedException {
		
		PunchIcon.click();
		return punchInOutPage;
	}
	public boolean isInThisPage() {
		// TODO Auto-generated method stub
		
		if(PageTitle.isDisplayed() && driver.getCurrentUrl().contains("attendance_sheet"))
			return true;
		return false;
	}
	
	public MyAttendanceSheetPage clickOnDate(String string) {
		// TODO Auto-generated method stub
		ngDriver.waitForAngularRequestsToFinish();
		String script="document.evaluate('//div[text()=\""+string+"\"]/parent::span/parent::div', document, null, XPathResult.FIRST_ORDERED_NODE_TYPE, null).singleNodeValue.click();";
		js.executeScript(script);
		
		ngDriver.waitForAngularRequestsToFinish();
		return this;
	}
	
	public boolean isThisPunchInDataDisplayed(String string) {
		// TODO Auto-generated method stub
		ngDriver.waitForAngularRequestsToFinish();
		WebElement PunchInData=driver.findElement(By.xpath("//div[@Class='col-5 punchInColumn']/span[text()='"+string+"']"));
		return PunchInData.isDisplayed();
	}
	
	public boolean isThisPunchOutDataDisplayed(String string) {
		// TODO Auto-generated method stub
		ngDriver.waitForAngularRequestsToFinish();
		WebElement PunchOutData=driver.findElement(By.xpath("//div[@Class='col-5 punchOutColumn']/span[text()='"+string+"']"));
		return PunchOutData.isDisplayed();
	}
	
	public MyAttendanceSheetPage deleteTheSessionRecord(String string, String string2) {
		
		clickOnDate(string);
		
		js.executeScript("window.scrollBy(0,290)");
		ngDriver.waitForAngularRequestsToFinish();
		
		String script="document.evaluate('//span[text()=\""+string2+"\"]/parent::div/parent::div//span[text()=\"ohrm_delete\"]', document, null, XPathResult.FIRST_ORDERED_NODE_TYPE, null).singleNodeValue.click();";
		js.executeScript(script);
		
		ngDriver.waitForAngularRequestsToFinish();
		
		DeleteEle.click();
		
		js.executeScript("window.scrollBy(0,-290)");
		
		ngDriver.waitForAngularRequestsToFinish();
		
		return this;
	}
	
	public String getTotalHours() {
		
		ngDriver.waitForAngularRequestsToFinish();
		String Totaltime=TotalTime.getText();
		return Totaltime;
	}
	
	public  MyAttendanceSheetPage punchInOut(String punchIn,String punchOut) throws InterruptedException {
		this.clickOnPunchIcon();
		punchInOutPage.punchIn(punchIn);
		this.clickOnPunchIcon();
		punchInOutPage.punchOut(punchOut);
		return this;
	}


	public void deleteRecords(String string) {
		// TODO Auto-generated method stub
		this.clickOnDate(string);
		while(true)
		{
			try {
		String script="document.evaluate('//div[@Class=\"punchRecord\"]//span[text()=\"ohrm_delete\"]', document, null, XPathResult.FIRST_ORDERED_NODE_TYPE, null).singleNodeValue.click();";
		js.executeScript(script);
		
		ngDriver.waitForAngularRequestsToFinish();
		DeleteEle.click();
		ngDriver.waitForAngularRequestsToFinish();
		
			}
			catch(Exception e) {
				break;
			}
		}
	}

}
