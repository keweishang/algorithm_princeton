1. Question: __Merging with smaller auxiliary array.__ Suppose that the subarray 𝚊[𝟶] to 𝚊[𝚗−𝟷] is sorted and the subarray 𝚊[𝚗] to 𝚊[𝟸∗𝚗−𝟷] is sorted. How can you merge the two subarrays so that 𝚊[𝟶] to 𝚊[𝟸∗𝚗−𝟷] is sorted using an auxiliary array of length n (instead of 2n)?

   Answer:

   Copy the left subarray of n to auxiliary array and merge the right subarray and auxiliary array into the original array.

2. Question: __Counting inversions.__ An inversion in an array a[] is a pair of entries a[i] and a[j] such that i<j but a[i]>a[j]. Given an array, design a linearithmic algorithm to count the number of inversions.

   Answer:

   Mergesort the array, whenever there is an exchange, the count gets incremented.

3. Question: __Shuffling a linked list.__ Given a singly-linked list containing n items, rearrange the items uniformly at random. Your algorithm should consume a logarithmic (or constant) amount of extra memory and run in time proportional to nlogn in the worst case.

   Answer:

   Use a variation of mergesort. Note that it's a linked list, to find the mid-point, we need O(N). Then we divide the linked list into two linked lists, shuffle the two sub lists, and merge the two sub lists, which takes O(N). When merging two sub lists, flip a coin, if it's head, choose an item from the left list, otherwise choose an item from the right list. Since we use linked list, we don't need extra auxiliary array to merge two lists.