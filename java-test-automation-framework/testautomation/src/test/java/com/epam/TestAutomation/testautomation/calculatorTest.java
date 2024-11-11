package com.epam.TestAutomation.testautomation;

import junit.framework.TestCase;

import static org.junit.Assert.assertThrows;

import org.junit.*;

public class calculatorTest extends TestCase {
	@Test
	public void testAddPositives() {
		int a=Calculator.add(5, 10);
		assert(15==a);
	}
	@Test
	public void testAddwithZero() {
		int a=Calculator.add(1,0);
		assert(1==a);
	}
	@Test
	public void testAddNegativePositive() {
		int a=Calculator.add(-10,10);
		assert(0==a);
	}
	@Test
	public void testAddNegatives() {
		int a=Calculator.add(-15,-81);
		assert(-96==a);
		//assertThrows(ArithmeticException.class,()->Calculator.divide(10, 0));
	}
	@Test
	public void testSubtractPositives() {
		int a=Calculator.subtract(10, 5);
		assert(5==a);
	}
	@Test
	public void testSubtractEquals() {
		int a=Calculator.subtract(50,50);
		assertEquals(0,a);
	}
	
	@Test
	public void testSubtractNegativewithPositive() {
		int a=Calculator.subtract(-10,5);
		assert(-15==a);
	}
	@Test
	public void testSubtractNegatives() {
		int a=Calculator.subtract(-11,-26);
		assert(15==a);
	}
	@Test
	public void testMultiplyPositives() {
		int a=Calculator.multiply(2, 6);
		assert(12==a);
	}
	@Test
	public void testMultiplyPositiveNegative() {
		int a=Calculator.multiply(-12, 8);
		assert(-96==a);
	}
	@Test
	public void testMultiplywithZero() {
		int a=Calculator.multiply(0, 6);
		assert(0==a);
	}
	@Test
	public void testMultiplyNegatives() {
		int a=Calculator.multiply(-6, -7);
		assert(42==a);
	}
	@Test
	public void testDivide() {
		int a=Calculator.divide(12, 2);
		assert(6==a);
	}
	@Test
	public void testDivideEquals() {
		int a=Calculator.divide(2, 2);
		assert(1==a);
	}
	
	@Test
	public void testDividePositiveNegative() {
		int a=Calculator.divide(-24, 11);
		assert(-2==a);
	}
	@Test
	public void testDividewithZero() {
		assertThrows(ArithmeticException.class,()->Calculator.divide(10, 0));
	}
}

