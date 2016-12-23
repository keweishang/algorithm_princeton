1. Question: __Dynamic median.__ Design a data type that supports insert in logarithmic time, find-the-median in constant time, and remove-the-median in logarithmic time.

   Answer:

   Use 2 heaps: a maxHeap contains smaller half of items, a minHeap contains larger half of items.

   First add 2 first items into heaps. Add smaller one into maxHeap, add larger one into minHeap.

   Then process next item with following steps:

   1. If item is smaller than root of maxHeap, add it to maxHeap, otherwise, add it to minHeap.

   2. Balance the heaps (this this step heaps will be either balanced or one of them will contain 1 more item). If number of items in one of the heap is greater than other heap by more than 1, remove the root of larger heap and add it to other heap.

2. Question: __Randomized priority queue.__ Describe how to add the methods 𝚜𝚊𝚖𝚙𝚕𝚎() and 𝚍𝚎𝚕𝚁𝚊𝚗𝚍𝚘𝚖() to our binary heap implementation. The two methods return a key that is chosen uniformly at random among the remaining keys, with the latter method also removing that key. The 𝚜𝚊𝚖𝚙𝚕𝚎() method should take constant time; the 𝚍𝚎𝚕𝚁𝚊𝚗𝚍𝚘𝚖() method should take logarithmic time. Do not worry about resizing the underlying array.

   Answer:

   Randomly choose index i from 1 to N, where N is the size of the priority queue binary heap implementation. sample()'s value is a[i]. To delRandom(), we can exchange item of a[i] and a[N], then null out a[N], and sink a[i].

3. Question: Taxicab numbers. A taxicab number is an integer that can be expressed as the sum of two cubes of integers in two different ways: a^3+b^3=c^3+d^3. For example, 1729=9^3+10^3=1^3+12^3. Design an algorithm to find all taxicab numbers with a, b, c, and d less than n. Use time proportional to n2logn and space proportional to n.

   Answer:

   Imagine a 2-D matrix m[i][j] = i^3 + j^3. We don't have to create this matrix in memory. Row's item are in ascending order and column's item are in ascending order too. We could use a minHeap (minPQ) to store the diagonal first. Then do following steps until minHeap is empty:

   1. Get current min (minCur) from the minHeap, compare it to the preMin, if they are equal, we find a pair of sums (a^3+b^3=c^3+d^3).
   2. Put the item to the right of minCur in matrix to the minHeap.

   Algorithm works becuase it guarantees that all items in matrix are added and taken out of the minHeap in order. We always take the min item so far from the minHeap and add smallest larger item to the heap for every iteration. The heap contains N items only.
