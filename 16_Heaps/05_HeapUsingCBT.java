import java.util.ArrayList;

public class HeapImplementationBasics {

    // A Heap is represented using a contiguous dynamic array (ArrayList) rather than linked Node classes
    static class Heap {
        ArrayList<Integer> arr = new ArrayList<>();

        // Helper formulas to navigate Complete Binary Tree via indices:
        // For parent at index i:
        //   Left Child  = 2 * i + 1
        //   Right Child = 2 * i + 2
        // For child at index i:
        //   Parent Node = (i - 1) / 2

        public void printHeapArray() {
            System.out.println("Array representation: " + arr);
        }
    }

    public static void main(String[] args) {
        // Conceptual CBT values displayed on screen:
        // Root: 2, Left: 3, Right: 4, 3's Left: 5, 3's Right: 10
        Heap h = new Heap();
        h.arr.add(2);  // Index 0 (Root)
        h.arr.add(3);  // Index 1 (Left child of 2)
        h.arr.add(4);  // Index 2 (Right child of 2)
        h.arr.add(5);  // Index 3 (Left child of 3)
        h.arr.add(10); // Index 4 (Right child of 3)

        h.printHeapArray();
        // Output: Array representation: [2, 3, 4, 5, 10]

        // Demonstrating index arithmetic:
        int parentIdx = 1; // Node with value 3
        int leftChildIdx = 2 * parentIdx + 1;  // Index 3 -> value 5
        int rightChildIdx = 2 * parentIdx + 2; // Index 4 -> value 10

        System.out.println("Node at index " + parentIdx + " has value: " + h.arr.get(parentIdx));
        System.out.println("  Left child index " + leftChildIdx + " -> " + h.arr.get(leftChildIdx));
        System.out.println("  Right child index " + rightChildIdx + " -> " + h.arr.get(rightChildIdx));
    }
}

/*
==================== SUMMARY ====================

Topic: Chapter 37 — Heap Implementation Using CBT (Why Heap is NOT implemented as a Node Class)[cite: 25]

Core Question: Why do we not use a linked `Node` class (with left and right pointers) for Heaps?[cite: 25]

1. Memory Overhead of Pointer Links:
   - A typical linked `Node` object requires:
     - 4/8 bytes for node value
     - 8 bytes for left child reference
     - 8 bytes for right child reference
     - 8 bytes for parent reference (optional, but needed for upward bubble-up)
     - Object header overhead (12–16 bytes in JVM)
   - Storing tree elements in a flat array eliminates all pointer overhead, storing raw values contiguously.

2. Cache Locality and Performance:
   - Arrays occupy contiguous memory blocks, offering superior CPU cache spatial locality.
   - Pointer-based linked nodes are scattered across the heap memory, causing frequent cache misses.

3. Complete Binary Tree (CBT) Guarantee:
   - Because a Heap is strictly a Complete Binary Tree, every level is completely filled from left to right without missing positions.
   - This strict geometric packing allows 1-to-1 linear indexing with zero empty gaps.

4. Bidirectional Traversal in O(1) Time:
   - In a pointer tree, moving from child to parent requires storing parent pointers or maintaining an explicit call stack.
   - In an array-backed CBT, finding parents and children requires only basic integer arithmetic:
       - Parent(i)      = (i - 1) / 2
       - Left Child(i)  = 2 * i + 1
       - Right Child(i) = 2 * i + 2

-------------------------------------------------

Tree to Array Mapping Visual[cite: 25]

Conceptual Complete Binary Tree:
               2   (Index 0)[cite: 25]
             /   \
 (Index 1)  3     4  (Index 2)[cite: 25]
           / \
(Index 3) 5   10 (Index 4)[cite: 25]

Linear Array Layout:
  +-----+-----+-----+-----+-----+
  |  2  |  3  |  4  |  5  | 10  |[cite: 25]
  +-----+-----+-----+-----+-----+
     0     1     2     3     4   <-- Array Indices

Index Calculation Verification:
- For Node 2 (index 0):
  - Left  = 2(0) + 1 = 1  -> Value 3[cite: 25]
  - Right = 2(0) + 2 = 2  -> Value 4[cite: 25]
- For Node 3 (index 1):
  - Left  = 2(1) + 1 = 3  -> Value 5[cite: 25]
  - Right = 2(1) + 2 = 4  -> Value 10[cite: 25]
- For Node 10 (index 4):
  - Parent = (4 - 1) / 2 = 1 -> Value 3[cite: 25]

-------------------------------------------------

Array vs. Linked Node Implementation Comparison

---------------------------------------------------------------------------------------------------------
Feature                 | Linked Node Implementation (Treated as Class) | Array / ArrayList CBT Implementation[cite: 25]
---------------------------------------------------------------------------------------------------------
Structure Type          | Dispersed heap objects with pointers          | Flat, contiguous array buffer
Parent Lookup           | Needs parent pointer `Node parent`            | Simple arithmetic: `(i - 1) / 2`
Child Lookup            | Pointer dereferencing (`node.left`)           | Direct index calculation: `2 * i + 1`
Spatial Cache Locality  | Poor (frequent cache misses)                  | High (sequential cache lines)
Memory Overhead         | Significant (16-32 bytes overhead per node)   | Minimal (only array elements stored)
Insert / Delete Tail    | Hard to locate next insertion leaf node       | Trivial (`arr.get(arr.size() - 1)`)
---------------------------------------------------------------------------------------------------------

Complexity:
- Parent / Child Access: O(1)
- Space Consumption: O(n) contiguous storage
=================================================
*/
