import java.util.Arrays;
import java.util.Comparator;

public class FractionalKnapsack {
    // Operation: Fractional Knapsack Problem (Greedy Approach)
    public static double getMaxValue(int[] val, int[] weight, int capacity) {
        int n = val.length;

        // ratio[i][0] = original index
        // ratio[i][1] = value-to-weight ratio (val / weight)
        double ratio[][] = new double[n][2];

        for (int i = 0; i < n; i++) {
            ratio[i][0] = i;
            ratio[i][1] = (double) val[i] / weight[i];
        }

        // Sort items in ascending order based on value/weight ratio
        Arrays.sort(ratio, Comparator.comparingDouble(o -> o[1]));

        double totalVal = 0;
        int currentCapacity = capacity;

        // Traverse in descending order (highest ratio to lowest ratio)
        for (int i = n - 1; i >= 0; i--) {
            int idx = (int) ratio[i][0];

            // If full item fits into remaining knapsack capacity
            if (currentCapacity >= weight[idx]) {
                totalVal += val[idx];
                currentCapacity -= weight[idx];
            } else {
                // Take only the fractional part that fits
                totalVal += ratio[i][1] * currentCapacity;
                currentCapacity = 0;
                break; // Knapsack is completely filled
            }
        }

        return totalVal;
    }

    public static void main(String args[]) {
        int val[] = {60, 100, 120};
        int weight[] = {10, 20, 30};
        int W = 50;

        double maxTotalValue = getMaxValue(val, weight, W);
        System.out.println("Maximum value in Knapsack = " + maxTotalValue); // Output: 240.0
    }
}

/*
==================== SUMMARY ====================

Problem: Fractional Knapsack

Objective:
Given weights and values of $N$ items, put these items in a knapsack of capacity $W$ 
to maximize the total value. Unlike the 0/1 Knapsack problem (Dynamic Programming), 
fractions of items can be taken.

Greedy Choice Property:
- Calculate the **Value-to-Weight Ratio** for each item:
    Ratio = Value / Weight
- Always prioritize items offering the highest return per unit weight (highest ratio).

Algorithmic Steps:
1. Compute Ratio:
   - For every item $i$, calculate `ratio = (double) val[i] / weight[i]`.
2. Sort:
   - Sort items by ratio in descending order.
3. Pick Items Greedily:
   - If `currentCapacity >= weight[i]`:
     - Take the entire item: `totalVal += val[i]`, reduce capacity `currentCapacity -= weight[i]`.
   - Else (`currentCapacity < weight[i]`):
     - Take the fraction: `totalVal += ratio * currentCapacity`.
     - Knapsack is full (`currentCapacity = 0`); terminate loop.

-------------------------------------------------

Ratio Calculation & Item Packing Visual

Input:
  val    = [60, 100, 120]
  weight = [10,  20,  30]
  W      = 50

Step 1: Compute Ratios
  Item 0: 60  / 10 = 6.0 / unit weight
  Item 1: 100 / 20 = 5.0 / unit weight
  Item 2: 120 / 30 = 4.0 / unit weight

Order of Priority (Highest Ratio -> Lowest):
  Item 0 (ratio 6) -> Item 1 (ratio 5) -> Item 2 (ratio 4)

Step 2: Fill Knapsack (Capacity = 50)
  +--------------------------------------------------------+
  | Item 0: wt = 10, val = 60    | Rem Capacity: 50 - 10 = 40
  +--------------------------------------------------------+
  | Item 1: wt = 20, val = 100   | Rem Capacity: 40 - 20 = 20
  +--------------------------------------------------------+
  | Item 2: wt = 30 (Only 20 fits)| Fractional: 20 * 4 = 80
  +--------------------------------------------------------+
  Total Value = 60 + 100 + 80 = 240

-------------------------------------------------

Step-by-Step Trace Table

-------------------------------------------------------------------------------------------------------------------
Item Index | Weight | Value | Ratio (val/wt) | Remaining W | Decision / Contribution          | Cumulative Value
-------------------------------------------------------------------------------------------------------------------
0          | 10     | 60    | 6.0            | 50          | Full Item (10 <= 50, rem W = 40) | 60
1          | 20     | 100   | 5.0            | 40          | Full Item (20 <= 40, rem W = 20) | 60 + 100 = 160
2          | 30     | 120   | 4.0            | 20          | Fraction (20/30 of 120 = 80)     | 160 + 80 = 240
-------------------------------------------------------------------------------------------------------------------
Final Result: 240.0

Complexity Analysis:
- Time Complexity : O(n log n) — Due to sorting the ratio array of $n$ items.
- Space Complexity: O(n) — Auxiliary 2D array used to track original indices and ratios.
=================================================
*/
