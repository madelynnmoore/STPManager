package edu.ncsu.csc216.stp.model.manager;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

import org.junit.jupiter.api.Test;

import edu.ncsu.csc216.stp.model.tests.TestCase;



/**
 * Tests the TestPlanManager Class.
 *
 * @author Maddie Moore
 *
 */ 
class TestPlanManagerTest {
	
	/** This represents the file that the test will be using */
	private static File validTestFile = new File ("test-files/test-plans0.txt");
//	/** Represents the file to compare to loaded test file */
//	private static File validWriterFile = new File ("test-files/writer-check.txt");
	/** Represents the file to save and compare to test file */
	private static File outputFile = new File ("test-files/writer-test.txt");
	/** This represents the invalid file that the test will be using */
	private static File invalidTestFile = new File ("test-files/test-plans3.txt");
	/** This is the test variable for TestCaseId */
	private String testCaseId = "test1";
	/** This is the test variable for TestCaseType */
	private String testType = "Equivalence Class";
	/** This is the test variable for  */
	private String testDescription = "description\n" + "with multiple lines";
	/** String that represents the expected results parameter */
	private String expectedResults = "expected results\n" + "with multiple lines";
	/** String that represents the actual results parameter*/
	private String actualResults = "actual results";
	
	
	/**
	 * Tests TestPlanManager.TestPlanManager()
	 */
	@Test
	public void testTestPlanManager() {
		assertDoesNotThrow(() -> new TestPlanManager());
		TestPlanManager manager = new TestPlanManager();
		assertFalse(manager.isChanged());
	}

	/**
	 * Tests TestPlanManager.loadTestPlans()
	 */
	@Test
	public void testLoadTestPlans() {
		TestPlanManager manager = new TestPlanManager();
		manager.loadTestPlans(validTestFile);
		assertEquals(3, manager.getTestPlanNames().length);
		
		manager.clearTestPlans();
		Exception e1 = assertThrows(IllegalArgumentException.class, () -> manager.loadTestPlans(invalidTestFile));
	    assertEquals("Unable to load file.", e1.getMessage());
		
	}

	/**
	 * Tests TestPlanManager.isChanged()
	 */
	@Test
	public void testIsChanged() {
		TestPlanManager manager = new TestPlanManager();
		assertFalse(manager.isChanged());
		
		manager.addTestPlan("System Test Plan");
		assertTrue(manager.isChanged());
	}
	
	/**
	 * Tests TestPlanManager.addTestPlan()
	 */
	@Test
	public void testAddTestPlan() {
		TestPlanManager manager = new TestPlanManager();
		manager.addTestPlan("System Test Plan");
		assertEquals(2, manager.getTestPlanNames().length);
		
		Exception e1 = assertThrows(IllegalArgumentException.class, () -> manager.addTestPlan("System Test Plan"));
	    assertEquals("Invalid name.", e1.getMessage());
	    
	    // Replicating TSTestPlanManagerTest.testAddTestPlan()
	    Exception e2 = assertThrows(IllegalArgumentException.class, () -> manager.addTestPlan("Failing Tests"));
	    assertEquals("Invalid name.", e2.getMessage());
	    
	    manager.addTestPlan("Test Plan");
	    assertEquals("Test Plan", manager.getCurrentTestPlan().getTestPlanName());
	    
	    Exception e3 = assertThrows(IllegalArgumentException.class, () -> manager.addTestPlan("test plan"));
	    assertEquals("Invalid name.", e3.getMessage());
	    
	    manager.addTestPlan("Another Test Plan");
	    
	    assertEquals("Failing Tests", manager.getTestPlanNames()[0]);
	    assertEquals("Another Test Plan", manager.getTestPlanNames()[1]);
	    assertEquals("System Test Plan", manager.getTestPlanNames()[2]);
	}
	
	/**
	 * Tests TestPlanManager.addTestPlan()
	 */
	@Test
	public void testAddTestPlanTSTest() {
		TestPlanManager manager = new TestPlanManager();
	    
	    Exception e1 = assertThrows(IllegalArgumentException.class, () -> manager.addTestPlan("Failing Tests"));
	    assertEquals("Invalid name.", e1.getMessage());
	    
	    
	    manager.addTestPlan("Test Plan");
		assertEquals(2, manager.getTestPlanNames().length);
		
		Exception e2 = assertThrows(IllegalArgumentException.class, () -> manager.addTestPlan("test plan"));
	    assertEquals("Invalid name.", e2.getMessage());
	    
	    manager.addTestPlan("Another Test Plan");
	    String[] testPlans = manager.getTestPlanNames();
	    assertEquals("Another Test Plan", testPlans[1]);
		
	}
	
