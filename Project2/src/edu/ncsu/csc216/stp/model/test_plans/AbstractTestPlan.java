package edu.ncsu.csc216.stp.model.test_plans;

import edu.ncsu.csc216.stp.model.tests.TestCase;
import edu.ncsu.csc216.stp.model.util.ISwapList;
import edu.ncsu.csc216.stp.model.util.SwapList;
/**

 * The AbstractTestPlan class is an abstract class at the top of the hierarchy for test plans. 
 * The AbstractTestPlan knows its testPlanName and the ISwapList of TestCases.
 * The AbstractTestPlan allows its users to create a TestPlan and retrieve the name of the TestPlan. In addition
 * to that, the AbstractTestPlan can add, remove, and return the TestCases as a String Array or TestCase object,
 * as well as being able to add test results. 
 * 
 * @author Maddie Moore
 * @author Evans Chege
 * @author Annabella Jackmore
 *
 */
public abstract class AbstractTestPlan {
	
	/** Name of the test plan */
	private String testPlanName;
	/** SwapList of TestCases contained within the test plan */
	private SwapList<TestCase> testCases;  
	
	/**
	 * Sets the fields from the parameters and constructs a SwapList for the TestCases. An IAE is thrown with 
	 * the message “Invalid name.” if the testPlanName is null or empty string.
	 * @param testPlanName name to set test plan
	 * @throws IllegalArgumentException if testPlanName is null or empty
	 */
	public AbstractTestPlan(String testPlanName) {
		super();
		this.testCases = new SwapList<TestCase>();
		setTestPlanName(testPlanName);
	}
	
	/**
	 * Sets the test plan name. An IAE is thrown with the message “Invalid name.” if the testPlanName is null 
	 * or empty string. This method is public because it can be used to edit the test plan.
	 * @param testPlanName name to set test plan
	 * @throws IllegalArgumentException if testPlanName is null or empty
	 */
	public void setTestPlanName(String testPlanName) {
		if (testPlanName == null || "".equals(testPlanName)) {
			throw new IllegalArgumentException("Invalid name.");
		}
		if (this instanceof FailingTestList && !testPlanName.equalsIgnoreCase(FailingTestList.FAILING_TEST_LIST_NAME)) {
			throw new IllegalArgumentException("Failing Test ");
		}
		this.testPlanName = testPlanName;
	}
	
	/**
	 * Returns the name of the test plan
	 * @return name of the test plan
	 */
	public String getTestPlanName() {
		return testPlanName;
	}
	
	/**
	 * Returns a SwapList of the test cases in the test plan
	 * @return SwapList of the test cases in the test plan
	 */
	public ISwapList<TestCase> getTestCases() {
		return testCases;
	}
	
	/**
	 * Adds the TestCase to the end of the list.
	 * @param testCase testCase to add
	 * @throws NullPointerException if testCase is null
	 * @throws IllegalArgumentException if the testCase cannot be added
	 */
	public void addTestCase(TestCase testCase) {
		testCases.add(testCase);
	}
	
	/**
	 * Removes the TestCase from the list of test cases and returns the removed test case.
	 * @param idx index of testCase to remove
	 * @return element at given index
	 * @throws IndexOutOfBoundsException if the idx is out of bounds for the list
	 */
	public TestCase removeTestCase(int idx) {
		TestCase t = testCases.remove(idx);
		return t;
	}
	
	/**
	 * Returns the testCase at the given index.
	 * @param idx index of the testCase to retrieve
	 * @return testCase at the given index
	 * @throws IndexOutOfBoundsException if the idx is out of bounds for the list
	 */
	public TestCase getTestCase(int idx) {
		return testCases.get(idx);
	}
	 
	/**
	 * Counts the number of TestCases that are failing. 
	 * @return the number of failing test cases
	 */
	public int getNumberOfFailingTests() {
		int numberOfFailingTests = 0;
		for (int i = 0; i < testCases.size(); i++) {
			if (testCases.get(i) != null && !(testCases.get(i).isTestCasePassing())) {
				numberOfFailingTests++;
			}
			else {
				continue;
			}
		}
		return numberOfFailingTests;
	}
	
	/**
	 * Sends the test result parameters to the TestCase at the given index. 
	 * @param idx index represents the index to insert test result
	 * @param isPassing represents the flag determining if the test is passing or not
	 * @param actualResults represents the actual results from the test
	 */
	public void addTestResult(int idx, boolean isPassing, String actualResults) {
		TestCase t = testCases.get(idx);
		t.addTestResult(isPassing, actualResults);
	}
	
	/**
	 * An abstract method that returns a 2D String array of test cases
	 * @return a 2D String array of test cases
	 */
	public abstract String[][] getTestCasesAsArray();

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((testPlanName == null) ? 0 : testPlanName.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!(obj instanceof AbstractTestPlan))
			return false;
		AbstractTestPlan other = (AbstractTestPlan) obj;
		if (testPlanName == null) {
			if (other.testPlanName != null)
				return false;
		} else if (!testPlanName.equalsIgnoreCase(other.testPlanName))
			return false;
		return true;
	}
}
