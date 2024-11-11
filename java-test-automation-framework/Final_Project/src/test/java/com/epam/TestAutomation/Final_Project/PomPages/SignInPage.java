package com.epam.TestAutomation.Final_Project.PomPages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;



public class SignInPage {
	
	@FindBy(id = "exampleInputEmail1")
	private WebElement emailInput;

	@FindBy(id = "exampleInputPassword1")
	private WebElement passwordInput;
	
	@FindBy(id = "form_submit") 
	private WebElement submitButton;
	
	WebDriver driver;
	
	public SignInPage(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(new AjaxElementLocatorFactory(driver,10), this);
	}
	
	public SignInPage enterEmailID(String userName) {
		this.emailInput.sendKeys(userName);
		return this;
	}

	public SignInPage enterPassword(String password) {
		this.passwordInput.sendKeys(password);
		return this;
	}

	public DashBoardPage clickSubmitButton() {
		this.submitButton.click();
		return new DashBoardPage(driver);
	}

	
	public DashBoardPage login(String userName, String password) {
		return this.enterEmailID(userName).enterPassword(password).clickSubmitButton();
		
	}
	
	public void clickOnSignUp() {
		driver.findElement(By.xpath("//a[text()='Sign up']")).click();
	}

}
