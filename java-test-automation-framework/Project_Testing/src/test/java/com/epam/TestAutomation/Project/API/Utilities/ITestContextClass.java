package com.epam.TestAutomation.Project.API.Utilities;

import org.testng.ITestContext;

public class ITestContextClass {
	private static ITestContext context;

	public static ITestContext getContext() {
		return context;
	}

	public static void setContext(ITestContext context) {
		ITestContextClass.context = context;
	}
	
}
