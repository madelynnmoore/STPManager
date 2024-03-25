package edu.ncsu.csc216.stp.model.util;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import edu.ncsu.csc216.stp.model.tests.TestCase;

/**
 * This is the test class for SwapList
 * @author Evans Chege
 *
 */
public class SwapListTest {
	
	/** Field that holds value for constant test id */
	public static final String TEST_ID = "testid";
	/** Field that holds value for constant test id */
	public static final String TYPE = "Equivalence Class";
	/** Field that holds value for constant test id */
	public static final String DESCRIPTION = "description";
	/** Field that holds value for constant test id */
	public static final String EXPECTED = "expected results";

	/**
	 * This is the test method for SwapList constructor
	 */
	@Test
	public void testSwapList() {
		assertDoesNotThrow(() -> new SwapList<TestCase>());
		SwapList<TestCase> swapList = new SwapList<TestCase>();
		assertEquals(0, swapList.size());
	}

	/**
	 * This is the test method for SwapList.add()
	 */
	@Test
	public void testAdd() {
		SwapList<TestCase> swapList = new SwapList<TestCase>();
		assertEquals(0, swapList.size());
		swapList.add(new TestCase(TEST_ID, TYPE, DESCRIPTION, EXPECTED));
		assertEquals(1, swapList.size());
		swapList.add(new TestCase(TEST_ID, TYPE, DESCRIPTION, EXPECTED));
		assertEquals(2, swapList.size());
		swapList.add(new TestCase(TEST_ID, TYPE, DESCRIPTION, EXPECTED));
		assertEquals(3, swapList.size());
		swapList.add(new TestCase(TEST_ID, TYPE, DESCRIPTION, EXPECTED));
		assertEquals(4, swapList.size());
		swapList.add(new TestCase(TEST_ID, TYPE, DESCRIPTION, EXPECTED));
		assertEquals(5, swapList.size());
		swapList.add(new TestCase(TEST_ID, TYPE, DESCRIPTION, EXPECTED));
		assertEquals(6, swapList.size());
		swapList.add(new TestCase(TEST_ID, TYPE, DESCRIPTION, EXPECTED));
		assertEquals(7, swapList.size());
		swapList.add(new TestCase(TEST_ID, TYPE, DESCRIPTION, EXPECTED));
		assertEquals(8, swapList.size());
		swapList.add(new TestCase(TEST_ID, TYPE, DESCRIPTION, EXPECTED));
		assertEquals(9, swapList.size());
		swapList.add(new TestCase(TEST_ID, TYPE, DESCRIPTION, EXPECTED));
		assertEquals(10, swapList.size());
		swapList.add(new TestCase(TEST_ID, TYPE, DESCRIPTION, EXPECTED));
		assertEquals(11, swapList.size());
	}

	/**
	 * This is the test method for SwapList.remove()
	 */
	@Test
	public void testRemove() {
		SwapList<TestCase> swapList = new SwapList<TestCase>();
		assertEquals(0, swapList.size());
		swapList.add(new TestCase(TEST_ID, TYPE, DESCRIPTION, EXPECTED));
		assertEquals(1, swapList.size());
		swapList.add(new TestCase(TEST_ID, TYPE, DESCRIPTION, EXPECTED));
		assertEquals(2, swapList.size());
		swapList.add(new TestCase(TEST_ID, TYPE, DESCRIPTION, EXPECTED));
		assertEquals(3, swapList.size());
		swapList.add(new TestCase(TEST_ID, TYPE, DESCRIPTION, EXPECTED));
		assertEquals(4, swapList.size());
		swapList.add(new TestCase(TEST_ID, TYPE, DESCRIPTION, EXPECTED));
		assertEquals(5, swapList.size());
		swapList.add(new TestCase(TEST_ID, TYPE, DESCRIPTION, EXPECTED));
		assertEquals(6, swapList.size());
		swapList.add(new TestCase(TEST_ID, TYPE, DESCRIPTION, EXPECTED));
		assertEquals(7, swapList.size());
		swapList.add(new TestCase(TEST_ID, TYPE, DESCRIPTION, EXPECTED));
		assertEquals(8, swapList.size());
		swapList.add(new TestCase(TEST_ID, TYPE, DESCRIPTION, EXPECTED));
		assertEquals(9, swapList.size());
		swapList.add(new TestCase(TEST_ID, TYPE, DESCRIPTION, EXPECTED));
		assertEquals(10, swapList.size());
		swapList.add(new TestCase("testRemove", TYPE, DESCRIPTION, EXPECTED));
		assertEquals(11, swapList.size());
		assertEquals("testRemove", swapList.remove(10).getTestCaseId());
		assertEquals(10, swapList.size());
	}

