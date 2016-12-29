1. Question: __Red–black BST with no extra memory.__ Describe how to save the memory for storing the color information when implementing a red–black BST.

   Answer: Maybe we could use only one instance of BitSet and use key to find color, if the key is integer.

2. Question: Generalized queue. Design a generalized queue data type that supports all of the following operations in logarithmic time (or better) in the worst case.

  - Create an empty data structure.
  - Append an item to the end of the queue.
  - Remove an item from the front of the queue.
  - Return the ith item in the queue.
  - Remove the ith item from the queue.

   Answer:

   We could assign a incremental ID to each item we insert in the queue. We could use a Black-red tree as the data-structure underneath. The key of each node is the generated incremental ID and the value of each note is the item associated with the ID. The problem becomes inserting, removing,  searching the ith smallest item. The runtime is guaranteed to be logarithmic for Black-red trees.