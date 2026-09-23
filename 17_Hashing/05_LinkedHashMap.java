import java.util.HashMap;
import java.util.LinkedHashMap;

public class LinkedHashMapBasics {

    public static void main(String[] args) {
        // ==========================================
        // 1. Regular HashMap (Unordered)
        // ==========================================
        HashMap<String, Integer> hm = new HashMap<>();
        hm.put("India", 100);
        hm.put("China", 150);
        hm.put("US", 50);
        hm.put("Nepal", 5);

        // Insertion order is NOT guaranteed to be preserved
        System.out.println("HashMap Output (Unordered):       " + hm);

        // ==========================================
        // 2. LinkedHashMap (Insertion Ordered)
        // ==========================================
        // Instantiation syntax:
        // LinkedHashMap<K, V> lhm = new LinkedHashMap<>();
        LinkedHashMap<String, Integer> lhm = new LinkedHashMap<>();[cite: 20]
        lhm.put("India", 100);
        lhm.put("China", 150);
        lhm.put("US", 50);
        lhm.put("Nepal", 5);

        // Output strictly follows the order in which keys were inserted[cite: 20]
        System.out.println("LinkedHashMap Output (Insertion): " + lhm);
        // Expected: {India=100, China=150, US=50, Nepal=5}

        // ==========================================
        // Core Operations (Identical syntax and O(1) performance)
        // ==========================================
        System.out.println("Get 'India': " + lhm.get("India"));         // 100
        System.out.println("Contains 'US'? " + lhm.containsKey("US"));  // true
        System.out.println("Remove 'China': " + lhm.remove("China"));   // 150
        System.out.println("After Removal: " + lhm);                    // {India=100, US=50, Nepal=5}
    }
}

/*
==================== SUMMARY ====================

Topic: Chapter 38 — LinkedHashMap[cite: 20]

Definition & Key Property:
- `LinkedHashMap` is a subclass of `HashMap` that maintains a doubly-linked list running 
  through all of its entries[cite: 20].
- **Core Feature:** Keys are **insertion ordered** — iterations occur in the exact sequence 
  the keys were inserted into the map[cite: 20].

Internal Data Structure:
- Standard `HashMap`:
  - An array of buckets (LinkedList / Red-Black Tree)[cite: 19].
  - Pointers only connect nodes inside the same bucket (collision chain)[cite: 19].
- `LinkedHashMap`:
  - Retains the exact same hash table / bucket array architecture for $O(1)$ operations[cite: 19].
  - **Additionally:** Every Entry node contains two extra reference pointers:
      1. `before`: points to the previous entry in insertion sequence.
      2. `after`: points to the next entry in insertion sequence.
  - A global `head` and `tail` track the chronological timeline of entries.

-------------------------------------------------

Underlying Architecture Visual

Entry Node Layout:
  +--------+-----+-------+--------+
  | before | key | value | after  |
  +--------+-----+-------+--------+

Linked Timeline (Doubly Linked List across buckets):
  [Head] -> ("India", 100) <===> ("China", 150) <===> ("US", 50) <===> ("Nepal", 5) <- [Tail]
                 |                      |                    |                 |
            (Hashed to             (Hashed to           (Hashed to        (Hashed to
             Bucket 2)              Bucket 0)            Bucket 3)         Bucket 2)

When iterating, LinkedHashMap simply traverses from `head` to `tail` along the `after` pointers,
guaranteeing insertion order without visiting empty bucket slots[cite: 20].

-------------------------------------------------

HashMap vs. LinkedHashMap Comparison Table

---------------------------------------------------------------------------------------------------------
Feature                  | HashMap                           | LinkedHashMap[cite: 20]
---------------------------------------------------------------------------------------------------------
Ordering                 | Unordered                         | Insertion Ordered (or Access Ordered)[cite: 20]
Underlying Architecture  | Array + LinkedList / Red-Black Tree | Array + Doubly Linked List running across entries
Node Extra Memory        | key, value, next, hash            | key, value, next, hash + before, after pointers
`put()` Performance      | O(1) average                      | O(1) average (slightly higher constant factor)
`get()` Performance      | O(1) average                      | O(1) average
Iteration Speed          | Proportional to Capacity + Size   | Proportional to Size only (follows linked list)
Common Use Cases         | General-purpose fast caching      | LRU Caches, preserving chronological feeds
---------------------------------------------------------------------------------------------------------

Complexity Analysis:
- `put(k, v)`     : O(1) average (computes hash, updates bucket, and links to tail)
- `get(k)`        : O(1) average
- `remove(k)`     : O(1) average (unlinks node from bucket and doubly linked list)
- `containsKey(k)`: O(1) average
- Space Complexity: O(n) auxiliary memory (carries a small constant overhead compared to HashMap due to `before` and `after` pointers).
=================================================
*/
