package edu.ncsu.csc216.stp.model.manager;

import java.io.File;
import edu.ncsu.csc216.stp.model.io.TestPlanReader;
import edu.ncsu.csc216.stp.model.io.TestPlanWriter;
import edu.ncsu.csc216.stp.model.test_plans.AbstractTestPlan;
import edu.ncsu.csc216.stp.model.test_plans.FailingTestList;
import edu.ncsu.csc216.stp.model.test_plans.TestPlan;
import edu.ncsu.csc216.stp.model.tests.TestCase;
import edu.ncsu.csc216.stp.model.util.ISortedList;
import edu.ncsu.csc216.stp.model.util.SortedList;

/**
 * This is the class in charge of managing Test Plan
 * A TestPlanManager has an ISortedList of TestPlans, one FailingTestList, an AbstractTestPlan for the c
 * urrentTestPlan, and a boolean flag that keeps track of if the TestPlanManager has been changed since 
 * the last save.
 * 
 * @author Evans Chege, Maddie Moore, Annabella Jackmore
 *
 */
public class TestPlanManager {
	
	/** This is the variable to check if the Test Plan has changed */
	private boolean isChanged;
	/** This is the variable that represents the current test plan */
	private AbstractTestPlan currentTestPlan;
	/** This is a sorted list of TestPlans */
	private ISortedList<TestPlan> testPlans;
	/** This is the variable that represents the failing tests list */
	private FailingTestList failingTestList;
	
	/**
	 * The testPlans field is constructed as a SortedList and the failingTestList is constructed and is set as 
	 * the currentTestPlan. isChanged is initialized to false. The clearTestPlans() method can be helpful for
	 * completing these tasks.
	 */
	public TestPlanManager() {
		clearTestPlans();
		this.testPlans = new SortedList<TestPlan>();
		setCurrentTestPlan(this.failingTestList.getTestPlanName());
		this.isChanged = false;
	}
	
	/**
	 * The TestPlanReader is used to load the file and return a list of potential TestPlans to add to the list 
	 * of TestPlans. If there is already a TestPlan in the testPlans with the given name, the incoming TestPlan 
	 * is not added (remember SortedList already checks for duplicates!). After loading all the new TestPlans 
	 * (or not), the currentTestPlan is set to the “Failing Tests” list. isChanged should be set to true since 
	 * the state of the system has changed.
	 * @param filename represents the name of the file
	 */
	public void loadTestPlans(File filename) {
		ISortedList<TestPlan> testPlanList = TestPlanReader.readTestPlansFile(filename);
		//check for same names
		for(int i = 0; i < testPlanList.size(); i++) {
			testPlans.add(testPlanList.get(i));
		}
		setCurrentTestPlan(failingTestList.getTestPlanName());
		this.isChanged = true;
	}
	
	/**
	 *  Saves the current TestPlans to the given file. isChanged is updated to false.
	 * @param fileName represents the name of the file
	 */
	public void saveTestPlans(File fileName) {
		TestPlanWriter.writeTestPlanFile(fileName, testPlans);
		this.isChanged = false;
	}
	
	/**
	 * This is the method for checking if the manager has changed
	 * @return true if the manager has changed
	 */
	public boolean isChanged() {
		return isChanged;
	}
	 
	/**
	 * Responsible for adding a test plan to the list of test plans. If the new TestPlan’s name is 
	 * FAILING_TESTS_LIST_NAME or a duplicate of an existing TestPlan, then an IAE is thrown with message 
	 * “Invalid name.”. Otherwise, the TestPlan is added to the list of test plans, the current test plan is 
	 * updated to the new test plan, and isChanged is updated to true.
	 * @param testPlanName represents the TestPlan name 
	 * @throws IllegalArgumentException if the TestPlan name is FAILING_TESTS_LIST_NAME or a duplicate of an 
	 * existing TestPlan
	 */
	public void addTestPlan(String testPlanName) {
		if(testPlanName.equalsIgnoreCase(FailingTestList.FAILING_TEST_LIST_NAME)) {
			throw new IllegalArgumentException("Invalid name.");
		}
		for(int i = 0; i < testPlans.size(); i++) {
			if(testPlanName.equalsIgnoreCase(testPlans.get(i).getTestPlanName())){
				throw new IllegalArgumentException("Invalid name.");
			}
		}
		TestPlan testPlan = new TestPlan(testPlanName);
		testPlans.add(testPlan);
		setCurrentTestPlan(testPlan.getTestPlanName());
		this.isChanged = true;
	}
	
	/**
	 * Returns a list of test plan names. The “Failing Tests” list is always listed first.
	 * @return testPlanNames an Array containing the test plans' names
	 */
	public String[] getTestPlanNames() {
		//implement
		int size = testPlans.size() + 1;
		String[] testPlanNames = new String[size];
		testPlanNames[0] = failingTestList.getTestPlanName();
		for (int i = 1; i < testPlanNames.length - 1; i++) {
			testPlanNames[i] = testPlans.get(i - 1).getTestPlanName();
		}
		return testPlanNames;
	}
	
