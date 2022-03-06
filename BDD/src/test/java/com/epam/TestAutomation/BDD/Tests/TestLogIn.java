package com.epam.TestAutomation.BDD.Tests;

import org.testng.ITestContext;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import com.epam.TestAutomation.BDD.Suppliers.TestContextSupplier;
@CucumberOptions(
		features="src\\test\\resources\\features\\LogInFeature.feature",
		glue= {"com.epam.TestAutomation.BDD.CucumberDefinitions"}
		)
public class TestLogIn extends AbstractTestNGCucumberTests{
	@BeforeClass
	public void testlogIn(ITestContext context) {
		System.out.println("BeforeClass of TestLogIn");
		TestContextSupplier.setContext(context);
	}
}
