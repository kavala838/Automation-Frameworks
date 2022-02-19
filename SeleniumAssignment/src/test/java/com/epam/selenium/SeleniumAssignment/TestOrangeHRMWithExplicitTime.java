package com.epam.selenium.SeleniumAssignment;

import static org.testng.Assert.assertEquals;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.epam.selenium.SeleniumAssignment.WebDriverFactory.webDriverFactory;

public class TestOrangeHRMWithExplicitTime {
WebDriver driver;
	
	@Parameters({"driverName"})
	@BeforeClass
	public void setUp(String driverName) {
		
		driver =webDriverFactory.getDriver(driverName);

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
		new WebDriverWait(driver, Duration.ofSeconds(5))
        .until(driver -> driver.findElement(By.xpath("//*[@id=\"menu_admin_viewAdminModule\"]/a/span[3]"))).click();
		/*
		 * driver.findElement(By.xpath(
		 * "//*[@id=\"menu_admin_viewAdminModule\"]/a/span[3]")) .click();
		 */
		
		
		driver.findElement(By.xpath("//*[@id=\"menu_admin_UserManagement\"]/a/span[3]"))
		      .click();
		
		driver.findElement(By.xpath("//*[@id=\"menu_admin_viewSystemUsers\"]/span[2]"))
		      .click();
		
		
		assertEquals("https://prasoonr-trials73.orangehrmlive.com/client/#/admin/systemUsers",driver.getCurrentUrl());
	}
	@Test(priority=3)
	public void testChangeAdminRoleOfuser() throws InterruptedException {
		
		 // Selecting Edit Button of user Amanda
	     new WebDriverWait(driver, Duration.ofSeconds(30))
         .until(driver -> driver.findElement(By.xpath("//td/ng-include[span=\"amanda\"]/parent::td/parent::tr//i[\"ohrm_edit\"]")))
         .click();
		  
		 // Thread.sleep(1000);
	     new WebDriverWait(driver, Duration.ofSeconds(3)).until(ExpectedConditions.invisibilityOfElementLocated(By.id("preloader")));
	     
		 //Selecting DropDown Button
	     //input[@id="user_name"]
	    WebElement ele= new WebDriverWait(driver, Duration.ofSeconds(30))
         .until(driver -> driver.findElement(By.xpath("//*[@id=\"modal-holder\"]/div/div/div/div[2]/form/oxd-decorator[3]/div/div[1]/div/div[1]/button/div/div/div")))
         ;
	    new WebDriverWait(driver,Duration.ofSeconds(10))
		  .until(ExpectedConditions.attributeToBeNotEmpty(ele,"innerHTML"));
	     System.out.println("pre-click of dropdown");
	     //System.out.println(ele.getText());
		 new WebDriverWait(driver, Duration.ofSeconds(4))
         .until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div[3]/div/div/div/div[2]/form/oxd-decorator[3]/div/div[1]/div/div[2]/div/button/i[2]")))
         .click();
		 System.out.println("clicked");
		//*[@id="modal-holder"]/div/div/div/div[2]/form/oxd-decorator[3]/div/div[1]/div/div[2]/div/button/i[2]
		 //Selecting Global Admin in DropDown Options
		 new WebDriverWait(driver, Duration.ofSeconds(20))
         .until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//ul/li/a/span[text()=\"Global Admin\"]")))
         .click();
		 
		 new WebDriverWait(driver,Duration.ofSeconds(5))
		  .until(ExpectedConditions.textToBePresentInElementLocated(By.xpath("//select[@id=\"adminrole\"]/parent::div/button/div/div/div"), "Global Admin"));
		 
		 
		 new WebDriverWait(driver,Duration.ofSeconds(2))
		 .until(ExpectedConditions.elementToBeClickable(By.id("modal-save-button")));
	}
	
	
	@Test(priority=4)
	public void testTheChangesAdded() throws InterruptedException {
		//Thread.sleep(1000);
		 new WebDriverWait(driver, Duration.ofSeconds(3)).until(ExpectedConditions.invisibilityOfElementLocated(By.id("preloader")));
		 WebElement ele2=new WebDriverWait(driver, Duration.ofSeconds(2))
	     .until(driver -> driver.findElement(By.xpath("//*[@id=\"systemUserDiv\"]/crud-panel/div/div/list/table/tbody/tr[6]/td[3]/ng-include/span")));
		
		String s=ele2.getAttribute("innerHTML");
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
		new WebDriverWait(driver, Duration.ofSeconds(3)).until(ExpectedConditions.invisibilityOfElementLocated(By.id("preloader")));
		new WebDriverWait(driver, Duration.ofSeconds(20))
        .until(ExpectedConditions.elementToBeClickable(By.xpath("//td/ng-include[span=\"amanda\"]/parent::td/parent::tr//i[\"ohrm_edit\"]"))).click();
		
		
		new WebDriverWait(driver, Duration.ofSeconds(10))
	     .until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div[3]/div/div/div/div[2]/form/oxd-decorator[3]/div/div[1]/div/div[2]/div/button/i[2]")))
	     .click();
	   

	    new WebDriverWait(driver, Duration.ofSeconds(20))
	     .until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"bs-select-6-0\"]")))
	     .click();
	    
	    new WebDriverWait(driver,Duration.ofSeconds(5))
		  .until(ExpectedConditions.textToBePresentInElementLocated(By.xpath("//select[@id=\"adminrole\"]/parent::div/button/div/div/div"), "-- Select --"));

	    new WebDriverWait(driver,Duration.ofSeconds(2))
		 .until(ExpectedConditions.elementToBeClickable(By.id("modal-save-button")));
		
	}
	
	
	@Test(priority=6)
	public void testRemoveAdminRoleChanges() throws InterruptedException {
		 new WebDriverWait(driver, Duration.ofSeconds(3)).until(ExpectedConditions.invisibilityOfElementLocated(By.id("preloader")));
		//Thread.sleep(1000);
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
	public void testLogOut() throws InterruptedException {
		//Thread.sleep(1000);
		 new WebDriverWait(driver, Duration.ofSeconds(3)).until(ExpectedConditions.invisibilityOfElementLocated(By.id("preloader")));
		driver.findElement(By.id("user-dropdown")).click();
		driver.findElement(By.id("logoutLink")).click();
		assertEquals("https://prasoonr-trials73.orangehrmlive.com/auth/login",driver.getCurrentUrl());
		
	}
	@AfterTest
	public void closeTab() {
		//driver.quit();
	}
}

