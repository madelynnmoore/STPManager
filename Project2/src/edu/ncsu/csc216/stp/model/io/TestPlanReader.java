package edu.ncsu.csc216.stp.model.io;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.NoSuchElementException;
import java.util.Scanner;

import edu.ncsu.csc216.stp.model.test_plans.AbstractTestPlan;
import edu.ncsu.csc216.stp.model.test_plans.TestPlan;
import edu.ncsu.csc216.stp.model.tests.TestCase;
import edu.ncsu.csc216.stp.model.util.ISortedList;
import edu.ncsu.csc216.stp.model.util.SortedList;

/**
 * Processes a file containing test plans with zero to many test cases each with zero to many test results.
 * Allows the user to read in a file which then processes the input to create test plans and test cases.
 * @author Maddie Moore, Evans Chege
 *
 */
public class TestPlanReader {
	
	/**
	 * Receives a File with the file to read from. If the file cannot be loaded because it doesn’t exist, the 
	 * method will throw an IllegalArgumentException with the message “Unable to load file.” Any invalid test 
	 * plans or test cases (i.e., they cannot be constructed or information is missing) are ignored.
	 * @param file File to retrieve information from
	 * @return ISortedList of TestPlans containing TestCases
	 * @throws IllegalArgumentException if the file cannot be loaded because it doesn’t exist
	 */
	public static ISortedList<TestPlan> readTestPlansFile(File file) {
		
		ISortedList<TestPlan> testPlans = new SortedList<TestPlan>();
		
		try {
			Scanner fileReader = new Scanner(file);
			Scanner testPlanReader;
			String fileContents = ""; 
			fileReader.useDelimiter("\n");
			while (fileReader.hasNext()) { // reads in all the files to this string object
				fileContents += fileReader.next() + "\n";
			}
//			System.out.print(fileContents);
			
			if (fileContents.charAt(0) != '!') {
				fileReader.close();
				throw new IllegalArgumentException("Unable to load file.");
			}
			
			testPlanReader = new Scanner(fileContents); //scanner that parses through fileContents
			 //Throws IAE if it doesn't start with !
			testPlanReader.useDelimiter("\\r?\\n?[!]"); //delimiter used to parse through TestPlans
			while (testPlanReader.hasNext()) {
				try {
					testPlanReader.useDelimiter("[!]");
					TestPlan testPlan = processTestPlan(testPlanReader.next()); //This line processes Test plans
					testPlans.add(testPlan);
				} catch (IllegalArgumentException e) {
//						e.printStackTrace();
				}
			}
				
			
			fileReader.close();
			testPlanReader.close();

			
		} catch (FileNotFoundException e) {
			throw new IllegalArgumentException("Unable to load file.");
		}
		return testPlans;
	}
	
	/**
	 * Receives a String of information about a TestPlan and creates a TestPlan object to return.
	 * @param nextLine String of information to process into a TestPlan
	 * @return TestPlan object created using nextLine
	 * @throws IllegalArgumentException if the first character of the file is not "!"
	 */
	private static TestPlan processTestPlan(String nextLine) {
		TestPlan plan;
	
		
		try {
			Scanner scan = new Scanner(nextLine);
			scan.useDelimiter("\\r?\\n?[!]"); // Used to break apart Test Plans
			String testPlanName = scan.nextLine().trim(); //This gets the test plan name from the file being read

//			testPlanName = testPlanName.substring(1, testPlanName.indexOf("\r")).trim();
			plan = new TestPlan(testPlanName);	
			Scanner testCaseScanner = new Scanner(nextLine);
			testCaseScanner.useDelimiter("\\r?\\n?[#]"); //Used to break apart Test Case contents
			testCaseScanner.next(); //skips the Test Plan line
			while (testCaseScanner.hasNextLine()) {
				if (testCaseScanner.hasNextLine()) {
					String testCaseContents = testCaseScanner.next();
					processTestCase(plan, testCaseContents);
				}
			}
			scan.close();
			testCaseScanner.close();
		} catch (NoSuchElementException e) {
			throw new IllegalArgumentException();
		}
		
		return plan;
	}
	
