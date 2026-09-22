import java.util.HashMap;

public class HashMapBasics {

    public static void main(String[] args) {
        // Create a HashMap storing (Key, Value) pairs
        // Keys: String (Country/Item name), Values: Integer (Population/Count)
        HashMap<String, Integer> hm = new HashMap<>();

        // 1. Insert / Put - O(1) average
        hm.put("India", 140);
        hm.put("China", 135);
        hm.put("US", 33);

        System.out.println("Initial Map: " + hm); 
        // Note: HashMaps are unordered; printed sequence may vary

        // Updating a value for an existing key:
        hm.put("India", 142); // Overwrites previous value 140
        System.out.println("After Updating India: " + hm);

        // 2. Get - O(1) average
        int indiaPop = hm.get("India");
        System.out.println("Population of India: " + indiaPop);
        System.out.println("Population of Brazil (non-existent key): " + hm.get("Brazil")); // null

        // 3. ContainsKey - O(1) average
        System.out.println("Contains 'India'? " + hm.containsKey("India"));   // true
        System.out.println("Contains 'Brazil'? " + hm.containsKey("Brazil")); // false

        // 4. Remove - O(1) average
        Integer removedVal = hm.remove("China");
        System.out.println("Removed Value for China: " + removedVal); // 135
        System.out.println("After Removing China: " + hm);

        // 5. Size & IsEmpty - O(1)
        System.out.println("Total Pairs (Size): " + hm.size());
        System.out.println("Is Map Empty? " + hm.isEmpty());
    }
}

/*
==================== SUMMARY ====================

Topic: Chapter 38 — Hashing: Introduction to HashMap[cite: 28]

Definition:
A HashMap is an associative data structure that stores data in key-value pairs `(key, value)`[cite: 28].
It provides near instantaneous data access by computing an internal array index using a 
mathematical hash function on the key.

Core Characteristics:
1. Key Uniqueness:
   - All keys must be unique.
   - Values do not need to be unique (duplicate values are permitted across different keys).
   - If a duplicate key is inserted via `.put(key, val)`, the old value is overwritten.
2. Unordered Collection:
   - Elements do not preserve insertion order, sorted order, or FIFO/LIFO ordering.
   - The storage position is determined entirely by the key's hash code modulo the bucket array length.
3. Null Keys & Values:
   - Allows exactly one `null` key and multiple `null` values.

-------------------------------------------------

Key-Value Mapping Visual Representation

Input data:
  ("India", 142), ("US", 33), ("Nepal", 3)

Logical Structure:
+-------------------+---------------------+
|        KEY        |        VALUE        |
+-------------------+---------------------+
|      "India"      |         142         |
|       "US"        |          33         |
|      "Nepal"      |           3         |
+-------------------+---------------------+

How Storage Works:
  Key ("India") 
       |
  [ Hash Function: hashCode() % N ] 
       |
  Bucket Array Index (e.g., Index 3) ===> Stores Entry Node("India", 142)

-------------------------------------------------

Primary Operations & Complexity Table

---------------------------------------------------------------------------------------------------------
Operation        | Method Signature             | Average Time | Worst Case* | Functionality
---------------------------------------------------------------------------------------------------------
Insertion        | put(K key, V value)          | O(1)         | O(n)        | Inserts new pair or updates value
Search / Lookup  | get(K key)                   | O(1)         | O(n)        | Returns value or null if key absent
Key Existence    | containsKey(K key)           | O(1)         | O(n)        | Returns boolean (true/false)
Deletion         | remove(K key)                | O(1)         | O(n)        | Removes pair and returns its value
Size Query       | size()                       | O(1)         | O(1)        | Returns count of active pairs
Reset / Clear    | clear()                      | O(n)         | O(n)        | Empties all buckets
---------------------------------------------------------------------------------------------------------
* Worst Case O(n) occurs only when catastrophic hash collisions place all keys in the exact same bucket 
  (mitigated to O(log n) in Java 8+ using balanced Red-Black tree buckets).

Space Complexity:
- O(n) to store $n$ key-value entries within the underlying bucket array.
=================================================
*/
