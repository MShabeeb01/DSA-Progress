import java.util.Arrays;
import java.util.Comparator;

public class MaxLengthChainOfPairs {
    // Operation: Maximum Length Chain of Pairs (LeetCode 646 - Greedy Approach)
    public static int findLongestChain(int[][] pairs) {
        // Step 1: Sort pairs in ascending order based on their second element (end point)
        Arrays.sort(pairs, Comparator.comparingInt(a -> a[1]));

        // Step 2: Always select the first pair (ends earliest)
        int chainLen = 1;
        int chainEnd = pairs[0][1]; // End of the currently chosen pair

        // Step 3: Iterate through remaining pairs
        for (int i = 1; i < pairs.length; i++) {
            // Chaining condition: a pair (c, d) can follow (a, b) if b < c
            if (pairs[i][0] > chainEnd) {
                chainLen++;
                chainEnd = pairs[i][1]; // Update chain end to current pair's end
            }
        }

        return chainLen;
    }

    public static void main(String[] args) {
        int pairs[][] = {
            {5, 24},
            {39, 60},
            {5, 28},
            {27, 40},
            {50, 90}
        };

        System.out.println("Maximum Length Chain = " + findLongestChain(pairs)); // Output: 3
    }
}

/*
==================== SUMMARY ====================

Problem: LeetCode 646 - Maximum Length Chain of Pairs

Objective:
Given $n$ pairs where for each pair $(a, b)$, $a < b$. A pair $(c, d)$ can come after $(a, b)$ 
if and only if $b < c$. Find the length of the longest chain that can be formed.

Equivalence to Activity Selection:
- Pair $(a, b) \equiv$ Activity [start, end].
- Condition $b < c \equiv$ Disjoint intervals where next start time is strictly greater than previous finish time.
- To maximize chain length, greedily pick pairs that finish the earliest (smallest second element).

Algorithmic Steps:
1. Sort:
   - Sort the pairs based on the 2nd number (`pairs[i][1]`) in ascending order.
2. Base Selection:
   - Select the 1st pair: `chainLen = 1`, `chainEnd = pairs[0][1]`.
3. Greedy Extension:
   - For every subsequent pair $i$:
     - Check if `pairs[i][0] > chainEnd`.
     - If true: valid chain extension $\implies$ `chainLen++`, update `chainEnd = pairs[i][1]`.
     - Otherwise: skip (overlapping).

-------------------------------------------------

Chain Extension Visual

Input Pairs: (5, 24), (39, 60), (5, 28), (27, 40), (50, 90)

Step 1: Sort by second element:
  Pair 1: (5, 24)   -> end = 24
  Pair 2: (5, 28)   -> end = 28
  Pair 3: (27, 40)  -> end = 40
  Pair 4: (39, 60)  -> end = 60
  Pair 5: (50, 90)  -> end = 90

Step 2: Chain Construction:
  Select (5, 24)   -> chainEnd = 24
  Check  (5, 28)   -> 5 > 24 is False (Skip)
  Check  (27, 40)  -> 27 > 24 is True  -> Select (27, 40) -> chainEnd = 40
  Check  (39, 60)  -> 39 > 40 is False (Skip)
  Check  (50, 90)  -> 50 > 40 is True  -> Select (50, 90) -> chainEnd = 90

Longest Chain: (5, 24) -> (27, 40) -> (50, 90)
Total Length = 3

-------------------------------------------------

Step-by-Step Trace Table

---------------------------------------------------------------------------------------------------------
Sorted Pair `(c, d)` | `chainEnd` Before | Condition (`c > chainEnd`) | Action Taken       | `chainEnd` After
---------------------------------------------------------------------------------------------------------
(5, 24)              | -                 | First Pair (Always picked) | Pick (Len = 1)     | 24
(5, 28)              | 24                | 5 > 24 (False)             | Discard (Overlap)  | 24
(27, 40)             | 24                | 27 > 24 (True)             | Pick (Len = 2)     | 40
(39, 60)             | 40                | 39 > 40 (False)            | Discard (Overlap)  | 40
(50, 90)             | 40                | 50 > 40 (True)             | Pick (Len = 3)     | 90
---------------------------------------------------------------------------------------------------------
Result: Maximum Chain Length = 3

Complexity Analysis:
- Time Complexity : O(n log n) — Due to sorting $n$ pairs by their second value.
- Space Complexity: O(1) or O(log n) — In-place array sorting using primitive comparator stack space.
=================================================
*/
