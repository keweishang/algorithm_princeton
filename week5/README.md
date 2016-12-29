# Summary

* 2-3 search tree

  Contains 2 types of nodes:
    1. 2-node: has 1 key and 2 links to left and right subtree.
    2. 3-node: has 2 key and 3 links to left, middle and right subtree. All nodes' keys in middle subtree are larger than key #1 but smaller than key #2.
  
  Properties:
    1. Symmetric order. Inorder traversal yields keys in ascending order.
    2. Perfect balance. Every path from root to null link has same length. 
  
  ![2-3 search tree][2-3 search tree]

  Guaranteed lgN search() and insert() operation.

* Red-black BST

  LLRB (Left-leaning red-black BST)
    - Represent 2-3 search tree as a BST.
    - Use "internal" left-learning links as "glue" fro 3-nodes.
    ![LLRB corres 2-3][LLRB corres 2-3]

  Properties. a BST such that:
    - No node has two red links connected to it.
    - Every path from root to null link has the same number of black links.
    - Red links lean left.
    - To mark the red linke, we encode color of links in nodes. A node is red when "the color of the link pointing to the node is red".

  Elementary red-black BST operations
    - Left rotation. Orient a (temporarily) right-leaning red link to lean left. __Invariants__: Maintains symmetric order and perfect black balance.
    - Right rotation. Orient a left-leaning red link to (temporarily) lean right. __Invariants__: Maintains symmetric order and perfect black balance.
    - Color flip. Recolor to split a (temporary) 4-node.

* B-tree
  
  A generalisation of Red-black tree (which is also a 2-3 tree).
    - At least 2 key-link pairs at root.
    - At least M/2 key-link pairs in other nodes.
    - External nodes (leaf nodes) contain client keys.
    - Internal nodes contain copies of keys to guide search.

  A search or an insertion in a B-tree of order M with N keys requires between log M-1 N and log M/2 N probes.

* 2d-tree
   
   A BST, in which each node's key is a point in a 2D plane. The node divides the plane into 2 half-planes horizontally (for nodes at even level) or vertically (for odd level).

[2-3 search tree]: coursera_resource/2-3-search-tree.png
[LLRB corres 2-3]: coursera_resource/LLRB-corres-2-3.png
