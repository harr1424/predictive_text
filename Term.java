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
import java.util.Comparator;

public class Term implements Comparable<Term> {
    private final String query;
    private final long weight;

    // Initializes a term with the given query string and weight.
    public Term(String query, long weight) {
        this.query = query;
        this.weight = weight;
    }

    // Compare terms in reverse order by associated weight
    public static Comparator<Term> byReverseWeightOrder() {
        return new Comparator<Term>() {
            public int compare(Term v, Term w) {
                if (v.weight > w.weight) return -1;
                else if (v.weight == w.weight) return 0;
                else if (v.weight < w.weight) return 1;
                else return -999;
            }
        };
    }

    // Compares the two terms in lexicographic order but using only the first r characters of each query.
    public static Comparator<Term> byPrefixOrder(int r) {
        return new Comparator<Term>() {
            public int compare(Term v, Term w) {
                int lengthV = v.query.length();
                int lengthW = w.query.length();
                if (r > lengthV || r > lengthW) {
                    return v.query.compareTo(w.query);
                }
                return v.query.substring(0,r).compareTo(w.query.substring(0,r));
            }
        };
    }

    // Compares the two terms in lexicographic order by query.
    public int compareTo(Term that) {
        if (this.query.compareTo(that.query) > 0) return 1;
        else if (this.query.compareTo(that.query) == 0) return 0;
        else if (this.query.compareTo(that.query) < 0) return -1;
        else return -999;
    }

    // Returns a string representation of this term in the following format:
    // the weight, followed by a tab, followed by the query.
    public String toString() {
        return String.valueOf(this.weight) + '\t' + this.query;
    }

    // Unit testing (you should have some Unit Testing here to confirm that your methods work); for example...
    public static void main(String[] args) {
        Term[] terms = new Term[5];
        terms[0] = new Term("Trevor", 45);
        terms[1] = new Term("Kathy", 43);
        terms[2] = new Term("Ellie", 11);
        terms[3] = new Term("Allen", 9);
        terms[4] = new Term("Eva", 1);
        
        Arrays.sort(terms);
        for (Term t : terms) {
            StdOut.println(t);
        }
        
        StdOut.println("");
        Arrays.sort(terms, Term.byReverseWeightOrder());
        for (Term t : terms) {
            StdOut.println(t);
        }
        
        StdOut.println("");
        Arrays.sort(terms, Term.byPrefixOrder(2));
        for (Term t : terms) {
            StdOut.println(t);
        }        
    }
}
