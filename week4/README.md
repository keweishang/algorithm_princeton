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

[1]: https://www.coursera.org/learn/algorithms-part1/lecture/xAltF/sorting-complexity
[img-1]: coursera_resource/binary-heap.png