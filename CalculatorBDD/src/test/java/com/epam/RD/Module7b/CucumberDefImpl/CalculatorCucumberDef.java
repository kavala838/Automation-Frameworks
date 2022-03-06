package com.epam.RD.Module7b.CucumberDefImpl;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.Color;
import org.testng.ITestContext;

import com.epam.RD.Module7b.Pages.CalculatorPage;
import com.epam.RD.Module7b.SetterAndProvider.TestContextProvider;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.*;

public class CalculatorCucumberDef {
	WebDriver driver;
	CalculatorPage calculatorPage;
	String url;
	
	@Before
	public void setUp() {
		ITestContext context=TestContextProvider.getContext();
		driver=(WebDriver)context.getAttribute("driver");
		calculatorPage=(CalculatorPage)context.getAttribute("CalculatorPage");
		url=context.getCurrentXmlTest().getParameter("HtmlUrl");
	}
	

	@Given("Calculator page is open")
	public void calculator_page_is_open() {
	    driver.get(url);
	}

	@Then("Calcaultor has number {string}")
	public void calcaultor_has_number(String string) {
		assertTrue(calculatorPage.hasButtonWithText(string));
	}

	@Then("Calcaultor has operator {string}")
	public void calcaultor_has_operator(String string) {
		assertTrue(calculatorPage.hasButtonWithText(string));
	}

	@Then("Calcaultor has display field")
	public void calcaultor_has_display_field() {
	    assertTrue(calculatorPage.hasDisplayField());
	    
	    
	}

	@Then("Calcaultor {string}  has color {string}")
	public void calcaultor_has_color(String string, String string2) {
		WebElement displayElement=calculatorPage.getWebelementByText(string);
		Color Actualcolor=Color.fromString(displayElement.getCssValue("Color"));
	    Color ExpectedColor=Color.fromString(string2);
	    assertEquals(Actualcolor,ExpectedColor);
	}

	@When("number button is clicked {string}")
	public void number_button_is_clicked(String string) {
		calculatorPage.getWebelementByText(string).click();
	}

	@Then("display screen should have number {string}")
	public void display_screen_should_have_number(String string) {
		String ActualText=calculatorPage.getDisplayField().getText();
		assertEquals(ActualText,string);
	}

	@When("numbers button is clicked")
	public void numbers_button_is_clicked(io.cucumber.datatable.DataTable dataTable) {
	   
		List<List<String>> numbers=dataTable.asLists(String.class);
		for(List<String> list : numbers){
		    for(String i : list){
		    	calculatorPage.getWebelementByText(i).click();
		    }
		}

	}

	@When("number  is typed {string}")
	public void number_is_typed(String string) {
	    int n=string.length();
	    for(int i=0;i<n;i++)
	    {
	    	String x=Character.toString(string.charAt(i));
	    	calculatorPage.getWebelementByText(x).click();
	    }
	}

	@When("Operator button is clicked {string}")
	public void operator_button_is_clicked(String string) {
		calculatorPage.getWebelementByText(string).click();
	}
	
}
