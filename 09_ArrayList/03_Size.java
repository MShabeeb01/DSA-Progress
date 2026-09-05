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

        // 1. Get the size of the ArrayList
        System.out.println(list.size()); // Prints total number of elements: 5

        // 2. Print elements of the ArrayList using a for loop
        // //print the arraylist
        for (int i = 0; i < list.size(); i++) { // Traverse from index 0 to size - 1
            System.out.print(list.get(i) + " "); // Fetch each element using .get() and print with space
        }
        System.out.println(); // Print newline
    }
}

/*
==================== SUMMARY ====================

Approach:
1. Initialize an ArrayList and populate it with elements using .add().
2. Use list.size() method to obtain the total count of elements.
3. Traverse the list sequentially using a standard for loop from i = 0 up to list.size() - 1.
4. Retrieve the element at each index using list.get(i).
5. Print each retrieved element inline separated by spaces.

-------------------------------------------------

Iteration Trace

Input:
list = [1, 2, 3, 4, 5]
list.size() = 5

---------------------------------------------------
i (Index) | Condition (i < 5) | list.get(i) | Output
---------------------------------------------------
0         | 0 < 5 (true)      | 1           | 1 
1         | 1 < 5 (true)      | 2           | 1 2 
2         | 2 < 5 (true)      | 3           | 1 2 3 
3         | 3 < 5 (true)      | 4           | 1 2 3 4 
4         | 4 < 5 (true)      | 5           | 1 2 3 4 5 
5         | 5 < 5 (false)     | Loop ends   | Terminate
---------------------------------------------------

Output:
5
1 2 3 4 5 

Time Complexity : 
- list.size()  : O(1)
- Traversal    : O(n) where n is the number of elements

Space Complexity: O(1) auxiliary space (ignoring storage of the list)

=================================================
*/
