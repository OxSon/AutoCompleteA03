package Autocomplete;

import java.util.Comparator;

public class BinarySearchDeluxe {
    // Return the index of the first key in a[] that equals the search key, or -1 if no such key.
    public static <Key> int firstIndexOf(Key[] a, Key key, Comparator<Key> comparator) {
        //NOTE: a must be sorted
        return binarySearchStub(a, key, comparator, true);
    }

    // Return the index of the last key in a[] that equals the search key, or -1 if no such key.
    public static <Key> int lastIndexOf(Key[] a, Key key, Comparator<Key> comparator) {
        //NOTE: a must be sorted
        return binarySearchStub(a, key, comparator, false);
    }

    private static <Key> int binarySearchStub(Key[] a, Key key, Comparator<Key> comparator, boolean first) {
        if (a == null || key == null || comparator == null) {
            throw new IllegalArgumentException("Arguments cannot be null");
        }
        //start with indices at beginning and end of array
        int low = 0;
        int hi = a.length - 1;
        while(low <= hi) {
            int mid = low + (hi - low) / 2; //middle of array
            int compareResult = comparator.compare(key, a[mid]);
            if (compareResult > 0)
                low = mid + 1;
            else if (compareResult < 0)
                hi = mid - 1;
            else if (low != mid)
                if (first)
                    hi = mid;
                else
                    low = mid;
            else
                return mid;
        }

        return -1;
    }
}
