package edu.ncsu.csc216.stp.model.test_plans;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import edu.ncsu.csc216.stp.model.tests.TestCase;

/**
 * Tests the TestPlan Class.
 *
 * @author Maddie Moore
 * @author Annabella Jackmore
 *
 */
public class TestPlanTest {
	
	/** TestCase ID used for testing */
	public static final String ID = "Invalid File";
	/** TestCase type used for testing */
	public static final String TYPE = "Equivalence Class";
	/** TestCase description used for testing */
	public static final String DESCRIPTION = "1. Run WolfScheduler GUI";
	/** TestCase expected results used for testing */
	public static final String EXPECTED = "WolfSchedulerGUI loads";
	
	/**
	 * Tests TestPlan.addTestCase()
	 */
	@Test
	public void testAddTestCase() {
		TestPlan t = new TestPlan("WolfScheduler");
		TestCase c = new TestCase(ID, TYPE, DESCRIPTION, EXPECTED);
		t.addTestCase(c);
		assertEquals(c, t.getTestCase(0));
	}

	/**
	 * Tests TestPlan.getTestCasesAsArray()
	 */
	@Test
	public void testGetTestCasesAsArray() {
		TestPlan t = new TestPlan("WolfScheduler");
		TestCase c = new TestCase(ID, TYPE, DESCRIPTION, EXPECTED);
		t.addTestCase(c);
		assertEquals(ID, t.getTestCasesAsArray()[0][0]);
	}
	
	/**
	 * Tests TestPlan.TestPlan()
	 */
	@Test
	public void testTestPlan() {
		TestPlan t = new TestPlan("WolfScheduler");
		assertEquals("WolfScheduler", t.getTestPlanName());
	}

	/**
	 * Tests TestPlan.compareTo()
	 */
	@Test
	public void testCompareTo() {
		// Creates 3 TestPlan objects
		TestPlan t1 = new TestPlan("WolfScheduler");
		TestPlan t2 = new TestPlan("PackScheduler");
		TestPlan t3 = new TestPlan("Project1");

		// Checks multiple cases of compareTo()
		assertEquals(1, t1.compareTo(t2));
		assertEquals(-1, t2.compareTo(t1));
		assertEquals(1, t1.compareTo(t3));
		assertEquals(-1, t3.compareTo(t1));
		assertEquals(-1, t2.compareTo(t3));
		assertEquals(1, t3.compareTo(t2));
				
		//Replicating TSTestPlanTest.testCompareTo()
		TestPlan tp1 = new TestPlan("PackScheduler");
		TestPlan tp2 = new TestPlan("WolfScheduler");
		TestPlan tp3 = new TestPlan("packscheduler");
		assertEquals(-1, tp1.compareTo(tp2));
		assertEquals(1, tp2.compareTo(tp1));
		assertEquals(0, tp1.compareTo(tp3));
	}
	
	
	/**
	 * Tests TestPlan.hashCode()
	 */
	@Test
	public void testHashCode() {
		TestPlan t1 = new TestPlan("WolfScheduler");
		TestPlan t2 = new TestPlan("WolfScheduler");
		
		assertEquals(t1.hashCode(), t2.hashCode());
		assertEquals(t2.hashCode(), t2.hashCode());
		assertEquals(t1.hashCode(), t1.hashCode());
		assertEquals(t2.hashCode(), t1.hashCode());
	}
	
	
	/**
	 * Tests TestPlan.equals() 
	 */
	@Test
	public void testEquals() {
		TestPlan t1 = new TestPlan("WolfScheduler");
		TestPlan t2 = new TestPlan("WolfScheduler");
		
		assertTrue(t1.equals(t2));
		assertTrue(t2.equals(t1));
		assertTrue(t2.equals(t2));
		assertTrue(t1.equals(t1));
	}
}
