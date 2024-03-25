package edu.ncsu.csc216.stp.model.tests;

import edu.ncsu.csc216.stp.model.test_plans.TestPlan;
import edu.ncsu.csc216.stp.model.util.ILog;
import edu.ncsu.csc216.stp.model.util.Log;

/**
 * TestCase represents a test case Object that can be part of a test plan. Contains 
 * the information about each individual test case including the testCaseId, testType, 
 * testDescription, expectedResults, and ILog of TestResults. The TestCase additionally 
 * contains a TestPlan that the TestCase belongs to.
 * @author Annabella Jackmore
 *
 */
public class TestCase {
	
	/** The TestCase's ID*/
	private String testCaseId;
	/** The TestCase's type*/
	private String testType;
	/** The TestCase's description*/
	private String testDescription;
	/** The TestCase's expected results*/
	private String expectedResults;
	/** The TestCase's test plan*/
	private TestPlan testPlan;
	/** This creates a log of test results */
	private ILog<TestResult> testResults;
	/**
	 * Constructs a TestCase object using the given parameters. The testResults field is 
	 * constructed to an empty Log of TestResults. The testPlan is set to null. Checks if each 
	 * parameter is null or an empty string.
	 * @param testCaseId the TestCase's ID
	 * @param testType the TestCase's type
	 * @param testDescription the TestCase's description
	 * @param expectedResults the TestCase's expected results
	 */ 
	public TestCase(String testCaseId, String testType, String testDescription, String expectedResults) {
		setTestCaseId(testCaseId);
		setTestType(testType);
		setTestDescription(testDescription);
		setExpectedResults(expectedResults);
		this.testResults = new Log<TestResult>();
		this.testPlan = null;
		
	}
	
	/**
	 * Returns the the TestCase's ID.
	 * @return testCaseID the TestCase's ID
	 */
	public String getTestCaseId() {
		return testCaseId;
	}

	/**
	 * Sets the TestCase's ID. Checks if the id is null or an empty String.
	 * @param testCaseId the TestCase's ID to set.
	 * @throws IllegalArgumentException if testCaseId is null or an empty String.
	 */
	private void setTestCaseId(String testCaseId) {
		if (testCaseId == null || "".equals(testCaseId)) {
			throw new IllegalArgumentException("Invalid test information.");
		}
		this.testCaseId = testCaseId;
	}
	
	/**
	 * Returns the the TestCase's type.
	 * @return testType the TestCase's type
	 */
	public String getTestType() {
		return testType;
	}
	
	/**
	 * Sets the TestCase's type. Checks if the type is null or an empty String.
	 * @param testType the TestCase's type to set.
	 * @throws IllegalArgumentException if testType is null or an empty String.
	 */
	private void setTestType(String testType) {
		if (testType == null || "".equals(testType)) {
			throw new IllegalArgumentException("Invalid test information.");
		}
		this.testType = testType;
	}
	
	/**
	 * Returns the the TestCase's description.
	 * @return testDescription the TestCase's description
	 */
	public String getTestDescription() {
		return testDescription;
	}
	
	/**
	 * Sets the TestCase's description. Checks if the description is null or an empty String.
	 * @param testDescription the TestCase's description to set.
	 * @throws IllegalArgumentException if testDescription is null or an empty String.
	 */
	private void setTestDescription(String testDescription) {
		if (testDescription == null || "".contains(testDescription)) {
			throw new IllegalArgumentException("Invalid test information.");
		}
		this.testDescription = testDescription;
	}
	
	/**
	 * Returns the the TestCase's expected results.
	 * @return expectedResults the TestCase's expected results
	 */
	public String getExpectedResults() {
		return expectedResults;
	}
	
	/**
	 * Sets the TestCase's expected results. Checks if the expected results are null or an empty String.
	 * @param expectedResults the TestCase's expected results to set.
	 * @throws IllegalArgumentException if expectedResults are null or an empty String.
	 */
	private void setExpectedResults(String expectedResults) {
		if (expectedResults == null || "".equals(expectedResults)) {
			throw new IllegalArgumentException("Invalid test information.");
		}
		this.expectedResults = expectedResults;
	}
	
	/**
	 * Creates a TestResult from the given values and adds the TestResult to the end of the testResults log.
	 * Checks if TestResult can be constructed.
	 * @param isPassing the flag for checking if a test is passing
	 * @param actualResults represents a string with the test results
	 * @throws IllegalArgumentException if TestResult cannot be constructed
	 */
	public void addTestResult(boolean isPassing, String actualResults) {
		TestResult result = new TestResult(isPassing, actualResults);
		testResults.add(result);
	}
	
	/**
	 * Returns true if the last TestResult in the Log is passing. If there are no TestResults 
	 * in the Log the test is considered failing since it has not yet run and false is returned.
	 * @return true if latest TestResult in the Log is passing
	 */
	public boolean isTestCasePassing() {
		TestResult result = null;
		if (testResults.size() == 0) {
			return false;
		}
		for(int i = testResults.size() - 1; i >= 0; i--) {
			if(testResults.get(i) != null) {
				result = testResults.get(i);
				break;
			}
		}
		if(result == null) {
			return false;
		} else 
			return result.isPassing();
	}
	
	/**
	 * Returns the the TestCase's status as "PASS" or "FAIL".
	 * @return status the TestCase's status
	 */
	public String getStatus() {
		String status = null;
		
		if (isTestCasePassing()) {
			status = TestResult.PASS;
		} else {
			status = TestResult.FAIL;
		}
		
		return status;
	}
	
	/**
	 * Returns a String representation of the testResults Log. A leading 
	 * “- “ (a dash character followed by a space character) is added to the start of each 
	 * TestResult and a “\n” is added to the end. UseS TestResult.toString() to generate 
	 * the test result information and build up the output.
	 * @return actualResults the String representation of the testResults Log
	 */
	public String getActualResultsLog() {
		String actualResults = "";
		for (int i = 0; i < testResults.size(); i++) {
			if(testResults.get(i) != null) {
				actualResults += "- " + testResults.get(i).toString() + "\n";
			} 
		}
		return actualResults;
	}
	
	/**
	 * Sets the TestCase's TestPlan. Checks if the TestPlan is null.
	 * @param testPlan the TestCase's TestPlan to set.
	 * @throws IllegalArgumentException if testPlan is null.
	 */
	public void setTestPlan(TestPlan testPlan) {
		if (testPlan == null) {
			throw new IllegalArgumentException("Invalid test plan.");
		}
		this.testPlan = testPlan;
	}
	
	/**
	 * Returns the the TestCase's TestPlan.
	 * @return testPlan the TestCase's TestPlan
	 */
	public TestPlan getTestPlan() {
		return testPlan;
	}
	
	/**
	 * Returns a string representation of the TestCase for printing to a file. Uses getActualResultsLog() 
	 * to help build this output.
	 * @return s the string representation of the TestCase
	 */
	public String toString() {
		String output = null;
		output = "# " + getTestCaseId() + "," + getTestType() + "\n" + "* " + getTestDescription() + "\n" + "* " + getExpectedResults() + "\n" 
				+ getActualResultsLog();
		
		return output;
	}
}
