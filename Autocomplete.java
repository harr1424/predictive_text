/*
        A Programming Assignment for Data Structures and Algorithms completed
        by John Harrington in October, 2020.

        This assignment is being shared in order to demonstrate my work to future
        employers. I do not authorize nor encourage any academic misconduct that may
        result from sharing this content online.

        This assignment was designed by Robert Sedgewick and Kevin Wayne as part of
        their Algorithms Coursera Course partnered with Princeton University.

        This program contains three classes: Term, BinarySearchDeluxe, and
        Autocomplete.

        Term initializes a Term data structure, which consists of a String query
        and a long weight. The weight associated with a query determines how
        relevant the Term object is. This class also contains several
        comparators used to compare Terms according to different criteria.

        BinarySearchDeluxe allows a user to search for all occurrences
        of a search Term within an sorted array using the different comparators
        implemented in the Term class. It contains a modified binary search that
        will continue searching for a Term after the initial 'hit,' continuing
        until the first and last index of the sorted array containing the search
        key have been identified.

        Autocomplete utilizes the comparators and binary search implemented in
        Terms and BinarySearchDeluxe to return an array of all matching search
        Terms sorted in descending order of weight (most relevant results
        appear first).

        Supporting code for this assignment is part of the Algorithms Fourth Edition
        Library and can be found here: https://algs4.cs.princeton.edu/code/
*/

import java.util.Arrays;

public class Autocomplete {
    private Term[] terms;
    private Term[] results;

    // Initializes the data structure from the given array of terms.
    public Autocomplete(Term[] terms) {
        Arrays.sort(terms);
        this.terms = terms;
    }

    // Returns all terms that start with the given prefix, in descending order of weight.
    public Term[] allMatches(String prefix) {
        // Create an empty term array to return if no matches found
        Term[] empty = new Term[0];

        // Create a term to pass as key to search for
        Term t = new Term(prefix, 0);
        int lo = BinarySearchDeluxe.firstIndexOf(terms, t, Term.byPrefixOrder(prefix.length()));
        int hi = BinarySearchDeluxe.lastIndexOf(terms, t, Term.byPrefixOrder(prefix.length()));

        // Return empty array if no matches
        if (hi == -1 || lo == -1) return empty;

        // Copy terms from lo to hi to results array
        int length = (hi - lo) + 1;
        results = new Term[length];
        int j = 0; // Index for results array
        for (int i = lo; i <= hi; i++) {
                results[j++] = terms[i];
            }

        // Sort results by reverse weight order
        Arrays.sort(results, Term.byReverseWeightOrder());

        return results;
    }

    // Returns the number of terms that start with the given prefix.
    public int numberOfMatches(String prefix) {
        Term t = new Term(prefix, 0);
        int lo = BinarySearchDeluxe.firstIndexOf(terms, t, Term.byPrefixOrder(prefix.length()));
        int hi = BinarySearchDeluxe.lastIndexOf(terms, t, Term.byPrefixOrder(prefix.length()));
        if (hi == -1 || lo == -1) return 0;
        int length = (hi - lo) + 1;

        return length;
    }
    

    // A sample client for unit testing
    public static void main(String[] args) {

        // read in the terms from a file
        String filename = args[0];
        In in = new In(filename);
        int N = in.readInt();
        Term[] terms = new Term[N];
        int i;
        for (i = 0; i < N; i++) {
            long weight = in.readLong();           // read the next weight
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
            for ( i = 0; i < Math.min(k, results.length); i++)
                StdOut.println(results[i]);
        }
    }
}
