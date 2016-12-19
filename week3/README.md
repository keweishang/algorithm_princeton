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