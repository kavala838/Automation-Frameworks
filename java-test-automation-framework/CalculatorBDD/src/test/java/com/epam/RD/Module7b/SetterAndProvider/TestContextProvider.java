package com.epam.RD.Module7b.SetterAndProvider;

import org.testng.ITestContext;

public class TestContextProvider {
	private static ITestContext context;

	public static ITestContext getContext() {
		return context;
	}

	public static void setContext(ITestContext context1) {
		context = context1;
	}
	
}
