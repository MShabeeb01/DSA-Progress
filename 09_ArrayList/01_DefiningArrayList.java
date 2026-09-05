import java.util.ArrayList; // Import ArrayList class

public class Main { // Main class

    public static void main(String[] args) { // Driver function
        // Java Collection Framework
        // Syntax: ClassName objectName = new ClassName();

        ArrayList<Integer> list = new ArrayList<>(); // Dynamic list to store Integer wrapper objects
        ArrayList<String> list2 = new ArrayList<>(); // Dynamic list to store String objects
        ArrayList<Boolean> list3 = new ArrayList<>(); // Dynamic list to store Boolean wrapper objects

        // Adding sample elements
        list.add(10); // Append 10 to Integer list
        list.add(20); // Append 20 to Integer list

        list2.add("Apple"); // Append "Apple" to String list

        list3.add(true); // Append true to Boolean list

        System.out.println("Integer List: " + list); // Print integer list contents
        System.out.println("String List: " + list2); // Print string list contents
        System.out.println("Boolean List: " + list3); // Print boolean list contents
    }
}

/*
==================== SUMMARY ====================

Approach:
1. Import ArrayList from java.util.
2. Declare ArrayList specifying the object type inside angle brackets (<Type>).
3. Use wrapper classes (Integer, Float, Boolean, etc.) instead of primitives (int, float, boolean).
4. Initialize using the new keyword.
5. Use built-in methods (e.g., .add(), .get(), .remove()) to manipulate data dynamically.

-------------------------------------------------

Iteration / Operation Trace

Input:
list operations: add(10) -> add(20)

---------------------------------------------------
Step | Operation      | Element | Resulting List
---------------------------------------------------
1    | list.add(10)   | 10      | [10]
2    | list.add(20)   | 20      | [10, 20]
3    | list2.add(...) | "Apple" | ["Apple"]
4    | list3.add(...) | true    | [true]
---------------------------------------------------

Output:
Integer List: [10, 20]
String List: [Apple]
Boolean List: [true]

Time Complexity : 
- Initialization : O(1)
- Add element    : O(1) amortized

Space Complexity: O(n) where n is the number of stored elements

=================================================
*/
