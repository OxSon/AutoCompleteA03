package Autocomplete;

import java.util.Comparator;

public class BinarySearchDeluxe {
    // Return the index of the first key in a[] that equals the search key, or -1 if no such key.
    public static <Key> int firstIndexOf(Key[] a, Key key, Comparator<Key> comparator) {
        //Performance reqs:  FIXME
        // first/lastIndexOf should make at most 1 + ceiling(log2(N)) compares in the worst case
        if (a == null || key == null || comparator == null) {
            throw new IllegalArgumentException("Arguments cannot be null");
        }
        //TODO
        return 0;
    }

    // Return the index of the last key in a[] that equals the search key, or -1 if no such key.
    public static <Key> int lastIndexOf(Key[] a, Key key, Comparator<Key> comparator) {
        if (a == null || key == null || comparator == null) {
            throw new IllegalArgumentException("Arguments cannot be null");
        }
        //TODO
        return 0;
    }
}
