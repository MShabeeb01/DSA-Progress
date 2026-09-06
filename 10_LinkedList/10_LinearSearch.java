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

    // Operation: Search (Iterative) / Linear Search on a Linked List
    public int itrSearch(int key) {
        Node temp = head; // Start scanning from head
        int i = 0;        // Tracks 0-based index position

        // Traverse each node until end of the list (null)
        while (temp != null) {
            if (temp.data == key) { // Key found
                return i;           // Return index/position
            }
            temp = temp.next;       // Advance pointer to next node
            i++;                    // Increment index counter
        }

        // Key not found in the entire list
        return -1;
    }

    public static void main(String[] args) {
        LinkedList ll = new LinkedList();

        // Building list: [1] -> [2] -> [3] -> [4] -> [5] -> null
        head = new Node(1);
        head.next = new Node(2);
        head.next.next = new Node(3);
        head.next.next.next = new Node(4);
        head.next.next.next.next = new Node(5);

        int key = 4;
        int idx = ll.itrSearch(key);

        System.out.println("Key " + key + " found at index: " + idx); // Output: 3
    }
}

/*
==================== SUMMARY ====================

Operation: itrSearch(int key) [Linear Search]

Problem Statement:
Search for a target `key` in the singly linked list. 
- Return the index (0-based) where it is found.
- If not present anywhere in the list, return `-1`.

Algorithm Steps:
1. Initialize Traversal Pointer & Index Tracker:
   - `Node temp = head;`
   - `int i = 0;`
2. Traversal Loop (`while (temp != null)`):
   - Compare current node data: `if (temp.data == key)` -> `return i;`
   - Move forward: `temp = temp.next;`
   - Increment tracker: `i++;`
3. Fallback (Key Not Found):
   - If traversal finishes (`temp == null`), return `-1`.

-------------------------------------------------

Pointer Traversal Diagram

Target: key = 4
List:   [1] -> [2] -> [3] -> [4] -> [5] -> null

i = 0:  temp points to [1] -> 1 == 4 (false) -> move temp, i++
i = 1:  temp points to [2] -> 2 == 4 (false) -> move temp, i++
i = 2:  temp points to [3] -> 3 == 4 (false) -> move temp, i++
i = 3:  temp points to [4] -> 4 == 4 (true)  -> return 3

           head                                 temp
             |                                    |
             v                                    v
Index:       0          1          2              3              4
          +-----+    +-----+    +-----+        +-----+        +-----+
          |  1  | -> |  2  | -> |  3  |  --->  |  4  |  --->  |  5  | ---> null
          +-----+    +-----+    +-----+        +-----+        +-----+
                                                  ^
                                             (Found Key!)

-------------------------------------------------

Step-by-Step Trace

Searching for `key = 4` in `[1, 2, 3, 4, 5]`

-----------------------------------------------------------------------------------------
Iteration | Index (i) | temp.data | Comparison (temp.data == key) | Action Taken
-----------------------------------------------------------------------------------------
1         | 0         | 1         | 1 == 4 (false)                | temp = temp.next, i = 1
2         | 1         | 2         | 2 == 4 (false)                | temp = temp.next, i = 2
3         | 2         | 3         | 3 == 4 (false)                | temp = temp.next, i = 3
4         | 3         | 4         | 4 == 4 (true)                 | Return i (3)
-----------------------------------------------------------------------------------------

Complexity Analysis:
- Time Complexity : O(n) — Worst-case visits all n nodes if element is at the end or absent.
- Space Complexity: O(1) — Uses only constant auxiliary variables (`temp` and `i`).
=================================================
*/
