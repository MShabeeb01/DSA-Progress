import java.util.TreeMap;

public class TreeMapBasics {

    public static void main(String[] args) {
        // Instantiation syntax:
        // TreeMap<K, V> tm = new TreeMap<>();
        TreeMap<String, Integer> tm = new TreeMap<>();[cite: 21]

        // put() -> O(log n)[cite: 21]
        tm.put("India", 100);
        tm.put("China", 150);
        tm.put("US", 50);
        tm.put("Indonesia", 6);
        tm.put("Nepal", 5);

        // Keys are automatically sorted in natural alphabetical/ascending order[cite: 21]
        System.out.println("TreeMap Output (Sorted by Key): " + tm);
        // Expected: {China=150, India=100, Indonesia=6, Nepal=5, US=50}

        // get() -> O(log n)[cite: 21]
        System.out.println("Population of India: " + tm.get("India")); // 100[cite: 21]
        System.out.println("Population of Brazil: " + tm.get("Brazil")); // null

        // containsKey() -> O(log n)
        System.out.println("Contains 'China'? " + tm.containsKey("China")); // true

        // remove() -> O(log n)[cite: 21]
        Integer removedVal = tm.remove("China");[cite: 21]
        System.out.println("Removed China with value: " + removedVal); // 150
        System.out.println("After Removal: " + tm);
        // Expected: {India=100, Indonesia=6, Nepal=5, US=50}

        // NavigableMap / SortedMap special features:
        System.out.println("First Key: " + tm.firstKey()); // China (or India after removal)
        System.out.println("Last Key:  " + tm.lastKey());  // US
    }
}

/*
==================== SUMMARY ====================

Topic: Chapter 38 — TreeMap[cite: 21]

Definition & Key Property:
- `TreeMap` is a Red-Black Tree (Self-Balancing Binary Search Tree) based implementation of Java's `NavigableMap` / `SortedMap` interfaces.
- **Core Feature:** Keys are always **sorted** in their natural ascending order (or by a custom `Comparator` provided at construction)[cite: 21].
- In contrast to `HashMap` and `LinkedHashMap`, `TreeMap` does NOT use hashing or bucket arrays.

Why Operations are O(log n)[cite: 21]:
- `HashMap` and `LinkedHashMap` provide average $O(1)$ operations via direct bucket hashing.
- `TreeMap` stores elements as nodes within a self-balancing Red-Black BST.
- Every operation (`put`, `get`, `remove`, `containsKey`) traverses down the height of the tree, which is strictly bounded by $O(\log n)$[cite: 21].

-------------------------------------------------

Internal Red-Black Tree Architecture Visual

Data Inserted: ("India", 100), ("China", 150), ("US", 50), ("Indonesia", 6), ("Nepal", 5)

Conceptual Balanced BST Structure:
                   India (100)
                  /           \
           China (150)         US (50)
                \             /
           Indonesia (6)   Nepal (5)

Inorder Traversal (Sorted Output Order):
  "China" -> "India" -> "Indonesia" -> "Nepal" -> "US"[cite: 21]

-------------------------------------------------

Map Implementations Comparison Table

-------------------------------------------------------------------------------------------------------------------------
Feature                  | HashMap                    | LinkedHashMap              | TreeMap[cite: 21]
-------------------------------------------------------------------------------------------------------------------------
Ordering                 | Unordered                  | Insertion Ordered          | Sorted by Keys (Ascending)[cite: 21]
Underlying Structure     | Array of Buckets (Hash)    | Hash Table + Doubly-Linked | Red-Black Tree (Self-Balancing BST)
put(k, v) Time           | O(1) average               | O(1) average               | O(log n)[cite: 21]
get(k) Time              | O(1) average               | O(1) average               | O(log n)[cite: 21]
remove(k) Time           | O(1) average               | O(1) average               | O(log n)[cite: 21]
Null Keys Permitted?     | Yes (1 null key)           | Yes (1 null key)           | No (Throws NullPointerException)
Null Values Permitted?   | Yes                        | Yes                        | Yes
Best Use Case            | General fastest lookups    | LRU caches / timeline data | Range queries / ordered data
-------------------------------------------------------------------------------------------------------------------------

Complexity Analysis:
- `put(k, v)`     : O(log n) — Traverses tree depth, performs color flips and tree rotations[cite: 21].
- `get(k)`        : O(log n) — Standard BST binary search from root[cite: 21].
- `remove(k)`     : O(log n) — BST node removal followed by re-balancing rotations[cite: 21].
- `containsKey(k)`: O(log n) — Traversal bounded by height.
- Space Complexity: O(n) — Node storage for $n$ elements (stores left, right, parent, and color metadata per node).
=================================================
*/
