package autoComplete;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class BinarySearchTest {
		private Key[] sortedArray = {7, 8, 9, 10, 11, 11, 13};
		
		@BeforeEach
		void setUp() throws Exception {
		}

		@Test
		void testFirstofIndex() {
			int expected = 4;
			int actual = BinarySearchDeluxe.firstIndexOf(sortedArray, 9); // We need to call the method linear 
			assertEquals(expected, actual); // watch order of arguments.. Expected first then ACTUAL.
		}
		
		@Test
		void testLastofIndex() {
			 // We need to call the method linear.. Index values expected 
			//assertEquals(6, Search.linear(unsortedArray, 6)); // watch order of arguments.. Expected first then ACTUAL.
		}
		
}