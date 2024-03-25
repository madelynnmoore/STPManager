package edu.ncsu.csc216.stp.model.util;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

/**
 * This is the test class for SortedList
 * @author Evans Chege
 *
 */
public class SortedListTest {

	/**
	 * This is the test method for the constructor
	 */
	@Test
	public void testSortedList() {
		SortedList<String> list = new SortedList<String>();
		assertEquals(0, list.size());
		assertFalse(list.contains("apple"));
		
	}

	/**
	 * This is the test method for SortedList.add()
	 */
	@Test
	public void testAdd() {
		SortedList<String> list = new SortedList<String>();
		
		list.add("Arnold");
		assertEquals(1, list.size());

		list.add("apple");
		assertEquals(2, list.size());

		list.add("kiwi");
		assertEquals(3, list.size());

		list.add("cranberry");
		assertEquals(4, list.size());

		
		assertThrows(NullPointerException.class,
				() -> list.add(null));
	}

	/**
	 * This is the test method for SortedList.remove()
	 */
	@Test
	public void testRemove() {
		SortedList<String> list = new SortedList<String>();

		assertThrows(IndexOutOfBoundsException.class, 
				() -> list.remove(-1));

		list.add("banana");
		assertEquals("banana", list.get(0));
		list.add("apple");
		assertEquals("apple", list.get(0));
		assertEquals("banana", list.get(1));
		list.add("orange");
		assertEquals("apple", list.get(0));
		assertEquals("banana", list.get(1));
		assertEquals("orange", list.get(2));
		list.add("eggplant");
		assertEquals("apple", list.get(0));
		assertEquals("banana", list.get(1));
		assertEquals("eggplant", list.get(2));
		assertEquals("orange", list.get(3));
		
		assertEquals("banana", list.remove(1));
		
		list.add("Algeria");
		list.add("Botswana");
		list.add("Cameroon");
		list.add("Djibouti");
		assertEquals(7, list.size());

		assertThrows(IndexOutOfBoundsException.class, 
				() -> list.remove(-3));

		assertThrows(IndexOutOfBoundsException.class, 
				() -> list.remove(list.size()));

		list.remove(2);
		assertEquals(6, list.size());
	}


	/**
	 * This is the test method for SortedList.contains()
	 */
	@Test
	public void testContains() {
		SortedList<String> list = new SortedList<String>();


		list.add("Algeria");
		list.add("Botswana");
		list.add("Cameroon");
		list.add("Djibouti");
		
		assertFalse(list.contains("America"));
		assertTrue(list.contains("Botswana"));
	}

	/**
	 * This is the test method for SortedList.get()
	 */
	@Test
	public void testGet() {
		SortedList<String> list = new SortedList<String>();

		assertThrows(IndexOutOfBoundsException.class, 
				() -> list.get(-1));


		list.add("Algeria");
		list.add("Botswana");
		list.add("Cameroon");
		list.add("Djibouti");
		
		assertEquals("Algeria", list.get(0));
		assertEquals("Botswana", list.get(1));
		assertEquals("Cameroon", list.get(2));
		assertEquals("Djibouti", list.get(3));
	}


}
