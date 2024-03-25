package edu.ncsu.csc216.stp.model.util;


/**
 * Represents as list where elements can only be added to the end of the list.  Once an element is added, it 
 * cannot be removed.
 * @author Evans Chege, Maddie Moore
 * @param <E> represents the type of Log
 *
 */
public class Log<E> implements ILog<E> {
	/** This is the list of log */
	private E[] log;
	/**This is the variable representing size */
	private int size;
	/**This is the final variable representing initial capacity */
	private static final int INIT_CAPACITY = 10;
	
	/**
	 * Constructs a Log by setting the log field equal to a new arraylist of elements. Sets the size to 10 using
	 * the initial capacity field.
	 */
	@SuppressWarnings("unchecked")
	public Log() {
		size = 0;
		log = (E[]) new Object[INIT_CAPACITY];
	}
	
	/**
	 * Adds elements to Log.
	 * @param element represents the element to be added
	 * @throws NullPointerException if the element to be added is null
	 */
	@SuppressWarnings("unchecked")
	public void add(E element) {
		if (element == null) {
			throw new NullPointerException("Cannot add null element.");
		}
		if (log[0] == null) {
			try {
				log[0] = element;
				size++;
			} catch (Exception E) {
				throw new IllegalArgumentException("Cannot add element");
			}
		}
		else {
			if (log[size - 1] != null && (size + 1) > log.length) {
					E[] newList = (E[]) new Object[size * 2];
					for (int i = 0; i < log.length; i++) {
						newList[i] = log[i];
					}
					log = newList;
			}
			try {
				for (int i = 0; i < log.length; i++) {
					if (log[i] == null) {
						log[i] = element;
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
	
	/**s
	 * Gets elements at an index in the Log.
	 * @param idx represents the index
	 * @return null for now
	 * @throws IndexOutOfBoundsException if if the index parameter is out of bounds for the list 
	 */
	public E get(int idx) {
		if (idx >= size || idx < 0) {
			throw new IndexOutOfBoundsException("Invalid index.");
		}
		return log[idx];
	}
	
	/**
	 * Gets the size of the Log.
	 * @return size of the log
	 */
	public int size() {
		return size;
	}
}
