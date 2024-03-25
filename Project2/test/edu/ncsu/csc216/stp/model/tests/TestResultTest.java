package edu.ncsu.csc216.stp.model.tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

/**
 * Tests the TestResult class.
 * @author Annabella Jackmore, Maddie Moore
 *
 */
class TestResultTest {
	
	/**
	 * Tests the TestResult constructor
	 */
	@Test
	void testTestResult() {
		//Tests creating a valid TestResult object
		assertDoesNotThrow(() -> new TestResult(true, "actual results"));
		//Tests creating an invalid TestResult object, empty string
		assertThrows(IllegalArgumentException.class,
				() -> new TestResult(true, ""));
		////Tests creating an invalid TestResult object, null string
		assertThrows(IllegalArgumentException.class,
				() -> new TestResult(true, null));
		
	}

	/**
	 * Tests getting a string representation of the TestResult for printing to a file and listing in the GUI.
	 */
	@Test
	void testToString() { 
		//Tests toString for a failing test
		TestResult failing = new TestResult(false, "failing results");
		assertEquals("FAIL: failing results", failing.toString());
		//Tests toString for a passing test
		TestResult passing = new TestResult(true, "passing results");
		assertEquals("PASS: passing results", passing.toString());
	}

	/**
	 * Tests TestResult.getActualResults()
	 */
	@Test
	void testGetActualResults() {
		TestResult failing = new TestResult(false, "failing results");
		assertEquals("failing results", failing.getActualResults());
		TestResult passing = new TestResult(true, "passing results");
		assertEquals("passing results", passing.getActualResults());
	}

	/**
	 * Tests TestResult.isPassing()
	 */
	@Test
	void testIsPassing() {
		TestResult failing = new TestResult(false, "failing results");
		assertFalse(failing.isPassing());
		TestResult passing = new TestResult(true, "passing results");
		assertTrue(passing.isPassing());
	}

}
