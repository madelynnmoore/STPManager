/**
 * 
 */
package edu.ncsu.csc216.stp.model.tests;

import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

/**
 * Tests the TestCase class.
 * @author Annabella Jackmore, Evans Chege
 *
 */
public class TestCaseTest {
	
	/** This is the test variable for TestCaseId */
	private String testCaseId = "test1";
	/** This is the test variable for TestCaseType */
	private String testType = "Equivalence Class";
	/** String to represent the test description parameter */
	private String testDescription = "description\n" + "with multiple lines";
	/** String to represent the expected results parameter */
	private String expectedResults = "expected results\n" + "with multiple lines";
	/** String to represent the actual results parameter */
	private String actualResults = "actual results";
	
	/**
	 * Tests the construction of a valid TestCase.
	 */
	@Test
	public void testValidTestCase() {
		TestCase t1 = assertDoesNotThrow(
				() -> new TestCase(testCaseId, testType, testDescription, expectedResults));
		
		assertAll("TestCase",
				() -> assertEquals(testCaseId, t1.getTestCaseId(), "Incorrect Test Case ID"),
				() -> assertEquals(testType, t1.getTestType(), "incorrect Test Case Type"),
				() -> assertEquals(testDescription, t1.getTestDescription(), "Incorrect Test Case Description"),
				() -> assertEquals(expectedResults, t1.getExpectedResults(), "Incorrect Test Case ID"));
		
	}
	
	/**
	 * Tests the construction of an invalid TestCase.
	 */
	@Test
	public void testInvalidTestCase() {
		Exception exception = assertThrows(IllegalArgumentException.class,
				() -> new TestCase(null, testType, testDescription, expectedResults));
		assertEquals("Invalid test information.", exception.getMessage(), "Incorrect exception thrown with invalid test case Id - " + null);
		
		Exception exception2 = assertThrows(IllegalArgumentException.class,
				() -> new TestCase(testCaseId, null, testDescription, expectedResults));
		assertEquals("Invalid test information.", exception2.getMessage(), "Incorrect exception thrown with invalid test type - " + null);
		
		Exception exception3 = assertThrows(IllegalArgumentException.class,
				() -> new TestCase(testCaseId, testType, null, expectedResults));
		assertEquals("Invalid test information.", exception3.getMessage(), "Incorrect exception thrown with invalid test case description - " + null);
		
		Exception exception4 = assertThrows(IllegalArgumentException.class,
				() -> new TestCase(testCaseId, testType, testDescription, null));
		assertEquals("Invalid test information.", exception4.getMessage(), "Incorrect exception thrown with invalid test case expected results - " + null);
		
		
	}
	
	/**
	 * Tests adding a valid test result.
	 */
	@Test
	public void testAddValidTestResult() {
		TestCase t = new TestCase(testCaseId, testType, testDescription, expectedResults);
		t.addTestResult(true, actualResults);
		assertTrue(t.isTestCasePassing());
	}
	
	/**
	 * Tests adding an invalid test result.
	 */
	@Test
	public void testAddInvalidTestResult() {
		TestCase t = new TestCase(testCaseId, testType, testDescription, expectedResults);
		assertThrows(IllegalArgumentException.class,
				() -> t.addTestResult(true, null));
	}
	
	/**
	 * Tests that last TestResult in the Log is not passing.
	 */
	@Test
	public void testFailingTestResult() {
		TestCase t = new TestCase(testCaseId, testType, testDescription, expectedResults);
		t.addTestResult(false, "- FAIL: actual results on\n" + "multiple lines");
		assertFalse(t.isTestCasePassing());
	}
	
	/**
	 * Tests getting the status of the TestCase as “PASS” or “FAIL”. 
	 */
	@Test
	public void testGetStatus() {
		TestCase t = new TestCase(testCaseId, testType, testDescription, expectedResults);
		t.addTestResult(true, actualResults);
		assertEquals("PASS", t.getStatus());
		
		TestCase t2 = new TestCase("Test2", "Boundary Type", testDescription, expectedResults);
		t.addTestResult(false, "Index out of bounds");
		assertEquals("FAIL", t2.getStatus());
	}
	
	/**
	 * Tests getting a string representation of the testResults Log.
	 */
	@Test
	public void testGetActualResultsLog() {
		TestCase t = new TestCase(testCaseId, testType, testDescription, expectedResults);
		t.addTestResult(true, actualResults);
		assertEquals("- PASS: " + actualResults + "\n", t.getActualResultsLog());
	}
	
	/**
	 * Tests getting a string representation of the TestCase for printing to a file.
	 */
	@Test
	public void testToString() {
		TestCase t = new TestCase(testCaseId, testType, testDescription, expectedResults);
		t.addTestResult(true, actualResults);
		assertEquals("# test1,Equivalence Class\n" + "* description\n" + "with multiple lines\n" + "* expected results\n"
				+ "with multiple lines\n" + "- PASS: actual results\n", t.toString());
	}
}
