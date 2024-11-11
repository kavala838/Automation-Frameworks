package com.epam.TestAutomation.BDD.Suppliers;

import org.testng.ITestContext;

public class TestContextSupplier {
	private static ITestContext context;
	public static void setContext(ITestContext context1) {
		context=context1;
	}
	public static ITestContext getContext() {
		return context;
	}
}
