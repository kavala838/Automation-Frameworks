package com.epam.TestAutomation.Project.Selenium.WebFactory;

public class DriverFactoryProvider {
	private static DriverFactory driverFactory;
	public static DriverFactory getDriverFactory(String PlatformName) {
		  if(PlatformName.equals("LocalWebDriver")) {
			  driverFactory=new WebDriverFactory();
		  }
		  else if(PlatformName.equals("GridWebDriver")) {
			  driverFactory=new GridDriverFactory();
		  }
		  else if(PlatformName.equals("SauceWebDriver")) {
			  driverFactory=new SauceDriverFactory();
		  }
		  return driverFactory;
	}
	
}
