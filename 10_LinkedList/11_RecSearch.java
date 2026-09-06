public class LinkedList {

    public static class Node {
        int data;
        Node next;

        public Node(int data) {
            this.data = data;
            this.next = null;
        }
    }

    public static Node head;
    public static Node tail;

    // Recursive helper function to find key index
    private int helper(Node head, int key) {
        // Base Case 1: Reached end of the list without finding the key
        if (head == null) {
            return -1;
        }

        // Base Case 2: Key found at current head node
        if (head.data == key) {
            return 0; // Relative index from current head is 0
        }

        // Recursive Step: Look for key in the rest of the list
        int idx = helper(head.next, key);

        // If key was not found in downstream nodes
        if (idx == -1) {
            return -1;
        }

        // Return current absolute index by adding 1 to the recursive result
        return idx + 1;
    }

    // Public search wrapper method
    public int recSearch(int key) {
        return helper(head, key);
    }

    public static void main(String[] args) {
        LinkedList ll = new LinkedList();

        // Building list: [1] -> [2] -> [3] -> [4] -> null
        head = new Node(1);
        head.next = new Node(2);
        head.next.next = new Node(3);
        head.next.next.next = new Node(4);

        int key = 3;
        int idx = ll.recSearch(key);

        System.out.println("Key " + key + " found at index: " + idx); // Output: 2
    }
}

/*
==================== SUMMARY ====================

Operation: recSearch(int key) [Recursive Search]

Core Logic & Recurrence:
1. Base Cases:
   - `head == null`: Key is absent in the entire list -> return `-1`.
   - `head.data == key`: Found target at current head -> return `0`.
2. Recursive Call:
   - Search the remaining list: `helper(head.next, key)`.
3. Backtracking / Index Adjustment:
   - If downstream call returns `-1`, propagate `-1`.
   - If found, shift the relative index by adding 1: `idx + 1`.

-------------------------------------------------

Call Stack & Backtracking Visualization

List: [1] -> [2] -> [3] -> [4] -> null
Target: key = 3

--- Forward Calls (Push to Stack) ---
helper(Node(1), 3)
  └── helper(Node(2), 3)
        └── helper(Node(3), 3)  <-- head.data == key (3 == 3) -> Returns 0

--- Return Phase (Pop & Add 1) ---
Stack Level 3: helper(Node(3)) returns 0
Stack Level 2: helper(Node(2)) receives 0 -> returns (0 + 1) = 1
Stack Level 1: helper(Node(1)) receives 1 -> returns (1 + 1) = 2

Final Result: 2

-------------------------------------------------

Step-by-Step Call Trace

List: 1 -> 2 -> 3 -> 4 -> null | Key: 3

-----------------------------------------------------------------------------------------
Call Level | Node Checked | Condition Evaluated         | Return Value Passed Upwards
-----------------------------------------------------------------------------------------
1          | Node(1)      | 1 == 3 (false), recurse     | receives 1 -> returns 1 + 1 = 2
2          | Node(2)      | 2 == 3 (false), recurse     | receives 0 -> returns 0 + 1 = 1
3          | Node(3)      | 3 == 3 (true), Base Case 2  | returns 0 directly
-----------------------------------------------------------------------------------------
Final Output: 2

Complexity Analysis:
- Time Complexity : O(n) — Traverses each node at most once.
- Space Complexity: O(n) — Auxiliary stack space due to recursive call stack frames.
=================================================
*/
