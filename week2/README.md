# Summary
* Stack implementation (Array and Linked List)
  * Resizing array : double the capacity or half the capacity dynamically
* Queue implementation (Array and Linked List)
  * Resizing array : double the capacity or half the capacity dynamically
* Iterator implementation (usually an inner class of the collection (Iterable) class)
  * A new iterator instance is created every time iterator() is called upon collection instance
* Selection Sort
  * Algorithm
    * In iteration `i`, find index `min` of smallest remaining entry.
    * Swap `a[i]` and `a[min]`
  * Time complexity = O(N^2). 
  * Running time insensitive to input. Quadartic time, even if input is sorted.
  * Data movement is minimal. Linear number of exchanges.
* Insertion Sort (on average 2 times faster than Selection Sort)
  * Algorithm
    * In iteration `i`, swap a[i] with each large entry to its left.
  * Time complexity = O(N^2).
  * Running time sensitive to input. Best case N-1 compares and 0 exchanges, better than Selection Sort. Worst case ~1/2 N^2 compares and ~1/2 N^2 exchanges, worse than Selection Sort.
  * An __inversion__ is a pair of keys that are out of order. A E E L M O T R X P S has 6 inverions: T-R T-P T-S R-P X-P X-S. An array is __partially sorted__ if the number of inversion is ≤ c N. Ex. an array of size N with only 10 entries out of place.
  * For __partially sorted__ arrays, insertion sort runs in linear time. Because the number of exchanges equals to the number of inversions.
* Knuth Shuffle
  * Algorithm
    * in iteration `i`, pick integer `r` between `0` and `i` uniformly at random.
    * Swap `a[i]` and `a[j]`
  * Time complexity = O(N)

Assignments:
* Implement a Deque
* Implement a RandomizedQueue (a bag)
* Spec: http://coursera.cs.princeton.edu/algs4/assignments/queues.html
* Checklists: http://coursera.cs.princeton.edu/algs4/checklists/queues.html

