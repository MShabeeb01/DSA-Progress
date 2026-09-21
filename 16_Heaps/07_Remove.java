import java.util.ArrayList;

public class DeleteInHeap {

    // Min-Heap Implementation using ArrayList
    static class Heap {
        ArrayList<Integer> arr = new ArrayList<>();

        public void add(int data) {
            arr.add(data);
            int x = arr.size() - 1;
            int par = (x - 1) / 2;

            while (x > 0 && arr.get(x) < arr.get(par)) {
                int temp = arr.get(x);
                arr.set(x, arr.get(par));
                arr.set(par, temp);

                x = par;
                par = (x - 1) / 2;
            }
        }

        public int peek() {
            if (arr.isEmpty()) {
                throw new RuntimeException("Heap is empty");
            }
            return arr.get(0);
        }

        // Helper: Down-Heapify / Sift-Down to restore Min-Heap invariant - O(log n)
        private void heapify(int i) {
            int left = 2 * i + 1;
            int right = 2 * i + 2;
            int minIdx = i; // Assume current root is minimum

            // Check if left child exists and is smaller than current minimum
            if (left < arr.size() && arr.get(left) < arr.get(minIdx)) {
                minIdx = left;
            }

            // Check if right child exists and is smaller than current minimum
            if (right < arr.size() && arr.get(right) < arr.get(minIdx)) {
                minIdx = right;
            }

            // If root is not the minimum, swap with the smaller child and continue heapifying
            if (minIdx != i) {
                int temp = arr.get(i);
                arr.set(i, arr.get(minIdx));
                arr.set(minIdx, temp);

                heapify(minIdx); // Recursively heapify the affected subtree
            }
        }

        // Operation: Remove/Delete the highest-priority element (Root) - O(log n)[cite: 27]
        public int remove() {
            if (arr.isEmpty()) {
                throw new RuntimeException("Heap is empty");
            }

            int data = arr.get(0); // Store root data to return

            // Step 1: Swap first (root) and last element
            int temp = arr.get(0);
            arr.set(0, arr.get(arr.size() - 1));
            arr.set(arr.size() - 1, temp);

            // Step 2: Remove the last element (original root) in O(1)
            arr.remove(arr.size() - 1);

            // Step 3: Fix the heap via down-heapify starting from the root
            if (!arr.isEmpty()) {
                heapify(0);
            }

            return data;
        }

        public boolean isEmpty() {
            return arr.isEmpty();
        }

        public void printHeap() {
            System.out.println("Heap array: " + arr);
        }
    }

    public static void main(String[] args) {
        Heap h = new Heap();

        // Initial tree from lecture slide: [2, 3, 4, 5, 10, 6][cite: 27]
        h.add(2);
        h.add(3);
        h.add(4);
        h.add(5);
        h.add(10);
        h.add(6);

        System.out.print("Initial Heap: ");
        h.printHeap(); 
        // Output: [2, 3, 4, 5, 10, 6][cite: 27]

        // Delete root (2)[cite: 27]
        int removedVal = h.remove();
        System.out.println("Removed Root Value: " + removedVal); // 2

        System.out.print("Heap After Deletion: ");
        h.printHeap(); 
        // Output: [3, 5, 4, 6, 10]
    }
}

/*
==================== SUMMARY ====================

Topic: Chapter 37 — Delete / Remove from Heap (Down-Heapify / Sift-Down)[cite: 27]

Why We Cannot Simply Delete the Root at Index 0:
- Deleting index 0 directly shifts every subsequent element left by 1 position ($O(n)$ in an ArrayList).
- It breaks the Complete Binary Tree (CBT) parent-child index equations ($2i+1, 2i+2$).

The 3-Step Algorithmic Deletion Strategy:
1. Swap First and Last Elements:
   - Swap `arr[0]` (root) with `arr[size - 1]` (deepest, rightmost leaf node).
2. Remove the Last Element:
   - `arr.remove(arr.size() - 1)` removes the target value from the end of the array in $O(1)$ time without shifting elements.
3. Down-Heapify (`heapify(0)`):
   - The element now at index 0 may violate heap order.
   - Compare index $i$ with its left child ($2i+1$) and right child ($2i+2$).
   - Identify the smallest of the three (`minIdx`).
   - If `minIdx != i`, swap `arr[i]` with `arr[minIdx]` and recursively call `heapify(minIdx)`.

-------------------------------------------------

Down-Heapify Lifecycle Visual (Delete 2)[cite: 27]

Initial Min-Heap[cite: 27]:
               2   (Index 0)[cite: 27]
             /   \
 (Index 1)  3     4  (Index 2)[cite: 27]
           / \   /
(Index 3) 5  10 6 (Index 5)[cite: 27]

Array: [2, 3, 4, 5, 10, 6][cite: 27]

Step 1: Swap Root (2) with Last Element (6):
               6   <-- Swapped to Root
             /   \
            3     4
           / \   /
          5  10 2  <-- Moved to end

Array: [6, 3, 4, 5, 10, 2]

Step 2: Remove Last Element:
  `arr.remove(5)` removes 2 in O(1).
Array: [6, 3, 4, 5, 10]

Step 3: Down-Heapify from Root (Index 0):
  - Compare Root (6) with Left Child (3) and Right Child (4).
  - Minimum of {6, 3, 4} is 3 (Index 1).
  - Swap 6 and 3:
               3   <-- Fixed
             /   \
            6     4
           / \
          5  10

  - Now at Index 1 (Value 6):
    - Compare with Left Child (5) and Right Child (10).
    - Minimum of {6, 5, 10} is 5 (Index 3).
    - Swap 6 and 5:
               3
             /   \
            5     4
           / \
          6  10

  - At Index 3 (Value 6): Left child index = 7 >= size (5), no children left.
  - Terminate.

Final Array: [3, 5, 4, 6, 10]

-------------------------------------------------

Step-by-Step Trace Table

----------------------------------------------------------------------------------------------------------------------
Step | Index `i` | Children Checked (`left`, `right`) | Values Compared (`i`, `L`, `R`) | `minIdx` Chosen | Action
----------------------------------------------------------------------------------------------------------------------
0    | -         | Swap root (2) & last (6)          | -                               | -               | Remove 2
1    | 0         | Left: 1, Right: 2                 | arr[0]=6, arr[1]=3, arr[2]=4    | 1 (Value 3)     | Swap arr[0] & arr[1]
2    | 1         | Left: 3, Right: 4                 | arr[1]=6, arr[3]=5, arr[4]=10   | 3 (Value 5)     | Swap arr[1] & arr[3]
3    | 3         | Left: 7 (Out of bounds)           | None                            | 3 (No change)   | Terminate
----------------------------------------------------------------------------------------------------------------------

Complexity Analysis:
- Time Complexity : O(log n) — Downward traversal visits one node per tree level ($H = \log_2 n$).
- Space Complexity: O(log n) auxiliary stack space for recursion (or O(1) if implemented iteratively with a while loop).
=================================================
*/
