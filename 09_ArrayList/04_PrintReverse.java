import java.util.ArrayList; // Import ArrayList class

public class Classroom { // Main class

    public static void main(String[] args) { // Driver function
        ArrayList<Integer> list = new ArrayList<>(); // Create integer ArrayList

        // Adding elements to the list
        list.add(1); // Index 0
        list.add(2); // Index 1
        list.add(3); // Index 2
        list.add(4); // Index 3
        list.add(5); // Index 4

        // Reverse print
        for (int i = list.size() - 1; i >= 0; i--) { // Start from last index down to 0
            System.out.print(list.get(i) + " "); // Retrieve and print each element with a space
        }
        System.out.println(); // Print newline
    }
}

/*
==================== SUMMARY ====================

Approach:
1. Initialize and populate the ArrayList with elements.
2. Determine the starting point: the last valid index is always (list.size() - 1).
3. Set up a reverse for-loop initializing i = list.size() - 1, running as long as i >= 0, and decrementing i by 1 each step (i--).
4. Access each element using list.get(i) and print inline.
5. Print a newline after completing the traversal.

-------------------------------------------------

Iteration Trace

Input:
list = [1, 2, 3, 4, 5]
list.size() = 5
Starting index = list.size() - 1 = 4

---------------------------------------------------
i (Index) | Condition (i >= 0) | list.get(i) | Output
---------------------------------------------------
4         | 4 >= 0 (true)      | 5           | 5 
3         | 3 >= 0 (true)      | 4           | 5 4 
2         | 2 >= 0 (true)      | 3           | 5 4 3 
1         | 1 >= 0 (true)      | 2           | 5 4 3 2 
0         | 0 >= 0 (true)      | 1           | 5 4 3 2 1 
-1        | -1 >= 0 (false)    | Loop ends   | Terminate
---------------------------------------------------

Output:
5 4 3 2 1

Time Complexity : O(n) where n is the number of elements in the ArrayList
Space Complexity: O(1) auxiliary space

=================================================
*/
