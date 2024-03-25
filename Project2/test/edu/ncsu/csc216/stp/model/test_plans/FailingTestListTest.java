package edu.ncsu.csc216.stp.model.test_plans;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import edu.ncsu.csc216.stp.model.tests.TestCase;
/**
 * Tests FailingTestList Class
 *
 * @author Maddie Moore, Annabella Jackmore
 *
 */
public class FailingTestListTest {

	/** TestCase ID used for testing */
	public static final String ID = "Invalid File";
	/** TestCase type used for testing */
	public static final String TYPE = "Equivalence Class";
	/** TestCase description used for testing */
	public static final String DESCRIPTION = "1. Run WolfScheduler GUI";
	/** TestCase expected results used for testing */
	public static final String EXPECTED = "WolfSchedulerGUI loads";
	/** This final variable represents the string "Failing Tests*/
	public static final String FAILING_TEST_LIST_NAME = "Failing Tests";
	
	/**
	 * Tests FailingTestList.setTestPlanName()
	 */
	@Test
	public void testSetTestPlanName() {
		FailingTestList f = new FailingTestList();
		assertEquals(FAILING_TEST_LIST_NAME, f.getTestPlanName());
	}


	/**
	 * Tests FailingTestList.getTestCasesAsArray()
	 */
	@Test
	public void testGetTestCasesAsArray() {
		FailingTestList f = new FailingTestList();
		TestPlan plan = new TestPlan("Test");
		TestCase t =  new TestCase(ID, TYPE, DESCRIPTION, EXPECTED);
		t.addTestResult(false, DESCRIPTION);
		plan.addTestCase(t);
		f.addTestCase(plan.getTestCase(0));
		
		
		assertEquals(f.getTestCasesAsArray().length, 1);
		
		
		TestCase t2 =  new TestCase("Test 2", TYPE, DESCRIPTION, EXPECTED);
		t.addTestResult(true, EXPECTED);
		plan.addTestCase(t2);
		f.addTestCase(plan.getTestCase(1));
		
		assertEquals(f.getTestCasesAsArray().length, 1);
	}

	/**
	 * Tests FailingTestList.FailingTestList()
	 */
	@Test
	public void testFailingTestList() {
		FailingTestList f = new FailingTestList();
		TestPlan plan = new TestPlan("Test");
		TestCase t =  new TestCase(ID, TYPE, DESCRIPTION, EXPECTED);
		plan.addTestCase(t);
		
		f.addTestCase(plan.getTestCase(0));
		assertEquals(f.getTestCasesAsArray().length, 1);
	}

	/**
	 * Tests FailingTestList.clearTests()
	 */
	@Test
	public void testClearTests() {
		FailingTestList f = new FailingTestList();
		f.addTestCase(new TestCase(ID, TYPE, DESCRIPTION, EXPECTED));
		assertEquals(1, f.getTestCasesAsArray().length);
		f.clearTests();
		assertEquals(f.getTestCasesAsArray().length, 0);
	}
	
	/**
	 * Tests FailingTestList.addTestCase()
	 */
	@Test
	public void testAddTestCase(){
		FailingTestList f = new FailingTestList();
		TestCase t = new TestCase(ID, TYPE, DESCRIPTION, EXPECTED);
		f.addTestCase(t);
		assertEquals(t, f.getTestCase(0));
		assertNotNull(f.getTestPlanName());
	}

}
