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

public class BinarySearchDeluxe{

    // Returns the index of the first key in a[] that equals the search key, or -1 if no such key.
    public static <Key> int firstIndexOf(Key[] a, Key key, Comparator<Key> comparator) {
        // First sort the array passed to this function
        // Autocomplete constructor also sorts array, but lexicographically
        // Allow for a sort based upon choice comparator passed as parameter
        Arrays.sort(a, comparator);

        // Set up for modified binary search
        int lo = 0;
        int hi = a.length - 1;
        int found = -1;

        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;
            int cmp = comparator.compare(key, a[mid]);
            // If key found, continue checking to the left to find first occurrence of key
            if (cmp == 0) {
                found = mid;
                hi = mid - 1;
            }
            else if (cmp < 0) hi = mid - 1;
            else if (cmp > 0) lo = mid + 1;
        }
        return found;
    }

    
    // Returns the index of the last key in a[] that equals the search key, or -1 if no such key.
    public static <Key> int lastIndexOf(Key[] a, Key key, Comparator<Key> comparator) {
        // first sort the array passed to this function using chosen comparator
        Arrays.sort(a, comparator);

        // Set up for modified binary search
        int lo = 0;
        int hi = a.length - 1;
        int found = -1;

        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;
            int cmp = comparator.compare(key, a[mid]);
            // If key found, continue checking to the right to find the last occurrence of that key
            if (cmp == 0) {
                found = mid;
                lo = mid + 1;
            }
            else if (cmp < 0) hi = mid - 1;
            else if (cmp > 0) lo = mid + 1;
        }
        return found;
    }

    // unit testing
    public static void main(String[] args) {
    
        Term[] terms = new Term[5];
        terms[0] = new Term("Trevor", 45);
        terms[1] = new Term("Kathy", 43);
        terms[2] = new Term("Ellie", 11);
        terms[3] = new Term("Allen", 9);
        terms[4] = new Term("Eva", 1);
        Arrays.sort(terms);
        
        Term searchme = new Term("J",0);
        int first = BinarySearchDeluxe.firstIndexOf(terms, searchme, Term.byPrefixOrder(1));
        int last = BinarySearchDeluxe.lastIndexOf(terms, searchme, Term.byPrefixOrder(1));
        StdOut.println("J: " + first + " to " + last);

        searchme = new Term("A",0);
        first = BinarySearchDeluxe.firstIndexOf(terms, searchme, Term.byPrefixOrder(1));
        last = BinarySearchDeluxe.lastIndexOf(terms, searchme, Term.byPrefixOrder(1));
        StdOut.println("A: " + first + " to " + last);
       
        searchme = new Term("E",0);
        first = BinarySearchDeluxe.firstIndexOf(terms, searchme, Term.byPrefixOrder(1));
        last = BinarySearchDeluxe.lastIndexOf(terms, searchme, Term.byPrefixOrder(1));
        StdOut.println("E: " + first + " to " + last);
        
        searchme = new Term("T",0);
        first = BinarySearchDeluxe.firstIndexOf(terms, searchme, Term.byPrefixOrder(1));
        last = BinarySearchDeluxe.lastIndexOf(terms, searchme, Term.byPrefixOrder(1));
        StdOut.println("T: " + first + " to " + last);        
    }
}