	/**
	 * Tests TestPlanManager.setCurrentTestPlan()
	 */
	@Test
	public void testSetCurrentTestPlan() {
		TestPlanManager manager = new TestPlanManager();
		manager.addTestPlan("System Test Plan");
		manager.addTestPlan("BBTP");
		
		manager.setCurrentTestPlan("System Test Plan");
		assertEquals("System Test Plan", manager.getCurrentTestPlan().getTestPlanName());
		
		manager.addTestPlan("White Box Testing");
		assertEquals("White Box Testing", manager.getCurrentTestPlan().getTestPlanName());
	}
	
	
	/**
	 * Tests TestPlanManager.editTestPlan()
	 */
	@Test
	public void testEditTestPlan() {
		TestPlanManager manager = new TestPlanManager();
		
		Exception e1 = assertThrows(IllegalArgumentException.class, () -> manager.editTestPlan("System Test Plan"));
	    assertEquals("The Failing Tests list may not be edited.", e1.getMessage());
	    
	    manager.addTestPlan("System Test Plan");
	    Exception e2 = assertThrows(IllegalArgumentException.class, () -> manager.editTestPlan("System Test Plan"));
	    assertEquals("Invalid name.", e2.getMessage());
	    
	    manager.editTestPlan("New STP");
	    assertEquals("New STP", manager.getCurrentTestPlan().getTestPlanName());
	}
	
	/**
	 * Tests TestPlanManager.removeTestPlan()
	 */
	@Test
	public void testRemoveTestPlan() {
		TestPlanManager manager = new TestPlanManager();
		
		Exception e1 = assertThrows(IllegalArgumentException.class, () -> manager.removeTestPlan());
	    assertEquals("The Failing Tests list may not be deleted.", e1.getMessage());
	    
		manager.addTestPlan("System Test Plan");
		manager.addTestPlan("BBTP");
		manager.addTestPlan("New STP");
		assertEquals(4, manager.getTestPlanNames().length);
		
		manager.removeTestPlan();
		assertEquals(3, manager.getTestPlanNames().length);
		assertTrue(manager.isChanged());
	}
	
	/**
	 * Tests TestPlanManager.addTestCase()
	 */
	@Test
	public void testAddTestCase() {
		TestPlanManager manager = new TestPlanManager();
		manager.addTestPlan("WolfScheduler");
		TestCase t = new TestCase(testCaseId, testType, testDescription, expectedResults);
		
		manager.addTestCase(t);
		assertEquals(t, manager.getCurrentTestPlan().getTestCase(0));	
	}
	
	/**
	 * Tests TestPlanManager.addTestResult()
	 */
	@Test
	public void testAddTestResult() {
		TestPlanManager manager = new TestPlanManager();
		manager.addTestPlan("WolfScheduler");
		TestCase t = new TestCase(testCaseId, testType, testDescription, expectedResults);
		manager.addTestCase(t);
		manager.addTestResult(0, true, actualResults);
		assertTrue(manager.getCurrentTestPlan().getTestCase(0).isTestCasePassing());
	}
	
	/**
	 * Tests TestPlanManager.clearTestPlans()
	 */
	@Test
	public void testClearTestPlans() {
		TestPlanManager manager = new TestPlanManager();
		
		manager.addTestPlan("System Test Plan");
		manager.addTestPlan("BBTP");
		manager.addTestPlan("New STP");
		
		assertEquals(4, manager.getTestPlanNames().length);
		assertTrue(manager.isChanged());
		
		manager.clearTestPlans();
		
		assertEquals(1, manager.getTestPlanNames().length);
		assertFalse(manager.isChanged());
		
	}
	
	/**
	 * Tests TestPlanManager.testSaveTestPlans()
	 */
	@Test
	public void testSaveTestPlans() {
		TestPlanManager manager = new TestPlanManager();
		manager.loadTestPlans(validTestFile);

		manager.saveTestPlans(outputFile);
		
		checkFiles(outputFile, outputFile);
		assertFalse(manager.isChanged());
	}
	
	
	/**
	 * Helper method to compare two files for the same contents
	 * @param expFile expected output
	 * @param actFile actual output
	 */
	private void checkFiles(File expFile, File actFile) {
		try (Scanner expScanner = new Scanner(expFile);
			 Scanner actScanner = new Scanner(actFile);) {
			
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
