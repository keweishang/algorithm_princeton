1. Question: **Intersection of two sets**. Given two arrays 𝚊[] and 𝚋[], each containing n distinct 2D points in the plane, design a subquadratic algorithm to count the number of points that are contained both in array 𝚊[] and array 𝚋[].

   Answer:
   
   Solution 1. We could iterate through array a and put items in a hash set. Then we iterate through array b, if the hash set contains b[i], we add it to the result. Time complexity = O(N), Space complexity = O(N)

   Solution 2. We could sort array a and b. Then we iterate through a and b simultaneously.
   ```
   If a[i] > b[j]: j++;
   else if a[i] < b[j]: i++;
   else: add a[i] to result; i++;j++;
   ```
   Time complexity = O(N*lg(N)), Space complexity = O(1)

2. Question: **Permutation**. Given two integer arrays of size n, design a subquadratic algorithm to determine whether one is a permutation of the other. That is, do they contain exactly the same entries but, possibly, in a different order.

   Answer:

   Solution 1. Sort two arrays and compare the two sorted arrays. If for any i a[i] ≠ b[i], then two original arrays are not permutation. Time complexity = O(N*lg(N)), Space complexity = O(1), where N is the number of items in array.

   Solution 2. Use 2 hash tables to store the number of occurance of items of array a and b. If the 2 hash tables are equals, then they are permutations. Time complexity = O(N), space complexity = O(N), where N is the number of items in array.

3. Question: **Dutch national flag**. Given an array of n buckets, each containing a red, white, or blue pebble, sort them by color. The allowed operations are:

   * swap(i,j): swap the pebble in bucket i with the pebble in bucket j.
   * color(i): color of pebble in bucket i.
   
   The performance requirements are as follows:
   * At most n calls to color().
   * At most n calls to swap().
   * Constant extra space.

   Solution 1. Bucket sort, but need O(N) space.
   Solution 2. I didn't come with with this solution. http://www.geeksforgeeks.org/sort-an-array-of-0s-1s-and-2s/