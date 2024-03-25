package edu.ncsu.csc216.stp.model.test_plans;

import edu.ncsu.csc216.stp.model.tests.TestCase;

/**
 * Extends AbstractTestPlan to hold test cases that belong to a name project. TestPlan allows the user to 
 * retrieve TestCases as an Array, add TestCases to the TestPlan, and compare TestPlan names. TestPlan extends 
 * AbstractTestPlan and implements the Comparable interface.
 *
 * @author Maddie Moore
 * @author Annabella Jackmore
 *
 */
public class TestPlan extends AbstractTestPlan implements Comparable<TestPlan> {
	
	/**
	 * Constructs the TestPlan with the given name. If the proposed name is the same as 
	 * FailingListTest.FAILING_TEST_LIST_NAME (case insensitive), then throw an IAE with message “Invalid name.”
	 * @param testPlanName name of test plan to set
	 * @throws IllegalArgumentException if testPlanName is the same as a failing test name
	 */
	public TestPlan(String testPlanName) {
		super(testPlanName);
		if(testPlanName.equalsIgnoreCase(FailingTestList.FAILING_TEST_LIST_NAME)) {
			throw new IllegalArgumentException("Invalid name.");
		}
	}
	 
	/**
	 * Returns a 2D String array where the first column is the test case id, the second column is the test type, 
	 * and the third column is the status (“PASS” or “FAIL”).
	 * @return list of TestCases in the form of an Array 
	 */ 
	public String[][] getTestCasesAsArray() {
		String[][] cases = new String[getTestCases().size()][3];
		for(int i = 0; i < cases.length; i++) {
			if(getTestCase(i) != null) {
				cases[i][0] = getTestCase(i).getTestCaseId();
				cases[i][1] = getTestCase(i).getTestType();
				cases[i][2] = getTestCase(i).getStatus();	
			}
		}
		return cases;
	}
	
	/**
	 * Overrides the AbstractTestPlan.addTestCase() method so that it adds the test case via call to super AND 
	 * sets the TestCase’s TestPlan to the current TestPlan
	 * @param t TestCase to add to list
	 */
	@Override
	public void addTestCase(TestCase t) {
		super.addTestCase(t);
		t.setTestPlan(this);
	}

	/**
	 * Compares the names of the TestPlans. This comparison is case insensitive. Used by the SortedList class 
	 * to determine the order that TestPlans should be added. Compares this object with the specified object 
	 * for order. Returns a negative integer, zero, or a positive integer as this object is less than, equal 
	 * to, or greater than the specified object.
	 * @param testPlan TestPlan to compare to another TestPlan
	 * @return a negative integer, zero, or a positive integer as this object is less than, equal 
	 * to, or greater than the specified object.
	 */
	@Override
	public int compareTo(TestPlan testPlan) {
		// Determines longer String length between the TestPlan names
		if (testPlan == null) {
			throw new NullPointerException();
		}
		
		String compareName = this.getTestPlanName().toLowerCase();

		if (this.getClass() != testPlan.getClass()) {
			throw new ClassCastException();
		}

		if (compareName.equalsIgnoreCase(testPlan.getTestPlanName())) {
			return 0;
		}

		int compareTestPlanName = this.getTestPlanName().compareTo(testPlan.getTestPlanName());

		if (compareTestPlanName < 0) {
			return -1;
		} else if (compareTestPlanName > 0) {
			return 1;
		} else {
			return 0;
		}
	}
}
