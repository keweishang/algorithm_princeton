# Summary
* How to solve recurrence when analysing time complexity?
  ex. __Proposition__. D(N) = 2D(N/2) + N, for N > 1, with D(1) = 0, then D(N) = Nlg(N), where N is the number of compares when mergesorting an array of size N.
  
  * The easier way is the draw a tree graph. Every parent has two children. Each level's cost is N + next level's cost, there are in total lg(N) levels, so D(N) = Nlg(N).
  
  * A mathmatical way to prove the proposition:
  ```
  D(N)   = 2D(N/2) + N
  D(N)/N = 2D(N/2)/N + 1
         = D(N/2)/(N/2) + 1
         = D(N/4)/(N/4) + 1 + 1
         ...
         = D(N/N)/(N/N) + 1 + 1 + ... + 1
         = lg N
  ```

* Mergesort
  * Time complexity: O(Nlg(N)), where N is the size of array
  * Space complexity: O(N), need an auxiliary array of size N
  * Practical improvements:
    * __Use insertion sort for small subarrays__. Becuase there would be too much overhead with the recursive calls to get that done efficiently. Cutoff to insertion sort for ≤ 7 items. It should be 20% faster with insertion sort.
    * __Stop if already sorted__. Is biggest item in first half ≤ smallest item in second half? Helps for partially-ordered arrays. [A B C][D E F], C ≤ D, so we don't have to merge [A B C][D E F].

* Complexity analysis
  * Model of computation: Allowable operations.
  * Cost model: Operation count(s).
  * Upper bound: Cost guarantee provided by __some__ algorithm for problem X. Worst case.
  * Lower bound: Proven limit on cost guarantee of __all__ algorithms for problem X. Best conceivable complexity based on theory.
  * Optimal algorithm: Algorithm with best possible cost guarantee for problem X. The best upper bound, which upper bound equals to lower bound.

* Complexity analysis example: sorting.
  * Model of computation: decision tree.
  * Cost model: $ compares.
  * Upper bound: ~ NlgN from mergesort.
  * Lower bound: ~ NlgN. (see proof [here][1]).
  * Optimal algorithm = mergesort.

* First goal of algorithm design: optimal algorithms.

* Complexity results in context
  * Compares? Mergesort __is__ optimal with respect to # compares.
  * Space? Mergesort __is not__ optimal with respect to space usage.
  * __Lessons__. Use theory as a guide.
    * Ex. Don't try to design sorting algorithm that guarantees 1/2 NlgN compares, because the lower bound says no. 
    * Ex. Design sorting algorithm that is both time and space optimal
  * Lower bound may not hold if the algorithm has information about:
    * The initial order of the input. Maybe it's already __Partially-ordered__ and inserting sort requires only linear time in this case.
    * The distribution of key values. __Dupliacte keys__: depending on the input distribution of duplicates, we may not need NlgN compares (3-way quicksort).
    * The representation of the keys. __Digital properties of keys__: we can use digit/character compares instead of key compares for numbers and strings (radix sorts).

* Stability

  A typical application. Given a list of students, first, sort by name; then sort by section. We want students to be sorted by name within the same section after sorted by section.

  Not all sorts preserve __stability__.

  A __stable__ sort preserves the relative order of items with equal keys.

  Q. Which sorts are stable?

  A. Insertion sort and mergesort (but not selection sort and quicksort).
    * __Proof. Check whether there is a long-distance exchange might move an item past some equal item.__
    * Insertion sort is stable because we never move equal items pass one another.
    * Selection sort is not stable because for [b1, b2, a] where b1 = b2, and a < b1, if we start at b1, we will exchange b1 and a, so we move an item past equal item.
    * Mergesort is stable depending on how we merge, if we take only the left element when two elements are equal, we preserve the order, thus it's stable.
    * Quicksort is not stable.

  Note. Need to carefully check code ("less than" vs "less than or equal to").

[1]: https://www.coursera.org/learn/algorithms-part1/lecture/xAltF/sorting-complexity