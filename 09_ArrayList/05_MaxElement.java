import java.util.ArrayList; // Import ArrayList class

public class Classroom { // Main class

    public static void main(String args[]) { // Driver function
        ArrayList<Integer> list = new ArrayList<>(); // Create integer ArrayList

        // Adding elements to the list
        list.add(2); // Index 0
        list.add(5); // Index 1
        list.add(9); // Index 2
        list.add(6); // Index 3
        list.add(8); // Index 4

        // Initialize max with the smallest possible integer value
        int max = Integer.MIN_VALUE;

        // Traverse the ArrayList to find the maximum element
        for (int i = 0; i < list.size(); i++) {
            if (max < list.get(i)) { // If current element is greater than current max
                max = list.get(i);   // Update max
            }
            // Alternative one-liner: max = Math.max(max, list.get(i));
        }

        // Print the maximum element
        System.out.println("max element = " + max);
    }
}

/*
==================== SUMMARY ====================

Approach:
1. Initialize a variable `max` to `Integer.MIN_VALUE` (-2^31) to safely handle negative values.
2. Iterate through each index of the ArrayList from 0 to list.size() - 1.
3. Compare the current element `list.get(i)` with `max`.
4. If `list.get(i)` is strictly greater than `max`, update `max = list.get(i)`.
5. After completing the loop, print the maximum value found.

-------------------------------------------------

Iteration Trace

Input:
list = [2, 5, 9, 6, 8]
Initial max = Integer.MIN_VALUE (-2147483648)

----------------------------------------------------------------------
i | list.get(i) | Condition (max < list.get(i)) | Updated max
----------------------------------------------------------------------
0 | 2           | -2147483648 < 2 (true)        | 2
1 | 5           | 2 < 5 (true)                  | 5
2 | 9           | 5 < 9 (true)                  | 9
3 | 6           | 9 < 6 (false)                 | 9
4 | 8           | 9 < 8 (false)                 | 9
----------------------------------------------------------------------

Output:
max element = 9

Time Complexity : O(n) where n is the number of elements in the ArrayList
Space Complexity: O(1) auxiliary space

=================================================
*/
