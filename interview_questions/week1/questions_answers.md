1. Question: **Social network connectivity**. Given a social network containing n members and a log file containing m timestamps at which times pairs of members formed friendships, design an algorithm to determine the earliest time at which all members are connected (i.e., every member is a friend of a friend of a friend ... of a friend). Assume that the log file is sorted by timestamp and that friendship is an equivalence relation. The running time of your algorithm should be mlogn or better and use extra space proportional to n.

   Answer:
   
   We could use a weighted union find data structure. We maintain the size of each tree when doing union() operation. Each union() is lg(n) time. We check the size of the tree after each union() operation, if size equals to n, then it means everyone's connected.

2. Question: **Union-find with specific canonical element**. Add a method 𝚏𝚒𝚗𝚍() to the union-find data type so that 𝚏𝚒𝚗𝚍(𝚒) returns the largest element in the connected component containing i. The operations, 𝚞𝚗𝚒𝚘𝚗(), 𝚌𝚘𝚗𝚗𝚎𝚌𝚝𝚎𝚍(), and 𝚏𝚒𝚗𝚍() should all take logarithmic time or better.

   For example, if one of the connected components is {1,2,6,9}, then the 𝚏𝚒𝚗𝚍() method should return 9 for each of the four elements in the connected components.

   Answer:
   
   We can add a int max[] array that represents the largest element in the tree. Whenever two trees got merged, we select the larger one of their max and update the max[] array. Then find(i) returns the root index of i with max[i] in logarithmic time.

3. Question: **Successor with delete**. Given a set of N integers S={0,1,...,N−1} and a sequence of requests of the following form:

   Remove x from S
   Find the successor of x: the smallest y in S such that y≥x.
   design a data type so that all operations (except construction) should take logarithmic time or better.

   Answer:
   
   When removing x from S, if its neighbours x-1 and x+1 were deleted, union them, the successor of x in S is the largest element in the component + 1, we maintain the largest element in the component when do union().