	/**
	 * This is the test method for SwapList.moveUp()
	 */
	@Test
	public void testMoveUp() {
		SwapList<TestCase> swapList = new SwapList<TestCase>();
		
		swapList.add(new TestCase("test1", TYPE, DESCRIPTION, EXPECTED));
		swapList.add(new TestCase("test2", TYPE, DESCRIPTION, EXPECTED));
		swapList.add(new TestCase("test3", TYPE, DESCRIPTION, EXPECTED));
		
		assertEquals("test1", swapList.get(0).getTestCaseId());
		assertEquals("test2", swapList.get(1).getTestCaseId());
		assertEquals("test3", swapList.get(2).getTestCaseId());
		
		swapList.moveUp(1);
		
		assertEquals("test2", swapList.get(0).getTestCaseId());
		assertEquals("test1", swapList.get(1).getTestCaseId());
		assertEquals("test3", swapList.get(2).getTestCaseId());
		
		swapList.moveUp(2);
		
		assertEquals("test2", swapList.get(0).getTestCaseId());
		assertEquals("test3", swapList.get(1).getTestCaseId());
		assertEquals("test1", swapList.get(2).getTestCaseId());
	}

	/**
	 * This is the test method for SwapList.moveDown()
	 */
	@Test
	public void testMoveDown() {
		SwapList<TestCase> swapList = new SwapList<TestCase>();
		
		swapList.add(new TestCase("test1", TYPE, DESCRIPTION, EXPECTED));
		swapList.add(new TestCase("test2", TYPE, DESCRIPTION, EXPECTED));
		swapList.add(new TestCase("test3", TYPE, DESCRIPTION, EXPECTED));
		
		assertEquals("test1", swapList.get(0).getTestCaseId());
		assertEquals("test2", swapList.get(1).getTestCaseId());
		assertEquals("test3", swapList.get(2).getTestCaseId());
		
		swapList.moveDown(2);
		
		assertEquals("test1", swapList.get(0).getTestCaseId());
		assertEquals("test2", swapList.get(1).getTestCaseId());
		assertEquals("test3", swapList.get(2).getTestCaseId());
		
		swapList.moveDown(1);
		
		assertEquals("test1", swapList.get(0).getTestCaseId());
		assertEquals("test3", swapList.get(1).getTestCaseId());
		assertEquals("test2", swapList.get(2).getTestCaseId());
		
		swapList.moveDown(0);
		
		assertEquals("test3", swapList.get(0).getTestCaseId());
		assertEquals("test1", swapList.get(1).getTestCaseId());
		assertEquals("test2", swapList.get(2).getTestCaseId());
	}

	/**
	 * This is the test method for SwapList.moveToFront()
	 */
	@Test
	public void testMoveToFront() {
		SwapList<TestCase> swapList = new SwapList<TestCase>();
		
		swapList.add(new TestCase("test1", TYPE, DESCRIPTION, EXPECTED));
		swapList.add(new TestCase("test2", TYPE, DESCRIPTION, EXPECTED));
		swapList.add(new TestCase("test3", TYPE, DESCRIPTION, EXPECTED));
		
		assertEquals("test1", swapList.get(0).getTestCaseId());
		assertEquals("test2", swapList.get(1).getTestCaseId());
		assertEquals("test3", swapList.get(2).getTestCaseId());
		
		swapList.moveToFront(2);
		
		assertEquals("test3", swapList.get(0).getTestCaseId());
		assertEquals("test1", swapList.get(1).getTestCaseId());
		assertEquals("test2", swapList.get(2).getTestCaseId());
		
		swapList.moveToFront(2);
		
		assertEquals("test2", swapList.get(0).getTestCaseId());
		assertEquals("test3", swapList.get(1).getTestCaseId());
		assertEquals("test1", swapList.get(2).getTestCaseId());
		
		swapList.moveToFront(2);
		
		assertEquals("test1", swapList.get(0).getTestCaseId());
		assertEquals("test2", swapList.get(1).getTestCaseId());
		assertEquals("test3", swapList.get(2).getTestCaseId());
	}

