package com.epam.selenium.SeleniumAssignment.PageFactory;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class UsersPage extends MenuContentSection {
	private final WebDriver driver;
	private JavascriptExecutor js;
	public UsersPage(WebDriver driver) {
		super(driver);
		this.driver=driver;
		js=(JavascriptExecutor)driver;
	}
	
	public boolean checkTheUserWhetherAssignedToGivenRole(String username,String role) {
		
		WebElement RolesElement=driver.findElement(By.xpath("//span[text()=\"amanda\"]/parent::ng-include/parent::td/following-sibling::td/ng-include/span"));
		
		String roles=RolesElement.getText();
		
		return(roles.contains(role));
	}
	
	public UsersPage AssignThisRoleToTheUser(String username) {
		String script="document.evaluate('//td/ng-include[span=\""+username+"\"]/parent::td/parent::tr//i[\"ohrm_edit\"]', document, null, XPathResult.FIRST_ORDERED_NODE_TYPE, null).singleNodeValue.click();";
		js.executeScript(script);
		
		script="document.evaluate('//label[@for=\"adminrole\"]/following-sibling::div', document, null, XPathResult.FIRST_ORDERED_NODE_TYPE, null).singleNodeValue.click();";
		js.executeScript(script);
		
		script="document.evaluate('//ul/li/a/span[text()=\"Global Admin\"]', document, null, XPathResult.FIRST_ORDERED_NODE_TYPE, null).singleNodeValue.click();";
		js.executeScript(script);
		
		script="document.getElementById('modal-save-button').click();";
		js.executeScript(script);
		
		return this;
	}
	
	public UsersPage RemoveThisRoleToTheUserIfNotDefaultRole(String username) {
		String script="document.evaluate('//td/ng-include[span=\""+username+"\"]/parent::td/parent::tr//i[\"ohrm_edit\"]', document, null, XPathResult.FIRST_ORDERED_NODE_TYPE, null).singleNodeValue.click();";
		js.executeScript(script);
		
		script="document.evaluate('//label[@for=\"adminrole\"]/following-sibling::div', document, null, XPathResult.FIRST_ORDERED_NODE_TYPE, null).singleNodeValue.click();";
		js.executeScript(script);
		
		script="document.evaluate('//*[@id=\"bs-select-6-0\"]', document, null, XPathResult.FIRST_ORDERED_NODE_TYPE, null).singleNodeValue.click();";
		js.executeScript(script);
		
		script="document.getElementById('modal-save-button').click();";
		js.executeScript(script);
		
		return this;
	}
}
