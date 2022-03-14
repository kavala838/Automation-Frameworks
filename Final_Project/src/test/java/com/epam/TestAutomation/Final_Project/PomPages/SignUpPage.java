package com.epam.TestAutomation.Final_Project.PomPages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

public class SignUpPage {
	@FindBy(id = "exampleInputEmail1")
	private WebElement emailInput;

	@FindBy(id = "exampleInputPassword1")
	private WebElement passwordInput;
	
	@FindBy(id = "form_submit") 
	private WebElement submitButton;
	
	WebDriver driver;
	
	public SignUpPage(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(new AjaxElementLocatorFactory(driver,10), this);
	}
	
	public void signUp(String emailID,String password) {
		emailInput.sendKeys(emailID);
		passwordInput.sendKeys(password);
		submitButton.click();
	}
}
