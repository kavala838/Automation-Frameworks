package com.epam.selenium.SeleniumAssignment.PageFactory;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

public class ApplyLeavePage extends MenuContentSection {
	private final WebDriver driver;

	@FindBy(id = "applyLeaveForm")
	private WebElement FormElement;
	
	@FindBy(id="mleaveType_inputfileddiv")
	private WebElement LeaveTypeDropDownElement;
	
	@FindBy(id = "from")
	private WebElement FormDateInputElement;
	
	@FindBy(id = "to")
	private WebElement ToDateInputElement;
	
	@FindBy(id = "comment")
	private WebElement CommentTextAreaElement;
	
	@FindBy(xpath="//a[text()=\"Check Leave Balance\"]")
	private WebElement chechBalanceLeavesHyperTextElement;
	
	@FindBy(xpath="//*[@id=\"application_balance_modal\"]/div[2]/div/crud-panel/div/div/list/table/tbody/tr/td[1]/ng-include/span")
	private WebElement NoOfDaysElement;
	
	@FindBy(xpath="//a[text()=\"Close\"]")
	private WebElement CloseButtonForPopUpElement;
	
	@FindBy(xpath="//span[text()=\"From date should be before to date\"]")
	private WebElement DateErrorMessageElement;
	
	@FindBy(xpath="//div[@id=\"leaveType_inputfileddiv\"]/div/input")
	private WebElement LeaveTypeInputElement;
	
	public ApplyLeavePage(WebDriver driver) {
		super(driver);
		this.driver=driver;
		PageFactory.initElements(new AjaxElementLocatorFactory(driver,5), this);
	}
	
	public ApplyLeavePage setLeaveTypeTo(String LeaveType) {
		LeaveTypeDropDownElement.click();
		FormElement.findElement(By.xpath("//span[text()=\""+LeaveType+"\"]")).click();
		
		return this;
	}
	
	public ApplyLeavePage setFromDate(String FromDate) {
		FormDateInputElement.sendKeys(FromDate);;
		return this;
	}
	
	public ApplyLeavePage setToDate(String ToDate) {
		ToDateInputElement.sendKeys(ToDate);;
		return this;
	}
	
	public ApplyLeavePage TypeComment(String comment) {
		CommentTextAreaElement.sendKeys(comment);;
		return this;
	}
	
	public ApplyLeavePage submitForm() {
		FormElement.submit();
		return this;
	}
	
	public ApplyLeavePage clickOnCheckBalanceLeavesHyperText() {
		chechBalanceLeavesHyperTextElement.click();
		return this;
	}
	
	public double checkAvailableBalanceDaysInPopUp() {
		return Double.parseDouble(NoOfDaysElement.getAttribute("innerHTML"));
	}
	
	public ApplyLeavePage closeBalanceLeavesPopUp() {
		CloseButtonForPopUpElement.click();
		return this;
	}
	
	public WebElement getDateErrorMessageElement() {
		return DateErrorMessageElement;
	}
	
	public String getLeaveTypeSelectedInDropDown() {
		return LeaveTypeInputElement.getAttribute("value");
	}
}
