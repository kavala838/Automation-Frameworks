package com.epam.TestAutomation.BDD.Tests;

import org.testng.ITestContext;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.cucumber.core.gherkin.Feature;
import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import io.cucumber.testng.FeatureWrapper;
import io.cucumber.testng.PickleWrapper;
import io.cucumber.testng.TestNGCucumberRunner;

import com.epam.TestAutomation.BDD.Suppliers.TestContextSupplier;
@CucumberOptions(
		features="src\\test\\resources\\features\\LogInFeature.feature",
		glue= {"com.epam.TestAutomation.BDD.CucumberDefinitions"}
		)
public class TestLogInWithRunner {
	TestNGCucumberRunner runner=new TestNGCucumberRunner(this.getClass());
	@BeforeClass
	public void testlogIn(ITestContext context) {
		TestContextSupplier.setContext(context);
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
	
}
