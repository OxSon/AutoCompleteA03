package autoComplete;

import edu.princeton.cs.algs4.Quick;

import java.util.Arrays;

/**
 * Provides autocomplete functionality for a given set of Strings and weights
 * <p>
 * @author Alec Mills
 * @author Tony Arceo
 */
public class Autocomplete {
    private  Term[] terms;

    /**
     * Initializes the data structure from the given array of terms.
     *
     * @param terms the terms to compare searches against.
     */
    public Autocomplete(Term[] terms) {
        if (terms == null)
            throw new NullPointerException("Argument cannot be null");

        this.terms = terms;
        //TODO what kind of sort to use
        Quick.sort(this.terms);
//        Heap.sort(terms);
    }


    /**
     * Returns all terms that start with the given prefix, in descending order of weight.
     * @param prefix prefix to search for.
     * @return all terms that start with the given prefix.
     */
    public Term[] allMatches(String prefix) {
        if (prefix == null)
            throw new NullPointerException("Argument cannot be null");
        //FIXME does weight passed in matter?
        Term key = new Term(prefix, 0);
        int firstIndex = index(key, true);
        int lastIndex = index(key, false);

        Term[] matches = Arrays.copyOfRange(terms, firstIndex, lastIndex + 1);

        //FIXME parallelsort?
        //Arrays.sort(matches, Term.byReverseWeightOrder());
        Arrays.parallelSort(matches, Term.byReverseWeightOrder());

        return matches;
    }

    /**
     * Returns the number of terms that start with the given prefix.
     *
     * @param prefix the prefix to search for.
     * @return number of terms that start with the given prefix.
     */
    public int numberOfMatches(String prefix) {
        if (prefix == null)
            throw new NullPointerException("Argument cannot be null");

        Term key = new Term(prefix, 0);

        return nonNegativeNumber(index(key, false) - index(key, true));
    }

    //rounds all negative values up to zero
    private int nonNegativeNumber(int number) {
        return number < 0 ? 0 : number;
    }

    //Helper method to easily call either of the BinarySearch (index first or index last) methods
    private int index(Term key, boolean first) {
        int length = key.query().length();
        return first ?
                BinarySearchDeluxe.firstIndexOf(terms, key, Term.byPrefixOrder(length)) :
                BinarySearchDeluxe.firstIndexOf(terms, key, Term.byPrefixOrder(length));
    }

}
