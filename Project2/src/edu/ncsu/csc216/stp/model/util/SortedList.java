package edu.ncsu.csc216.stp.model.util;

/**
 * This is the class that implements iSortedList
 * @author Evans Chege, Annabella Jackmore, Maddie Moore
 *
 * @param <E> represents the type of SortedList
 */
public class SortedList<E extends Comparable<E>> implements ISortedList<E> {
	/** This represents the first node */
	private ListNode front;
	/** This represents the size of the sorted list*/
	private int size;
	
	/**
	 * This is the constructor for SortedList
	 */
	public SortedList() {
		front = null;
		size = 0;
	}
	
	/**
	 * Adds the element to the list in sorted order.
	 * @param element element to add
	 * @throws NullPointerException if element is null
	 * @throws IllegalArgumentException if element cannot be added 
	 */
	public void add(E element) {
		if(element == null) {
			throw new NullPointerException("Cannot add null element.");
		}
		ListNode current = front;
		for (int i = 0; i < size; i++) {
			if (i == 0) {
				if (front.data.compareTo(element) == 0) {
					throw new IllegalArgumentException("Cannot add duplicate element.");
				}
				if (front.data.compareTo(element) > 0) {
					front = new ListNode(element, front);
					break;
				}
			}
			if (current.next != null) {
				if (current.next.data.compareTo(element) == 0) {
					throw new IllegalArgumentException("Cannot add duplicate element.");
				}
				if (current.next.data.compareTo(element) > 0) {
					current.next = new ListNode(element, current.next);
					break;
				}
				current = current.next;
				continue;
			}
			else {
				current.next = new ListNode(element, null);
			}
		}
		if (front == null) {
			front = new ListNode(element, null);
		}
		size++;
	}
	
	/**
	 * This method is in charge of removing elements from the Sorted List
	 * @param idx represents the index of the element
	 * @return the data contained in the ListNode to remove
	 */
	public E remove(int idx) {
		checkIndex(idx);
		E value = null;
		if (front == null) {
			return null;
		}
		if (idx == 0) {
			value = front.data;
			front = front.next;
		} else {
			ListNode current = front;
			int i = 0;
			while (current.next != null && i < idx - 1) {
				current = current.next;
				i++;
			}
			if (current.next == null) {
				throw new IndexOutOfBoundsException();
			}
			value = current.next.data;
			current.next = current.next.next;
		}
		size--;
		return value;
	}
	
	/**
	 * This method is in charge of checking the index
	 * @param idx represents the index being checked.
	 */
	private void checkIndex(int idx) {
		if (idx >= size || idx < 0) {
			throw new IndexOutOfBoundsException("Invalid index.");
		}
	}
	
	/**
	 * This method is in charge of checking if a given element exists in the Sorted List
	 * @param element represents the element being checked
	 * @return false for now
	 */
	public boolean contains (E element) {
		for (int i = 0; i < size; i++  ) {
			if (get(i) == element) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * This method is in charge of getting an element at a given index
	 * @param idx represents the given index
	 * @return null for now 
	 */
	public E get(int idx) {
		checkIndex(idx);
		E value = null;
		if (front == null) {
			return null;
		}
		if (idx == 0) {
			value = front.data;
		} else {
			ListNode current = front;
			int i = 0;
			while (current.next != null && i < idx - 1) {
				current = current.next;
				i++;
			}
			if (current.next == null) {
				throw new IndexOutOfBoundsException();
			}
			value = current.next.data;
		}
		return value;
	}
	
	/**
	 * This method is in charge of returning the size
	 * @return size of the SortedList
	 */
	public int size() {
		return size;
	}
	
	/**
	 * This is the ListNode class which will be used to traverse through SortedList
	 * @author Evans Chege
	 *
	 */
	private class ListNode {
		/** This represents the current data*/
		public E data;
		/** This represents the next list node*/
		public ListNode next;
		/**
		 * This is the constructor for the List Node 
		 * @param data represents the current node's data
		 * @param next represents the next node;
		 */
		public ListNode(E data, ListNode next) {
			this.data = data;
			this.next = next;
		}
	}

}
