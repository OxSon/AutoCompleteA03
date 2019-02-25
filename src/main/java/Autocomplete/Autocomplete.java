package Autocomplete;

public class Autocomplete {
    // Initialize the data structure from the given array of terms.
    public Autocomplete(Term[] terms) {
        if (terms == null)
            throw new NullPointerException("Argument cannot be null");
        //TODO
    }

    // Return all terms that start with the given prefix, in descending order of weight.
    public Term[] allMatches(String prefix) {
        if (prefix == null)
            throw new NullPointerException("Argument cannot be null");
        //TODO
        return null;
    }

    // Return the number of terms that start with the given prefix.
    public int numberOfMatches(String prefix) {
        if (prefix == null)
            throw new NullPointerException("Argument cannot be null");
        //TODO
        return 0;
    }
}
