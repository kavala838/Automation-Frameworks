package com.epam.TestAutomation.TestNG;
import static org.testng.Assert.assertTrue;
import org.testng.annotations.*;
public class IgnoreTest {
	@Test
	public void testApp()
    {
        assertTrue( true );
        
    }
    @Test(enabled=false)
    public void beforeSuite() {
    	assertTrue( true );
    }
}
