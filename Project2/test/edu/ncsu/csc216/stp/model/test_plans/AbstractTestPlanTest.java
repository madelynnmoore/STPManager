package edu.ncsu.csc216.stp.model.test_plans;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import edu.ncsu.csc216.stp.model.tests.TestCase;
/**
 * Tests AbstractTestPlan Class.
 *
 * @author Maddie Moore, Evans Chege, Annabella Jackmore
 *
 */
public class AbstractTestPlanTest {
	
	/** This is a test variable for Test Plan Name */
	private String testPlanName = "System Test Plan";
	/** This is a test variable for Test Plan Name */
	private String testPlanName2 = "Black Box Test Plan";
	/** This is the test variable for Test Case Id */
	private String testCaseId = "test1";
	/** This is the test variable for Test Case Type */
	private String testType = "Equivalence Class";
	/** This is the test variable for Test Description */
	private String testDescription = "with multiple lines";
	/** This is the test variable for expected results */
	private String expectedResults = "with multiple lines\n" + "- PASS: actual results\n" + "- FAIL: actual results on\n"
			+ "multiple lines";
	/** This is is the test variable that represents actual results*/
	private String actualResults = "- PASS: actual results\n" + "on three\n" + "lines";
	
	
	/**
	 * Tests AbstractTestPlan.AbstractTestPlan()
	 */
	@Test 
	public void testAbstractTestPlan() {
		assertDoesNotThrow(() -> new TestPlan(testPlanName));
		
		Exception e1 = assertThrows(IllegalArgumentException.class, () -> new TestPlan(null));
	    assertEquals("Invalid name.", e1.getMessage());
	}

	/**
	 * Tests AbstractTestPlan.setTestPlanName()
	 */
	@Test
	public void testSetTestPlanName() {
		AbstractTestPlan t1 = new TestPlan(testPlanName);
		t1.setTestPlanName(testPlanName);
		assertEquals(testPlanName, t1.getTestPlanName());
		
		Exception e1 = assertThrows(IllegalArgumentException.class, () -> new TestPlan(""));
	    assertEquals("Invalid name.", e1.getMessage());
	}


	/**
	 * Tests AbstractTestPlan.removeTestCase()
	 */
	@Test
	public void testRemoveTestCase() {
		AbstractTestPlan testPlan = new TestPlan(testPlanName);
		TestCase t = new TestCase(testCaseId, testType, testDescription, expectedResults);
		TestCase t2 = new TestCase("test2", "Boundary Type", testDescription, expectedResults);
		testPlan.addTestCase(t); 
		testPlan.addTestCase(t2);
		testPlan.removeTestCase(0);
		assertEquals(t2, testPlan.getTestCase(0));
		
	}

	/**
	 * Tests AbstractTestPlan.getTestCase()
	 */
	@Test
	public void testGetTestCase() {
		AbstractTestPlan testPlan = new TestPlan(testPlanName);
		TestCase t = new TestCase(testCaseId, testType, testDescription, expectedResults);
		TestCase t2 = new TestCase("test2", "Boundary Type", testDescription, expectedResults);
		testPlan.addTestCase(t);
		testPlan.addTestCase(t2);
		assertEquals(t, testPlan.getTestCase(0));
		assertEquals(t2, testPlan.getTestCase(1));
	}

	/**
	 * Tests AbstractTestPlan.getNumberOfFailingTests()
	 */
	@Test
	public void testGetNumberOfFailingTests() {
		AbstractTestPlan testPlan = new TestPlan(testPlanName);
		TestCase t = new TestCase(testCaseId, testType, testDescription, expectedResults);
		t.addTestResult(true, actualResults);
		testPlan.addTestCase(t);
		TestCase t2 = new TestCase("test2", "Boundary Type", testDescription, expectedResults);
		t2.addTestResult(false, "Index Out of Bounds");
		testPlan.addTestCase(t2);
		assertEquals(1, testPlan.getNumberOfFailingTests());
	}

	/**
	 * Tests AbstractTestPlan.addTestResult()
	 */
	@Test
	public void testAddTestResult() {
		AbstractTestPlan testPlan = new TestPlan(testPlanName);
		TestCase t = new TestCase(testCaseId, testType, testDescription, expectedResults);
		testPlan.addTestCase(t);
		testPlan.addTestResult(0, false, "Index Out of Bounds");
		assertEquals(1, testPlan.getNumberOfFailingTests());
	}

	/**
	 * Tests AbstractTestPlan.equals(Object)
	 */
	@Test
	public void testEqualsObject() {
		AbstractTestPlan testPlan = new TestPlan(testPlanName);
		AbstractTestPlan testPlan2 = new TestPlan(testPlanName2);
		AbstractTestPlan testPlan3 = new TestPlan(testPlanName2);
		
		assertTrue(testPlan2.equals(testPlan3));
		assertFalse(testPlan.equals(testPlan2));
	}
	
	/**
	 * Tests AbstractTestPlan.hashcode
	 */
	@Test
	public void testHashCode() {
		AbstractTestPlan testPlan = new TestPlan(testPlanName);
		AbstractTestPlan testPlan2 = new TestPlan(testPlanName2);
		AbstractTestPlan testPlan3 = new TestPlan(testPlanName2);
		
		assertEquals(testPlan2.hashCode(), testPlan3.hashCode());
		assertNotEquals(testPlan.hashCode(), testPlan2.hashCode());
	}

}
