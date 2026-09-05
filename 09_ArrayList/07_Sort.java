import java.util.ArrayList;   // Import ArrayList class
import java.util.Collections; // Import Collections utility class

public class Classroom { // Main class

    public static void main(String args[]) { // Driver function
        ArrayList<Integer> list = new ArrayList<>(); // Create integer ArrayList

        // Adding elements to the list
        list.add(2); // Index 0
        list.add(5); // Index 1
        list.add(9); // Index 2
        list.add(3); // Index 3
        list.add(6); // Index 4

        System.out.println(list); // Print original unsorted list

        // Ascending Order Sorting
        Collections.sort(list); // Sorts the list in natural ascending order
        System.out.println(list); // Print list sorted in ascending order

        // Descending Order Sorting
        Collections.sort(list, Collections.reverseOrder()); // Sorts using reverse comparator
        System.out.println(list); // Print list sorted in descending order
    }
}

/*
==================== SUMMARY ====================

Approach:
1. Use the built-in utility class `Collections` from `java.util`.
2. Ascending Order:
   - Call `Collections.sort(list)`.
   - Sorts elements in natural ascending order in-place (uses Dual-Pivot Quicksort / TimSort).
3. Descending Order:
   - Call `Collections.sort(list, Collections.reverseOrder())`.
   - Passes a comparator `Collections.reverseOrder()` to invert the sorting logic.

-------------------------------------------------

Step-by-Step Operation Trace

Input: list = [2, 5, 9, 3, 6]

---------------------------------------------------------------------------------------------------
Step | Operation                                           | Resulting List   | Order Type
---------------------------------------------------------------------------------------------------
1    | System.out.println(list)                            | [2, 5, 9, 3, 6]  | Original / Unsorted
2    | Collections.sort(list)                              | [2, 3, 5, 6, 9]  | Ascending Order
3    | Collections.sort(list, Collections.reverseOrder())  | [9, 6, 5, 3, 2]  | Descending Order
---------------------------------------------------------------------------------------------------

Output:
[2, 5, 9, 3, 6]
[2, 3, 5, 6, 9]
[9, 6, 5, 3, 2]

Time Complexity : 
- Both Ascending & Descending Sort: O(n log n) where n is the number of elements

Space Complexity: 
- O(n) auxiliary space (TimSort internal buffering)

=================================================
*/
