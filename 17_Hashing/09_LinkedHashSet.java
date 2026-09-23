import java.util.HashSet;
import java.util.LinkedHashSet;

public class LinkedHashSetBasics {

    public static void main(String[] args) {
        // ==========================================
        // 1. Standard HashSet (Unordered)
        // ==========================================
        HashSet<String> cities = new HashSet<>();
        cities.add("Delhi");
        cities.add("Mumbai");
        cities.add("Noida");
        cities.add("Bengaluru");

        // Elements do not maintain the insertion order
        System.out.println("HashSet (Unordered):       " + cities);

        // ==========================================
        // 2. LinkedHashSet (Ordered using DLL)[cite: 20]
        // ==========================================
        // Instantiation syntax:
        // LinkedHashSet<E> lhs = new LinkedHashSet<>();
        LinkedHashSet<String> lhs = new LinkedHashSet<>();[cite: 20]
        lhs.add("Delhi");
        lhs.add("Mumbai");
        lhs.add("Noida");
        lhs.add("Bengaluru");
        lhs.add("Delhi"); // Duplicate element -> Ignored

        // Iteration/Print strictly preserves the exact insertion order[cite: 20]
        System.out.println("LinkedHashSet (DLL Order): " + lhs);
        // Expected: [Delhi, Mumbai, Noida, Bengaluru]

        // Core Operations: O(1) average time
        System.out.println("Contains 'Mumbai'? " + lhs.contains("Mumbai")); // true
        lhs.remove("Delhi");
        System.out.println("After removing 'Delhi':   " + lhs);
        // Expected: [Mumbai, Noida, Bengaluru]
    }
}

/*
==================== SUMMARY ====================

Topic: Chapter 38 — LinkedHashSet (Ordered using DLL)[cite: 20]

Definition & Key Characteristics:
- `LinkedHashSet` extends `HashSet` and implements the `Set` interface[cite: 20].
- **Core Property:** Maintains elements in **insertion order** while guaranteeing that **no duplicates** exist[cite: 20].
- How it is ordered: **Ordered using a Doubly Linked List (DLL)** running across all its elements[cite: 20].

Internal Implementation (Under the Hood):
- A standard `HashSet` is backed internally by a `HashMap`[cite: 20].
- A `LinkedHashSet` is backed internally by a `LinkedHashMap`[cite: 20].
  - Every element is inserted into an underlying `LinkedHashMap` as a key, paired with a constant dummy `PRESENT` Object[cite: 20].
  - It maintains the hash table for $O(1)$ fast lookups, while a Doubly Linked List (DLL) tracks the chronological insertion sequence via `before` and `after` pointers[cite: 20].

-------------------------------------------------

Internal Data Architecture Visual

Insertion Sequence:
  "Delhi" -> "Mumbai" -> "Noida" -> "Bengaluru"

Doubly Linked List (Chronological Chain across Buckets)[cite: 20]:
  [HEAD] <==> ["Delhi"] <===> ["Mumbai"] <===> ["Noida"] <===> ["Bengaluru"] <==> [TAIL]
                  |               |               |                |
             (Hashed to      (Hashed to      (Hashed to       (Hashed to
              Bucket 3)       Bucket 0)       Bucket 7)        Bucket 2)

When printing or traversing via Iterator or enhanced for-loop, the traversal walks 
directly along the DLL pointers from HEAD to TAIL, ensuring exact insertion ordering[cite: 20].

-------------------------------------------------

HashSet vs. LinkedHashSet Comparison Table

---------------------------------------------------------------------------------------------------------
Feature                  | HashSet                                 | LinkedHashSet[cite: 20]
---------------------------------------------------------------------------------------------------------
Ordering                 | Unordered (randomized by hash)[cite: 20]    | Insertion Ordered[cite: 20]
Underlying Architecture  | HashMap (Array of Buckets)[cite: 20]        | LinkedHashMap (Hash Table + DLL)[cite: 20]
Performance (Add/Find)   | O(1) average                            | O(1) average (slightly higher constant factor)
Iteration Speed          | Proportional to Capacity + Size         | Proportional to Size only (traverses DLL)
Memory Footprint         | Lower                                   | Slightly higher (extra DLL pointers per node)
Null Elements            | Allowed (at most 1)                     | Allowed (at most 1)
---------------------------------------------------------------------------------------------------------

Complexity Analysis:
- `add(e)`        : O(1) average — Hashes element, inserts into bucket, and appends to the DLL tail.
- `contains(e)`   : O(1) average — Direct bucket lookup via hash code and `.equals()`.
- `remove(e)`     : O(1) average — Unlinks the element from both its bucket and the DLL.
- Space Complexity: O(n) — Stores $n$ elements in the hash table along with two pointer references (`before`, `after`) per entry.
=================================================
*/
