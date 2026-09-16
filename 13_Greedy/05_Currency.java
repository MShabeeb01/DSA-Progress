import java.util.ArrayList;

public class IndianCoins {
    // Operation: Indian Coins / Currency Change Problem (Greedy Approach)
    public static int getMinCoins(int denominations[], int V) {
        int countOfCoins = 0;
        ArrayList<Integer> usedCoins = new ArrayList<>();

        // Traverse denominations in descending order (largest value to smallest)
        for (int i = denominations.length - 1; i >= 0; i--) {
            int coin = denominations[i];

            // Use the largest possible denomination as many times as possible
            while (V >= coin) {
                countOfCoins++;
                usedCoins.add(coin);
                V -= coin;
            }

            // Early exit if the amount is fully settled
            if (V == 0) {
                break;
            }
        }

        // Display individual coins/notes chosen
        System.out.print("Coins/Notes used: ");
        for (int coin : usedCoins) {
            System.out.print(coin + " ");
        }
        System.out.println();

        return countOfCoins;
    }

    public static void main(String args[]) {
        // Standard canonical Indian coin/note denominations (pre-sorted ascending)
        int denominations[] = {1, 2, 5, 10, 20, 50, 100, 500, 2000};

        int V1 = 121;
        System.out.println("Total coins for " + V1 + ": " + getMinCoins(denominations, V1));
        // Output: Coins: 100 20 1 | Count: 3

        int V2 = 590;
        System.out.println("Total coins for " + V2 + ": " + getMinCoins(denominations, V2));
        // Output: Coins: 500 50 20 20 | Count: 4
    }
}

/*
==================== SUMMARY ====================

Problem: Indian Coins Change

Objective:
Given an infinite supply of currency denominations:
[1, 2, 5, 10, 20, 50, 100, 500, 2000],
find the minimum number of coins/notes required to make change for an amount V.

Greedy Choice Property:
- Always pick the **largest possible denomination** that is less than or equal to the remaining value $V$.
- Subtract that denomination from $V$ and repeat until $V = 0$.
- Why Greedy Works Here:
  The Indian currency system is a "canonical coin system" where every larger denomination 
  is sufficiently large relative to smaller denominations, ensuring the local optimal choice 
  always yields the globally optimal minimum count.
  (Note: For arbitrary non-canonical systems like [1, 7, 10] with V = 14, Dynamic Programming is required).

Algorithmic Steps:
1. Denominations Array:
   - Given `denominations[]` in ascending order.
2. Descending Scan:
   - Iterate from the largest denomination (`denominations.length - 1`) down to index `0`.
3. Greedy Consumption:
   - While `V >= denominations[i]`:
     - Deduct `V -= denominations[i]`.
     - Increment `countOfCoins++`.
     - Track coin in `usedCoins` list.
4. Termination:
   - Break when `V == 0` and return total count.

-------------------------------------------------

Coin Subtraction Lifecycle Visual

Target: V = 590
Available: [1, 2, 5, 10, 20, 50, 100, 500, 2000]

Step 1: Check 2000 -> 590 < 2000 (Skip)
Step 2: Check 500  -> 590 >= 500 -> Pick 500 -> Remainder: 590 - 500 = 90
Step 3: Check 100  -> 90 < 100  (Skip)
Step 4: Check 50   -> 90 >= 50  -> Pick 50  -> Remainder: 90 - 50 = 40
Step 5: Check 20   -> 40 >= 20  -> Pick 20  -> Remainder: 40 - 20 = 20
                      20 >= 20  -> Pick 20  -> Remainder: 20 - 20 = 0
Step 6: V == 0 -> Done!

Selected Currency: 500 + 50 + 20 + 20 = 590
Total Count = 4

-------------------------------------------------

Step-by-Step Trace Table

Test Case 1: V = 121

---------------------------------------------------------------------------------------------------------
Denomination Considered | Condition (`V >= coin`) | Action Taken / Coin Used | Remaining V | Coin Count
---------------------------------------------------------------------------------------------------------
2000                    | 121 >= 2000 (False)     | Skip                     | 121         | 0
500                     | 121 >= 500 (False)      | Skip                     | 121         | 0
100                     | 121 >= 100 (True)       | Use 100                  | 21          | 1
50                      | 21 >= 50 (False)        | Skip                     | 21          | 1
20                      | 21 >= 20 (True)         | Use 20                   | 1           | 2
10                      | 1 >= 10 (False)         | Skip                     | 1           | 2
5                       | 1 >= 5 (False)          | Skip                     | 1           | 2
2                       | 1 >= 2 (False)          | Skip                     | 1           | 2
1                       | 1 >= 1 (True)           | Use 1                    | 0           | 3
---------------------------------------------------------------------------------------------------------
Result: 3 Coins/Notes (100, 20, 1)

Complexity Analysis:
- Time Complexity : O(V) in worst case (e.g., all 1s), or O(number of denominations) using direct division: `count += V / coin; V %= coin;`.
- Space Complexity: O(1) auxiliary space (excluding list used to print coins).
=================================================
*/
