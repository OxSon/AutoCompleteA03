# Programming Assignment: Autocomplete Me


Write a program to implement autocomplete for a given set of N strings and positive weights. That is, given a prefix, find all strings in the set that start with the prefix, in descending order of weight.

Autocomplete is an important feature of many modern applications. As the user types, the program predicts the complete query (typically a word or phrase) that the user intends to type. Autocomplete is most effective when there are a limited number of likely queries. For example, the [http://www.imdb.com](Internet Movie Database) uses it to display the names of movies as the user types; search engines use it to display suggestions as the user enters web search queries; cell phones use it to speed up text input.

IMDB search    Google search    Phone keyboard suggestions


In these examples, the application predicts how likely it is that the user is typing each query and presents to the user a list of the top-matching queries, in descending order of weight. These weights are determined by historical data, such as box office revenue for movies, frequencies of search queries from other Google users, or the typing history of a cell phone user. For the purposes of this assignment, you will have access to a set of all possible queries and associated weights (and these queries and weights will not change).

The performance of autocomplete functionality is critical in many systems. For example, consider a search engine which runs an autocomplete application on a server farm. According to one study, the application has only about 50ms to return a list of suggestions for it to be useful to the user. Moreover, in principle, it must perform this computation for every keystroke typed into the search bar and for every user!

In this assignment, you will implement autocomplete by sorting the queries in lexicographic order; using binary search to find the set of queries that start with a given prefix; and sorting the matching queries in descending order by weight.

### Part 1: autocomplete term
Write an immutable data type Term.java that represents an autocomplete term: a string query and an associated real-valued weight. You must implement the following API, which supports comparing terms by three different orders: [http://docs.oracle.com/javase/7/docs/api/java/lang/String.html#compareTo(java.lang.String)](lexicographic order) by query string (the natural order); in descending order by weight (an alternate order); and lexicographic order by query string but using only the first r characters (a family of alternate orderings). The last order may seem a bit odd, but you will use it in Part 3 to find all terms that start with a given prefix (of length r).

```java
public class Term implements Comparable<Term> {

    // Initialize a term with the given query string and weight.
    public Term(String query, double weight)

    // Compare the terms in descending order by weight.
    public static Comparator<Term> byReverseWeightOrder()

    // Compare the terms in lexicographic order but using only the first r characters of each query.
    public static Comparator<Term> byPrefixOrder(int r)

    // Compare the terms in lexicographic order by query.
    public int compareTo(Term that)

    // Return a string representation of the term in the following format:
    // the weight, followed by a tab, followed by the query.
    public String toString()
}
```

The constructor should throw a java.lang.NullPointerException if query is null and a java.lang.IllegalArgumentException unless weight is nonnegative. The byPrefixOrder() method should throw a java.lang.IllegalArgumentException if r is negative.

### Part 2: binary search
When binary searching a sorted array that contains more than one key equal to the search key, the client may want to know the index of either the first or the last such key. Accordingly, implement the following API:

```java
public class BinarySearchDeluxe {

    // Return the index of the first key in a[] that equals the search key, or -1 if no such key.
    public static <Key> int firstIndexOf(Key[] a, Key key, Comparator<Key> comparator)

    // Return the index of the last key in a[] that equals the search key, or -1 if no such key.
    public static <Key> int lastIndexOf(Key[] a, Key key, Comparator<Key> comparator)
}
```

Corner cases.  Each static method should throw a java.lang.NullPointerException if any of its arguments is null. You should assume that the argument array is in sorted order (with respect to the supplied comparator).

Performance requirements.  The firstIndexOf() and lastIndexOf() methods should make at most 1 + ⌈log2 N⌉ compares in the worst case,

### Part 3: autocomplete
In this part, you will implement an immutable data type that provides autocomplete functionality for a given set of string and weights, using Term and BinarySearchDeluxe. To do so, sort the terms in lexicographic order; use binary search to find the set of terms that start with a given prefix; and sort the matching terms in descending order by weight. Organize your program by creating an immutable data type Autocomplete with the following API:

```java
public class Autocomplete {

    // Initialize the data structure from the given array of terms.
    public Autocomplete(Term[] terms)

    // Return all terms that start with the given prefix, in descending order of weight.
    public Term[] allMatches(String prefix)

    // Return the number of terms that start with the given prefix.
    public int numberOfMatches(String prefix)
}
```

Corner cases.  The constructor and each method should throw a java.lang.NullPointerException its argument is null.

Performance requirements.  The constructor should make proportional to N log N compares (or better) in the worst case, where N is the number of terms. The allMatches() method should make proportional to log N + M log M compares (or better) in the worst case, where M is the number of matching terms. The numberOfMatches() method should make proportional to log N compares (or better) in the worst case. In this context, a compare is one call to any of the compare() or compareTo() methods defined in Term.

### Input format
We provide a number of sample input files for testing. Each file consists of an integer N followed by N pairs of query strings and positive weights. There is one pair per line, with the weight and string separated by a tab. A query string can be an arbitrary sequence of Unicode characters, including spaces (but not newlines).

The file [./input_files/wiktionary.txt](wiktionary.txt) contains the 10,000 most common words in Project Gutenberg, with weights equal to their frequencies.

The file [./input_files/cities.txt](cities.txt) contains over 90,000 cities, with weights equal to their populations. 

        % more wiktionary.txt     
        10000
           56271872.00	the
           33950064.00	of
           29944184.00	and
           25956096.00	to
           17420636.00	in
           11764797.00	i
           11073318.00	that
           10078245.00	was
            8799755.00	his
                        ...
               3923.23	calves

    	

    % more cities.txt
    93827
          14608512	Shanghai, China
          13076300	Buenos Aires, Argentina
          12691836	Mumbai, India
          12294193	Mexico City, Distrito Federal, Mexico
          11624219	Karachi, Pakistan
          11174257	İstanbul, Turkey
          10927986	Delhi, India
          10444527	Manila, Philippines
          10381222	Moscow, Russia
                    ...
                 2	Al Khāniq, Yemen

Below is a sample client that takes the name of an input file and an integer k as command-line arguments. It reads the data from the file; then it repeatedly reads autocomplete queries from standard input, and prints out the top k matching terms in descending order of weight.

    public static void main(String[] args) {

        // read in the terms from a file
        String filename = args[0];
        In in = new In(filename);
        int N = in.readInt();
        Term[] terms = new Term[N];
        for (int i = 0; i < N; i++) {
            double weight = in.readDouble();       // read the next weight
            in.readChar();                         // scan past the tab
            String query = in.readLine();          // read the next query
            terms[i] = new Term(query, weight);    // construct the term
        }

        // read in queries from standard input and print out the top k matching terms
        int k = Integer.parseInt(args[1]);
        Autocomplete autocomplete = new Autocomplete(terms);
        while (StdIn.hasNextLine()) {
            String prefix = StdIn.readLine();
            Term[] results = autocomplete.allMatches(prefix);
            for (int i = 0; i < Math.min(k, results.length); i++)
                StdOut.println(results[i]);
        }
    }

Here are a few sample executions:

    % java Autocomplete wiktionary.txt 5
    auto
            6197.0  automobile
            4250.0  automatic
    comp
          133159.0  company
           78039.8  complete
           60384.9  companion
           52050.3  completely
           44817.7  comply
    the
        56271872.0  the
         3340398.0  they
         2820265.0  their
         2509917.0  them
         1961200.0  there

    	         	

    % java Autocomplete cities.txt 7
    M
        12691836.0  Mumbai, India
        12294193.0  Mexico City, Distrito Federal, Mexico
        10444527.0  Manila, Philippines
        10381222.0  Moscow, Russia
         3730206.0  Melbourne, Victoria, Australia
         3268513.0  Montréal, Quebec, Canada
         3255944.0  Madrid, Spain
    Al M
          431052.0  Al Maḩallah al Kubrá, Egypt
          420195.0  Al Manşūrah, Egypt
          290802.0  Al Mubarraz, Saudi Arabia
          258132.0  Al Mukallā, Yemen
          227150.0  Al Minyā, Egypt
          128297.0  Al Manāqil, Sudan
           99357.0  Al Maţarīyah, Egypt

### Interactive GUI (optional, but fun and no extra work)
Download and compile [ftp://ftp.cs.princeton.edu/pub/cs226/autocomplete/AutocompleteGUI.java](AutocompleteGUI.java). The program takes the name of a file and an integer k as command-line arguments and provides a GUI for the user to enter queries. It presents the top k matching terms in real time. When the user selects a term, the GUI opens up the results from a Google search for that term in a browser.

    % java AutocompleteGUI cities.txt 10


### Extra credit 1
This is an opportunity to earn extra credit and contribute to future offerings of this assignment. Create a real-world data (preferably large or huge) for which autocomplete would be appropriate and document it in your readme file (including a reference to the original data source). Below are a few possibilities. Note that some of the datasets are massive and you will need to filter them down to appropriate sizes and put them into our file format.

   - [http://dumps.wikimedia.org/other/pagecounts-raw/](Wikipedia): term = Wikipedia page, weight = number of hits per year.

   - [http://storage.googleapis.com/books/ngrams/books/datasetsv2.html](Google books Ngram Viewer): term = n-gram, weight = frequency of occurrence in corpus of books.

   - [http://en.wiktionary.org/wiki/Wiktionary%3AFrequency_lists](Wiktionary): term = word, weight = frequency of occurrence in corpus. Pick a language with a non-Latin alphabet such as Hebrew, Arabic, Russian, Greek, or Japanese.

   - [http://www.imdb.com/interfaces](The Internet Movie Database): term = movie, weight = number of reviews or average rating. 

Be sure that your file is in the prescribed format (tab-separated and UTF-8 encoded). If your file is less than 50MB, submit it as usual; if it is larger, please contact your preceptor for submission instructions.

### Extra credit 2
Improve [ftp://ftp.cs.princeton.edu/pub/cs226/autocomplete/AutocompleteGUI.java](AutocompleteGUI.java) in the following (or other) ways:

   - Improve the graphical layout (e.g., align search bar and suggestion box, allow search bar and suggestion box to expand to width of window).

   - Fix some of the known bugs, which are documented in the file. 

### Deliverables
Submit `Autocomplete.java`, `BinarySearchDeluxe.java`, and `Term.java`. Your may not call any library functions other than those in `java.lang`, `java.util`, `stdlib.jar`, and `algs4.jar`. Finally, submit a [./readme.txt](readme.txt) file and answer the questions.

This assignment was developed by Matthew Drabick and Kevin Wayne.
`Copyright © 2014.`
