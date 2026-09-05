import java.util.ArrayList; // Import ArrayList class

public class Classroom { // Main class

    // Method to swap two elements in an ArrayList given their indices
    public static void swap(ArrayList<Integer> list, int idx1, int idx2) {
        int temp = list.get(idx1);              // Store element at idx1 in temporary variable
        list.set(idx1, list.get(idx2));          // Set element at idx1 with value from idx2
        list.set(idx2, temp);                    // Set element at idx2 with value stored in temp
    }

    public static void main(String args[]) { // Driver function
        ArrayList<Integer> list = new ArrayList<>(); // Create integer ArrayList

        // Adding elements to the list
        list.add(2); // Index 0
        list.add(5); // Index 1
        list.add(9); // Index 2
        list.add(3); // Index 3
        list.add(6); // Index 4

        int idx1 = 1, idx2 = 3; // Indices to swap

        System.out.println(list); // Print list before swapping

        swap(list, idx1, idx2); // Perform swap operation

        System.out.println(list); // Print list after swapping
    }
}

/*
==================== SUMMARY ====================

Approach:
1. Define a helper function `swap(list, idx1, idx2)`.
2. Retrieve and save the element at `idx1` inside a temporary variable `temp` using `list.get(idx1)`.
3. Overwrite the element at `idx1` with the value from `idx2` using `list.set(idx1, list.get(idx2))`.
4. Overwrite the element at `idx2` with `temp` using `list.set(idx2, temp)`.
5. Since ArrayList objects are passed by reference, the modifications reflect directly in the original list.

-------------------------------------------------

Iteration / Step-by-Step Trace

Input List : [2, 5, 9, 3, 6]
Indices    : idx1 = 1, idx2 = 3

-------------------------------------------------------------------------------------
Step | Action                               | Current State of list | Explanation
-------------------------------------------------------------------------------------
1    | int temp = list.get(1)               | [2, 5, 9, 3, 6]       | temp = 5
2    | list.set(1, list.get(3))             | [2, 3, 9, 3, 6]       | Index 1 set to 3
3    | list.set(3, temp)                    | [2, 3, 9, 5, 6]       | Index 3 set to 5
-------------------------------------------------------------------------------------

Output:
[2, 5, 9, 3, 6]
[2, 3, 9, 5, 6]

Time Complexity : O(1) (Direct index lookups and updates via .get() and .set())
Space Complexity: O(1) auxiliary space (Only uses a single integer variable `temp`)

=================================================
*/
