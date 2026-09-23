import java.util.HashSet;
import java.util.Iterator;

public class HashSetIteration {

    public static void main(String[] args) {
        HashSet<String> cities = new HashSet<>();
        cities.add("Delhi");
        cities.add("Mumbai");
        cities.add("Noida");
        cities.add("Bengaluru");

        // ==========================================================
        // Approach A: Using Iterator (java.util.Iterator)[cite: 20]
        // ==========================================================
        // cities.iterator() returns an Iterator object pointing before the first element
        Iterator<String> it = cities.iterator();[cite: 20]

        System.out.println("--- Iterating using Iterator ---");[cite: 20]
        // it.hasNext() checks whether there is another element available in the set
        while (it.hasNext()) {
            // it.next() returns the current element and advances cursor forward
            System.out.println(it.next());
        }

        // ==========================================================
        // Approach B: Using Enhanced for-each loop (Advanced for loop)[cite: 20]
        // ==========================================================
        // Syntactic sugar internally compiled to use the Iterator interface
        System.out.println("\n--- Iterating using Enhanced for loop ---");[cite: 20]
        for (String city : cities) {[cite: 20]
            System.out.println(city);
        }
    }
}

/*
==================== SUMMARY ====================

Topic: Chapter 38 — Iteration on HashSet[cite: 20]

Why Standard For-Loops Do Not Work:
- `HashSet` elements do not have numerical index positions (no `get(index)` method).
- Data is scattered across hash table buckets, so traversal requires iterator-based visiting[cite: 20].

The Two Primary Iteration Techniques[cite: 20]:

1. Using Iterators (`java.util.Iterator`)[cite: 20]:
   - Explicit cursor-based traversal interface.
   - Core Methods:
     - `it.hasNext()`: Returns `true` if more elements remain in the iteration.
     - `it.next()`: Advances the cursor and returns the next element.
     - `it.remove()`: Safely removes the element returned by the last `next()` call without causing `ConcurrentModificationException`.

2. Using the Enhanced For-Each Loop[cite: 20]:
   - Shorter, cleaner, readable syntax: `for (Type val : set)`[cite: 20].
   - Internally backed by the collection's Iterator under the hood[cite: 20].
   - Ideal for read-only traversal where elements are not dynamically modified/removed during iteration.

-------------------------------------------------

Iterator Cursor Mechanics Visual

HashSet: {"Delhi", "Mumbai", "Noida", "Bengaluru"}

Initial Cursor Position:
  [Cursor] -> "Delhi" -> "Mumbai" -> "Noida" -> "Bengaluru"
  it.hasNext() = true

Step 1: it.next() returns "Delhi"
  "Delhi" -> [Cursor] -> "Mumbai" -> "Noida" -> "Bengaluru"
  it.hasNext() = true

Step 2: it.next() returns "Mumbai"
  "Delhi" -> "Mumbai" -> [Cursor] -> "Noida" -> "Bengaluru"
  it.hasNext() = true

Step 3: it.next() returns "Noida"
  "Delhi" -> "Mumbai" -> "Noida" -> [Cursor] -> "Bengaluru"
  it.hasNext() = true

Step 4: it.next() returns "Bengaluru"
  "Delhi" -> "Mumbai" -> "Noida" -> "Bengaluru" -> [Cursor]
  it.hasNext() = false (Loop terminates)

-------------------------------------------------

Comparison: Iterator vs Enhanced For-Each Loop

---------------------------------------------------------------------------------------------------------
Feature                  | Iterator Approach[cite: 20]             | Enhanced For Loop[cite: 20]
---------------------------------------------------------------------------------------------------------
Syntax Cleanliness       | Verbose (`hasNext()` & `next()`)        | Concise (`for (Type item : collection)`)[cite: 20]
Removal during iteration | Supported safely via `it.remove()`     | Throws `ConcurrentModificationException`
Underlying Mechanism     | Direct `Iterator` object traversal[cite: 20]| Implicitly compiled to use an Iterator
Best Application         | Dynamic filtering & conditional removal | Read-only sequence processing & display
---------------------------------------------------------------------------------------------------------

Complexity Analysis:
- Time Complexity : O(n) — Traverses every element across all allocated bucket chains once.
- Space Complexity: O(1) auxiliary space — Only holds a lightweight cursor pointer.
=================================================
*/
