import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class HashMapIteration {

    public static void main(String[] args) {
        HashMap<String, Integer> hm = new HashMap<>();
        hm.put("India", 140);
        hm.put("China", 135);
        hm.put("US", 33);
        hm.put("Indonesia", 27);
        hm.put("Nepal", 3);

        // ==========================================================
        // Approach 1: Iteration using keySet() (Standard Lecture Approach)[cite: 28]
        // ==========================================================
        // hm.keySet() returns a Set view of all keys in O(1) time[cite: 28]
        Set<String> keys = hm.keySet(); //[cite: 28]
        System.out.println("Set of Keys: " + keys);

        System.out.println("\n--- Iterating via keySet() (Foreach Loop) ---"); //[cite: 28]
        for (String k : keys) {
            // Retrieve associated value using get(k) in O(1)
            System.out.println("Key = " + k + ", Value = " + hm.get(k));
        }

        // ==========================================================
        // Approach 2: Direct Iteration using entrySet() (Industrial Best Practice)
        // ==========================================================
        // entrySet() iterates through key-value pairs simultaneously without calling get(k)
        System.out.println("\n--- Iterating via entrySet() ---");
        for (Map.Entry<String, Integer> entry : hm.entrySet()) {
            System.out.println("Key = " + entry.getKey() + ", Value = " + entry.getValue());
        }
    }
}

/*
==================== SUMMARY ====================

Topic: Chapter 38 — Iteration on HashMap (Loops)[cite: 28]

Core Problem:
A HashMap cannot be traversed using standard index-based for-loops (`for(int i=0; i<n; i++)`) 
because entries are stored non-contiguously across an array of hash buckets without indices.

Methods of Iteration:

1. Method 1: Using `keySet()`[cite: 28]
   - Method Signature: `Set<K> keySet = hm.keySet();`[cite: 28]
   - `hm.keySet()` creates a backing Set view containing all keys present in the map in O(1) time[cite: 28].
   - Why `Set`? Keys are guaranteed to be unique, matching the mathematical properties of a Set.
   - Using an enhanced for-each loop over the keys[cite: 28]:
       ```java
       for (String k : hm.keySet()) {
           System.out.println("Key: " + k + ", Value: " + hm.get(k));
       }
       ```
   - Total Time: O(N) where N is the number of entries (traverses buckets + O(1) `get()` lookups).

2. Method 2: Using `entrySet()`
   - Method Signature: `Set<Map.Entry<K, V>> entries = hm.entrySet();`
   - Directly exposes each key-value node (`Entry`) simultaneously.
   - Eliminates redundant hash lookups (`hm.get(k)`), making it faster in high-throughput applications.

-------------------------------------------------

KeySet Extraction & Iteration Visual[cite: 28]

HashMap Structure:
  [Bucket 0] -> ("US", 33)
  [Bucket 3] -> ("China", 135)
  [Bucket 7] -> ("India", 140)

Step 1: Extract Keys to Set[cite: 28]:
  Set<String> keys = hm.keySet(); //[cite: 28]
  keys -> {"US", "China", "India"}

Step 2: Loop Iteration[cite: 28]:
  Iteration 1: k = "US"    -> hm.get("US")    -> 33
  Iteration 2: k = "China" -> hm.get("China") -> 135
  Iteration 3: k = "India" -> hm.get("India") -> 140

-------------------------------------------------

Iteration Techniques Comparison Table

---------------------------------------------------------------------------------------------------------
Iteration Technique       | Return Type              | Lookup Efficiency | Best Used When
---------------------------------------------------------------------------------------------------------
hm.keySet()[cite: 28]    | Set<K>[cite: 28]        | Needs hm.get(k)   | You only need keys or want clean syntax[cite: 28]
hm.values()               | Collection<V>            | No key access     | You only care about the values
hm.entrySet()             | Set<Map.Entry<K, V>>     | Direct access     | You need both key and value efficiently
hm.forEach((k, v) -> ...) | void (Lambda Consumer)   | Direct access     | Functional Java 8+ programming
---------------------------------------------------------------------------------------------------------

Complexity Analysis:
- `hm.keySet()` Generation: O(1) auxiliary time (returns a view backed by the map)[cite: 28].
- Full Traversal Time: O(N) where N is the number of elements plus bucket capacity.
- Auxiliary Space: O(1) additional memory (Set view does not duplicate data).
=================================================
*/
