package edu.ncsu.csc216.stp.model.io;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

import org.junit.jupiter.api.Test;

import edu.ncsu.csc216.stp.model.test_plans.TestPlan;
import edu.ncsu.csc216.stp.model.tests.TestCase;
import edu.ncsu.csc216.stp.model.util.ISortedList;
import edu.ncsu.csc216.stp.model.util.SortedList;

/**
 * Tests the TestPlanWriter Class
 *
 * @author Maddie Moore
 * @author Annabella Jackmore
 *
 */
class TestPlanWriterTest {
	
	/** This represents the file that the test will be using */
	private static File testFile = new File ("test-files/writer-test.txt");
	/** This is the test variable for TestCaseId */
	private String testCaseId = "test1";
	/** This is the test variable for TestCaseType */
	private String testType = "Equivalence Class";
	/** String that represents the test description parameter */
	private String testDescription = "description\n" + "with multiple lines";
	/** String that represents the expected results parameter */
	private String expectedResults = "expected results\n" + "with multiple lines";
	
	/**
	 * Tests TestPlanWriter.writeTestPlanFile()
	 */
	@Test
	void testWriteTestPlanFile() {
		ISortedList<TestPlan> plans = new SortedList<TestPlan>();
		TestPlan t = new TestPlan("WolfScheduler");
		TestCase c = new TestCase(testCaseId, testType, testDescription, expectedResults);
		t.addTestCase(c);
		plans.add(t);
		assertDoesNotThrow(() -> TestPlanWriter.writeTestPlanFile(testFile, plans));
		
		checkFiles("test-files/writer-check.txt", testFile);
	}
	
	/**
	 * Helper method to compare two files for the same contents
	 * @param expFile expected output
	 * @param testFile file to check
	 */
	private void checkFiles(String expFile, File testFile) {
		try (Scanner expScanner = new Scanner(new File(expFile));
			 Scanner actScanner = new Scanner(testFile);) {
			
			while (expScanner.hasNextLine()) {
				assertEquals(expScanner.nextLine(), actScanner.nextLine());
			}
			
			expScanner.close();
			actScanner.close();
		} catch (IOException e) {
			fail("Error reading files.");
		}
	}

}
