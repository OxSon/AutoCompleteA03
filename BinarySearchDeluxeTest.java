package autoComplete;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Comparator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class BinarySearchDeluxeTest {

	private Integer[] repeatElementArray = { 7, 8, 8, 11, 11, 13 };
	private Integer[] firstElementArray = { 8, 9, 11, 11, 13, 14, 15 };
	private Integer[] lastElementArray = { 3, 4, 5, 6, 7, 8 };
	private Integer[] singleElementArray = { 8 };
	private Integer[] emptyArray = {};

	@BeforeEach
	void setUp() throws Exception {
	}

	@Test
	void testFirstofKey() {
		int expected = 1;
		int actual = BinarySearchDeluxe.firstIndexOf(repeatElementArray, 8,
				(thisKey, thatKey) -> ((Integer) thisKey).compareTo((Integer) thatKey)); 
		assertEquals(expected, actual); 
	}

	@Test
	void testFirstofKey_NoSuchElement() {
		int expected = -1;
		int actual = BinarySearchDeluxe.firstIndexOf(repeatElementArray, 2,
				(thisKey, thatKey) -> ((Integer) thisKey).compareTo((Integer) thatKey)); 
		assertEquals(expected, actual); 
	}

	@Test
	void testFirstofKey_SingleElement() {
		int expected = 0;
		int actual = BinarySearchDeluxe.firstIndexOf(singleElementArray, 8,
				(thisKey, thatKey) -> ((Integer) thisKey).compareTo((Integer) thatKey)); 
		assertEquals(expected, actual); 
	}

	@Test
	void testFirstofKey_EmptyArray() {
		int expected = -1;
		int actual = BinarySearchDeluxe.firstIndexOf(emptyArray, 8,
				(thisKey, thatKey) -> ((Integer) thisKey).compareTo((Integer) thatKey)); 
		assertEquals(expected, actual); 
	}

	// * * * * * * * * * * * * * * * ARRAY HEAD/TAIL TESTS * * * * * * * * * * * * *

	@Test
	void testFirstofKey_ElementAtFirstIndex() {
		int expected = 0;
		int actual = BinarySearchDeluxe.firstIndexOf(firstElementArray, 8,
				(thisKey, thatKey) -> ((Integer) thisKey).compareTo((Integer) thatKey)); 
		assertEquals(expected, actual); 
	}

	@Test
	void testFirstofKey_ElementAtLastIndex() {
		int expected = 5;
		int actual = BinarySearchDeluxe.firstIndexOf(lastElementArray, 8,
				(thisKey, thatKey) -> ((Integer) thisKey).compareTo((Integer) thatKey)); 
		assertEquals(expected, actual); 
	}

	// * * * * * * * * * * * * * * * NULL ARGUMENT TESTS * * * * * * * * * * * * * *
	@Test
	public void testFirstofKey_ArrayNull() {
		assertThrows(IllegalArgumentException.class, () -> BinarySearchDeluxe.firstIndexOf(null, 8,
				(thisKey, thatKey) -> ((Integer) thisKey).compareTo((Integer) thatKey)));
	}

	@Test
	public void testFirstofKey_ElementNull() {
		assertThrows(IllegalArgumentException.class, () -> BinarySearchDeluxe.firstIndexOf(repeatElementArray, null,
				(thisKey, thatKey) -> ((Integer) thisKey).compareTo((Integer) thatKey)));
	}

	@Test
	public void testFirstofKey_CompartorNull() {
		assertThrows(IllegalArgumentException.class,
				() -> BinarySearchDeluxe.firstIndexOf(repeatElementArray, 8, null));

	}

	// @Test
	// void testFirstofIndex() {
	// Integer expected = 4;
	// Integer actual = BinarySearchDeluxe.firstIndexOf(sortedArray, new
	// Integer(11), ourComparator()); // We need to call the method linear
	// assertEquals(expected, actual); // watch order of arguments.. Expected first
	// then ACTUAL.
	// }
	//
	//
	// public <Key> Comparator<Key> ourComparator() {
	// return new Comparator<Key>() {
	// @Override
	// public int compare(Key o1, Key o2) {
	// Integer a = (Integer) o1;
	// Integer b = (Integer) 02;
	// return a.compareTo(b);
	// }
	// };
	// }

	@Test
	void testLastofKey() {
		int expected = 2;
		int actual = BinarySearchDeluxe.lastIndexOf(repeatElementArray, 8,
				(thisKey, thatKey) -> ((Integer) thisKey).compareTo((Integer) thatKey));
		assertEquals(expected, actual); 
	}

	@Test
	void testLastofKey_NoSuchElement() {
		int expected = -1;
		int actual = BinarySearchDeluxe.lastIndexOf(repeatElementArray, 2,
				(thisKey, thatKey) -> ((Integer) thisKey).compareTo((Integer) thatKey)); 
		assertEquals(expected, actual); 
	}

	@Test
	void testLastofKey_SingleElement() {
		int expected = 0;
		int actual = BinarySearchDeluxe.lastIndexOf(singleElementArray, 8,
				(thisKey, thatKey) -> ((Integer) thisKey).compareTo((Integer) thatKey)); 
		assertEquals(expected, actual); 
	}

	@Test
	void testLastofKey_EmptyArray() {
		int expected = -1;
		int actual = BinarySearchDeluxe.lastIndexOf(emptyArray, 8,
				(thisKey, thatKey) -> ((Integer) thisKey).compareTo((Integer) thatKey)); 
		assertEquals(expected, actual); 
	}

	// * * * * * * * * * * * * * * * ARRAY HEAD/TAIL TESTS * * * * * * * * * * * * *
	@Test
	void testLastofKey_ElementAtFirstIndex() {
		int expected = 0;
		int actual = BinarySearchDeluxe.lastIndexOf(firstElementArray, 8,
				(thisKey, thatKey) -> ((Integer) thisKey).compareTo((Integer) thatKey)); 
		assertEquals(expected, actual); 
	}

	@Test
	void testLastofKey_ElementAtLastIndex() {
		int expected = 5;
		int actual = BinarySearchDeluxe.lastIndexOf(lastElementArray, 8,
				(thisKey, thatKey) -> ((Integer) thisKey).compareTo((Integer) thatKey)); 
		assertEquals(expected, actual); 
	}

	// * * * * * * * * * * * * * * * NULL ARGUMENT TESTS * * * * * * * * * * * * * *
	@Test
	public void testLastofKey_ArrayNull() {
		assertThrows(IllegalArgumentException.class, () -> BinarySearchDeluxe.lastIndexOf(null, 8,
				(thisKey, thatKey) -> ((Integer) thisKey).compareTo((Integer) thatKey)));
	}

	@Test
	public void testLastofKey_ElementNull() {
		assertThrows(IllegalArgumentException.class, () -> BinarySearchDeluxe.lastIndexOf(repeatElementArray, null,
				(thisKey, thatKey) -> ((Integer) thisKey).compareTo((Integer) thatKey)));
	}

	@Test
	public void testLastofKey_CompartorNull() {
		assertThrows(IllegalArgumentException.class, () -> BinarySearchDeluxe.lastIndexOf(repeatElementArray, 8, null));

	}

}
