import java.util.ArrayList; // Import ArrayList class

public class Main { // Main class

    public static void main(String[] args) { // Driver function
        // 1. Initialize ArrayList
        ArrayList<Integer> list = new ArrayList<>();

        // 2. Add Element - O(1)
        list.add(1); // Appends 1
        list.add(2); // Appends 2
        list.add(3); // Appends 3
        list.add(4); // Appends 4
        list.add(5); // Appends 5

        // 3. Get Element - O(1)
        int element = list.get(2); // Returns element at index 2

        // 4. Remove Element - O(n)
        list.remove(2); // Removes element at index 2 and shifts remaining elements left

        // 5. Set Element at Index - O(n) / O(1) depending on method
        list.set(2, 10); // Replaces value at index 2 with 10

        // 6. Contains Element - O(n)
        boolean exists = list.contains(10); // Checks whether 10 is present in the list

        // Print outputs
        System.out.println("Element at index 2: " + element);
        System.out.println("Contains 10: " + exists);
        System.out.println("Updated List: " + list);
    }
}

/*
==================== SUMMARY ====================

Core Operations & Complexity:
1. Add Element:
   - Method: list.add(element)
   - Time Complexity: O(1) (Amortized constant time to append at end)

2. Get Element:
   - Method: list.get(index)
   - Time Complexity: O(1) (Direct index lookup via base pointer arithmetic)

3. Remove Element:
   - Method: list.remove(index)
   - Time Complexity: O(n) (Requires linear time to shift subsequent elements left)

4. Set Element at Index:
   - Method: list.set(index, element)
   - Time Complexity: O(n)* / O(1) (In slides listed as O(n) when accounting for shifts/reallocations)

5. Contains Element:
   - Method: list.contains(element)
   - Time Complexity: O(n) (Linear search from index 0 to n - 1)

-------------------------------------------------

Step-by-Step Operation Trace

Initial: list = []

----------------------------------------------------------------------
Step | Operation       | Target / Arg | Resulting List | Explanation
----------------------------------------------------------------------
1    | list.add(1)     | 1            | [1]            | Appends 1 to list
2    | list.add(2)     | 2            | [1, 2]         | Appends 2 to list
3    | list.add(3)     | 3            | [1, 2, 3]      | Appends 3 to list
4    | list.add(4)     | 4            | [1, 2, 3, 4]   | Appends 4 to list
5    | list.add(5)     | 5            | [1, 2, 3, 4, 5]| Appends 5 to list
6    | list.get(2)     | index: 2     | [1, 2, 3, 4, 5]| Returns value 3
7    | list.remove(2)  | index: 2     | [1, 2, 4, 5]   | Removes 3; 4 & 5 shift left
8    | list.set(2, 10) | idx: 2, val  | [1, 2, 10, 5]  | Index 2 overwritten with 10
9    | list.contains(10) | val: 10    | [1, 2, 10, 5]  | Linear search finds 10 -> true
----------------------------------------------------------------------

Output:
Element at index 2: 3
Contains 10: true
Updated List: [1, 2, 10, 5]

=================================================
*/
