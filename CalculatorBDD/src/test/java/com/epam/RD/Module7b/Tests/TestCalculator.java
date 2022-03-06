package com.epam.RD.Module7b.Tests;

import java.net.MalformedURLException;
import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.epam.RD.Module7b.Pages.CalculatorPage;
import com.epam.RD.Module7b.SetterAndProvider.TestContextProvider;
import com.epam.RD.Module7b.WebDriverFcatory.webDriverFactory;

import io.cucumber.testng.CucumberOptions;
import io.cucumber.testng.FeatureWrapper;
import io.cucumber.testng.PickleWrapper;
import io.cucumber.testng.TestNGCucumberRunner;


@CucumberOptions(
		features="src\\test\\resources\\Features\\CalculatorFeatures.feature",
		glue= {"com.epam.RD.Module7b.CucumberDefImpl"},
		dryRun= false,
		plugin = { "pretty:target/cucumber-reports/report.html"}
		)
public class TestCalculator {
	WebDriver driver;
	TestNGCucumberRunner runner=new TestNGCucumberRunner(this.getClass());
	@BeforeClass
	public void setUp(ITestContext context) throws MalformedURLException {
		String driverName=(String)context.getCurrentXmlTest().getParameter("driverName");
	    driver = webDriverFactory.getDriver(driverName);
	    driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(4));
	    context.setAttribute("driver", driver);
	    CalculatorPage calculatorPage=new CalculatorPage(driver);
	    context.setAttribute("CalculatorPage", calculatorPage);
	    TestContextProvider.setContext(context);
	}
	@Test(dataProvider= "Scenarios")
	public void testLogIn(PickleWrapper pickle,FeatureWrapper feature) {
		runner.runScenario(pickle.getPickle());
	}
	@DataProvider
	public Object[][] Scenarios(){
		Object[][] sc=runner.provideScenarios();
		return sc;
	}
	@AfterClass
	public void close() {
		driver.quit();
	}
}
