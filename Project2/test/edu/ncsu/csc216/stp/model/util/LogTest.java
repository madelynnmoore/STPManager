package edu.ncsu.csc216.stp.model.util;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import edu.ncsu.csc216.stp.model.tests.TestResult;
/**
 * This is the test class for Log.java
 * @author Evans Chege
 *
 */
public class LogTest {

	/**
	 * This is the test method for Log Constructor
	 */
	@Test
	public void testLog() {
		Log<TestResult> log = new Log<TestResult>();
		assertEquals(0, log.size());
	}

	/**
	 * This is the test method for Log.Add();
	 */
	@Test
	public void testAdd() {
		Log<TestResult> log = new Log<TestResult>();
		assertEquals(0, log.size());
		log.add(new TestResult(true, "1"));
		assertEquals(1, log.size());
		log.add(new TestResult(true, "2"));
		assertEquals(2, log.size());
		log.add(new TestResult(true, "3"));
		assertEquals(3, log.size());
		log.add(new TestResult(true, "4"));
		assertEquals(4, log.size());
		log.add(new TestResult(true, "5"));
		assertEquals(5, log.size());
		log.add(new TestResult(true, "6"));
		assertEquals(6, log.size());
		log.add(new TestResult(true, "7"));
		assertEquals(7, log.size());
		log.add(new TestResult(true, "8"));
		assertEquals(8, log.size());
		log.add(new TestResult(true, "9"));
		assertEquals(9, log.size());
		log.add(new TestResult(true, "10"));
		assertEquals(10, log.size());
		log.add(new TestResult(true, "11"));
		assertEquals(11, log.size());
		//Tests adding a null element
		Log<TestResult> log1 = new Log<TestResult>();
		assertThrows(NullPointerException.class,
				() -> log1.add(null));
	}

	/**
	 * This is the test method for Log.get
	 */
	@Test
	public void testGet() {
		Log<TestResult> log = new Log<TestResult>();
		log.add(new TestResult(true, "actual"));
		log.add(new TestResult(false, "actual1"));
		log.add(new TestResult(true, "actual2"));
		assertTrue(log.get(0).isPassing());
		assertEquals("actual", log.get(0).getActualResults());
		assertFalse(log.get(1).isPassing());
		assertEquals("actual1", log.get(1).getActualResults());
		assertTrue(log.get(2).isPassing());
		assertEquals("actual2", log.get(2).getActualResults());
		assertEquals(3, log.size());
		//Tests getting an 11th elementassertEquals(3, log.size());
		Log<TestResult> log1 = new Log<TestResult>();
		assertEquals(0, log1.size());
		log1.add(new TestResult(true, "1"));
		assertEquals(1, log1.size());
		log1.add(new TestResult(true, "2"));
		assertEquals(2, log1.size());
		log1.add(new TestResult(true, "3"));
		assertEquals(3, log1.size());
		log1.add(new TestResult(true, "4"));
		assertEquals(4, log1.size());
		log1.add(new TestResult(true, "5"));
		assertEquals(5, log1.size());
		log1.add(new TestResult(true, "6"));
		assertEquals(6, log1.size());
		log1.add(new TestResult(true, "7"));
		assertEquals(7, log1.size());
		log1.add(new TestResult(true, "8"));
		assertEquals(8, log1.size());
		log1.add(new TestResult(true, "9"));
		assertEquals(9, log1.size());
		log1.add(new TestResult(true, "10"));
		assertEquals(10, log1.size());
		log1.add(new TestResult(true, "11"));
		assertEquals(11, log1.size());
		assertEquals("1", log1.get(0).getActualResults());
		assertEquals("2", log1.get(1).getActualResults());
		assertEquals("3", log1.get(2).getActualResults());
		assertEquals("4", log1.get(3).getActualResults());
		assertEquals("5", log1.get(4).getActualResults());
		assertEquals("6", log1.get(5).getActualResults());
		assertEquals("7", log1.get(6).getActualResults());
		assertEquals("8", log1.get(7).getActualResults());
		assertEquals("9", log1.get(8).getActualResults());
		assertEquals("10", log1.get(9).getActualResults());
		assertEquals("11", log1.get(10).getActualResults());
		//Tests get() with an index out of bounds
		Log<TestResult> log2 = new Log<TestResult>();
		log2.add(new TestResult(true, "1"));
		log2.add(new TestResult(true, "2"));
		assertThrows(IndexOutOfBoundsException.class,
				() -> log2.get(-1));
		assertThrows(IndexOutOfBoundsException.class,
				() -> log2.get(11));
	}

	/**
	 * This is the test method for Log.size
	 */
	@Test
	public void testSize() {
		Log<TestResult> log = new Log<TestResult>();
		assertEquals(0, log.size());
		log.add(new TestResult(true, "actual"));
		log.add(new TestResult(true, "actual"));
		log.add(new TestResult(true, "actual"));
		log.add(new TestResult(true, "actual"));
		log.add(new TestResult(true, "actual"));
		log.add(new TestResult(true, "actual"));
		log.add(new TestResult(true, "actual"));
		log.add(new TestResult(true, "actual"));
		log.add(new TestResult(true, "actual"));
		log.add(new TestResult(true, "actual"));
		assertEquals(10, log.size());
	}

}
