import java.util.Arrays;

public class MinAbsoluteDifferencePairs {
    // Operation: Minimum Sum Absolute Difference Pairs (Greedy Approach)
    public static int minAbsoluteDifference(int[] A, int[] B) {
        // Step 1: Sort both arrays in ascending order
        Arrays.sort(A);
        Arrays.sort(B);

        int minDiff = 0;

        // Step 2: Pair elements with corresponding rank (same index)
        for (int i = 0; i < A.length; i++) {
            minDiff += Math.abs(A[i] - B[i]);
        }

        return minDiff;
    }

    public static void main(String[] args) {
        int[] A = {1, 2, 3};
        int[] B = {2, 1, 3};

        System.out.println("Minimum Absolute Difference Sum: " + minAbsoluteDifference(A, B)); // Output: 0

        int[] A2 = {4, 1, 8, 7};
        int[] B2 = {2, 3, 6, 5};

        System.out.println("Minimum Absolute Difference Sum: " + minAbsoluteDifference(A2, B2)); // Output: 6
    }
}

/*
==================== SUMMARY ====================

Problem: Minimum Sum Absolute Difference Pairs

Objective:
Given two arrays A and B of equal length n, pair each element of array A with an element 
of array B such that the total sum of absolute differences across all pairs is minimized:
    Minimize S = sum(|A[i] - B[j]|)

Greedy Choice Property:
- To minimize the distance between paired numbers, pair the closest available values together.
- Sorting both arrays aligns them by rank:
    - Smallest in A pairs with Smallest in B
    - 2nd smallest in A pairs with 2nd smallest in B
    - ...
    - Largest in A pairs with Largest in B
- Rearrangement Inequality guarantees that pairing values in the same monotonic order minimizes 
  the total metric distance.

Algorithmic Steps:
1. Sort Array A in ascending order: `Arrays.sort(A)`.
2. Sort Array B in ascending order: `Arrays.sort(B)`.
3. Iterate from `i = 0` to `n - 1`:
   - Calculate absolute difference: `Math.abs(A[i] - B[i])`.
   - Accumulate into running sum: `minDiff += Math.abs(A[i] - B[i])`.
4. Return `minDiff`.

-------------------------------------------------

Pairing Alignment Visual

Input:
  A = [4, 1, 8, 7]
  B = [2, 3, 6, 5]

After Sorting:
  A_sorted = [ 1,  4,  7,  8 ]
               |   |   |   |   (Pair vertically)
  B_sorted = [ 2,  3,  5,  6 ]

Absolute differences:
  |1 - 2| = 1
  |4 - 3| = 1
  |7 - 5| = 2
  |8 - 6| = 2

Total Minimum Sum S = 1 + 1 + 2 + 2 = 6

-------------------------------------------------

Step-by-Step Trace Table

Test Case: A = [1, 2, 3], B = [2, 1, 3]
After Sorting: A = [1, 2, 3], B = [1, 2, 3]

---------------------------------------------------------------------------------------------------------
Index `i` | Element `A[i]` | Element `B[i]` | Absolute Difference `|A[i] - B[i]|` | Running `minDiff` Sum
---------------------------------------------------------------------------------------------------------
0         | 1              | 1              | |1 - 1| = 0                         | 0
1         | 2              | 2              | |2 - 2| = 0                         | 0 + 0 = 0
2         | 3              | 3              | |3 - 3| = 0                         | 0 + 0 = 0
---------------------------------------------------------------------------------------------------------
Final Output: 0

Complexity Analysis:
- Time Complexity : O(n log n) — Dominated by sorting both arrays of length $n$.
- Space Complexity: O(1) or O(log n) — Depending on Java's `DualPivotQuicksort` primitive recursion stack.
=================================================
*/
