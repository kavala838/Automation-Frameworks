package com.epam.selenium.OrangeHRM.PomPages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.paulhammant.ngwebdriver.NgWebDriver;

public class UsersPage extends MenuContentSection {
	private final WebDriver driver;
	private JavascriptExecutor js;
	private NgWebDriver ngDriver;
	public UsersPage(WebDriver driver) {
		super(driver);
		this.driver=driver;
		js=(JavascriptExecutor)driver;
		ngDriver=new NgWebDriver(js);
	}
	
	public boolean checkTheUserWhetherAssignedToGivenRole(String username,String role) throws InterruptedException {
		//Thread.sleep(2000);
		ngDriver.waitForAngularRequestsToFinish();
		WebElement RolesElement=driver.findElement(By.xpath("//span[text()=\""+username+"\"]/parent::ng-include/parent::td/following-sibling::td/ng-include/span"));
		
		String roles=RolesElement.getText();
		
		return(roles.contains(role));
	}
	
	public UsersPage AssignGlobalAdminRoleToTheUser(String username) throws InterruptedException {
		driver.findElement(By.xpath("//td/ng-include[span=\""+username+"\"]/parent::td/parent::tr/td/i[text()=\"ohrm_edit\"]"));
		String script="document.evaluate('//td/ng-include[span=\""+username+"\"]/parent::td/parent::tr/td/i[text()=\"ohrm_edit\"]', document, null, XPathResult.FIRST_ORDERED_NODE_TYPE, null).singleNodeValue.click();";
		js.executeScript(script);
		
		driver.findElement(By.xpath("//label[@for=\"adminrole\"]/following::div/following::div/following::div/div/button/i[@class=\"material-icons\"]"));
		//Thread.sleep(2000);
		ngDriver.waitForAngularRequestsToFinish();
		script="document.evaluate('//label[@for=\"adminrole\"]/following::div/following::div/following::div/div/button/i[@class=\"material-icons\"]', document, null, XPathResult.FIRST_ORDERED_NODE_TYPE, null).singleNodeValue.click();";
		js.executeScript(script);
		
		script="document.evaluate('//ul/li/a/span[text()=\"Global Admin\"]', document, null, XPathResult.FIRST_ORDERED_NODE_TYPE, null).singleNodeValue.click();";
		js.executeScript(script);
		ngDriver.waitForAngularRequestsToFinish();
		//Thread.sleep(2000);
		script="document.getElementById('modal-save-button').click();";
		js.executeScript(script);
		
		return this;
	}
	
	public UsersPage RemoveGlobalAdminRoleToTheUser(String username) throws InterruptedException {
		driver.findElement(By.xpath("//td/ng-include[span=\""+username+"\"]/parent::td/parent::tr/td/i[text()=\"ohrm_edit\"]"));
		String script="document.evaluate('//td/ng-include[span=\""+username+"\"]/parent::td/parent::tr//i[text()=\"ohrm_edit\"]', document, null, XPathResult.FIRST_ORDERED_NODE_TYPE, null).singleNodeValue.click();";
		js.executeScript(script);
		
		driver.findElement(By.xpath("//label[@for=\"adminrole\"]/following::div/following::div/following::div/div/button/i[@class=\"material-icons\"]"));
		//Thread.sleep(2000);
		ngDriver.waitForAngularRequestsToFinish();
		script="document.evaluate('//label[@for=\\\"adminrole\\\"]/following::div/following::div/following::div/div/button/i[@class=\\\"material-icons\\\"]', document, null, XPathResult.FIRST_ORDERED_NODE_TYPE, null).singleNodeValue.click();";
		js.executeScript(script);
		
		
		script="document.evaluate('//*[@id=\"bs-select-6-0\"]', document, null, XPathResult.FIRST_ORDERED_NODE_TYPE, null).singleNodeValue.click();";
		js.executeScript(script);
		
		//Thread.sleep(2000);
		ngDriver.waitForAngularRequestsToFinish();
		script="document.getElementById('modal-save-button').click();";
		js.executeScript(script);
		
		return this;
	}
}
