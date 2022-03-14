package com.epam.TestAutomation.Final_Project.PomPages;

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

import com.paulhammant.ngwebdriver.NgWebDriver;

public class SpeakersPage extends MenuContentSection {

	@FindBy(xpath = "//div[@Class=\"panel-heading\"]/div/div/button[@Class=\"uui-button blue\"]")
	private WebElement addSpeakerButton;
	
	@FindBy(xpath = "//input[@name=\"speakerName\"]")
	private WebElement nameField;
	
	@FindBy(xpath = "//input[@name=\"speakerEmail\"]")
	private WebElement emailField;
	
	@FindBy(xpath = "//input[@name=\"speakerProfile\"]")
	private WebElement profileField;
	
	@FindBy(xpath = "//button[text()=\"Save\"]")
	private WebElement saveButton;
	
	@FindBy(xpath = "//span[text()=\"Speaker created successfully.\"]")
	private WebElement MsgEle;
	
	@FindBy(xpath = "//input[@type=\"search\"]")
	private WebElement searchField;
	
	private static NgWebDriver ngDriver;
	private JavascriptExecutor js;
	
	public SpeakersPage(WebDriver driver) {
		super(driver);
		PageFactory.initElements(new AjaxElementLocatorFactory(driver,10), this);
		
		js=(JavascriptExecutor)driver;
		ngDriver=new NgWebDriver(js);
	}

	public SpeakersPage clickOnAddSpeakerButton() {
		addSpeakerButton.click();
		return this;
	}

	public SpeakersPage addDetails(String string, String string2, String string3) {
		fillNameField(string);
		fillEmailField(string2);
		fillProfileField(string3);
		return this;
		
	}

	public void fillProfileField(String string) {
		new WebDriverWait(driver, Duration.ofSeconds(10))
        .until(ExpectedConditions.elementToBeClickable(profileField));
		profileField.sendKeys(string);
		
	}

	public void fillEmailField(String string) {
		new WebDriverWait(driver, Duration.ofSeconds(10))
        .until(ExpectedConditions.elementToBeClickable(emailField));
		emailField.sendKeys(string);
		
	}

	public void fillNameField(String string) {
		new WebDriverWait(driver, Duration.ofSeconds(10))
        .until(ExpectedConditions.elementToBeClickable(nameField));
		nameField.sendKeys(string);
		
	}
	
	public SpeakersPage clickOnSave() {
		saveButton.click();
		return this;
	}

	public boolean isSuccefullyAddedMsgAppeared() {
		return MsgEle.isDisplayed();
	}

	public void searchName(String speakerName) throws InterruptedException {
		// TODO Auto-generated method stub
		
		//ngDriver.waitForAngularRequestsToFinish();
		//searchField.sendKeys(speakerName);
		
		/*
		 * String
		 * script="document.evaluate('//input[@type=\"search\"]', document, null, XPathResult.FIRST_ORDERED_NODE_TYPE, null).singleNodeValue.value="
		 * +speakerName+";"; js.executeScript(script);
		 */
		
		Thread.sleep(3000);
		searchField.sendKeys(speakerName);
	}

	public boolean isDataDisplayed(String speakerName, String speakerEmail, String speakerProfile) {
		// TODO Auto-generated method stub
		WebElement name= new WebDriverWait(driver, Duration.ofSeconds(30))
		         .until(driver -> driver.findElement(By.xpath("//td[text()=\""+speakerName+"\"]")));
		
		WebElement mail= new WebDriverWait(driver, Duration.ofSeconds(30))
		         .until(driver -> driver.findElement(By.xpath("//td[text()=\""+speakerEmail+"\"]")));
		
		WebElement profile= new WebDriverWait(driver, Duration.ofSeconds(30))
		         .until(driver -> driver.findElement(By.xpath("//td[text()=\""+speakerProfile+"\"]")));
		return (name.isDisplayed() && mail.isDisplayed() && profile.isDisplayed());
	}

}
