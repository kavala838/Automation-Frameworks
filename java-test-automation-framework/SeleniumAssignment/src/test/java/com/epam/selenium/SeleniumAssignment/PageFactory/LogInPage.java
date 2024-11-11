package com.epam.selenium.SeleniumAssignment.PageFactory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LogInPage {
	private final WebDriver driver;

	@FindBy(id = "txtUsername")
	private WebElement userName;

	@FindBy(id = "txtPassword")
	private WebElement password;

	@FindBy(xpath = "//button[@type=\"submit\"]") // //button[text()="Login"]
	private WebElement loginButton;

	public LogInPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public LogInPage enterUserName(String userName) {
		this.userName.sendKeys(userName);
		return this;
	}

	public LogInPage enterPassword(String password) {
		this.password.sendKeys(password);
		return this;
	}

	public LogInPage clickLoginButton() {
		this.loginButton.click();
		return this;
	}

	public LogInPage login(String userName, String password) {
		this.enterUserName(userName).enterPassword(password).clickLoginButton();
		return this;
	}
}
