package edu.ncsu.csc216.stp.model.test_plans;

import edu.ncsu.csc216.stp.model.tests.TestCase;
/**
 * The FailingTestList extends AbstractTestPlan to hold failing test cases for all projects currently available 
 * in the system.
 * The FailingTestList knows its expected list name, "Failing Tests."
 * The FailingTestList allows its user to add, clear, and return test cases as an array.
 * @author Evans Chege, Maddie Moore, Annabella Jackmore
 *
 */
public class FailingTestList extends AbstractTestPlan {
	/** This final variable represents the string "Failing Tests*/
	public static final String FAILING_TEST_LIST_NAME = "Failing Tests";
	
	/**
	 * Constructs the FailingTestList with the expected name.
	 */
	public FailingTestList() {
		super(FAILING_TEST_LIST_NAME);
		setTestPlanName(FAILING_TEST_LIST_NAME);
	}

	
	/**
	 * Overrides the method to check that the TestCase is failing before adding to the end of the ISwapList. If 
	 * the TestCase is not failing, the test is not added to the list and an IAE is thrown with the message 
	 * “Cannot add passing test case.”.
	 * @param t represents the test case to be added.
	 * @throws IllegalArgumentException if the TestCase to be added is not failing
	 */
	@Override
	public void addTestCase(TestCase t) {
		if (!(t.isTestCasePassing())) {
			super.addTestCase(t);
		} else {
			throw new IllegalArgumentException("Cannot add passing test case.");
		}
	}
	
	/**
	 * Overrides the method to ensure that the parameter value matches the expected name (case insensitive). If 
	 * so, the name is set to the constant value. If not, an IAE is thrown with the message “The Failing Tests 
	 * list cannot be edited.”
	 * @param testPlanName represents the test plan name
	 * @throws IllegalArgumentException if testPlanName does not match the expected name for the list
	 */
	@Override
	public void setTestPlanName(String testPlanName) {
		if(!(testPlanName.equals(FAILING_TEST_LIST_NAME))) {
			throw new IllegalArgumentException("The Failing Tests list cannot be edited.");
		} else {
			super.setTestPlanName(testPlanName);
		}

		
	}

	/**
	 * Returns a 2D String array where the first column is the test case id, the second column is the test type,
	 * and the third column is the test plan name associated with the TestCase. If the test plan is null, then 
	 * use empty string for the test plan name.
	 * @return 2D String array of TestCases
	 */
	@Override
	public String[][] getTestCasesAsArray() {
		int size = super.getNumberOfFailingTests();
		String[][] testCases = new String [size][3];
		for (int i = 0; i < size; i++) {
			testCases[i][0] = getTestCase(i).getTestCaseId();
			testCases[i][1] = getTestCase(i).getTestType();
			if (getTestCase(i).getTestPlan() != null) {
				String planName = getTestCase(i).getTestPlan().getTestPlanName();
				testCases[i][2] = planName;
			} else {
				testCases[i][2] = "";
			} 
			
		}
		return testCases;
	}
	
	/**
	 * Clears the FailingTestList of all TestCases.
	 */
	public void clearTests() {
		for (int i = getTestCases().size() - 1; i >= 0; i--) {
			super.removeTestCase(i);
		}
	}

}