	/**
	 * A private helper method that is useful for working with the FailingTestList.
	 */
	private void getFailingTests() {
		//implement
	}
	
	/**
	 * Sets the currentTestPlan to the AbstractTestPlan with the given name. If a TestPlan with that name is 
	 * not found, then the currentTestPlan is set to the failingTestList
	 * @param testPlanName represents the test plan bound to be set
	 */
	public void setCurrentTestPlan(String testPlanName) {
		AbstractTestPlan testPlan = null;
		for (int i = 0; i < testPlans.size(); i++) {
			if (testPlans.get(i).getTestPlanName().equals(testPlanName)) {
				testPlan = testPlans.get(i);
			}
		}
		if (testPlan == null) {
			currentTestPlan = failingTestList;
		}
		else {
			this.currentTestPlan = testPlan;
		}
	}
	
	/**
	 * Returns the currentTestPlan field
	 * @return currentTestPlan represents current test plan
	 */
	public AbstractTestPlan getCurrentTestPlan() {
		return currentTestPlan;
	}
	
	/**
	 * Responsible for editing a test plan, given the test plan name. An IAE is thrown if the currentTestPlan 
	 * is an FailingTestList (message “The Failing Tests list may not be edited.”), if the new name matches 
	 * “Failing Tests” (case insensitive) (message “Invalid name.”), or is a duplicate of the name of another 
	 * TestPlan (case insensitive and including if the name is the same as the list that will be renamed) 
	 * (message “Invalid name.”).
	 * @param testPlanName represents the test plan 
	 * @throws IllegalArgumentException if the currentTestPlan is a FailingTestList
	 * @throws IllegalArgumentException if the new name matches "Failing Tests"
	 * @throws IllegalArgumentException if the new name is a duplicate of the name of an existing test plan
	 */
	public void editTestPlan(String testPlanName) {
		if(currentTestPlan == failingTestList) {
			throw new IllegalArgumentException("The Failing Tests list may not be edited.");
		}
		if("Failing Tests".equalsIgnoreCase(testPlanName)){
			throw new IllegalArgumentException("Invalid name.");
		}
		for(int i = 0; i < testPlans.size(); i++) {
			if(testPlanName.equalsIgnoreCase(testPlans.get(i).getTestPlanName())) {
				throw new IllegalArgumentException("Invalid name.");
			}
		}
		currentTestPlan.setTestPlanName(testPlanName);
		this.isChanged = true;
	}
	
	/**
	 * Responsible for removing the current test plan from the list of test plans. An IAE is thrown if the 
	 * currentTestPlan is an FailingTestList with the message “The Failing Tests list may not be deleted.”. 
	 * Otherwise, the currentTestPlan is removed and then set to the failingTestList.
	 * @throws IllegalArgumentException if the currentTestPlan is an FailingTestList
	 */
	public void removeTestPlan() {
		if(currentTestPlan == failingTestList) {
			throw new IllegalArgumentException("The Failing Tests list may not be deleted.");
		}
		for(int i = 0; i < testPlans.size(); i++) {
			if(currentTestPlan.equals(testPlans.get(i))){
				testPlans.remove(i);
				this.currentTestPlan = failingTestList;
				this.isChanged = true;
			}
		}
		
	}
	/**
	 * Responsible for adding a test case to the currentTestPlan. A TestCase can only be added directly to a 
	 * TestPlan. If the currentTestPlan is not a TestPlan do nothing with the TestCase. If the currentTestPlan 
	 * is a TestPlan, then the test case is added and checked if the TestCase is failing. If so, then the 
	 * failingTestList is updated. isChanged is updated to true.
	 * @param t represents the test case
	 */
	public void addTestCase(TestCase t) {
		if (currentTestPlan instanceof TestPlan) {
			currentTestPlan.addTestCase(t);
			getFailingTests();
			if(!t.isTestCasePassing()) {
				failingTestList.addTestCase(t);
			}
			this.isChanged = true;
		}
	}
	/** 
	 * Adds the test result to the test case at the given index in the current test plan. If the tests are 
	 * failing, then the Failing Test List is updated.
	 * @param idx represents the index of the test case
	 * @param passing represents a flag checking to see if the case is passing
	 * @param actualResults represents the results from the test.
	 */
	public void addTestResult(int idx, boolean passing, String actualResults) {
		currentTestPlan.addTestResult(idx, passing, actualResults);
		if(!passing) {
			failingTestList.addTestCase(currentTestPlan.getTestCase(idx));
		}
	}
	
	/**
	 * Clears out the TestPlanManager by setting testPlans to an empty SortedList, failingTestList to an empty 
	 * FailingTestList(), currentTestPlan to the failingTestList, and isChanged to false.
	 */
	public void clearTestPlans() {
		this.testPlans = new SortedList<TestPlan>();
		this.failingTestList = new FailingTestList();
		this.currentTestPlan = failingTestList;
		this.isChanged = false;
	}
}