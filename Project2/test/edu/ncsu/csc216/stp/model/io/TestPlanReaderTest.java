package edu.ncsu.csc216.stp.model.io;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;

import org.junit.Before;
import org.junit.jupiter.api.Test;

import edu.ncsu.csc216.stp.model.test_plans.TestPlan;
import edu.ncsu.csc216.stp.model.tests.TestCase;
import edu.ncsu.csc216.stp.model.util.ISortedList;

/**
 * Tests the TestPlanReader Class
 *
 * @author Maddie Moore, Evans Chege
 *
 */
class TestPlanReaderTest {
	
	/** This represents the file that the test will be using */
	private static File validTestFile0 = new File ("test-files/test-plans0.txt");
	/** This represents the file that the test will be using */
	private static File validTestFile1 = new File ("test-files/test-plans1.txt");
	/** This represents the file that the test will be using */
	private static File validTestFile2 = new File ("test-files/test-plans2.txt");
	/** This represents the file that the test will be using */
	private static File validTestFile4 = new File ("test-files/test-plans4.txt");
	/** This represents the file that the test will be using */
	private static File validTestFile5 = new File ("test-files/test-plans5.txt");
	/** This represents the file that the test will be using */
	private static File validTestFile6 = new File ("test-files/test-plans6.txt");
	/** This represents the file that the test will be using */
	private static File validTestFile7 = new File ("test-files/test-plans7.txt");	
	/** This represents the file that the test will be using */
	private static File validTestFile8 = new File ("test-files/test-plans8.txt");
	/** This represents the file that the test will be using */
	private static File validTestFile9 = new File ("test-files/test-plans9.txt");
	/** This represents the file that the test will be using */
	private static File invalidTestFile = new File ("test-files/test-plans3.txt");
	
	/**
	 * Resets course_records.txt for use in other tests.
	 * @throws Exception if there is an invalid path
	 * */
	@Before
	public void setUp() throws Exception {
		//Reset project_records.txt so that it's fine for other needed tests
		Path sourcePath = FileSystems.getDefault().getPath("test-files", "test-plans0.txt");
		Path destinationPath = FileSystems.getDefault().getPath("test-files", "test-plans0.txt");
		try {
			Files.deleteIfExists(destinationPath);
			Files.copy(sourcePath, destinationPath);
		} catch (IOException e) {
			fail("Unable to reset files");
		}
	}
	
	/**
	 * Tests TestPlanReader.readTestPlansFile()
	 */
	@Test
	public void testReadTestPlansFileValid() {
	
		ISortedList<TestPlan> testPlans = TestPlanReader.readTestPlansFile(validTestFile0);
		TestPlan t = new TestPlan("WolfScheduler");
		TestCase caseTest = new TestCase ("test1", "Equivalence class", "description\n with multiple lines", "expected results\n with multiple lines");
		TestCase caseTest2 = new TestCase ("test2", "Boundary value", "description", "expected results");
		TestCase caseTest3 = new TestCase ("test3", "Requirements", "description\n on multiple lines", "expected results");
		
		
		caseTest.addTestResult(true, "actual results");
		caseTest.addTestResult(false, "actual results on\n multiple lines");
		caseTest.addTestResult(true, "actual results\n on three");
		
		caseTest3.addTestResult(false, "actual results");
		
		
		t.addTestCase(caseTest);
		t.addTestCase(caseTest2);
		t.addTestCase(caseTest3);
		
		assertEquals(2, testPlans.size());
		assertEquals(3, testPlans.get(1).getTestCases().size());
		assertEquals(2, testPlans.get(0).getTestCases().size());
		assertEquals(t, testPlans.get(1));
		
		
		ISortedList<TestPlan> testPlan1 = TestPlanReader.readTestPlansFile(validTestFile1);
		assertEquals(2, testPlan1.size());
		assertEquals(2, testPlan1.get(0).getTestCases().size());
		assertEquals(3, testPlan1.get(1).getTestCases().size());

		ISortedList<TestPlan> testPlan2 = TestPlanReader.readTestPlansFile(validTestFile2);
		assertEquals(1, testPlan2.size());
		assertEquals(0, testPlan2.get(0).getTestCases().size());
		
		ISortedList<TestPlan> testPlan4 = TestPlanReader.readTestPlansFile(validTestFile4);
		assertEquals(1, testPlan4.size());
		assertEquals(0, testPlan4.get(0).getTestCasesAsArray().length);
		
		ISortedList<TestPlan> testPlan5 = TestPlanReader.readTestPlansFile(validTestFile5);
		assertEquals(1, testPlan5.size());
		assertEquals(0, testPlan5.get(0).getTestCasesAsArray().length);
		
		ISortedList<TestPlan> testPlan6 = TestPlanReader.readTestPlansFile(validTestFile6);
		assertEquals(1, testPlan6.size());
		assertEquals(0, testPlan6.get(0).getTestCasesAsArray().length);
		
		
		ISortedList<TestPlan> testPlan7 = TestPlanReader.readTestPlansFile(validTestFile7);
		assertEquals(1, testPlan7.size());
		assertEquals(0, testPlan7.get(0).getTestCasesAsArray().length);
		
		ISortedList<TestPlan> testPlan8 = TestPlanReader.readTestPlansFile(validTestFile8);
		assertEquals(1, testPlan8.size());
		assertEquals(0, testPlan8.get(0).getTestCasesAsArray().length);
		
		ISortedList<TestPlan> testPlan9 = TestPlanReader.readTestPlansFile(validTestFile9);
		assertEquals(1, testPlan9.size());
		assertEquals(0, testPlan8.get(0).getTestCasesAsArray().length);
		
		
	}
	
	/**
	 * Tests TestPlanReader.readTestPlansFile()
	 */
	@Test
	void testReadTestPlansFileInvalid() {
		Exception e1 = assertThrows(IllegalArgumentException.class, () -> TestPlanReader.readTestPlansFile(invalidTestFile));
	    assertEquals("Unable to load file.", e1.getMessage());
	   
	}
}