	/**
	 * This is the test method for SwapList.moveToBack();
	 */
	@Test
	public void testMoveToBack() {
		SwapList<TestCase> swapList = new SwapList<TestCase>();
		
		swapList.add(new TestCase("test1", TYPE, DESCRIPTION, EXPECTED));
		swapList.add(new TestCase("test2", TYPE, DESCRIPTION, EXPECTED));
		swapList.add(new TestCase("test3", TYPE, DESCRIPTION, EXPECTED));
		
		assertEquals("test1", swapList.get(0).getTestCaseId());
		assertEquals("test2", swapList.get(1).getTestCaseId());
		assertEquals("test3", swapList.get(2).getTestCaseId());
		
		swapList.moveToBack(0);
		
		assertEquals("test2", swapList.get(0).getTestCaseId());
		assertEquals("test3", swapList.get(1).getTestCaseId());
		assertEquals("test1", swapList.get(2).getTestCaseId());
		
		swapList.moveToBack(0);
		
		assertEquals("test3", swapList.get(0).getTestCaseId());
		assertEquals("test1", swapList.get(1).getTestCaseId());
		assertEquals("test2", swapList.get(2).getTestCaseId());
		
		swapList.moveToBack(0);
		
		assertEquals("test1", swapList.get(0).getTestCaseId());
		assertEquals("test2", swapList.get(1).getTestCaseId());
		assertEquals("test3", swapList.get(2).getTestCaseId());
	}

	/**
	 * This is the test method for SwapList.get()
	 */
	@Test
	public void testGet() {
		SwapList<TestCase> swapList = new SwapList<TestCase>();
		
		swapList.add(new TestCase("test1", TYPE, DESCRIPTION, EXPECTED));
		swapList.add(new TestCase("test2", TYPE, DESCRIPTION, EXPECTED));
		swapList.add(new TestCase("test3", TYPE, DESCRIPTION, EXPECTED));
		
		assertEquals("test1", swapList.get(0).getTestCaseId());
		assertEquals("test2", swapList.get(1).getTestCaseId());
		assertEquals("test3", swapList.get(2).getTestCaseId());

	}

	/**
	 * This is the test method for SwapList.size()
	 */
	@Test
	public void testSize() {
		SwapList<TestCase> swapList = new SwapList<TestCase>();
		
		assertEquals(0, swapList.size());
		
		swapList.add(new TestCase("test1", TYPE, DESCRIPTION, EXPECTED));
		swapList.add(new TestCase("test2", TYPE, DESCRIPTION, EXPECTED));
		swapList.add(new TestCase("test3", TYPE, DESCRIPTION, EXPECTED));
		swapList.add(new TestCase("test4", TYPE, DESCRIPTION, EXPECTED));
		swapList.add(new TestCase("test5", TYPE, DESCRIPTION, EXPECTED));
		swapList.add(new TestCase("test6", TYPE, DESCRIPTION, EXPECTED));
		swapList.add(new TestCase("test7", TYPE, DESCRIPTION, EXPECTED));
		swapList.add(new TestCase("test8", TYPE, DESCRIPTION, EXPECTED));
		swapList.add(new TestCase("test9", TYPE, DESCRIPTION, EXPECTED));
		swapList.add(new TestCase("test10", TYPE, DESCRIPTION, EXPECTED));
		swapList.add(new TestCase("test11", TYPE, DESCRIPTION, EXPECTED));
		
		assertEquals(11, swapList.size());
	}

}
