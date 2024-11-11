package com.epam.TestAutomation.Final_Project.Listeners;

import java.util.List;
import java.util.Map;

import org.testng.IReporter;
import org.testng.ISuite;
import org.testng.ISuiteResult;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.xml.XmlSuite;

public class ReportListenerImpl implements IReporter {

	 @Override
	   public void generateReport(List<XmlSuite> xmlSuites, List<ISuite> suites,
	      String outputDirectory) {
		 IReporter.super.generateReport(xmlSuites, suites, outputDirectory);

	      //Iterating over each suite included in the test
	      for (ISuite suite : suites) {

	         //Following code gets the suite name
	         String suiteName = suite.getName();
	         System.out.println("Suite-> "+suiteName+":");
	         
	         
	         //Getting the results for the said suite
	         Map<String, ISuiteResult> suiteResults = suite.getResults();
	         
	         for (ISuiteResult sr : suiteResults.values()) {
	        	 
	            ITestContext tc = sr.getTestContext();
	            String TestName=tc.getName();
	            System.out.println("          Test-> "+TestName+":");
	            System.out.println();
	            
	            System.out.println("Passed tests-" + tc.getPassedTests().getAllResults().size());
	            System.out.println("Failed tests-" + tc.getFailedTests().getAllResults().size());
	            System.out.println("Skipped tests-"+ tc.getSkippedTests().getAllResults().size());
	            System.out.println();
	            
	            
	            System.out.println("All Passed Tests:");
	            for(ITestResult x: tc.getPassedTests().getAllResults()) {
	            	System.out.println(x.getName());
	            }
	            System.out.println();
	            
	            System.out.println("All Failed Tests:");
	            for(ITestResult x: tc.getFailedTests().getAllResults()) {
	            	System.out.println(x.getName());
	            }
	         }
	      }
	   }

}
