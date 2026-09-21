import java.util.ArrayList;

public class InsertInHeap {

    // Min-Heap Implementation using ArrayList
    static class Heap {
        ArrayList<Integer> arr = new ArrayList<>();

        // Operation: Insert an element into Min-Heap (Up-Heapify / Sift-Up) - O(log n)
        public void add(int data) {
            // Step 1: Add the new element at the very end of the array (next leaf position in CBT)
            arr.add(data);

            // Child index is the last element's index
            int x = arr.size() - 1;       // Child index
            int par = (x - 1) / 2;        // Parent index: (i - 1) / 2

            // Step 2: Up-Heapify (Bubble up)
            // While child is not the root and child's value is smaller than parent's value
            while (x > 0 && arr.get(x) < arr.get(par)) {
                // Swap child and parent
                int temp = arr.get(x);
                arr.set(x, arr.get(par));
                arr.set(par, temp);

                // Move pointers up the tree
                x = par;
                par = (x - 1) / 2;
            }
        }

        public void printHeap() {
            System.out.println("Heap array: " + arr);
        }
    }

    public static void main(String[] args) {
        Heap h = new Heap();

        // Initial tree from the video: [2, 3, 4, 5, 10]
        h.add(2);
        h.add(3);
        h.add(4);
        h.add(5);
        h.add(10);

        System.out.print("Before inserting 1 -> ");
        h.printHeap(); 
        // Output: [2, 3, 4, 5, 10]

        // Insert new element: 1
        h.add(1);

        System.out.print("After inserting 1  -> ");
        h.printHeap(); 
        // Output: [1, 3, 2, 5, 10, 4]
    }
}

/*
==================== SUMMARY ====================

Topic: Chapter 37 — Insert in Heap (Up-Heapify / Sift-Up)[cite: 26]

Core Mechanics:

1. Step 1: Add at the End (O(1))
   - Place the new element at `arr.add(data)`.
   - This ensures the Complete Binary Tree (CBT) structural invariant is preserved (no missing gaps, leaves filled left to right).

2. Step 2: Up-Heapify / Sift-Up (O(log n))
   - Compare the newly added node with its parent:
     `parent_idx = (child_idx - 1) / 2`
   - In a Min-Heap: if `arr[child] < arr[parent]`, swap them.
   - Continue bubbling up until:
     a. The child reaches the root (`child_idx == 0`), OR
     b. The heap invariant is restored (`arr[child] >= arr[parent]`).

-------------------------------------------------

Step-by-Step Insertion Lifecycle Visual (insert 1)[cite: 26]

Initial Min-Heap:
               2   (Index 0)[cite: 26]
             /   \
 (Index 1)  3     4  (Index 2)[cite: 26]
           / \
(Index 3) 5   10 (Index 4)[cite: 26]

Array: [2, 3, 4, 5, 10][cite: 26]

Step 1: Append 1 to the end (Index 5, left child of 4)[cite: 26]:
               2
             /   \
            3     4
           / \   /
          5  10 1  <-- Newly added at index 5

Array: [2, 3, 4, 5, 10, 1]

Step 2: Compare with parent at index 2 (value 4):
  - 1 < 4  -> Swap 1 and 4
               2
             /   \
            3     1  <-- Swapped
           / \   /
          5  10 4

Array: [2, 3, 1, 5, 10, 4]

Step 3: Compare with parent at index 0 (value 2):
  - 1 < 2  -> Swap 1 and 2
               1  <-- Swapped to Root!
             /   \
            3     2
           / \   /
          5  10 4

Array: [1, 3, 2, 5, 10, 4]
  - child index is now 0 (Root reached). Up-heapify terminates.

-------------------------------------------------

Trace Table for Insertion of 1[cite: 26]

-------------------------------------------------------------------------------------------------------------------------
Step | Child Index (`x`) | Child Val | Parent Index (`par`) | Parent Val | Swap Condition (`val < par`) | Array State After
-------------------------------------------------------------------------------------------------------------------------
0    | 5                 | 1         | (5-1)/2 = 2          | 4          | 1 < 4 (True, Swap)           | [2, 3, 1, 5, 10, 4]
1    | 2                 | 1         | (2-1)/2 = 0          | 2          | 1 < 2 (True, Swap)           | [1, 3, 2, 5, 10, 4]
2    | 0                 | 1         | -                    | -          | `x == 0` (Terminates at root)| [1, 3, 2, 5, 10, 4]
-------------------------------------------------------------------------------------------------------------------------

Complexity Analysis:
- Time Complexity : O(log n) — In the worst case, the element bubbles up from the deepest leaf to the root (equal to tree height $H = \log_2 n$).
- Space Complexity: O(1) auxiliary space (performed in-place within the dynamically resizing array).
=================================================
*/
