package edu.ncsu.csc216.stp.model.util;


/**
 * Implements the ISwapList interface. 
 *
 * @author Maddie Moore
 *
 * @param <E> type for the ISwapList
 */
public class SwapList<E> implements ISwapList<E> {
	
	/** Holds the initial capacity value */
	private static final int INITIAL_CAPACITY = 10;
	/** List of elements to sort */
	private E[] list;
	/** Size of the list */
	private int size;
	
	/**
	 * Constructs a SwapList
	 */
	@SuppressWarnings("unchecked")
	public SwapList() {
		size = 0;
		list = (E[]) new Object[INITIAL_CAPACITY];
	}
	
	/**
	 * Adds the element to the end of the list.
	 * @param element element to add
	 * @throws NullPointerException if element is null
	 * @throws IllegalArgumentException if the element cannot be added
	 */
	public void add(E element) {
		if (element == null) {
			throw new NullPointerException("Cannot add null element.");
		}
		if (list[0] == null) {
			try {
				list[0] = element;
				size++;
			} catch (Exception E) {
				throw new IllegalArgumentException("Cannot add element");
			}
		}
		else {
			if (list[size - 1] != null) {
				checkCapacity(size + 1);
			}
			try {
				for (int i = 0; i < list.length; i++) {
					if (list[i] == null) {
						list[i] = element;
						size++;
						break;
					}
				}
			}
			catch (Exception E) {
				throw new IllegalArgumentException("Cannot add element");
			}
		}
	}
	
	/**
	 * It checks the capacity and grows (doubles) the array if needed. It accepts newSize, the amount of space 
	 * needed, as an argument and checks if the list size is greater than the newSize. If not, it grows the size
	 * of the list.
	 * @param newSize the amount of space needed
	 */
	@SuppressWarnings("unchecked")
	private void checkCapacity(int newSize) {
		if (newSize > list.length) {
			E[] newList = (E[]) new Object[size * 2];
			for (int i = 0; i < list.length; i++) {
				newList[i] = list[i];
			}
			list = newList;
		}
	}
	
	/**
	 * The element is removed from the list with the given index. Returns the element from the given index.
	 * @param idx index to remove element from
	 * @throws IndexOutOfBoundsException if the idx is out of bounds for the list
	 * @return the element from the given index
	 */
	public E remove(int idx) {
		checkIndex(idx);
		if(list[0] == null) {
			throw new IndexOutOfBoundsException("Invalid index.");
		}
		E element = list[idx];
		for (int i = 0; i < size; i++) {
			if (i == size - 1) {
				list[i] = null;
				break;
			}
			if (i >= idx) {
				list[i] = list[i + 1];
			}
		}
		size--;
		return element;
	}
	
	/**
	 * Checks if the given index is valid (between the expected bounds) and throws an IndexOutofBoundException 
	 * if it exceeds the bounds. It can be used while adding, moving, or removing elements from the sortedList.
	 * @param idx index to check 
	 * @throws IndexOutOfBoundsException if the given index exceeds the bounds
	 */
	private void checkIndex(int idx) {
		if (idx >= size || idx < 0) {
			throw new IndexOutOfBoundsException("Invalid index.");
		}
	}
	
	/**
	 * Moves the element at the given index to index-1.  If the element is
	 * already at the front of the list, the list is not changed.
	 * @param idx index of element to move up
	 * @throws IndexOutOfBoundsException if the idx is out of bounds
	 * 		for the list
	 */
	public void moveUp(int idx) {
		checkIndex(idx);
		if (idx != 0) {
			E element = list[idx - 1];
			list[idx - 1] = list[idx];
			list[idx] = element;
		}
	}
	
	/**
	 * Moves the element at the given index to index+1.  If the element is
	 * already at the end of the list, the list is not changed.
	 * @param idx index of element to move down
	 * @throws IndexOutOfBoundsException if the idx is out of bounds
	 * 		for the list
	 */
	public void moveDown(int idx) {
		checkIndex(idx);
		if(size == 0 && idx == 0) {
			throw new IndexOutOfBoundsException("Invalid index.");
		}
		if (idx != size - 1 && list[idx + 1] != null) {
			E element = list[idx + 1];
			list[idx + 1] = list[idx];
			list[idx] = element;
		}
	}
	
	/**
	 * Moves the element at the given index to index 0.  If the element is
	 * already at the front of the list, the list is not changed.
	 * @param idx index of element to move to the front
	 * @throws IndexOutOfBoundsException if the idx is out of bounds
	 * 		for the list
	 */
	public void moveToFront(int idx) {
		checkIndex(idx);
		if(size == 0 && idx == 0) {
			throw new IndexOutOfBoundsException("Invalid index.");
		}
		E element = list[idx];
		if (idx != 0) {
			for (int i = size - 1; i > -1; i--) {
				if (i <= idx && i != 0) {
					list[i] = list[i - 1];
				}
				else if (i == 0) {
					list[i] = element;
				}
				else
					continue;
			}
		}
	}
	
	/**
	 * Moves the element at the given index to size-1.  If the element is
	 * already at the end of the list, the list is not changed.
	 * @param idx index of element to move to the back
	 * @throws IndexOutOfBoundsException if the idx is out of bounds
	 * 		for the list
	 */
	public void moveToBack(int idx) {
		checkIndex(idx);
		if(size == 0 && idx == 0) {
			throw new IndexOutOfBoundsException("Invalid index.");
		}
		E element = list[idx];
		if (idx != size - 1) {
			for (int i = 0; i < size; i++) {
				if (i >= idx && i != size - 1) {
					list[i] = list[i + 1];
				}
				else if (i == size - 1) {
					list[i] = element;
				}
			}
		}
	}
	
	/**
	 * Returns the element at the given index.
	 * @param idx index of the element to retrieve
	 * @return element at the given index
	 * @throws IndexOutOfBoundsException if the idx is out of bounds
	 * 		for the list
	 */
	public E get(int idx) {
		checkIndex(idx);
		return list[idx];
	}
	
	/**
	 * Returns the number of elements in the list.
	 * @return number of elements in the list
	 */
	public int size() {
		return size;
	}
}
