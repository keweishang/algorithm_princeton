1. Question: **Queue with two stacks**. Implement a queue with two stacks so that each queue operations takes a constant amortized number of stack operations.

  Answer:

  One stack is used for enqueue item, another stack is used for dequeue item. When the dequeue stack is empty and dequeue operation is called, dequeue stack gets elements from enqueue stack.
  * Dequeue operation: time complexity = O(1) amortized
  * Enqueue operation: time complexity = O(1)

2. Question: **Stack with max**. Create a data structure that efficiently supports the stack operations (push and pop) and also a return-the-maximum operation. Assume the elements are reals numbers so that you can compare them.

	Answer:

	Using 2 stacks, one stores the numbers, another stores the max-so-far.
	* Push operation: time complexity = O(1)
	* GetMax operation: time complexity = O(1)
	* Data structure space complexity: twice as normal stack, but still O(N)