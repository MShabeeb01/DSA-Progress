import java.util.ArrayList; // Import ArrayList class

public class Classroom { // Main class

    public static void main(String args[]) { // Driver function
        // 1. Create a 2D (Multi-dimensional) ArrayList
        ArrayList<ArrayList<Integer>> mainList = new ArrayList<>();

        // 2. Create and populate the first sub-list
        ArrayList<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        mainList.add(list); // Add first inner list to mainList

        // 3. Create and populate the second sub-list
        ArrayList<Integer> list2 = new ArrayList<>();
        list2.add(3);
        list2.add(4);
        mainList.add(list2); // Add second inner list to mainList

        // 4. Print the multi-dimensional list directly
        System.out.println(mainList);

        // 5. Traverse and print using nested loops
        for (int i = 0; i < mainList.size(); i++) {
            ArrayList<Integer> currentList = mainList.get(i); // Fetch row
            for (int j = 0; j < currentList.size(); j++) {
                System.out.print(currentList.get(j) + " "); // Fetch column element
            }
            System.out.println();
        }
    }
}

/*
==================== SUMMARY ====================

Approach:
1. Multi-dimensional ArrayLists are created using nested generics:
   ArrayList<ArrayList<Integer>> mainList = new ArrayList<>();
2. Create individual 1D ArrayList instances (e.g., list, list2).
3. Add elements to each inner list via .add().
4. Add the individual lists into the parent list: mainList.add(list).
5. Elements can be of variable sizes (ragged/jagged lists are supported).
6. To traverse, use nested loops:
   - Outer loop iterates over mainList.size() (rows).
   - Inner loop iterates over currentList.size() (columns).

-------------------------------------------------

Step-by-Step Construction Trace

-----------------------------------------------------------------------------------------
Step | Operation              | Inner List State   | mainList State
-----------------------------------------------------------------------------------------
1    | mainList = new ...     | -                  | []
2    | list.add(1); add(2)    | list = [1, 2]      | []
3    | mainList.add(list)     | list = [1, 2]      | [[1, 2]]
4    | list2.add(3); add(4)   | list2 = [3, 4]     | [[1, 2]]
5    | mainList.add(list2)    | list2 = [3, 4]     | [[1, 2], [3, 4]]
-----------------------------------------------------------------------------------------

Traversal Output:
[[1, 2], [3, 4]]
1 2 
3 4 

Time Complexity : 
- Insertion    : O(1) per element
- Traversal    : O(n * m) where n is rows and m is columns

Space Complexity: O(N) total auxiliary space to store all N elements across all sublists

=================================================
*/
