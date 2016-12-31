# Summary

* Hash Table

  If the key is simple object (hashcode is easy to computer and takes constant time), __Hash Table__ is a good implementation of __Symbol Table__'s core functions : search() and insert() take constant time.

  __Hash code.__ An int between -2^31 and 2^31 - 1.

  __Hash function.__ An int between O and M-1 (for use as array index), where M is the size of the array.

  There are 2 popular implementations of hash table's collusion resolution:

  1. Seperate Chaining

  Each index of the table stores a linked list of key-value pairs. If M is the size of the table (array), N is the number of keys. Some optimal ratio that allows constant time search and insert would be M ~ N/5.
  
  2. Linear Probing (a.k.a. Open Addressing)

  When a new key collides, find next empty slot, and put it there.

  __Hash.__ Map key to integer i between 0 and M-1.
  __Insert.__ Put at table index i if free; if not try i+1, i+2, etc. (A slight improved version to effectively avoid clustering is double-hashing, which hashes the index again instead of i+1)
  __Search.__ Search the table index i; if occupied but no match, try i+1, i+2, etc.

    - Note. Array size M __must be greater__ than number of key-value pairs N.
    - Analysis
      - Half-full. With N = M/2, mean # of probing is ~ 3/2 (constant).
      - Full. With N = M, mean # of probing is ~ M^(1/2).

  If the cost of hash function is too much, we could even consider using Red-Black tree instead of Hash Table. As Red-Black tree guarantees the performance.

* Diversion

  __One-way hash function.__ "Hard" to find a key that will hash to a desired value (or two keys that hash to same value).

    * Ex. MD4, MD5, SHA-1 (all known to be insecure), SHA-2 (secure)
    * Application. Digital fingerprint, storing password.
    * Caveat. Too expensive for use in ST (Symbol Table) implementations.

* Hash table vs. Balanced search tree

  * Hash tables
    - Simpler to code.
    - No effective alternative for unordered keys.
    - Faster for simple keys (a few arithmetic ops vs lgN compares).
    - Better system support in Java for strings (eg. cached hash code).

  * Balanced search trees.
    - Stronger performance guarantee.
    - Support for ordered ST operations.
    - Easier to implement compareTo() correctly than equals() and hashCode().

  * Java system includes both.
    - Red-black BSTs: TreeMap, TreeSet
    - Hash tables: HashMap, HashSet