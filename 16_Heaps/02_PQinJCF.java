import java.util.Collections;
import java.util.PriorityQueue;

public class PQinJCF {

    public static void main(String[] args) {
        // ==========================================
        // 1. Default PriorityQueue: Min-Heap
        // ==========================================
        // Elements are ordered in natural ascending order (smallest has highest priority)
        PriorityQueue<Integer> minPq = new PriorityQueue<>();

        // add() -> O(log n)
        minPq.add(3);
        minPq.add(4);
        minPq.add(1);
        minPq.add(7);

        System.out.print("Min-Heap extraction: ");
        while (!minPq.isEmpty()) {
            // peek()   -> O(1)    : inspect root element
            // remove() -> O(log n): remove root element and re-heapify
            System.out.print(minPq.peek() + " ");
            minPq.remove();
        }
        System.out.println();
        // Output: 1 3 4 7

        // ==========================================
        // 2. Custom PriorityQueue: Max-Heap
        // ==========================================
        // Using Collections.reverseOrder() reverses priority (largest has highest priority)
        PriorityQueue<Integer> maxPq = new PriorityQueue<>(Collections.reverseOrder());

        // add() -> O(log n)
        maxPq.add(3);
        maxPq.add(4);
        maxPq.add(1);
        maxPq.add(7);

        System.out.print("Max-Heap extraction: ");
        while (!maxPq.isEmpty()) {
            System.out.print(maxPq.peek() + " ");
            maxPq.remove();
        }
        System.out.println();
        // Output: 7 4 3 1
    }
}

/*
==================== SUMMARY ====================

Topic: Chapter 37 — Priority Queues in Java Collections Framework (JCF)[cite: 22]

Key Concepts & Mechanics:

1. Nature of PriorityQueue in JCF:
   - Part of `java.util` package implementing the `Queue` interface[cite: 22].
   - Underlying data structure is a dynamically resizing array-based Complete Binary Tree (CBT).
   - **Default behavior:** Min-Heap (natural ordering via `Comparable`). The smallest element 
     always resides at the root index [0].
   - **Reverse behavior:** Max-Heap via a custom comparator (`Collections.reverseOrder()`).

2. Core JCF Methods and Operations[cite: 22]:
   - `add(E e)` / `offer(E e)`:
     - Inserts the element at the next leaf position and bubbles it up ("up-heapify / sift-up")[cite: 22].
     - **Time Complexity: O(log n)**[cite: 22].
   - `remove()` / `poll()`:
     - Extracts the element at the root, swaps the last leaf element to the root, 
       and pushes it down ("down-heapify / sift-down")[cite: 22].
     - **Time Complexity: O(log n)**[cite: 22].
   - `peek()` / `element()`:
     - Directly returns the element at index 0 without removing it[cite: 22].
     - **Time Complexity: O(1)**[cite: 22].

-------------------------------------------------

Internal Tree Transition Visual (Min-Heap)

Insertions: 3, 4, 1, 7

1. add(3)         2. add(4)            3. add(1)             4. add(7)
      3                 3                    3                     1
                       /                    / \                   / \
                      4                    4   1                 4   3
                                          (Swap 1 & 3)          /
                                          ===========>         7
                                               1
                                             /   \
                                            4     3

Removal Sequence (Min-Heap):
- peek() = 1 -> remove(): replaces 1 with 7, sifts down -> root becomes 3
- peek() = 3 -> remove(): replaces 3 with 7, sifts down -> root becomes 4
- peek() = 4 -> remove(): root becomes 7
- peek() = 7 -> remove(): queue empty

-------------------------------------------------

Operation Complexity Comparison Table[cite: 22]

---------------------------------------------------------------------------------------------------------
Operation             | JCF Method                 | Time Complexity[cite: 22] | Description
---------------------------------------------------------------------------------------------------------
Insertion             | add(x) / offer(x)          | O(log n)[cite: 22]        | Appends to array and sifts up
Root Inspection       | peek()                     | O(1)[cite: 22]            | Accesses root element at index 0
Root Extraction       | remove() / poll()          | O(log n)[cite: 22]        | Removes root, replaces with leaf, sifts down
Search by Element     | contains(x)                | O(n)                       | Linear scan across internal array
Size Query            | size()                     | O(1)                       | Returns tracked element counter
---------------------------------------------------------------------------------------------------------

Space Complexity:
- Auxiliary Space: O(n) to store $n$ items in the underlying array.
=================================================
*/
