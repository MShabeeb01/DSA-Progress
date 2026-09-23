import java.util.HashSet;

public class HashSetBasics {

    public static void main(String[] args) {
        // Instantiate a HashSet
        HashSet<Integer> set = new HashSet<>();

        // 1. add(element) -> O(1) average
        set.add(1);
        set.add(2);
        set.add(4);
        set.add(2); // Duplicate element -> Ignored
        set.add(1); // Duplicate element -> Ignored

        // NULL values are allowed
        set.add(null);

        // Sets contain no duplicates and are unordered
        System.out.println("HashSet elements: " + set);
        // Expected elements: [null, 1, 2, 4] (exact order may vary)

        // 2. contains(element) -> O(1) average
        System.out.println("Contains 2? " + set.contains(2)); // true
        System.out.println("Contains 3? " + set.contains(3)); // false

        // 3. remove(element) -> O(1) average
        set.remove(2);
        System.out.println("After removing 2: " + set);
        System.out.println("Contains 2 after remove? " + set.contains(2)); // false

        // 4. size() and isEmpty() -> O(1)
        System.out.println("Set size: " + set.size());
        System.out.println("Is set empty? " + set.isEmpty());

        // 5. clear() -> O(n)
        set.clear();
        System.out.println("After clear, size: " + set.size()); // 0
    }
}

/*
==================== SUMMARY ====================

Topic: Chapter 38 — Introduction to HashSet (Mathematical Set Implementation)[cite: 22]

Mathematical Analogy:
In mathematics, a Set is defined as a well-defined collection of distinct objects[cite: 22].
Java's `HashSet` implements the `Set` interface, reflecting these exact mathematical properties:

Core Properties of HashSet:
1. No Duplicates[cite: 22]:
   - Every element in a HashSet must be unique[cite: 22].
   - If an element already exists, calling `.add(x)` returns `false` and does not insert the duplicate.
2. Unordered[cite: 22]:
   - Elements have no guaranteed sequence or positional indexing[cite: 22].
   - The iteration order depends entirely on the hash code of the elements and internal bucket layout.
3. NULL is Allowed[cite: 22]:
   - Allows at most one `null` element (since duplicate `null`s are rejected)[cite: 22].

How HashSet is Implemented Internally (Under the Hood):
- A `HashSet` is internally backed by a `HashMap`!
- When you create:
    `HashSet<Integer> set = new HashSet<>();`
  Java internally instantiates:
    `private transient HashMap<E, Object> map;`
- When you call:
    `set.add(1);`
  It executes:
    `map.put(1, PRESENT);`
  (where `PRESENT` is a dummy `new Object()` placeholder value).
- Because `HashMap` keys are unique, `HashSet` inherently enforces uniqueness.

-------------------------------------------------

Internal Mapping Visual

User Invocation:
  set.add(1)
  set.add(2)
  set.add(2) (Duplicate)
  set.add(null)

Backing HashMap State:
+-------------------+----------------------------+
|     KEY (Set)     |     VALUE (Dummy Object)   |
+-------------------+----------------------------+
|         1         |      PRESENT (Object)      |
|         2         |      PRESENT (Object)      |
|       null        |      PRESENT (Object)      |
+-------------------+----------------------------+
(Any subsequent set.add(2) simply updates the dummy object for existing key 2, preserving cardinality)

-------------------------------------------------

HashSet Operations & Complexity Table

---------------------------------------------------------------------------------------------------------
Operation         | Method Signature       | Average Time | Worst Case* | Description
---------------------------------------------------------------------------------------------------------
Add Element       | add(E e)               | O(1)         | O(n)        | Adds if not present; returns true/false
Search/Check      | contains(Object o)     | O(1)         | O(n)        | Tests membership via hashCode & equals
Remove Element    | remove(Object o)       | O(1)         | O(n)        | Removes element if present
Size Query        | size()                 | O(1)         | O(1)        | Number of distinct elements present
Empty Check       | isEmpty()              | O(1)         | O(1)        | Returns true if size == 0
Clear             | clear()                | O(n)         | O(n)        | Flushes all elements from table
---------------------------------------------------------------------------------------------------------
* Worst Case O(n) occurs under pathological hash collisions across all elements into one bucket.

Complexity Summary:
- Time Complexity : O(1) average for add, contains, and remove operations.
- Space Complexity: O(n) to store $n$ unique elements in the internal hash table.
=================================================
*/
