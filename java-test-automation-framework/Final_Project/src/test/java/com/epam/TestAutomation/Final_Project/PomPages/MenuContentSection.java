package com.epam.TestAutomation.Final_Project.PomPages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.paulhammant.ngwebdriver.NgWebDriver;



public class MenuContentSection {
	
	protected final WebDriver driver;
	//private static JavascriptExecutor js;
	private static NgWebDriver ngDriver;
	private JavascriptExecutor js;
	
	@FindBy(xpath = "//div[@Class='uui-toggle-box']")
	private WebElement toggleBoxEle;
	
	@FindBy(id = "mCSB_2_container")
	private WebElement menuContentDivElement;
	Actions builder;
	public MenuContentSection(WebDriver driver) {
		this.driver=driver;
		js=(JavascriptExecutor)driver;
		PageFactory.initElements(new AjaxElementLocatorFactory(driver,10), this);
		builder = new Actions(driver);
		js=(JavascriptExecutor)driver;
		ngDriver=new NgWebDriver(js);
        
	}
	
	
public MenuContentSection clickOnElementInMenuContent(String elementName) throws InterruptedException {
		
		if(!menuContentDivElement.getAttribute("style").contains("none")) {
			toggleBoxEle.click();
			
		}
		
		WebElement Menu=driver.findElement(By.xpath("//div[@id=\"mCSB_2_container\"]//span[text()='"+elementName+"']"));
		WebElement MenuContainer=driver.findElement(By.xpath("//span[text()=\""+elementName+"\"]/parent::a/parent::li"));
		
		if(!MenuContainer.getAttribute("class").contains("open")) {
			
			new WebDriverWait(driver, Duration.ofSeconds(10))
	         .until(ExpectedConditions.elementToBeClickable(Menu))
	         .click();
		
		}
		
		return this;
	}
	
	public MenuContentSection clickOnElementInSubMenuContent(String subElementName) throws InterruptedException {
		
		WebElement subMenu= driver.findElement(By.xpath("//div[@id=\"mCSB_2_container\"]//span[text()=\""+subElementName+"\"]"));
		
		new WebDriverWait(driver, Duration.ofSeconds(10))
        .until(ExpectedConditions.elementToBeClickable(subMenu))
        .click();
		
		return this;
	}
	
	public MenuContentSection clickOnMenuDropdown() {
		toggleBoxEle.click();
		return this;
	}
	
	
}
