package com.epam.TestAutomation.TestNG;

import static org.testng.Assert.assertTrue;
import org.testng.annotations.*;

/**
 * Unit test for simple App.
 */
public class AppTest 
{
    @Test
    public void testApp()
    {
        assertTrue( true );
        System.out.println("Executing Test");
    }
    @BeforeSuite
    public void beforeSuite() {
    	System.out.println("Before Suit");
    }
    @AfterMethod
    public void afterMethod() {
    	System.out.println("Executing after Method");
    }
    @BeforeMethod
    public void beforeMethod() {
    	System.out.println("Executing Before Method");
    }
    
    @AfterSuite
    public void afterSuite() {
    	System.out.println("Executing After Suit");
    }
    @AfterTest
    public void aftertest() {
    	System.out.println("Executing After Test");
    }
    
}
