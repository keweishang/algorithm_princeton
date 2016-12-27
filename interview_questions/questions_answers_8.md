1. Question: __Java autoboxing and equals().__ Consider two 𝚍𝚘𝚞𝚋𝚕𝚎 values 𝚊 and 𝚋 and their corresponding <tt>Double</tt> values 𝚡 and 𝚢.

   * Find values such that (𝚊==𝚋) is 𝚝𝚛𝚞𝚎 but 𝚡.𝚎𝚚𝚞𝚊𝚕𝚜(𝚢) is 𝚏𝚊𝚕𝚜𝚎.
   * Find values such that (𝚊==𝚋) is 𝚏𝚊𝚕𝚜𝚎 but 𝚡.𝚎𝚚𝚞𝚊𝚕𝚜(𝚢) is 𝚝𝚛𝚞𝚎.

   Answer: I cannot find such values.

2. Question: __Check if a binary tree is a BST.__ Given a binary tree where each 𝙽𝚘𝚍𝚎 contains a key, determine whether it is a binary search tree. Use extra space proportional to the height of the tree.

   Answer:

   ```
   public static boolean isBST(Node root) {
      if (root == null) return false;
         int min = Integer.MIN_VALUE, max = Integer.MAX_VALUE;
         return isBST(root, min, max);
   }

   private static boolean isBST(Node root, int min, int max) {
      if (root == null) return true;
      boolean rootInRange = root.value > min && root.value < max;
      return rootInRange && isBST(root.left, min, root.value) && isBST(root.right, root.value, max);
   }
   ```

3. Question: __Inorder traversal with constant extra space.__ Design an algorithm to perform an inorder traversal of a binary search tree using only a constant amount of extra space.

   Answer: Use [morris-traversal]. It modifies the binary tree back when it is done.

4. Question: __Web tracking.__ Suppose that you are tracking n web sites and m users and you want to support the following API:

   * User visits a website.
   * How many times has a given user visited a given site?
   
   What data structure or data structures would you use?

   Answer: Use a BST and use tuple of (user + website) as key, would take lg(mn) to insert and update the count. Use a hash table and use tuple of (user + website) as key can take constant time to insert and update count.


[morris-traversal]: http://www.geeksforgeeks.org/inorder-tree-traversal-without-recursion-and-without-stack/