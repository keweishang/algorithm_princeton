# Summary
* Binary heap
  Array representation of a heap-ordered complete binary tree.

* Heap-ordered binary tree
  * Keys in nodes.
  * Parent's key no smaller than children's keys.

* Array representation.
  * Indices start at 1 (for less computation).
  * Take nodes in __level__ order.
  * No explicit links needed.
  
  [![binary-heap][img-1]][img-1]

* Promotion in a heap: child's key becomes __larger__ key than its parent's key.
  ```
  private void swim(int k) {
    while (k > 1 && less(k/2, k)) {  // k is not root and parent is smaller
      exch(k, k/2);
      k = k/2;
    }
  }
  ```
  Insertion in a heap: add node at end, then swim it up. Cost is O(lgN) compares.

* Demotion in a heap: parent's key becomes __smaller__ than one (or both) of its children's. To eliminate the violation, we sink the parent:
  * Exchange key in parent with key in larger child.
  * Repeat until heap order restored.
  ```
  private void sink(int k) {
    while (2*k <= N) { // k has child
      int j = 2*k;
      if (j < N && less(j, j+1)) j++; // update j to the index of larger child
      if (!less(k, j)) break; // we are done
      exch(k, j);
      k = j;
    }
  }
  ```
  Delete the max in a heap: exchange root with node at end, then sink it down. Cost is O(lgN) compares.

* Heapsort
  * 1st pass: build max-heap using bottom-up method. Time ~ O(N), a bit mathmetical to prove.
  ```
  for(int k = N/2; k >= 1; k--)
    sink(a, k, N);
  ```
  * 2nd pass: Remove the max, one at a time; Leave in array, instead of nulling out. Time ~ O(NlgN), since each sink() takes lgN time.
  ```
  while (N > 1) {
    exch(a, 1, N--);
    sink(a, 1, N);
  }
  ```
  * __Mergesort vs Quicksort vs Heapsort__
  
  Sort | In-place (Space) | Time | Stable
  --- | --- | --- | ---
  Mergesort | no (linear extra space) | guaranteed NlgN | yes
  Quicksort | yes | quadratic time in worst case | no
  Heapsort | yes | guaranteed NlgN | no

* Caveat: sink and swim don't have to change the implementation even when we use array in 0-indexed fashion. But we have to change less() and exch by "off-by-one" in 0-indexed case:
```
private static boolean less(Comparable[] pq, int i, int j) {
  return pq[i-1].compareTo(pq[j-1]) < 0;
}

private static void exch(Object[] pq, int i, int j) {
  Object swap = pq[i-1];
  pq[i-1] = pq[j-1];
  pq[j-1] = swap;
}
```

* Equals design
  * "Standard" recipe
    1. Optimization for reference equality. `if (y == this) return true`
    2. Check against null. `if (y == null) return false`
    3. Check that two objects are of the same type and cast.
    4. Compare each significant field:
      - if field is a primitive type, use ==
      - if field is an object, use equals()
      - if field is an array, apply to each entry, alternatively, use `Arrays.equals(a, b)` or `Arrays.deepEqual(a, b)`, but not `a.equals(b)`
  * Best practices
    - No need to use calculated fields that depend on other fields.
    - Compare fields mostly likely to differ first.
    - Make compareTo() consistent with equals().

* Ordered Symbol Table runtime
[![ordered-symbol-table][img-2]][img-2]

Assignments:

* Solve 8-puzzle problem using priority queue.
* Spec: http://coursera.cs.princeton.edu/algs4/assignments/8puzzle.html
* Checklists: http://coursera.cs.princeton.edu/algs4/checklists/8puzzle.html

[1]: https://www.coursera.org/learn/algorithms-part1/lecture/xAltF/sorting-complexity
[img-1]: coursera_resource/binary-heap.png
[img-2]: coursera_resource/ordered-symbol-table.png