	/**
	 * Receives a String of information about a TestCase and creates a TestCase object to return.
	 * @param plan represents the TestPlan in which the testPlans will be added to
	 * @param nextLine String of information to process into a TestCase
	 * @return TestCase object created using nextLine
	 */
	private static TestCase processTestCase(AbstractTestPlan plan, String nextLine) {
		
//		System.out.println(nextLine);
		// These are the objects used to construct a test case
		TestCase caseTest = null;
		String testCaseId;
		String testType;
		String testDescription;
		String expectedResultsUnformatted = null;
		String expectedResults = null;
		boolean isPassing = false;
		String actualResults = null;
		String idANDTypeString;
		String expectedResultsString;
		String booleanCheck;
		
		
		
		Scanner scan = new Scanner(nextLine);
		scan.useDelimiter("\\r?\\n?[#]");
		
		idANDTypeString = scan.nextLine();
		Scanner idnType = new Scanner(idANDTypeString);
		Scanner tokens = new Scanner(nextLine);
		
		idnType.useDelimiter(",");  // Delimiter used to break apart testId and testType
		scan.useDelimiter("\\r?\\n?[*]"); // Delimiter used to break apart testDescription and expectedResults
		tokens.useDelimiter("\\r?\\n?[-]"); //Delimiter used to break apart actualResults
		
		
		tokens.nextLine();
		
		try {
			testCaseId = idnType.next().substring(1).trim();
			
			if (idnType.hasNext()) {
				testType = idnType.next().trim();
				if (idnType.hasNext()) {
					testType = idnType.next().trim();
				}
			} else {
				scan.close();
				idnType.close();
				tokens.close();
				return null;
			}
			
			if (scan.hasNext()) {
				testDescription = scan.next().substring(1).trim();
				if (scan.hasNext()) {
					expectedResultsUnformatted = scan.next().substring(1).trim();
					if (expectedResultsUnformatted.charAt(0) == '-') {
						scan.close();
						idnType.close();
						tokens.close();
						return null;
					}
				} else {
					scan.close();
					idnType.close();
					tokens.close();
					return null;
				}
			} else {
				scan.close();
				idnType.close();
				tokens.close();
				return null;
			}
			
//			System.out.println(expectedResults);

			
			
			Scanner isPassingCheck = new Scanner(expectedResultsUnformatted); //This scan is used to get the pass/fail
			isPassingCheck.useDelimiter("\\r?\\n?[-]"); //This delimiter is used to break apart actual results
			if (isPassingCheck.hasNext()) {
				expectedResults = isPassingCheck.next();
				
				if (testCaseId.length() == 0 || testType.length() == 0 || testDescription.length() == 0 || expectedResults.length() == 0) {
					isPassingCheck.close();
					return null;
				}
				caseTest = new TestCase(testCaseId, testType, testDescription, expectedResults);

				if (isPassingCheck.hasNext()) {
					while (isPassingCheck.hasNext()) {
						try {
							expectedResultsString = isPassingCheck.next();

							if (expectedResultsString.length() > 6 && expectedResultsString.charAt(1) == 'P' || expectedResultsString.charAt(1) == 'F') {
								booleanCheck = expectedResultsString.substring(1, 5).trim();
								actualResults = expectedResultsString.substring(7).trim();
								if (booleanCheck == null || actualResults == null) {
									isPassingCheck.close();
									return null;
								}
								
								if("PASS".equals(booleanCheck)) {
									isPassing = true;
								} else if ("FAIL".equals(booleanCheck)) {
									isPassing = false;
								} else {
									isPassingCheck.close();
									return null;
								}
								
								caseTest.addTestResult(isPassing, actualResults);
								
							} else {
								return null;
							}
						} catch (NoSuchElementException e) {
							return null;
						}
						

					}
					
//					System.out.println("[CASE TEST]" + "\n" +  caseTest + "\n" + "[ACTUAL RESULTS LOG] = " + "\n" + caseTest.getActualResultsLog());
				}
				plan.addTestCase(caseTest);
			} else {
				isPassingCheck.close();
				plan.addTestCase(caseTest);
				return caseTest;
			}
			
			
//			caseTest = new TestCase(testCaseId, testType, testDescription, expectedResults);
			
			
			
			idnType.close();
			tokens.close();
			isPassingCheck.close();
			return caseTest;
			
		} catch (NoSuchElementException e) {
			throw new IllegalArgumentException(e);
		}
	}
}
