package com.epam.TestAutomation.BDD.PomPages;

import java.io.File;
import java.io.IOException;
import java.time.Duration;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ApplyLeavePage extends MenuContentSection {
	private final WebDriver driver;

	@FindBy(id = "applyLeaveForm")
	private WebElement FormElement;
	
	@FindBy(xpath="//div[@id=\"leaveType_inputfileddiv\"]/div")
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
	
	private JavascriptExecutor js;
	public ApplyLeavePage(WebDriver driver) {
		super(driver);
		this.driver=driver;
		js=(JavascriptExecutor)driver;
		PageFactory.initElements(new AjaxElementLocatorFactory(driver,10), this);
	}
	
	public ApplyLeavePage setLeaveTypeTo(String LeaveType) {
		
		LeaveTypeDropDownElement.click();
		
		FormElement.findElement(By.xpath("//span[text()=\""+LeaveType+"\"]")).click();
		
		WebElement ele=chechBalanceLeavesHyperTextElement;
		
		return this;
	}
	
	public ApplyLeavePage setFromDate(String FromDate) {
		
		FormDateInputElement.clear();
		
		FormDateInputElement.sendKeys(FromDate);
		
		return this;
	}
	
	public ApplyLeavePage setToDate(String ToDate) throws IOException {
		
		ToDateInputElement.click();
		
		/*
		 * TakesScreenshot screenShot = (TakesScreenshot)driver; File screenShotFile =
		 * screenShot.getScreenshotAs(OutputType.FILE);
		 * FileUtils.copyFile(screenShotFile, new File("./Todate.jpg"));
		 */
		
		ToDateInputElement.clear();
		
		ToDateInputElement.sendKeys(ToDate);
		
		String script="document.getElementById('to').blur();";
		js.executeScript(script);
		
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
		new WebDriverWait(driver, Duration.ofSeconds(3)).until(ExpectedConditions.invisibilityOfElementLocated(By.id("materialize-lean-overlay-1")));
		return this;
	}
	
	public WebElement getDateErrorMessageElement() throws NoSuchElementException {
		return DateErrorMessageElement;
		
	}
	
	public String getLeaveTypeSelectedInDropDown() {
		return LeaveTypeInputElement.getAttribute("value");
	}
}
