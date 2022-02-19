package com.epam.selenium.SeleniumAssignment.PageFactory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LogInPageWithActionsClass {
	private final WebDriver driver;
	Actions actions;

	@FindBy(id = "txtUsername")
	private WebElement userName;

	@FindBy(id = "txtPassword")
	private WebElement password;

	@FindBy(xpath = "//button[@type=\"submit\"]") // //button[text()="Login"]
	private WebElement loginButton;

	public LogInPageWithActionsClass(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		actions=new Actions(driver);
	}

	public LogInPageWithActionsClass enterUserName(String userName) {
		actions.moveToElement(this.userName).sendKeys(userName);
		//this.userName.sendKeys(userName);
		return this;
	}

	public LogInPageWithActionsClass enterPassword(String password) {
		actions.moveToElement(this.password).sendKeys(password);
		//this.password.sendKeys(password);
		return this;
	}

	public LogInPageWithActionsClass clickLoginButton() {
		actions.moveToElement(this.loginButton).click().perform();
		//this.loginButton.click();
		return this;
	}

	public LogInPageWithActionsClass login(String userName, String password) {
		this.enterUserName(userName).enterPassword(password).clickLoginButton();
		return this;
	}
}
