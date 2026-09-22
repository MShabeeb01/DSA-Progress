import java.util.HashMap;

public class HashMapOperations {

    public static void main(String[] args) {
        // Create a HashMap storing country names (Keys) and population in millions (Values)
        HashMap<String, Integer> hm = new HashMap<>();

        // ==========================================================
        // 1. put(key, value) -> Time Complexity: O(1)
        // ==========================================================
        // Inserts the pair if key is absent; updates the value if key already exists
        hm.put("India", 140);
        hm.put("China", 135);
        hm.put("US", 33);
        hm.put("Nepal", 3);

        System.out.println("HashMap after additions: " + hm);

        // Overwrite / Update existing key's value
        hm.put("India", 142);
        System.out.println("After updating India's population: " + hm);

        // ==========================================================
        // 2. get(key) -> Time Complexity: O(1)
        // ==========================================================
        // Returns corresponding value if key exists, otherwise returns null
        int populationIndia = hm.get("India");
        System.out.println("Value for key 'India': " + populationIndia); // 142

        Integer populationBhutan = hm.get("Bhutan");
        System.out.println("Value for key 'Bhutan' (absent): " + populationBhutan); // null

        // ==========================================================
        // 3. containsKey(key) -> Time Complexity: O(1)
        // ==========================================================
        // Checks whether a specific key exists; returns boolean true/false
        System.out.println("Contains key 'India'? " + hm.containsKey("India"));   // true
        System.out.println("Contains key 'Bhutan'? " + hm.containsKey("Bhutan")); // false

        // ==========================================================
        // 4. remove(key) -> Time Complexity: O(1)
        // ==========================================================
        // Removes the key-value pair and returns the associated value (or null if absent)
        Integer removedVal = hm.remove("China");
        System.out.println("Removed key 'China' with value: " + removedVal); // 135
        System.out.println("HashMap after removing 'China': " + hm);

        Integer absentRemoved = hm.remove("Bhutan");
        System.out.println("Removing absent key 'Bhutan': " + absentRemoved); // null

        // ==========================================================
        // Additional Fundamental Operations
        // ==========================================================
        // size() -> O(1): Returns count of key-value pairs
        System.out.println("Current size: " + hm.size()); // 3

        // isEmpty() -> O(1): Checks if the map contains zero pairs
        System.out.println("Is map empty? " + hm.isEmpty()); // false

        // clear() -> O(n): Removes all entries
        hm.clear();
        System.out.println("After clear(), isEmpty: " + hm.isEmpty()); // true
    }
}

/*
==================== SUMMARY ====================

Topic: Chapter 38 — Core HashMap Operations & Complexities[cite: 28]

The 4 Essential HashMap Operations[cite: 28]:

1. `put(key, value)` — Average O(1)[cite: 28]:
   - Hashes the key to calculate its bucket index.
   - If the key does not exist, appends a new `Node(key, value)` to that bucket's linked list/tree.
   - If the key already exists, updates the node's value with the new value.

2. `get(key)` — Average O(1)[cite: 28]:
   - Computes the bucket index via key hashing.
   - Scans the short bucket chain using `.equals()`.
   - Returns the associated value $V$ if found; returns `null` if the key is not present.

3. `containsKey(key)` — Average O(1)[cite: 28]:
   - Navigates to the hashed bucket.
   - Checks if any entry has a key matching the queried key using `.equals()`.
   - Returns `true` if found, `false` otherwise.

4. `remove(key)` — Average O(1)[cite: 28]:
   - Locates the target entry inside the computed bucket.
   - Unlinks the node from the bucket list and decrements map size.
   - Returns the value that was removed, or `null` if the key was never present.

-------------------------------------------------

Internal Execution Mechanism Visual

Key Input: "India" (value: 142)

  Step 1: Key Hasher
          "India".hashCode() -> 70793502

  Step 2: Bucket Index Computation
          index = Math.abs(70793502) % array_length (e.g., 16) -> Bucket [14]

  Step 3: Action in Bucket [14]
          - put(): Store Entry("India", 142)
          - get(): Read 142 directly from Entry
          - containsKey(): Check if "India".equals(node.key) -> true
          - remove(): Detach Entry from Bucket [14] and return 142

-------------------------------------------------

Operations Complexity Reference Table[cite: 28]

---------------------------------------------------------------------------------------------------------
Operation         | Method Signature         | Average Case[cite: 28] | Worst Case* | Returns
---------------------------------------------------------------------------------------------------------
Insertion / Update| put(K key, V value)      | O(1)[cite: 28]         | O(n)        | Previous value or null
Lookup / Search   | get(K key)               | O(1)[cite: 28]         | O(n)        | Value or null
Membership Check  | containsKey(K key)       | O(1)[cite: 28]         | O(n)        | boolean (true/false)
Deletion          | remove(K key)            | O(1)[cite: 28]         | O(n)        | Removed value or null
Cardinality       | size()                   | O(1)                    | O(1)        | int (total pairs)
State Check       | isEmpty()                | O(1)                    | O(1)        | boolean
---------------------------------------------------------------------------------------------------------
* Worst Case O(n) happens when all inserted keys collide onto a single bucket index. 
  Since Java 8, worst case is optimized to O(log n) as large buckets convert from LinkedList to Red-Black Balanced Trees.

Space Complexity:
- O(n) auxiliary memory for storing $n$ key-value node references in the bucket array.
=================================================
*/
