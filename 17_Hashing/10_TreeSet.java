import java.util.TreeSet;

public class TreeSetBasics {

    public static void main(String[] args) {
        // Instantiation of TreeSet
        TreeSet<String> cities = new TreeSet<>();[cite: 20]

        // add() -> O(log n)
        cities.add("Delhi");
        cities.add("Mumbai");
        cities.add("Noida");
        cities.add("Bengaluru");
        cities.add("Delhi"); // Duplicate element -> Ignored

        // Elements are automatically sorted in natural ascending (alphabetical) order[cite: 20]
        System.out.println("TreeSet (Sorted Order): " + cities);
        // Expected Output: [Bengaluru, Delhi, Mumbai, Noida]

        // NULL values are NOT allowed (Throws NullPointerException if attempted)[cite: 20]
        try {
            cities.add(null);[cite: 20]
        } catch (NullPointerException e) {
            System.out.println("Exception caught: NULL values are NOT allowed in TreeSet");[cite: 20]
        }

        // contains() -> O(log n)
        System.out.println("Contains 'Noida'? " + cities.contains("Noida")); // true

        // remove() -> O(log n)
        cities.remove("Delhi");
        System.out.println("After removing 'Delhi': " + cities);
        // Expected Output: [Bengaluru, Mumbai, Noida]

        // NavigableSet / SortedSet operations:
        System.out.println("First (Lowest) Element: " + cities.first()); // Bengaluru
        System.out.println("Last (Highest) Element: " + cities.last());  // Noida
    }
}

/*
==================== SUMMARY ====================

Topic: Chapter 38 — TreeSet[cite: 20]

Definition & Key Properties:
- `TreeSet` is an implementation of the `NavigableSet` / `SortedSet` interfaces backed internally by a `TreeMap` (Red-Black Tree).
- **Sorted in Ascending Order**: Elements are automatically arranged in natural ascending sequence (or via a custom `Comparator`)[cite: 20].
- **No Duplicates**: Enforces unique mathematical set behavior.
- **NULL Values Are NOT Allowed**: Inserting `null` triggers a `NullPointerException` because elements must be compared with existing nodes using `compareTo()`[cite: 20].

Under the Hood:
- Just as `HashSet` is backed by `HashMap`, `TreeSet` is backed by `TreeMap`.
- Each element added to the set becomes a key inside the underlying Red-Black Tree, mapped to a static dummy object.
- Because it is a balanced binary search tree, all core search, insertion, and deletion operations take $O(\log n)$ time.

-------------------------------------------------

Internal Red-Black Tree Structure Visual

Insertion: "Delhi", "Mumbai", "Noida", "Bengaluru"

Conceptual Balanced BST:
                   Delhi
                  /     \
         Bengaluru       Mumbai
                               \
                                Noida

Inorder Traversal (Sorted Output Order):
  "Bengaluru" -> "Delhi" -> "Mumbai" -> "Noida"[cite: 20]

-------------------------------------------------

Set Implementations Complete Comparison Table

---------------------------------------------------------------------------------------------------------
Feature                  | HashSet[cite: 20]       | LinkedHashSet[cite: 20]   | TreeSet[cite: 20]
---------------------------------------------------------------------------------------------------------
Ordering                 | Unordered[cite: 20]     | Insertion Ordered[cite: 20]| Sorted in Ascending Order[cite: 20]
Underlying Architecture  | HashMap[cite: 20]       | LinkedHashMap (DLL)[cite: 20]| TreeMap (Red-Black Tree)
Operation Time (add/rem) | O(1) average             | O(1) average               | O(log n)
Null Value Permitted?    | Yes (at most 1)[cite: 20]| Yes (at most 1)           | NO (NullPointerException)[cite: 20]
Interface Hierarchy      | Set                      | Set                        | NavigableSet / SortedSet
Best Use Case            | Fast membership testing  | Ordered unique history     | Sorted unique elements / Range searches
---------------------------------------------------------------------------------------------------------

Complexity Analysis:
- `add(e)`        : O(log n) — Red-Black tree insertion and balancing.
- `contains(e)`   : O(log n) — Binary search down tree height.
- `remove(e)`     : O(log n) — Tree node deletion and rotation.
- Space Complexity: O(n) — Node storage tracking left, right, parent, and node color.
=================================================
*/
