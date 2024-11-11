package com.epam.selenium.SeleniumAssignment;

import static org.testng.Assert.assertEquals;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.epam.selenium.SeleniumAssignment.WebDriverFactory.webDriverFactory;

public class TestOrangeHRMWithImplicitTime {
WebDriver driver;
	
	@Parameters({"driverName"})
	@Test
	public void setUp(String driverName) {
		
		driver =webDriverFactory.getDriver(driverName);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		driver.manage().window().maximize();
		driver.get("https://prasoonr-trials73.orangehrmlive.com/");
	}
	@Test(priority=1)
	public void testLogIn() throws InterruptedException {
		
		
		driver.findElement(By.id("txtUsername"))
	         .sendKeys("Admin");
		
		
		driver.findElement(By.id("txtPassword"))
	          .sendKeys("U@qBLVtm09");
		
		driver.findElement(By.className("button-holder"))
		      .click();
		
		
		
		
		assertEquals("https://prasoonr-trials73.orangehrmlive.com/client/#/dashboard",driver.getCurrentUrl());
	}
	@Test(priority=2)
	public void testNavigateToUsers() throws InterruptedException {
		
		driver.findElement(By.xpath("//*[@id=\"menu_admin_viewAdminModule\"]/a/span[3]"))
		      .click();
		
		
		driver.findElement(By.xpath("//*[@id=\"menu_admin_UserManagement\"]/a/span[3]"))
		      .click();
		
		driver.findElement(By.xpath("//*[@id=\"menu_admin_viewSystemUsers\"]/span[2]"))
		      .click();
		
		
		assertEquals("https://prasoonr-trials73.orangehrmlive.com/client/#/admin/systemUsers",driver.getCurrentUrl());
	}
	@Test(priority=3)
	public void testChangeAdminRoleOfuser() throws InterruptedException {
		 
		  driver.findElement(By.xpath("//td/ng-include[span=\"amanda\"]/parent::td/parent::tr//i[\"ohrm_edit\"]")).click();
		  
		 // Thread.sleep(2000);
		  
		   driver.findElement(By.xpath(
		  "//button/div/div/div[text()=\"-- Select --\"]"
		  )).click();
		  
		  driver.findElement(By.id("bs-select-3-2")).click();
		  
		  driver.findElement(By.id("modal-save-button")).click();
		 
	}
	
	
	@Test(priority=4)
	public void testTheChangesAdded() throws InterruptedException {

		Thread.sleep(1000);
		WebElement spanElement=driver.findElement(By.xpath("//*[@id=\"systemUserDiv\"]/crud-panel/div/div/list/table/tbody/tr[6]/td[3]/ng-include/span"));
		String s=spanElement.getAttribute("innerHTML");
		String[] arr=s.split(",");
		
		boolean flag=false;
		
		for(String role:arr) {
			System.out.println(role);
			if(role.equals(" Global Admin"))
				flag=true;
		}
		
		assert(flag);
	}
	
	
	@Test(priority=5)
	public void testChangeWhenAdminRoleRemoved() throws InterruptedException {
	
		
		driver.findElement(By.xpath("//td/ng-include[span=\"amanda\"]/parent::td/parent::tr//i[\"ohrm_edit\"]")).click();
				 
		Thread.sleep(1000);
		
		WebElement dropDown = driver.findElement(By.xpath(
						"//*[@id=\"modal-holder\"]/div/div/div/div[2]/form/oxd-decorator[3]/div/div[1]/div/div[1]/button"));
		dropDown.click();
				

		driver.findElement(By.id("bs-select-6-0")).click();

		driver.findElement(By.id("modal-save-button")).click();
		
	}
	
	
	@Test(priority=6)
	public void testRemoveAdminRoleChanges() throws InterruptedException {
		Thread.sleep(1000);
		
		WebElement spanElement=driver.findElement(By.xpath("//*[@id=\"systemUserDiv\"]/crud-panel/div/div/list/table/tbody/tr[6]/td[3]/ng-include/span"));
		String s=spanElement.getAttribute("innerHTML");
		String[] arr=s.split(",");
		
		boolean flag=true;
		
		for(String role:arr) {
			System.out.println(role);
			if(role.equals(" Global Admin"))
				flag=false;
		}
		
		assert(flag);
	}
	
	
	@Test(priority=7)
	public void testLogOut() {
		driver.findElement(By.id("user-dropdown")).click();
		driver.findElement(By.id("logoutLink")).click();
		assertEquals("https://prasoonr-trials73.orangehrmlive.com/auth/login",driver.getCurrentUrl());
		
	}
	@AfterClass
	public void closeTab() {
		driver.quit();
	}
}
