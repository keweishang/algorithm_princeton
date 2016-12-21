1. Question: __Nuts and bolts.__ A disorganized carpenter has a mixed pile of n nuts and n bolts. The goal is to find the corresponding pairs of nuts and bolts. Each nut fits exactly one bolt and each bolt fits exactly one nut. By fitting a nut and a bolt together, the carpenter can see which one is bigger (but the carpenter cannot compare two nuts or two bolts directly). Design an algorithm for the problem that uses nlogn compares (probabilistically).
   
   Answer:
   Mergesort (or quicksort) the nuts, it takes nlgn compares. Then sort the bolts, it too takes nlgn compares. When both nuts and bolts are sorted from small to big, they fit one by one. n[i] fits b[i], where n[i] is the i-th nut and b[i] is the i-th bolt.

2. Question: __Selection in two sorted arrays.__ Given two sorted arrays a[] and b[], of sizes n1 and n2, respectively, design an algorithm to find the kth largest key. The order of growth of the worst case running time of your algorithm should be logn, where n=n1+n2.

   Answer:
   ```
   i = k/2
   j = k - i
   step = k/4
   while step > 0
       if a[i-1] > b[j-1]
           i -= step
           j += step
       else
           i += step
           j -= step
       step /= 2   

   if a[i-1] > b[j-1]
       return a[i-1]
   else
       return b[j-1]
   ```

   The invariant is i + j = k. The runtime is O(lgk) ~ O(lgn)

3. Question: __Decimal dominants.__ Given an array with n keys, design an algorithm to find all values that occur more than n/10 times. The expected running time of your algorithm should be linear.

   Answer:
   
   Solution 1. Use a hash table to count the occurence of all keys, and select keys whose occurence is more than n/10. Time complexity is O(n). Space complexity is O(n).

   Solution 2. Use __quickselect__ to find the N/10th largest key - O(n) time; check if it is a dominant - O(n) time; if it is, add key to result, recur in the subarray with 9N/10 keys.