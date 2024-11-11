package com.epam.TestAutomation.BDD.CucumberDefinitions;

import java.net.MalformedURLException;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;


import com.epam.TestAutomation.BDD.DriverFactory.DriverFactoryProvider;
import com.epam.TestAutomation.BDD.PomPages.DashBoardPage;
import com.epam.TestAutomation.BDD.PomPages.LogInPage;
import com.epam.TestAutomation.BDD.PomPages.DashBoardPage;

import io.cucumber.java.After;
import io.cucumber.java.AfterAll;
import io.cucumber.java.Before;
import io.cucumber.java.BeforeAll;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import com.epam.TestAutomation.BDD.Suppliers.TestContextSupplier;
public class CucumberDefinitionImpl {
	WebDriver driver;
	LogInPage logInPage;
	DashBoardPage dashBoardPage;
	String platformName;
	String url;
	ITestContext context;
	//ITestContext context;
	
	
	  @Before 
	  public void before_all() throws MalformedURLException {
	  context=TestContextSupplier.getContext(); System.out.println("setUp");
	  String driverName=(String)context.getCurrentXmlTest().getParameter("driverName");
	  platformName=(String)context.getCurrentXmlTest().getParameter("PlatformName");
	  driver=DriverFactoryProvider.getDriverFactory(platformName).getWebDriver(driverName); 
	  url=context.getCurrentXmlTest().getParameter("OrangeHRMUrl");
	  //driver.get(url);
	  logInPage=new LogInPage(driver); 
	  }
	 
	 
	
	  /*@Given("Open Browser") public static void Open_Browser() throws
	  MalformedURLException { context=TestContextSupplier.getContext();
	  System.out.println("setUp"); String
	  driverName=(String)context.getCurrentXmlTest().getParameter("driverName");
	  platformName=(String)context.getCurrentXmlTest().getParameter("PlatformName")
	  ; driver=DriverFactoryProvider.getDriverFactory(platformName).getWebDriver(
	  driverName); url=context.getCurrentXmlTest().getParameter("OrangeHRMUrl");
	  //driver.get(url); 
	  logInPage=new LogInPage(driver); 
	  }*/
	 
	@Given("user in logIn Page")
	public void user_in_logIn_Page() {
		driver.get(url);
	}
	@Given("username is {string} and password is {string}")
	public void username_is_and_password_is(String string, String string2) {
		System.out.println(url);
		logInPage.enterUserName(string)
			     .enterPassword(string2);
		//System.out.println(url);
		
	}

	@When("user click on login button")
	public void user_click_on_login_button() {
	    logInPage.clickLoginButton();
	    //System.out.println(url);
	}

	@Then("user should navigate to {string}")
	public void user_should_navigate_to_dashboard_page(String page) {
		assert(driver.getCurrentUrl().contains(page));
		//System.out.println(url);
	}
	@After
	public void closeTab() {
		driver.quit();
	}
}
