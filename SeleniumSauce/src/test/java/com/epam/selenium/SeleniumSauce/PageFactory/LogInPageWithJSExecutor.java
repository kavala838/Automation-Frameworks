package com.epam.selenium.SeleniumSauce.PageFactory;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

public class LogInPageWithJSExecutor {
	private final WebDriver driver;
	JavascriptExecutor js;
	@FindBy(id = "txtUsername")
	private WebElement userName;

	@FindBy(id = "txtPassword")
	private WebElement password;

	@FindBy(xpath = "//button[@type=\"submit\"]") // //button[text()="Login"]
	private WebElement loginButton;

	public LogInPageWithJSExecutor(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(new AjaxElementLocatorFactory(driver,3), this);
		js=(JavascriptExecutor)driver;
	}

	public LogInPageWithJSExecutor enterUserName(String userName) {
		String script="document.getElementById('txtUsername').value='"+userName+"';";
		js.executeScript(script);
		//this.userName.sendKeys(userName);
		return this;
	}

	public LogInPageWithJSExecutor enterPassword(String password) {
		String script="document.getElementById('txtPassword').value='"+password+"';";
		js.executeScript(script);
		//this.password.sendKeys(password);
		return this;
	}

	public LogInPageWithJSExecutor clickLoginButton() {
		String script="document.getElementsByTagName('button')[0].click();";
		js.executeScript(script);
		//this.loginButton.click();
		return this;
	}

	public LogInPageWithJSExecutor login(String userName, String password) {
		this.enterUserName(userName).enterPassword(password).clickLoginButton();
		return this;
	}
}