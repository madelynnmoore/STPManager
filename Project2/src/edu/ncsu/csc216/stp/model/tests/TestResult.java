package edu.ncsu.csc216.stp.model.tests;

/**
 * TestResult contains information about the results of a single execution of a 
 * TestCase. The TestResult knows if it is passing or failing and has details about 
 * the actual results from execution.
 * @author Annabella Jackmore, Maddie Moore
 *
 */
public class TestResult {

	/** Constant String for the pass result of a TestCase*/
	public static final String PASS = "PASS";
	/** Constant String for the fail result of a TestCase */
	public static final String FAIL = "FAIL";
	/** The boolean holding the pass or fail result of a TestCase */
	private boolean passing;
	/** Actual results of the TestCase */
	private String actualResults;
	
	/**
	 * Constructs a TestResult object with the given parameters.
	 * @param passing boolean holding pass or fail result of the TestCase
	 * @param actualResults the actual results for the TestCase
	 */
	public TestResult(boolean passing, String actualResults) {
		setActualResults(actualResults);
		setPassing(passing);
	}
	
	/**
	 * Private helper method to check for a valid actualResults string. Checks if actualResults
	 * is null or empty string.
	 * @param actualResults the actual results for the TestCase
	 * @throws IllegalArgumentException if actualResults is null or empty string.
	 */
	private void setActualResults(String actualResults) {
		if ("".equals(actualResults) || actualResults == null) {
			throw new IllegalArgumentException("Invalid test results.");
		}
		this.actualResults = actualResults;
	}
	
	/**
	 * Returns a string representation of the TestResult for printing to a file and listing in the GUI. 
	 * If the test is passing, “PASS” is printed followed by a colon (:), a space, and the actual results.
	 * If the test is failing, “FAIL” is printed followed by a colon (:), a space, and the actual results.
	 * @return s the string representation of the TestResult
	 */
	public String toString() {
		if (passing) {
			return PASS + ": " + actualResults;
		}
		else 
			return FAIL + ": " + actualResults;
	}
	
	/**
	 * Returns the actual results of the TestCase.
	 * @return actualResults the actual results of the TestCase
	 */
	public String getActualResults() {
		return actualResults;
	}
	
	/**
	 * Returns the boolean holding pass or fail result of the TestCase.
	 * @return isPassing the boolean holding pass or fail result of the TestCase
	 */
	public boolean isPassing() {
		return passing;
	}
	
	/**
	 * Sets the boolean holding pass or fail result of the TestCase.
	 * @param passing boolean to set passing variable
	 */
	private void setPassing(boolean passing) {
		this.passing = passing;
	}
}
