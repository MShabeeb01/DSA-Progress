import java.util.Collections;
import java.util.PriorityQueue;

public class PriorityQueueBasics {

    public static void main(String[] args) {
        // By default, PriorityQueue in Java acts as a Min-Heap (Ascending order)
        PriorityQueue<Integer> minPq = new PriorityQueue<>();

        minPq.add(3); // O(log n)
        minPq.add(4);
        minPq.add(1);
        minPq.add(7);

        System.out.print("Min-PriorityQueue extraction: ");
        while (!minPq.isEmpty()) {
            System.out.print(minPq.peek() + " "); // O(1) peek
            minPq.remove();                       // O(log n) remove
        }
        System.out.println();
        // Output: 1 3 4 7 (Smallest element has the highest priority)

        // Using Collections.reverseOrder() configures it as a Max-Heap (Descending order)
        PriorityQueue<Integer> maxPq = new PriorityQueue<>(Collections.reverseOrder());

        maxPq.add(3);
        maxPq.add(4);
        maxPq.add(1);
        maxPq.add(7);

        System.out.print("Max-PriorityQueue extraction: ");
        while (!maxPq.isEmpty()) {
            System.out.print(maxPq.peek() + " "); // O(1) peek
            maxPq.remove();                       // O(log n) remove
        }
        System.out.println();
        // Output: 7 4 3 1 (Largest element has the highest priority)
    }
}

/*
==================== SUMMARY ====================

Topic: Chapter 37 — Heaps / Priority Queues (Introduction)[cite: 20]

1. What is a Priority Queue (PQ)?[cite: 20]
- A specialized Abstract Data Type (ADT) extending the standard Queue.
- Standard Queue: First-In, First-Out (FIFO). Elements leave based on insertion order.
- Priority Queue: Elements are dequeued based on their **priority** regardless of when they entered.
  - Min-Priority Queue: Smallest numerical value has the highest priority (default in Java).
  - Max-Priority Queue: Largest numerical value has the highest priority.

2. Relationship Between Heaps and Priority Queues:
- **Priority Queue** is the Abstract Interface (specifying operations: `add`, `peek`, `remove`).
- **Heap** is the concrete underlying data structure (typically an array-backed Complete Binary Tree) 
  used to implement the Priority Queue efficiently.

3. Standard Queue vs Priority Queue Operations:

---------------------------------------------------------------------------------------------------------
Operation   | Standard Queue (FIFO) | Priority Queue (Heap-backed) | Description
---------------------------------------------------------------------------------------------------------
Insertion   | add() -> O(1)         | add() -> O(log n)            | Inserts and adjusts element position
Inspection  | peek() -> O(1)        | peek() -> O(1)               | Retrieves the highest-priority element
Removal     | remove() -> O(1)      | remove() -> O(log n)         | Extracts the highest-priority element
---------------------------------------------------------------------------------------------------------

4. Array / Complete Binary Tree (CBT) Indexing Formula:
For any element stored at zero-based array index `i`:
- Left Child Index   : 2 * i + 1
- Right Child Index  : 2 * i + 2
- Parent Node Index  : (i - 1) / 2

-------------------------------------------------

Queue vs Priority Queue Processing Visual

Input sequence: [ 7, 1, 4, 3 ]

Standard Queue (FIFO):
  Enqueue:  [ 7, 1, 4, 3 ]
  Dequeue:  7 -> 1 -> 4 -> 3

Min-Priority Queue:
  Insert:   [ 7, 1, 4, 3 ]
  Priority: 1 (Highest) > 3 > 4 > 7 (Lowest)
  Dequeue:  1 -> 3 -> 4 -> 7

Max-Priority Queue:
  Insert:   [ 7, 1, 4, 3 ]
  Priority: 7 (Highest) > 4 > 3 > 1 (Lowest)
  Dequeue:  7 -> 4 -> 3 -> 1

-------------------------------------------------

Step-by-Step Trace Table (Min-PQ)

Elements added: 3, 4, 1, 7

---------------------------------------------------------------------------------------------------------
Operation     | Value Involved | Heap State (Array-Backed CBT) | Peek Value (Root) | Action Detail
---------------------------------------------------------------------------------------------------------
add(3)        | 3              | [3]                           | 3                 | Placed at root
add(4)        | 4              | [3, 4]                        | 3                 | Added as left child
add(1)        | 1              | [1, 4, 3]                     | 1                 | Up-heapified to root
add(7)        | 7              | [1, 4, 3, 7]                  | 1                 | Added as leaf
remove()      | 1 extracted    | [3, 4, 7]                     | 3                 | Down-heapified
remove()      | 3 extracted    | [4, 7]                        | 4                 | Down-heapified
remove()      | 4 extracted    | [7]                           | 7                 | Last parent
remove()      | 7 extracted    | []                            | null              | Queue empty
---------------------------------------------------------------------------------------------------------

Complexity Summary:
- Peek (`peek()`)     : O(1) — Direct access to the root element.
- Insert (`add()`)    : O(log n) — Element appended to leaf, bubble up (up-heapify).
- Delete (`remove()`) : O(log n) — Root replaced by last leaf, bubble down (down-heapify).
- Space Complexity    : O(n) — Linear space to store $n$ elements in the array.
=================================================
*/
