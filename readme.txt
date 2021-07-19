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
