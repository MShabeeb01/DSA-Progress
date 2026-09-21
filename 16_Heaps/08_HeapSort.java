public class HeapSortAlgorithm {

    // Helper: Down-Heapify for Max-Heap within a defined boundary 'size' - O(log n)
    public static void heapify(int arr[], int i, int size) {
        int left = 2 * i + 1;
        int right = 2 * i + 2;
        int maxIdx = i; // Assume current root is largest

        // Check if left child is within heap boundary and larger than root
        if (left < size && arr[left] > arr[maxIdx]) {
            maxIdx = left;
        }

        // Check if right child is within heap boundary and larger than current max
        if (right < size && arr[right] > arr[maxIdx]) {
            maxIdx = right;
        }

        // If root is not the largest, swap with largest child and recurse
        if (maxIdx != i) {
            int temp = arr[i];
            arr[i] = arr[maxIdx];
            arr[maxIdx] = temp;

            heapify(arr, maxIdx, size);
        }
    }

    // Operation: Heap Sort (Ascending Order) - O(n log n)[cite: 28]
    public static void heapSort(int arr[]) {
        int n = arr.length;

        // Step 1: Build Max-Heap from arbitrary array (Bottom-Up)
        // Non-leaf nodes start from index (n/2 - 1) down to 0
        for (int i = n / 2 - 1; i >= 0; i--) {
            heapify(arr, i, n);
        }

        // Step 2: Extract elements from heap one by one (Push largest to end)
        for (int i = n - 1; i > 0; i--) {
            // Swap current root (maximum element) with the last element
            int temp = arr[0];
            arr[0] = arr[i];
            arr[i] = temp;

            // Restore Max-Heap on the reduced heap of size 'i'
            heapify(arr, 0, i);
        }
    }

    public static void main(String[] args) {
        int arr[] = {1, 2, 4, 5, 3}; //[cite: 28]

        System.out.print("Original Array: ");
        for (int val : arr) {
            System.out.print(val + " ");
        }
        System.out.println();

        heapSort(arr);

        System.out.print("Sorted Array:   ");
        for (int val : arr) {
            System.out.print(val + " ");
        }
        System.out.println();
        // Output: 1 2 3 4 5
    }
}

/*
==================== SUMMARY ====================

Topic: Chapter 37 — Heap Sort[cite: 28]

Objective:
Sort an unsorted array in-place using the Heap data structure in O(n log n) time[cite: 28].

Sorting Direction Rule:
- To sort in **Ascending Order**: Build a **Max-Heap** (largest element moves to end).
- To sort in **Descending Order**: Build a **Min-Heap** (smallest element moves to end).

Two Core Phases of Heap Sort:

1. Phase 1: Build Max-Heap — O(n)
   - Leaf nodes are already valid heaps trivially.
   - Run `heapify` only on non-leaf nodes: indices from `(n/2 - 1)` down to `0`.
   - Result: Array satisfies the Max-Heap property where `arr[0]` is the maximum element.

2. Phase 2: Push Largest Elements to the End — O(n log n)
   - Loop `i` from `n - 1` down to `1`:
     a. Swap `arr[0]` (maximum) with `arr[i]` (end of current heap partition).
     b. Call `heapify(arr, 0, i)` on the reduced heap boundary `i` to place the next largest element at `arr[0]`.

-------------------------------------------------

Step-by-Step Transition Visual

Input: arr = [1, 2, 4, 5, 3][cite: 28]
Size: n = 5, Non-leaf nodes: indices 1 down to 0

Phase 1: Build Max-Heap
- heapify at index 1 (value 2):
  Children: left = arr[3] (5), right = arr[4] (3)
  Max is 5 -> Swap 2 and 5 -> arr = [1, 5, 4, 2, 3]

- heapify at index 0 (value 1):
  Children: left = arr[1] (5), right = arr[2] (4)
  Max is 5 -> Swap 1 and 5 -> arr = [5, 1, 4, 2, 3]
  Recurse at index 1: children 2, 3 -> Max is 3 -> Swap 1 and 3 -> arr = [5, 3, 4, 2, 1]

Max-Heap Built: [5, 3, 4, 2, 1]

Phase 2: Push to End & Sift-Down
- Swap arr[0] (5) with arr[4] (1) -> [1, 3, 4, 2, | 5]
  heapify(0, size=4) -> [4, 3, 1, 2, | 5]

- Swap arr[0] (4) with arr[3] (2) -> [2, 3, 1, | 4, 5]
  heapify(0, size=3) -> [3, 2, 1, | 4, 5]

- Swap arr[0] (3) with arr[2] (1) -> [1, 2, | 3, 4, 5]
  heapify(0, size=2) -> [2, 1, | 3, 4, 5]

- Swap arr[0] (2) with arr[1] (1) -> [1, | 2, 3, 4, 5]

Final Sorted Array: [1, 2, 3, 4, 5]

-------------------------------------------------

Step-by-Step Execution Trace Table

-------------------------------------------------------------------------------------------------------------------------
Iteration / Step | Active Heap Window | Action Taken                      | Array State After Step
-------------------------------------------------------------------------------------------------------------------------
Build-Heap (i=1) | [0 ... 4]          | heapify(1) -> swap 2 with 5       | [1, 5, 4, 2, 3]
Build-Heap (i=0) | [0 ... 4]          | heapify(0) -> swap 1, 5 then 1, 3 | [5, 3, 4, 2, 1]
Sort Step 1      | size = 5           | Swap arr[0] & arr[4]; heapify(0)  | [4, 3, 1, 2, 5]
Sort Step 2      | size = 4           | Swap arr[0] & arr[3]; heapify(0)  | [3, 2, 1, 4, 5]
Sort Step 3      | size = 3           | Swap arr[0] & arr[2]; heapify(0)  | [2, 1, 3, 4, 5]
Sort Step 4      | size = 2           | Swap arr[0] & arr[1]; heapify(0)  | [1, 2, 3, 4, 5]
-------------------------------------------------------------------------------------------------------------------------

Complexity Analysis:
- Time Complexity :
  - Building Heap: O(n) (mathematically bounded by summation of node depths)
  - Sorting Phase: (n - 1) * O(log n) = O(n log n)[cite: 28]
  - Overall Time Complexity: O(n log n) in all cases (Best, Average, Worst)[cite: 28]
- Space Complexity: O(1) auxiliary space (completely in-place sort; O(log n) if counting recursive call stack, reducible to O(1) with iterative heapify).
=================================================
*/
